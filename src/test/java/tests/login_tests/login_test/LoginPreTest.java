package tests.login_tests.login_test;

import org.testng.annotations.BeforeMethod;
import pages.login_page.LoginPage;
import tests.abstract_tests.AbstractTest;

public class LoginPreTest extends AbstractTest {
    protected LoginPage loginPage;

    @BeforeMethod
    public void initPage() {
        loginPage = new LoginPage(getDriver());
    }
}
