package tests.inventory_tests.sort_test;

import org.testng.annotations.Test;
import products.InventoryProduct;
import tests.abstract_tests.LogInAndGetInventoryBeforeTest;
import utilities.comparators.ProductNameAToZComparator;
import utilities.comparators.ProductNameZToAComparator;
import utilities.comparators.ProductPriceHighToLowComparator;
import utilities.comparators.ProductPriceLowToHighComparator;
import utilities.TestUtilities;

import java.util.Comparator;
import java.util.List;

import static tests.inventory_tests.values.InventorySortTestValues.*;

public class InventorySortTest extends LogInAndGetInventoryBeforeTest {

    //TODO ADD JAVADOC
    private void productSortTest(String sortType, Comparator<InventoryProduct> comparator) {
        //get default product list and sort them using test-method
        List<InventoryProduct> expectedProductList = inventoryPage.getInventoryProductList();
        expectedProductList.sort(comparator);
        //sort products on page using page-method
        inventoryPage.selectSort(sortType);
        List<InventoryProduct> actualProductList = inventoryPage.getInventoryProductList();
        //asserting both lists
        TestUtilities.assertProductListEquals(TestUtilities.transformInventoryProductToAbstractProductList.apply(actualProductList),
                TestUtilities.transformInventoryProductToAbstractProductList.apply(expectedProductList));
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
