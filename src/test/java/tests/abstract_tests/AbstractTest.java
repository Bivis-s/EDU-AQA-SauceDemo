package tests.abstract_tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractTest {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public void initTest() {
        driver = DriverManagerFactory.getDriverManager(DriverType.CHROME);
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void endTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}
