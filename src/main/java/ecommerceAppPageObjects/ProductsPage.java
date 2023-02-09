package ecommerceAppPageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class ProductsPage {
	
	public ProductsPage(AppiumDriver<AndroidElement> driver) throws NoSuchFieldException
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@text='ADD TO CART']")
	public List<WebElement> addToCart;
	
	@FindBy(id="com.androidsample.generalstore:id/productName")
	public List<WebElement> productName;
	
	@FindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	public WebElement buttonCart;
	
	@FindBy(xpath="//android.widget.Toast[1]")
	public WebElement toastMessage;

}
