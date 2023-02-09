package ecommerceApp;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ecommerceAppPageObjects.CheckoutPage;
import ecommerceAppPageObjects.FormPage;
import ecommerceAppPageObjects.ProductsPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import resources.TestData;
import resources.Utilities;
import resources.base;

@Listeners(resources.Listeners.class)
public class Ecommerce_tc_3 extends base {
	public static Logger log = LogManager.getLogger(Ecommerce_tc_3.class.getName());

	@Test(groups={"Regression"}, dataProvider="Ecommerce_InputData1", dataProviderClass=TestData.class)
	public void addToCartValidation(String nameFieldInput) throws IOException, InterruptedException, NoSuchFieldException {
		//shop the items in the app by scrolling to specific Product and add to cart
		//validate if the items selected in the page 2 are matching with the items displayed in check out page

		AndroidDriver<AndroidElement> driver = runCapabilities("GeneralStoreApp", false);

		FormPage formPage = new FormPage(driver);
		formPage.getNameField().sendKeys(nameFieldInput);
		driver.hideKeyboard();
		formPage.femaleOption.click();
		log.debug("Clicked on female option");
		
		formPage.getCountrySelection().click();
		log.debug("Clicked on Country to select Argentina");
		Utilities utilities = new Utilities(driver);
		utilities.scrollToText("Argentina");
		formPage.argentina.click();
		formPage.btnLetsShop.click();
		log.debug("Clicked on Lets shop button");
		
		ProductsPage productsPage = new ProductsPage(driver);
	
		log.info("Scrolling to Jordan 6 Rings product");
		utilities.scrollToText("Jordan 6 Rings");
		
		int count = productsPage.productName.size();

		for (int i = 0; i < count; i++) {
			String text = productsPage.productName.get(i).getText();
			if (text.equalsIgnoreCase("Jordan 6 Rings")) 
			{
				productsPage.addToCart.get(i).click();
				break;
			}
		}
		
		productsPage.buttonCart.click();
		log.debug("Clicked on Cart button");
		Thread.sleep(4000);
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		String checkoutPageProductText = checkoutPage.productName.get(0).getText();
		Assert.assertEquals("Jordan 6 Rings", checkoutPageProductText);
		log.info("Product on checkout page is equal to added product");
	}
}
