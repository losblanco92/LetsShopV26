package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager extends BaseClass implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();// Thread safe

	String repName;

	public void onStart(ITestContext testContext) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(new Date());// time stamp
		repName = "Test-Report_" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);// specify location of the report

		sparkReporter.config().setDocumentTitle("Lets Shop Automation Report"); // Title of report
		sparkReporter.config().setReportName("Lets Shop Functional Testing"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Lets Shop");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt", "QA");

		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}

	}

	public void onTestSuccess(ITestResult result) {

		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.PASS, result.getName() + " got successfully executed");
		test.set(extentTest);
	}

	public void onTestFailure(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		extentTest.assignCategory(result.getMethod().getGroups());

		extentTest.log(Status.FAIL, result.getName() + " got failed");
		extentTest.log(Status.INFO, result.getThrowable().getMessage());

		test.set(extentTest); 

		try {
			WebDriver driver = ((BaseClass) result.getInstance()).driver;
			String imgPath = ((BaseClass) result.getInstance()).captureScreen(result.getMethod().getMethodName(),
					driver);

			extentTest.addScreenCaptureFromPath(imgPath);

		} catch (Exception e) {
			extentTest.log(Status.WARNING, "Unable to capture screenshot.");
		}
	}

	public void onTestSkipped(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
	    extentTest.assignCategory(result.getMethod().getGroups());
	    extentTest.log(Status.SKIP, result.getName() + " got skipped");
	    test.set(extentTest);
	}

	public void onFinish(ITestContext testContext) {

		extent.flush();

	}

}
