package cucumber;

import io.cucumber.testng.CucumberOptions;



import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features = "src/test/java/features",
		monochrome = true,
		plugin = {
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "pretty",
                "json:target/cucumber-reports/",
                "html:target/cucumber-reports.html"},
		glue={"stepDefinitions"}
		)

public class TestRunnerTestNg extends AbstractTestNGCucumberTests{

}
