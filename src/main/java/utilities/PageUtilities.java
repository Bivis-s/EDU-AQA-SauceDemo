package utilities;

import org.openqa.selenium.WebElement;
import products.CartProduct;

import java.util.ArrayList;
import java.util.List;

public class PageUtilities {
    public static List<CartProduct> getCartProductList(List<WebElement> webElementList) {
        List<CartProduct> cartProductList = new ArrayList<>();
        for (WebElement productElement : webElementList) {
            cartProductList.add(new CartProduct(productElement));
        }
        return cartProductList;
    }
}
