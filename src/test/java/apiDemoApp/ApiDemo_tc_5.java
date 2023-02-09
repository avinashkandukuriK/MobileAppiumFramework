package apiDemoApp;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import resources.base;

@Listeners(resources.Listeners.class) 
public class ApiDemo_tc_5 extends base{
	public static Logger log = LogManager.getLogger(ApiDemo_tc_5.class.getName());

	@Test(groups={"Smoke"})
	public void landingPageScrollValidation() throws IOException, NoSuchFieldException, InterruptedException {
		// TODO Auto-generated method stub
		
		AndroidDriver<AndroidElement> driver = runCapabilities("ApiDemosApp", false);
		
		driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();
		
		//Android API code, not Appium
		log.info("Scrolling to WebView element");
		System.out.println(driver.findElementsByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text('WebView'));"));
		
	}
}
