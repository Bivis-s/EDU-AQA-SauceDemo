package pages.cart_page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.AbstractPage;
import pages.footer_subpage.FooterSubpage;
import pages.inventory_page.InventoryPage;
import pages.inventory_page.InventoryProduct;

import java.util.ArrayList;
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
        PageFactory.initElements(driver, this);
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
        List<CartProduct> cartProductList = new ArrayList<>();
        for (WebElement productElement : driver.findElements(PRODUCT_BY)) {
            cartProductList.add(new CartProduct(productElement));
        }
        return cartProductList;
    }

    public String getSubtitleText() {
        return subtitle.getText();
    }

    public InventoryPage continueShopping() {
        continueShoppingButton.click();
        return new InventoryPage(driver);
    }

    //TODO добавить checkout page
    public AbstractPage checkout() {
        return null;
    }
}
