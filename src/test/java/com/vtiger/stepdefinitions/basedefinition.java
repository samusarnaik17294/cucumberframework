package com.vtiger.stepdefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vtiger.pages.HomePage;
import com.vtiger.pages.LeadPage;
import com.vtiger.pages.LoginPage;

public class basedefinition {
	
	public static WebDriver driver;
	public static LoginPage lp;
	public static HomePage hp;
	public static LeadPage ldp;
	public static Properties prop;
	public static Map<String,Map<String,String>> dt;
	public static String TCName;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	
	public void readproperties() throws IOException, FilloException
	{
		//readexcel();
		readjson();
		
		System.out.println(dt);
		//System.exit(0);
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		prop.load(fis);
	}
	
	public void launchApp() throws IOException, FilloException
	{
		readproperties();
		if(prop.getProperty("browser").equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(prop.getProperty("browser").equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(prop.getProperty("browser").equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		driver.get(prop.getProperty("AppUrl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public void readexcel() throws FilloException
	{
		Fillo fillo=new Fillo();
		Connection connection=fillo.getConnection(System.getProperty("user.dir")+"/src/test/resources/data/testdata.xlsx");
		String strQuery="Select * from Sheet1";
		Recordset recordset=connection.executeQuery(strQuery);
		dt = new HashMap<String,Map<String,String>>();
		List<String> colms = recordset.getFieldNames();
		while(recordset.next()){
		//System.out.println(recordset.getField("Details"));
			Map<String,String> rows = new HashMap<String,String>();
			for(int i=0;i<colms.size();i++)
			{
				rows.put(colms.get(i), recordset.getField(colms.get(i)));
			}
			
			dt.put(recordset.getField(colms.get(0)), rows);
		}
		 
		recordset.close();
		connection.close();
	}
	
	
	public void readjson() throws StreamReadException, DatabindException, IOException
	{
		 String jsonFilePath = System.getProperty("user.dir")+"/src/test/resources/data/testdata.json"; // Replace with your JSON file path

	      
	            File jsonFile = new File(jsonFilePath);
	            ObjectMapper mapper = new ObjectMapper();

	            // Read JSON file into a Map<String, Map<String, String>>
	             dt = mapper.readValue(jsonFile,
	                    new TypeReference<Map<String, Map<String, String>>>() {});
	}
	
	public void createExtentReport()
	{
		Date d = new Date();
		DateFormat ft = new SimpleDateFormat("ddMMyyyyhhmmss");
		String fileName = ft.format(d);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/src/test/java/com/vtiger/reports/ExtentReport"+fileName+".html");
    	// Create an object of Extent Reports
		extent = new ExtentReports();  
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Automation Test Hub");
		    	extent.setSystemInfo("Environment", "Test");
		extent.setSystemInfo("User Name", "Rajesh U");
		htmlReporter.config().setDocumentTitle("vTiger Regression Report"); 
		            // Name of the report
		htmlReporter.config().setReportName("Name of the Report Comes here "); 
		            // Dark Theme
		htmlReporter.config().setTheme(Theme.STANDARD); 
		
	}

}
