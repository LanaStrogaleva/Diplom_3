package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private By nameField = By.xpath(".//label[text() = 'Имя']/parent::*/input");
    private By emailField = By.xpath(".//label[text() = 'Email']/parent::*/input");
    private By passwordField = By.xpath(".//label[text() = 'Пароль']/parent::*/input");
    private By registrationButton = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private By enterLink = By.xpath(".//a[text() = 'Войти']");
    private By errorPasswordMessage = By.xpath(".//p[text() = 'Некорректный пароль']");


    @Step("Ввести данные в поле Имя: {name}")
    public RegistrationPage inputNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
        return this;
    }

    @Step("Ввести данные в поле Еmail: {email}")
    public RegistrationPage inputЕmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    @Step("Ввести данные в поле Пароль: {password}")
    public RegistrationPage inputPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    @Step("Кликнуть на кнопку Зарегистрироваться")
    public RegistrationPage clickRegistrationButton() {
        driver.findElement(registrationButton).click();
        return this;
    }

    @Step("Кликнуть по ссылке Войти")
    public RegistrationPage clickEnterLink() {
        driver.findElement(enterLink).click();
        return this;
    }

    // Получить текст сообщения для некорректного пароля
    public String getErrorPasswordMessageText() {
        return driver.findElement(errorPasswordMessage).getText();
    }

}
