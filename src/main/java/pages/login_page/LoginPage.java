package pages.login_page;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AbstractPage;
import pages.inventory_page.InventoryPage;

@Log4j2
public class LoginPage extends AbstractPage {
    protected static final String LOGIN_PAGE_URL = URL;
    private final String LOGIN_PIC_XPATH = "//img[contains(@class,'bot_column') and not(@style='hidden')]";
    private final String ERROR_XPATH = "//*[contains(@data-test,'error')]";
    private final By ERROR_BY = By.xpath(ERROR_XPATH);
    private final String CLOSE_ERROR_BUTTON_XPATH = ERROR_XPATH + "//button[contains(@class,'error-button')]";
    @FindBy(id = "user-name")
    private WebElement usernameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(id = "login-button")
    private WebElement loginButton;

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
        log.info("Opening page: " + LOGIN_PAGE_URL);
        return waitForPageLoaded();
    }

    @Override
    public boolean isPageOpened() {
        boolean isPageOpened = loginButton.isDisplayed();
        log.info("Login page was opened " + isPageOpened + ", URL: " + LOGIN_PAGE_URL);
        return isPageOpened;
    }

    @Step("Click login button")
    public void clickLoginButton() {
        log.info("Clicked login button " + loginButton);
        loginButton.click();
    }

    @Step("Enter username '{username}'")
    public LoginPage enterUsername(String username) {
        log.info("Sent keys to username field, field:" + usernameField + ", username: " + username);
        usernameField.sendKeys(username);
        return this;
    }

    @Step("Enter password '{password}'")
    public LoginPage enterPassword(String password) {
        log.info("Sent keys to password field, field:" + usernameField + ", password: " + password);
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Check if login picture visible")
    public boolean isLoginPicVisible() {
        boolean isLoginPicVisible = !driver.findElements(By.xpath(LOGIN_PIC_XPATH)).isEmpty();
        log.info("Is login picture visible: " + isLoginPicVisible);
        return isLoginPicVisible;
    }

    @Step("Check if login error displayed")
    public boolean isErrorDisplayed() {
        boolean isErrorDisplayed = !driver.findElements(ERROR_BY).isEmpty();
        log.info("Is login error is displayed: " + isErrorDisplayed);
        return isErrorDisplayed;
    }

    @Step("Get login error text")
    public String getLoginErrorMessage() {
        String loginErrorMessage = driver.findElement(By.xpath(ERROR_XPATH)).getText();
        log.info("Login error message: " + loginErrorMessage);
        return loginErrorMessage;
    }

    @Step("Close login error")
    public LoginPage closeLoginError() {
        log.info("Clicked close login error button, xpath " + CLOSE_ERROR_BUTTON_XPATH);
        driver.findElement(By.xpath(CLOSE_ERROR_BUTTON_XPATH)).click();
        return this;
    }

    @Step("Login using username '{username}' and password '{password}'")
    public InventoryPage login(String username, String password) {
        enterUsername(username).enterPassword(password).clickLoginButton();
        return new InventoryPage(driver);
    }
}
