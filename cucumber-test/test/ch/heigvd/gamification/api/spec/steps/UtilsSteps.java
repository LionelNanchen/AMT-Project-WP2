package ch.heigvd.gamification.api.spec.steps;

import ch.heigvd.gamification.ApiException;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.DefaultApi;
import ch.heigvd.gamification.api.dto.BadgesResponseDTO;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class UtilsSteps {
    private static String apiKey = "a1";
    private static int statusCode;

    @Given("^I have an api key$")
    public void iHaveAnApiKey() {
        statusCode = -1;//invalidate last scenario code

        // try to get badges with apiKey to make sure apiKey is valid
        int apiKeyStatusCode;
        try {
            ApiResponse<List<BadgesResponseDTO>> res = new DefaultApi().badgesGetWithHttpInfo(UtilsSteps.getApiKey());
            apiKeyStatusCode = res.getStatusCode();
        } catch (ApiException e) {
            apiKeyStatusCode = e.getCode();
        }

        Assert.assertEquals(200, apiKeyStatusCode);
    }

    @Then("^I receive status code <(\\d+)>$")
    public void i_receive_status_code(int arg1) throws Throwable {
        Assert.assertEquals(arg1, statusCode);
    }

    public static void setStatusCode(int statusCode) {
        UtilsSteps.statusCode = statusCode;
    }

    public static String getApiKey() {
        return apiKey;
    }
}
