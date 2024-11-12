package NewBoardTestCase;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class SauceLab {

	@Test(groups = { "Signup Testing", "CompleteRegressionSuite" })
	public void Login() throws MalformedURLException, InterruptedException
	{
		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("latest");
		Map<String, Object> sauceOptions = new HashMap<>();
		sauceOptions.put("username", "oauth-cseusamashafiq-1fbb7");
		sauceOptions.put("accessKey", "5db47cec-5a85-4684-80d0-724fe22953c7");
		sauceOptions.put("build", "<your build id>");
		sauceOptions.put("name", "<your test name>");
		browserOptions.setCapability("sauce:options", sauceOptions);

		// start the session
		URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
		RemoteWebDriver driver = new RemoteWebDriver(url, browserOptions);
		

		driver.get("https://my.newboard.io/"); 
		Thread.sleep(9000);

		driver.findElement(By.xpath("//input[@name = 'email']")).sendKeys("csusamashafiq@gmail.com");
		driver.findElement(By.xpath("//button[@type = 'submit']")).click();

		driver.quit();

	}
}
