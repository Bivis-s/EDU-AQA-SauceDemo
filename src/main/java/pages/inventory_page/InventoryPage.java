package pages.inventory_page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.cart_page.CartPage;
import pages.footer_subpage.FooterSubpage;
import products.InventoryProduct;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends FooterSubpage {
    protected static final String INVENTORY_PAGE_URL = URL + "inventory.html";
    @FindBy(xpath = "//*[contains(text(),'Product')]")
    private WebElement productLabel;
    @FindBy(xpath = "//*[contains(@class,'peek')]")
    private WebElement subtitlePicDiv;
    @FindBy(xpath = "//*[contains(@class,'product_sort_container')]")
    private WebElement sortSelectElement;
    @FindBy(xpath = "//*[contains(@class,'shopping_cart_link')]")
    private WebElement cartLink;
    private final Select SORT_SELECT;
    private final By PRODUCT_BY =
            By.xpath("//*[contains(@class,'inventory_list')]//*[@class='inventory_item']");
    private final By CART_COUNTER_BY = By.xpath("//*[contains(@class, 'fa-layers-counter')]");

    public InventoryPage(WebDriver driver) {
        super(driver);
        SORT_SELECT = new Select(sortSelectElement);
    }

    @Override
    public InventoryPage waitForPageLoaded(int timeout) {
        getWebDriverWait(timeout).until(ExpectedConditions.visibilityOf(productLabel));
        return this;
    }

    @Override
    public InventoryPage open(int timeout) {
        driver.get(INVENTORY_PAGE_URL);
        return waitForPageLoaded(timeout);
    }

    @Override
    public boolean isPageOpened() {
        return productLabel.isDisplayed();
    }

    public String getSubtitleText() {
        return productLabel.getText();
    }

    public String getSubtitlePicBackgroundAttribute() {
        return subtitlePicDiv.getCssValue("background-image");
    }

    public List<InventoryProduct> getInventoryProductList() {
        List<InventoryProduct> inventoryProductList = new ArrayList<>();
        for (WebElement productElement : driver.findElements(PRODUCT_BY)) {
            inventoryProductList.add(new InventoryProduct(productElement));
        }
        return inventoryProductList;
    }

    public void selectSort(String value) {
        SORT_SELECT.selectByValue(value);
    }

    public boolean isCartCounterVisible() {
        return !driver.findElements(CART_COUNTER_BY).isEmpty();
    }

    public int getCartCounterCount() {
        return Integer.parseInt(driver.findElement(CART_COUNTER_BY).getText());
    }

    public CartPage clickCartLink() {
        cartLink.click();
        return new CartPage(driver);
    }
}
