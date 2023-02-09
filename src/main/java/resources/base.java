package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class base {
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;

	public AppiumDriverLocalService startServer() {
		boolean flag = checkIfServerIsRunnning(4723);
		if (!flag) {
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;
	}

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public static void startEmulator() throws IOException, InterruptedException {
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\test\\resources\\startEmulator.bat");
		Thread.sleep(6000);
	}
	
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
	
	public static AndroidDriver<AndroidElement> capabilities(String appName) throws IOException, InterruptedException {

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);

		File appDir = new File("src\\test\\resources\\apks");

		File app = new File(appDir, (String) prop.get(appName));

		DesiredCapabilities cap = new DesiredCapabilities();
		
		String runMode = (String) prop.get("runMode");
		
		String device;
		
		if (runMode.contains("maven_command")) {
			device = System.getProperty("deviceName");
		}
		else
		{
			device = (String) prop.get("device");
		}

		if (device.contains("emulator")) {
			startEmulator();
		}
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);

		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");// new step

		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);

		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

		cap.setCapability("chromeOptions", ImmutableMap.of("w3c", false));

		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;

	}

	public static AndroidDriver<AndroidElement> cloudcapabilities(String appName) throws IOException, InterruptedException {

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\global.properties");
		Properties prop = new Properties();
		
		prop.load(fis);

		DesiredCapabilities cap = new DesiredCapabilities();
		
		String browserstackUser = (String) prop.get("browserstackUser");
		
		String browserstackKey = (String) prop.get("browserstackKey");
		
		String browserstackApp1Url = (String) prop.get("browserstackApp1Url");
		
		String browserstackApp2Url = (String) prop.get("browserstackApp2Url");
		
		String browserstackDevice = (String) prop.get("browserstackDevice");
		
		String browserstackAppOSVersion = (String) prop.get("browserstackAppOSVersion");
		
		String browserstackHubUrl = (String) prop.get("browserstackHubUrl");
		
    	cap.setCapability("browserstack.user", browserstackUser);
    	
    	cap.setCapability("browserstack.key", browserstackKey);

		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");// new step

		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);

		cap.setCapability("chromeOptions", ImmutableMap.of("w3c", false));
		
		if(appName.equalsIgnoreCase("GeneralStoreApp"))
		{
	    	cap.setCapability("app", browserstackApp1Url);
		}
		else
		{
			cap.setCapability("app", browserstackApp2Url);
		}
		
    	cap.setCapability("device", browserstackDevice);
    	
    	cap.setCapability("os_version", browserstackAppOSVersion);
    	
		driver = new AndroidDriver<>(new URL(browserstackHubUrl), cap);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;

	}

	public String getScreenshot(String s) throws IOException {
		Date date = new Date();
		File scrfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\screenshots\\" 
				+ s + "_" + date.toString().replace(":", "_").replace(" ", "_") + ".png";
		FileUtils.copyFile(scrfile, new File(destinationFile));
		return destinationFile;
	}
}
