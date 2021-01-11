package tests.cart_tests.cart_test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.cart_page.CartPage;
import pages.cart_page.CartProduct;
import pages.inventory_page.InventoryProduct;
import tests.inventory_tests.InventoryPreTest;
import utilities.Utilities;

import java.util.List;

import static tests.GlobalValues.EMPTY_INT_VALUE;
import static tests.cart_tests.values.CartTestValues.*;

public class CartTest extends InventoryPreTest {
    @Test
    public void cartSubtitleTest() {
        Assert.assertEquals((inventoryPage.clickCartLink()).getSubtitleText(), CART_SUBTITLE_TEXT);
    }

    private void addAllProductsToCart(List<InventoryProduct> invProdList) {
        for (InventoryProduct ip : invProdList) {
            ip.addToCart();
        }
    }

    @Test
    public void inventoryAndCartProductEqualsTest() {
        List<InventoryProduct> inventoryProductList = inventoryPage.getInventoryProductList();
        addAllProductsToCart(inventoryProductList);
        List<CartProduct> cartProductList = inventoryPage.clickCartLink().getCartProductList();
        Assert.assertTrue(Utilities.equalsProductLists(
                Utilities.transformInventoryProductToAbstractProductList(inventoryProductList),
                Utilities.transformCartProductToAbstractProductList(cartProductList)));
    }

    @Test
    public void cartProductCountValidTest() {
        List<InventoryProduct> inventoryProductList = inventoryPage.getInventoryProductList();
        addAllProductsToCart(inventoryProductList);
        Assert.assertEquals(inventoryPage.clickCartLink().getCartProductList().size(), CART_ALL_PRODUCT_COUNT);
    }

    @Test
    public void cartProductsCountInCartValidTest() {
        List<InventoryProduct> inventoryProductList = inventoryPage.getInventoryProductList();
        addAllProductsToCart(inventoryProductList);
        for (CartProduct cartProduct : inventoryPage.clickCartLink().getCartProductList()) {
            Assert.assertEquals(cartProduct.getQuantity(), CART_PRODUCT_QUANTITY);
        }
    }

    @Test
    public void cartProductRemovingTest() {
        List<InventoryProduct> inventoryProductList = inventoryPage.getInventoryProductList();
        addAllProductsToCart(inventoryProductList);
        CartPage cartPage = inventoryPage.clickCartLink();
        for (CartProduct cartProduct : cartPage.getCartProductList()) {
            cartProduct.removeFromCart();
        }
        Assert.assertEquals(cartPage.getCartProductList().size(), EMPTY_INT_VALUE);
    }
}
