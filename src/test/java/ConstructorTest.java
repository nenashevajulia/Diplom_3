import configs.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.MainPage;


public class ConstructorTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        String browserName = System.getProperty("browserName");
        driver = WebDriverFactory.getDriver(browserName);
    }


    @Test
    @DisplayName("Переход к разделу «Булки»")
    public void bunsSectionTest() {
        mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLinkFills();
        mainPage.clickLinkBuns();
        Assert.assertEquals("Булки", mainPage.getCurrentSection());
    }

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    public void souseSectionTest() {
        mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLinkSouse();
        Assert.assertEquals("Соусы", mainPage.getCurrentSection());
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    public void fillsSectionTest() {
        mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLinkFills();
        Assert.assertEquals("Начинки", mainPage.getCurrentSection());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
