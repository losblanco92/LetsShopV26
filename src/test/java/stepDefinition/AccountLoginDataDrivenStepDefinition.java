package stepDefinition;

import org.testng.Assert;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.CucumberExcelReader;

public class AccountLoginDataDrivenStepDefinition extends BaseClass {

	CucumberExcelReader excel;
	String expectedResult;
	LoginPage loginPage = new LoginPage(getDriver());


	@Given("^User enters the vaild email and password from excel row (.+)$")
	public void user_enter_login_details_from_excel(Integer rowIndex) {
        
       

		
		excel = new CucumberExcelReader(".\\testData\\LetsShop_LoginData.xlsx");

		String username = excel.getCellData("Sheet1", "Username", rowIndex);
		String password = excel.getCellData("Sheet1", "Password", rowIndex);
		expectedResult = excel.getCellData("Sheet1", "Result", rowIndex);

		loginPage.loginApp(username, password);
	}

	@Then("the user should be successfully logged in when valid credentails are entered")
	public void verify_login_using_excel_row() {

		boolean signOut = loginPage.signOut();

		if (expectedResult.equalsIgnoreCase("Valid")) {
			if (signOut == true) {
				loginPage.clickSignOut();
				Assert.assertTrue(true);

			}

			else {
				Assert.assertTrue(false);
			}
		}

		if (expectedResult.equalsIgnoreCase("Invalid")) {
			if (signOut == true) {
				loginPage.clickSignOut();
				Assert.assertTrue(false);
			}

			else {
				Assert.assertTrue(true);

			}
		}

	}
}
