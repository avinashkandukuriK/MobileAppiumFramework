package resources;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports createInstance() {
		String fileName = getReportName();
		String directory = System.getProperty("user.dir") + "\\reports\\htmlreports\\";
		new File(directory).mkdirs();
		String path = directory + fileName;
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(path);
		
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation reports");
		htmlReporter.config().setReportName("Automation Test Results");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		
		extent = new ExtentReports();
		extent.setSystemInfo("Project", "Appium Framework");
		extent.setSystemInfo("OS", "Android");
		extent.setSystemInfo("Engineer", "Andrey Yudin");
		extent.attachReporter(htmlReporter);
		
		return extent;
	}
	
	public static String getReportName() {
		Date d = new Date();
		String fileName = "AutomationReport_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
		return fileName;
	}

}
