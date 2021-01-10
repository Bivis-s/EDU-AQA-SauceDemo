package pages.login_page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.AbstractPage;
import pages.inventory_page.InventoryPage;

public class LoginPage extends AbstractPage {
    protected static final String LOGIN_PAGE_URL = URL;
    @FindBy(id = "user-name")
    private WebElement usernameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(id = "login-button")
    private WebElement loginButton;
    private final String LOGIN_PIC_XPATH = "//img[contains(@class,'bot_column') and not(@style='hidden')]";
    private final String ERROR_MESSAGE_XPATH = "//*[contains(@data-test,'error')]";
    private final String CLOSE_ERROR_BUTTON_XPATH = ERROR_MESSAGE_XPATH + "//button[contains(@class,'error-button')]";

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public LoginPage waitForPageLoaded(int timeout) {
        return this;
    }

    @Override
    public LoginPage open(int timeout) {
        driver.get(LOGIN_PAGE_URL);
        return waitForPageLoaded(timeout);
    }

    @Override
    public boolean isPageOpened() {
        return loginButton.isDisplayed();
    }

    public boolean isLoginButtonEnabled() {
        return isElementEnabled(loginButton);
    }

    public boolean isUsernameFieldEnabled() {
        return isElementEnabled(usernameField);
    }

    public boolean isPasswordFieldEnabled() {
        return isElementEnabled(passwordField);
    }

    public LoginPage clickLoginButton() {
        clickElement(loginButton);
        return this;
    }

    public LoginPage enterUsername(String username) {
        sendKeys(usernameField, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        sendKeys(passwordField, password);
        return this;
    }

    public boolean isLoginPicVisible() {
        return !driver.findElements(By.xpath(LOGIN_PIC_XPATH)).isEmpty();
    }

    public String getLoginErrorMessage() {
        return driver.findElement(By.xpath(ERROR_MESSAGE_XPATH)).getText();
    }

    public LoginPage closeLoginError() {
        clickElement(driver.findElement(By.xpath(CLOSE_ERROR_BUTTON_XPATH)));
        return this;
    }

    public InventoryPage getInventoryPage() {
        return new InventoryPage(driver);
    }

    public LoginPage login(String username, String password) {
        enterUsername(username).enterPassword(password).clickLoginButton();
        return this;
    }
}