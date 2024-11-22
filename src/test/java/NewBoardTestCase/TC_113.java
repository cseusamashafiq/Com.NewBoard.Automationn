package NewBoardTestCase;

import org.testng.annotations.Test;

import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import NewBoard.Automationn.LambdaTestDriver;
import NewBoardPage.NewBoardPages;

public class TC_113 extends LambdaTestDriver {

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
	public void CreateBoard() throws Exception {

		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("130");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "cseusamashafiq");
		ltOptions.put("accessKey", "8W4s0ngSSHoiazfVVFXqltH4Orrqvee5SFyWEl4KAQVdess7jQ");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("build", "TC_113_BoardCreate");
		ltOptions.put("project", "TC_113_BoardCreate");
		ltOptions.put("name", "TC_113_BoardCreate");
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

			// Hover over the "+" button to reveal options if required
			WebElement plusButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Add New']")));
			Actions actions = new Actions(driver);
			actions.moveToElement(plusButton).click().perform();

			// Click on the "New Board" option   //button//p[text()="New Board"]
			WebElement newBoardOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button//p[text()=\"New Board\"]")));
			newBoardOption.click();

			// Enter the board name in the "Add New Board" modal
			WebElement boardNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[1]//div[3]//input[@type='text']")));
			boardNameInput.sendKeys("My New Board");

			// Click the "Add Board" button to create the board//button[2][text()="Add Board"]
			WebElement addBoardButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[2][text()=\"Add Board\"]")));
			addBoardButton.click();

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
