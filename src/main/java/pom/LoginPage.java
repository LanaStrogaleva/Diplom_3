package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By loginHeader = By.xpath(".//div/h2[text() = 'Вход']");
    private By emailField = By.xpath(".//label[text() = 'Email']/parent::*/input");
    private By passwordField = By.xpath(".//label[text() = 'Пароль']/parent::*/input");
    private By enterButton = By.xpath(".//button[text() = 'Войти']");
    private By registrationLink = By.xpath(".//a[text() = 'Зарегистрироваться']");
    private By recoverPasswordLink = By.xpath(".//a[text() = 'Восстановить пароль']");

    // Получить текст заголовка Вход
    public String getLoginHeaderText() {
        return driver.findElement(loginHeader).getText();
    }

    @Step("Ввести данные в поле Еmail: {email}")
    public LoginPage inputЕmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    @Step("Ввести данные в поле Пароль: {password}")
    public LoginPage inputPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    @Step("Кликнуть на кнопку Войти")
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }

    @Step("Кликнуть на ссылку Зарегистрироваться")
    public LoginPage clickRegistrationLink() {
        driver.findElement(registrationLink).click();
        return this;
    }

    @Step("Кликнуть на ссылку Восстановить пароль")
    public LoginPage clickRecoverPasswordLink() {
        driver.findElement(recoverPasswordLink).click();
        return this;
    }

}
