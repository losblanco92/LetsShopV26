package hooks;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class Hooks extends BaseClass {

	public static LoginPage loginPage;
	public static Properties pr;
	public static FileInputStream fis;

	@Before
	public void setUp() throws IOException {
		pr = new Properties();
		fis = new FileInputStream(".\\src\\test\\resources\\config.properties");
		pr.load(fis);
		loginPage = launchApplication();

	}

	@After
	public void tearDown(Scenario scenario) {

	    if (scenario.isFailed()) {
	         byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	        scenario.attach(screenshot, "image/png", "Failed Screenshot");
	    }

	    driver.quit();
	}

}
