package pages.inventory_page;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.cart_page.CartPage;
import pages.footer_page.FooterPage;
import products.InventoryProduct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
public class InventoryPage extends FooterPage {
    protected static final String INVENTORY_PAGE_URL = URL + "inventory.html";
    private final By PRODUCT_BY = By.xpath("//*[contains(@class,'inventory_list')]//*[@class='inventory_item']");
    private final By CART_COUNTER_BY = By.xpath("//*[contains(@class, 'fa-layers-counter')]");
    @FindBy(xpath = "//*[contains(text(),'Product')]")
    private WebElement productLabel;
    @FindBy(xpath = "//*[contains(@class,'peek')]")
    private WebElement subtitlePicDiv;
    @FindBy(xpath = "//*[contains(@class,'product_sort_container')]")
    private WebElement sortSelectElement;
    @FindBy(xpath = "//*[contains(@class,'shopping_cart_link')]")
    private WebElement cartLink;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public InventoryPage waitForPageLoaded() {
        try {
            log.info("Inventory page was loaded");
            getWebDriverWait().until(ExpectedConditions.visibilityOf(productLabel));
        } catch (TimeoutException e) {
            log.error("Inventory page was not loaded");
            throw e;
        }
        return this;
    }

    @Override
    @Step("Open inventory page")
    public InventoryPage openPage() {
        log.info("Opened inventory page, URL: " + INVENTORY_PAGE_URL);
        driver.get(INVENTORY_PAGE_URL);
        return waitForPageLoaded();
    }

    @Override
    public boolean isPageOpened() {
        boolean isPageOpened = productLabel.isDisplayed();
        log.info("Login page is open" + isPageOpened + ", URL: " + INVENTORY_PAGE_URL);
        return isPageOpened;
    }

    @Step("Get subtitle text")
    public String getSubtitleText() {
        String subtitleText = productLabel.getText();
        log.info("Got subtitle text: " + subtitleText);
        return subtitleText;
    }

    @Step("Get subtitle picture background attribute")
    public String getSubtitlePicBackgroundAttribute() {
        String subtitlePicBackgroundAttribute = subtitlePicDiv.getCssValue("background-image");
        log.info("Got subtitle picture background: " + subtitlePicBackgroundAttribute);
        return subtitlePicBackgroundAttribute;
    }

    @Step("Get inventory products list")
    public List<InventoryProduct> getInventoryProductList() {
        List<InventoryProduct> inventoryProductList = new ArrayList<>();
        log.info("Found element list by " + PRODUCT_BY);
        for (WebElement productElement : driver.findElements(PRODUCT_BY)) {
            inventoryProductList.add(new InventoryProduct(productElement));
        }
        log.info("Got inventory product list: " + Arrays.toString(inventoryProductList.toArray()));
        return inventoryProductList;
    }

    @Step("Select sort '{value}'")
    public void selectSort(String value) {
        log.info("Selected " + value + " sort");
        (new Select(sortSelectElement)).selectByValue(value);
    }

    @Step("Is cart counter visible")
    public boolean isCartCounterVisible() {
        boolean isCarCounterVisible = !driver.findElements(CART_COUNTER_BY).isEmpty();
        log.info("Is car counter visible: " + isCarCounterVisible);
        return isCarCounterVisible;
    }

    @Step("Get cart counter count")
    public int getCartCounterCount() {
        int cartCounter = Integer.parseInt(driver.findElement(CART_COUNTER_BY).getText());
        log.info("Cart counter count: " + cartCounter);
        return cartCounter;
    }

    @Step("Click cart link")
    public CartPage clickCartLink() {
        log.info("Clicked cart link " + cartLink);
        cartLink.click();
        return new CartPage(driver).waitForPageLoaded();
    }
}
