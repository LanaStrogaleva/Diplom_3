import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pom.MainPage;

import java.time.Duration;

import static driver.WebDriverCreator.createWebDriver;

public class ConstructorTest {
    WebDriver webDriver;

    @BeforeClass
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() {
        webDriver = createWebDriver();
        MainPage mainPage = new MainPage(webDriver);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        mainPage
                .open();
    }

    @After
    public void TearDown() {
        webDriver.quit();
    }

    @Test
    @DisplayName("Раздел «Конструктор»: переход к разделу «Булки»")
    public void transferToBunsTab() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        mainPage
                .clickFillingTab();
        Thread.sleep(1000);

        mainPage.clickBunTab();
        Thread.sleep(1000);

        Assert.assertTrue(mainPage.isCurrentBunTab());

    }

    @Test
    @DisplayName("Раздел «Конструктор»: переход к разделу «Соусы»")
    public void transferToSauceTab() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);

        mainPage
                .clickSauceTab();
        Thread.sleep(1000);

        Assert.assertTrue(mainPage.isCurrentSauceTab());

    }

    @Test
    @DisplayName("Раздел «Конструктор»: переход к разделу «Начинки»")
    public void transferToFillingTab() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        mainPage
                .clickFillingTab();
        Thread.sleep(1000);

        Assert.assertTrue(mainPage.isCurrentFillingTab());

    }

}
