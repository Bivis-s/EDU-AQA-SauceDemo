package tests.checkout_tests.values;

import org.testng.annotations.DataProvider;

import static tests.GlobalValues.EMPTY_STRING_VALUE;

public class CheckoutValues {
    public static final String CHECKOUT_INFORMATION_SUBTITLE_TEXT = "Checkout: Your Information";
    public static final String CHECKOUT_OVERVIEW_SUBTITLE_TEXT = "Checkout: Overview";
    public static final String CHECKOUT_FINISH_SUBTITLE_TEXT = "Finish";
    public static final String VALID_FIRSTNAME = "John";
    public static final String VALID_LASTNAME = "Lennon";
    public static final String VALID_ZIP = "102150";
    public static final String COMPLETE_TEXT =
            "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

    @DataProvider(name = "invalidCheckoutData")
    public Object[][] provideInvalidCheckoutData() {
        return new Object[][]{
                {EMPTY_STRING_VALUE, EMPTY_STRING_VALUE, EMPTY_STRING_VALUE, "Error: First Name is required"},
                {"BigBaby", "", "", "Error: Last Name is required"},
                {"BigBaby", "Tape", "", "Error: Postal Code is required"},
                //[ALWAYS FAILS] checks if error shows after submitting invalid zip field
                {"BigBaby", "Tape", "NotANumber", "Error: Invalid Postal Code"}
        };
    }
}
