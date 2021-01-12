package tests.inventory_tests.values;

import utilities.TripleExpression;

public class InventorySortTestValues {
    public static final String SORT_SELECT_VALUE_A_TO_Z = "az";
    public static final String SORT_SELECT_VALUE_Z_TO_A = "za";
    public static final String SORT_SELECT_VALUE_LOW_TO_HIGH = "lohi";
    public static final String SORT_SELECT_VALUE_HIGH_TO_LOW = "hilo";

    public static final TripleExpression<Double, Double, Integer>
            PRICES_LOHI_COMPARE_EXPRESSION = (d1, d2) -> d1 >= d2 ? 1 : -1;
    public static final TripleExpression<Double, Double, Integer>
            PRICES_HILO_COMPARE_EXPRESSION = (d1, d2) -> d1 > d2 ? 1 : -1;
    public static final TripleExpression<String, String, Integer> NAMES_COMPARE_EXPRESSION = String::compareTo;
}
