import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverSet {

    public static WebDriver createDriver(String browserName) {
        if (browserName.equals("FIREFOX")) {
                return new FirefoxDriver();
            } else if (browserName.equals("CHROME")) {
                return new ChromeDriver();
            } else {
                throw new RuntimeException("Нераспознанный браузер: " + browserName);
            }
        }
}

