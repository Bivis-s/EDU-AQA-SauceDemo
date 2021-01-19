package pages.checkout_page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import products.CartProduct;
import utilities.PageUtilities;

import java.util.List;

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
        getWebDriverWait().until(ExpectedConditions.visibilityOf(getSubtitle()));
        return this;
    }

    @Override
    public CheckoutStepTwoPage openPage() {
        driver.get(CHECKOUT_STEP_TWO_PAGE_URL);
        return this;
    }

    public List<CartProduct> getCheckoutProductList() {
        return PageUtilities.getCartProductList(driver.findElements(PRODUCT_BY));
    }

    public CheckoutCompletePage clickFinishButton() {
        finishButton.click();
        return new CheckoutCompletePage(driver);
    }
}
