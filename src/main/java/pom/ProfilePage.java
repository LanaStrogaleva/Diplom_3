package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    private By profileHeader = By.xpath(".//a[text() = 'Профиль']");
    private By registrationLink = By.xpath(".//a[text() = 'Зарегистрироваться']");
    private By constructorButton = By.xpath(".//p[text() = 'Конструктор']");
    private By stellarBurgersLogo = By.xpath(".//div[contains(@class,'AppHeader_header__logo')]");
    private By exitButton = By.xpath(".//button[text() = 'Выход']");

    // Получить текст заголовка Профиль
    public String getProfileHeaderText() {
        return driver.findElement(profileHeader).getText();
    }

    @Step("Кликнуть на ссылку Зарегистрироваться")
    public void clickRegistrationLink() {
        driver.findElement(registrationLink).click();
    }

    @Step("Кликнуть на кнопку Конструктор")
    public ProfilePage clickConstructorButton() {
        driver.findElement(constructorButton).click();
        return this;
    }

    @Step("Кликнуть на логотип Stellar Burgers")
    public ProfilePage clickStellarBurgersLogo() {
        driver.findElement(stellarBurgersLogo).click();
        return this;
    }

    @Step("Кликнуть на кнопку Выход")
    public ProfilePage clickExitButton() {
        driver.findElement(exitButton).click();
        return this;
    }

}
