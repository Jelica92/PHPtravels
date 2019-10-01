package Register;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Register.RegisterPageElements;

public class RegisterTestCases {
	RegisterPageElements locator;
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
	
	@Test (priority = 0)
	public void VerifyElementsOnRegisterPage(){
		locator = new RegisterPageElements(driver);
		locator.RegisterPage();
		locator.VerifyElementsOnRegPage();
	}
	
	@Test (priority = 1)
	public void SignUpWithoutFirstName() throws InterruptedException{
		locator = new RegisterPageElements(driver);
		Thread.sleep(2000);
		locator.Input("FNAME", "");
		locator.Input("LNAME", "Petrovic");
		locator.Input("PHONE", "066054992");
		locator.Input("EMAIL", "petarpb@gmail.com");
		locator.Input("PASS", "petar123");
		locator.Input("CONFIRMPASS", "petar123");
		locator.SignUpButton();	
		Thread.sleep(2000);
		String actualErrorMessage=locator.erorSignUp.getText();
		String expectedErrorMessage="The First name field is required.";
		AssertJUnit.assertEquals(actualErrorMessage, expectedErrorMessage);	
		
	}
	
	@Test (priority = 2)
	public void SignUpWithoutLastName() throws InterruptedException{
		locator = new RegisterPageElements(driver);
		driver.navigate().refresh();
		Thread.sleep(1000);
		locator.Input("FNAME", "Petar");
		locator.Input("LNAME", "");
		locator.Input("PHONE", "066054992");
		locator.Input("EMAIL", "petarpb@gmail.com");
		locator.Input("PASS", "petar123");
		locator.Input("CONFIRMPASS", "petar123");
		locator.SignUpButton();	
		Thread.sleep(1000);
		String actualErrorMessage=locator.erorSignUp.getText();
		String expectedErrorMessage="The Last Name field is required.";
		AssertJUnit.assertEquals(actualErrorMessage, expectedErrorMessage);	
	}
	
	@Test (priority = 3)
	public void SignUpWithoutEmail() throws InterruptedException {
		locator = new RegisterPageElements(driver);
		driver.navigate().refresh();
		locator.Input("FNAME", "Petar");
		locator.Input("LNAME", "Petrovic");
		locator.Input("PHONE", "066054992");
		locator.Input("EMAIL", "");
		locator.Input("PASS", "petar123");
		locator.Input("CONFIRMPASS", "petar123");
		locator.SignUpButton();	
		Thread.sleep(1000);
		String actualErrorMessage=locator.erorSignUp.getText();
		String expectedErrorMessage="The Email field is required.";
		AssertJUnit.assertEquals(actualErrorMessage, expectedErrorMessage);
	}
	
	@Test (priority=4) 
	public void SignUpWithWrongEmail() throws InterruptedException{
		locator = new RegisterPageElements(driver);
		driver.navigate().refresh();
		Thread.sleep(1000);
		locator.Input("FNAME", "Petar");
		locator.Input("LNAME", "Petrovic");
		locator.Input("PHONE", "066054992");
		locator.Input("EMAIL", "petarp");
		locator.Input("PASS", "petar123");
		locator.Input("CONFIRMPASS", "petar123");
		locator.SignUpButton();	
		Thread.sleep(1000);
		String actualErrorMessage=locator.erorSignUp.getText();
		String expectedErrorMessage="The Email field must contain a valid email address.";
		AssertJUnit.assertEquals(actualErrorMessage, expectedErrorMessage);	
	}
	
	@Test (priority=4) 
	public void SignUpWithoutPassandConfirmPass() throws InterruptedException{
		locator = new RegisterPageElements(driver);
		driver.navigate().refresh();
		Thread.sleep(1000);
		locator.Input("FNAME", "Petar");
		locator.Input("LNAME", "Petrovic");
		locator.Input("PHONE", "066054992");
		locator.Input("EMAIL", "petarpb@gmail.com");
		locator.Input("PASS", "");
		locator.Input("CONFIRMPASS", "");
		locator.SignUpButton();	
		Thread.sleep(1000);
		String actualErrorMessage=locator.erorSignUp.getText();
		String expectedErrorMessage = "The Password field is required." +"\n" + "The Password field is required.";
		AssertJUnit.assertEquals(actualErrorMessage, expectedErrorMessage);	
	}
	
	@Test (priority=4)
	public void SignUpWithWrongRepeatedPassword() throws InterruptedException{
		locator = new RegisterPageElements(driver);
		driver.navigate().refresh();
		Thread.sleep(1000);
		locator.Input("FNAME", "Petar");
		locator.Input("LNAME", "Petrovic");
		locator.Input("PHONE", "066054992");
		locator.Input("EMAIL", "petarpb@gmail.com");
		locator.Input("PASS", "petar123");
		locator.Input("CONFIRMPASS", "petar12");
		locator.SignUpButton();
		Thread.sleep(1000);
		String actualErrorMessage=locator.erorSignUp.getText();
		String expectedErrorMessage="Password not matching with confirm password.";
		AssertJUnit.assertEquals(actualErrorMessage, expectedErrorMessage);	
	}
	
	@Test (priority=4)
	public void SignUpWithoutAnyInformation() throws InterruptedException{
		locator = new RegisterPageElements(driver);
		driver.navigate().refresh();
		Thread.sleep(1000);
		locator.Input("FNAME", "");
		locator.Input("LNAME", "");
		locator.Input("PHONE", "");
		locator.Input("EMAIL", "");
		locator.Input("PASS", "");
		locator.Input("CONFIRMPASS", "");
		locator.SignUpButton();	
		Thread.sleep(2000);
		String actualErrorMessage=locator.erorSignUp.getText();
		String expectedErrorMessage="The Email field is required."+"\n"+"The Password field is required."+"\n"+"The Password field is required."+"\n"+"The First name field is required." +"\n"+"The Last Name field is required.";
		AssertJUnit.assertEquals(actualErrorMessage, expectedErrorMessage);	
	}
	
	@Test (priority=5)
	public void SingUpSuccessfully() throws InterruptedException  {
		locator = new RegisterPageElements(driver);
		driver.navigate().refresh();
		Thread.sleep(1000);
		locator.Input("FNAME", "Petar");
		locator.Input("LNAME", "Petrovic");
		locator.Input("PHONE", "066054993");
		locator.InsertRandomEmail();
		locator.Input("PASS", "petar123");
		locator.Input("CONFIRMPASS", "petar123");
		locator.SignUpButton();
		Thread.sleep(2000);
		locator.LogOut();
		String actualUrl ="https://www.phptravels.net/login";
		String expectedUrl = driver.getCurrentUrl();
		AssertJUnit.assertEquals(actualUrl, expectedUrl);
	}
 
	@Test (priority=5)
	public void SingUpSuccessfullyWithoutPhone() throws InterruptedException  {
		locator = new RegisterPageElements(driver);
		locator.RegisterPage();
		locator.Input("FNAME", "Petar");
		locator.Input("LNAME", "Petrovic");
		locator.Input("PHONE", "");
		locator.InsertRandomEmail();
		locator.Input("PASS", "petar123");
		locator.Input("CONFIRMPASS", "petar123");
		locator.SignUpButton();
		Thread.sleep(2000);
		locator.LogOut();
		String actualUrl ="https://www.phptravels.net/login";
		String expectedUrl = driver.getCurrentUrl();
		AssertJUnit.assertEquals(actualUrl, expectedUrl);
	}
	
	@AfterClass
	public void quit() {

		driver.close();
	}
}
