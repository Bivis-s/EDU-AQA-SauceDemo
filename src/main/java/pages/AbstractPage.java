package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected static final String URL = "https://www.saucedemo.com/";
    protected WebDriverWait webDriverWait;

    public WebDriverWait getWebDriverWait(int timeout) {
        return webDriverWait = new WebDriverWait(driver, timeout);
    }

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public abstract AbstractPage waitForPageLoaded(int timeout);

    public abstract AbstractPage open(int timeout);

    public abstract boolean isPageOpened();
}
