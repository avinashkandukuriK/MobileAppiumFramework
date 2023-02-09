package apiDemoApp;
import static io.appium.java_client.touch.offset.ElementOption.element;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import apiDemoAppPageObjects.DragAndDropPage;
import apiDemoAppPageObjects.HomePage;
import apiDemoAppPageObjects.ViewsPage;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import resources.base;

@Listeners(resources.Listeners.class) 
public class ApiDemo_tc_3 extends base {
	public static Logger log = LogManager.getLogger(ApiDemo_tc_3.class.getName());

	@Test(groups={"Smoke"})
	public void dragAndDropPageValidation() throws IOException, NoSuchFieldException, InterruptedException{
		//Drag and Drop the circle
		
		AndroidDriver<AndroidElement> driver = runCapabilities("ApiDemosApp", false);
		
		HomePage homePage = new HomePage(driver);
		ViewsPage viewsPage = new ViewsPage(driver);
		DragAndDropPage dragAndDropPage = new DragAndDropPage(driver);
		
		homePage.Views.click();
		log.debug("Clicked views Link");
		viewsPage.dragAndDrop.click();
		log.debug("Clicked dragAndDrop Link");
		
		WebElement source = dragAndDropPage.circlesList.get(0);
		WebElement destination = dragAndDropPage.circlesList.get(1);
		TouchAction<?> touchAction = new TouchAction<>(driver);
		log.info("Moving circle to the right");
		touchAction.longPress(element(source)).moveTo(element(destination)).release().perform();
	}
}
