package apiDemoAppPageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ViewsPage {
	public static AndroidDriver<AndroidElement> driver;
	
	public ViewsPage(AppiumDriver<AndroidElement> driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//android.widget.TextView[@text='Drag and Drop']")
	public WebElement dragAndDrop;
	
	@FindBy(xpath="//android.widget.TextView[@text='Expandable Lists']")
	public WebElement expandableLists;
	
	@FindBy(xpath="//android.widget.TextView[@text='Date Widgets']")
	public WebElement dateWidgets;
}
