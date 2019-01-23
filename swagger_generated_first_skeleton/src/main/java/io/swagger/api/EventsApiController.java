package io.swagger.api;

import io.swagger.model.EventDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-23T21:24:14.760Z")

@Controller
public class EventsApiController implements EventsApi {

    private static final Logger log = LoggerFactory.getLogger(EventsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public EventsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<EventDTO> reportEvent(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Token", required=true) String xApiToken,@ApiParam(value = "The event that occured in the application" ,required=true )  @Valid @RequestBody EventDTO event) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<EventDTO>(objectMapper.readValue("{  \"type\" : \"type\",  \"GamifiedUserId\" : \"GamifiedUserId\",  \"properties\" : [ {    \"value\" : \"value\",    \"key\" : \"key\"  }, {    \"value\" : \"value\",    \"key\" : \"key\"  } ],  \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"}", EventDTO.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<EventDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<EventDTO>(HttpStatus.NOT_IMPLEMENTED);
    }

}
