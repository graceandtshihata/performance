package views;

import helpers.Config;
import helpers.TestHelper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClientAppHomePage extends Page {
    private static final By PRESENTATION_BUTTON = By.xpath("//a[contains(@class,'nav-button presentation-button ember-view')]");
    private static final By TOGGLE_VIDEO_BUTTON = By.xpath("//a[@class='nav-button zoom-video-button']");
    private static final By ASK_A_QUESTION_BUTTON = By.xpath("//a[@class='nav-button ask-a-question-button ember-view']");

    public ClientAppHomePage(final WebDriver driver) {
        super.driver = driver;
        waitForElement(PRESENTATION_BUTTON);
        waitForElement(SLIDE_IMAGE);
    }


    public void clickOnAnswer(int answerId, String answerLabel) {
        click(By.xpath(ANSWER.replace("XXXX", answerId + "").replace("YYYY", answerLabel)));
        delay(500);
    }

    public void switchToVideoFrame() {
        driver.switchTo().frame(driver.findElement(VIDEO_IFRAME));
    }

}
