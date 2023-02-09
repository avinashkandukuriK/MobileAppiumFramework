package apiDemoAppPageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class DragAndDropPage {
	
	public DragAndDropPage(AppiumDriver<AndroidElement> driver)
	{
		 PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="android.view.View")
	public List<WebElement> circlesList;
}
