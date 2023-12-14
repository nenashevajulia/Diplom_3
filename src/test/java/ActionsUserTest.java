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
import pageObject.UserPage;
import user.User;
import user.UserResponse;

import static user.UserData.*;

public class ActionsUserTest {
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
    @DisplayName("Переход по клику на «Личный кабинет»")
    public void clickAccountButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.loginButtonIsDisplayed());
    }

    @Test
    @DisplayName("Переход по клику на «Конструктор»")
    public void clickConstructorButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmailField(user.getEmail());
        loginPage.fillPasswordField(user.getPassword());
        loginPage.clickLoginButton();
        UserPage userPage = new UserPage(driver);
        userPage.clickConstructorButton();
        Assert.assertTrue(mainPage.makeBurgerTextIsDisplayed());
    }

    @Test
    @DisplayName("Переход по клику на логотип Stellar Burgers")
    public void clickLogoButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmailField(user.getEmail());
        loginPage.fillPasswordField(user.getPassword());
        loginPage.clickLoginButton();
        UserPage userPage = new UserPage(driver);
        userPage.clickLogoButton();
        Assert.assertTrue(mainPage.makeBurgerTextIsDisplayed());
    }

    @After
    public void delete() {
        if (accessToken != null) {
            userResponse.deleteUser(accessToken);
        }
        driver.quit();
    }
}
