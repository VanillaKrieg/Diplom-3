package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private final WebDriver driver;
    public ForgotPasswordPage(WebDriver driver){
        this.driver = driver;
    }


    // Ссылка на страницу
    public static final String FORGOT_PASSWORD_PAGE_LINK = "https://stellarburgers.nomoreparties.site/forgot-password";

    // Кнопка Войти
    private final By enterButton = By.xpath("//a[text()='Войти']");


    // Клик по кнопке Войти
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }
}
