package pages.cart_page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.checkout_page.CheckoutStepOnePage;
import pages.footer_subpage.FooterSubpage;
import pages.inventory_page.InventoryPage;
import products.CartProduct;
import utilities.PageUtilities;

import java.util.List;

public class CartPage extends FooterSubpage {
    protected static final String CART_PAGE_URL = URL + "cart.html";
    @FindBy(xpath = "//*[contains(@class, 'subheader')]")
    private WebElement subtitle;
    @FindBy(xpath = "//*[contains(@class, 'btn_secondary') and contains(text(),'Continue')]")
    private WebElement continueShoppingButton;
    @FindBy(xpath = "//*[contains(@class, 'btn_action checkout_button') and contains(text(),'CHECKOUT')]")
    private WebElement checkoutButton;
    private final By PRODUCT_BY =
            By.xpath("//*[contains(@class,'cart_list')]//*[@class='cart_item']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CartPage waitForPageLoaded(int timeout) {
        getWebDriverWait(timeout).until(ExpectedConditions.visibilityOf(subtitle));
        return this;
    }

    @Override
    public CartPage open(int timeout) {
        driver.get(CART_PAGE_URL);
        return waitForPageLoaded(timeout);
    }

    @Override
    public boolean isPageOpened() {
        return subtitle.isDisplayed();
    }

    public List<CartProduct> getCartProductList() {
        return PageUtilities.getCartProductList(driver.findElements(PRODUCT_BY));
    }

    public String getSubtitleText() {
        return subtitle.getText();
    }

    public InventoryPage continueShopping() {
        continueShoppingButton.click();
        return new InventoryPage(driver);
    }

    public boolean isCheckoutButtonEnabled() {
        return checkoutButton.isEnabled();
    }

    public CheckoutStepOnePage checkout() {
        checkoutButton.click();
        return new CheckoutStepOnePage(driver);
    }
}
