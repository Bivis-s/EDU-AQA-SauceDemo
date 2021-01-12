package tests.inventory_tests.sort_test.comparators;

import pages.inventory_page.InventoryProduct;

import java.util.Comparator;

import static tests.inventory_tests.values.InventorySortTestValues.PRICES_LOHI_COMPARE_EXPRESSION;

public class ProductPriceLowToHighComparator implements Comparator<InventoryProduct> {
    @Override
    public int compare(InventoryProduct a, InventoryProduct b) {
        return PRICES_LOHI_COMPARE_EXPRESSION.invoke(a.getPriceValue(), b.getPriceValue());
    }
}
