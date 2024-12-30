package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;

    //кнопка принятия кук
    @FindBy(xpath = ".//button[@class='App_CookieButton__3cvqF']")
    private WebElement cookieButton;
    //кнопка "Заказать" вверху страницы
    @FindBy(xpath = ".//div[@class='Header_Nav__AGCXC']/button[@class='Button_Button__ra12g']")
    private WebElement orderButtonTop;
    //кнопка "Заказать" внизу страницы
    @FindBy(xpath = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']")
    private WebElement orderButtonDown;
    //элемент раскрывающегося списка "Вопросы о важном"
    private String questionAccordionItem = ".//div[@id='accordion__heading-%d']";
    //отображающийся текст после раскрытия аккордеона
    private String answerAccordionItem = ".//div[@id='accordion__panel-%d']";


    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getQuestionText(int accordionIndex) {
        WebElement element = driver.findElement(By.xpath(String.format(questionAccordionItem, accordionIndex)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return element.getText();
    }

    public String getAccordionText(int accordionIndex) {
        WebElement element = driver.findElement(By.xpath(String.format(questionAccordionItem, accordionIndex)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(answerAccordionItem, accordionIndex))));
        return driver.findElement(By.xpath(String.format(answerAccordionItem, accordionIndex))).getText();
    }

    public void clickCookieButton() {
        cookieButton.click();
    }

    public void clickOrderButton(String place) {
        switch (place) {
            case "Top":
                orderButtonTop.click();
                break;
            case "Down":
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", orderButtonDown);
                orderButtonDown.click();
                break;
        }
    }

}
