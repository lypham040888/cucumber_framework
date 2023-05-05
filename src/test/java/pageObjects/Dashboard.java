package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Dashboard extends BasePage {
	public Dashboard(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h1[normalize-space()='Dashboard']") // Dashboard Page heading
	WebElement msgHeading;

	@FindBy(xpath = "//a[normalize-space()='Logout']")
	WebElement lnkLogout;

	@FindBy(xpath = "//a[@href='#']//p[contains(text(),'Customers')]")
	WebElement lnkCustomer;
	
	public boolean isDashboardPage()
	{
		return msgHeading.isDisplayed();
	}

	public void clickCustomerOption() // Dashboard Page heading display status
	{
		lnkCustomer.click();
	}

	public void clickLogout() {
		lnkLogout.click();

	}
}
