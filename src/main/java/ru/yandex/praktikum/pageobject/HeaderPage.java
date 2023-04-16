package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderPage {
    private final WebDriver driver;
    public HeaderPage(WebDriver driver){
        this.driver = driver;
    }


    // Кнопка Конструктор
    private final By constructorButton = By.xpath("//p[text()='Конструктор']");

    // Логотип Stellar Burgers
    private final By stellarBurgersLogo = By.xpath("//div[contains(@class, 'AppHeader_header__logo')]");

    // Кнопка Личный кабинет
    private final By accountButton = By.xpath("//p[text()='Личный Кабинет']");


    // Клик по кнопке Конструктор
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    // Клик по логотипу Stellar Burgers
    public void clickStellarBurgersLogo() {
        driver.findElement(stellarBurgersLogo).click();
    }

    // Клик по кнопке Личный кабинет
    public void clickPersonalAccountButton() {
        driver.findElement(accountButton).click();
    }
}
