package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepDefinitions",
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        plugin = {
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "pretty",
                "html:test-output/cucumber.html",
                "json:test-output/cucumber.json",
                "junit:test-output/cucumber.xml"
        },
        tags = ""
)
public class TestRunner {
}
