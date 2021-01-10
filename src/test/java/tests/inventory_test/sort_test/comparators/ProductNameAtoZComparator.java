package tests.inventory_test.sort_test.comparators;

import pages.inventory_page.Product;

import java.util.Comparator;

public class ProductNameAtoZComparator implements Comparator<Product> {
    @Override
    public int compare(Product a, Product b) {
        return a.getNameText().compareTo(b.getNameText());
    }
}
