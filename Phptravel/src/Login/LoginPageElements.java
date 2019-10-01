package Login;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.AssertJUnit;

	public class LoginPageElements {
		 @FindBy(id = "li_myaccount") List<WebElement> allElements;
		 @FindBy(css="ul.dropdown-menu .go-text-right") List<WebElement> logelements;
		 @FindBy (name="username") WebElement email;
		 @FindBy (name="password") WebElement password;
		 @FindBy (css="button.btn.btn-action.btn-lg.btn-block.loginbtn") WebElement login;
		 @FindBy (xpath="//div[@class='resultlogin']/div[@class='alert alert-danger']") WebElement errorMessage;
		 @FindBy (css="a.dropdown-toggle.go-text-right") List<WebElement> account;
		 @FindBy(css="a.go-text-right") List<WebElement> logout;
		
		 WebDriver driver;
	 
	 public LoginPageElements (WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	 }
	 
	 public void LogInPage(){
		allElements.get(1).click();
		logelements.get(20).click();
		String actualUrl ="https://www.phptravels.net/login";
		String expectedUrl = driver.getCurrentUrl();
		AssertJUnit.assertEquals(actualUrl, expectedUrl);
	}
	 
	 public void VerifyElements(){
		 boolean actualValue = email.isDisplayed() || password.isDisplayed() || login.isDisplayed();
		 if(actualValue) {
			System.out.println("All elements on Login page are displayed");
		 }else 
			System.out.println("Elements are not displayed");
	}
	 
	 public void LogIn( String eml,String pass) {
		 email.clear();
		 password.clear();
		 email.sendKeys(eml);
		 password.sendKeys(pass);
		 try {
			 Thread.sleep(1000);
		 } catch (InterruptedException e) {
			 e.printStackTrace();
		 }
		driver.manage().deleteAllCookies();
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].scrollIntoView()", login);
		 login.click();

		 String actualUrl ="https://www.phptravels.net/login";
		String expectedUrl = driver.getCurrentUrl();
		AssertJUnit.assertEquals(actualUrl, expectedUrl);		
	}
	 
	 public void LogOut(){
		 account.get(3).click();
		 	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		 logout.get(25).click();
	}

}
