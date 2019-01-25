package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.RewardDTO;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Reward;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.model.User;
import ch.heigvd.gamification.repository.ApplicationRepository;
import ch.heigvd.gamification.repository.RewardRepository;
import ch.heigvd.gamification.repository.RulesRepository;
import ch.heigvd.gamification.repository.UserRepository;
import ch.heigvd.gamification.util.ModelToDTOConverter;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RewardController implements RewardsApi {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RewardRepository rewardRepository;

    @Autowired
    RulesRepository rulesRepository;

    @Override
    public ResponseEntity<Object> rewardsUserIdRuleIdGet(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Key", required=true) String xApiKey,@ApiParam(value = "User id",required=true ) @PathVariable("user_id") Integer userId,@ApiParam(value = "Rule id",required=true ) @PathVariable("rule_id") Integer ruleId) {
        Application application = applicationRepository.findByAppKey(xApiKey);

        if (application != null) {
            Long user_id = Long.valueOf(userId);
            Long rule_id = Long.valueOf(ruleId);
            User user = userRepository.findUserById(user_id);
            Rule rule = rulesRepository.findRulesById(rule_id);
            Reward reward = rewardRepository.findByUserAndRule(user, rule);
            RewardDTO response = ModelToDTOConverter.convert(reward);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
