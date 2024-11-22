package NewBoardPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import NewBoard.Automationn.LambdaTestDriver;

public class NewBoardPages extends LambdaTestDriver{

	// Initialize elements with the active WebDriver instance

	public NewBoardPages() {
		if (LambdaTestDriver.getDriver() == null) {
			throw new IllegalStateException("WebDriver is not initialized. Call LambdaBrowser() first.");
		}
		PageFactory.initElements(LambdaTestDriver.getDriver(), this);
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

	@FindBy(xpath = "//input[@type='text']")
	public WebElement OTPfield;

	
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
	
	public void enterOTP(String otp) {
		OTPfield.sendKeys(otp);
	}

}
