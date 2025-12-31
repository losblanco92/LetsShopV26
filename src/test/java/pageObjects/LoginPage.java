package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement textBox_email;

	@FindBy(id = "userPassword")
	WebElement textBox_password;

	@FindBy(id = "login")
	WebElement btn_login;
	
	@FindBy (xpath = "//a[text()='Register here']")
	WebElement lnk_register;

	public ProductCataloguePage loginApp(String email, String password) {

		textBox_email.sendKeys(email);
		textBox_password.sendKeys(password);
		btn_login.click();
		return new ProductCataloguePage(driver);
	}
	
	public RegistrationPage goToregisterAccount() {
		
		lnk_register.click();
		return new RegistrationPage(driver);
	}
}
