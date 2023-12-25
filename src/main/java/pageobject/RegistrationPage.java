package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static configs.Urls.REGISTRATION_PAGE_URL;


public class RegistrationPage {
    private final WebDriver driver;
    private final By nameField = By.xpath(".//label[text() = 'Имя']/../input[contains(@name, 'name')]");
    private final By emailField = By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]");
    private final By passwordField = By.xpath(".//label[text() = 'Пароль']/../input[contains(@type, 'password')]");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By invalidPasswordMessage = By.xpath(".//*[text()='Некорректный пароль']");
    private final By loginLinkRegistration = By.xpath(".//a[text()='Войти']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(REGISTRATION_PAGE_URL);
    }

    @Step("Заполнение формы регистрации")
    public void fillRegistrationForm(String name, String email, String password) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Клик на кнопку «Зарегистрироваться»")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Клик на ссылку «Войти»")
    public void clickLinkRegistration() {
        driver.findElement(loginLinkRegistration).click();
    }

    @Step("Отображение сообщения «Некорректный пароль»")
    public boolean invalidPasswordMessageIsDisplayed() {
        return driver.findElement(invalidPasswordMessage).isDisplayed();
    }

    @Step("Отображение кнопки «Зарегистрироваться»")
    public boolean registerButtonIsDisplayed() {
        return driver.findElement(registerButton).isDisplayed();
    }


}
