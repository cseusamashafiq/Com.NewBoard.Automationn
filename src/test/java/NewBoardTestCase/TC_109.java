package NewBoardTestCase;

import org.testng.annotations.Test;
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

import NewBoard.Automationn.Basestep;
import NewBoard.Automationn.LambdaTestDriver;
import NewBoardPage.NewBoardPages;

@Test
public class TC_109 extends LambdaTestDriver{
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
	@Test(groups = {"DeleteWorspace", "CompleteRegressionSuite"})
	public void newBoardLogin() throws Exception {
		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("130");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "cseusamashafiq");
		ltOptions.put("accessKey", "8W4s0ngSSHoiazfVVFXqltH4Orrqvee5SFyWEl4KAQVdess7jQ");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("build", "TC_109_Delete_Workspace");
		ltOptions.put("project", "TC_109_Delete_Workspace");
		ltOptions.put("name", "TC_109_Delete_Workspace");
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
			WebDriverWait wait = new WebDriverWait(driver, null);
			// Navigate to Profile Settings
			WebElement settingsButton = driver.findElement(By.xpath("//p[contains(text(), 'Settings')]")); // Update with the actual locator
			settingsButton.click();

			// Go to the Workspace section
			WebElement workspaceTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[2]//p[contains(text(), 'Workspace')]")));
			workspaceTab.click();

			// Fetch available workspaces
			WebElement workspaceList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div[2]/div/div[2]/div[2]/div/div/div/div[2]/div[2]/div[2]/div[2]/div/h2")));
			System.out.println("Workspaces fetched successfully.");

			// Select a workspace to delete
			WebElement deleteButton = workspaceList.findElement(By.xpath("//button[contains(text(), 'Delete')]"));
			deleteButton.click();

			// Handle the delete confirmation popup
			WebElement confirmationInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder=\"Your workspace name\"]")));
			confirmationInput.sendKeys("Workspace Name"); // Replace with the workspace name to delete

			WebElement confirmDeleteButton = driver.findElement(By.xpath("//button[2][contains(text(), 'Delete')]"));
			confirmDeleteButton.click();


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
