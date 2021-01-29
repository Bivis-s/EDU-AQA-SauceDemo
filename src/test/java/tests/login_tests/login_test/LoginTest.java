package tests.login_tests.login_test;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.login_page.LoginPage;
import tests.abstract_tests.AbstractTest;
import tests.login_tests.values.LoginTestValues;

import static tests.GlobalValues.*;
import static tests.login_tests.values.LoginTestValues.*;

public class LoginTest extends AbstractTest {
    private LoginPage loginPage;

    @BeforeMethod(description = "Open login page", alwaysRun = true)
    public void initLoginPage() {
        loginPage = new LoginPage(getDriver());
        loginPage.openPage();
    }

    @Test(description = "Check if inputs fields available to write chars", groups = {"positive_tests", "login_tests"})
    public void inputFieldsEnabledTest() {
        Assert.assertTrue(loginPage.isUsernameFieldEnabled());
        Assert.assertTrue(loginPage.isPasswordFieldEnabled());
    }

    @Test(description = "Check if login button available to submit", groups = {"positive_tests", "login_tests"})
    public void loginButtonEnabledTest() {
        Assert.assertTrue(loginPage.isLoginButtonEnabled());
    }

    @Test(description = "Check if login pic visible", groups = {"positive_tests", "login_tests"})
    @Description("Is the robot pic visible on login page")
    public void loginPicVisibleTest() {
        Assert.assertTrue(loginPage.isLoginPicVisible());
    }

    @Test(description = "Check if you can login using standard user data", groups = {"positive_tests", "login_tests"})
    public void loginViaStandardUserTest() {
        Assert.assertTrue(loginPage.login(STANDARD_USER_USERNAME, STANDARD_USER_PASSWORD)
                .isPageOpened());
    }

    @Test(description = "Check if you can login using performance glitch user data",
            groups = {"positive_tests", "login_tests"})
    public void loginViaPerformanceGlitchUserTest() {
        Assert.assertTrue(loginPage.login(PERFORMANCE_GLITCH_USER_USERNAME, PERFORMANCE_GLITCH_USER_PASSWORD)
                .isPageOpened());
    }

    @Test(description = "Check if you can login using problem user data", groups = {"positive_tests", "login_tests"})
    public void loginViaProblemUserTest() {
        Assert.assertTrue(loginPage.login(PROBLEM_USER_USERNAME, PROBLEM_USER_PASSWORD)
                .isPageOpened());
    }

    @Test(description = "Check if you can login using invalid user data",
            groups = {"negative_tests", "login_tests"},
            dataProvider = "invalidLoginData", dataProviderClass = LoginTestValues.class)
    public void errorAfterLoginWithInvalidDataTest(String username, String password, String errorMessage) {
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertEquals(loginPage.getLoginErrorMessage(), errorMessage);
    }

    @Test(description = "Check if error closes after click error-button", groups = {"negative_tests", "login_tests"})
    public void closeLoginErrorTest() {
        loginPage.login(LOCKED_USER_USERNAME, LOCKED_USER_PASSWORD);
        loginPage.closeLoginError();
        Assert.assertFalse(loginPage.isErrorDisplayed());
    }
}
