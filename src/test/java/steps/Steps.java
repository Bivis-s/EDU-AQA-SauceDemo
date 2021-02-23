package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.inventory_page.InventoryPage;
import pages.login_page.LoginPage;

import static tests.GlobalValues.STANDARD_USER_PASSWORD;
import static tests.GlobalValues.STANDARD_USER_USERNAME;

public class Steps {
    private final WebDriver driver;

    public Steps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Login via standard user data")
    public InventoryPage loginViaStandardData() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .openPage()
                .login(STANDARD_USER_USERNAME, STANDARD_USER_PASSWORD)
                .waitForPageLoaded();
        InventoryPage inventoryPage = new InventoryPage(driver);
        if (!inventoryPage.waitForPageLoaded().isPageOpened()) {
            Assert.fail("Inventory page has not been loaded");
        }
        return inventoryPage;
    }
}
