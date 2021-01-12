package utilities;

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

import static tests.GlobalValues.OPEN_PAGE_REDUCED_TIMEOUT;

public class TestUtilities {
    public static void addAllProductsToCart(List<InventoryProduct> invProdList) {
        for (InventoryProduct ip : invProdList) {
            ip.addToCart();
        }
    }

    public static boolean equalProducts(AbstractProduct p1, AbstractProduct p2) {
        return p1.compareTo(p2) == 0;
    }

    //TODO ADD JAVADOC
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

    //TODO ADD JAVADOC
    public static Function<List<InventoryProduct>, List<AbstractProduct>>
            transformInventoryProductToAbstractProductList = ArrayList::new;

    //TODO ADD JAVADOC
    public static Function<List<CartProduct>, List<AbstractProduct>>
            transformCartProductToAbstractProductList = ArrayList::new;

    public static CheckoutStepOnePage addOneProductGetCheckoutStepOnePage(InventoryPage inventoryPage) {
        inventoryPage.getInventoryProductList().get(0).addToCart();
        return inventoryPage
                .clickCartLink()
                .checkout();
    }

    public static CheckoutStepTwoPage addOneProductGetCheckoutStepTwoPage(InventoryPage inventoryPage, String firstname,
                                                                          String lastname, String zip) {
        return addOneProductGetCheckoutStepOnePage(inventoryPage)
                .setFirstName(firstname)
                .setLastName(lastname)
                .setZip(zip)
                .next()
                .waitForPageLoaded(OPEN_PAGE_REDUCED_TIMEOUT);
    }

    public static CheckoutCompletePage addOneProductGetCheckoutCompletePage(InventoryPage inventoryPage,
                                                                            String firstname, String lastname,
                                                                            String zip) {
        return addOneProductGetCheckoutStepTwoPage(inventoryPage, firstname, lastname, zip)
                .finish()
                .waitForPageLoaded(OPEN_PAGE_REDUCED_TIMEOUT);
    }
}