package ch.heigvd.gamification.api.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.PointScaleDTO;
import ch.heigvd.gamification.api.dto.PointScaleIdDTO;
import cucumber.api.java.en.When;

import java.util.List;

public class PointScalesSteps {
    private DefaultApi defaultApi = new DefaultApi();
    private long id;

    @When("^I try to POST a new pointScale$")
    public void i_try_to_POST_a_new_pointScale() throws Throwable {
        PointScaleDTO pointScale = new PointScaleDTO();
        pointScale.setPointScaleName("test");

        try {
            ApiResponse<Void> res = defaultApi.pointScalePostWithHttpInfo(UtilsSteps.getApiKey(), pointScale);
            UtilsSteps.setStatusCode(res.getStatusCode());
            id = Long.parseLong(res.getHeaders().get("id").get(0));
        } catch (ApiException e) {
            UtilsSteps.setStatusCode(e.getCode());
        }
    }

    @When("^I GET all the badges from the endpoint /pointScale$")
    public void i_GET_all_the_badges_from_the_endpoint_pointScale() throws Throwable {
        try {
            ApiResponse<List<PointScaleIdDTO>> res = defaultApi.pointScaleGetWithHttpInfo(UtilsSteps.getApiKey());
            UtilsSteps.setStatusCode(res.getStatusCode());
        } catch (ApiException e) {
            UtilsSteps.setStatusCode(e.getCode());
        }
    }

    @When("^I try to DELETE the pointScale$")
    public void i_try_to_DELETE_the_pointScale() throws Throwable {
        try {
            ApiResponse<Void> res = defaultApi.pointScaleIdDeleteWithHttpInfo(UtilsSteps.getApiKey(), id);
            UtilsSteps.setStatusCode(res.getStatusCode());
        } catch (ApiException e) {
            UtilsSteps.setStatusCode(e.getCode());
        }
    }

    @When("^I try to change the pointScale's name$")
    public void i_try_to_change_the_pointScale_s_name() throws Throwable {
        PointScaleDTO pointScale = new PointScaleDTO();
        pointScale.setPointScaleName("test updated");
        try {
            ApiResponse<Void> res = defaultApi.pointScaleIdPutWithHttpInfo(UtilsSteps.getApiKey(), id, pointScale);
            UtilsSteps.setStatusCode(res.getStatusCode());
        } catch (ApiException e) {
            UtilsSteps.setStatusCode(e.getCode());
        }
    }
}
