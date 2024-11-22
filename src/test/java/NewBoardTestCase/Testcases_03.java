package NewBoardTestCase;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class Testcases_03 {

	@Test(groups = { "Signup Testing", "CompleteRegressionSuite" })
	public void Login() throws MalformedURLException, InterruptedException {

		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("130");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "cseusamashafiq");
		ltOptions.put("accessKey", "8W4s0ngSSHoiazfVVFXqltH4Orrqvee5SFyWEl4KAQVdess7jQ");
		ltOptions.put("video", true);
		ltOptions.put("project", "Unknown");
		ltOptions.put("w3c", true);
		ltOptions.put("plugin", "java-testNG");
		browserOptions.setCapability("LT:Options", ltOptions);
		WebDriver driver = new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"), browserOptions);

		driver.get("https://my.newboard.io/"); 
		Thread.sleep(9000);

		driver.findElement(By.xpath("//a[contains(text(), 'Create an account.')]")).click();
		Thread.sleep(9000);
		driver.findElement(By.xpath("//input[@placeholder='First name']")).sendKeys("Usama");
		driver.findElement(By.xpath("//input[@placeholder='Last name']")).sendKeys("sha");
		driver.findElement(By.xpath("//input[@name = 'email']")).sendKeys("csusamashafiq@gmail.com");
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//button[@type = 'submit']")).click();
		driver.quit();		
	}
}
