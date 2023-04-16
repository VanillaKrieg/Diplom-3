package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RegistrationPage {
    private final WebDriver driver;
    public RegistrationPage(WebDriver driver){
        this.driver = driver;
    }


    // Ссылка на страницу
    public static final String REGISTRATION_PAGE_LINK = "https://stellarburgers.nomoreparties.site/register";

    // Поле ввода Имя
    private final By nameInput = By.xpath("//label[text()='Имя']/parent::div/input");

    // Поле ввода Email
    private final By emailInput = By.xpath("//label[text()='Email']/parent::div/input");

    // Поле ввода Пароль
    private final By passwordInput = By.xpath("//label[text()='Пароль']/parent::div/input");

    // Кнопка Зарегистрироваться
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");

    // Кнопка Войти
    private final By enterButton = By.xpath("//a[text()='Войти']");

    // Варнинг Некорректный пароль
    private final By invalidPasswordWarning = By.xpath("//p[text()='Некорректный пароль']");


    // Заполнение поля Имя
    public void fillNameInput(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    // Заполнение поля Email
    public void fillEmailInput(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    // Заполнение поля Пароль
    public void fillPasswordInput(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    // Заполнение формы Регистрация
    public void fillRegistrationForm(String name, String email, String password) {
        fillNameInput(name);
        fillEmailInput(email);
        fillPasswordInput(password);
    }

    // Клик по кнопке Зарегистрироваться
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    // Клик по кнопке Войти
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }

    // Проверка отображения варнинга Некорректный пароль
    public void assertInvalidPasswordWarningIsDisplayed() {
        assertThat("При попытке зарегистрироваться с паролем короче 6 символов отображается варнинг", true, equalTo(driver.findElement(invalidPasswordWarning).isDisplayed()));
    }

    // Проверка перехода на страницу Регистрации
    public void assertCurrentUrl() {
        assertThat("Происходит переход на страницу Регистрации", REGISTRATION_PAGE_LINK, equalTo(driver.getCurrentUrl()));
    }
}
