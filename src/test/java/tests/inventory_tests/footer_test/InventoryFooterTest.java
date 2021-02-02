package tests.inventory_tests.footer_test;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.footer_page.FooterPage;
import pages.inventory_page.InventoryPage;
import tests.abstract_tests.AbstractTest;

import static tests.GlobalValues.EMPTY_STRING_VALUE;
import static tests.inventory_tests.values.InventoryFooterValues.SOCIAL_MEDIA_LIST;

public class InventoryFooterTest extends AbstractTest {
    private FooterPage footerPage;

    @BeforeMethod(description = "Init footer page", alwaysRun = true)
    public void initPage() {
        footerPage = new InventoryPage(getDriver());
        footerPage.openPage();
    }

    @Test(description = "Check if footer copy text visible", groups = {"positive_tests", "footer_tests"})
    public void footerTextCopyVisibleTest() {
        Assert.assertNotEquals(footerPage.getCopyText(), EMPTY_STRING_VALUE);
    }

    @Test(description = "Check if footer picture visible", groups = {"positive_tests", "footer_tests"})
    public void footerPicPathValidTest() {
        Assert.assertTrue(footerPage.isPicVisible());
    }

    @Test(description = "Checks social media list", groups = {"positive_tests", "footer_tests"})
    @Description("Gets sorted social media list and compare it with expected media list")
    public void socialMediaListTest() {
        Assert.assertEquals(footerPage.getSocialTextList(), SOCIAL_MEDIA_LIST);
    }
}
