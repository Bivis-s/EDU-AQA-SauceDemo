package tests.inventory_test.sort_test.comparators;

import pages.inventory_page.Product;

import java.util.Comparator;

public class ProductNameZtoAComparator implements Comparator<Product> {
    @Override
    public int compare(Product a, Product b) {
        return b.getNameText().compareTo(a.getNameText());
    }
}
