package io.swagger.api;

import io.swagger.model.BadgesDTO;
import io.swagger.model.BadgesResponseDTO;
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
public class BadgesApiController implements BadgesApi {

    private static final Logger log = LoggerFactory.getLogger(BadgesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public BadgesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<BadgesResponseDTO>> badgesGet(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Token", required=true) String xApiToken) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<BadgesResponseDTO>>(objectMapper.readValue("[ {  \"name\" : \"name\",  \"description\" : \"description\",  \"id\" : 0}, {  \"name\" : \"name\",  \"description\" : \"description\",  \"id\" : 0} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<BadgesResponseDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<BadgesResponseDTO>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> badgesIdDelete(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Token", required=true) String xApiToken,@ApiParam(value = "Badge id to delete",required=true) @PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> badgesIdPut(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Token", required=true) String xApiToken,@ApiParam(value = "BadgeModel id to update",required=true) @PathVariable("id") Long id,@ApiParam(value = "The badge must have a new name and a new description" ,required=true )  @Valid @RequestBody BadgesDTO body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> badgesPost(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Token", required=true) String xApiToken,@ApiParam(value = "The new badge need to have a name and a description." ,required=true )  @Valid @RequestBody BadgesDTO body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
