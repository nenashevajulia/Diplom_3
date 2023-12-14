import configs.BrowserType;
import configs.Urls;
import configs.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RecoveryPasswordPage;
import pageObject.RegistrationPage;
import user.User;
import user.UserResponse;

import java.time.Duration;

import static user.UserData.*;


public class LoginUserTest {
    public WebDriver driver;
    public UserResponse userResponse;
    public String accessToken;
    public User user;

    @Before
    public void setUp() {
        driver = WebDriverFactory.getDriver(BrowserType.YANDEX);
        user = new User(EMAIL, PASSWORD, NAME);
        userResponse = new UserResponse();
        Urls.start();
        ValidatableResponse responseUser = userResponse.createUser(user);
        accessToken = responseUser.extract().path("accessToken");
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной странице")
    public void loginButtonMainPageTest() {

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmailField(user.getEmail());
        loginPage.fillPasswordField(user.getPassword());
        loginPage.clickLoginButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertTrue(mainPage.createOrderButtonIsDisplayed());

    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет» на главной странице")
    public void accountButtonMainPageTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmailField(user.getEmail());
        loginPage.fillPasswordField(user.getPassword());
        loginPage.clickLoginButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertTrue(mainPage.createOrderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку «Войти» на странице регистрации")
    public void loginButtonRegistrationPageTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLinkRegistration();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickLinkRegistration();
        loginPage.fillEmailField(user.getEmail());
        loginPage.fillPasswordField(user.getPassword());
        loginPage.clickLoginButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertTrue(mainPage.createOrderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку «Войти» на странице восстановление пароля")
    public void loginButtonRecoveryPasswordPageTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLinkForgotPassword();
        RecoveryPasswordPage recoveryPasswordPage = new RecoveryPasswordPage(driver);
        recoveryPasswordPage.clickLoginLinkRecovery();
        loginPage.fillEmailField(user.getEmail());
        loginPage.fillPasswordField(user.getPassword());
        loginPage.clickLoginButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertTrue(mainPage.createOrderButtonIsDisplayed());
    }

    @After
    public void delete() {
        if (accessToken != null) {
            userResponse.deleteUser(accessToken);
        }
        driver.quit();
    }
}
