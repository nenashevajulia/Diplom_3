package configs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
    public static WebDriver getDriver(BrowserType browserType) throws RuntimeException {
        switch (browserType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeDriver chromeDriver = new ChromeDriver();
                chromeDriver.manage().window().maximize();
                return chromeDriver;
            case YANDEX:
                System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
                ChromeDriver yandexDriver = new ChromeDriver();
                yandexDriver.manage().window().maximize();
                yandexDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                return yandexDriver;
            default:
                throw new RuntimeException("Браузер не выбран");
        }
    }
}
