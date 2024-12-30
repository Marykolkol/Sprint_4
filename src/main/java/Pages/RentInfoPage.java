package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;


public class RentInfoPage {

    int randomDay;
    int randomMonth;
    int randomYear;
    int randomPeriod;
    int randomColour;

    private WebDriver driver;
    //
    private final String rentBlock = ".//div[@class='App_App__15LM-']";
    //Выбор даты
    @FindBy(xpath = ".//input[@placeholder='* Когда привезти самокат']")
    private WebElement calendarPicker;
    //выбор срока аренды
    @FindBy(xpath = ".//div[@class='Dropdown-control']")
    private WebElement rentalPeriodSelect;
    //варианты срока аренды
    private final String rentalPeriodItem = ".//div[@class='Dropdown-option']";
    //выбор черного самоката
    @FindBy(xpath = ".//input[@id='black']")
    private WebElement scooterBlackColour;
    //выбор серого самоката
    @FindBy(xpath = ".//input[@id='grey']")
    private WebElement scooterGreyColour;
    //комментарий
    @FindBy(xpath = ".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN']")
    private WebElement comment;
    //кнопка "Заказать"
    @FindBy(xpath = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']")
    private WebElement orderButton;
    //кнопка "Да"
    private By yesButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    //Текст "Заказ оформлен"
    private final String orderConfirm = ".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']";

    public RentInfoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setRandomValues() {
        Random random = new Random();
        randomDay = random.nextInt(28) + 1;
        randomMonth = random.nextInt(12) + 1;
        randomYear = random.nextInt(4) + 2025;
        randomPeriod = random.nextInt(6) + 1;
        randomColour = random.nextInt(2);
    }
    public void fillRentForm() {
        setRandomValues();
        new WebDriverWait(driver, Duration.ofSeconds(10))
              .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(rentBlock)));
        calendarPicker.click();
        calendarPicker.sendKeys(randomDay + "-" + randomMonth + "-" + randomYear);
        calendarPicker.sendKeys(Keys.ENTER);
        rentalPeriodSelect.click();
        driver.findElement(By.xpath(rentalPeriodItem + "["+ randomPeriod + "]")).click();
        switch (randomColour) {
            case 0:
                scooterBlackColour.click();
                break;
            case 1:
                scooterGreyColour.click();
                break;
        }
        comment.sendKeys("Заказ самоката");
    }

    public void doOrder() {
        orderButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(yesButton));
        driver.findElement(yesButton).click();
    }

    public boolean checkOrderWindowIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(orderConfirm)));
        return driver.findElement(By.xpath(orderConfirm)).isDisplayed();
    }
}
