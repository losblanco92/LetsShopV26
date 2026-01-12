package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.RegistrationPage;
import testBase.BaseClass;
import testBase.TestNGBase;
import utilities.Retry;
import utilities.TestDataUtils;

public class AccountRegistrationTest extends TestNGBase {

	@Test(groups = "Retry",retryAnalyzer = Retry.class)
	public void verify_account_registration() {

		logger.info("********Starting Account Registration Test. Opening Registration Form**********");
		RegistrationPage registrationPage = loginPage.goToregisterAccount();
		logger.info("********Entering Details In Registration Form**********");
		registrationPage.setFirstName(TestDataUtils.randomFirstName());
		registrationPage.setLastName(TestDataUtils.randomLastName());
		registrationPage.setEmail(TestDataUtils.randomEmail());
		registrationPage.setNumber(TestDataUtils.randomPhoneNumber());

		String password = TestDataUtils.randomPassword();
		registrationPage.setPassword(password);
		registrationPage.confirmPassword(password);

		registrationPage.selectCheckBox();
		logger.info("********Clicking on Register**********");
		registrationPage.clickRegister();
		logger.info("********Validating Expected Message**********");
		String message = registrationPage.displayRegistrationMessage();
		Assert.assertEquals(message, "Account Created Successfully");

		logger.info("********Account Registration Test Finished*********");
	}

}
