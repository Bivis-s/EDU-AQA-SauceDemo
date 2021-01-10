package tests.inventory_test.sort_test.comparators;

import pages.inventory_page.Product;

import java.util.Comparator;

public class ProductPriceLowToHighComparator implements Comparator<Product> {
    @Override
    public int compare(Product a, Product b) {
        return Double.compare(a.getPriceValue(), b.getPriceValue());
    }
}
