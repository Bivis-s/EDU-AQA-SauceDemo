package pages.cart_page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.checkout_page.CheckoutStepOnePage;
import pages.footer_page.FooterPage;
import pages.inventory_page.InventoryPage;
import products.CartProduct;
import utilities.PageUtilities;

import java.util.List;

public class CartPage extends FooterPage {
    protected static final String CART_PAGE_URL = URL + "cart.html";
    @FindBy(xpath = "//*[contains(@class, 'subheader')]")
    private WebElement subtitle;
    @FindBy(xpath = "//*[contains(@class, 'btn_secondary') and contains(text(),'Continue')]")
    private WebElement continueShoppingButton;
    @FindBy(xpath = "//*[contains(@class, 'btn_action checkout_button') and contains(text(),'CHECKOUT')]")
    private WebElement checkoutButton;
    private final By PRODUCT_BY = By.xpath("//*[contains(@class,'cart_list')]//*[@class='cart_item']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CartPage waitForPageLoaded() {
        getWebDriverWait().until(ExpectedConditions.visibilityOf(subtitle));
        return this;
    }

    @Override
    @Step("Open cart page")
    public CartPage openPage() {
        driver.get(CART_PAGE_URL);
        return waitForPageLoaded();
    }

    @Override
    public boolean isPageOpened() {
        return subtitle.isDisplayed();
    }

    @Step("Get cart products list")
    public List<CartProduct> getCartProductList() {
        return PageUtilities.getCartProductList(driver.findElements(PRODUCT_BY));
    }

    @Step("Get cart subtitle text")
    public String getSubtitleText() {
        return subtitle.getText();
    }

    @Step("Click continue shopping button")
    public InventoryPage clickContinueShoppingButton() {
        continueShoppingButton.click();
        return new InventoryPage(driver);
    }

    @Step("Click checkout button")
    public CheckoutStepOnePage clickCheckoutButton() {
        checkoutButton.click();
        return new CheckoutStepOnePage(driver);
    }
}
