package pages.cart_page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.AbstractProduct;

public class CartProduct extends AbstractProduct {
    private final By NAME_BY = By.xpath(".//*[contains(@class,'inventory_item_name')]");
    private final By DESCRIPTION_BY = By.xpath(".//*[contains(@class,'inventory_item_desc')]");
    private final By PRICE_BY = By.xpath(".//*[contains(@class,'item_pricebar')]");
    private final By QUANTITY_BY = By.xpath(".//*[contains(@class,'cart_quantity')]");
    private final By REMOVE_BUTTON_BY =
            By.xpath(".//*[contains(@class,'cart_button') and contains(text(), 'REMOVE')]");

    public CartProduct(WebElement product) {
        super(product);
        init(this, NAME_BY, DESCRIPTION_BY, PRICE_BY);
    }

    public int getQuantity() {
        return Integer.parseInt(product.findElement(QUANTITY_BY).getText());
    }

    public void removeFromCart() {
        product.findElement(REMOVE_BUTTON_BY).click();
    }


}
