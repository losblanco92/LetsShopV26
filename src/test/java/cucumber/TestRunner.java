package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = ".\\features", glue = "stepDefinition", monochrome = true, 
plugin = {"pretty", "html:target/cucumber.html", 
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, dryRun = false)
public class TestRunner extends AbstractTestNGCucumberTests {

	
	
	
}
