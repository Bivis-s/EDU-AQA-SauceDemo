package utilities;

import org.testng.Assert;
import pages.AbstractProduct;
import pages.cart_page.CartProduct;
import pages.inventory_page.InventoryProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Utilities {
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
        }
    }

    //TODO ADD JAVADOC
    public static Function<List<InventoryProduct>, List<AbstractProduct>>
            transformInventoryProductToAbstractProductList = ArrayList::new;

    //TODO ADD JAVADOC
    public static Function<List<CartProduct>, List<AbstractProduct>>
            transformCartProductToAbstractProductList = ArrayList::new;
}
