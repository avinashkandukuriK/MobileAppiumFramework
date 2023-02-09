package stepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.testng.Assert;

import ecommerceAppPageObjects.CheckoutPage;
import ecommerceAppPageObjects.ProductsPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.junit.Cucumber;
import resources.base;

//Ecommerce_tc_5
@RunWith(Cucumber.class)
public class StepDefinitionCheckoutPage extends base{
	public static Logger log = LogManager.getLogger(StepDefinitionCheckoutPage.class.getName());
	
    @Then("^User successfully getting to Checkout page with 2 products$")
    public void user_successfully_getting_to_checkout_page() throws Throwable {
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		int count = checkoutPage.getProductList().size();
		Assert.assertTrue(count>1);
    }

    @And("^selected item (.+) on Products page is matching with the item on Checkout page$")
    public void selected_item_on_products_page_is_matching_with_the_item_on_checkout_page(String productname) throws Throwable {
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		String checkoutPageProductText = checkoutPage.productName.get(0).getText();
		Assert.assertEquals(productname, checkoutPageProductText);
    }

    @And("^adding first 2 products from the list$")
    public void adding_first_2_products_from_the_list() throws Throwable {
		ProductsPage productsPage = new ProductsPage(driver);
		productsPage.addToCart.get(0).click();
		productsPage.addToCart.get(0).click();
    }

    @And("^products sum on is matching with sum of 2 selected products$")
    public void products_sum_on_is_matching_with_sum_of_2_selected_products() throws Throwable {
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
		log.info("Successfully validated Products sum");
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
	
	public static double getAmount(String value)

	{
		value = value.substring(1);
		double amount2value = Double.parseDouble(value);
		return amount2value;
	}

}
