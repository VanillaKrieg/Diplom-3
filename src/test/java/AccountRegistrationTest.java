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
import ru.yandex.praktikum.pageobject.RegistrationPage;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.is;
import static ru.yandex.praktikum.pageobject.LoginPage.LOGIN_PAGE_LINK;
import static ru.yandex.praktikum.pageobject.RegistrationPage.REGISTRATION_PAGE_LINK;

public class AccountRegistrationTest {
    private WebDriver driver;

    private String name;
    private String email;
    private String password;

    private UserClient userClient;
    private User user;

    private LoginPage loginPage;
    private RegistrationPage registrationPage;


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

        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
    }

    @After
    public void teardown() {
        driver.quit();
        userClient.delete(user);
    }


    @Test
    @DisplayName("Пользователь попадает на страницу Регистрации через кнопку Зарегистрироваться на странице Логина")
    public void userGetsRegistrationPageByRegisterButtonOnLoginPage() {
        // Переход на страницу Логина
        driver.get(LOGIN_PAGE_LINK);

        // Клик по кнопке Зарегистрироваться
        loginPage.clickRegisterButton();

        // Проверка перехода на страницу Регистрации
        registrationPage.assertCurrentUrl();
    }

    @Test
    @DisplayName("Пользователь может быть зарегистрирован с валидными данными")
    public void userCanBeRegisteredWithValidData() {
        // Переход на страницу Регистрации
        driver.get(REGISTRATION_PAGE_LINK);

        // Заполнение формы Регистрация
        registrationPage.fillRegistrationForm(name, email, password);

        // Клик по кнопке Зарегистрироваться
        registrationPage.clickRegisterButton();

        // Проверка успешного логина через API
        userClient.login(user)
                .assertThat()
                .statusCode(SC_OK)
                .body("success", is(true));
    }

    @Test
    @DisplayName("Пользователь не может быть зарегистрирован с коротким паролем")
    public void userCanNotBeRegisteredWithShortPassword() {
        // Переход на страницу Регистрации
        driver.get(REGISTRATION_PAGE_LINK);

        // Заполнение формы Регистрация
        registrationPage.fillRegistrationForm(name, email, "55555");

        // Клик по кнопке Зарегистрироваться
        registrationPage.clickRegisterButton();

        // Проверка отображения варнинга Некорректный пароль
        registrationPage.assertInvalidPasswordWarningIsDisplayed();
    }
}
