package stepDefinitions;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AccountRegistrationPage;
import pageObjects.Dashboard;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataReader;

public class steps {
	WebDriver driver;
	HomePage hp;
	LoginPage lp;
	Dashboard dashboard;
	AccountRegistrationPage accRegis;

	List<HashMap<String, String>> datamap; // Data driven

	public Logger logger; // for logging
	public ResourceBundle rb; // for reading properties file
	public String br; // to store browser name
	public String url; // url link
	public String admin_url; // admin url link

	@Before
	public void setup() // Junit hook - executes once before starting
	{
		// for logging
		logger = LogManager.getLogger(this.getClass());
		// Reading config.properties (for browser)
		rb = ResourceBundle.getBundle("config");
		br = rb.getString("browser");
		url = rb.getString("url");
		admin_url = rb.getString("admin_url");

		dashboard = new Dashboard(driver);

	}

	@After
	public void tearDown(Scenario scenario) {
		System.out.println("Scenario status ======>" + scenario.getStatus());
		if (scenario.isFailed()) {
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());
		}
		driver.quit();
	}

	@Given("User Launch browser")
	public void user_launch_browser() {
		if (br.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (br.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (br.equals("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	@Given("User navigate to url {string}")
	public void user_navigate_to_url(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}

	@Given("User navigate to admin url")
	public void user_navigate_to_admin_url() {
		driver.get(admin_url);
	}

	@And("User navigate to home page")
	public void user_navigate_to_home_page() {
		driver.get(url);
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String pwd) {
		lp = new LoginPage(driver);

		lp.setEmail(email);
		logger.info("Provided Email ");
		lp.setPassword(pwd);
		logger.info("Provided Password ");
	}

	@When("Click login button")
	public void click_on_login_button() {
		lp.clickLogin();
		logger.info("Clicked on Login button");
	}

	@Then("The user redirect to Dashboard page")
	public void user_navigates_to_my_account_page() {
		dashboard = new Dashboard(driver);
		boolean targetpage = dashboard.isDashboardPage();

		if (targetpage) {
			logger.info("Login Success ");
			Assert.assertTrue(true);
		} else {
			logger.error("Login Failed ");
			Assert.assertTrue(false);
		}

	}

	// ******* Data Driven test method **************
	@When("User enters Email and Password as {string}")
	public void user_enters_email_as_and_password_as(String rows) {
		lp = new LoginPage(driver);

		datamap = DataReader.data(System.getProperty("user.dir") + "\\testData\\Opencart_LoginData.xlsx", "Sheet1");

		int index = Integer.parseInt(rows) - 1;
		String email = datamap.get(index).get("username");
		String pwd = datamap.get(index).get("password");
		// String exp_res = datamap.get(index).get("res");
		lp = new LoginPage(driver);
		lp.setEmail(email);
		logger.info("Provided Email ");
		lp.setPassword(pwd);
		logger.info("Provided Password ");

	}

	// ******* Account Registration Methods **************
	@And("Select Register link")
	public void select_registor_link() {
		hp = new HomePage(driver);
		hp.clickRegister();

	}

	@When("User enters mandatory fields as {string}")
	public void user_enters_mandatory_fields_as(String rows) {
		datamap = DataReader.data(System.getProperty("user.dir") + "\\testData\\AccountRegistrationList.xlsx",
				"Sheet1");

		int index = Integer.parseInt(rows) - 1;
		String email = datamap.get(index).get("email");
		String pwd = datamap.get(index).get("password");
		String confirmpwd = datamap.get(index).get("confirmpassword");
		String firstname = datamap.get(index).get("firstname");
		String lastname = datamap.get(index).get("lastname");
		String sex = datamap.get(index).get("sex");
		String day = datamap.get(index).get("day");
		String month = datamap.get(index).get("month");
		String year = datamap.get(index).get("year");
		String companyname = datamap.get(index).get("companyname");
		accRegis = new AccountRegistrationPage(driver);
		if (sex.endsWith("Female"))
			accRegis.setFemale();
		else
			accRegis.setMale();

		accRegis.setFirstName(firstname);
		accRegis.setLastName(lastname);
		accRegis.setDay(day);
		accRegis.setMonth(month);
		accRegis.setYear(year);
		accRegis.setEmail(email);
		if (companyname != null && !companyname.isEmpty())
			accRegis.setCompany(companyname);

		accRegis.setPassword(pwd);
		accRegis.setConfirmPassword(confirmpwd);
	}

	@When("Click register button")
	public void click_register_button() {
		accRegis.clickRegister();
		Assert.assertEquals(accRegis.getRegistratorResult(), "Your registration completed");

	}

	@Then("The user redirect to Registion result page")
	public void the_user_redirect_to_registion_result_page() {
		accRegis.clickContinue();

	}

}
