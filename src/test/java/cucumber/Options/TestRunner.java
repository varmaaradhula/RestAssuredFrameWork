package cucumber.Options;

import StepDefinitions.Stepdefinitions;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
//@CucumberOptions(features = "src/test/java/Features",glue = {"StepDefinitions"},tags = "@DeletePlace")
@CucumberOptions(features = "src/test/java/Features",plugin="json:target/jsonReports/cucumber-report.json",glue = {"StepDefinitions"})
public class TestRunner {

}
