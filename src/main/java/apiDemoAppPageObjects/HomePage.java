package apiDemoAppPageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class HomePage {
	
	public HomePage(AppiumDriver<AndroidElement> driver) throws NoSuchFieldException
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//android.widget.TextView[@text='Preference']")
	public WebElement Preferences;
	
	@FindBy(xpath="//android.widget.TextView[@text='Views']")
	public WebElement Views;
}
