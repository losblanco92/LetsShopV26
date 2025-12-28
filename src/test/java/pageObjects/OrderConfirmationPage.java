package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPage {
	
	WebDriver driver;
	
	public OrderConfirmationPage (WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	
	@FindBy (xpath = "//h1")
	WebElement msg_Confirmation;
	
	
	
	public boolean confirmationMessage() {
		
		boolean message= msg_Confirmation.isDisplayed();
		
		return message;
	}
}
