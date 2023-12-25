import configs.Urls;
import configs.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.UserPage;
import user.User;
import user.UserResponse;

import java.time.Duration;

import static user.UserData.*;

public class LogoutUserTest {
    public WebDriver driver;
    public UserResponse userResponse;
    public String accessToken;
    public User user;

    @Before
    public void setUp() {
        String browserName = System.getProperty("browserName");
        driver = WebDriverFactory.getDriver(browserName);
        user = new User(EMAIL, PASSWORD, NAME);
        userResponse = new UserResponse();
        Urls.start();
        ValidatableResponse responseUser = userResponse.createUser(user);
        accessToken = responseUser.extract().path("accessToken");

    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    public void logoutTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmailField(user.getEmail());
        loginPage.fillPasswordField(user.getPassword());
        loginPage.clickLoginButton();
        mainPage.clickAccountButton();
        UserPage userPage = new UserPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        userPage.clickLogoutButton();
        Assert.assertTrue(loginPage.loginButtonIsDisplayed());
    }

    @After
    public void delete() {
        if (accessToken != null) {
            userResponse.deleteUser(accessToken);
        }
        driver.quit();
    }

}
