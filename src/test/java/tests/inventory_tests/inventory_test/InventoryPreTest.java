package tests.inventory_tests.inventory_test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import pages.inventory_page.InventoryPage;
import pages.login_page.LoginPage;
import tests.abstract_test.AbstractTest;

import static tests.GlobalValues.*;

public class InventoryPreTest extends AbstractTest {
    protected InventoryPage inventoryPage;

    @BeforeMethod
    public void login() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open(OPEN_PAGE_REDUCED_TIMEOUT).
                login(STANDARD_USER_USERNAME, STANDARD_USER_PASSWORD);

        inventoryPage = new InventoryPage(getDriver());
        if (!inventoryPage.waitForPageLoaded(OPEN_PAGE_STANDARD_TIMEOUT).isPageOpened()) {
            Assert.fail("Inventory page has not been loaded");
        }
    }
}
