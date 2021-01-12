package tests.inventory_tests.inventory_test;

import org.testng.Assert;
import org.testng.annotations.Test;
import products.InventoryProduct;
import tests.abstract_tests.LogInAndGetInventoryBeforeTest;

import static tests.inventory_tests.values.InventoryTestValues.*;

public class InventoryTest extends LogInAndGetInventoryBeforeTest {
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
        Assert.assertEquals(inventoryPage.getInventoryProductList().size(), PRODUCT_COUNT);
    }

    @Test
    public void productPicsVisibleTest() {
        for (InventoryProduct product : inventoryPage.getInventoryProductList()) {
            Assert.assertTrue(product.getPicUrl().endsWith(VALID_PICTURE_EXTENSION));
        }
    }

    @Test
    public void productPricesVisibleTest() {
        for (InventoryProduct product : inventoryPage.getInventoryProductList()) {
            Assert.assertTrue(TEXT_VALID_EXPRESSION.apply(product.getPriceText()));
        }
    }

    @Test
    public void productPricesValidTest() {
        for (InventoryProduct product : inventoryPage.getInventoryProductList()) {
            Assert.assertTrue(PRICE_VALID_EXPRESSION.apply(product.getPriceValue()));
        }
    }

    @Test
    public void productNamesValidTest() {
        for (InventoryProduct product : inventoryPage.getInventoryProductList()) {
            Assert.assertTrue(TEXT_VALID_EXPRESSION.apply(product.getNameText()));
        }
    }

    @Test
    public void productAddToCartButtonEnabledTest() {
        for (InventoryProduct product : inventoryPage.getInventoryProductList()) {
            Assert.assertTrue(product.isAddToCartButtonEnabled());
        }
    }

    @Test
    public void productAddToCartButtonClickTest() {
        InventoryProduct product = inventoryPage.getInventoryProductList().get(0);
        Assert.assertTrue(product.isAddToCartButtonEnabled());
        product.addToCart();
        Assert.assertFalse(product.isAddToCartButtonEnabled());
    }

    @Test
    public void inventoryCartCounterTest() {
        for (InventoryProduct product : inventoryPage.getInventoryProductList()) {
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
