package pages.checkout_page;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import products.CartProduct;
import utilities.PageUtilities;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class CheckoutStepTwoPage extends AbstractCheckoutPage {
    public static final String CHECKOUT_STEP_TWO_PAGE_URL = URL + "checkout-step-two.html";
    private final By PRODUCT_BY = By.xpath("//*[contains(@class,'cart_list')]//*[@class='cart_item']");
    @FindBy(xpath = "//*[contains(@class,'btn_secondary') and contains(text(), 'CANCEL')]")
    private WebElement cancelButton;
    @FindBy(xpath = "//*[contains(@class,'cart_button') and contains(text(), 'FINISH')]")
    private WebElement finishButton;

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CheckoutStepTwoPage waitForPageLoaded() {
        try {
            getWebDriverWait().until(ExpectedConditions.visibilityOf(getSubtitle()));
        } catch (TimeoutException e) {
            log.error("Checkout page step two was not loaded");
            throw e;
        }
        return this;
    }

    @Override
    @Step("Open checkout step-two page")
    public CheckoutStepTwoPage openPage() {
        log.info("Opened checkout step two page, URL: " + CHECKOUT_STEP_TWO_PAGE_URL);
        driver.get(CHECKOUT_STEP_TWO_PAGE_URL);
        return this;
    }

    @Step("Get checkout product list")
    public List<CartProduct> getCheckoutProductList() {
        List<CartProduct> checkoutProductList = PageUtilities.getCartProductList(driver.findElements(PRODUCT_BY));
        log.info("Got checkout product list: " + Arrays.toString(checkoutProductList.toArray()));
        return checkoutProductList;
    }

    @Step("Click finish checkout button")
    public CheckoutCompletePage clickFinishButton() {
        log.info("Clicked finish button " + finishButton);
        finishButton.click();
        return new CheckoutCompletePage(driver);
    }
}
