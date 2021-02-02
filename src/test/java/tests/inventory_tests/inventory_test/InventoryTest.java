package tests.inventory_tests.inventory_test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.inventory_page.InventoryPage;
import products.InventoryProduct;
import tests.abstract_tests.AbstractTest;

import static tests.inventory_tests.values.InventoryTestValues.*;

public class InventoryTest extends AbstractTest {
    private InventoryPage inventoryPage;

    @BeforeMethod(description = "Get inventory page", alwaysRun = true)
    public void getInventoryPage() {
        inventoryPage = steps.loginViaStandardData();
    }

    @Test(description = "Check if subtitle equals expected value", groups = {"positive_tests", "inventory_tests"})
    public void inventorySubtitleTest() {
        Assert.assertEquals(inventoryPage.getSubtitleText(), SUBTITLE);
    }

    @Test(description = "Check if subtitle pic visible (robot pic)", groups = {"positive_tests", "inventory_tests"})
    public void inventorySubtitlePicVisibleTest() {
        Assert.assertEquals(inventoryPage.getSubtitlePicBackgroundAttribute(), SUBTITLE_PIC_BACKGROUND_ATTRIBUTE);
    }

    @Test(description = "Check if products visible", groups = {"positive_tests", "inventory_tests"})
    public void productsVisibleTest() {
        Assert.assertEquals(inventoryPage.getInventoryProductList().size(), PRODUCT_COUNT);
    }

    @Test(description = "Check if product pictures visible", groups = {"positive_tests", "inventory_tests"})
    public void productPicsVisibleTest() {
        for (InventoryProduct product : inventoryPage.getInventoryProductList()) {
            Assert.assertTrue(product.getPicUrl().endsWith(VALID_PICTURE_EXTENSION));
        }
    }

    @Test(description = "Check if product prices visible", groups = {"positive_tests", "inventory_tests"})
    public void productPricesVisibleTest() {
        for (InventoryProduct product : inventoryPage.getInventoryProductList()) {
            Assert.assertTrue(TEXT_VALID_EXPRESSION.apply(product.getPriceText()));
        }
    }

    @Test(description = "Check if product prices valid", groups = {"positive_tests", "inventory_tests"})
    public void productPricesValidTest() {
        for (InventoryProduct product : inventoryPage.getInventoryProductList()) {
            Assert.assertTrue(PRICE_VALID_EXPRESSION.apply(product.getPriceValue()));
        }
    }

    @Test(description = "Check if product names visible", groups = {"positive_tests", "inventory_tests"})
    public void productNamesValidTest() {
        for (InventoryProduct product : inventoryPage.getInventoryProductList()) {
            Assert.assertTrue(TEXT_VALID_EXPRESSION.apply(product.getNameText()));
        }
    }

    @Test(description = "Check if product add to cart button available to submit",
            groups = {"positive_tests", "inventory_tests"})
    public void productAddToCartButtonEnabledTest() {
        for (InventoryProduct product : inventoryPage.getInventoryProductList()) {
            Assert.assertTrue(product.isAddToCartButtonEnabled());
        }
    }

    @Test(description = "Check if product add to cart button makes grey after click",
            groups = {"positive_tests", "inventory_tests"})
    public void productAddToCartButtonClickTest() {
        InventoryProduct product = inventoryPage.getInventoryProductList().get(0);
        product.addToCart();
        Assert.assertFalse(product.isAddToCartButtonEnabled());
    }

    @Test(description = "Check if inventory cart counter default invisible",
            groups = {"positive_tests", "inventory_tests"})
    public void inventoryCartCounterDefaultTest() {
        Assert.assertFalse(inventoryPage.isCartCounterVisible(), "Cart-counter default visible");
    }

    @Test(description = "Check if inventory cart counter increases after clicking add to cart",
            groups = {"positive_tests", "inventory_tests"})
    public void inventoryCartCounterTest() {
        for (InventoryProduct product : inventoryPage.getInventoryProductList()) {
            product.addToCart();
            Assert.assertEquals(inventoryPage.getCartCounterCount(), CART_COUNTER_COUNT,
                    "Incorrect cart-counter counting. Product: " + product.toString());
            product.removeFromCart();
        }
    }
}
