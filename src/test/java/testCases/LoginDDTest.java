package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testBase.BaseClass;
import testBase.TestNGBase;
import utilities.DataProviders;

public class LoginDDTest extends TestNGBase  {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	public void verifyLogin(String email, String password, String exp) {

		logger.info("*******Starting Login DD Test*******");

		logger.info("******Entring Login Details******");
		loginPage.loginApp(email, password);
		boolean signOut = loginPage.signOut();

		if (exp.equalsIgnoreCase("Valid")) {
			if (signOut == true) {
				loginPage.clickSignOut();
				Assert.assertTrue(true);

			}

			else {
				Assert.assertTrue(false);
			}
		}

		if (exp.equalsIgnoreCase("Invalid")) 
		{
			if (signOut == true) {
				loginPage.clickSignOut();
				Assert.assertTrue(false);
			}

			else {
				Assert.assertTrue(true);

			}
		}

		logger.info("******Login DD Test Finished******");
	}

}
