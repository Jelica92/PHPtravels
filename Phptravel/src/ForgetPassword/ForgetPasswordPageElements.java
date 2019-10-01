package ForgetPassword;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

	public class ForgetPasswordPageElements {
		
		 @FindBy (className="col-md-12") List<WebElement> forgetPass;
		 @FindBy (css =".form-control") List<WebElement> resetEmail;
		 @FindBy(className="input-group-btn") WebElement resetButton;
		 @FindBy(className="resultreset") WebElement resetEror;
		 @FindBy(className="close") List<WebElement> closeButton;
	 
		 WebDriver driver;
	 
	 public ForgetPasswordPageElements(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}

	 public void ForgetPassword(String email){
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView()", forgetPass.get(1));
		 try {
			 Thread.sleep(1000);
		 } catch (InterruptedException e) {
			 e.printStackTrace();
		 }
		 forgetPass.get(1).click();
		 resetEmail.get(2).sendKeys(email);
		 resetButton.click();
	}

}
