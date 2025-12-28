package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class ProductCataloguePage extends AbstractComponent{

	WebDriver driver;
	
	public ProductCataloguePage (WebDriver driver)
	{  
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//div[@class='card']//b")
	List<WebElement> products;
	
	@FindBy (xpath = "//*[text()=' Add To Cart']")
	List<WebElement> btn_addToCart;
	

	@FindBy (xpath = "//button[contains(@routerlink,'cart')]")
	WebElement lnk_cart;
	
	@FindBy (xpath = "//div[@aria-label='Login Successfully']")
	WebElement alert_login;
	
	
	
	public void addToCart(String product) {
		
		for(int i=0; i<products.size(); i++)
		{
			String productName= products.get(i).getText();
			
			if(productName.equals(product))
			{
				
				btn_addToCart.get(i).click();
			}
		}
		
	}
	
	
	
	
	
		
	}
	

