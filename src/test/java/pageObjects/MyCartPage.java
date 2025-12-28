package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyCartPage {

	WebDriver driver;

	public MyCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='cartSection']//h3")
	List<WebElement> addedItem;
	
	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement btn_checkOut;

	public String addedProductList(String product) {

		for (WebElement item : addedItem) {
			if (item.getText().equalsIgnoreCase(product)) {
				return item.getText();
			}
		}
		return null;
	}
	
	public PaymentPage checkOut() {
		
		btn_checkOut.click();
		return new PaymentPage(driver);
	}
}
