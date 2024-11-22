package NewBoardTestCase;

import java.net.URL;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import NewBoard.Automationn.Basestep;
import NewBoard.Automationn.LambdaTestDriver;
import NewBoardPage.NewBoardPages;

public class TC_90 extends LambdaTestDriver{

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

	@Test(groups = {"Update FirstName and LastName", "CompleteRegressionSuite"})
	public void newBoardLogin() throws Exception {
		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("130");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "cseusamashafiq");
		ltOptions.put("accessKey", "8W4s0ngSSHoiazfVVFXqltH4Orrqvee5SFyWEl4KAQVdess7jQ");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("build", "TC_90_UpdateProfile");
		ltOptions.put("project", "TC_90_UpdateProfile");
		ltOptions.put("name", "TC_90_UpdateProfiles");
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

		try {

			// Navigate to Profile Settings
			WebElement settingsButton = driver.findElement(By.xpath("//p[contains(text(), 'Settings')]")); // Update with the actual locator
			settingsButton.click();

			Thread.sleep(3000); // Wait for settings page to load

			// Locate and update First Name and Last Name
			WebElement firstNameField = driver.findElement(By.xpath("//input[@placeholder='Please Enter First Name']")); // Update locator
			WebElement lastNameField = driver.findElement(By.xpath("//input[@placeholder='Last name']")); // Update locator


			String updatedFirstName = "Ussama";
			String updatedLastName = "Shafi";
			firstNameField.clear();
			firstNameField.sendKeys(updatedFirstName);
			lastNameField.clear();
			lastNameField.sendKeys(updatedLastName);

			// Wait for the page to update
			Thread.sleep(5000);

			// Verify the updated name
			WebElement profileName = driver.findElement(By.xpath(" font-semibold text-[1.125rem] text-[#323338]")); // Update locator for the name display
			String displayedName = profileName.getText();
			String expectedName = updatedFirstName + " " + updatedLastName;

			Assert.assertEquals(displayedName, expectedName, "Name was not updated successfully!");

			System.out.println("Test Passed: Name updated successfully to " + displayedName);

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
