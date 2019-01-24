package ch.heigvd.gamification.api.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.PointScaleDTO;
import ch.heigvd.gamification.api.dto.PointScaleIdDTO;
import ch.heigvd.gamification.api.dto.RuleDTO;
import ch.heigvd.gamification.api.dto.RuleResponseDTO;
import cucumber.api.PendingException;
import cucumber.api.java.en.When;

import java.util.List;

public class RulesSteps {
    private DefaultApi defaultApi = new DefaultApi();
    private int id;

    @When("^I try to POST a new rule$")
    public void i_try_to_POST_a_new_rule() throws Throwable {
        RuleDTO ruleDTO = new RuleDTO();
        //TODO
        /*ruleDTO.setBadgeID();
        ruleDTO.setConditions();
        ruleDTO.setName();
        ruleDTO.setPointsScaleID();
        ruleDTO.setProperties();
        ruleDTO.setQuantity();
        ruleDTO.setType();*/

        ApiResponse<Void> res = defaultApi.rulesPostWithHttpInfo(UtilsSteps.getApiKey(), ruleDTO);
        UtilsSteps.setStatusCode(res.getStatusCode());
        id = Integer.parseInt(res.getHeaders().get("id").get(0));
    }

    @When("^I GET all the badges from the endpoint /rules$")
    public void i_GET_all_the_badges_from_the_endpoint_rules() throws Throwable {
        ApiResponse<List<RuleResponseDTO>> res = defaultApi.rulesGetWithHttpInfo(UtilsSteps.getApiKey());
        UtilsSteps.setStatusCode(res.getStatusCode());
    }

    @When("^I try to DELETE the rule$")
    public void i_try_to_DELETE_the_rule() throws Throwable {
        ApiResponse<Void> res = defaultApi.rulesIdDeleteWithHttpInfo(UtilsSteps.getApiKey(), id);
        UtilsSteps.setStatusCode(res.getStatusCode());
    }

    @When("^I try to change the rule's name$")
    public void i_try_to_change_the_rule_s_name() throws Throwable {
        RuleDTO ruleDTO = new RuleDTO();
        //TODO
        /*ruleDTO.setBadgeID();
        ruleDTO.setConditions();
        ruleDTO.setName();
        ruleDTO.setPointsScaleID();
        ruleDTO.setProperties();
        ruleDTO.setQuantity();
        ruleDTO.setType();*/

        try {
            ApiResponse<Void> res = defaultApi.rulesIdPutWithHttpInfo(UtilsSteps.getApiKey(), id, ruleDTO);
            UtilsSteps.setStatusCode(res.getStatusCode());
        } catch (ApiException e) {
            UtilsSteps.setStatusCode(e.getCode());
        }
    }
}
