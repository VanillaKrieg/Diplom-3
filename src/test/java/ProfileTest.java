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
import ru.yandex.praktikum.pageobject.AccountPage;
import ru.yandex.praktikum.pageobject.HeaderPage;
import ru.yandex.praktikum.pageobject.LoginPage;
import ru.yandex.praktikum.pageobject.MainPage;

import static ru.yandex.praktikum.pageobject.LoginPage.LOGIN_PAGE_LINK;
import static ru.yandex.praktikum.pageobject.MainPage.MAIN_PAGE_LINK;


public class ProfileTest {
    private WebDriver driver;

    private String name;
    private String email;
    private String password;

    private UserClient userClient;
    private User user;

    private MainPage mainPage;
    private LoginPage loginPage;
    private AccountPage accountPage;
    private HeaderPage headerPage;


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
        accountPage = new AccountPage(driver);
        headerPage = new HeaderPage(driver);

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
    @DisplayName("Пользователь выходит из аккаунта через кнопку Выход на странице Личного кабинета")
    public void userLogsOutByExitAccountButtonOnAccountPage() {
        // Клик по кнопке Личный кабинет
        headerPage.clickPersonalAccountButton();

        // Клик по кнопке Выход
        accountPage.clickExitAccountButton();

        // Переход на Главную страницу
        driver.get(MAIN_PAGE_LINK);

        // Проверка отображения кнопки Войти в аккаунт
        mainPage.assertEnterAccountButtonIsDisplayed();
    }
}
