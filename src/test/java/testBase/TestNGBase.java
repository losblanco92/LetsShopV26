package testBase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestNGBase extends BaseClass {

	@Parameters({ "browser", "os" })
	@BeforeMethod(alwaysRun = true)
	public void setup(@Optional String browser, @Optional String os) throws Exception {

		loadConfig();

		if (browser != null) {
			pr.setProperty("browser", browser);
		}

		if (os != null) {
			pr.setProperty("os", os);
		}

		launchApplication();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		getDriver().quit();
		tlDriver.remove();
	}
}
