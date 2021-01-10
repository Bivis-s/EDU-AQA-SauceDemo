package pages.inventory_page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.AbstractPage;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends AbstractPage {
    protected static final String INVENTORY_PAGE_URL = URL + "inventory.html";
    @FindBy(xpath = "//*[contains(text(),'Product')]")
    private WebElement productLabel;
    @FindBy(xpath = "//*[contains(@class,'peek')]")
    private WebElement subtitlePicDiv;
    @FindBy(xpath = "//*[contains(@class,'product_sort_container')]")
    private WebElement sortSelectElement;
    private Select sortSelect;
    private final By PRODUCT_BY =
            By.xpath("//*[contains(@class,'inventory_list')]//*[@class='inventory_item']");

    public InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        sortSelect = new Select(sortSelectElement);
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

    public List<Product> getProductList() {
        List<Product> productList = new ArrayList<>();
        for (WebElement productElement : driver.findElements(PRODUCT_BY)) {
            productList.add(new Product(productElement));
        }
        return productList;
    }

    public InventoryPage selectSort(String value) {
        sortSelect.selectByValue(value);
        return this;
    }
}
