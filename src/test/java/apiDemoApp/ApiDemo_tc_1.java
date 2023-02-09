package apiDemoApp;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import apiDemoAppPageObjects.DependenciesPage;
import apiDemoAppPageObjects.HomePage;
import apiDemoAppPageObjects.PreferencesPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import resources.TestData;
import resources.base;

@Listeners(resources.Listeners.class) 
public class ApiDemo_tc_1 extends base{
	public static Logger log = LogManager.getLogger(ApiDemo_tc_1.class.getName());

	@Test(groups={"Smoke"}, dataProvider="ApiDemoTest_InputData1", dataProviderClass=TestData.class)
	public void dependenciesPageValidation(String input) throws IOException, NoSuchFieldException, InterruptedException 
	{
		AndroidDriver<AndroidElement> driver = runCapabilities("ApiDemosApp", false);
		
		HomePage homePage = new HomePage(driver);
		homePage.Preferences.click();
		log.debug("Clicked Preferencies Link");
		PreferencesPage preferencesPage = new PreferencesPage(driver);
		preferencesPage.dependencies.click();
		log.debug("Clicked Dependencies Link");
		
		DependenciesPage dependenciesPage = new DependenciesPage(driver);
		
		dependenciesPage.checkbox.click();
		dependenciesPage.settings.click();
		dependenciesPage.editTextField.sendKeys(input);
		dependenciesPage.buttons.get(1).click();
		
	}
}
