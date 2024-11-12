package NewBoardTestCase;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import NewBoard.Automationn.Basestep;
import NewBoardPage.NewBoardPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LamdaTest {

	@Test(groups = { "Signup Testing", "CompleteRegressionSuite" })
	public void Login() throws MalformedURLException, InterruptedException {

		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("130");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "cseusamashafiq");
		ltOptions.put("accessKey", "YIfnDPSVuRYHz1mLs6Oy0fE19jlDYUlMLEtd7mIXDpa5Ws8QLW");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("build", "SeleniumBuild");
		ltOptions.put("project", "SeleniumBuildProject");
		ltOptions.put("selenium_version", "4.0.0");
		ltOptions.put("w3c", true);
		browserOptions.setCapability("LT:Options", ltOptions);

		WebDriver driver = new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"), browserOptions);

		driver.get("https://my.newboard.io/"); 
		Thread.sleep(9000);

		driver.findElement(By.xpath("//input[@name = 'email']")).sendKeys("csusamashafiq@gmail.com");
		driver.findElement(By.xpath("//button[@type = 'submit']")).click();

		driver.quit();		








	}
}
