package ch.heigvd.gamification.api.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.BadgesDTO;
import ch.heigvd.gamification.api.dto.BadgesResponseDTO;
import cucumber.api.java.en.When;

import java.util.List;

public class BadgesSteps {
    private DefaultApi defaultApi = new DefaultApi();
    private long id;

    @When("^I try to POST a new badge$")
    public void i_try_to_POST_a_new_badge() throws Throwable {
        BadgesDTO badges = new BadgesDTO();
        badges.setName("test");
        badges.setDescription("a test");

        try {
            ApiResponse<Void> res = defaultApi.badgesPostWithHttpInfo(UtilsSteps.getApiKey(), badges);
            UtilsSteps.setStatusCode(res.getStatusCode());
            id = Long.parseLong(res.getHeaders().get("id").get(0));
        } catch (ApiException e) {
            UtilsSteps.setStatusCode(e.getCode());
        }
    }

    @When("^I GET all the badges from the endpoint /badges$")
    public void i_GET_all_the_badges_from_the_endpoint_badges() throws Throwable {
        try {
            ApiResponse<List<BadgesResponseDTO>> res = defaultApi.badgesGetWithHttpInfo(UtilsSteps.getApiKey());
            UtilsSteps.setStatusCode(res.getStatusCode());
        } catch (ApiException e) {
            UtilsSteps.setStatusCode(e.getCode());
        }
    }

    @When("^I try to DELETE the badge$")
    public void i_try_to_DELETE_the_badge() throws Throwable {
        try {
            ApiResponse<Void> res = defaultApi.badgesIdDeleteWithHttpInfo(UtilsSteps.getApiKey(), id);
            UtilsSteps.setStatusCode(res.getStatusCode());
        } catch (ApiException e) {
            UtilsSteps.setStatusCode(e.getCode());
        }
    }

    @When("^I try to change the badge's name$")
    public void i_try_to_change_the_badge_s_name() throws Throwable {
        BadgesDTO badges = new BadgesDTO();
        badges.setName("test updated");
        badges.setDescription("a test updated");
        try {
            ApiResponse<Void> res = defaultApi.badgesIdPutWithHttpInfo(UtilsSteps.getApiKey(), id, badges);
            UtilsSteps.setStatusCode(res.getStatusCode());
        } catch (ApiException e) {
            UtilsSteps.setStatusCode(e.getCode());
        }
    }
}
