package apiDemoApp;
import static java.time.Duration.ofSeconds;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import apiDemoAppPageObjects.DateWidgetsPage;
import apiDemoAppPageObjects.HomePage;
import apiDemoAppPageObjects.ViewsPage;
import io.appium.java_client.TouchAction;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import resources.base;

@Listeners(resources.Listeners.class)
public class ApiDemo_tc_6 extends base {
	public static Logger log = LogManager.getLogger(ApiDemo_tc_6.class.getName());

	@Test(groups={"Regression"})
	public void dateWidgetsPageSwipingValidation() throws IOException, NoSuchFieldException, InterruptedException {
		// TODO Auto-generated method stub
		
		//swiping test
		AndroidDriver<AndroidElement> driver = runCapabilities("ApiDemosApp", false);
		
		HomePage homePage = new HomePage(driver);
		ViewsPage viewsPage = new ViewsPage(driver);
		DateWidgetsPage dateWidgetsPage = new DateWidgetsPage(driver);
		
		homePage.Views.click();
		viewsPage.dateWidgets.click();
		log.debug("Clicked views Link");
		
		dateWidgetsPage.getDatetWidgetsOptions().get(1).click();
		log.debug("Clicked Datet Widgets option 1");
		driver.findElementByXPath("//*[@content-desc='9']").click();
		
		TouchAction<?> touchAction = new TouchAction<>(driver);
		
		//long press //on element // 2 sec // move to another element and release
		WebElement first = driver.findElementByXPath("//*[@content-desc='15']");
		WebElement second = driver.findElementByXPath("//*[@content-desc='45']");
		log.info("Long press on 45 minutes and moving the arrow to 15 minutes");
		touchAction.longPress(longPressOptions().withElement(element(first)).withDuration(ofSeconds(2))).moveTo(element(second)).release().perform();
	}
}
