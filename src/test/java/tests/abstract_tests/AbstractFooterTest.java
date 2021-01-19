package tests.abstract_tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.footer_page.FooterPage;

import static tests.GlobalValues.EMPTY_STRING_VALUE;
import static tests.inventory_tests.values.InventoryFooterValues.SOCIAL_MEDIA_LIST;

public abstract class AbstractFooterTest extends LogInAndGetInventoryBeforeTest {
    protected FooterPage footerPage;

    @Test(groups = {"positive_tests", "footer_tests"})
    public void footerTextCopyVisibleTest() {
        Assert.assertNotEquals(footerPage.getCopyText(), EMPTY_STRING_VALUE);
    }

    @Test(groups = {"positive_tests", "footer_tests"})
    public void footerPicPathValidTest() {
        Assert.assertTrue(footerPage.isPicVisible());
    }

    @Test(groups = {"positive_tests", "footer_tests"})
    public void socialMediaListTest() {
        Assert.assertEquals(footerPage.getSocialTextList(), SOCIAL_MEDIA_LIST);
    }
}
