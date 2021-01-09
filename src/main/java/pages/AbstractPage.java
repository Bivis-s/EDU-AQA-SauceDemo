package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected static final String URL = "https://www.saucedemo.com/";
    protected WebDriverWait webDriverWait;

    public WebDriverWait getWebDriverWait(int timeout) {
        return webDriverWait = new WebDriverWait(driver, timeout);
    }

    protected boolean isElementEnabled(WebElement element) {
        return element.isEnabled();
    }

    protected void clickElement(WebElement element) {
        element.click();
    }

    protected void sendKeys(WebElement element, String... keys) {
        element.sendKeys(keys);
    }

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public abstract AbstractPage waitForPageLoaded(int timeout);

    public abstract AbstractPage open(int timeout);

    public abstract boolean isPageOpened();
}
