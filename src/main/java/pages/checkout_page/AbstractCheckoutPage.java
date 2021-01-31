package pages.checkout_page;

import io.qameta.allure.Step;
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
    @Step("Open checkout page")
    public boolean isPageOpened() {
        return driver.findElement(SUBTITLE).isDisplayed();
    }

    @Step("Get checkout subtitle")
    protected WebElement getSubtitle() {
        return driver.findElement(SUBTITLE);
    }

    @Step("Get checkout subtitle text")
    public String getSubtitleText() {
        return driver.findElement(SUBTITLE).getText();
    }
}
