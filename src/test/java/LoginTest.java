import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pom.*;
import userAPI.User;
import userAPI.UserClient;

import java.util.concurrent.TimeUnit;

import static driver.WebDriverCreator.createWebDriver;
import static utils.Utils.randomString;

public class LoginTest {

    WebDriver webDriver;
    private static final String EXPECTED_BUTTON_TEXT = "Оформить заказ";
    private String email;
    private String password;
    private String accessToken;

    @BeforeClass
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() {
        UserClient userClient = new UserClient();
        userClient.setBaseURL();
        User user = new User()
                .withEmail(randomString(6) + "@yandex.ru")
                .withPassword(randomString(6))
                .withName(randomString(6));
        Response response = userClient.createUser(user);
        password = user.getPassword();
        email = user.getEmail();
        accessToken = response.body().path("accessToken").toString().substring(7);

        webDriver = createWebDriver();
        MainPage mainPage = new MainPage(webDriver);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        mainPage
                .open();
    }

    @After
    public void TearDown() {
        UserClient userClient = new UserClient();
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
        webDriver.quit();
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginByGoToAccountButton() {
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        mainPage
                .clickGoToAccountButton();
        loginPage
                .inputЕmailField(email)
                .inputPasswordField(password)
                .clickEnterButton();
        Assert.assertEquals("Не удалось войти в аккаунт", EXPECTED_BUTTON_TEXT, mainPage.getButtonSetOrderText());

    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginByProfile() {
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        mainPage
                .clickProfileButton();
        loginPage
                .inputЕmailField(email)
                .inputPasswordField(password)
                .clickEnterButton();
        Assert.assertEquals("Не удалось войти в аккаунт", EXPECTED_BUTTON_TEXT, mainPage.getButtonSetOrderText());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginByRegistrationForm() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        RegistrationPage registrationPage = new RegistrationPage(webDriver);
        mainPage
                .clickGoToAccountButton();
        loginPage
                .clickRegistrationLink();
        registrationPage
                .clickEnterLink();
        loginPage
                .inputЕmailField(email)
                .inputPasswordField(password)
                .clickEnterButton();
        Assert.assertEquals("Не удалось войти в аккаунт", EXPECTED_BUTTON_TEXT, mainPage.getButtonSetOrderText());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля.")
    public void loginByRestorePasswordForm() {
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        RestorePasswordPage restorePasswordPage = new RestorePasswordPage(webDriver);
        mainPage
                .clickGoToAccountButton();
        loginPage
                .clickRecoverPasswordLink();
        restorePasswordPage
                .clickEnterButton();
        loginPage
                .inputЕmailField(email)
                .inputPasswordField(password)
                .clickEnterButton();
        Assert.assertEquals("Не удалось войти в аккаунт", EXPECTED_BUTTON_TEXT, mainPage.getButtonSetOrderText());
    }
}
