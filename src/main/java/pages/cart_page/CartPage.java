package pages.cart_page;

import org.openqa.selenium.WebDriver;
import pages.AbstractPage;
import pages.footer_subpage.FooterSubpage;

public class CartPage extends FooterSubpage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public AbstractPage waitForPageLoaded(int timeout) {
        return null;
    }

    @Override
    public AbstractPage open(int timeout) {
        return null;
    }

    @Override
    public boolean isPageOpened() {
        return false;
    }
}
