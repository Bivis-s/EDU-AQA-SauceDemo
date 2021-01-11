package tests.inventory_tests.sort_test.comparators;

import pages.inventory_page.Product;

import java.util.Comparator;

import static tests.inventory_tests.sort_test.InventorySortTestValues.PRICES_LOHI_COMPARE_EXPRESSION;

public class ProductPriceLowToHighComparator implements Comparator<Product> {
    @Override
    public int compare(Product a, Product b) {
        return PRICES_LOHI_COMPARE_EXPRESSION.compare(a.getPriceValue(), b.getPriceValue());
    }
}
