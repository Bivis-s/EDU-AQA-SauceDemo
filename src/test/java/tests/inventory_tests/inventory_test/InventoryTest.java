package tests.inventory_tests.inventory_test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.inventory_page.Product;
import tests.inventory_tests.InventoryPreTest;

import static tests.inventory_tests.inventory_test.InventoryTestValues.*;

public class InventoryTest extends InventoryPreTest {
    @Test
    public void inventorySubtitleTest() {
        Assert.assertEquals(inventoryPage.getSubtitleText(), SUBTITLE);
    }

    @Test
    public void inventorySubtitlePicVisibleTest() {
        Assert.assertEquals(inventoryPage.getSubtitlePicBackgroundAttribute(), SUBTITLE_PIC_BACKGROUND_ATTRIBUTE);
    }

    @Test
    public void productsVisibleTest() {
        Assert.assertEquals(inventoryPage.getProductList().size(), PRODUCT_COUNT);
    }

    @Test
    public void productPicsVisibleTest() {
        for (Product product : inventoryPage.getProductList()) {
            Assert.assertTrue(product.getPicUrl().endsWith(VALID_PICTURE_EXTENSION));
        }
    }

    @Test
    public void productPricesVisibleTest() {
        for (Product product : inventoryPage.getProductList()) {
            Assert.assertTrue(IS_STRING_NOT_EMPTY_EXPRESSION.execute(product.getPriceText()));
        }
    }

    @Test
    public void productPricesValidTest() {
        for (Product product : inventoryPage.getProductList()) {
            Assert.assertTrue(IS_DOUBLE_NOT_EMPTY_EXPRESSION.execute(product.getPriceValue()));
        }
    }

    @Test
    public void productNamesValidTest() {
        for (Product product : inventoryPage.getProductList()) {
            Assert.assertTrue(IS_STRING_NOT_EMPTY_EXPRESSION.execute(product.getNameText()));
        }
    }

    @Test
    public void productAddToCartButtonEnabledTest() {
        for (Product product : inventoryPage.getProductList()) {
            Assert.assertTrue(product.isAddToCartButtonEnabled());
        }
    }

    @Test
    public void productAddToCartButtonClickTest() {
        Product product = inventoryPage.getProductList().get(0);
        Assert.assertTrue(product.isAddToCartButtonEnabled());
        product.addToCart();
        Assert.assertFalse(product.isAddToCartButtonEnabled());
    }

    @Test
    public void inventoryCartCounterTest() {
        for (Product product : inventoryPage.getProductList()) {
            Assert.assertFalse(inventoryPage.isCartCounterVisible(), "Cart-counter default visible");
            product.addToCart();
            Assert.assertEquals(inventoryPage.getCartCounterCount(), CART_COUNTER_COUNT,
                    "Incorrect cart-counter counting. Product: " + product.toString());
            product.removeFromCart();
            Assert.assertFalse(inventoryPage.isCartCounterVisible(),
                    "Product " + product.toString() + " has not been removed");
        }
    }
}
