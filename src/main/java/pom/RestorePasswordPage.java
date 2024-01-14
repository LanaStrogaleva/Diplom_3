package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RestorePasswordPage {
    private final WebDriver driver;

    public RestorePasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    private By enterLink = By.xpath(".//a[text() = 'Войти']");

    @Step("Кликнуть на ссылку Войти")
    public RestorePasswordPage clickEnterButton() {
        driver.findElement(enterLink).click();
        return this;
    }
}
