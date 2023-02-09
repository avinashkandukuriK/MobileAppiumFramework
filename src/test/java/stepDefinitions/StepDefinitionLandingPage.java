package stepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.testng.Assert;

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
public class StepDefinitionLandingPage extends base{
	public static Logger log = LogManager.getLogger(StepDefinitionLandingPage.class.getName());
    
    @Given("^User is on General Store landing page$")
    public void user_is_on_general_store_landing_page() throws Throwable {

    }

    @When("^User choosing his country (.+) from dropdown$")
    public void user_choosing_his_country_from_dropdown(String country) throws Throwable {
    	FormPage formPage = new FormPage(driver);
    	formPage.getCountrySelection().click();
		Utilities u = new Utilities(driver);
		u.scrollToText(country);
		formPage.argentina.click();
    }

    @Then("^Toast error message for entering the name is displayed$")
    public void toast_error_message_for_entering_the_name_is_displayed() throws Throwable {
    	FormPage formPage = new FormPage(driver);
    	String toastMessage = formPage.toastMessage.getAttribute("name");
		Assert.assertEquals("Please enter your name", toastMessage);
    }

    @And("^and his Gender (.+) from checkboxes$")
    public void his_gender_from_checkboxes(String gender) throws Throwable {
    	FormPage formPage = new FormPage(driver);
    	formPage.femaleOption.click();
    }

    @And("^and clicking on Lets shop button$")
    public void clicking_on_lets_shop_button() throws Throwable {
    	FormPage formPage = new FormPage(driver);
    	formPage.btnLetsShop.click();
    }

    @Then("^Toast error message for entering the name is not displayed$")
    public void toast_error_message_for_entering_the_name_is_not_displayed() throws Throwable {
    	FormPage formPage = new FormPage(driver);
    }

    @And("^entering his name (.+) in Your name field$")
    public void entering_his_name_something_in_your_name_field(String name) throws Throwable {
    	FormPage formPage = new FormPage(driver);
        formPage.getNameField().sendKeys(name);
    }

    @And("^User successfully getting to Products page$")
    public void user_successfully_getting_to_products_page() throws Throwable {
		ProductsPage productsPage = new ProductsPage(driver);
		productsPage.addToCart.get(0).click();
    }
    
    @Before
    public void driverInitialization() throws IOException, InterruptedException
    {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
		service=startServer();
    	
    	AndroidDriver<AndroidElement> driver = runCapabilities("GeneralStoreApp", false);
    }
    
	@After
	public void stopServices() throws IOException, InterruptedException
	{
		service.stop();
		Thread.sleep(6000);
	}

}
