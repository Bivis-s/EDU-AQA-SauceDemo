package tests.inventory_test;

import org.testng.annotations.BeforeMethod;
import pages.login_page.LoginPage;
import tests.abstract_test.AbstractTest;

import static tests.GlobalValues.*;

public class InventoryPreTest extends AbstractTest {
    @BeforeMethod
    public void login() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open(OPEN_PAGE_REDUCED_TIMEOUT).
                login(STANDARD_USER_USERNAME, STANDARD_USER_PASSWORD);
    }
}
