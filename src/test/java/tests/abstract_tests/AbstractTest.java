package tests.abstract_tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import steps.Steps;

@Listeners(ScreenshotTestListener.class)
public abstract class AbstractTest {
    private WebDriver driver;
    protected Steps steps;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public void initTest(ITestContext context) {
        driver = DriverManagerFactory.getDriverManager(DriverType.CHROME);
        driver.manage().window().maximize();
        steps = new Steps(driver);
        context.setAttribute("driver", driver);
    }

    @AfterMethod(alwaysRun = true)
    public void endTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}
