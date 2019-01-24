package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.EventDTO;
import ch.heigvd.gamification.model.*;
import ch.heigvd.gamification.repository.*;
import ch.heigvd.gamification.service.EventProcessor;
import ch.heigvd.gamification.util.ModelToDTOConverter;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Authors: Amrani Kamil, Nanchen Lionel, Nicole Olivier, Reka Mentor
 * AMT WP2 2018-2019
 */
@RestController
public class EventController implements ch.heigvd.gamification.api.EventsApi {

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
    public ResponseEntity<EventDTO> reportEvent(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Key", required=true) String xApiKey, @ApiParam(value = "The event that occured in the application" ,required=true )  @Valid @RequestBody EventDTO event) {
        Event _event = ModelToDTOConverter.convert(event);
        EventProcessor eventProcessor = new EventProcessor();
        if (eventProcessor.process(_event, xApiKey)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}