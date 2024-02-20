import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(io.cucumber.junit.Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features/app/android/HomePage.feature"},
        glue = {"step_defination"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true,
        tags = "@android")

public class MobileRunner {
}