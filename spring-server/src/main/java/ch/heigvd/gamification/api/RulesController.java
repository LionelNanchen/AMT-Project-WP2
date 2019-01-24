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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<RuleResponseDTO>> rulesGet(String xApiKey) {
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
    public ResponseEntity<Void> rulesIdDelete(String xApiKey, Integer id) {
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
    public ResponseEntity<Void> rulesIdPut(String xApiKey, Integer id, RuleDTO body) {
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
    public ResponseEntity<Void> rulesPost(String xApiKey, RuleDTO body) {
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

