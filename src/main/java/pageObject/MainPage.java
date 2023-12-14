package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static configs.Urls.BASE_URL;

public class MainPage {
    private final WebDriver driver;
    private final By loginAccountButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private final By accountButton = By.xpath(".//p[text() = 'Личный Кабинет']");
    private final By createOrderButton = By.xpath(".//button[text() = 'Оформить заказ']");
    private final By makeBurgerText = By.xpath(".//h1[text() = 'Соберите бургер']");
    private final By bunsButton = By.xpath(".//span[contains(text(),'Булки')]");
    private final By souseButton = By.xpath(".//span[contains(text(),'Соусы')]");
    private final By fillsButton = By.xpath(".//span[contains(text(),'Начинки')]");
    private final By currentSection = By.xpath("//div[contains(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc')]");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть главную страницу")
    public void open() {
        driver.get(BASE_URL);
    }

    @Step("Клик на кнопку «Войти в аккаунт»")
    public void clickLoginAccountButton() {
        driver.findElement(loginAccountButton).click();
    }

    @Step("Клик на кнопку «Личный кабинет»")
    public void clickAccountButton() {
        driver.findElement(accountButton).click();
    }

    @Step("Отображение кнопки «Оформить заказ»")
    public boolean createOrderButtonIsDisplayed() {
        return driver.findElement(createOrderButton).isDisplayed();
    }


    @Step("Отображение кнопки «Соберите бургер»")
    public boolean makeBurgerTextIsDisplayed() {
        return driver.findElement(makeBurgerText).isDisplayed();
    }

    @Step("Клик на «Булки»")
    public void clickLinkBuns() {
        driver.findElement(bunsButton).click();
    }

    @Step("Клик на «Соусы»")
    public void clickLinkSouse() {
        driver.findElement(souseButton).click();
    }

    @Step("Клик на «Начинки»")
    public void clickLinkFills() {
        driver.findElement(fillsButton).click();
    }

    @Step("Текущий раздел")
    public String getCurrentSection() {
        return driver.findElement(currentSection).getText();
    }
}
