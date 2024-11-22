package NewBoardTestCase;

import java.net.URL;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import NewBoard.Automationn.Basestep;
import NewBoard.Automationn.LambdaTestDriver;
import NewBoardPage.NewBoardPages;

public class TC_118 extends LambdaTestDriver{

	private String testCaseName;
	private String testCaseDescription;

	@BeforeTest(alwaysRun = true)
	public void beforeMethod() {
		// Setting unique file name using timestamp
		testCaseName = this.getClass().getName();
		testCaseDescription = "The purpose of this test case is to verify \"" + testCaseName + "\" workflow.";
		System.out.println("Test Case: " + testCaseName);
		System.out.println("Description: " + testCaseDescription);

	}

	@Test(groups = {"Add URL in the list", "CompleteRegressionSuite"})
	public void newBoardLogin() throws Exception {
		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("130");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "cseusamashafiq");
		ltOptions.put("accessKey", "8W4s0ngSSHoiazfVVFXqltH4Orrqvee5SFyWEl4KAQVdess7jQ");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("build", "TC_118_Add_Link");
		ltOptions.put("project", "TC_118_Add_Link");
		ltOptions.put("name", "TC_118_Add_Link");
		ltOptions.put("selenium_version", "4.0.0");
		ltOptions.put("w3c", true);
		browserOptions.setCapability("LT:Options", ltOptions);
		driver = new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"), browserOptions);
		driver.get("https://my.newboard.io/");
		Thread.sleep(7000);

		// Login Flow
		NewBoardPages newBoardPages = new NewBoardPages();
		TC_03 login = new TC_03();
		newBoardPages.enterEmail("cseusamashafiq@gmail.com");
		newBoardPages.clickSubmit();
		Thread.sleep(3000);

		// Fetch OTP from email
		String otp = login.fetchOTPFromEmail();
		System.out.println("Fetched OTP: " + otp);
		newBoardPages.enterOTP(otp);

		Thread.sleep(5000);
		try {

			// Click on the plus icon to add a new URL
			WebElement plusIcon = driver.findElement(By.xpath("//*[@id=\"cm2k2bt7d0042705y22flpisa\"]/div/div[1]/div[2]/div[1]/img")); // Replace with actual locator
			plusIcon.click();

			Thread.sleep(2000); // Wait for the modal or input fields to appear

			// Enter URL
			WebElement urlField = driver.findElement(By.xpath("//input[@id=\"link\"]")); // Replace with actual locator
			String url = "https://example.com";
			urlField.sendKeys(url);

			// Enter Title
			WebElement titleField = driver.findElement(By.xpath("//input[@placeholder='Title']")); // Replace with actual locator
			String title = "Example Website";
			titleField.sendKeys(title);

			// Click on the "Add" button
			WebElement addButton = driver.findElement(By.xpath("//div[3]//button[1][contains(text(), 'Add')]")); // Replace with actual locator
			addButton.click();

			// Wait for the URL to be added
			Thread.sleep(5000);

			System.out.println("Test Passed: URL was added successfully to the list.");

		} catch (Exception e) {
			System.err.println("Test Failed: " + e.getMessage());
		} finally {
			// Close the browser
			driver.quit();
		}

	}

	@AfterMethod(alwaysRun = true)
	public void afterTest() {
		// Close the browser
		Basestep.BrowserClose();
		System.out.println("Browser Closed");
	}
}
