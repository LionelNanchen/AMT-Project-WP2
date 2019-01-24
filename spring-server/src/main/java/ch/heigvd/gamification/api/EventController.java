package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.EventDTO;
import ch.heigvd.gamification.model.*;
import ch.heigvd.gamification.repository.*;
import ch.heigvd.gamification.util.ModelToDTOConverter;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

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

    public ResponseEntity<EventDTO> reportEvent(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Key", required=true) String xApiKey, @ApiParam(value = "The event that occured in the application" ,required=true )  @Valid @RequestBody EventDTO event) {
        Application application = applicationRepository.findByAppKey(xApiKey);

        if (application != null) {
            User user = userRepository.findByRemoteUserIdAndApplication(event.getGamifiedUserId(), application);

            if (user == null)
                user = new User(application, event.getGamifiedUserId());

            Event _event = ModelToDTOConverter.convert(event);
            _event.setUser(user);
            eventRepository.save(_event);

            /**
             * Process each event
             */
            EventProcessor eventProcessor = new EventProcessor() {

                public void process(Event event) {
                    List<Rule> rules = EventController.this.rulesRepository.findAllByTypeAndBadge_Application(event.getType(), event.getUser().getApplicationModel());

                    for (Rule rule : rules) {
                        if (rule.getConditions().size() > 0 && !event.isApplicable(rule.getConditions())) {
                                continue;
                        }

                        Reward award = EventController.this.rewardRepository.findByUserAndRule(event.getUser(), rule);
                        if (award == null) {
                            award = new Reward(event.getUser(), rule, 0);
                        }

                        award.setNbPoints(award.getNbPoints() + event.getQuantityFromProperties());
                        EventController.this.rewardRepository.save(award);

                        if (award.getNbPoints() >= rule.getQuantity() &&
                                !EventController.this.badgeRepository.existsByUsersContainsAndId(award.getUser(), rule.getBadge().getId())) {
                            rule.getBadge().getUsers().add(award.getUser());
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