package tests.inventory_test;

import utilities.IDoubleEquals;
import utilities.IStringEquals;

public class InventoryTestValues {
    public static final String SUBTITLE = "Products";
    public static final String SUBTITLE_PIC_BACKGROUND_ATTRIBUTE = "url(\"https://www.saucedemo.com/img/peek.png\")";
    public static final int PRODUCT_COUNT = 6;
    public static final String VALID_PICTURE_EXTENSION = ".jpg";
    public static final IStringEquals IS_STRING_NOT_EMPTY_EXPRESSION = (String string) -> !string.equals("");
    public static final IDoubleEquals IS_DOUBLE_BIGGER_THAN_0 = (double d) -> d > 0;
}
