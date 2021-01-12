package tests.cart_tests.footer_test;

import org.testng.annotations.BeforeMethod;
import pages.cart_page.CartPage;
import tests.abstract_tests.AbstractFooterTest;

import static tests.GlobalValues.OPEN_PAGE_REDUCED_TIMEOUT;

public class CartFooterTest extends AbstractFooterTest {
    @BeforeMethod
    public void initPage() {
        footerSubpage = new CartPage(getDriver());
        inventoryPage.clickCartLink().waitForPageLoaded(OPEN_PAGE_REDUCED_TIMEOUT);
    }
}
