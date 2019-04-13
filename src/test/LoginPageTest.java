package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.AutoConstant;
import Pom.POMLoginPage;
import Util.TestDataUtil;

public class LoginPageTest {

	WebDriver driver;
	
	@BeforeMethod
	public void setup(){
		System.setProperty(AutoConstant.CHROME_KEY, AutoConstant.CHROME_DRIVER_PATH);
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.get(AutoConstant.URL);	
	}
	
	@DataProvider
	public Iterator<Object[]> getTestData(){
		ArrayList<Object[]> data=TestDataUtil.getTestData();
		return data.iterator();
	}
	
	@Test(dataProvider="getTestData")
	public  void loginMethod(String userId,String password) throws IOException {
		
			POMLoginPage loginPage=new POMLoginPage(driver);
			loginPage.loginMethod(userId, password);
			
			 /* Determine Pass Fail Status of the Script
	         * If login credentials are correct,  Alert(Pop up) is NOT present. An Exception is thrown and code in catch block is executed	
	         * If login credentials are invalid, Alert is present. Code in try block is executed 	    
	         */
			try{
				Alert alt=driver.switchTo().alert();
				System.out.println(alt.getText());
				Reporter.log(alt.getText());
				Assert.assertEquals(alt.getText(), "User or Password is not valid","Test passed");
				alt.accept();
			}catch(NoAlertPresentException e){
				String title=driver.getTitle();
				Reporter.log(title);
				Assert.assertEquals(title, "Guru99 Bank Manager HomePage","Test passed");
			}		
			
			// Code to take Screenshot
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			// Code to save screenshot at desired location
			FileUtils.copyFile(scrFile, new File("/Users/RoopaPrabhagaran/Documents/Selenium_training/screenshot.png"));
						
	}
	
	@Test
	public void test2(){
		Assert.assertEquals(true, true);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
		
}
