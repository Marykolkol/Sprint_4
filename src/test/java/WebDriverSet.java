import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverSet {
    private static final String URL = "https://qa-scooter.praktikum-services.ru/";
    private static final String BROWSER_NAME = "CHROME";

    WebDriver driver;

    @Before
    public void prepareDriver() {
        driver = WebDriverSet.createDriver(BROWSER_NAME);
        driver.get(URL);
    }

    public static WebDriver createDriver(String browserName) {
        if (browserName.equals("FIREFOX")) {
                return new FirefoxDriver();
            } else if (browserName.equals("CHROME")) {
                return new ChromeDriver();
            } else {
                throw new RuntimeException("Нераспознанный браузер: " + browserName);
            }
        }

    @After
    public void quitDriver() {
        driver.quit();
    }
}

