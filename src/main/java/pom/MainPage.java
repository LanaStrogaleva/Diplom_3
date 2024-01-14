package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private static final String URL = "https://stellarburgers.nomoreparties.site/";

    private final WebDriver driver;


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By profileButton = By.xpath(".//p[text() = 'Личный Кабинет']");
    private By goToAccountButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private By buttonSetOrder = By.xpath(".//button[text() = 'Оформить заказ']");

    private By constructorHeader = By.xpath(".//h1[text() = 'Соберите бургер']");
    private By bunTab = By.xpath(".//span[text() = 'Булки']/parent::*");
    private By sauceTab = By.xpath(".//span[text() = 'Соусы']/parent::*");
    private By fillingTab = By.xpath(".//span[text() = 'Начинки']/parent::*");

    // открыть главную страницу
    public MainPage open() {
        driver.get(URL);
        return this;
    }

    // кликнуть на кнопку "Личный кабинет"
    public MainPage clickProfileButton() {
        driver.findElement(profileButton).click();
        return this;
    }

    // кликнуть на кнопку "Войти в аккаунт"
    public MainPage clickGoToAccountButton() {
        driver.findElement(goToAccountButton).click();
        return this;
    }

    // кликнуть по вкладке "Булки"
    public MainPage clickBunTab() {
        driver.findElement(bunTab).click();
        return this;
    }

    // кликнуть по вкладке "Соусы"
    public MainPage clickSauceTab() {
        driver.findElement(sauceTab).click();
        return this;
    }

    // кликнуть по вкладке "Начинки"
    public MainPage clickFillingTab() {
        driver.findElement(fillingTab).click();
        return this;
    }

    public String getButtonSetOrderText() {
        return driver.findElement(buttonSetOrder).getText();
    }

    public String getConstructorHeaderText() {
        return driver.findElement(constructorHeader).getText();
    }

    public boolean isCurrentBunTab() {
        return driver.findElement(bunTab).getAttribute("class").contains("current");
    }

    public boolean isCurrentSauceTab() {
        return driver.findElement(sauceTab).getAttribute("class").contains("current");
    }

    public boolean isCurrentFillingTab() {
        return driver.findElement(fillingTab).getAttribute("class").contains("current");
    }

}
