package ch.heigvd.gamification.service;

import ch.heigvd.gamification.api.EventController;
import ch.heigvd.gamification.model.*;
import ch.heigvd.gamification.repository.*;
import ch.heigvd.gamification.util.ModelToDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EventProcessor {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RulesRepository rulesRepository;

    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private BadgeRepository badgeRepository;

    @Async
    @Transactional
    public Boolean processEvent(Event event, String xApiKey) {
        return true;
        Application application = applicationRepository.findByAppKey(xApiKey);

        if (application != null) {
            User user = userRepository.findByRemoteUserIdAndApplication(event.getRemoteUserId(), application);

            if (user == null)
                user = new User(application, event.getRemoteUserId());

            Event _event = ModelToDTOConverter.convert(event);
            _event.setUser(user);
            eventRepository.save(_event);
            List<Rule> rules = rulesRepository.findAllByTypeAndBadge_Application(event.getType(), event.getUser().getApplication());

            for (Rule rule : rules) {
                if (rule.getConditions().size() > 0 && !event.checkProperties(rule.getConditions()))
                    continue;

                Reward reward = rewardRepository.findByUserAndRule(event.getUser(), rule);
                if (reward == null)
                    reward = new Reward(event.getUser(), rule, 0);

                reward.setNbPoints(reward.getNbPoints() + event.getQuantityFromProperties());
                rewardRepository.save(reward);

                boolean exist = badgeRepository.existsByUsersContainsAndId(reward.getUser(), rule.getBadge().getId());
                if (reward.getNbPoints() >= rule.getQuantity() && !exist) {
                    rule.getBadge().getUsers().add(reward.getUser());
                    badgeRepository.save(rule.getBadge());
                }
            }
            return true;
        } else {
            return false;
        }
    }

}
