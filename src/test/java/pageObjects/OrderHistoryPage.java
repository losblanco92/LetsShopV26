package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class OrderHistoryPage extends AbstractComponent {

	WebDriver driver;
	
	public OrderHistoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//tbody//td[2]")
	List<WebElement> orders;
	
	public boolean checkOrder (String product)
	{
		for(WebElement order: orders)
		{
			if(order.getText().equals(product))
			{
				return true;
			}
		}
		return false;
	}
	
}
