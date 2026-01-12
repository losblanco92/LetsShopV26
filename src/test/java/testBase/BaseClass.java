package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import pageObjects.LoginPage;

public class BaseClass {

 
    protected static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    static protected Properties pr;
     protected Logger logger;
    protected LoginPage loginPage;

    // ---------------- CONFIG + LOGGER ----------------
    public void loadConfig() throws IOException {
        pr = new Properties();
        FileInputStream fis =
            new FileInputStream("src/test/resources/config.properties");
        pr.load(fis);

        logger = LogManager.getLogger(this.getClass());
        logger.info("Config file loaded successfully");
    }

    // ---------------- DRIVER INITIALIZATION ----------------
    public WebDriver initializeBrowser() throws Exception {

        String env = pr.getProperty("exe_env");
        String browser = pr.getProperty("browser");

        WebDriver driver;

        if (env.equalsIgnoreCase("remote")) {

            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setBrowserName(browser);
            cap.setPlatform(Platform.ANY);

            logger.info("Running tests on Selenium Grid");

            driver = new RemoteWebDriver(
                new URL(pr.getProperty("grid_url")), cap);

        } else {

            logger.info("Running tests locally on browser: " + browser);

            switch (browser.toLowerCase()) {
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
                throw new RuntimeException("Invalid browser name");
            }
        }

        driver.manage().window().maximize();
        driver.manage().timeouts()
              .implicitlyWait(Duration.ofSeconds(10));

        tlDriver.set(driver);
        return driver;
    }

    // ---------------- LAUNCH APPLICATION ----------------
    public LoginPage launchApplication() throws Exception {

        WebDriver driver = initializeBrowser();
        driver.get(pr.getProperty("url"));

        logger.info("Application launched: " + pr.getProperty("url"));

        loginPage = new LoginPage(driver);
        return loginPage;
    }

    // ---------------- GET DRIVER ----------------
    public WebDriver getDriver() {
        return tlDriver.get();
    }

    // ---------------- SCREENSHOT UTILITY ----------------
    public String captureScreen(String testName) throws IOException {

        String timeStamp =
            new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        TakesScreenshot ts = (TakesScreenshot) getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);

        String targetPath =
            System.getProperty("user.dir")
            + "/screenshots/"
            + testName + "_" + timeStamp + ".png";

        File target = new File(targetPath);
        FileUtils.copyFile(source, target);

        logger.info("Screenshot captured for test: " + testName);

        return targetPath;
    }
}
