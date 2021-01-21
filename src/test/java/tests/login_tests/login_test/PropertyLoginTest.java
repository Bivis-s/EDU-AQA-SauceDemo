package tests.login_tests.login_test;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.abstract_tests.OpenLoginPageBeforeTest;

public class PropertyLoginTest extends OpenLoginPageBeforeTest {
    @Test
    public void loginViaPropertyData() {
        loginPage.openPage();
        boolean isPageOpened = loginPage
                .login(System.getProperty("login"), System.getProperty("password"))
                .getInventoryPage()
                .isPageOpened();
        Assert.assertTrue(isPageOpened);
    }
}
