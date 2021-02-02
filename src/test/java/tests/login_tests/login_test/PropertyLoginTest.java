package tests.login_tests.login_test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login_page.LoginPage;
import tests.abstract_tests.AbstractTest;

public class PropertyLoginTest extends AbstractTest {
    @Test(enabled = false)
    public void loginViaPropertyData() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.openPage();
        boolean isPageOpened = loginPage
                .login(System.getProperty("login"), System.getProperty("password"))
                .isPageOpened();
        Assert.assertTrue(isPageOpened);
    }
}
