package pages.checkout_page;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CheckoutCompletePage extends AbstractCheckoutPage {
    public static final String CHECKOUT_COMPLETE_PAGE_URL = URL + "checkout-complete.html";
    @FindBy(className = "complete-text")
    private WebElement completeText;
    @FindBy(xpath = "//img[@class='pony_express']")
    private WebElement completeImg;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CheckoutCompletePage waitForPageLoaded() {
        try {
            getWebDriverWait().until(ExpectedConditions.visibilityOf(getSubtitle()));
        } catch (TimeoutException e) {
            log.error("Checkout complete page was not loaded");
            throw e;
        }
        return this;
    }

    @Override
    @Step("Open checkout-complete page")
    public CheckoutCompletePage openPage() {
        log.info("Opened checkout complete page, URL: " + CHECKOUT_COMPLETE_PAGE_URL);
        driver.get(CHECKOUT_COMPLETE_PAGE_URL);
        return this;
    }

    @Step("Get checkout complete text")
    public String getCompleteText() {
        String checkoutCompeteText = completeText.getText();
        log.info("Getting complete text: " + checkoutCompeteText);
        return checkoutCompeteText;
    }

    @Step("Is complete picture displayed")
    public boolean isCompletePicDisplayed() {
        boolean isCompletePicDisplayed = completeImg.isDisplayed();
        log.info("Is complete picture displayed: " + isCompletePicDisplayed);
        return isCompletePicDisplayed;
    }
}
