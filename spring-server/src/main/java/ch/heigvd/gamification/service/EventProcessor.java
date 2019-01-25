package ch.heigvd.gamification.service;

import ch.heigvd.gamification.api.dto.EventDTO;
import ch.heigvd.gamification.model.*;
import ch.heigvd.gamification.repository.*;
import ch.heigvd.gamification.util.ModelToDTOConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EventProcessor {

    private ApplicationRepository applicationRepository;

    private EventRepository eventRepository;

    private RulesRepository rulesRepository;

    private RewardRepository rewardRepository;

    private BadgeRepository badgeRepository;

    private UserRepository userRepository;

    public EventProcessor(ApplicationRepository applicationRepository, BadgeRepository badgeRepository, EventRepository eventRepository, RulesRepository rulesRepository, RewardRepository rewardRepository, UserRepository userRepository) {
        this.applicationRepository = applicationRepository;
        this.eventRepository = eventRepository;
        this.rulesRepository = rulesRepository;
        this.rewardRepository = rewardRepository;
        this.badgeRepository = badgeRepository;
        this.userRepository = userRepository;
    }

    @Async
    @Transactional
    public Boolean processEvent(EventDTO event, String xApiKey) {
        Application application = applicationRepository.findByAppKey(xApiKey);
        if (application != null) {
            User user = userRepository.findByRemoteUserIdAndApplication(event.getRemoteUserId(), application);

            if (user == null)
                user = new User(application, event.getRemoteUserId());

            Event _event = ModelToDTOConverter.convert(event);
            _event.setUser(user);
            eventRepository.save(_event);
            List<Rule> rules = rulesRepository.findAllByTypeAndBadge_Application(event.getType(), _event.getUser().getApplication());

            for (Rule rule : rules) {
                if (rule.getConditions().size() <= 0 || _event.checkProperties(rule.getConditions())) {
                    Reward reward = rewardRepository.findByUserAndRule(_event.getUser(), rule);
                    if (reward == null)
                        reward = new Reward(_event.getUser(), rule, 0);

                    reward.setNbPoints(reward.getNbPoints() + _event.getQuantityFromProperties());
                    rewardRepository.save(reward);

                    boolean exist = badgeRepository.existsByUsersContainsAndId(reward.getUser(), rule.getBadge().getId());
                    if (reward.getNbPoints() >= rule.getQuantity() && !exist) {
                        rule.getBadge().getUsers().add(reward.getUser());
                        badgeRepository.save(rule.getBadge());
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

}
