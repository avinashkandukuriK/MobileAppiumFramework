package apiDemoAppPageObjects;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class ExpandableListsPage {
	
	public ExpandableListsPage(AppiumDriver<AndroidElement> driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//android.widget.TextView[@text='1. Custom Adapter']")
	public WebElement customAdapter;
	
	@FindBy(xpath="//android.widget.TextView[@text='People Names']")
	public WebElement peopleNames;
	
	@FindBy(id="android:id/title")
	public WebElement sampleMenu;
}
