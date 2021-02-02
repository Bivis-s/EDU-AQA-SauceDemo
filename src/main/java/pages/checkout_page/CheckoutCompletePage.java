package pages.checkout_page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        getWebDriverWait().until(ExpectedConditions.visibilityOf(getSubtitle()));
        return this;
    }

    @Override
    @Step("Open checkout-complete page")
    public CheckoutCompletePage openPage() {
        driver.get(CHECKOUT_COMPLETE_PAGE_URL);
        return this;
    }

    @Step("Get checkout complete text")
    public String getCompleteText() {
        return completeText.getText();
    }

    @Step("Is complete picture displayed")
    public boolean isCompletePicDisplayed() {
        return completeImg.isDisplayed();
    }
}
