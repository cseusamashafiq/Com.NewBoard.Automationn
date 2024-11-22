package NewBoardTestCase;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.HashMap;

import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import NewBoard.Automationn.LambdaTestDriver;
import NewBoardPage.NewBoardPages;

public class TC_132  extends LambdaTestDriver {

	public WebDriverWait wait;
	private String testCaseName;
	private String testCaseDescription;

	@BeforeTest(alwaysRun = true)
	public void setup() throws Exception {
		testCaseName = this.getClass().getName();
		testCaseDescription = "The purpose of this test case is to verify \"" + testCaseName + "\" workflow.";
		System.out.println("Test Case: " + testCaseName);
		System.out.println("Description: " + testCaseDescription);
	}

	@Test
	public void uploadHTMLFile() throws Exception {

		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("130");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "cseusamashafiq");
		ltOptions.put("accessKey", "8W4s0ngSSHoiazfVVFXqltH4Orrqvee5SFyWEl4KAQVdess7jQ");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("build", "TC_132_UploadHTML");
		ltOptions.put("project", "TC_132_UploadHTML");
		ltOptions.put("name", "TC_132_UploadHTML");
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

		// Step 1: Click on "HTML" button
		WebElement htmlButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Upload HTML File']"))); // Adjust XPath as per your DOM
		htmlButton.click();

		// Step 2: Use Robot class to interact with system file dialog
		Thread.sleep(2000); // Wait for system file dialog to appear
		uploadFileWithRobot(""); // Adjust file path
	}

	private void uploadFileWithRobot(String filePath) throws AWTException {
		Robot robot = new Robot();

		// Type the file path into the system dialog
		for (char c : filePath.toCharArray()) {
			if (Character.isUpperCase(c)) {
				robot.keyPress(KeyEvent.VK_SHIFT); // Press SHIFT for uppercase letters
			}
			robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(c));
			robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(c));
			if (Character.isUpperCase(c)) {
				robot.keyRelease(KeyEvent.VK_SHIFT); // Release SHIFT after uppercase letters
			}
		}

		// Press "Enter" to confirm the file selection
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}


	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		BrowserClose(); // Close the browser session
	}
}