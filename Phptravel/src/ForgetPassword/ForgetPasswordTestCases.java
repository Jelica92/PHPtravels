package ForgetPassword;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Login.LoginPageElements;

public class ForgetPasswordTestCases {
	
	ForgetPasswordPageElements locator;
	LoginPageElements login;
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
		
	@Test
	public void ResetWhithoutEmail() throws InterruptedException{
		locator = new ForgetPasswordPageElements(driver);
		login= new LoginPageElements(driver);
		login.LogInPage();
		locator.ForgetPassword("");
		Thread.sleep(1000);
		String actualErrorMessage=locator.resetEror.getText();
		String expectedErrorMessage="Email Not Found";
		AssertJUnit.assertEquals(actualErrorMessage, expectedErrorMessage);
		locator.closeButton.get(2).click();
		login.VerifyElements();
	}
		
	@Test
	public void ResetWithWrongEmail() throws InterruptedException {
		locator = new ForgetPasswordPageElements(driver);
		locator.ForgetPassword("petarpbgmail.com");
		Thread.sleep(1000);
		String actualErrorMessage=locator.resetEror.getText();
		String expectedErrorMessage="Email Not Found";
		AssertJUnit.assertEquals(actualErrorMessage, expectedErrorMessage);
		locator.closeButton.get(2).click();
		login.VerifyElements();
	}
		
	@Test
	public void SuccessfullyForgetPassword() {
		locator = new ForgetPasswordPageElements(driver);
		locator.ForgetPassword("petarpb@gmail.com");
		locator.closeButton.get(2).click();
	}
		
	@AfterClass
	public void quit() {
		driver.close();
	} 
}
