package configs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    public static WebDriver getDriver(String browserName) throws RuntimeException {
        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeDriver chromeDriver = new ChromeDriver();
                chromeDriver.manage().window().maximize();
                return chromeDriver;
            case "yandex":
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
