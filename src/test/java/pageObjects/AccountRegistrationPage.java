package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	// Elements
	@FindBy(id = "gender-male")
	WebElement rdMale;
	@FindBy(id = "gender-female")
	WebElement rdFemale;

	@FindBy(name = "FirstName")
	WebElement txtFirstname;

	@FindBy(name = "LastName")
	WebElement txtLasttname;

	@FindBy(name = "DateOfBirthDay")
	WebElement txtDay;

	@FindBy(name = "DateOfBirthMonth")
	WebElement txtMonth;

	@FindBy(name = "DateOfBirthYear")
	WebElement txtYear;

	@FindBy(name = "Email")
	WebElement txtEmail;

	@FindBy(name = "Company")
	WebElement txtCompany;

	@FindBy(name = "Password")
	WebElement txtPassword;

	@FindBy(name = "ConfirmPassword")
	WebElement txtConfirmPassword;

	@FindBy(name = "agree")
	WebElement chkdPolicy;

	@FindBy(xpath = "//a[normalize-space()='Continue']")
	WebElement btnContinue;

	@FindBy(id = "register-button")
	WebElement btnRegister;

	@FindBy(xpath = "//div[@class='result']")
	WebElement msgResult;

	public void setFirstName(String fname) {
		txtFirstname.clear();
		txtFirstname.sendKeys(fname);

	}

	public void setLastName(String lname) {
		txtLasttname.clear();
		txtLasttname.sendKeys(lname);

	}

	public void setEmail(String email) {
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}

	public void setPassword(String pwd) {
		txtPassword.clear();
		txtPassword.sendKeys(pwd);
	}

	public void setConfirmPassword(String pwd) {
		txtConfirmPassword.clear();
		txtConfirmPassword.sendKeys(pwd);
	}

	public void setFemale() {
		rdFemale.click();
	}

	public void setMale() {
		rdMale.click();
	}

	public void setDay(String day) {
		Select selectDay = new Select(txtDay);
		selectDay.selectByValue(day);
	}

	public void setMonth(String month) {
		Select selectDay = new Select(txtMonth);
		selectDay.selectByValue(month);
	}

	public void setYear(String year) {
		Select selectDay = new Select(txtYear);
		selectDay.selectByValue(year);
	}

	public void setCompany(String name) {
		txtCompany.clear();
		txtCompany.sendKeys(name);
	}

	public void setPolicy() {
		chkdPolicy.click();

	}

	public void clickContinue() {
		btnContinue.click();

	}

	public void clickRegister() {
		btnRegister.click();

	}

	public String getRegistratorResult() {
		try {
			return (msgResult.getText());
		} catch (Exception e) {
			return (e.getMessage());

		}

	}

}