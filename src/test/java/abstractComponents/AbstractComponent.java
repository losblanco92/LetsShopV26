package abstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.MyCartPage;
import pageObjects.OrderHistoryPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//button[contains(@routerlink,'cart')]")
	WebElement lnk_cart;

	By toast_Message = By.xpath("//div[@aria-label='Product Added To Cart']");
	
	@FindBy (xpath = "//button[contains(@routerlink,'myorders')]")
	WebElement lnk_orders;
	
	@FindBy (xpath = "//i[@class='fa fa-sign-out']")
	WebElement lnk_signOut;

	public void waitForElementToAppear(By finBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(finBy));
	}
	
	
	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void waitforElementToBeClickable(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public void scrollToTop() {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollTo(0, 0)");
	}

	public void scrollToBottom() {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}


	public MyCartPage goToCart() {
		scrollToTop();
		waitForElementToAppear(toast_Message);
		
		lnk_cart.click();
        return new MyCartPage(driver);
	}
	
	public OrderHistoryPage goToOrderHistory() {
		lnk_orders.click();
		return new OrderHistoryPage(driver);
	}
	
	public void clickSignOut() {
		
		lnk_signOut.click();
		}

	public boolean signOut() {
	    try {
	        return lnk_signOut.isDisplayed();
	    } catch (Exception e) {
	        return false;
	    }
	}
}
