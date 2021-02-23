package utilities;

import io.qameta.allure.Step;
import org.testng.Assert;
import pages.checkout_page.CheckoutCompletePage;
import pages.checkout_page.CheckoutStepOnePage;
import pages.checkout_page.CheckoutStepTwoPage;
import pages.inventory_page.InventoryPage;
import products.AbstractProduct;
import products.CartProduct;
import products.InventoryProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TestUtilities {
    /**
     * Transforms List<InventoryProduct> to List<AbstractProduct>> for later comparison
     */
    public static Function<List<InventoryProduct>, List<AbstractProduct>>
            transformInventoryProductToAbstractProductList = ArrayList::new;
    /**
     * Transforms List<CartProduct> to List<AbstractProduct>> for later comparison
     */
    public static Function<List<CartProduct>, List<AbstractProduct>>
            transformCartProductToAbstractProductList = ArrayList::new;

    public static void addAllProductsToCart(List<InventoryProduct> invProdList) {
        for (InventoryProduct ip : invProdList) {
            ip.addToCart();
        }
    }

    @Step("Equal two products '{p1}' and '{p2}'")
    public static boolean equalProducts(AbstractProduct p1, AbstractProduct p2) {
        return p1.compareTo(p2) == 0;
    }

    /**
     * Takes two List<AbstractProduct>
     * Asserts List's sizes, if not equals fails with message about different sizes
     * Asserts comparison of each AbstractProduct pares (using inner overridden .compareTo()),
     * if not equals fails with message about failed pair comparison
     *
     * @param productList1 List<AbstractProduct>
     * @param productList2 List<AbstractProduct>
     */
    public static void assertProductListEquals(List<AbstractProduct> productList1, List<AbstractProduct> productList2) {
        if (productList1.size() == productList2.size()) {
            for (int i = 0; i < productList1.size(); i++) {
                Assert.assertEquals(productList1.get(i).compareTo(productList2.get(i)), 0,
                        "index " + i + ": " + productList1.get(i) +
                                " not equals " + productList2.get(i));
            }
        } else {
            Assert.fail("Lists have different sizes");
        }
    }

    @Step("Add one product and get checkout step-one")
    public static CheckoutStepOnePage addOneProductGetCheckoutStepOnePage(InventoryPage inventoryPage) {
        inventoryPage.getInventoryProductList().get(0).addToCart();
        return inventoryPage
                .clickCartLink()
                .clickCheckoutButton();
    }

    @Step("Add one product and get checkout step-two")
    public static CheckoutStepTwoPage addOneProductGetCheckoutStepTwoPage(InventoryPage inventoryPage, String firstname,
                                                                          String lastname, String zip) {
        return addOneProductGetCheckoutStepOnePage(inventoryPage)
                .setFirstName(firstname)
                .setLastName(lastname)
                .setZip(zip)
                .clickContinueButton()
                .waitForPageLoaded();
    }

    @Step("Add one product and get checkout comlete page")
    public static CheckoutCompletePage addOneProductGetCheckoutCompletePage(InventoryPage inventoryPage,
                                                                            String firstname, String lastname,
                                                                            String zip) {
        return addOneProductGetCheckoutStepTwoPage(inventoryPage, firstname, lastname, zip)
                .clickFinishButton()
                .waitForPageLoaded();
    }
}
