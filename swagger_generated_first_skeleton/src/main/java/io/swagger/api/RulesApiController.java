package io.swagger.api;

import io.swagger.model.RuleDTO;
import io.swagger.model.RuleResponseDTO;
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
public class RulesApiController implements RulesApi {

    private static final Logger log = LoggerFactory.getLogger(RulesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public RulesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<RuleResponseDTO>> rulesGet(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Token", required=true) String xApiToken) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<RuleResponseDTO>>(objectMapper.readValue("[ {  \"badge\" : {    \"name\" : \"name\",    \"description\" : \"description\",    \"id\" : 0  },  \"pointScale\" : {    \"pointScaleName\" : \"pointScaleName\",    \"pointScaleId\" : 0  },  \"quantity\" : 6,  \"name\" : \"name\",  \"id\" : 0,  \"type\" : \"type\",  \"properties\" : {    \"value\" : \"value\",    \"key\" : \"key\"  }}, {  \"badge\" : {    \"name\" : \"name\",    \"description\" : \"description\",    \"id\" : 0  },  \"pointScale\" : {    \"pointScaleName\" : \"pointScaleName\",    \"pointScaleId\" : 0  },  \"quantity\" : 6,  \"name\" : \"name\",  \"id\" : 0,  \"type\" : \"type\",  \"properties\" : {    \"value\" : \"value\",    \"key\" : \"key\"  }} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<RuleResponseDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<RuleResponseDTO>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> rulesIdDelete(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Token", required=true) String xApiToken,@ApiParam(value = "Rule id to delete",required=true) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> rulesIdPut(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Token", required=true) String xApiToken,@ApiParam(value = "Rule id to update",required=true) @PathVariable("id") Integer id,@ApiParam(value = "A rule is defined by a type, a quantity, a badge id, a point scale id and some properties" ,required=true )  @Valid @RequestBody RuleDTO body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> rulesPost(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Token", required=true) String xApiToken,@ApiParam(value = "A rule is defined by a type, a quantity, a badge id, a point scale id and some properties" ,required=true )  @Valid @RequestBody RuleDTO body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
