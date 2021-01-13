package tests.login_tests.values;

import org.testng.annotations.DataProvider;

import static tests.GlobalValues.EMPTY_STRING_VALUE;

public class LoginTestValues {
    public static final String PROBLEM_USER_USERNAME = "problem_user";
    public static final String PROBLEM_USER_PASSWORD = "secret_sauce";

    public static final String LOCKED_USER_USERNAME = "locked_out_user";
    public static final String LOCKED_USER_PASSWORD = "secret_sauce";

    public static final String PERFORMANCE_GLITCH_USER_USERNAME = "performance_glitch_user";
    public static final String PERFORMANCE_GLITCH_USER_PASSWORD = "secret_sauce";

    public static final String INVALID_USER_USERNAME = "some_login";
    public static final String INVALID_USER_PASSWORD = "psswrd";

    @DataProvider(name = "invalidLoginData")
    public Object[][] provideInvalidLoginData() {
        return new Object[][]{
                {EMPTY_STRING_VALUE, EMPTY_STRING_VALUE, "Epic sadface: Username is required"},
                {INVALID_USER_USERNAME, EMPTY_STRING_VALUE, "Epic sadface: Password is required"},
                {INVALID_USER_USERNAME, INVALID_USER_PASSWORD,
                        "Epic sadface: Username and password do not match any user in this service"},
                {LOCKED_USER_USERNAME, LOCKED_USER_PASSWORD, "Epic sadface: Sorry, this user has been locked out."}
        };
    }
}
