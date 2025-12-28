package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {

	WebDriver driver;

	public PaymentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement txt_country;

	@FindBy(xpath = "//span[@class='ng-star-inserted']")
	List<WebElement> countries;
	
	@FindBy (xpath = "//a[text()='Place Order ']")
	WebElement btn_placeOrder;

	public void enterCountry(String selectedCountry) {

		txt_country.sendKeys(selectedCountry);

	}

	public void selectCountry(String country) {

		for (int i = 0; i < countries.size(); i++) {
			String countryName = countries.get(i).getText();

			if (countryName.equalsIgnoreCase(country)) {
				countries.get(i).click();
				break;
			}
		}

	}
	
	public OrderConfirmationPage placeOrder() {
		btn_placeOrder.click(); 
		return new OrderConfirmationPage(driver);
	}
}
