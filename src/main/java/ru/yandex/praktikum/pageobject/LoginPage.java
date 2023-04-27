package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginPage {
    private final WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }


    // Ссылка на страницу
    public static final String LOGIN_PAGE_LINK = "https://stellarburgers.nomoreparties.site/login";

    // Поле ввода Email
    private final By emailInput = By.xpath("//label[text()='Email']/parent::div/input");

    // Поле ввода Пароль
    private final By passwordInput = By.xpath("//label[text()='Пароль']/parent::div/input");

    // Кнопка Войти
    private final By enterButton = By.xpath("//button[text()='Войти']");

    // Кнопка Зарегистрироваться
    private final By registerButton = By.xpath("//a[text()='Зарегистрироваться']");


    // Заполнение поля Email
    public void fillEmailInput(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    // Заполнение поля Пароль
    public void fillPasswordInput(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    // Заполнение формы Логин
    public void fillLoginForm(String email, String password) {
        fillEmailInput(email);
        fillPasswordInput(password);
    }

    // Клик по кнопке Войти
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }

    // Клик по кнопке Зарегистрироваться
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    // Проверка перехода на страницу Логина
    public void assertCurrentUrl() {
        assertThat("Происходит переход на страницу Логина", LOGIN_PAGE_LINK, equalTo(driver.getCurrentUrl()));
    }
}
