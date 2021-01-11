package tests.inventory_tests.footer_test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.footer_subpage.FooterSubpage;
import pages.inventory_page.InventoryPage;
import tests.inventory_tests.inventory_test.InventoryPreTest;

import static tests.GlobalValues.EMPTY_STRING_VALUE;
import static tests.inventory_tests.footer_test.InventoryFooterValues.SOCIAL_MEDIA_LIST;

public class InventoryFooterTest extends InventoryPreTest {
    protected FooterSubpage footerSubpage;

    @BeforeMethod
    public void initPage() {
        footerSubpage = new InventoryPage(getDriver());
    }

    @Test
    public void footerTextCopyVisibleTest() {
        Assert.assertNotEquals(footerSubpage.getCopyText(), EMPTY_STRING_VALUE);
    }

    @Test
    public void footerPicPathValidTest() {
        Assert.assertTrue(footerSubpage.isPicVisible());
    }

    @Test
    public void socialMediaListTest() {
        Assert.assertEquals(footerSubpage.getSocialTextList(), SOCIAL_MEDIA_LIST);
    }
}
