package ch.heigvd.gamification.api.spec.steps;

import ch.heigvd.gamification.api.DefaultApi;
import cucumber.api.PendingException;
import cucumber.api.java.en.When;

public class EventsSteps {
    private DefaultApi defaultApi = new DefaultApi();
    private long id;

    @When("^I try to POST a new event$")
    public void i_try_to_POST_a_new_event() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I GET all the badges from the endpoint /events$")
    public void i_GET_all_the_badges_from_the_endpoint_events() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I try to DELETE the event$")
    public void i_try_to_DELETE_the_event() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I try to change the event's name$")
    public void i_try_to_change_the_event_s_name() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
