package tests.login_test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import tests.abstract_test.AbstractTest;

import static tests.GlobalValues.OPEN_PAGE_EXTENDED_TIMEOUT;
import static tests.GlobalValues.OPEN_PAGE_STANDARD_TIMEOUT;
import static tests.login_test.LoginTestValues.*;

public class LoginTest extends AbstractTest {
    LoginPage loginPage;

    @BeforeMethod
    public void initPage() {
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void inputFieldsEnabledTest() {
        loginPage.open(OPEN_PAGE_STANDARD_TIMEOUT);
        Assert.assertTrue(loginPage.isUsernameFieldEnabled());
        Assert.assertTrue(loginPage.isPasswordFieldEnabled());
    }

    @Test
    public void loginButtonEnabledTest() {
        loginPage.open(OPEN_PAGE_STANDARD_TIMEOUT);
        Assert.assertTrue(loginPage.isLoginButtonEnabled());
    }

    @Test
    public void loginPicVisibleTest() {
        loginPage.open(OPEN_PAGE_STANDARD_TIMEOUT);
        Assert.assertTrue(loginPage.isLoginPicVisible());
    }

    @Test
    public void loginViaStandardUserTest() {
        loginPage.open(OPEN_PAGE_STANDARD_TIMEOUT);
        Assert.assertTrue(loginPage.login(STANDARD_USER_USERNAME, STANDARD_USER_PASSWORD)
                .getInventoryPage().isPageOpened());
    }

    @Test
    public void loginViaPerformanceGlitchUserTest() {
        loginPage.open(OPEN_PAGE_EXTENDED_TIMEOUT);
        Assert.assertTrue(loginPage.login(PERFORMANCE_GLITCH_USER_USERNAME, PERFORMANCE_GLITCH_USER_PASSWORD)
                .getInventoryPage().isPageOpened());
    }

    @Test
    public void loginViaProblemUserTest() {
        loginPage.open(OPEN_PAGE_STANDARD_TIMEOUT);
        Assert.assertTrue(loginPage.login(PROBLEM_USERNAME, PROBLEM_USER_PASSWORD)
                .getInventoryPage().isPageOpened());
    }
}
