package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.RuleDTO;
import ch.heigvd.gamification.api.dto.RuleResponseDTO;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Badge;
import ch.heigvd.gamification.model.PointScale;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.repository.ApplicationRepository;
import ch.heigvd.gamification.repository.BadgeRepository;
import ch.heigvd.gamification.repository.PointScaleRepository;
import ch.heigvd.gamification.repository.RulesRepository;
import ch.heigvd.gamification.util.ModelToDTOConverter;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RulesController implements RulesApi {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private PointScaleRepository pointScaleRepository;

    @Autowired
    private RulesRepository rulesRepository;

    @Override
    public ResponseEntity<List<RuleResponseDTO>> rulesGet(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Key", required=true) String xApiKey) {
        Application application = applicationRepository.findByAppKey(xApiKey);

        if (application != null) {
            List<RuleResponseDTO> response = new ArrayList<>();

            for (Rule rule : rulesRepository.findAllByBadge_Application(application)) {
                response.add(ModelToDTOConverter.convert(rule));
            }

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public ResponseEntity<Void> rulesIdDelete(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Key", required=true) String xApiKey,@ApiParam(value = "Rule id to delete",required=true ) @PathVariable("id") Integer id) {
        Application application = applicationRepository.findByAppKey(xApiKey);

        if (application != null) {
            Rule rule = rulesRepository.findByBadge_ApplicationAndId(application, id);
            if (rule == null)
                return ResponseEntity.notFound().build();

            rulesRepository.delete(rule);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public ResponseEntity<Void> rulesIdPut(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Key", required=true) String xApiKey,@ApiParam(value = "Rule id to update",required=true ) @PathVariable("id") Integer id,@ApiParam(value = "A rule is defined by a type, a quantity, a badge id, a point scale id and some properties" ,required=true )  @Valid @RequestBody RuleDTO body) {
        Application application = applicationRepository.findByAppKey(xApiKey);

        if (application != null) {
            Rule rule = rulesRepository.findByBadge_ApplicationAndId(application, id);

            if (rule == null) return ResponseEntity.notFound().build();

            PointScale pointScale = pointScaleRepository.findByApplicationAndId(application, body.getBadgeID());
            Badge badge = badgeRepository.findByApplicationAndId(application, body.getBadgeID());

            if (pointScale == null ||badge == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

            rule.setBadge(badge);
            rule.setName(body.getName());
            rule.setPointScale(pointScale);
            rule.setQuantity(body.getQuantity());
            rule.setType(body.getType());
            rulesRepository.save(rule);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public ResponseEntity<Void> rulesPost(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Key", required=true) String xApiKey,@ApiParam(value = "A rule is defined by a type, a quantity, a badge id, a point scale id and some properties" ,required=true )  @Valid @RequestBody RuleDTO body) {
        Application application = applicationRepository.findByAppKey(xApiKey);

        if (application != null) {
            Badge badge = badgeRepository.findByApplicationAndId(application, body.getBadgeID());
            PointScale pointScale = pointScaleRepository.findByApplicationAndId(application, body.getPointsScaleID());

            if(pointScale == null || badge == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            Rule rule = ModelToDTOConverter.convert(body);
            rule.setBadge(badge);
            rule.setPointScale(pointScale);

            rulesRepository.save(rule);

            return ResponseEntity.status(HttpStatus.CREATED).header("id", String.valueOf(rule.getId())).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

