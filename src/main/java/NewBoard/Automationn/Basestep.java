package NewBoard.Automationn;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.time.Duration;

public class Basestep extends WebdriverSession {

	// Take a screenshot and save it to the specified location
	public static void screencapture() {
		if (webdriver != null) {
			// Capture the current date and time to make the filename unique
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			File screenshot = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);

			try {
				// Save the screenshot with a unique filename
				String filePath = "F:\\Screenshots\\screenshot_" + timestamp + ".png";
				FileHandler.copy(screenshot, new File(filePath));
				System.out.println("Screenshot captured and saved at " + filePath);
			} catch (IOException e) {
				System.out.println("Error while saving screenshot: " + e.getMessage());
			}
		} else {
			System.out.println("Webdriver not initialized, cannot take screenshot.");
		}
	}

	// Wait until the page load is complete
	public static void waitForPageLoad() {
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (webdriver != null) {
			WebDriverWait wait = new WebDriverWait(webdriver, Duration.ofSeconds(360));
		} else {
			System.out.println("Webdriver not initialized, cannot wait for page load.");
		}
	}
}
