package NewBoard.Automationn;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LambdaTestDriver {

    protected static WebDriver driver = null;

    public static WebDriver LambdaBrowser() throws MalformedURLException {
        if (driver == null) {
            ChromeOptions browserOptions = new ChromeOptions();
            browserOptions.setPlatformName("Windows 10");
            browserOptions.setBrowserVersion("130");

            HashMap<String, Object> ltOptions = new HashMap<>();
            ltOptions.put("username", "cseusamashafiq");
            ltOptions.put("accessKey", "8W4s0ngSSHoiazfVVFXqltH4Orrqvee5SFyWEl4KAQVdess7jQ");
            ltOptions.put("video", true);
            ltOptions.put("project", "login");
            ltOptions.put("w3c", true);
            ltOptions.put("plugin", "java-testNG");

            browserOptions.setCapability("LT:Options", ltOptions);
            driver = new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"), browserOptions);
            driver.get("https://my.newboard.io/");
        }
        return driver;
    }

    // Closes browser and sets driver to null
    public static void BrowserClose() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    // Getter for WebDriver to be used in PageFactory
    public static WebDriver getDriver() {
        return driver;
    }
}
