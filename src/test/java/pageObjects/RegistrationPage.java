package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

	WebDriver driver;

	public RegistrationPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "firstName")
	WebElement txt_firstName;

	@FindBy(id = "lastName")
	WebElement txt_lastName;

	@FindBy(id = "userEmail")
	WebElement txt_Email;

	@FindBy(id = "userMobile")
	WebElement txt_number;

	@FindBy(id = "userPassword")
	WebElement txt_password;

	@FindBy(id = "confirmPassword")
	WebElement txt_confirmpassword;

	@FindBy(xpath = "//input[@type='checkbox']")
	WebElement chkBox;

	@FindBy(id = "login")
	WebElement btn_register;

	@FindBy(xpath = "//h1[text()='Account Created Successfully']")
	WebElement msg_register;

	public void setFirstName(String fname) {
		txt_firstName.sendKeys(fname);
	}

	public void setLastName(String lname) {
		txt_lastName.sendKeys(lname);
	}

	public void setEmail(String email) {
		txt_Email.sendKeys(email);
	}

	public void setNumber(String number) {
		txt_number.sendKeys(number);
	}

	public void setPassword(String password) {
		txt_password.sendKeys(password);
	}

	public void confirmPassword(String password) {
		txt_confirmpassword.sendKeys(password);
	}

	public void selectCheckBox() {
		chkBox.click();
	}

	public void clickRegister() {
		btn_register.click();
	}

	public String displayRegistrationMessage() {
		try {
			String message = msg_register.getText();
			return message;
		} catch (Exception e) {
			return e.getMessage();
		}

	}
}
