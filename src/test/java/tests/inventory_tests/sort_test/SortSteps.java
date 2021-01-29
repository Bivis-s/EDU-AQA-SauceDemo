package tests.inventory_tests.sort_test;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.inventory_page.InventoryPage;
import products.InventoryProduct;

import java.util.Comparator;
import java.util.List;

public class SortSteps {
    private final WebDriver driver;
    private final InventoryPage inventoryPage;

    public SortSteps(WebDriver driver, InventoryPage inventoryPage) {
        this.driver = driver;
        this.inventoryPage = inventoryPage;
    }

    @Step("Get default product list and sort them using comparator")
    public List<InventoryProduct> getInventoryProductListAndSort(InventoryPage inventoryPage, Comparator<InventoryProduct> comparator) {
        List<InventoryProduct> expectedProductList = inventoryPage.getInventoryProductList();
        expectedProductList.sort(comparator);
        return expectedProductList;
    }

    @Step("Select product sort on page")
    public List<InventoryProduct> selectProductSortOnPage(InventoryPage inventoryPage, String sortType) {
        inventoryPage.selectSort(sortType);
        return  inventoryPage.getInventoryProductList();
    }
}
