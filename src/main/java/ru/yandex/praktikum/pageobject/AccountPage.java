package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AccountPage {
    private final WebDriver driver;
    public AccountPage(WebDriver driver){
        this.driver = driver;
    }


    // Ссылка на страницу
    public static final String ACCOUNT_PAGE_LINK = "https://stellarburgers.nomoreparties.site/account";
    public static final String ACCOUNT_PROFILE_PAGE_LINK = "https://stellarburgers.nomoreparties.site/account/profile";

    // Кнопка Выход
    private final By exitAccountButton = By.xpath("//button[text()='Выход']");


    // Проверка перехода на страницу Личного кабинета
    public void assertCurrentUrl() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.urlToBe((ACCOUNT_PROFILE_PAGE_LINK)));
        assertThat("Происходит переход на страницу Личного кабинета", ACCOUNT_PROFILE_PAGE_LINK, equalTo(driver.getCurrentUrl()));
    }

    // Клик по кнопке Выход
    public void clickExitAccountButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable((exitAccountButton)));
        driver.findElement(exitAccountButton).click();
    }
}
