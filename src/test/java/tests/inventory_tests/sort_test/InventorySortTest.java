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
    private void assertProductListEquals(List<AbstractProduct> productList1, List<AbstractProduct> productList2) {
        if (Utilities.equalsProductLists(productList1, productList2))
        if (productList1.size() == productList2.size()) {
            for (int i = 0; i < productList1.size(); i++) {
                Assert.assertEquals(productList1.get(i).compareTo(productList2.get(i)), 0,
                        "index " + i + ": " + productList1.get(i) +
                                " not equals " + productList2.get(i));
            }
        } else {
            Assert.fail("Lists have different sizes");
        }
    }

    //TODO ADD JAVADOC
    private void productSortTest(String sortType, Comparator<InventoryProduct> comparator) {
        //get default product list and sort them using test-method
        List<InventoryProduct> expectedProductList = inventoryPage.getInventoryProductList();
        expectedProductList.sort(comparator);
        //sort products on page using page-method
        inventoryPage.selectSort(sortType);
        List<InventoryProduct> actualProductList = inventoryPage.getInventoryProductList();
        //asserting both lists
        assertProductListEquals(Utilities.transformInventoryProductToAbstractProductList(actualProductList),
                Utilities.transformInventoryProductToAbstractProductList(expectedProductList));
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
