package cucumber.Options;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",
glue ="stepDefinitions"  ,tags="@DeletePlace", plugin="json:target/jsonReports/cucumber-report.json")
public class TestRunner {

	
}
