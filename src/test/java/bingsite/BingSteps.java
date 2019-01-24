package bingsite;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BingSteps {
	
	
	WebElement element;
	WebDriverWait wait;
	ExtentTest test;
	WebDriver driver;
	LandingPage landingPage;
	String searchQuery;
		

	@Before
	public void setup() {
		System.setProperty("webdriver.gecko.driver", Constant.FIREFOXDRIVERPATH + Constant.FIREFOXDRIVER);
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		test = TestRunner.report.startTest("Test");
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	
	@Given("^I go to \"([^\"]*)\" website$")
	public void i_go_to_website(String arg1) {
		driver.get(arg1);
		landingPage = PageFactory.initElements(driver, LandingPage.class);
		test.log(LogStatus.INFO, "Landing page loaded");
	}

	@When("^I search for \"([^\"]*)\"$")
	public void i_search_for(String arg1) {
		searchQuery = arg1.replace(' ', '+');
	    landingPage.sendSearch(arg1);
	    landingPage.submit();
	    test.log(LogStatus.INFO, arg1 + " sent as query");
	}

	@Then("^I am taken to a list of data for that search$")
	public void i_am_taken_to_a_list_of_data_for_that_search() {
		if (driver.getCurrentUrl().contains(searchQuery)){
			test.log(LogStatus.PASS, "URL contains " + searchQuery);
		} else {
			test.log(LogStatus.FAIL, "URL doesn't contain" + searchQuery);
		}
		System.out.println(searchQuery);
		assertTrue(driver.getCurrentUrl().contains(searchQuery));
		
	}


}
