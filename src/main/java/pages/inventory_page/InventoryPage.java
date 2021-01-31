package pages.inventory_page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.cart_page.CartPage;
import pages.footer_page.FooterPage;
import products.InventoryProduct;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends FooterPage {
    protected static final String INVENTORY_PAGE_URL = URL + "inventory.html";
    @FindBy(xpath = "//*[contains(text(),'Product')]")
    private WebElement productLabel;
    @FindBy(xpath = "//*[contains(@class,'peek')]")
    private WebElement subtitlePicDiv;
    @FindBy(xpath = "//*[contains(@class,'product_sort_container')]")
    private WebElement sortSelectElement;
    @FindBy(xpath = "//*[contains(@class,'shopping_cart_link')]")
    private WebElement cartLink;
    private final By PRODUCT_BY = By.xpath("//*[contains(@class,'inventory_list')]//*[@class='inventory_item']");
    private final By CART_COUNTER_BY = By.xpath("//*[contains(@class, 'fa-layers-counter')]");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public InventoryPage waitForPageLoaded() {
        getWebDriverWait().until(ExpectedConditions.visibilityOf(productLabel));
        return this;
    }

    @Override
    @Step("Open inventory page")
    public InventoryPage openPage() {
        driver.get(INVENTORY_PAGE_URL);
        return waitForPageLoaded();
    }

    @Override
    public boolean isPageOpened() {
        return productLabel.isDisplayed();
    }

    @Step("Get subtitle text")
    public String getSubtitleText() {
        return productLabel.getText();
    }

    @Step("Get subtitle picture background attribute")
    public String getSubtitlePicBackgroundAttribute() {
        return subtitlePicDiv.getCssValue("background-image");
    }

    @Step("Get inventory products list")
    public List<InventoryProduct> getInventoryProductList() {
        List<InventoryProduct> inventoryProductList = new ArrayList<>();
        for (WebElement productElement : driver.findElements(PRODUCT_BY)) {
            inventoryProductList.add(new InventoryProduct(productElement));
        }
        return inventoryProductList;
    }

    @Step("Select sort '{value}'")
    public void selectSort(String value) {
        (new Select(sortSelectElement)).selectByValue(value);
    }

    @Step("Is cart counter visible")
    public boolean isCartCounterVisible() {
        return !driver.findElements(CART_COUNTER_BY).isEmpty();
    }

    @Step("Get cart counter count")
    public int getCartCounterCount() {
        return Integer.parseInt(driver.findElement(CART_COUNTER_BY).getText());
    }

    @Step("Click cart link")
    public CartPage clickCartLink() {
        cartLink.click();
        return new CartPage(driver);
    }
}
