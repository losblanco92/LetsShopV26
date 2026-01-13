package cucumberRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions( //features = ".\\features\\AccountRegistration.feature",
//features = ".\\features\\SubmitOrder.feature", 
		features = ".\\features\\AccountLoginDataDriven.feature",
		//features = {"@rerun/rerun.txt"},
		glue = { "stepDefinition", "hooks" }, monochrome = true, plugin = { "pretty", "html:target/cucumber.html",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"rerun:rerun/rerun.txt"}, 
		         dryRun = false)

public class TestRunner extends AbstractTestNGCucumberTests {

}
