package pages.checkout_page;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.cart_page.CartPage;

import java.util.Arrays;

@Log4j2
public class CheckoutStepOnePage extends AbstractCheckoutPage {
    protected static final String CHECKOUT_PAGE_URL = URL + "checkout-step-one.html";
    @FindBy(xpath = "//*[contains(@class,'btn_secondary') and contains(text(), 'CANCEL')]")
    private WebElement cancelButton;
    @FindBy(xpath = "//*[contains(@class,'cart_button') and contains(@value, 'CONTINUE')]")
    private WebElement continueButton;
    @FindBy(id = "first-name")
    private WebElement firsNameInput;
    @FindBy(id = "last-name")
    private WebElement lastNameInput;
    @FindBy(id = "postal-code")
    private WebElement zipInput;
    private final String ERROR_XPATH = "//*[@data-test='error']";
    private final By ERROR_BY = By.xpath(ERROR_XPATH);
    private final String CLOSE_ERROR_BUTTON_XPATH = ERROR_XPATH + "//button[@class='error-button']";
    private final By CLOSE_ERROR_BUTTON_BY = By.xpath(CLOSE_ERROR_BUTTON_XPATH);

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CheckoutStepOnePage waitForPageLoaded() {
        try {
            getWebDriverWait().until(ExpectedConditions.visibilityOf(getSubtitle()));
        } catch (TimeoutException e) {
            log.error("Checkout page step one was not loaded");
            throw e;
        }
        return this;
    }

    @Override
    @Step("Open checkout step-one page")
    public CheckoutStepOnePage openPage() {
        log.info("Opened checkout step one page, URL: " + CHECKOUT_PAGE_URL);
        driver.get(CHECKOUT_PAGE_URL);
        return this;
    }

    @Step("Click cancel button")
    public CartPage clickCancelButton() {
        log.info("Clicked cancel button " + cancelButton);
        cancelButton.click();
        return new CartPage(driver);
    }

    @Step("Click continue button")
    public CheckoutStepTwoPage clickContinueButton() {
        log.info("Clicked continue button " + continueButton);
        continueButton.click();
        return new CheckoutStepTwoPage(driver).waitForPageLoaded();
    }

    @Step("Is checkout error displayed")
    public boolean isErrorDisplayed() {
        boolean isErrorDisplayed = !driver.findElements(ERROR_BY).isEmpty();
        log.info("Is Checkout step two error displayed: " + isErrorDisplayed);
        return isErrorDisplayed;
    }

    @Step("Get error text")
    public String getErrorText() {
        String errorText = driver.findElement(ERROR_BY).getText();
        log.info("Checkout step two error text: " + errorText);
        return errorText;
    }

    @Step("Click close checkout error button")
    public CheckoutStepOnePage clickCloseErrorButton() {
        log.info("Clicked close error button, xpath: " + CLOSE_ERROR_BUTTON_BY);
        driver.findElement(CLOSE_ERROR_BUTTON_BY).click();
        return this;
    }

    @Step("Set firs name '{firstNames}'")
    public CheckoutStepOnePage setFirstName(String... firstNames) {
        firsNameInput.sendKeys(firstNames);
        log.info("Sent keys to first name input, value: " + Arrays.toString(firstNames));
        return this;
    }

    @Step("Set last name '{lastNames}'")
    public CheckoutStepOnePage setLastName(String... lastNames) {
        lastNameInput.sendKeys(lastNames);
        log.info("Sent keys to last name input, value: " + Arrays.toString(lastNames));
        return this;
    }

    @Step("Set zip '{zips}'")
    public CheckoutStepOnePage setZip(String... zips) {
        zipInput.sendKeys(zips);
        log.info("Sent keys to zip input, value: " + Arrays.toString(zips));
        return this;
    }
}
