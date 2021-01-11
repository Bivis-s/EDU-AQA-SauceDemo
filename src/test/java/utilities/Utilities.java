package utilities;

import pages.AbstractProduct;
import pages.cart_page.CartProduct;
import pages.inventory_page.InventoryProduct;

import java.util.List;

public class Utilities {
    public static boolean equalsProducts(AbstractProduct p1, AbstractProduct p2) {
        return p1.compareTo(p2) == 0;
    }

    public static boolean equalsProductLists(List<AbstractProduct> products1, List<AbstractProduct> products2) {
        if (products1.size() == products2.size()) {
            for (int i = 0; i < products1.size(); i++) {
                if (!equalsProducts(products1.get(i), products2.get(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static List<AbstractProduct> transformInventoryProductToAbstractProductList(List<InventoryProduct> inventoryProductList) {
        ProductListTransformer productListTransformer = new ProductListTransformer<InventoryProduct, AbstractProduct>() {
            @Override
            AbstractProduct transform(InventoryProduct inventoryProduct) {
                return (AbstractProduct) inventoryProduct;
            }
        };
        return productListTransformer.transform(inventoryProductList);
    }

    public static List<AbstractProduct> transformCartProductToAbstractProductList(List<CartProduct> cartProductList) {
        ProductListTransformer productListTransformer = new ProductListTransformer<CartProduct, AbstractProduct>() {
            @Override
            AbstractProduct transform(CartProduct cartProduct) {
                return (AbstractProduct) cartProduct;
            }
        };
        return productListTransformer.transform(cartProductList);
    }
}
