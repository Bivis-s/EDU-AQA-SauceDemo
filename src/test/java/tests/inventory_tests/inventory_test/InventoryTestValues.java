package tests.inventory_tests.inventory_test;

import utilities.DoubleBooleanExpression;
import utilities.StringBooleanExpression;

import static tests.GlobalValues.EMPTY_DOUBLE_VALUE;
import static tests.GlobalValues.EMPTY_STRING_VALUE;

public class InventoryTestValues {
    public static final String SUBTITLE = "Products";
    public static final String SUBTITLE_PIC_BACKGROUND_ATTRIBUTE = "url(\"https://www.saucedemo.com/img/peek.png\")";
    public static final int PRODUCT_COUNT = 6;
    public static final String VALID_PICTURE_EXTENSION = ".jpg";
    public static final StringBooleanExpression IS_STRING_NOT_EMPTY_EXPRESSION = (String string) -> !string.equals(EMPTY_STRING_VALUE);
    public static final DoubleBooleanExpression IS_DOUBLE_NOT_EMPTY_EXPRESSION = (double d) -> d > EMPTY_DOUBLE_VALUE;
    public static final int CART_COUNTER_COUNT = 1;
}
