package tests.inventory_tests.inventory_test;

import org.testng.Assert;
import org.testng.annotations.Test;
import products.InventoryProduct;
import tests.abstract_tests.LogInAndGetInventoryBeforeTest;

import static tests.inventory_tests.values.InventoryTestValues.*;

public class InventoryTest extends LogInAndGetInventoryBeforeTest {

    @Test(groups = {"positive_tests", "inventory_tests"})
    public void inventorySubtitleTest() {
        Assert.assertEquals(inventoryPage.getSubtitleText(), SUBTITLE);
    }

    @Test(groups = {"positive_tests", "inventory_tests"})
    public void inventorySubtitlePicVisibleTest() {
        Assert.assertEquals(inventoryPage.getSubtitlePicBackgroundAttribute(), SUBTITLE_PIC_BACKGROUND_ATTRIBUTE);
    }

    @Test(groups = {"positive_tests", "inventory_tests"})
    public void productsVisibleTest() {
        Assert.assertEquals(inventoryPage.getInventoryProductList().size(), PRODUCT_COUNT);
    }

    @Test(groups = {"positive_tests", "inventory_tests"})
    public void productPicsVisibleTest() {
        for (InventoryProduct product : inventoryPage.getInventoryProductList()) {
            Assert.assertTrue(product.getPicUrl().endsWith(VALID_PICTURE_EXTENSION));
        }
    }

    @Test(groups = {"positive_tests", "inventory_tests"})
    public void productPricesVisibleTest() {
        for (InventoryProduct product : inventoryPage.getInventoryProductList()) {
            Assert.assertTrue(TEXT_VALID_EXPRESSION.apply(product.getPriceText()));
        }
    }

    @Test(groups = {"positive_tests", "inventory_tests"})
    public void productPricesValidTest() {
        for (InventoryProduct product : inventoryPage.getInventoryProductList()) {
            Assert.assertTrue(PRICE_VALID_EXPRESSION.apply(product.getPriceValue()));
        }
    }

    @Test(groups = {"positive_tests", "inventory_tests"})
    public void productNamesValidTest() {
        for (InventoryProduct product : inventoryPage.getInventoryProductList()) {
            Assert.assertTrue(TEXT_VALID_EXPRESSION.apply(product.getNameText()));
        }
    }

    @Test(groups = {"positive_tests", "inventory_tests"})
    public void productAddToCartButtonEnabledTest() {
        for (InventoryProduct product : inventoryPage.getInventoryProductList()) {
            Assert.assertTrue(product.isAddToCartButtonEnabled());
        }
    }

    @Test(groups = {"positive_tests", "inventory_tests"})
    public void productAddToCartButtonClickTest() {
        InventoryProduct product = inventoryPage.getInventoryProductList().get(0);
        product.addToCart();
        Assert.assertFalse(product.isAddToCartButtonEnabled());
    }

    @Test(groups = {"positive_tests", "inventory_tests"})
    public void inventoryCartCounterDefaultTest() {
        Assert.assertFalse(inventoryPage.isCartCounterVisible(), "Cart-counter default visible");
    }

    @Test(groups = {"positive_tests", "inventory_tests"})
    public void inventoryCartCounterTest() {
        for (InventoryProduct product : inventoryPage.getInventoryProductList()) {
            product.addToCart();
            Assert.assertEquals(inventoryPage.getCartCounterCount(), CART_COUNTER_COUNT,
                    "Incorrect cart-counter counting. Product: " + product.toString());
            product.removeFromCart();
        }
    }
}
