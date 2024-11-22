package NewBoardTestCase;

import NewBoard.Automationn.Basestep;
import NewBoard.Automationn.LambdaTestDriver;
import NewBoardPage.NewBoardPages;


import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.*;

import java.net.URL;
import java.util.HashMap;

public class TC_03  extends LambdaTestDriver{
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

	@Test(groups = {"Login Testing", "CompleteRegressionSuite"})
	public void newBoardLogin() throws Exception {

		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("130");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "cseusamashafiq");
		ltOptions.put("accessKey", "8W4s0ngSSHoiazfVVFXqltH4Orrqvee5SFyWEl4KAQVdess7jQ");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("build", "TC_03_Login");
		ltOptions.put("project", "TC_03_Login");
		ltOptions.put("name", "TC_03_Login");
		ltOptions.put("selenium_version", "4.0.0");
		ltOptions.put("w3c", true);
		browserOptions.setCapability("LT:Options", ltOptions);
		driver = new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"), browserOptions);
		driver.get("https://my.newboard.io/");
		Thread.sleep(7000);

		// Login Flow
		NewBoardPages newBoardPages = new NewBoardPages();
		newBoardPages.enterEmail("nbtestautomation@gmail.com");
		newBoardPages.clickSubmit();
		Thread.sleep(8000);

		// Fetch OTP from email
		String otp = fetchOTPFromEmail();
		System.out.println("Fetched OTP: " + otp);

		// Enter OTP and submit
		newBoardPages.enterOTP(otp);
	}

	@AfterMethod(alwaysRun = true)
	public void afterTest() {
		// Close the browser
		Basestep.BrowserClose();
		System.out.println("Browser Closed");
	}

	/**
	 * Fetches the OTP from the email inbox.
	 *
	 * @return The extracted OTP as a string.
	 * @throws Exception
	 */
	public String fetchOTPFromEmail() throws Exception {
		// Set up email properties
		Properties props = new Properties();
		props.put("mail.store.protocol", "imaps");
		props.put("mail.imap.ssl.enable", "true");

		// Connect to the email server
		Session session = Session.getDefaultInstance(props);
		Store store = session.getStore("imaps");
		try {
			store.connect("imap.gmail.com", "nbtestautomation@gmail.com", "Test@1234");
		} catch (AuthenticationFailedException e) {
			throw new Exception("Authentication failed! Please check your credentials and security settings.", e);
		}

		// Access the inbox folder
		Folder inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_ONLY);

		// Fetch the latest email
		Message[] messages = inbox.getMessages();
		Message latestMessage = messages[messages.length - 1];

		// Extract OTP from the email body
		String emailBody = getTextFromMessage(latestMessage);
		String otp = extractOTP(emailBody);

		// Close connections
		inbox.close(false);
		store.close();

		return otp;
	}

	/**
	 * Extracts OTP from the email body using a regex.
	 *
	 * @param emailBody The email content.
	 * @return The extracted OTP.
	 */
	private String extractOTP(String emailBody) {
		Pattern otpPattern = Pattern.compile("\\b\\d{6}\\b"); // Adjust regex for your OTP format
		Matcher matcher = otpPattern.matcher(emailBody);
		if (matcher.find()) {
			return matcher.group();
		}
		throw new RuntimeException("OTP not found in email.");
	}

	/**
	 * Extracts the text content from an email message.
	 *
	 * @param message The email message.
	 * @return The email content as a string.
	 * @throws Exception
	 */
	private String getTextFromMessage(Message message) throws Exception {
		if (message.isMimeType("text/plain")) {
			return message.getContent().toString();
		} else if (message.isMimeType("text/html")) {
			return message.getContent().toString(); // For HTML content
		} else if (message.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart) message.getContent();
			for (int i = 0; i < multipart.getCount(); i++) {
				BodyPart bodyPart = multipart.getBodyPart(i);
				if (bodyPart.isMimeType("text/plain") || bodyPart.isMimeType("text/html")) {
					return bodyPart.getContent().toString();
				}
			}
		}
		return null;
	}
}
