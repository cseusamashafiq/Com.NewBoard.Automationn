package NewBoardTestCase;

import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import NewBoard.Automationn.Basestep;
import NewBoard.Automationn.LambdaTestDriver;
import NewBoardPage.NewBoardPages;

public class TC_344 extends LambdaTestDriver{

	private String testCaseName;
	private String testCaseDescription;

	@BeforeTest(alwaysRun = true)
	public void beforeMethod() {
		// Setting unique file name using timestam
		testCaseName = this.getClass().getName();
		testCaseDescription = "The purpose of this test case is to verify \"" + testCaseName + "\" workflow.";
		System.out.println("Test Case: " + testCaseName);
		System.out.println("Description: " + testCaseDescription);
	}

	@Test(groups = {"Login with case_sensitive", "CompleteRegressionSuite"})
	public void newBoardLogin() throws Exception {

		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("130");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "cseusamashafiq");
		ltOptions.put("accessKey", "8W4s0ngSSHoiazfVVFXqltH4Orrqvee5SFyWEl4KAQVdess7jQ");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("build", "TC_344_Login_CaseSensitive");
		ltOptions.put("project", "TC_344_Login_CaseSensitive");
		ltOptions.put("name", "TC_344_Login_CaseSensitive");
		ltOptions.put("selenium_version", "4.0.0");
		ltOptions.put("w3c", true);
		browserOptions.setCapability("LT:Options", ltOptions);
		driver = new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"), browserOptions);
		driver.get("https://my.newboard.io/");

		Thread.sleep(8000);

		NewBoardPages newBoardPages = new NewBoardPages(); // Pass driver to page class constructor
		TC_03 login = new TC_03();

		// Use the Page Object methods
		newBoardPages.enterEmail("CSusamashafiq@gmail.com");
		newBoardPages.clickSubmit();
		Thread.sleep(8000);

		// Fetch OTP from email
		String otp = login.fetchOTPFromEmail();
		System.out.println("Fetched OTP: " + otp);
		newBoardPages.enterOTP(otp);
	}

	@AfterMethod(alwaysRun = true)
	public void afterTest() {
		// Close the browser
		Basestep.BrowserClose();
		System.out.println("Browser Closed");
	}

}
