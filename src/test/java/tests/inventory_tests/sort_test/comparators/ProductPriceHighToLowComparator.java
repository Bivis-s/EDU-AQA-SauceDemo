package tests.inventory_tests.sort_test.comparators;

import pages.inventory_page.Product;

import java.util.Comparator;

import static tests.inventory_tests.sort_test.InventorySortTestValues.PRICES_HILO_COMPARE_EXPRESSION;

public class ProductPriceHighToLowComparator implements Comparator<Product> {
    @Override
    public int compare(Product a, Product b) {
        return PRICES_HILO_COMPARE_EXPRESSION.compare(b.getPriceValue(), a.getPriceValue());
    }
}
