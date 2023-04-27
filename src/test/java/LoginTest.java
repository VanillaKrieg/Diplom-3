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
import ru.yandex.praktikum.pageobject.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import static ru.yandex.praktikum.pageobject.ForgotPasswordPage.FORGOT_PASSWORD_PAGE_LINK;
import static ru.yandex.praktikum.pageobject.LoginPage.LOGIN_PAGE_LINK;
import static ru.yandex.praktikum.pageobject.MainPage.MAIN_PAGE_LINK;
import static ru.yandex.praktikum.pageobject.RegistrationPage.REGISTRATION_PAGE_LINK;


public class LoginTest {
    private WebDriver driver;

    private String name;
    private String email;
    private String password;

    private UserClient userClient;
    private User user;

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private ForgotPasswordPage forgotPasswordPage;
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
        registrationPage = new RegistrationPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        headerPage = new HeaderPage(driver);
    }

    @After
    public void teardown() {
        driver.quit();
        userClient.delete(user);
    }


    @Test
    @DisplayName("Пользователь может залогиниться на странице Логина")
    public void userCanLoginOnLoginPage() {
        // Переход на страницу Логина
        driver.get(LOGIN_PAGE_LINK);

        // Заполнение формы Логин
        loginPage.fillLoginForm(email, password);

        // Клик по кнопке Войти
        loginPage.clickEnterButton();

        // Проверка отображения кнопки Оформить заказ
        mainPage.assertPlaceOrderButtonIsDisplayed();
    }

    @Test
    @DisplayName("Пользователь попадает на страницу Логина через кнопку Войти в аккаунт на Главной странице")
    public void userGetsLoginPageByEnterAccountButtonOnMainPage() {
        // Переход на Главную страницу
        driver.get(MAIN_PAGE_LINK);

        // Клик по кнопке Войти в аккаунт
        mainPage.clickEnterAccountButton();

        // Проверка перехода на страницу Логина
        loginPage.assertCurrentUrl();
    }

    @Test
    @DisplayName("Пользователь попадает на страницу Логина через кнопку Личный кабинет в Шапке")
    public void userGetsLoginPageByPersonalAccountButtonOnHeader() {
        // Переход на Главную страницу
        driver.get(MAIN_PAGE_LINK);

        // Клик по кнопке Личный кабинет
        headerPage.clickPersonalAccountButton();

        // Проверка перехода на страницу Логина
        loginPage.assertCurrentUrl();
    }

    @Test
    @DisplayName("Пользователь попадает на страницу Логина через кнопку Войти на странице Регистрации")
    public void userGetsLoginPageByEnterButtonOnRegistrationPage() {
        // Переход на страницу Регистрации
        driver.get(REGISTRATION_PAGE_LINK);

        // Клик по кнопке Войти
        registrationPage.clickEnterButton();

        // Проверка перехода на страницу Логина
        loginPage.assertCurrentUrl();
    }

    @Test
    @DisplayName("Пользователь попадает на страницу Логина через кнопку Войти на странице Восстановления пароля")
    public void userGetsLoginPageByEnterButtonOnForgotPasswordPage() {
        // Переход на страницу Восстановления пароля
        driver.get(FORGOT_PASSWORD_PAGE_LINK);

        // Клик по кнопке Войти
        forgotPasswordPage.clickEnterButton();

        // Проверка перехода на страницу Логина
        loginPage.assertCurrentUrl();
    }
}
