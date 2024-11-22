package NewBoardTestCase;

import java.io.FileInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Store;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import NewBoard.Automationn.EmailUtils;
import NewBoard.Automationn.LambdaTestDriver;

public class Email_testing extends LambdaTestDriver{

	public static void main(String args[]) throws Exception {

		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("130");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "cseusamashafiq");
		ltOptions.put("accessKey", "8W4s0ngSSHoiazfVVFXqltH4Orrqvee5SFyWEl4KAQVdess7jQ");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("build", "Email Verify Text");
		ltOptions.put("project", "Email Verify Text");
		ltOptions.put("name", "Email Verify Text");
		ltOptions.put("selenium_version", "4.0.0");
		ltOptions.put("w3c", true);
		browserOptions.setCapability("LT:Options", ltOptions);
		driver = new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"), browserOptions);
		driver.get("https://my.newboard.io/");
		Thread.sleep(7000);

		try {
			WebElement createAccountLink = driver.findElement(By.xpath("//a[contains(text(), 'Create an account.')]"));
			createAccountLink.click();
			Thread.sleep(5000);

			driver.findElement(By.xpath("//input[@placeholder='First name']")).sendKeys("John");
			driver.findElement(By.xpath("//input[@placeholder='Last name']")).sendKeys("Doe");
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("nbtestautomation@gmail.com");

			WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
			checkbox.click();

			EmailUtils emailUtils = new EmailUtils();
			Properties properties = new Properties();
			try (FileInputStream fis = new FileInputStream("C:\\Users\\dell\\eclipse-workspace\\Com.NewBoard.Automationn\\src\\test\\java\\NewBoardTestCase\\Data.properties")) {
				properties.load(fis);
			}

			properties.setProperty("mail.debug", "true");
			Store store = emailUtils.connectToGmail(properties);

			List<String> emailText = emailUtils.getUnreadMessageByFromEmail(
					store,
					"Inbox",
					"no-reply@notify.newboard.io",
					"Newboard Email Verification"
					);

			if (emailText.isEmpty()) {
				throw new Exception("No email found from the specified sender.");
			}

			for (String emailContent : emailText) {
				System.out.println("Email Content: " + emailContent);
				String otp = extractOtp(emailContent);
				if (otp != null) {
					System.out.println("OTP Found: " + otp);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}
	private static String extractOtp(String emailContent) {
		Pattern otpPattern = Pattern.compile("\\b\\d{4}\\b");
		Matcher matcher = otpPattern.matcher(emailContent);
		return matcher.find() ? matcher.group() : null;
	}
}
