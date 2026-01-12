package stepDefinition;

import java.io.IOException;

import org.testng.Assert;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;
import pageObjects.MyCartPage;
import pageObjects.OrderConfirmationPage;
import pageObjects.PaymentPage;
import pageObjects.ProductCataloguePage;
import testBase.BaseClass;

public class SubmitOrderStepDefinition extends BaseClass {
	
	public ProductCataloguePage productCataloguePage;
	public PaymentPage paymentPage;
	public MyCartPage myCartPage;
	public OrderConfirmationPage orderConfirmationPage;

    @Given("^User logged in with username(.+) and password(.+)$")
	public void user_logged_in_with_username_and_password(String username, String password)
    {
    	
    	productCataloguePage = Hooks.loginPage.loginApp(username.trim(), password.trim());


	}
   @When("^I add product (.+) to the cart$")
	public void i_add_product_to_the_cart(String product) {
		productCataloguePage.addToCart(product);
	}

	@When("Go to cart page")
	public void go_to_cart_page() {
		myCartPage = productCataloguePage.goToCart();
	}

	@When("Checkout")
	public void checkout() {
		 paymentPage = myCartPage.checkOut();
	}

	@When("Enter payment details and place order")
	public void enter_payment_details_and_place_order() {
		paymentPage.enterCountry(pr.getProperty("selectedCountry"));
		paymentPage.selectCountry(pr.getProperty("country"));
		orderConfirmationPage = paymentPage.placeOrder();
	}

	@Then("{string} message is displayed on the confirmation page")
	public void confirm_message(String Message) {
		boolean message = orderConfirmationPage.confirmationMessage();
		Assert.assertTrue(message);
	}

}
