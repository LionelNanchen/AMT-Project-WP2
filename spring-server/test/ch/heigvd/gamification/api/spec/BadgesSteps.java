package ch.heigvd.gamification.api.spec;

import ch.heigvd.gamification.api.BadgesApi;
import ch.heigvd.gamification.api.BadgesController;

import ch.heigvd.gamification.api.dto.BadgesDTO;
import ch.heigvd.gamification.api.dto.BadgesResponseDTO;
import cucumber.api.PendingException;
import cucumber.api.java.en.When;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class BadgesSteps {
    private UtilsSteps utilsSteps = new UtilsSteps();
    private BadgesApi badgesApi = new BadgesController();

    @When("^I try to POST a new badge$")
    public void i_try_to_POST_a_new_badge() throws Throwable {
        BadgesDTO badges = new BadgesDTO();
        badges.setName("test");
        badges.setDescription("a test");
        badgesApi.badgesPost(utilsSteps.getApiKey(), badges);
    }

    @When("^I GET all the badges from the endpoint /badges$")
    public void i_GET_all_the_badges_from_the_endpoint_badges() throws Throwable {
        ResponseEntity<List<BadgesResponseDTO>> res = badgesApi.badgesGet(utilsSteps.getApiKey());
        utilsSteps.setStatusCode(res.getStatusCodeValue());
    }

    @When("^I try to DELETE the badge$")
    public void i_try_to_DELETE_the_badge() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
    }

    @When("^I try to change the badge's name$")
    public void i_try_to_change_the_badge_s_name() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
