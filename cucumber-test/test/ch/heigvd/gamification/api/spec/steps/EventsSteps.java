package ch.heigvd.gamification.api.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.joda.time.DateTime;

import java.util.LinkedList;
import java.util.List;

public class EventsSteps {
    private DefaultApi defaultApi = new DefaultApi();
    private int ruleId;
    private long badgeId;
    private long pointScaleId;

    @And("^I have a badge$")
    public void iHaveABadge() throws Throwable {
        BadgesDTO badges = new BadgesDTO();
        badges.setName("test 2");
        badges.setDescription("a test 2");
        ApiResponse<Void> res = defaultApi.badgesPostWithHttpInfo(UtilsSteps.getApiKey(), badges);
        badgeId = Long.parseLong(res.getHeaders().get("id").get(0));
    }

    @And("^I have a pointScale$")
    public void iHaveAPointScale() throws Throwable {
        PointScaleDTO pointScale = new PointScaleDTO();
        pointScale.setPointScaleName("test 2");
        ApiResponse<Void> res = defaultApi.pointScalePostWithHttpInfo(UtilsSteps.getApiKey(), pointScale);
        pointScaleId = Long.parseLong(res.getHeaders().get("id").get(0));
    }

    @And("^I have a rule$")
    public void iHaveARule() throws Throwable {
        RuleDTO ruleDTO = RulesSteps.createTestRuleDTO(badgeId, pointScaleId);
        ApiResponse<Void> res = defaultApi.rulesPostWithHttpInfo(UtilsSteps.getApiKey(), ruleDTO);
        UtilsSteps.setStatusCode(res.getStatusCode());
        ruleId = Integer.parseInt(res.getHeaders().get("id").get(0));
    }

    @When("^I try to POST a new event$")
    public void i_try_to_POST_a_new_event() throws Throwable {
        EventDTO eventDTO= new EventDTO();
        List<ValueDTO> properties = new LinkedList<>();
        ValueDTO valueDTO = new ValueDTO();
        valueDTO.setKey("key");
        valueDTO.setValue("3");

        eventDTO.setProperties(properties);
        eventDTO.setRemoteUserId("1");
        eventDTO.setTimestamp(new DateTime("2019-01-25T08:04:49.694Z"));
        eventDTO.setType("test");

        try {
            ApiResponse<EventDTO> res = defaultApi.reportEventWithHttpInfo(UtilsSteps.getApiKey(), eventDTO);
            UtilsSteps.setStatusCode(res.getStatusCode());
        } catch (ApiException e) {
            UtilsSteps.setStatusCode(e.getCode());
        }

        iHaveDeletedARule();
        iHaveDeletedABadge();
        iHaveDeletedAPointScale();
    }

    private void iHaveDeletedARule() throws Throwable {
        try {
            defaultApi.rulesIdDeleteWithHttpInfo(UtilsSteps.getApiKey(), ruleId);
        } catch (ApiException e) {
            System.err.println(e.getCode());
        }
    }

    private void iHaveDeletedABadge() throws Throwable {
        try {
            defaultApi.badgesIdDeleteWithHttpInfo(UtilsSteps.getApiKey(), badgeId);
        } catch (ApiException e) {
            System.err.println(e.getCode());
        }
    }

    private void iHaveDeletedAPointScale() throws Throwable {
        try {
            defaultApi.pointScaleIdDeleteWithHttpInfo(UtilsSteps.getApiKey(), pointScaleId);
        } catch (ApiException e) {
            System.err.println(e.getCode());
        }
    }
}
