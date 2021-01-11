package tests.inventory_tests.sort_test.comparators;

import pages.inventory_page.InventoryProduct;

import java.util.Comparator;

import static tests.inventory_tests.values.InventorySortTestValues.NAMES_COMPARE_EXPRESSION;

public class ProductNameAToZComparator implements Comparator<InventoryProduct> {
    @Override
    public int compare(InventoryProduct a, InventoryProduct b) {
        return NAMES_COMPARE_EXPRESSION.compare(a.getNameText(), b.getNameText());
    }
}
