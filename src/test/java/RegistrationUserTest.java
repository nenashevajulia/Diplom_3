import configs.BrowserType;
import configs.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegistrationPage;
import user.User;
import user.UserResponse;

import java.time.Duration;

import static user.UserData.*;

public class RegistrationUserTest {
    public WebDriver driver;
    public UserResponse userResponse;
    public String accessToken;
    public User user;

    @Before
    public void setUp() {
        driver = WebDriverFactory.getDriver(BrowserType.CHROME);
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
        if (accessToken != null) {
            userResponse.deleteUser(accessToken);
        }
        driver.quit();
    }
}
