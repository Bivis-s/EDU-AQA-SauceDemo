package pages.inventory_page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.AbstractProduct;

public class InventoryProduct extends AbstractProduct {
    private final By NAME_BY = By.xpath(".//*[contains(@class,'inventory_item_name')]");
    private final By DESCRIPTION_BY = By.xpath(".//*[contains(@class,'inventory_item_desc')]");
    private final By PRICE_BY = By.xpath(".//*[contains(@class,'inventory_item_price')]");
    private final By INVENTORY_ITEM_IMG_BY = By.xpath(".//*[contains(@class,'inventory_item_img')]//img");
    private final By ADD_TO_CART_BUTTON_BY =
            By.xpath(".//*[contains(@class,'btn_inventory') and contains(text(), 'ADD TO CART')]");
    private final By REMOVE_FROM_CART_BUTTON_BY =
            By.xpath(".//*[contains(@class,'btn_inventory') and contains(text(), 'REMOVE')]");

    public InventoryProduct(WebElement product) {
        super(product);
        init(this, NAME_BY, DESCRIPTION_BY, PRICE_BY);
    }

    public boolean isAddToCartButtonEnabled() {
        return !product.findElements(ADD_TO_CART_BUTTON_BY).isEmpty();
    }

    public void addToCart() {
        product.findElement(ADD_TO_CART_BUTTON_BY).click();
    }

    public void removeFromCart() {
        product.findElement(REMOVE_FROM_CART_BUTTON_BY).click();
    }

    public String getPicUrl() {
        return product.findElement(INVENTORY_ITEM_IMG_BY).getAttribute("src");
    }
}
