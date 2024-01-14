import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pom.LoginPage;
import pom.MainPage;
import pom.ProfilePage;
import userAPI.User;
import userAPI.UserClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static driver.WebDriverCreator.createWebDriver;
import static utils.Utils.randomString;

public class ProfileTest {
    WebDriver webDriver;
    private static final String EXPECTED_PROFILE_HEADER_TEXT = "Профиль";
    private static final String EXPECTED_CONSTRUCTOR_HEADER_TEXT = "Соберите бургер";
    private static final String EXPECTED_LOGIN_HEADER_TEXT = "Вход";
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
        LoginPage loginPage = new LoginPage(webDriver);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        mainPage
                .open()
                .clickGoToAccountButton();
        loginPage
                .inputЕmailField(email)
                .inputPasswordField(password)
                .clickEnterButton();
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
    @DisplayName("Переход в личный кабинет по клику на «Личный кабинет»")
    public void transferToProfileByProfileButton() {
        MainPage mainPage = new MainPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);
        mainPage
                .clickProfileButton();
        Assert.assertEquals("Не удалось перейти в личный кабинет", EXPECTED_PROFILE_HEADER_TEXT, profilePage.getProfileHeaderText());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void transferToConstructorByConstructorButton() {
        MainPage mainPage = new MainPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);
        mainPage
                .clickProfileButton();
        profilePage
                .clickConstructorButton();
        Assert.assertEquals("Не удалось перейти в конструктор", EXPECTED_CONSTRUCTOR_HEADER_TEXT, mainPage.getConstructorHeaderText());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void transferToConstructorByLogo() {
        MainPage mainPage = new MainPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);
        mainPage
                .clickProfileButton();
        profilePage
                .clickStellarBurgersLogo();
        Assert.assertEquals("Не удалось перейти в конструктор", EXPECTED_CONSTRUCTOR_HEADER_TEXT, mainPage.getConstructorHeaderText());
    }

    @Test
    @DisplayName("Выход из аккаунта по кнопке «Выйти» в личном кабинете")
    public void logAutByExitButton() {
        MainPage mainPage = new MainPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        mainPage
                .clickProfileButton();
        profilePage.clickExitButton();
        Assert.assertEquals("Не удалось выйти из аккаунта", EXPECTED_LOGIN_HEADER_TEXT, loginPage.getLoginHeaderText());
    }


}
