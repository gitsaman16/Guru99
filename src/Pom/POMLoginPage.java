package Pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class POMLoginPage {

	@FindBy(xpath="//input[@name='uid']")
	private WebElement userId;
	
	@FindBy(xpath="//input[@name='password']")
	private WebElement password;
	
	@FindBy(xpath="//input[@name='btnLogin']")
	private WebElement loginButton;
	
	public POMLoginPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	public void loginMethod(String uname,String pwd){
		userId.sendKeys(uname);
		password.sendKeys(pwd);
		loginButton.click();
	}
}
