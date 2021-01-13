package tests.cart_tests.cart_test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.cart_page.CartPage;
import products.CartProduct;
import products.InventoryProduct;
import tests.abstract_tests.LogInAndGetInventoryBeforeTest;
import utilities.TestUtilities;

import java.util.List;

import static tests.GlobalValues.*;
import static tests.cart_tests.values.CartTestValues.*;

public class CartTest extends LogInAndGetInventoryBeforeTest {

    @Test(groups = {"positive_tests", "cart_tests"})
    public void cartSubtitleTest() {
        Assert.assertEquals((inventoryPage.clickCartLink()).getSubtitleText(), CART_SUBTITLE_TEXT);
    }

    @Test(groups = {"positive_tests", "cart_tests"})
    public void addAllProductsToCartTest() {
        List<InventoryProduct> inventoryProductList = inventoryPage.getInventoryProductList();
        TestUtilities.addAllProductsToCart(inventoryProductList);
        List<CartProduct> cartProductList = inventoryPage.clickCartLink().getCartProductList();
        TestUtilities.assertProductListEquals(
                TestUtilities.transformInventoryProductToAbstractProductList.apply(inventoryProductList),
                TestUtilities.transformCartProductToAbstractProductList.apply(cartProductList));
    }

    @Test(groups = {"positive_tests", "cart_tests"})
    public void cartProductCountValidTest() {
        List<InventoryProduct> inventoryProductList = inventoryPage.getInventoryProductList();
        TestUtilities.addAllProductsToCart(inventoryProductList);
        Assert.assertEquals(inventoryPage.clickCartLink().getCartProductList().size(), CART_ALL_PRODUCT_COUNT);
    }

    @Test(groups = {"positive_tests", "cart_tests"})
    public void cartProductsCountInCartValidTest() {
        List<InventoryProduct> inventoryProductList = inventoryPage.getInventoryProductList();
        TestUtilities.addAllProductsToCart(inventoryProductList);
        for (CartProduct cartProduct : inventoryPage.clickCartLink().getCartProductList()) {
            Assert.assertEquals(cartProduct.getQuantity(), CART_PRODUCT_QUANTITY);
        }
    }

    @Test(groups = {"positive_tests", "cart_tests"})
    public void cartProductRemovingTest() {
        List<InventoryProduct> inventoryProductList = inventoryPage.getInventoryProductList();
        TestUtilities.addAllProductsToCart(inventoryProductList);
        CartPage cartPage = inventoryPage.clickCartLink();
        for (CartProduct cartProduct : cartPage.getCartProductList()) {
            cartProduct.removeFromCart();
        }
        Assert.assertEquals(cartPage.getCartProductList().size(), EMPTY_INT_VALUE);
    }

    @Test(groups = {"positive_tests", "cart_tests"})
    public void continueButtonTest() {
        CartPage cartPage = inventoryPage.clickCartLink().waitForPageLoaded(OPEN_PAGE_REDUCED_TIMEOUT);
        Assert.assertTrue(cartPage.isPageOpened());
        Assert.assertTrue(cartPage.continueShopping().waitForPageLoaded(OPEN_PAGE_STANDARD_TIMEOUT).isPageOpened());
    }

    @Test(groups = {"positive_tests", "cart_tests"})
    public void continueShoppingTest() {
        List<InventoryProduct> inventoryProductList;

        inventoryProductList = inventoryPage.getInventoryProductList();
        inventoryProductList.get(0).addToCart();
        CartPage cartPage = inventoryPage.clickCartLink();
        Assert.assertTrue(TestUtilities.equalProducts(inventoryProductList.get(0), cartPage.getCartProductList().get(0)));

        cartPage.continueShopping().waitForPageLoaded(OPEN_PAGE_STANDARD_TIMEOUT);
        inventoryProductList = inventoryPage.getInventoryProductList();
        inventoryProductList.get(1).addToCart();
        inventoryPage.clickCartLink();
        Assert.assertTrue(TestUtilities.equalProducts(inventoryProductList.get(1), cartPage.getCartProductList().get(1)));
    }

    @Test(groups = {"positive_tests", "cart_tests"})
    public void checkoutButtonEnabledTest() {
        Assert.assertTrue(inventoryPage.clickCartLink().waitForPageLoaded(OPEN_PAGE_REDUCED_TIMEOUT)
                .isCheckoutButtonEnabled());
    }
}
