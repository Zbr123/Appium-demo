import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/app/android/HomePage.feature"},
        glue = {"step_defination"},
        plugin = { "pretty", "html:target/cucumber-reports" },
        monochrome = true,
        tags = "@homePage"
)
public class MobileRunner {
}
