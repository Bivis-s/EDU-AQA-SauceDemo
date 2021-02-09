package pages.checkout_page;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.footer_page.FooterPage;

@Log4j2
public abstract class AbstractCheckoutPage extends FooterPage {
    private final By SUBTITLE = By.xpath("//*[contains(@class, 'subheader')]");

    public AbstractCheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Open checkout page")
    public boolean isPageOpened() {
        boolean isPageOpened = driver.findElement(SUBTITLE).isDisplayed();
        String className = this.getClass().getName();
        if (isPageOpened) {
            log.info(className+ " opened, subtitle: " + SUBTITLE);
        } else {
            log.error(className+ " not opened");
        }
        return isPageOpened;
    }

    @Step("Get checkout subtitle")
    protected WebElement getSubtitle() {
        log.info("Finding subtitle element, xpath: " + SUBTITLE);
        return driver.findElement(SUBTITLE);
    }

    @Step("Get checkout subtitle text")
    public String getSubtitleText() {
        String subtitleText = getSubtitle().getText();
        log.info("Getting subtitle text: " + subtitleText);
        return subtitleText;
    }
}
