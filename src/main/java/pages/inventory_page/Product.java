package pages.inventory_page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Product implements Comparable<Product>{
    private  WebElement product;
    private  WebElement name;
    private  WebElement description;
    private final By NAME_BY = By.xpath(".//*[contains(@class,'inventory_item_name')]");
    private final By DESCRIPTION_BY = By.xpath(".//*[contains(@class,'inventory_item_desc')]");
    private final By PRICE_BY = By.xpath(".//*[contains(@class,'inventory_item_price')]");
    private final By INVENTORY_ITEM_IMG_BY = By.xpath(".//*[contains(@class,'inventory_item_img')]//img");
    private final By ADD_TO_CART_BUTTON_BY =
            By.xpath(".//*[contains(@class,'btn_inventory') and contains(text(), 'ADD TO CART')]");

    public Product(WebElement product) {
        this.product = product;
        name = this.product.findElement(NAME_BY);
        description = this.product.findElement(DESCRIPTION_BY);
    }

    public String getNameText() {
        return name.getText();
    }

    public String getDescriptionText() {
        return description.getText();
    }

    public String getPriceText() {
        return product.findElement(PRICE_BY).getText();
    }

    /**
     * Removes all chars except nums and dot, converts string into double
     *
     * @return double price value
     */
    public double getPriceValue() {
        return Double.parseDouble(product.findElement(PRICE_BY).getText().replaceAll("[^.\\d]", ""));
    }

    public boolean isAddToCartButtonEnabled() {
        return product.findElement(ADD_TO_CART_BUTTON_BY).isEnabled();
    }

    public void addToCart() {
        product.findElement(ADD_TO_CART_BUTTON_BY).click();
    }

    public String getPicUrl() {
        return product.findElement(INVENTORY_ITEM_IMG_BY).getAttribute("src");
    }

    @Override
    public String toString() {
        return "Product{" +
                ", name=" + getNameText() +
                ", description=" + getDescriptionText() +
                ", price=" + getPriceText() +
                '}';
    }

    @Override
    public int compareTo(Product product) {
        if (getNameText().equals(product.getNameText()) &&
                getDescriptionText().equals(product.getDescriptionText()) &&
                getPriceText().equals(product.getPriceText())) {
            return 0;
        } else if (getPriceValue() >= product.getPriceValue()) {
            return 1;
        } else {
            return -1;
        }
    }
}
