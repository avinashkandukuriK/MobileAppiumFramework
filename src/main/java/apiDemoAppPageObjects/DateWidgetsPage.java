package apiDemoAppPageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class DateWidgetsPage {
	public static AndroidDriver<AndroidElement> driver;
	
	public DateWidgetsPage(AppiumDriver<AndroidElement> driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="android:id/text1")
	private List<WebElement> dateWidgetsOptions;
	
	@FindBy(xpath="(//*[@content-desc='9']")
	public WebElement nineClock;
	
	@FindBy(xpath="(//*[@content-desc='15']")
	public WebElement fifteenClock;
	
	@FindBy(xpath="(//*[@content-desc='45']")
	public WebElement fourtFiveClock;
	
	
	public List<WebElement> getDatetWidgetsOptions()
	{
		return dateWidgetsOptions;
	}
	
}
