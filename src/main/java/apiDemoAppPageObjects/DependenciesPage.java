package apiDemoAppPageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class DependenciesPage {
	
	public DependenciesPage(AppiumDriver<AndroidElement> driver)
	{
		 PageFactory.initElements(driver, this);
	}

	@FindBy(id="android:id/checkbox")
	public WebElement checkbox;
	
	@FindBy(xpath="(//android.widget.RelativeLayout)[2]")
	public WebElement settings;
	
	@FindBy(className="android.widget.EditText")
	public WebElement editTextField;
	
	@FindBy(className="android.widget.Button")
	public List<WebElement> buttons;
}
