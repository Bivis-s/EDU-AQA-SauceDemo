package pages.checkout_page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.cart_page.CartPage;

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
        getWebDriverWait().until(ExpectedConditions.visibilityOf(getSubtitle()));
        return this;
    }

    @Override
    @Step("Open checkout step-one page")
    public CheckoutStepOnePage openPage() {
        driver.get(CHECKOUT_PAGE_URL);
        return this;
    }

    @Step("Click cancel button")
    public CartPage clickCancelButton() {
        cancelButton.click();
        return new CartPage(driver);
    }

    @Step("Click continue button")
    public CheckoutStepTwoPage clickContinueButton() {
        continueButton.click();
        return new CheckoutStepTwoPage(driver);
    }

    @Step("Is checkout error displayed")
    public boolean isErrorDisplayed() {
        return !driver.findElements(ERROR_BY).isEmpty();
    }

    @Step("Get error text")
    public String getErrorText() {
        return driver.findElement(ERROR_BY).getText();
    }

    @Step("Click close checkout error button")
    public CheckoutStepOnePage clickCloseErrorButton() {
        driver.findElement(CLOSE_ERROR_BUTTON_BY).click();
        return this;
    }

    @Step("Is first name field enabled")
    public boolean isFirstNameFieldEnabled() {
        return firsNameInput.isEnabled();
    }

    @Step("Is last name field enabled")
    public boolean isLastNameFieldEnabled() {
        return lastNameInput.isEnabled();
    }

    @Step("Is zip field enabled")
    public boolean isZipFieldEnabled() {
        return zipInput.isEnabled();
    }

    @Step("Set firs name '{firstNames}'")
    public CheckoutStepOnePage setFirstName(String... firstNames) {
        firsNameInput.sendKeys(firstNames);
        return this;
    }

    @Step("Set last name '{lastNames}'")
    public CheckoutStepOnePage setLastName(String... lastNames) {
        lastNameInput.sendKeys(lastNames);
        return this;
    }

    @Step("Set zip '{zips}'")
    public CheckoutStepOnePage setZip(String... zips) {
        zipInput.sendKeys(zips);
        return this;
    }
}
