package Register;

import java.util.List;
import java.util.UUID;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.AssertJUnit;

public class RegisterPageElements {
	
	@FindBy(id = "li_myaccount") List<WebElement> allElements;
	@FindBy(css="ul.dropdown-menu .go-text-right") List<WebElement> logelements;
	@FindBy (className="btn btn-default br25 btn-block form-group") WebElement singUp;
	@FindBy (name="firstname") WebElement FirstName;
	@FindBy (name="lastname") WebElement LastName;
	@FindBy (name="phone") WebElement MobilePhone;
	@FindBy (name="email") WebElement SignEmail;
	@FindBy (name="password") WebElement SignPassword;
	@FindBy (name="confirmpassword") WebElement ConfirmPass;
    @FindBy (xpath="//div[@class='form-group']/button[@class='signupbtn btn_full btn btn-action btn-block btn-lg']") WebElement signUpButton;
    @FindBy (className="resultsignup") WebElement erorSignUp;
    @FindBy (xpath="//li[@class='']/a[@class='dropdown-toggle go-text-right']") List<WebElement> account;
     
    WebDriver driver;
    
    public static final String FIRSTNAME = "FNAME";
   	public static final String LASTNAME = "LNAME";
   	public static final String MOBILEPHONE = "PHONE";
   	public static final String EMAIL = "EMAIL";
   	public static final String PASSWORD = "PASS";
   	public static final String CONFIRMPASSWORD = "CONFIRMPASS";
   	
   	public RegisterPageElements (WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	 }
   	
   	public void RegisterPage() {
   		
		allElements.get(1).click();
			try {
			Thread.sleep(1000);
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		logelements.get(21).click();
		String actualUrl ="https://www.phptravels.net/register";
		String expectedUrl = driver.getCurrentUrl();
		AssertJUnit.assertEquals(actualUrl, expectedUrl);
	}
   	
   	public void VerifyElementsOnRegPage(){
   		
		boolean actualValue = FirstName.isDisplayed() || LastName.isDisplayed() ||  MobilePhone.isDisplayed() || SignEmail.isDisplayed() || SignPassword.isDisplayed() || ConfirmPass.isDisplayed() || signUpButton.isDisplayed();
		if(actualValue) {
			System.out.println("All elements on Register page are displayed");
		}else 
			System.out.println("Elements are not displayed");
	}

	public void Input(String polje, String text) {
		
		switch(polje) {
			case FIRSTNAME:
				FirstName.sendKeys(text);
				break;
			case LASTNAME:
				LastName.sendKeys(text);
				break;
			case MOBILEPHONE:
				MobilePhone.sendKeys(text);
				break;
			case EMAIL:
				SignEmail.sendKeys(text);
				break;
			case PASSWORD:
				SignPassword.sendKeys(text);
				break;
			case CONFIRMPASSWORD:
				ConfirmPass.sendKeys(text);
		}
	}

   	
   	public void InsertRandomEmail(){
   		
		final String randomEmail = randomEmail();
		SignEmail.sendKeys(randomEmail);
    }

	public static String randomEmail() {
   		return "random-" + UUID.randomUUID().toString() + "@example.com";
	}
	
	public void SignUpButton() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", signUpButton);
	}
	
	public void LogOut(){
		
		account.get(1).click();
		   try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		logelements.get(21).click();
	}

}
