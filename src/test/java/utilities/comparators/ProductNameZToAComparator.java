package utilities.comparators;

import products.InventoryProduct;

import java.util.Comparator;

import static tests.inventory_tests.values.InventorySortTestValues.NAMES_COMPARE_EXPRESSION;

public class ProductNameZToAComparator implements Comparator<InventoryProduct> {
    @Override
    public int compare(InventoryProduct a, InventoryProduct b) {
        return NAMES_COMPARE_EXPRESSION.invoke(b.getNameText(), a.getNameText());
    }
}
