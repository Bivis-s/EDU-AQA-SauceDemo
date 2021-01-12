package tests.abstract_tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.footer_subpage.FooterSubpage;

import static tests.GlobalValues.EMPTY_STRING_VALUE;
import static tests.inventory_tests.values.InventoryFooterValues.SOCIAL_MEDIA_LIST;

public abstract class AbstractFooterTest extends LogInAndGetInventoryBeforeTest {
    protected FooterSubpage footerSubpage;

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
