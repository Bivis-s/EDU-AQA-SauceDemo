package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InventoryPage extends AbstractPage {
    protected static final String INVENTORY_PAGE_URL = URL + "inventory.html";
    @FindBy(xpath = "//*[contains(text(),'Product')]")
    private WebElement productLabel;

    public InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public AbstractPage waitForPageLoaded(int timeout) {
        getWebDriverWait(timeout).until(ExpectedConditions.visibilityOf(productLabel));
        return this;
    }

    @Override
    public AbstractPage open(int timeout) {
        driver.get(INVENTORY_PAGE_URL);
        return waitForPageLoaded(timeout);
    }

    @Override
    public boolean isPageOpened() {
        return productLabel.isDisplayed();
    }
}
