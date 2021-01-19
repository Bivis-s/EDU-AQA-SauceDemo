package pages.checkout_page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.footer_page.FooterPage;

public abstract class AbstractCheckoutPage extends FooterPage {
    private final By SUBTITLE = By.xpath("//*[contains(@class, 'subheader')]");

    public AbstractCheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return driver.findElement(SUBTITLE).isDisplayed();
    }

    protected WebElement getSubtitle() {
        return driver.findElement(SUBTITLE);
    }

    public String getSubtitleText() {
        return driver.findElement(SUBTITLE).getText();
    }
}
