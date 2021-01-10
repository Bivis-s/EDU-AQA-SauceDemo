package tests.inventory_test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.inventory_page.InventoryPage;
import pages.inventory_page.Product;

import static tests.GlobalValues.*;
import static tests.inventory_test.InventoryTestValues.*;

public class InventoryTest extends InventoryPreTest {
    protected InventoryPage inventoryPage;

    @BeforeMethod
    public void initPage() {
        inventoryPage = new InventoryPage(getDriver());
        if (!inventoryPage.waitForPageLoaded(OPEN_PAGE_STANDARD_TIMEOUT).isPageOpened()) {
            Assert.fail("Inventory page has not been loaded");
        }
    }

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
            Assert.assertTrue(IS_DOUBLE_BIGGER_THAN_0.execute(product.getPriceValue()));
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
}
