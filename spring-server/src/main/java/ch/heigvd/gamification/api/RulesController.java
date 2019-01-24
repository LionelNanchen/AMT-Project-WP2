//package ch.heigvd.gamification.api;
//
//import io.swagger.annotations.ApiParam;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import javax.validation.Valid;
//import java.util.ArrayList;
//
//public class RulesController implements RulesApi {
//
//    private ApplicationRepository applicationRepository;
//    private BadgeRepository badgeRepository;
//    private PointScaleRepository pointScaleRepository;
//    private RulesRepository rulesRepository;
//
//    public RulesController(ApplicationRepository applicationRepository, BadgeRepository badgeRepository, PointScaleRepository pointScaleRepository, RulesRepository rulesRepository) {
//        this.applicationRepository = applicationRepository;
//        this.badgeRepository = badgeRepository;
//        this.pointScaleRepository = pointScaleRepository;
//        this.rulesRepository = rulesRepository;
//    }
//
//    @Override
//    ResponseEntity<List<RuleResponseDTO>> rulesGet(@ApiParam(value = "token that contains the application key",required = true) @RequestHeader(value = "X-Api-Token",required = true) String apiToken) {
//        ApplicationModel application = applicationRepository.findByApiToken(apiToken);
//
//        if (application != null) {
//            List<RuleResponseDTO> response = new ArrayList<>();
//
//            for(RuleModel ruleModel : rulesRepository.findAllRulesByApplication(application)) {
//                response.add(Converter.convert(rule));
//            }
//
//            return ResponseEntity.ok(response);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }
//
//    @Override
//    ResponseEntity<Void> rulesIdDelete(@ApiParam(value = "token that contains the application key",required = true) @RequestHeader(value = "X-Api-Token",required = true) String apiToken, @ApiParam(value = "Rule id to delete",required = true) @PathVariable("id") Integer id) {
//        ApplicationModel application = applicationRepository.findByApiToken(apiToken);
//
//        if (application != null) {
//            RuleModel ruleModel = this.rulesRepository.findByRuleByApplicationAndId(application, id);
//            if (ruleModel == null)
//                return ResponseEntity.notFound().build();
//
//            this.rulesRepository.delete(ruleModel);
//
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }
//
//    @Override
//    ResponseEntity<Void> rulesIdPut(@ApiParam(value = "token that contains the application key",required = true) @RequestHeader(value = "X-Api-Token",required = true) String apiToken, @ApiParam(value = "Rule id to update",required = true) @PathVariable("id") Integer id, @ApiParam(value = "A rule is defined by a type, a quantity, a badge id, a point scale id and some properties",required = true) @Valid @RequestBody RuleDTO ruleDTO) {
//        ApplicationModel application = applicationRepository.findByApiToken(apiToken);
//
//        if (application != null) {
//            RuleModel ruleModel = this.rulesRepository.findRuleByApplicationAndId(application, id);
//
//            if (ruleModel == null) return ResponseEntity.notFound().build();
//
//            BadgeModel badgeModel = badgeRepository.findByApplicationAndId(application, ruleDTO.getBadgeID());
//            PointScaleModel pointScaleModel = pointScaleRepository.findByIdAndApplication(application, ruleDTO.getBadgeID());
//
//            if(badgeModel == null || pointScaleModel == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//
//            ruleModel.setBadge(badgeModel);
//            ruleModel.setName(ruleDTO.getName());
//            ruleModel.setPointScale(pointScaleModel);
//            ruleModel.setQuantity(ruleDTO.getQuantity());
//            ruleModel.setType(ruleDTO.getType());
//
//            this.rulesRepository.save(ruleModel);
//
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }
//
//    @Override
//    ResponseEntity<Void> rulesPost(@ApiParam(value = "token that contains the application key",required = true) @RequestHeader(value = "X-Api-Token",required = true) String apiToken, @ApiParam(value = "A rule is defined by a type, a quantity, a badge id, a point scale id and some properties",required = true) @Valid @RequestBody RuleDTO ruleDTO) {
//        ApplicationModel application = applicationRepository.findByApiToken(apiToken);
//
//        if (application != null) {
//
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }
//}
//
