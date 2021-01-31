package tests.cart_tests.cart_test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.cart_page.CartPage;
import pages.inventory_page.InventoryPage;
import products.CartProduct;
import products.InventoryProduct;
import tests.abstract_tests.AbstractTest;
import utilities.TestUtilities;

import java.util.List;

import static tests.GlobalValues.*;
import static tests.cart_tests.values.CartTestValues.*;

public class CartTest extends AbstractTest {
    private InventoryPage inventoryPage;

    @BeforeMethod(description = "Get inventory page", alwaysRun = true)
    public void getInventoryPage() {
        inventoryPage = steps.loginViaStandardData();
    }

    @Test(description = "Check if subtitle equals expected value", groups = {"positive_tests", "cart_tests"})
    public void cartSubtitleTest() {
        Assert.assertEquals((inventoryPage.clickCartLink()).getSubtitleText(), CART_SUBTITLE_TEXT);
    }

    @Test(description = "Check if products in inventory are same when added to cart",
            groups = {"positive_tests", "cart_tests"})
    public void addAllProductsToCartTest() {
        List<InventoryProduct> inventoryProductList = inventoryPage.getInventoryProductList();
        TestUtilities.addAllProductsToCart(inventoryProductList);
        List<CartProduct> cartProductList = inventoryPage.clickCartLink().getCartProductList();
        TestUtilities.assertProductListEquals(
                TestUtilities.transformInventoryProductToAbstractProductList.apply(inventoryProductList),
                TestUtilities.transformCartProductToAbstractProductList.apply(cartProductList));
    }

    @Test(description = "Checks if cart product count valid", groups = {"positive_tests", "cart_tests"})
    public void cartProductCountValidTest() {
        List<InventoryProduct> inventoryProductList = inventoryPage.getInventoryProductList();
        TestUtilities.addAllProductsToCart(inventoryProductList);
        Assert.assertEquals(inventoryPage.clickCartLink().getCartProductList().size(), CART_ALL_PRODUCT_COUNT);
    }

    @Test(description = "Check if cart product count valid", groups = {"positive_tests", "cart_tests"})
    public void cartProductsCountInCartValidTest() {
        List<InventoryProduct> inventoryProductList = inventoryPage.getInventoryProductList();
        TestUtilities.addAllProductsToCart(inventoryProductList);
        for (CartProduct cartProduct : inventoryPage.clickCartLink().getCartProductList()) {
            Assert.assertEquals(cartProduct.getQuantity(), CART_PRODUCT_QUANTITY);
        }
    }

    @Test(description = "Check if can remove product from cart", groups = {"positive_tests", "cart_tests"})
    public void cartProductRemovingTest() {
        List<InventoryProduct> inventoryProductList = inventoryPage.getInventoryProductList();
        TestUtilities.addAllProductsToCart(inventoryProductList);
        CartPage cartPage = inventoryPage.clickCartLink();
        for (CartProduct cartProduct : cartPage.getCartProductList()) {
            cartProduct.removeFromCart();
        }
        Assert.assertEquals(cartPage.getCartProductList().size(), EMPTY_INT_VALUE);
    }

    @Test(description = "Check if page redirected after clicking continue button",
            groups = {"positive_tests", "cart_tests"})
    public void continueButtonTest() {
        CartPage cartPage = inventoryPage.clickCartLink().waitForPageLoaded();
        Assert.assertTrue(cartPage.isPageOpened());
        Assert.assertTrue(cartPage.clickContinueShoppingButton().waitForPageLoaded().isPageOpened());
    }

    @Test(description = "Add product click continue add another product and assert",
            groups = {"positive_tests", "cart_tests"})
    public void continueShoppingTest() {
        SoftAssert softAssert = new SoftAssert();
        List<InventoryProduct> inventoryProductList;
        inventoryProductList = inventoryPage.getInventoryProductList();
        inventoryProductList.get(0).addToCart();
        CartPage cartPage = inventoryPage.clickCartLink();
        softAssert.assertTrue(TestUtilities.equalProducts(inventoryProductList.get(0), cartPage.getCartProductList().get(0)));
        cartPage.clickContinueShoppingButton().waitForPageLoaded();
        inventoryProductList = inventoryPage.getInventoryProductList();
        inventoryProductList.get(1).addToCart();
        inventoryPage.clickCartLink();
        softAssert.assertTrue(TestUtilities.equalProducts(inventoryProductList.get(1), cartPage.getCartProductList().get(1)));
        softAssert.assertAll();
    }
}
