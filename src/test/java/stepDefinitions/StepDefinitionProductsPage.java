package stepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.testng.Assert;

import ecommerceAppPageObjects.CheckoutPage;
import ecommerceAppPageObjects.FormPage;
import ecommerceAppPageObjects.ProductsPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import resources.Utilities;
import resources.base;

//Ecommerce_tc_2
@RunWith(Cucumber.class)
public class StepDefinitionProductsPage extends base{
	public static Logger log = LogManager.getLogger(StepDefinitionProductsPage.class.getName());
    
    @Given("^User is on Products page$")
    public void user_is_on_products_page() throws Throwable {
    	AndroidDriver<AndroidElement> driver = runCapabilities("GeneralStoreApp", false);
		FormPage formPage = new FormPage(driver);
		formPage.getNameField().sendKeys("Bob");
		driver.hideKeyboard();
		formPage.femaleOption.click();
		formPage.btnLetsShop.click();
		ProductsPage productsPage = new ProductsPage(driver);
		System.out.println(productsPage.productName.size());
		int count = productsPage.productName.size();
		Assert.assertTrue(count>1);
    }

    @When("^User adding 1 product (.+)$")
    public void user_adding_1_product(String productname) throws Throwable {
		ProductsPage productsPage = new ProductsPage(driver);
    	int count = productsPage.productName.size();

		for (int i = 0; i < count; i++) {
			String text = productsPage.productName.get(i).getText();
			if (text.equalsIgnoreCase(productname)) 
			{
				productsPage.addToCart.get(i).click();
				break;
			}
		}
    }

    @When("^User clicking on Cart button$")
    public void user_clicking_on_cart_button() throws Throwable {
		ProductsPage productsPage = new ProductsPage(driver);
    	productsPage.buttonCart.click();
		Thread.sleep(2000);
    }

    @Then("^User successfully getting to Checkout page$")
    public void user_successfully_getting_to_checkout_page() throws Throwable {
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		String checkoutPageProductText = checkoutPage.productName.get(0).getText();
		Assert.assertEquals("Jordan 6 Rings", checkoutPageProductText);
    }

    @Then("^Toast error message for adding the product is displayed$")
    public void toast_error_message_for_adding_the_product_is_displayed() throws Throwable {
		ProductsPage productsPage = new ProductsPage(driver);
    	String toastMessage = productsPage.toastMessage.getAttribute("name");
		Assert.assertEquals("Please add some product at first", toastMessage);
    }

    @And("^scrolling to the product with the name (.+)$")
    public void scrolling_to_the_product_with_the_name(String productname) throws Throwable {
    	Utilities u = new Utilities(driver);
		u.scrollToText(productname);
    }

    @And("^and clicking on Cart button$")
    public void and_clicking_on_cart_button() throws Throwable {
		ProductsPage productsPage = new ProductsPage(driver);
    	productsPage.buttonCart.click();
		Thread.sleep(2000);
    }
    
    @Before
    public void driverInitialization() throws IOException, InterruptedException
    {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(2000);
		service=startServer();
    }
    
	@After
	public void stopServices() throws IOException, InterruptedException
	{
		service.stop();
		Thread.sleep(2000);
	}

}
