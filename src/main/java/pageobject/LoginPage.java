package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    protected final By linkRegistration = By.linkText("Зарегистрироваться");
    private final WebDriver driver;
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath(".//*[text()='Пароль']/following-sibling::input");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By linkForgotPassword = By.xpath(".//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик на ссылку «Зарегистрироваться»")
    public void clickLinkRegistration() {
        driver.findElement(linkRegistration).click();
    }

    @Step("Заполнение Email")
    public void fillEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Заполнение Password")
    public void fillPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Клик на кнопку «Войти»")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Клик на «Восстановить пароль»")
    public void clickLinkForgotPassword() {
        driver.findElement(linkForgotPassword).click();
    }

    @Step("Отображение кнопки «Войти»")
    public boolean loginButtonIsDisplayed() {
        return driver.findElement(loginButton).isDisplayed();
    }
}