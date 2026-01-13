package stepDefinition;

import org.testng.Assert;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class AccountRegistrationStepDefinition extends BaseClass {

	LoginPage loginPage = new LoginPage(getDriver());
	RegistrationPage registrationPage;

	@Given("I navigate to the registration page")
	public void i_navigate_to_the_registration_page() {
		registrationPage = loginPage.goToregisterAccount();
	}

	@When("^I enter first name (.+)$")
	public void i_enter_first_name(String firstName) {
		registrationPage.setFirstName(firstName);
	}

	@When("^I enter last name (.+)$")
	public void i_enter_last_name(String lastName) {
		registrationPage.setLastName(lastName);

	}

	@When("^I enter email (.+)$")
	public void i_enter_email(String email) {
		registrationPage.setEmail(email);
	}

	@When("^I enter phone number (.+)")
	public void i_enter_phone_number(String number) {
		registrationPage.setNumber(number);
	}

	@When("^I enter password (.+)$")
	public void i_enter_password(String password) {
		registrationPage.setPassword(password);

	}

	@When("^I confirm password (.+)$")
	public void i_confirm_password(String password) {
		registrationPage.confirmPassword(password);
	}

	@When("I tick the terms and conditions checkbox")
	public void i_tick_the_terms_and_conditions_checkbox() {
		registrationPage.selectCheckBox();
	}

	@When("I click on the Register button")
	public void i_click_on_the_register_button() {

		registrationPage.clickRegister();
	}

	@Then("I should see the message {string}")
	public void i_should_see_the_message(String message) {
		String message1 = registrationPage.displayRegistrationMessage();
		Assert.assertEquals(message1, "Account Created Successfully");

	}
}
