package tests.abstract_tests;

import org.testng.annotations.BeforeMethod;
import pages.login_page.LoginPage;

public abstract class OpenLoginPageBeforeTest extends AbstractTest {
    protected LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        loginPage = new LoginPage(getDriver());
    }
}
