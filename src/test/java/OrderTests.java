import pages.MainPage;
import pages.OrderPage;
import pages.RentInfoPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTests extends WebDriverSet {

    String caseButton;
    String nameInput;
    String surnameInput;
    String addressInput;
    String stationInput;
    String phoneInput;

    public OrderTests (String caseButton, String nameInput, String surnameInput, String addressInput, String stationInput, String phoneInput) {
        this.caseButton = caseButton;
        this.nameInput = nameInput;
        this.surnameInput = surnameInput;
        this.addressInput = addressInput;
        this.stationInput = stationInput;
        this.phoneInput = phoneInput;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {"Top", "Иван", "Иванов", "Париж, Эйфелева башня, 2 ярус", "Пролетарская", "89099900990"},
                {"Down", "Тест", "Тестович", "Подвал Иннокентия Васильевича, нижняя полка", "Парк Победы", "89555555667"},
        };
    }

    @Test
    public void createOrder()
    {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookieButton();
        mainPage.clickOrderButton(caseButton);
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillOrderForm(nameInput, surnameInput, addressInput, stationInput, phoneInput);
        RentInfoPage rentInfoPage = new RentInfoPage(driver);
        rentInfoPage.fillRentForm();
        rentInfoPage.doOrder();
        assertTrue(rentInfoPage.checkOrderWindowIsDisplayed());
    }

}
