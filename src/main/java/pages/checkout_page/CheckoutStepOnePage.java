package pages.checkout_page;

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
    public CheckoutStepOnePage waitForPageLoaded(int timeout) {
        getWebDriverWait(timeout).until(ExpectedConditions.visibilityOf(getSubtitle()));
        return this;
    }

    @Override
    public CheckoutStepOnePage open(int timeout) {
        driver.get(CHECKOUT_PAGE_URL);
        return this;
    }

    public CartPage cancel() {
        cancelButton.click();
        return new CartPage(driver);
    }

    public CheckoutStepTwoPage next() {
        continueButton.click();
        return new CheckoutStepTwoPage(driver);
    }

    public boolean isErrorDisplayed() {
        return !driver.findElements(ERROR_BY).isEmpty();
    }

    public String getErrorText() {
        return driver.findElement(ERROR_BY).getText();
    }

    public CheckoutStepOnePage closeError() {
        driver.findElement(CLOSE_ERROR_BUTTON_BY).click();
        return this;
    }

    public boolean isFirstNameFieldEnabled() {
        return firsNameInput.isEnabled();
    }

    public boolean isLastNameFieldEnabled() {
        return lastNameInput.isEnabled();
    }

    public boolean isZipFieldEnabled() {
        return zipInput.isEnabled();
    }

    public CheckoutStepOnePage setFirstName(String... keys) {
        firsNameInput.sendKeys(keys);
        return this;
    }

    public CheckoutStepOnePage setLastName(String... keys) {
        lastNameInput.sendKeys(keys);
        return this;
    }

    public CheckoutStepOnePage setZip(String... keys) {
        zipInput.sendKeys(keys);
        return this;
    }
}
