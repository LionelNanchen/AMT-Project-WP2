package ch.heigvd.gamification.api.spec;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;

public class UtilsSteps {
    private String apiKey = "a1";
    private int statusCode;

    @Given("^I have an api key$")
    public void i_have_an_api_key() throws Throwable {
        Assert.assertNotEquals("", apiKey);
    }

    @Then("^I receive status code <(\\d+)>$")
    public void i_receive_status_code(int arg1) throws Throwable {
        Assert.assertEquals(arg1, statusCode);
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getApiKey() {
        return apiKey;
    }
}
