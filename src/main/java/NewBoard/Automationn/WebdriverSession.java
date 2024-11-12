package NewBoard.Automationn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebdriverSession {
    
    protected static WebDriver webdriver = null;

    // Launches browser if not already initialized
    public static void BrowserLaunch() {
        if (webdriver == null) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\dell\\eclipse-workspace\\Com.NewBoard.Automationn\\Drivers\\chromedriver.exe");
            webdriver = new ChromeDriver();
            webdriver.manage().window().maximize();
            webdriver.navigate().to("https://my.newboard.io/");
            Basestep.waitForPageLoad();
        }
    }

    // Closes browser and sets webdriver to null
    public static void BrowserClose() {
        if (webdriver != null) {
            webdriver.quit();
            webdriver = null;
        }
    }

    // Getter for WebDriver to be used in PageFactory
    public static WebDriver getDriver() {
        return webdriver;
    }
}
