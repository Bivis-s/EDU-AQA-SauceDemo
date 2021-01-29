package pages.login_page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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
    private final String ERROR_XPATH = "//*[contains(@data-test,'error')]";
    private final By ERROR_BY = By.xpath(ERROR_XPATH);
    private final String CLOSE_ERROR_BUTTON_XPATH = ERROR_XPATH + "//button[contains(@class,'error-button')]";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public LoginPage waitForPageLoaded() {
        return this;
    }

    @Override
    @Step("Open login page")
    public LoginPage openPage() {
        driver.get(LOGIN_PAGE_URL);
        return waitForPageLoaded();
    }

    @Override
    public boolean isPageOpened() {
        return loginButton.isDisplayed();
    }

    @Step("Check if login button enabled")
    public boolean isLoginButtonEnabled() {
        return loginButton.isEnabled();
    }

    @Step("Check if username button enabled")
    public boolean isUsernameFieldEnabled() {
        return usernameField.isEnabled();
    }

    @Step("Check if password field enabled")
    public boolean isPasswordFieldEnabled() {
        return passwordField.isEnabled();
    }

    @Step("Click login button")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Enter username '{username}'")
    public LoginPage enterUsername(String username) {
        usernameField.sendKeys(username);
        return this;
    }

    @Step("Enter password '{password}'")
    public LoginPage enterPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Check if login picture visible")
    public boolean isLoginPicVisible() {
        return !driver.findElements(By.xpath(LOGIN_PIC_XPATH)).isEmpty();
    }

    @Step("Check if login error displayed")
    public boolean isErrorDisplayed() {
        return !driver.findElements(ERROR_BY).isEmpty();
    }

    @Step("Get login error text")
    public String getLoginErrorMessage() {
        return driver.findElement(By.xpath(ERROR_XPATH)).getText();
    }

    @Step("Close login error")
    public LoginPage closeLoginError() {
        driver.findElement(By.xpath(CLOSE_ERROR_BUTTON_XPATH)).click();
        return this;
    }

    @Step("Login using username '{username}' and password '{password}'")
    public InventoryPage login(String username, String password) {
        enterUsername(username).enterPassword(password).clickLoginButton();
        return new InventoryPage(driver);
    }
}
