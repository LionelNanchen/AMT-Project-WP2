package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.EventDTO;
import ch.heigvd.gamification.model.*;
import ch.heigvd.gamification.repository.*;
import ch.heigvd.gamification.util.ModelToDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Authors: Amrani Kamil, Nanchen Lionel, Nicole Olivier, Reka Mentor
 * AMT WP2 2018-2019
 */
@RestController
public class EventController implements EventsApi {

    interface EventProcessor {
        @Async
        @Transactional
        void process(Event event);
    }

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

    @Override
    public ResponseEntity<EventDTO> reportEvent(String xApiKey, EventDTO event) {
        Application application = applicationRepository.findByAppKey(xApiKey);

        if (application != null) {
            User user = userRepository.findByRemoteUserIdAndApplication(event.getRemoteUserId(), application);

            if (user == null)
                user = new User(application, event.getRemoteUserId());

            Event _event = ModelToDTOConverter.convert(event);
            _event.setUser(user);
            eventRepository.save(_event);

            /**
             * Process each event
             */
            EventProcessor eventProcessor = new EventProcessor() {

                public void process(Event event) {
                    List<Rule> rules = EventController.this.rulesRepository.findAllByTypeAndBadge_Application(event.getType(), event.getUser().getApplication());

                    for (Rule rule : rules) {
                        if (rule.getConditions().size() > 0 && !event.checkProperties(rule.getConditions()))
                            continue;

                        Reward reward = EventController.this.rewardRepository.findByUserAndRule(event.getUser(), rule);
                        if (reward == null)
                            reward = new Reward(event.getUser(), rule, 0);

                        reward.setNbPoints(reward.getNbPoints() + event.getQuantityFromProperties());
                        EventController.this.rewardRepository.save(reward);

                        boolean exist = EventController.this.badgeRepository.existsByUsersContainsAndId(reward.getUser(), rule.getBadge().getId());
                        if (reward.getNbPoints() >= rule.getQuantity() && !exist) {
                            rule.getBadge().getUsers().add(reward.getUser());
                            EventController.this.badgeRepository.save(rule.getBadge());
                        }
                    }
                }

            };
            eventProcessor.process(_event);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}