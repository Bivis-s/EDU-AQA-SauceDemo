package tests.inventory_tests.values;

import java.util.function.Function;

import static tests.GlobalValues.EMPTY_DOUBLE_VALUE;
import static tests.GlobalValues.EMPTY_STRING_VALUE;

public class InventoryTestValues {
    public static final String SUBTITLE = "Products";
    public static final String SUBTITLE_PIC_BACKGROUND_ATTRIBUTE = "url(\"https://www.saucedemo.com/img/peek.png\")";
    public static final int PRODUCT_COUNT = 6;
    public static final String VALID_PICTURE_EXTENSION = ".jpg";
    public static final Function<String, Boolean> TEXT_VALID_EXPRESSION = (String string) -> !string.equals(EMPTY_STRING_VALUE);
    public static final Function<Double, Boolean> PRICE_VALID_EXPRESSION = (Double d) -> d > EMPTY_DOUBLE_VALUE;
    public static final int CART_COUNTER_COUNT = 1;
}
