package ch.heigvd.gamification.api.spec;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="test/resources/scenarios/", plugin = {"pretty", "html:target/cucumber"})
public class SpecificationTest {

}