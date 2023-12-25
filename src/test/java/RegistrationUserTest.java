import configs.Urls;
import configs.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import login.Login;
import login.LoginResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegistrationPage;
import user.User;
import user.UserResponse;

import java.time.Duration;

import static user.UserData.*;

public class RegistrationUserTest {
    public WebDriver driver;
    public UserResponse userResponse;
    public LoginResponse loginResponse;
    public String accessToken;
    public User user;

    @Before
    public void setUp() {
        String browserName = System.getProperty("browserName");
        driver = WebDriverFactory.getDriver(browserName);
        userResponse = new UserResponse();
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void registrationTest() {
        user = new User(EMAIL, PASSWORD, NAME);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        registrationPage.registerButtonIsDisplayed();
        registrationPage.fillRegistrationForm(user.getName(), user.getEmail(), user.getPassword());
        registrationPage.clickRegisterButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginButtonIsDisplayed();
        loginPage.fillEmailField(user.getEmail());
        loginPage.fillPasswordField(user.getPassword());
        loginPage.clickLoginButton();
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.createOrderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Регистрация с неправильным паролем")
    public void registrationWithWrongPassTest() {
        user = new User(EMAIL, WRONG_PASSWORD, NAME);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        registrationPage.registerButtonIsDisplayed();
        registrationPage.fillRegistrationForm(user.getName(), user.getEmail(), user.getPassword());
        registrationPage.clickRegisterButton();
        Assert.assertTrue(registrationPage.invalidPasswordMessageIsDisplayed());
    }

    @After
    public void delete() {
        loginResponse = new LoginResponse();
        Login login = new Login(user.getEmail(), user.getPassword());
        Urls.start();
        ValidatableResponse responseLogin = loginResponse.login(login);
        accessToken = responseLogin.extract().path("accessToken");
        if (accessToken != null) {
            userResponse.deleteUser(accessToken);
        }
        driver.quit();
    }
}
