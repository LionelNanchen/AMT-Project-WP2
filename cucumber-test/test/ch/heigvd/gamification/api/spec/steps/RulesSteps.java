package ch.heigvd.gamification.api.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.*;
import cucumber.api.java.en.When;

import java.util.LinkedList;
import java.util.List;

public class RulesSteps {
    private DefaultApi defaultApi = new DefaultApi();
    private int id;
    private long badgeId;
    private long pointScaleId;

    protected static RuleDTO createTestRuleDTO(long badgeId, long pointScaleId) {
        RuleDTO ruleDTO = new RuleDTO();

        String key = "key";
        String ope = "==";
        String val = "3";

        List<ConditionDTO> conditions = new LinkedList<>();
        ConditionDTO cond = new ConditionDTO();
        cond.setKey(key);
        cond.setOperator(ope);
        cond.setValue(val);
        conditions.add(new ConditionDTO());

        ValueDTO value = new ValueDTO();
        value.setValue("");
        value.setKey(key);

        ruleDTO.setBadgeID(badgeId);
        ruleDTO.setConditions(conditions);
        ruleDTO.setName("test");
        ruleDTO.setPointsScaleID(pointScaleId);
        ruleDTO.setProperties(value);
        ruleDTO.setQuantity(1);
        ruleDTO.setType("test");

        return ruleDTO;
    }

    private void iHaveCreatedABadge() throws Throwable {
        BadgesDTO badges = new BadgesDTO();
        badges.setName("test");
        badges.setDescription("a test");
        ApiResponse<Void> res = defaultApi.badgesPostWithHttpInfo(UtilsSteps.getApiKey(), badges);
        badgeId = Long.parseLong(res.getHeaders().get("id").get(0));
    }

    private void iHaveCreatedAPointScale() throws Throwable {
        PointScaleDTO pointScale = new PointScaleDTO();
        pointScale.setPointScaleName("test");
        ApiResponse<Void> res = defaultApi.pointScalePostWithHttpInfo(UtilsSteps.getApiKey(), pointScale);
        pointScaleId = Long.parseLong(res.getHeaders().get("id").get(0));
    }

    private void iHaveDeletedABadge() throws Throwable {
        defaultApi.badgesIdDeleteWithHttpInfo(UtilsSteps.getApiKey(), badgeId);
    }

    private void iHaveDeletedAPointScale() throws Throwable {
        defaultApi.pointScaleIdDeleteWithHttpInfo(UtilsSteps.getApiKey(), pointScaleId);
    }

    @When("^I try to POST a new rule$")
    public void i_try_to_POST_a_new_rule() throws Throwable {
        iHaveCreatedABadge();
        iHaveCreatedAPointScale();

        RuleDTO ruleDTO = createTestRuleDTO(badgeId, pointScaleId);
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

        iHaveDeletedABadge();
        iHaveDeletedAPointScale();
    }

    @When("^I try to change the rule's name$")
    public void i_try_to_change_the_rule_s_name() throws Throwable {
        RuleDTO ruleDTO = createTestRuleDTO(badgeId, pointScaleId);
        ruleDTO.setName("test updated");

        try {
            ApiResponse<Void> res = defaultApi.rulesIdPutWithHttpInfo(UtilsSteps.getApiKey(), id, ruleDTO);
            UtilsSteps.setStatusCode(res.getStatusCode());
        } catch (ApiException e) {
            UtilsSteps.setStatusCode(e.getCode());
        }
    }
}
