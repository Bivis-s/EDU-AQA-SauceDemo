package tests.checkout_tests.footer_test;

import org.testng.annotations.BeforeMethod;
import tests.abstract_tests.AbstractFooterTest;
import utilities.TestUtilities;

public class CheckoutFooterTest extends AbstractFooterTest {
    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        footerSubpage = TestUtilities.addOneProductGetCheckoutStepOnePage(inventoryPage);
    }
}
