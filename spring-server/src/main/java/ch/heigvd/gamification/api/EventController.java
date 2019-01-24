//package ch.heigvd.gamification.api;
//
//import io.swagger.annotations.ApiParam;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//
//import javax.validation.Valid;
//
//public class EventController {
//
//    private ApplicationRepository applicationRepository;
//    private EventProcessor eventProcessor;
//    private EventRepository eventRepository;
//
//    public EventsController(ApplicationRepository applicationsRepository, EventProcessor eventProcessor, EventRepository eventRepository) {
//        this.applicationRepository = applicationsRepository;
//        this.eventRepository = eventRepository;
//        this.eventProcessor = eventProcessor;
//
//        ResponseEntity<EventDTO> reportEvent(@ApiParam(value = "token that contains the application key",required = true) @RequestHeader(value = "X-Api-Token",required = true) String apiToken, @ApiParam(value = "The event that occured in the application",required = true) @Valid @RequestBody EventDTO eventDTO) {
//            ApplicationModel application = applicationRepository.findByApiToken(apiToken);
//
//            if (application != null) {
//
//            } else {
//
//            }
//        }
//
//}
