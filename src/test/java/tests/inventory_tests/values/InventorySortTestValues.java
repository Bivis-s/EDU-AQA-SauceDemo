package tests.inventory_tests.values;

import utilities.TwoDoublesIntExpression;
import utilities.TwoStringsIntExpression;

public class InventorySortTestValues {
    public static final String SORT_SELECT_VALUE_A_TO_Z = "az";
    public static final String SORT_SELECT_VALUE_Z_TO_A = "za";
    public static final String SORT_SELECT_VALUE_LOW_TO_HIGH = "lohi";
    public static final String SORT_SELECT_VALUE_HIGH_TO_LOW = "hilo";

    public static final TwoDoublesIntExpression PRICES_LOHI_COMPARE_EXPRESSION = ((d1, d2) -> d1 >= d2 ? 1 : -1);
    public static final TwoDoublesIntExpression PRICES_HILO_COMPARE_EXPRESSION = ((d1, d2) -> d1 > d2 ? 1 : -1);
    public static final TwoStringsIntExpression NAMES_COMPARE_EXPRESSION = String::compareTo;
}
