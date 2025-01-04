package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage {

    private WebDriver driver;

    //Заголовок страницы
    //@FindBy(xpath = ".//")
    //private final WebElement orderTitle = driver.findElement(By.className("Order_Header__BZXOb"));
    //Поле ввода имени
    @FindBy(xpath = ".//input[@placeholder='* Имя']")
    private WebElement nameInput;
    //Поле ввода фамилии
    @FindBy(xpath = ".//input[@placeholder='* Фамилия']")
    private WebElement surnameInput;
    //Поле ввода адреса
    @FindBy(xpath = ".//input[@placeholder='* Адрес: куда привезти заказ']")
    private WebElement addressInput;
    //Выпадающий список станций метро
    @FindBy(xpath = ".//input[@placeholder='* Станция метро']")
    private WebElement stationSelect;
    //Выбранная станция метро
    private final String stationItem = ".//button[@class='Order_SelectOption__82bhS select-search__option']/div[text()='%s']";
    //Поле ввода телефона
    @FindBy(xpath = ".//input[@placeholder='* Телефон: на него позвонит курьер']")
    private WebElement phoneInput;
    //Кнопка "Далее"
    @FindBy(xpath = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее']")
    private WebElement nextButton;

    public OrderPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillOrderForm (String name, String surname, String address, String station, String phone) {
        nameInput.sendKeys(name);
        surnameInput.sendKeys(surname);
        addressInput.sendKeys(address);
        stationSelect.click();
        stationSelect.sendKeys(station);
        driver.findElement(By.xpath(String.format(stationItem, station))).click();
        phoneInput.sendKeys(phone);
        clickNextButton();
    }

    public void clickNextButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", nextButton);
        nextButton.click();
    }

}
