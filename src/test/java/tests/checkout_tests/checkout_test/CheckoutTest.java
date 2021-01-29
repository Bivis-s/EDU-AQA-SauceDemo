package tests.checkout_tests.checkout_test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.cart_page.CartPage;
import pages.checkout_page.CheckoutCompletePage;
import pages.checkout_page.CheckoutStepOnePage;
import pages.checkout_page.CheckoutStepTwoPage;
import pages.inventory_page.InventoryPage;
import products.InventoryProduct;
import tests.abstract_tests.AbstractTest;
import tests.checkout_tests.values.CheckoutValues;
import utilities.TestUtilities;

import java.util.List;

import static tests.GlobalValues.EMPTY_STRING_VALUE;
import static tests.checkout_tests.values.CheckoutValues.*;

public class CheckoutTest extends AbstractTest {
    private InventoryPage inventoryPage;

    @BeforeMethod(description = "Get inventory page", alwaysRun = true)
    public void getInventoryPage() {
        inventoryPage = steps.loginViaStandardData();
    }

    @Test(description = "Check if subtitle equals expected value", groups = {"positive_tests", "checkout_tests"})
    public void checkoutInformationSubtitleTest() {
        Assert.assertEquals(TestUtilities.addOneProductGetCheckoutStepOnePage(inventoryPage).getSubtitleText(),
                CHECKOUT_INFORMATION_SUBTITLE_TEXT);
    }

    @Test(description = "Check if subtitle equals expected value", groups = {"positive_tests", "checkout_tests"})
    public void checkoutOverviewSubtitleTest() {
        Assert.assertEquals(TestUtilities.addOneProductGetCheckoutStepTwoPage(inventoryPage,
                VALID_FIRSTNAME, VALID_LASTNAME, VALID_ZIP).getSubtitleText(),
                CHECKOUT_OVERVIEW_SUBTITLE_TEXT);
    }

    @Test(description = "Check if subtitle equals expected value", groups = {"positive_tests", "checkout_tests"})
    public void checkoutFinishSubtitleTest() {
        Assert.assertEquals(TestUtilities.addOneProductGetCheckoutCompletePage(inventoryPage,
                VALID_FIRSTNAME, VALID_LASTNAME, VALID_ZIP).getSubtitleText(),
                CHECKOUT_FINISH_SUBTITLE_TEXT);
    }

    @Test(description = "Check if page redirected after clicking continue button",
            groups = {"positive_tests", "checkout_tests"})
    public void cancelButtonRedirectTest() {
        CartPage cartPage = inventoryPage
                .clickCartLink()
                .clickCheckoutButton()
                .clickCancelButton()
                .waitForPageLoaded();
        Assert.assertTrue(cartPage.isPageOpened());
    }

    @Test(description = "Checks if inputs fields available to write chars",
            groups = {"positive_tests", "checkout_tests"})
    public void inputFieldsEnabledTests() {
        inventoryPage.getInventoryProductList().get(0).addToCart();
        CheckoutStepOnePage page = inventoryPage.clickCartLink().clickCheckoutButton();
        Assert.assertTrue(page.isFirstNameFieldEnabled());
        Assert.assertTrue(page.isLastNameFieldEnabled());
        Assert.assertTrue(page.isZipFieldEnabled());
    }

    @Test(description = "Check if redirects to checkout step two page", groups = {"positive_tests", "checkout_tests"})
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
                .clickCheckoutButton()
                .waitForPageLoaded()
                .setFirstName(VALID_FIRSTNAME)
                .setLastName(VALID_LASTNAME)
                .setZip(VALID_ZIP)
                .clickContinueButton()
                .waitForPageLoaded();
        TestUtilities.assertProductListEquals(TestUtilities.transformCartProductToAbstractProductList
                        .apply(checkoutStepTwoPage.getCheckoutProductList()),
                TestUtilities.transformInventoryProductToAbstractProductList
                        .apply(inventoryProductList));
    }

    @Test(description = "Check if page redirected after clicking finish button",
            groups = {"positive_tests", "checkout_tests"})
    public void finishButtonRedirectTest() {
        CheckoutStepTwoPage checkoutStepTwoPage = TestUtilities.addOneProductGetCheckoutStepTwoPage(inventoryPage,
                VALID_FIRSTNAME, VALID_LASTNAME, VALID_ZIP);
        CheckoutCompletePage checkoutCompletePage =
                checkoutStepTwoPage.clickFinishButton().waitForPageLoaded();
        Assert.assertTrue(checkoutCompletePage.isPageOpened());
    }

    @Test(description = "Check if checkout finish delivery picture visible", groups = {"positive_tests", "checkout_tests"})
    public void checkoutFinishDeliveryPicVisibleTest() {
        Assert.assertTrue(TestUtilities.addOneProductGetCheckoutStepTwoPage(inventoryPage,
                VALID_FIRSTNAME, VALID_LASTNAME, VALID_ZIP)
                .clickFinishButton()
                .waitForPageLoaded()
                .isCompletePicDisplayed());
    }

    @Test(description = "Check if checkout finish message equals value", groups = {"positive_tests", "checkout_tests"})
    public void checkoutFinishMessageTest() {
        Assert.assertEquals(TestUtilities.addOneProductGetCheckoutCompletePage(inventoryPage,
                VALID_FIRSTNAME, VALID_LASTNAME, VALID_ZIP)
                .getCompleteText(), COMPLETE_TEXT);
    }

    @Test(description = "Check if error shows after submiting invalid data",
            groups = {"negative_tests", "checkout_tests"},
            dataProvider = "invalidCheckoutData", dataProviderClass = CheckoutValues.class)
    public void errorAfterCheckoutWithWithInvalidDataTest(String firstName, String lastName, String zip, String errorMessage) {
        CheckoutStepOnePage checkoutStepOnePage = TestUtilities.addOneProductGetCheckoutStepOnePage(inventoryPage);
        Assert.assertFalse(checkoutStepOnePage.isErrorDisplayed());
        checkoutStepOnePage
                .setFirstName(firstName)
                .setLastName(lastName)
                .setZip(zip)
                .clickContinueButton();
        if (checkoutStepOnePage.isErrorDisplayed()) {
            Assert.assertEquals(checkoutStepOnePage.getErrorText(), errorMessage);
        } else {
            Assert.fail("Error is not displayed");
        }
    }

    @Test(description = "[ALWAYS FAILS] checks if error shows after checking out no products",
            groups = {"negative_tests", "checkout_tests"})
    public void checkoutZeroProducts() {
        Assert.assertTrue(inventoryPage
                .clickCartLink()
                .clickCheckoutButton()
                .isErrorDisplayed(), "checking out no products is valid");
    }

    @Test(description = "Check if error closes after error-button click", groups = {"negative_tests", "checkout_tests"})
    public void closeCheckoutErrorTest() {
        CheckoutStepOnePage checkoutStepOnePage = TestUtilities.addOneProductGetCheckoutStepOnePage(inventoryPage);
        checkoutStepOnePage
                .setFirstName(EMPTY_STRING_VALUE)
                .setLastName(EMPTY_STRING_VALUE)
                .setZip(EMPTY_STRING_VALUE)
                .clickContinueButton();
        Assert.assertTrue(checkoutStepOnePage.isErrorDisplayed());
        checkoutStepOnePage.clickCloseErrorButton();
        Assert.assertFalse(checkoutStepOnePage.isErrorDisplayed());
    }
}
