/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.RuleDTO;
import ch.heigvd.gamification.api.dto.RuleResponseDTO;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-23T20:53:46.582+01:00")

@Api(value = "rules", description = "the rules API")
public interface RulesApi {

    @ApiOperation(value = "", nickname = "rulesGet", notes = "Get all rules from one application", response = RuleResponseDTO.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successfully retrieved all rules of an application", response = RuleResponseDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Wrong application API token") })
    @RequestMapping(value = "/rules",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<RuleResponseDTO>> rulesGet(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Token", required=true) String xApiToken);


    @ApiOperation(value = "Delete a rule", nickname = "rulesIdDelete", notes = "Delete a rule referenced by it's id.", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successfully deleted"),
        @ApiResponse(code = 401, message = "Wrong API token") })
    @RequestMapping(value = "/rules/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> rulesIdDelete(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Token", required=true) String xApiToken,@ApiParam(value = "Rule id to delete",required=true) @PathVariable("id") Integer id);


    @ApiOperation(value = "Update a rule", nickname = "rulesIdPut", notes = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successfully updated"),
        @ApiResponse(code = 400, message = "Wrong badge or point scale id"),
        @ApiResponse(code = 401, message = "Wrong API token") })
    @RequestMapping(value = "/rules/{id}",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> rulesIdPut(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Token", required=true) String xApiToken,@ApiParam(value = "Rule id to update",required=true) @PathVariable("id") Integer id,@ApiParam(value = "A rule is defined by a type, a quantity, a badge id, a point scale id and some properties" ,required=true )  @Valid @RequestBody RuleDTO body);


    @ApiOperation(value = "Add a new rule", nickname = "rulesPost", notes = "Add a new rule for the referenced application by it's token", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successfully added"),
        @ApiResponse(code = 400, message = "Wrong badge or point scale id"),
        @ApiResponse(code = 401, message = "Wrong API token") })
    @RequestMapping(value = "/rules",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> rulesPost(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Token", required=true) String xApiToken,@ApiParam(value = "A rule is defined by a type, a quantity, a badge id, a point scale id and some properties" ,required=true )  @Valid @RequestBody RuleDTO body);

}
