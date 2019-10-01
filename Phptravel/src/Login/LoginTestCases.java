package Login;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Login.LoginPageElements;

	public class LoginTestCases {
		LoginPageElements locator;
		WebDriver driver;

	@BeforeTest
	public void LoadPage(){
		System.setProperty("webdriver.chrome.driver","C:\\Program Files\\Selenium\\chromedriver.exe");
	    driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.phptravels.net/");	
	}
	
	@Test(priority = 0)
	public void VerifyElementsOnThePage(){
	 	locator=new LoginPageElements(driver);
	 	locator.LogInPage();
	 	locator.VerifyElements();	
	}
	
	@Test(priority=2)
	public void LogInWithoutEmail() throws InterruptedException{
		locator=new LoginPageElements(driver);
		locator.LogIn("", "petar123");
		Thread.sleep(2000);
		String actualErrorMessage;
        actualErrorMessage = locator.errorMessage.getText();
        String expectedErrorMessage;
        expectedErrorMessage = "Invalid Email or Password";
        AssertJUnit.assertEquals(actualErrorMessage, expectedErrorMessage);
	}
	
	@Test (priority=2)
	public void LogInWithoutPassword() throws InterruptedException {
		locator=new LoginPageElements(driver);
		locator.LogIn("petarp@gmail.com", "");
		Thread.sleep(2000);
		String actualErrorMessage=locator.errorMessage.getText();
		String expectedErrorMessage="Invalid Email or Password";
		AssertJUnit.assertEquals(actualErrorMessage, expectedErrorMessage);	
	}
	
	@Test(priority=3) 
	public void LogInWithWrongEmail() throws InterruptedException{
		locator=new LoginPageElements(driver);
		locator.LogIn("petarp@", "petar123");
		Thread.sleep(2000);
		String actualErrorMessage=locator.errorMessage.getText();
		String expectedErrorMessage="Invalid Email or Password";
		AssertJUnit.assertEquals(actualErrorMessage, expectedErrorMessage);	
	}
	
	@Test (priority=4)
	public void SuccessfullyLogIn() throws InterruptedException{
		locator=new LoginPageElements(driver);
		locator.LogIn("petarpb@gmail.com", "petar123");
		Thread.sleep(2000);
		String actualUrl ="https://www.phptravels.net/account/";
		String expectedUrl = driver.getCurrentUrl();
		AssertJUnit.assertEquals(actualUrl, expectedUrl);
		locator.LogOut();
	}
	
	@AfterClass
		public void quit() {
		driver.close();
	}
}
