package tests.inventory_tests.sort_test.comparators;

import pages.inventory_page.Product;

import java.util.Comparator;

import static tests.inventory_tests.sort_test.InventorySortTestValues.NAMES_COMPARE_EXPRESSION;

public class ProductNameAToZComparator implements Comparator<Product> {
    @Override
    public int compare(Product a, Product b) {
        return NAMES_COMPARE_EXPRESSION.compare(a.getNameText(), b.getNameText());
    }
}
