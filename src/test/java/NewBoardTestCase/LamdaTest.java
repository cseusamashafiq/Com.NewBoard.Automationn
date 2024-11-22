package NewBoardTestCase;

import java.net.MalformedURLException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import NewBoard.Automationn.LambdaTestDriver;
import NewBoardPage.NewBoardPages;

public class LamdaTest extends LambdaTestDriver{

	String testCaseName = null;
	String testCaseDescription = null;  

	@BeforeTest(alwaysRun = true)
	public void beforeMethod() {
		testCaseName = this.getClass().getName();
		testCaseDescription = "The purpose of this test case is to verify \"" + testCaseName + "\" workflow.";
		System.out.println("Test Case: " + testCaseName);
		System.out.println("Description: " + testCaseDescription);
	}
	@Test(groups = { "Login with Valid", "CompleteRegressionSuite" })
	public void Login() throws MalformedURLException, InterruptedException {
		// Start the browser first
		LambdaTestDriver.LambdaBrowser();
		Thread.sleep(9000); // Wait for the browser to load

		// Then initialize the Page Object
		NewBoardPages newBoardPages = new NewBoardPages();

		// Use the Page Object methods
		newBoardPages.enterEmail("csusamashafiq@gmail.com");
		newBoardPages.clickSubmit();
	}

	@AfterMethod(alwaysRun = true)
	public void afterTest() {
		LambdaTestDriver.BrowserClose();
	}

}
