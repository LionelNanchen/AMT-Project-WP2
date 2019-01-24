/**
 * NOTE: This class is auto generated by the swagger code generator program (2.2.3).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.EventDTO;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-24T13:22:00.036+01:00")

@Api(value = "events", description = "the events API")
public interface EventsApi {

    @ApiOperation(value = "Reporting a new event", notes = "Reporting a new event that occured in the application", response = EventDTO.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "The event has successfully been reported", response = EventDTO.class) })
    
    @RequestMapping(value = "/events",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<EventDTO> reportEvent(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Key", required=true) String xApiKey,@ApiParam(value = "The event that occured in the application" ,required=true )  @Valid @RequestBody EventDTO event);

}
