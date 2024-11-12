package NewBoardPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import NewBoard.Automationn.WebdriverSession;

public class NewBoardPages {

    // Initialize elements with the active WebDriver instance
    public NewBoardPages() {
        PageFactory.initElements(WebdriverSession.getDriver(), this);
    }

    @FindBy(xpath = "//input[@name = 'email']")
    public WebElement email;

    @FindBy(xpath = "//button[@type = 'submit']")
    public WebElement submitBtn;

    @FindBy(xpath = "//a[contains(text(), 'Create an account.')]")
    public WebElement createAccount;

    @FindBy(xpath = "//input[@placeholder='First name']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@placeholder='Last name']")
    public WebElement lastName;

    @FindBy(xpath = "//input[@type='checkbox']")
    public WebElement checkbox;

    public void clickCreateAccount() {
        createAccount.click();
    }

    public void enterFirstName(String fName) {
        firstName.sendKeys(fName);
    }

    public void enterLastName(String lName) {
        lastName.sendKeys(lName);
    }
    
    public void enterEmail(String emails) {
    	email.sendKeys(emails);
    }

    public void clickCheckbox() {
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void clickSubmit() {
        submitBtn.click();
    }

    public void createAccountForm(String userEmail, String fName, String lName) {
        email.sendKeys(userEmail);
        clickCreateAccount();
        enterFirstName(fName);
        enterLastName(lName);
        clickCheckbox();
        clickSubmit();
    }
}
