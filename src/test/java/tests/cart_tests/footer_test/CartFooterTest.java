package tests.cart_tests.footer_test;

import org.testng.annotations.BeforeMethod;
import tests.abstract_tests.AbstractFooterTest;

public class CartFooterTest extends AbstractFooterTest {
    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        footerPage = inventoryPage.clickCartLink().waitForPageLoaded();
    }
}
