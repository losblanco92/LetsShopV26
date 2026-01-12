package testCases;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.MyCartPage;
import pageObjects.OrderConfirmationPage;
import pageObjects.OrderHistoryPage;
import pageObjects.PaymentPage;
import pageObjects.ProductCataloguePage;
import testBase.BaseClass;
import testBase.TestNGBase;

public class SubmitOrderTest extends TestNGBase  {

	@Test(groups="Regression")
	public void submitOrder() throws IOException {

		logger.info("***Staring Submit Order Test******");
		logger.info("***On Login Page******");
		ProductCataloguePage productCataloguePage = loginPage.loginApp(pr.getProperty("user"),
				pr.getProperty("password"));
		logger.info("***On Product Catalogue Page.Adding product to cart******");
		productCataloguePage.addToCart(pr.getProperty("product"));
		logger.info("***Going to My Cart Page******");
		MyCartPage myCartPage = productCataloguePage.goToCart();

		String addedProduct = myCartPage.addedProductList(pr.getProperty("product"));
		logger.info("***Checking out, going to payments page******");
		PaymentPage paymentPage = myCartPage.checkOut();

		logger.info("***Selecting country******");
		paymentPage.enterCountry(pr.getProperty("selectedCountry"));
		paymentPage.selectCountry(pr.getProperty("country"));
		logger.info("***Placing order******");
		OrderConfirmationPage orderConfirmationPage = paymentPage.placeOrder();
		logger.info("***Validating Order Confirmation message******");
		boolean message = orderConfirmationPage.confirmationMessage();
		Assert.assertFalse(message);
		logger.info("***Submit Order Test Finished******");

	}
	
	@Test(dependsOnMethods = {"submitOrder"} )
	public void orderHistoryTest() {
		
		logger.info("***Staring Order History Test******");
		logger.info("***On Login Page******");
		ProductCataloguePage productCataloguePage = loginPage.loginApp(pr.getProperty("user"),
				pr.getProperty("password"));
		logger.info("***Going To order History Page******");
		OrderHistoryPage orderHistoryPage= productCataloguePage.goToOrderHistory();
		
		logger.info("***Validating Order******");
		boolean status= orderHistoryPage.checkOrder(pr.getProperty("product"));
		Assert.assertTrue(true);
		
		logger.info("***Order History Test Finished******");
		
	}

}
