package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import pageObjects.LoginPage;

public class BaseClass {

	public Properties pr;
	FileInputStream fis;
	String url;
	public WebDriver driver;
	public LoginPage loginPage;
	public Logger logger;
	String os;
	String br;
	
	// @Parameters({ "os", "browser" })
	public WebDriver initializeBrowser() throws IOException {

		pr = new Properties();
		fis = new FileInputStream(".\\src\\test\\resources\\config.properties");
		pr.load(fis);

		logger = LogManager.getLogger(this.getClass());

		if (pr.getProperty("exe_env").equalsIgnoreCase("remote")) {

			DesiredCapabilities cap = new DesiredCapabilities();
			if (os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);
			}

			else if (os.equalsIgnoreCase("linux")) {
				cap.setPlatform(Platform.LINUX);
			}

			else if (os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			}

			else {
				System.out.println("No matching browser");

			}

			switch (br.toLowerCase()) {
			case "chrome":
				cap.setBrowserName("chrome");
				break;
			case "edge":
				cap.setBrowserName("edge");
				break;
			case "firefox":
				cap.setBrowserName("firefox");
				break;
			default:
				System.out.println("Wrong browser");
				return null;

			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		}

		if (pr.getProperty("exe_env").equalsIgnoreCase("local"))

		{
			br = pr.getProperty("browser");

			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Wrong browser");
				return null;

			}
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;

	}

	//@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException {

		driver = initializeBrowser();
		driver.get(pr.getProperty("url"));
		loginPage = new LoginPage(driver);
		return loginPage;

	}

	//@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

	public String captureScreen(String tname, WebDriver driver) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;

	}
}
