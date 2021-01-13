package tests.login_tests.login_test;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.abstract_tests.OpenLoginPageBeforeTest;
import tests.login_tests.values.LoginTestValues;

import static tests.GlobalValues.*;
import static tests.login_tests.values.LoginTestValues.*;

public class LoginTest extends OpenLoginPageBeforeTest {
    private final int OPEN_TIMEOUT = OPEN_PAGE_REDUCED_TIMEOUT;

    @Test(groups = {"positive_tests", "login_tests"})
    public void inputFieldsEnabledTest() {
        loginPage.open(OPEN_TIMEOUT);
        Assert.assertTrue(loginPage.isUsernameFieldEnabled());
        Assert.assertTrue(loginPage.isPasswordFieldEnabled());
    }

    @Test(groups = {"positive_tests", "login_tests"})
    public void loginButtonEnabledTest() {
        loginPage.open(OPEN_TIMEOUT);
        Assert.assertTrue(loginPage.isLoginButtonEnabled());
    }

    @Test(groups = {"positive_tests", "login_tests"})
    public void loginPicVisibleTest() {
        loginPage.open(OPEN_TIMEOUT);
        Assert.assertTrue(loginPage.isLoginPicVisible());
    }

    @Test(groups = {"positive_tests", "login_tests"})
    public void loginViaStandardUserTest() {
        loginPage.open(OPEN_TIMEOUT);
        Assert.assertTrue(loginPage.login(STANDARD_USER_USERNAME, STANDARD_USER_PASSWORD)
                .getInventoryPage().isPageOpened());
    }

    @Test(groups = {"positive_tests", "login_tests"})
    public void loginViaPerformanceGlitchUserTest() {
        loginPage.open(OPEN_TIMEOUT);
        Assert.assertTrue(loginPage.login(PERFORMANCE_GLITCH_USER_USERNAME, PERFORMANCE_GLITCH_USER_PASSWORD)
                .getInventoryPage().isPageOpened());
    }

    @Test(groups = {"positive_tests", "login_tests"})
    public void loginViaProblemUserTest() {
        loginPage.open(OPEN_TIMEOUT);
        Assert.assertTrue(loginPage.login(PROBLEM_USER_USERNAME, PROBLEM_USER_PASSWORD)
                .getInventoryPage().isPageOpened());
    }

    @Test(groups = {"negative_tests", "login_tests"},
            dataProvider = "invalidLoginData", dataProviderClass = LoginTestValues.class)
    public void errorAfterLoginWithInvalidDataTest(String username, String password, String errorMessage) {
        loginPage.open(OPEN_TIMEOUT).login(username, password);
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertEquals(loginPage.getLoginErrorMessage(), errorMessage);
    }

    @Test(groups = {"negative_tests", "login_tests"})
    public void closeLoginErrorTest() {
        loginPage.open(OPEN_TIMEOUT).login(LOCKED_USER_USERNAME, LOCKED_USER_PASSWORD);
        Assert.assertTrue(loginPage.isErrorDisplayed());
        loginPage.closeLoginError();
        Assert.assertFalse(loginPage.isErrorDisplayed());
    }
}
