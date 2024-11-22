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

import NewBoard.Automationn.Basestep;
import NewBoard.Automationn.LambdaTestDriver;
import NewBoardPage.NewBoardPages;

public class TC_133 extends LambdaTestDriver{
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
	@Test(groups = {"Create Workspace", "CompleteRegressionSuite"})
	public void newBoardLogin() throws Exception {
		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("130");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "cseusamashafiq");
		ltOptions.put("accessKey", "8W4s0ngSSHoiazfVVFXqltH4Orrqvee5SFyWEl4KAQVdess7jQ");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("build", "TC_133_Create_Workspace");
		ltOptions.put("project", "TC_133_Create_Workspace");
		ltOptions.put("name", "TC_133_Create_Workspace");
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

			WebElement arrowIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[4]/div[1]/div/div[2]/div[1]/div[1]/button/p/img")));
			arrowIcon.click();

			// Click on "Workspace" option
			WebElement workspaceOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/div[contains(text(), 'Create Workspace')]")));
			workspaceOption.click();

			// Enter workspace name
			WebElement workspaceNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/input[@placeholder='Workspace name...']")));
			workspaceNameField.sendKeys("Test Workspace");

			WebElement nextButton1 = driver.findElement(By.xpath("//button[contains(text(), 'Next')]"));
			nextButton1.click();

			// Enter board name
			WebElement boardNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Board name...']")));
			boardNameField.sendKeys("Test Board");

			WebElement nextButton2 = driver.findElement(By.xpath("//button[contains(text(), 'Next')]"));
			nextButton2.click();

			// Enter list name
			WebElement listNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='List 1']")));
			listNameField.sendKeys("Test List");

			WebElement nextButton3 = driver.findElement(By.xpath("//button[contains(text(), 'Next')]"));
			nextButton3.click();

			// Validate that the workspace is created successfully
			Thread.sleep(7000); // Wait for the workspace creation process
			boolean isWorkspaceCreated = driver.findElements(By.xpath("//div[contains(text(), 'Test Workspace')]")).size() > 0;
			if (isWorkspaceCreated) {
				System.out.println("Workspace created successfully!");
			} else {
				System.out.println("Workspace creation failed.");
			}
		} catch (Exception e) {
			System.err.println("Test Failed: " + e.getMessage());
		} 
	}
	@AfterMethod(alwaysRun = true)
	public void afterTest() {
		// Close the browser
		Basestep.BrowserClose();
		System.out.println("Browser Closed");
	}
}

