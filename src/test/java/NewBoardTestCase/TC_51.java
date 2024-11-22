package NewBoardTestCase;

import NewBoard.Automationn.Basestep;
import NewBoard.Automationn.LambdaTestDriver;

import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import NewBoardPage.NewBoardPages;

public class TC_51 extends LambdaTestDriver {

	String testCaseName = null;
	String testCaseDescription = null;  

	@BeforeTest(alwaysRun = true)
	public void beforeMethod() {
		testCaseName = this.getClass().getName();
		testCaseDescription = "The purpose of this test case is to verify \"" + testCaseName + "\" workflow.";
		System.out.println("Test Case: " + testCaseName);
		System.out.println("Description: " + testCaseDescription);
	}

	@Test(groups = { "Signup Testing", "CompleteRegressionSuite" })
	public void testSignupFlow() throws Exception {
		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("130");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "cseusamashafiq");
		ltOptions.put("accessKey", "8W4s0ngSSHoiazfVVFXqltH4Orrqvee5SFyWEl4KAQVdess7jQ");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("build", "TC_51_Signup");
		ltOptions.put("project", "TC_51_Signup");
		ltOptions.put("name", "TC_51_Signup");
		ltOptions.put("selenium_version", "4.0.0");
		ltOptions.put("w3c", true);
		browserOptions.setCapability("LT:Options", ltOptions);
		driver = new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"), browserOptions);
		driver.get("https://my.newboard.io/");
		Thread.sleep(6000);

		NewBoardPages newBoardPages = new NewBoardPages();
		TC_03 login = new TC_03();

		newBoardPages.clickCreateAccount();
		Thread.sleep(6000);
		newBoardPages.enterFirstName("Aniket");
		newBoardPages.enterLastName("Borhade");
		newBoardPages.enterEmail("Aniket@gmail.com");
		newBoardPages.clickCheckbox();
		Basestep.screencapture();
		newBoardPages.clickSubmit();
		Basestep.screencapture();
		Thread.sleep(6000);
		String otp = login.fetchOTPFromEmail();
		System.out.println("Fetched OTP: " + otp);
		newBoardPages.enterOTP(otp);

	}

	@AfterMethod(alwaysRun = true)
	public void afterTest() {
		Basestep.BrowserClose();
		System.out.print("Browser Closed");
	}
}
