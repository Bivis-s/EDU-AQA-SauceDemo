package pages.cart_page;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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

@Log4j2
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
        try {
            getWebDriverWait().until(ExpectedConditions.visibilityOf(subtitle));
        } catch (TimeoutException e) {
            log.error("Cart page was not loaded");
            throw e;
        }
        return this;
    }

    @Override
    @Step("Open cart page")
    public CartPage openPage() {
        driver.get(CART_PAGE_URL);
        log.info("Opening cart page, URL: " + CART_PAGE_URL);
        return waitForPageLoaded();
    }

    @Override
    public boolean isPageOpened() {
        boolean isPageOpened = subtitle.isDisplayed();
        if (isPageOpened) {
            log.info("Cart page is open, URL: " + CART_PAGE_URL);
        } else {
            log.error("Cart page was not open, URL: " + CART_PAGE_URL);
        }
        return isPageOpened;
    }

    @Step("Get cart products list")
    public List<CartProduct> getCartProductList() {
        List<CartProduct> getCartProductList = PageUtilities.getCartProductList(driver.findElements(PRODUCT_BY));
        log.info("Getting cart product list: " + getCartProductList);
        return getCartProductList;
    }

    @Step("Get cart subtitle text")
    public String getSubtitleText() {
        String subtitleText = subtitle.getText();
        log.info("Getting subtitle text: " + subtitleText);
        return subtitleText;
    }

    @Step("Click continue shopping button")
    public InventoryPage clickContinueShoppingButton() {
        continueShoppingButton.click();
        log.info("Clicking continue shopping button " + continueShoppingButton);
        return new InventoryPage(driver);
    }

    @Step("Click checkout button")
    public CheckoutStepOnePage clickCheckoutButton() {
        checkoutButton.click();
        log.info("Clicking checkout button " + checkoutButton);
        return new CheckoutStepOnePage(driver).waitForPageLoaded();
    }
}
