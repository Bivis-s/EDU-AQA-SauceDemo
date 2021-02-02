package products;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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

    @Step("Is add product to cart button enabled")
    public boolean isAddToCartButtonEnabled() {
        return !product.findElements(ADD_TO_CART_BUTTON_BY).isEmpty();
    }

    @Step("Add product to cart")
    public void addToCart() {
        product.findElement(ADD_TO_CART_BUTTON_BY).click();
    }

    @Step("Remove product from cart")
    public void removeFromCart() {
        product.findElement(REMOVE_FROM_CART_BUTTON_BY).click();
    }

    @Step("Get product picture url")
    public String getPicUrl() {
        return product.findElement(INVENTORY_ITEM_IMG_BY).getAttribute("src");
    }
}
