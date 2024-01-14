import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pom.LoginPage;
import pom.MainPage;
import pom.ProfilePage;
import pom.RegistrationPage;
import userAPI.User;
import userAPI.UserClient;

import java.util.concurrent.TimeUnit;

import static driver.WebDriverCreator.createWebDriver;
import static utils.Utils.randomString;

public class RegistrationTest {
    WebDriver webDriver;
    private static final String EXPECTED_ERROR_MESSAGE = "Некорректный пароль";
    private static final String EXPECTED_PROFILE_HEADER = "Вход";
    private String name;
    private String email;
    private String password;
    private String accessToken;

    @BeforeClass
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() {
        webDriver = createWebDriver();
        MainPage mainPage = new MainPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        mainPage
                .open()
                .clickProfileButton();
        profilePage
                .clickRegistrationLink();
    }

    @After
    public void TearDown() {
        if (password.length() >= 6) {
            UserClient userClient = new UserClient();
            userClient.setBaseURL();
            Response response = userClient.loginUser(new User().withEmail(email).withPassword(password));
            accessToken = response.body().path("accessToken").toString().substring(7);
            if (accessToken != null) {
                userClient.deleteUser(accessToken);
            }
        }
        webDriver.quit();
    }

    @Test
    @DisplayName("Регистрация с корректными данными")
    public void successfulRegistration() {
        name = randomString(7);
        email = randomString(7) + "@yandex.ru";
        password = randomString(7);

        RegistrationPage registrationPage = new RegistrationPage(webDriver);

        registrationPage
                .inputNameField(name)
                .inputЕmailField(email)
                .inputPasswordField(password)
                .clickRegistrationButton();
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertEquals("Не появился заголовок 'Вход'", EXPECTED_PROFILE_HEADER, loginPage.getLoginHeaderText());

    }

    @Test
    @DisplayName("Регистрация с некорректным паролем")
    public void incorrectPasswordRegistration() {
        name = randomString(7);
        email = randomString(7) + "@yandex.ru";
        password = randomString(5);

        RegistrationPage registrationPage = new RegistrationPage(webDriver);
        registrationPage
                .inputNameField(name)
                .inputЕmailField(email)
                .inputPasswordField(password)
                .clickRegistrationButton();
        Assert.assertEquals("Не появилось сообщение об ошибке", EXPECTED_ERROR_MESSAGE, registrationPage.getErrorPasswordMessageText());
    }
}
