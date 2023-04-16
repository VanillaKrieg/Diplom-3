import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.praktikum.api.client.UserClient;
import ru.yandex.praktikum.api.model.User;
import ru.yandex.praktikum.pageobject.LoginPage;
import ru.yandex.praktikum.pageobject.MainPage;

import static ru.yandex.praktikum.pageobject.LoginPage.LOGIN_PAGE_LINK;
import static ru.yandex.praktikum.pageobject.MainPage.MAIN_PAGE_LINK;


public class ConstructorTest {
    private WebDriver driver;

    private String name;
    private String email;
    private String password;

    private UserClient userClient;
    private User user;

    private MainPage mainPage;
    private LoginPage loginPage;


    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);

        name = RandomStringUtils.randomAlphabetic(10);
        email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
        password = RandomStringUtils.randomAlphabetic(10);

        userClient = new UserClient();
        user = new User(email, password, name, "");
        userClient.create(user);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);

        // Переход на страницу Логина
        driver.get(LOGIN_PAGE_LINK);

        // Заполнение формы Логин
        loginPage.fillLoginForm(email, password);

        // Клик по кнопке Войти
        loginPage.clickEnterButton();
    }

    @After
    public void teardown() {
        driver.quit();
        userClient.delete(user);
    }


    @Test
    @DisplayName("При клике по вкладке Булки происходит переход в раздел Булки")
    public void userGetsBunsSectionByBunsTabOnMainPage() {
        // Переход на Главную страницу
        driver.get(MAIN_PAGE_LINK);

        // Клик по вкладке Соусы
        mainPage.clickSaucesTab();

        // Клик по вкладке Булки
        mainPage.clickBunsTab();

        // Проверка активности вкладки Булки
        mainPage.assertBunsTabActiveIsDisplayed();
    }

    @Test
    @DisplayName("При клике по вкладке Соусы происходит переход в раздел Соусы")
    public void userGetsSaucesSectionBySaucesTabOnMainPage() {
        // Переход на Главную страницу
        driver.get(MAIN_PAGE_LINK);

        // Клик по вкладке Соусы
        mainPage.clickSaucesTab();

        // Проверка активности вкладки Соусы
        mainPage.assertSaucesTabActiveIsDisplayed();
    }

    @Test
    @DisplayName("При клике по вкладке Начинки происходит переход в раздел Начинки")
    public void userGetsFillingsSectionByFillingsTabOnMainPage() {
        // Переход на Главную страницу
        driver.get(MAIN_PAGE_LINK);

        // Клик по вкладке Начинки
        mainPage.clickFillingsTab();

        // Проверка активности вкладки Начинки
        mainPage.assertFillingsTabActiveIsDisplayed();
    }
}
