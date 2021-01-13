package tests.checkout_tests.checkout_test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.cart_page.CartPage;
import pages.checkout_page.CheckoutCompletePage;
import pages.checkout_page.CheckoutStepOnePage;
import pages.checkout_page.CheckoutStepTwoPage;
import products.InventoryProduct;
import tests.abstract_tests.LogInAndGetInventoryBeforeTest;
import tests.checkout_tests.values.CheckoutValues;
import utilities.TestUtilities;

import java.util.List;

import static tests.GlobalValues.EMPTY_STRING_VALUE;
import static tests.GlobalValues.OPEN_PAGE_REDUCED_TIMEOUT;
import static tests.checkout_tests.values.CheckoutValues.*;

public class CheckoutTest extends LogInAndGetInventoryBeforeTest {
    @Test(groups = {"positive_tests", "checkout_tests"})
    public void checkoutInformationSubtitleTest() {
        Assert.assertEquals(TestUtilities.addOneProductGetCheckoutStepOnePage(inventoryPage).getSubtitleText(),
                CHECKOUT_INFORMATION_SUBTITLE_TEXT);
    }

    @Test(groups = {"positive_tests", "checkout_tests"})
    public void checkoutOverviewSubtitleTest() {
        Assert.assertEquals(TestUtilities.addOneProductGetCheckoutStepTwoPage(inventoryPage,
                VALID_FIRSTNAME, VALID_LASTNAME, VALID_ZIP).getSubtitleText(),
                CHECKOUT_OVERVIEW_SUBTITLE_TEXT);
    }

    @Test(groups = {"positive_tests", "checkout_tests"})
    public void checkoutFinishSubtitleTest() {
        Assert.assertEquals(TestUtilities.addOneProductGetCheckoutCompletePage(inventoryPage,
                VALID_FIRSTNAME, VALID_LASTNAME, VALID_ZIP).getSubtitleText(),
                CHECKOUT_FINISH_SUBTITLE_TEXT);
    }

    @Test(groups = {"positive_tests", "checkout_tests"})
    public void cancelButtonRedirectTest() {
        CartPage cartPage = inventoryPage
                .clickCartLink()
                .checkout()
                .cancel()
                .waitForPageLoaded(OPEN_PAGE_REDUCED_TIMEOUT);
        Assert.assertTrue(cartPage.isPageOpened());
    }

    @Test(groups = {"positive_tests", "checkout_tests"})
    public void inputFieldsEnabledTests() {
        inventoryPage.getInventoryProductList().get(0).addToCart();
        CheckoutStepOnePage page = inventoryPage.clickCartLink().checkout();
        Assert.assertTrue(page.isFirstNameFieldEnabled());
        Assert.assertTrue(page.isLastNameFieldEnabled());
        Assert.assertTrue(page.isZipFieldEnabled());
    }

    @Test(groups = {"positive_tests", "checkout_tests"})
    public void continueCheckoutWithValidDataTest() {
        CheckoutStepTwoPage checkoutStepTwoPage = TestUtilities.addOneProductGetCheckoutStepTwoPage(inventoryPage,
                VALID_FIRSTNAME, VALID_LASTNAME, VALID_ZIP);
        Assert.assertTrue(checkoutStepTwoPage.isPageOpened());
    }

    @Test(description = "Checks if products on inventory page and on step-two-checkout page equals",
            groups = {"positive_tests", "checkout_tests"})
    public void checkoutStepTwoProductNamesTest() {
        List<InventoryProduct> inventoryProductList = inventoryPage.getInventoryProductList();
        TestUtilities.addAllProductsToCart(inventoryProductList);
        CheckoutStepTwoPage checkoutStepTwoPage = inventoryPage
                .clickCartLink()
                .checkout()
                .waitForPageLoaded(OPEN_PAGE_REDUCED_TIMEOUT)
                .setFirstName(VALID_FIRSTNAME)
                .setLastName(VALID_LASTNAME)
                .setZip(VALID_ZIP)
                .next()
                .waitForPageLoaded(OPEN_PAGE_REDUCED_TIMEOUT);
        TestUtilities.assertProductListEquals(TestUtilities.transformCartProductToAbstractProductList
                        .apply(checkoutStepTwoPage.getCheckoutProductList()),
                TestUtilities.transformInventoryProductToAbstractProductList
                        .apply(inventoryProductList));
    }

    @Test(groups = {"positive_tests", "checkout_tests"})
    public void finishButtonRedirectTest() {
        CheckoutStepTwoPage checkoutStepTwoPage = TestUtilities.addOneProductGetCheckoutStepTwoPage(inventoryPage,
                VALID_FIRSTNAME, VALID_LASTNAME, VALID_ZIP);
        CheckoutCompletePage checkoutCompletePage =
                checkoutStepTwoPage.finish().waitForPageLoaded(OPEN_PAGE_REDUCED_TIMEOUT);
        Assert.assertTrue(checkoutCompletePage.isPageOpened());
    }

    @Test(groups = {"positive_tests", "checkout_tests"})
    public void checkoutFinishDeliveryPicVisibleTest() {
        Assert.assertTrue(TestUtilities.addOneProductGetCheckoutStepTwoPage(inventoryPage,
                VALID_FIRSTNAME, VALID_LASTNAME, VALID_ZIP)
                .finish()
                .waitForPageLoaded(OPEN_PAGE_REDUCED_TIMEOUT)
                .isCompletePicDisplayed());
    }

    @Test(groups = {"positive_tests", "checkout_tests"})
    public void checkoutFinishMessageTest() {
        Assert.assertEquals(TestUtilities.addOneProductGetCheckoutCompletePage(inventoryPage,
                VALID_FIRSTNAME, VALID_LASTNAME, VALID_ZIP)
                .getCompleteText(), COMPLETE_TEXT);
    }

    @Test(groups = {"negative_tests", "checkout_tests"},
            dataProvider = "invalidCheckoutData", dataProviderClass = CheckoutValues.class)
    public void errorAfterCheckoutWithWithInvalidDataTest(String firstName, String lastName, String zip, String errorMessage) {
        CheckoutStepOnePage checkoutStepOnePage = TestUtilities.addOneProductGetCheckoutStepOnePage(inventoryPage);
        Assert.assertFalse(checkoutStepOnePage.isErrorDisplayed());
        checkoutStepOnePage
                .setFirstName(firstName)
                .setLastName(lastName)
                .setZip(zip)
                .next();
        Assert.assertTrue(checkoutStepOnePage.isErrorDisplayed());
        Assert.assertEquals(checkoutStepOnePage.getErrorText(), errorMessage);
    }

    @Test(description = "[ALWAYS FAILS] checks if error shows after checkint out no products",
            groups = {"negative_tests", "checkout_tests"})
    public void checkoutZeroProducts() {
        Assert.assertTrue(inventoryPage
                .clickCartLink()
                .checkout()
                .isErrorDisplayed());
    }

    @Test(groups = {"negative_tests", "checkout_tests"})
    public void closeCheckoutErrorTest() {
        CheckoutStepOnePage checkoutStepOnePage = TestUtilities.addOneProductGetCheckoutStepOnePage(inventoryPage);
        checkoutStepOnePage
                .setFirstName(EMPTY_STRING_VALUE)
                .setLastName(EMPTY_STRING_VALUE)
                .setZip(EMPTY_STRING_VALUE)
                .next();
        Assert.assertTrue(checkoutStepOnePage.isErrorDisplayed());
        checkoutStepOnePage.closeError();
        Assert.assertFalse(checkoutStepOnePage.isErrorDisplayed());
    }
}
