package NewBoardTestCase;

import java.util.concurrent.TimeUnit;
import NewBoard.Automationn.Basestep;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import NewBoard.Automationn.WebdriverSession;
import NewBoardPage.NewBoardPages;

public class Testcases_02 extends Basestep {

    String testCaseName = null;
    String testCaseDescription = null;  
    
    @BeforeTest(alwaysRun = true)
    public void beforeMethod() {
        testCaseName = this.getClass().getName();
        testCaseDescription = "The purpose of this test case is to verify \"" + testCaseName + "\" workflow.";
        System.out.println("Test Case: " + testCaseName);
        System.out.println("Description: " + testCaseDescription);
        Basestep.BrowserLaunch();
        Basestep.waitForPageLoad();
    }

    @Test(groups = { "Signup Testing", "CompleteRegressionSuite" })
    public void testSignupFlow() {
    	NewBoardPages newBoardPages = new NewBoardPages();
        newBoardPages.clickCreateAccount();
        newBoardPages.enterFirstName("Aniket");
        newBoardPages.enterLastName("Borhade");
        newBoardPages.enterEmail("Aniket@gmail.com");
        newBoardPages.clickCheckbox();
        Basestep.screencapture();
        newBoardPages.clickSubmit();
        Basestep.screencapture();
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest() {
    	Basestep.BrowserClose();
       // System.out.print("Browser Closed");
    }
}
