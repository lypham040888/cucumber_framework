package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "Email")
	WebElement txtEmailAddress;

	@FindBy(id = "Password")
	WebElement txtPassword;

	@FindBy(xpath = "//button[normalize-space()='Log in']")
	WebElement btnLogin;

	public void setEmail(String email) {
		txtEmailAddress.clear();
		txtEmailAddress.sendKeys(email);
	}

	public void setPassword(String pwd) {
		txtPassword.clear();
		txtPassword.sendKeys(pwd);
	}

	public void clickLogin() {
		btnLogin.click();
	}

}
