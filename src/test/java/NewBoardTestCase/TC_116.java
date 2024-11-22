package NewBoardTestCase;

import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import NewBoard.Automationn.LambdaTestDriver;
import NewBoardPage.NewBoardPages;

public class TC_116 extends LambdaTestDriver {

	//private WebDriver driver;
	public WebDriverWait wait;
	private String testCaseName;
	private String testCaseDescription;

	@BeforeTest(alwaysRun = true)
	public void beforeMethod() {
		testCaseName = this.getClass().getName();
		testCaseDescription = "The purpose of this test case is to verify \"" + testCaseName + "\" workflow.";
		System.out.println("Test Case: " + testCaseName);
		System.out.println("Description: " + testCaseDescription);
	}

	@Test
	public void CreateList() throws Exception {

		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("130");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "cseusamashafiq");
		ltOptions.put("accessKey", "8W4s0ngSSHoiazfVVFXqltH4Orrqvee5SFyWEl4KAQVdess7jQ");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("build", "TC_116_AddList");
		ltOptions.put("project", "TC_116_AddList");
		ltOptions.put("name", "TC_116_AddList");
		ltOptions.put("selenium_version", "4.0.0");
		ltOptions.put("w3c", true);
		browserOptions.setCapability("LT:Options", ltOptions);
		driver = new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"), browserOptions);
		driver.get("https://my.newboard.io/");

		// Initialize the Page Object
		Thread.sleep(8000);
		NewBoardPages newBoardPages = new NewBoardPages(); // Pass driver to page class constructor
		TC_03 login = new TC_03();

		// Use the Page Object methods
		newBoardPages.enterEmail("csusamashafiq@gmail.com");
		newBoardPages.clickSubmit();

		// Fetch OTP from email
		String otp = login.fetchOTPFromEmail();
		System.out.println("Fetched OTP: " + otp);
		newBoardPages.enterOTP(otp);

		try {

			// Click on the "Add List" button (adjust the XPath as per your application's structure)
			WebElement addListButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Add List']")));
			addListButton.click();

			// Enter the list name in the "Add New List" modal (adjust the XPath if needed)
			WebElement listNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type list name here...']")));
			listNameInput.sendKeys("My New List");

			// Click the "Add" button to create the list
			WebElement addListSubmitButton = driver.findElement(By.xpath("//button[text()='Add']"));
			addListSubmitButton.click();

		} catch (Exception e) {
			System.err.println("Error during board creation: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@AfterMethod(alwaysRun = true)
	public void afterTest() {
		LambdaTestDriver.BrowserClose();
	}
}
