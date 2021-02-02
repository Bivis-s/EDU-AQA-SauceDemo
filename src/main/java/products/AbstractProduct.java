package products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class AbstractProduct implements Comparable<AbstractProduct>{
    protected WebElement product;
    protected WebElement name;
    protected WebElement description;
    protected WebElement price;
    protected String nameText;
    protected String descriptionText;
    protected double priceValue;

    public AbstractProduct(WebElement product) {
        this.product = product;
    }

    protected void init(AbstractProduct aProduct, By NAME_BY, By DESCRIPTION_BY, By PRICE_BY) {
        aProduct.name = aProduct.product.findElement(NAME_BY);
        aProduct.description = aProduct.product.findElement(DESCRIPTION_BY);
        aProduct.price = aProduct.product.findElement(PRICE_BY);
        nameText = name.getText();
        descriptionText = description.getText();
        priceValue = Double.parseDouble(price.getText().replaceAll("[^.\\d]", ""));
    }

    public String getNameText() {
        return nameText;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public String getPriceText() {
        return price.getText();
    }

    public double getPriceValue() {
        return priceValue;
    }

    @Override
    public int compareTo(AbstractProduct product) {
        if (getNameText().equals(product.getNameText()) &&
                getDescriptionText().equals(product.getDescriptionText()) &&
                getPriceValue() == product.getPriceValue()) {
            return 0;
        } else if (getPriceValue() >= product.getPriceValue()) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                ", name=" + getNameText() +
                ", description=" + getDescriptionText() +
                ", price=" + getPriceValue() +
                '}';
    }
}
