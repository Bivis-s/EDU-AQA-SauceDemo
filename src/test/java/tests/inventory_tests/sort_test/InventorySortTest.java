package tests.inventory_tests.sort_test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AbstractProduct;
import pages.inventory_page.InventoryProduct;
import tests.inventory_tests.InventoryPreTest;
import tests.inventory_tests.sort_test.comparators.ProductNameAToZComparator;
import tests.inventory_tests.sort_test.comparators.ProductNameZToAComparator;
import tests.inventory_tests.sort_test.comparators.ProductPriceHighToLowComparator;
import tests.inventory_tests.sort_test.comparators.ProductPriceLowToHighComparator;
import utilities.Utilities;

import java.util.Comparator;
import java.util.List;

import static tests.inventory_tests.values.InventorySortTestValues.*;

public class InventorySortTest extends InventoryPreTest {

    //TODO ADD JAVADOC
    private void productSortTest(String sortType, Comparator<InventoryProduct> comparator) {
        //get default product list and sort them using test-method
        List<InventoryProduct> expectedProductList = inventoryPage.getInventoryProductList();
        expectedProductList.sort(comparator);
        //sort products on page using page-method
        inventoryPage.selectSort(sortType);
        List<InventoryProduct> actualProductList = inventoryPage.getInventoryProductList();
        //asserting both lists
        Utilities.assertProductListEquals(Utilities.transformInventoryProductToAbstractProductList.apply(actualProductList),
                Utilities.transformInventoryProductToAbstractProductList.apply(expectedProductList));
    }

    @Test
    public void productAToZSortTest() {
        productSortTest(SORT_SELECT_VALUE_A_TO_Z, new ProductNameAToZComparator());
    }

    @Test
    public void productZToASortTest() {
        productSortTest(SORT_SELECT_VALUE_Z_TO_A, new ProductNameZToAComparator());
    }

    @Test
    public void productLowToHighSortTest() {
        productSortTest(SORT_SELECT_VALUE_LOW_TO_HIGH, new ProductPriceLowToHighComparator());
    }

    @Test
    public void productHighToLowSortTest() {
        productSortTest(SORT_SELECT_VALUE_HIGH_TO_LOW, new ProductPriceHighToLowComparator());
    }
}
