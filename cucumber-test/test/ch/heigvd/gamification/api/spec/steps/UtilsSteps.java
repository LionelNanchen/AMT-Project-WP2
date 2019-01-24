package ch.heigvd.gamification.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;

public class UtilsSteps {
    private static String apiKey = "a1";
    private static int statusCode;


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

    @Given("^I have an api key$")
    public void iHaveAnApiKey() {

    }
}
