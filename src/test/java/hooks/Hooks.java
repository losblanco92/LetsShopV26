package hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class Hooks extends BaseClass {


    @Before
    public void setUp() throws Exception {

        loadConfig();              
        loginPage = launchApplication();

        logger.info("Scenario started");
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {

            byte[] screenshot =
                ((TakesScreenshot) getDriver())
                .getScreenshotAs(OutputType.BYTES);

            scenario.attach(
                screenshot, "image/png", "Failed Screenshot");

            logger.error("Scenario failed: " + scenario.getName());
        }

        getDriver().quit();
        logger.info("Browser closed");
    }
}
