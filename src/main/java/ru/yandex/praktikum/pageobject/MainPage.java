package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MainPage {
    private final WebDriver driver;
    public MainPage(WebDriver driver){
        this.driver = driver;
    }


    // Ссылка на страницу
    public static final String MAIN_PAGE_LINK = "https://stellarburgers.nomoreparties.site/";

    // Кнопка Войти в аккаунт
    private final By enterAccountButton = By.xpath("//button[text()='Войти в аккаунт']");

    // Кнопка Оформить заказ
    private final By placeOrderButton = By.xpath("//button[text()='Оформить заказ']");

    // Вкладка Булки неактивная
    private final By bunsTabInactive = By.xpath("//div[contains(span/text(),'Булки') and not(contains(@class,'current'))]");

    // Вкладка Соусы неактивная
    private final By saucesTabInactive = By.xpath("//div[contains(span/text(),'Соусы') and not(contains(@class,'current'))]");

    // Вкладка Начинки неактивная
    private final By fillingsTabInactive = By.xpath("//div[contains(span/text(),'Начинки') and not(contains(@class,'current'))]");

    // Вкладка Булки активная
    private final By bunsTabActive = By.xpath("//div[contains(span/text(),'Булки') and contains(@class,'current')]");

    // Вкладка Соусы активная
    private final By saucesTabActive= By.xpath("//div[contains(span/text(),'Соусы') and contains(@class,'current')]");

    // Вкладка Начинки активная
    private final By fillingsTabActive= By.xpath("//div[contains(span/text(),'Начинки') and contains(@class,'current')]");


    // Клик по кнопке Войти в аккаунт
    public void clickEnterAccountButton() {
        driver.findElement(enterAccountButton).click();
    }

    // Клик по вкладке Булки
    public void clickBunsTab() {
        driver.findElement(bunsTabInactive).click();
    }

    // Клик по вкладке Соусы
    public void clickSaucesTab() {
        driver.findElement(saucesTabInactive).click();
    }

    // Клик по вкладке Начинки
    public void clickFillingsTab() {
        driver.findElement(fillingsTabInactive).click();
    }

    // Проверка активности вкладки Булки
    public void assertBunsTabActiveIsDisplayed() {
        assertThat("Вкладки Булки активна", true, equalTo(driver.findElement(bunsTabActive).isDisplayed()));
    }

    // Проверка активности вкладки Соусы
    public void assertSaucesTabActiveIsDisplayed() {
        assertThat("Вкладки Соусы активна", true, equalTo(driver.findElement(saucesTabActive).isDisplayed()));
    }

    // Проверка активности вкладки Начинки
    public void assertFillingsTabActiveIsDisplayed() {
        assertThat("Вкладки Начинки активна", true, equalTo(driver.findElement(fillingsTabActive).isDisplayed()));
    }

    // Проверка отображения кнопки Оформить заказ
    public void assertPlaceOrderButtonIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton));
        assertThat("Отображается кнопка Оформить заказ", true, equalTo(driver.findElement(placeOrderButton).isDisplayed()));
    }

    // Проверка отображения кнопки Войти в аккаунт
    public void assertEnterAccountButtonIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(enterAccountButton));
        assertThat("Отображается кнопка Войти в аккаунт", true, equalTo(driver.findElement(enterAccountButton).isDisplayed()));
    }

    // Проверка перехода на Главную страницу
    public void assertCurrentUrl() {
        assertThat("Происходит переход на Главную страницу", MAIN_PAGE_LINK, equalTo(driver.getCurrentUrl()));
    }
}
