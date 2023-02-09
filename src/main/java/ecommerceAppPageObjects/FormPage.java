package ecommerceAppPageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class FormPage {
	public static Logger log = LogManager.getLogger(FormPage.class.getName());
	
	public FormPage(AppiumDriver<AndroidElement> driver) throws NoSuchFieldException
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	@FindBy(xpath="//*[@text='Female']")
	public WebElement femaleOption;
	
	@FindBy(id="android:id/text1")
	private WebElement countryDropDown;
	
	@FindBy(xpath="//*[@text='Argentina']")
	public WebElement argentina;
	
	@FindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	public WebElement btnLetsShop;
	
	@FindBy(xpath="//android.widget.Toast[1]")
	public WebElement toastMessage;
	
	public WebElement getNameField()
	{
		log.debug("Trying to find the Name field");
		return nameField;
	}
	
	public WebElement getCountrySelection()
	{
		log.debug("Trying to find Country field");
		return countryDropDown;
	}

}
