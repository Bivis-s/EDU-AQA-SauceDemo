# EDU-AQA-SauceDemo
Tests for the site https://www.saucedemo.com/

**Maven commands:**
--------------

**Versions**

Scans a project dependencies and produces a report of those dependencies which have newer versions available

`mvn versions:display-dependency-updates`

```
[INFO] The following dependencies in Dependencies have newer versions:
[INFO]   io.github.bonigarcia:webdrivermanager ................. 4.2.2 -> 4.3.1
[INFO]   org.seleniumhq.selenium:selenium-java ...... 3.141.59 -> 4.0.0-alpha-7
[INFO]   org.testng:testng ..................................... 7.1.0 -> 7.3.0
```

Search the pom for all non-SNAPSHOT versions which have been a newer release and replaces them with the latest release version

`versions:use-latest-releases`

Search the pom for all versions which have been a newer version and replaces them with the latest version

`versions:use-latest-versions`

---

**Test run**

Clear target and run all test classes except property-related ones

`mvn clean "-Dtest=*Test, \!Property*" test`

Run only _negative_ tests

`mvn clean test -PPropertySuiteTest -DxmlSuite=src/test/resources/test_suites/negative_tests.xml`

Run only _positive_ tests

`mvn clean test -PPropertySuiteTest -DxmlSuite=src/test/resources/test_suites/positive_tests.xml`

Run only _InventorySortTest_ class

`mvn clean -Dtest=InventorySortTest test`
```
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 31.699 s - in tests.inventory_tests.sort_test.InventorySortTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  35.811 s
[INFO] Finished at: 2021-01-21T17:17:48+03:00
[INFO] ------------------------------------------------------------------------
```

Run only _cancelButtonRedirectTest_ from _CheckoutTest_ class

`mvn clean -Dtest=CheckoutTest#cancelButtonRedirectTest test`

Run only _cancelButtonRedirectTest_ and _finishButtonRedirectTest_ from _CheckoutTest_ class

`mvn clean -Dtest=CheckoutTest#cancelButtonRedirectTest+finishButtonRedirectTest test`

Run _loginViaPropertyData_ from _PropertyLoginTest_ with property _login_ and _password_

`mvn clean -Dtest=PropertyLoginTest#loginViaPropertyData -Dlogin=standard_user -Dpassword=secret_sauce test`
```
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 8.005 s - in tests.login_tests.login_test.PropertyLoginTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  11.815 s
[INFO] Finished at: 2021-01-21T17:15:55+03:00
[INFO] ------------------------------------------------------------------------
```

# Allure
![image](https://user-images.githubusercontent.com/66497536/106233027-9ee3ff00-6206-11eb-81cf-70bdde2443e7.png)
