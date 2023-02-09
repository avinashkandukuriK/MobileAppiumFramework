package ecommerceAppPageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class CheckoutPage {
	
	public CheckoutPage(AppiumDriver<AndroidElement> driver) throws NoSuchFieldException
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productList;
	
	@FindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	public WebElement totalAmount;
	
	@FindBy(id="android:id/text1")
	public WebElement countryDropDown;
	
	@FindBy(xpath="//*[@text='Argentina']")
	public WebElement argentina;
	
	@FindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	public WebElement btnLetsShop;
	
	@FindBy(id="com.androidsample.generalstore:id/productName")
	public List<WebElement> productName;
	
	public List<WebElement> getProductList()
	{
		return productList;
	}

}
