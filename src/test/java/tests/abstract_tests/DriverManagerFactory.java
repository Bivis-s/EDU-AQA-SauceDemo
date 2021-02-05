package tests.abstract_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public class DriverManagerFactory {
    public static WebDriver getDriverManager(DriverType type) {
        switch (type) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(new ChromeOptions().addArguments("--incognito", "--verbose"));
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver(new FirefoxOptions().addArguments("--incognito"));
            case OPERA:
                WebDriverManager.operadriver().setup();
                return new OperaDriver(new OperaOptions().addArguments("--incognito"));
        }
        throw new InvalidArgumentException("Invalid DriverType");
    }
}
