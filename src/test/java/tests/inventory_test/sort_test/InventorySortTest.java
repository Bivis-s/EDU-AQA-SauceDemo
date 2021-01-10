package tests.inventory_test.sort_test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.inventory_page.Product;
import tests.inventory_test.InventoryTest;
import tests.inventory_test.sort_test.comparators.ProductNameAtoZComparator;
import tests.inventory_test.sort_test.comparators.ProductNameZtoAComparator;
import tests.inventory_test.sort_test.comparators.ProductPriceHighToLowComparator;
import tests.inventory_test.sort_test.comparators.ProductPriceLowToHighComparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static tests.inventory_test.sort_test.InventorySortTestValues.*;

public class InventorySortTest extends InventoryTest {
    private void assertProductListEquals(List<Product> firstProductList, List<Product> secondProductList) {
        if (firstProductList.size() == secondProductList.size()) {
            for (int i = 0; i < firstProductList.size(); i++) {
                Assert.assertEquals(firstProductList.get(i).compareTo(secondProductList.get(i)), 0,
                        "index " + i + ": " + firstProductList.get(i) +
                                " not equals " + secondProductList.get(i));
            }
        } else {
            Assert.fail("Lists have different sizes");
        }
    }

    private void productSortTest(String sortType, Comparator<Product> comparator) {
        //get default product list and sort them using test-method
        List<Product> expectedProductList = inventoryPage.getProductList();
        expectedProductList.sort(comparator);
        //sort products on page using page-method
        inventoryPage.selectSort(sortType);
        List<Product> actualProductList = inventoryPage.getProductList();
        //asserting both lists
        assertProductListEquals(actualProductList, expectedProductList);
    }

    @Test
    public void productAToZSortTest() {
        productSortTest(SORT_SELECT_VALUE_A_TO_Z, new ProductNameAtoZComparator());
    }

    @Test
    public void productZToASortTest() {
        productSortTest(SORT_SELECT_VALUE_Z_TO_A, new ProductNameZtoAComparator());
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