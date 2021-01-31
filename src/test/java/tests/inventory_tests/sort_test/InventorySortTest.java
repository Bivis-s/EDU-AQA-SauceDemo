package tests.inventory_tests.sort_test;

import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.inventory_page.InventoryPage;
import products.InventoryProduct;
import steps.SortSteps;
import tests.abstract_tests.AbstractTest;
import tests.inventory_tests.values.InventorySortTestValues;
import utilities.TestUtilities;

import java.util.Comparator;
import java.util.List;

public class InventorySortTest extends AbstractTest {
    private SortSteps sortSteps;
    private InventoryPage inventoryPage;

    @BeforeMethod(description = "Open inventory page", alwaysRun = true)
    public void getInventoryPage() {
        inventoryPage = steps.loginViaStandardData();
    }

    @Test(description = "Sort products test",
            dataProvider = "sortData", dataProviderClass = InventorySortTestValues.class,
            groups = {"positive_tests", "inventory_tests", "inventory_sort_tests"})
    @Description("Checks if page-sort works correct using inner sort-methods")
    public void productSortTest(String sortType, Comparator<InventoryProduct> comparator) {
        sortSteps = new SortSteps(getDriver(), inventoryPage);
        //get default product list and sort them using test-method
        List<InventoryProduct> expectedProductList =
                sortSteps.getInventoryProductListAndSort(inventoryPage, comparator);
        //sort products on page using page-method
        List<InventoryProduct> actualProductList = sortSteps.selectProductSortOnPage(inventoryPage, sortType);
        //asserting both lists
        TestUtilities.assertProductListEquals(TestUtilities.transformInventoryProductToAbstractProductList.apply(actualProductList),
                TestUtilities.transformInventoryProductToAbstractProductList.apply(expectedProductList));
    }
}
