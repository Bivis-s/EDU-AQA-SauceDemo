package pages.footer_subpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AbstractPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class FooterSubpage extends AbstractPage {
    @FindBy(xpath = "//ul[@class='social']")
    private WebElement socialUlElement;
    private final By SOCIAL_LI_BY = By.xpath(".//li");
    @FindBy(xpath = "//*[contains(@class,'footer_copy')]")
    private WebElement copyDivElement;
    private final By FOOTER_PIC_BY = By.xpath("//img[contains(@class,'footer_robot') and not(@style='hidden')]");

    public FooterSubpage(WebDriver driver) {
        super(driver);
    }

    public List<String> getSocialTextList() {
        List<String> socialTextList = new ArrayList<>();
        List<WebElement> socialLiList = socialUlElement.findElements(SOCIAL_LI_BY);
        for (WebElement li : socialLiList) {
            socialTextList.add(li.getText());
        }
        // sort list for not to take into account the order of strings when asserting
        Collections.sort(socialTextList);
        return socialTextList;
    }

    public String getCopyText() {
        return copyDivElement.getText();
    }

    public boolean isPicVisible() {
        return !driver.findElements(FOOTER_PIC_BY).isEmpty();
    }
}
