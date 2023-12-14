package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoveryPasswordPage {
    private final WebDriver driver;
    private final By loginLinkRecovery = By.xpath(".//a[text()='Войти']");

    public RecoveryPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик на ссылку «Войти»")
    public void clickLoginLinkRecovery() {
        driver.findElement(loginLinkRecovery).click();
    }
}
