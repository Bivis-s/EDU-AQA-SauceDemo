package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected static final String URL = "https://www.saucedemo.com/";
    private final WebDriverWait webDriverWait;

    public WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        webDriverWait = new WebDriverWait(driver, 10);
    }

    public abstract AbstractPage waitForPageLoaded();

    public abstract AbstractPage openPage();

    public abstract boolean isPageOpened();
}
