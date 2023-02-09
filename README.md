<b><h3>[1. Project description](#description)</h3></b>

<b><h3>[2. How to run and use](#howtorun)</h3></b>

<b><h3>[3. Implemented Details](#details)</h3></b>
- [Testng xml suites to run tests](#testng)
- [Cucmber feature file with test cases, step definition file](#feature)
- [Parametrizing with data sets, driving all global variables from global properties file](#parametrizing)
- [Reusable methods](#methods)
- [Tagging mechanism](#tagging)
- [Data driven from feature files, external data files](#datadriven)
- [Extent Reports](#reports)
- [Logs](#logs)
- [Screenshots capturing](#screenshots)
- [OOPS concepts achieved](#oops)

<a id="description"></a>
## __1. Project description__

This Framework is a Appium test automation project built using Java, Maven (build management tool) with Page Object Mechanism (designing patterns for tests development), TestNG (Unit Testing Framework), Cucumber, Data Driven concepts from external resources, generating Logs, Reports, Capturing Screenshots. This Framework uses the ability to run tests with TestNG or Cucumber (BDD) approaches.

As the test target of this project as an example used 2 free apps for Android OS - ApiDemo and Ecommerce app.
<img src="src\test\resources\readme_images\apidemo_app.png" width="300">

<img src="src\test\resources\readme_images\ecommerce_app.png" width="300">

Structure:

<img src="src\test\resources\readme_images\structure.png">


<a id="howtorun"></a>
## __2. How to run and use__


For this project implemented 3 different types of tests launch using runMode variable from _global.properties_
* using IDE - add local_ide as the property;
* using command line with maven commands - add maven_command as the property. Example of maven command to run tests (from the root directory of project):
```
mvn test
```
or for Cucumber feature files:
<a id="filter"></a>
```
mvn test -Dcucumber.filter.tags="@Smoke"
```

* cloud testing using browserstack with cloud properties. You'll need to update cloud properties with your data.

settings from _global.properties_ file:
```
################Apps#################
GeneralStoreApp=General-Store.apk
ApiDemosApp=ApiDemos-debug.apk
########run mode - local_ide, cloud or maven_command########
runMode=local_ide
device=emulator-5554
################cloud properties##################
browserstackUser=
browserstackKey=
browserstackApp1Url=
browserstackApp2Url=
browserstackDevice=Google Pixel 3
browserstackAppOSVersion=9.0
browserstackHubUrl=http://hub.browserstack.com/wd/hub
###################################################
```
Each test use run runCapabilities() method to trigger test for particular App with test env (local or cloud):
```
AndroidDriver<AndroidElement> driver = runCapabilities("ApiDemosApp", false);
```
from _Base.java_
```
	public AndroidDriver<AndroidElement> runCapabilities(String appName, Boolean cloud) throws IOException, InterruptedException
	{
		if(cloud)
		{
			return cloudcapabilities(appName);
		}
		{
			return capabilities(appName);
		}
	}
    ....
```



To run this project locally you need to make sure you have installed Java, Eclipse or another ide, Maven with setting up Java, Maven home paths on your machine.

To run tests locally you'll need to setup Android Studio with Android virtual device:

<img src="src\test\resources\readme_images\emulator1.png" width="400">

then you'll need to update the path to emulator and emulator name in \src\test\resources\startEmulator.bat file

```
c:
cd C:\Users\andre\AppData\Local\Android\Sdk\emulator
emulator -avd Emulator_1_-_Pixel_2_XL_API_30
```
<a id="details"></a>
<b><h3>[3. Implemented Details](#details)</h3></b>
<a id="testng"></a>
### __3.1 Testng xml suites to run tests__
This project involves running tests for various needs. For the purposes of running parallel tests,running smoke, regression, cucumber type of tests created different types of test suites stored in src\test\resources\testsuites.

<img src="src\test\resources\readme_images\testsuites.png">

from _testng.xml_
```
<suite name="Suite">
	<listeners>
		<listener class-name="resources.Listeners" />
	</listeners>
	<test thread-count="5" name="ApiDemo app test">
		<classes>
			<class name="apiDemoApp.ApiDemo_tc_1" />
			<class name="apiDemoApp.ApiDemo_tc_2" />
			<class name="apiDemoApp.ApiDemo_tc_3" />
			<class name="apiDemoApp.ApiDemo_tc_4" />
			<class name="apiDemoApp.ApiDemo_tc_5" />
			<class name="apiDemoApp.ApiDemo_tc_6" />
		</classes>
	</test>
		<test thread-count="5" name="Ecommerce app test">
		<classes>
			<class name="ecommerceApp.Ecommerce_tc_1" />
			<class name="ecommerceApp.Ecommerce_tc_2" />
			<class name="ecommerceApp.Ecommerce_tc_3" />
			<class name="ecommerceApp.Ecommerce_tc_4" />
			<class name="ecommerceApp.Ecommerce_tc_5" />
		</classes>
	</test>
</suite>
```
<a id="feature"></a>
### __3.2 Cucumber feature file with test cases, step definition file__

BDD concepts with Cucumber implemented using feature files (with test cases), Step definitions files (with supported code) and TestRunner (class for running tests).

Feature File is an entry point to the Cucumber tests and cucumber proposes to write test scenario in the Given/When/Then/And format.  
Example of the feature file used in this API framework - _EcommerceAppLandingPage.feature_ (from src\test\java\features forlder):

```
Feature: Landing page form

@Regression @LandingPage
Scenario Outline: Landing page default login without the name
    Given User is on General Store landing page
    When User choosing his country <country> from dropdown
    And and his Gender <gender> from checkboxes
    And and clicking on Lets shop button
    Then Toast error message for entering the name is displayed
    
    Examples:
    |country   |gender |
    |Argentina |male   |
    
@Smoke @LandingPage
Scenario Outline: Landing page default login with the name
    Given User is on General Store landing page
    When User choosing his country <country> from dropdown
    And and his Gender <gender> from checkboxes
    And entering his name <name> in Your name field
    And and clicking on Lets shop button
    Then Toast error message for entering the name is not displayed
    And User successfully getting to Products page
    
    Examples:
    |country   |gender  |name    |
    |Argentina |male    |Bob     |
    |Argentina |female  |Jessica |
```

part of _StepDefinitionLandingPage.java_ (from src\test\java\stepDefinitions\ folder):
```
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
```
<a id="parametrizing"></a>
### __3.3 Parametrizing with data sets, driving all global variables from global properties file__
Implemented parametrizing to run tests with multiple data sets using Cucumber Example Keyword:

```
@Regression @ProductPage
Scenario Outline: Products page default login with adding 1 product
    Given User is on Products page
    And scrolling to the product with the name <productName>
    When User adding 1 product <productName>
    And and clicking on Cart button
    Then User successfully getting to Checkout page
    
    Examples:
    |productName    |
    |Jordan 6 Rings |
```

Implemented driving all global variables from _global.properties_ file:

```
################Apps#################
GeneralStoreApp=General-Store.apk
ApiDemosApp=ApiDemos-debug.apk
########run mode - local_ide, cloud or maven_command########
runMode=local_ide
device=emulator-5554
################cloud properties##################
browserstackUser=
browserstackKey=
browserstackApp1Url=
browserstackApp2Url=
browserstackDevice=Google Pixel 3
browserstackAppOSVersion=9.0
browserstackHubUrl=http://hub.browserstack.com/wd/hub
###################################################
```
<a id="methods"></a>
### __3.4 Reusable methods__
Created _Utilities.java class_ in resources folder to define all reusable methods - as scrollToText():
```
	public void scrollToText(String text)
	{
		driver.findElementsByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));");
	}
```
<a id="tagging"></a>
### __3.5 Tagging mechanism__

Implemented Cucumber tagging mechanism to run selected tests from Test Runner file or with Maven command using [filter](#filter). As example from EcommerceAppCheckoutPage.feature file tags @Regression, @CheckoutPage:
```
@Regression @CheckoutPage
Scenario Outline: Validate selected item on checkout page
    Given User is on Products page
    And scrolling to the product with the name <productName>
    When User adding 1 product <productName>
    And and clicking on Cart button
    Then User successfully getting to Checkout page
    And selected item <productName> on Products page is matching with the item on Checkout page
    
    Examples:
    |productName    |
    |Jordan 6 Rings |
```

### __3.6 Data driven from feature files, external data files__
Implemented Data driven mechanism to drive data dinamically from Feature files - 
as from _EcommerceAppLandingPage.feature_ file (from src\test\java\features folder) where we are driven data from Examples for country, gender, name:
```
@Smoke @LandingPage
Scenario Outline: Landing page default login with the name
    Given User is on General Store landing page
    When User choosing his country <country> from dropdown
    And and his Gender <gender> from checkboxes
    And entering his name <name> in Your name field
    And and clicking on Lets shop button
    Then Toast error message for entering the name is not displayed
    And User successfully getting to Products page
    
    Examples:
    |country    |gender  |name    |
    |Argentina  |male    |Bob     |
    |Argentina  |female  |Jessica |
```
also to get payload data for test classes created a separate class _TestData.java_:
```
public class TestData {
	
	@DataProvider(name="ApiDemoTest_InputData1")
	public Object[][] getDataForEditField()
	{
		Object[][] obj = new Object[][]
				{
			{"testdata1"}, {"testdata2"}
			
			};
			
			return obj;
	}
	
	@DataProvider(name="Ecommerce_InputData1")
	public Object[][] getDataForNameField()
	{
		Object[][] obj = new Object[][]
				{
			{"Sam"}
			
			};
			
			return obj;
		
	}

}
```
using dataProvider in test classes:
```
@Test(groups={"Smoke"}, dataProvider="ApiDemoTest_InputData1", dataProviderClass=TestData.class)
public void dependenciesPageValidation(String input) {
.....
```
<a id="reports"></a>
### __3.7 Extent Reports__
Reports are generating using Extent Reports and Extentreports adapter dependencies defined in _pom.xml_, Reports configs are defined in _extent.properties, spark-config.xml_ files. Generated 2 types of reports - with html and pdf formats.

<img src="src\test\resources\readme_images\report1.png">

<img src="src\test\resources\readme_images\report2.png">

<img src="src\test\resources\readme_images\report3.png">

<a id="logs"></a>

### __3.8 Logs__
Logs mechanisme implemented using log4j (log4j-api and log4j-core dependecies in pom.xml). Logs are stored in \reports\logs folder. Configs are stored in \src\main\resources\log4j2.xml:
<a id="screenshots"></a>
### __3.9 Screenshots capturing__
Screenshots capturing mechanisme is triggered from _Listeners.java_ on test failures:

```
    	try {
    		extentTest.get().addScreenCaptureFromPath(getScreenShotPath(testMethodName, driver), result.getMethod().getMethodName());
		} catch (IOException e) {
			extentTest.get().fail("Test failed, cannot attach screenshot");
			log.error("Test failed, cannot attach screenshot");
		}
```
using getScreenShot() method from _base.java_:
```
	public String getScreenshot(String s) throws IOException {
		Date date = new Date();
		File scrfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\screenshots\\" 
				+ s + "_" + date.toString().replace(":", "_").replace(" ", "_") + ".png";
		FileUtils.copyFile(scrfile, new File(destinationFile));
		return destinationFile;
	}
```
<a id="oops"></a>
* ### __3.1 OOPS concepts achieved__:


    __Abstraction__ - achieved by interfaces.

    As example from _Listeners.java_ where used __implements ItestListener__ interface:
    ```
    public class Listeners extends base implements ITestListener {
	...	
	}
    ```
    __Encapsulation__ - achieved by using private locators with public methods to avoid misuse the coding standards of the framework.
    
    Example from FormPage.java_:
    ```
    @FindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;

	public WebElement getNameField()
	{
		log.debug("Trying to find the Name field");
		return nameField;
	}

    ```
    __Polymorphism__ - achieved using combination of overloading and overriding.

    Used Implicit wait as an example of overloading from _base.java_:
    ```
   	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    ```
    another example of overloading used in Framework:
    ```
    .FindBy(“<All type of locator e.g. id , xpath>”).
    ```

    overriding methods used in _Listeneres.java_. As example:
    ```
    @Override		
    public void onTestSuccess(ITestResult result) {					
    	String logText = "<b> Test Method " + result.getMethod().getMethodName() + " Successfull<b>";
    	Markup m = (Markup) MarkupHelper.createLabel(logText, ExtentColor.GREEN);
    	extentTest.get().log(Status.PASS, m);
    }
    ```

    __Inheritance__ - achieved using extends base as an example in test classes.
    Where methods declared in _base.java_ called in child classes. As example in _HomePage.java_ we are getting runCapabilities() method declared in _base.java_ without creating any object:
    
    ```
	public class ApiDemo_tc_1 extends base{
	public static Logger log = LogManager.getLogger(ApiDemo_tc_1.class.getName());

	@Test(groups={"Smoke"}, dataProvider="ApiDemoTest_InputData1", dataProviderClass=TestData.class)
	public void dependenciesPageValidation(String input) throws IOException, NoSuchFieldException, InterruptedException 
	{
		AndroidDriver<AndroidElement> driver = runCapabilities("ApiDemosApp", false);
    ......
	}}
    ```