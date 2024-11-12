package NewBoardTestCase;

import NewBoard.Automationn.Basestep;
import NewBoardPage.NewBoardPages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Testcases_01 extends Basestep {
	private ExtentReports extent;
	private ExtentSparkReporter spark;
	private String testCaseName;
	private String testCaseDescription;

	@BeforeTest(alwaysRun = true)
	public void beforeMethod() {
		// Setting unique file name using timestamp
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		testCaseName = this.getClass().getName();
		testCaseDescription = "The purpose of this test case is to verify \"" + testCaseName + "\" workflow.";
		System.out.println("Test Case: " + testCaseName);
		System.out.println("Description: " + testCaseDescription);

		// Initialize Extent Reports with unique report file path
		extent = new ExtentReports();
		spark = new ExtentSparkReporter("F:\\Reports\\ExtentReport_" + timestamp + ".html");
		extent.attachReporter(spark);
	}

	@Test(groups = {"Login Testing", "CompleteRegressionSuite"})
	public void newBoardLogin() throws InterruptedException {
		ExtentTest test = extent.createTest("System Open");
		test.log(Status.INFO, "Starting Newboard Login Test");

		Basestep.BrowserLaunch();
		Basestep.waitForPageLoad();
		Thread.sleep(8000);

		NewBoardPages newBoardPages = new NewBoardPages();
		newBoardPages.enterEmail("Usama@gmail.com");
		newBoardPages.clickSubmit();
		Thread.sleep(3000);
		// Log completion in the report
		test.log(Status.PASS, "Login Test Completed Successfully");
	}

	@AfterMethod(alwaysRun = true)
	public void afterTest() {
		// Close the browser
		//Basestep.BrowserClose();
		System.out.println("Browser Closed");

		// Flush the report to ensure it's written to file
		if (extent != null) {
			extent.flush();
		}
	}
}
