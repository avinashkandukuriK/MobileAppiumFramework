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
public class Ecommerce_tc_4 extends base {
	public static Logger log = LogManager.getLogger(Ecommerce_tc_4.class.getName());
	
	@Test(groups={"Regression"}, dataProvider="Ecommerce_InputData1", dataProviderClass=TestData.class)
	public void checkoutPageValidation(String nameFieldInput) throws IOException, InterruptedException, NoSuchFieldException {
		//Validate the total amount displayed matched with the sum of product amount selected for Shopping
		
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
		log.info("Adding 2 products");
		productsPage.addToCart.get(0).click();
		productsPage.addToCart.get(0).click();
		productsPage.buttonCart.click();
		log.debug("Clicked on Cart button");
		Thread.sleep(4000);
		
		CheckoutPage checkoutPage = new CheckoutPage(driver);

		int count = checkoutPage.getProductList().size();
		double sum = 0;
		for (int i = 0; i < count; i++) {
			String amount1 = checkoutPage.getProductList().get(i).getText();
			double amount = getAmount(amount1);
			sum = sum + amount;
		}

		System.out.println("sum of products " + sum);

		String total = checkoutPage.totalAmount.getText();
		total = total.substring(1);
		double totalValue = Double.parseDouble(total);
		System.out.println("total value of products " + totalValue);
		Assert.assertEquals(sum, totalValue);
		log.info("sum of 2 products on checkout page is equal to total value of added products");
	}
	
	public static double getAmount(String value)

	{
		value= value.substring(1);
		double amount2value=Double.parseDouble(value);
		return amount2value;
	}
}
