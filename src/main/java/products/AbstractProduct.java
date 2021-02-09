package products;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Log4j2
public abstract class AbstractProduct implements Comparable<AbstractProduct> {
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

    protected void init(By NAME_BY, By DESCRIPTION_BY, By PRICE_BY) {
        name = product.findElement(NAME_BY);
        description = product.findElement(DESCRIPTION_BY);
        price = product.findElement(PRICE_BY);
        nameText = name.getText();
        descriptionText = description.getText();
        priceValue = Double.parseDouble(price.getText().replaceAll("[^.\\d]", ""));
        log.debug(String.format("Init " + this.getClass().getName() + " product with params: Name: %s; Description: %s; Price: %s; NameText: %s; DescriptionText: %s; PriceValue: %s;",
                name, description, price, nameText, descriptionText, priceValue));
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
        int result;
        if (getNameText().equals(product.getNameText()) &&
                getDescriptionText().equals(product.getDescriptionText()) &&
                getPriceValue() == product.getPriceValue()) {
            result = 0;
        } else if (getPriceValue() >= product.getPriceValue()) {
            result = 1;
        } else {
            result = -1;
        }
        log.debug("Comparing product " + this.toString() + " and " + product.toString() + " result: " + result);
        return result;
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
