package ru.appline.sberbank;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class SberbankTest extends Setup {



    @Test
    void mainTest(){
        $(By.xpath("//label[contains(text(), 'Карты')]")).click();
        $(By.xpath("//li[@class='kitt-top-menu__item']/a[contains(text(), 'Дебетовые')]")).click();
        $(By.xpath("//div[@class='kit-row']/h2")).shouldHave(exactText("Дебетовые карты"));
        $(By.xpath("//label[text()='Молодежная']")).click();
        $(By.xpath("//h3[text()='Молодежная карта']/../../..//b")).click();
        $(By.xpath("//h1[text()='Молодёжная карта']")).scrollTo().shouldHave(exactText("Молодёжная карта"));
        $(By.xpath("//span[text()='Оформить онлайн']/..")).click();
        $(By.xpath("//input[@placeholder='Иванов']")).setValue("Фамилия").shouldHave(value("Фамилия"));
        $(By.xpath("//input[@placeholder='Иван']")).setValue("Имя").shouldHave(value("Имя"));
        $(By.xpath("//input[@placeholder='Иванович']")).setValue("Отчество").shouldHave(value("Отчество"));
        $(By.xpath("//input[@placeholder='IVAN IVANOV']")).setValue("IMYA FAMILIYA").shouldHave(value("IMYA FAMILIYA"));
        $(By.id("odc-personal__birthDate")).setValue("04.06.2000").shouldHave(value("04.06.2000"));
        $(By.xpath("//input[@placeholder='example@example.com']")).setValue("ya@ya.ru").shouldHave(value("ya@ya.ru"));
        $(By.id("odc-personal__phone")).scrollTo().click();
        $(By.id("odc-personal__phone")).sendKeys("(123) 456-78-90");
        $(By.id("odc-personal__phone")).shouldHave(value("+7 (123) 456-78-90"));
        $(By.xpath("//span[text()='Далее']/..")).click();

        $(By.xpath("//label[text()='Серия']/../div[@class='odcui-error__text']")).shouldHave(text("Обязательное поле"));
        $(By.xpath("//label[text()='Номер']/../div[@class='odcui-error__text']")).shouldHave(text("Обязательное поле"));
        $(By.xpath("//label[text()='Дата выдачи']/../div[@class='odcui-error__text']")).shouldHave(text("Обязательное поле"));
        $(By.xpath("//div[text()='Я соглашаюсь на']/div[@class='odcui-error__text']")).shouldHave(text("Обязательное поле"));
    }
}
