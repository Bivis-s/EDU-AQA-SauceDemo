package tests.inventory_tests.footer_test;

import org.testng.annotations.BeforeMethod;
import pages.inventory_page.InventoryPage;
import tests.abstract_tests.AbstractFooterTest;

public class InventoryFooterTest extends AbstractFooterTest {
    @BeforeMethod
    public void initPage() {
        footerSubpage = new InventoryPage(getDriver());
    }
}
