package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
public abstract class AbstractPage {
    protected static final String URL = "https://www.saucedemo.com/";
    private final WebDriverWait webDriverWait;
    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        int timeout = 10;
        log.trace("Created new Webdriver wait, " + timeout);
        webDriverWait = new WebDriverWait(driver, timeout);
    }

    public WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }

    public abstract AbstractPage waitForPageLoaded();

    public abstract AbstractPage openPage();

    public abstract boolean isPageOpened();
}
