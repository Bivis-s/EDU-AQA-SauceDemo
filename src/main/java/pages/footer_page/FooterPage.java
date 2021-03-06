package pages.footer_page;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AbstractPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
public abstract class FooterPage extends AbstractPage {
    private final By SOCIAL_LI_BY = By.xpath(".//li");
    private final By FOOTER_PIC_BY = By.xpath("//img[contains(@class,'footer_robot') and not(@style='hidden')]");
    @FindBy(xpath = "//ul[@class='social']")
    private WebElement socialUlElement;
    @FindBy(xpath = "//*[contains(@class,'footer_copy')]")
    private WebElement copyDivElement;

    public FooterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get footer social medias text list")
    public List<String> getSocialTextList() {
        List<String> socialTextList = new ArrayList<>();
        log.info("Found element list by +" + SOCIAL_LI_BY);
        List<WebElement> socialLiList = socialUlElement.findElements(SOCIAL_LI_BY);
        for (WebElement li : socialLiList) {
            socialTextList.add(li.getText());
        }
        // sort list for not to take into account the order of strings when asserting
        Collections.sort(socialTextList);
        log.info("Got sorted media text list: " + socialTextList.toString());
        return socialTextList;
    }

    @Step("Get footer copy text")
    public String getCopyText() {
        String copyText = copyDivElement.getText();
        log.info("Got copy text: " + copyText);
        return copyText;
    }

    @Step("Is footer picture visible")
    public boolean isPicVisible() {
        boolean isPictureVisible = !driver.findElements(FOOTER_PIC_BY).isEmpty();
        log.info("Is footer picture visible: " + isPictureVisible);
        return isPictureVisible;
    }
}
