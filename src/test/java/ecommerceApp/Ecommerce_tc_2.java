package ecommerceApp;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ecommerceAppPageObjects.FormPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import resources.TestData;
import resources.Utilities;
import resources.base;

@Listeners(resources.Listeners.class)
public class Ecommerce_tc_2 extends base {
	public static Logger log = LogManager.getLogger(Ecommerce_tc_2.class.getName());
	
	@Test(dataProvider="Ecommerce_InputData1",dataProviderClass=TestData.class, groups={"Smoke"})
	public void landingPageToastValidation(String nameFieldInput) throws IOException, InterruptedException, NoSuchFieldException {
		//Fill the form details and verify Toast error messages displayed appropriately for wrong inputs
		
		AndroidDriver<AndroidElement> driver = runCapabilities("GeneralStoreApp", false);
		
		FormPage formPage = new FormPage(driver);
		formPage.femaleOption.click();
		log.debug("Clicked on female option");
		formPage.getCountrySelection().click();
		log.debug("Clicked on Country to select Argentina");
		Utilities utilities = new Utilities(driver);
		utilities.scrollToText("Argentina");
		formPage.argentina.click();
		formPage.btnLetsShop.click();
		log.debug("Clicked on Lets shop button");
		String toastMessage = formPage.toastMessage.getAttribute("name");
		Assert.assertEquals("Please enter your name", toastMessage);
		log.debug("Clicked on Lets shop button");
	}
}
