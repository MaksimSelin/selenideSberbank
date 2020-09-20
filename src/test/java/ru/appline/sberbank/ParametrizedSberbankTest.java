package ru.appline.sberbank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.close;

public class ParametrizedSberbankTest extends Setup{

    @ParameterizedTest
    @ValueSource(strings = {"Иванов_Иван_Иванович_IVAN IVANOV_04.06.2000_email1@email.ru_(123) 456-78-91",
            "Афонина_Алена_Петровна_ALENA PETROVNA_04.06.1999_email2@email.ru_(123) 456-78-92",
            "Лысенко_Олег_Владимирович_OLEG LISENKO_04.06.1998_email3@email.ru_(123) 456-78-93"})
    void mainTest(String str) {
        List<String> paramList = new ArrayList<>(Arrays.asList(str.split("_")));
        $(By.xpath("//label[contains(text(), 'Карты')]")).click();
        $(By.xpath("//li[@class='kitt-top-menu__item']/a[contains(text(), 'Дебетовые')]")).click();
        $(By.xpath("//div[@class='kit-row']/h2")).shouldHave(exactText("Дебетовые карты"));
        $(By.xpath("//label[text()='Молодежная']")).click();
        $(By.xpath("//h3[text()='Молодежная карта']/../../..//b")).click();
        $(By.xpath("//h1[text()='Молодёжная карта']")).scrollTo().shouldHave(exactText("Молодёжная карта"));
        $(By.xpath("//input[@placeholder='Иванов']")).setValue(paramList.get(0)).shouldHave(value(paramList.get(0)));
        $(By.xpath("//input[@placeholder='Иван']")).setValue(paramList.get(1)).shouldHave(value(paramList.get(1)));
        $(By.xpath("//input[@placeholder='Иванович']")).setValue(paramList.get(2)).shouldHave(value(paramList.get(2)));
        $(By.xpath("//input[@placeholder='IVAN IVANOV']")).setValue(paramList.get(3)).shouldHave(value(paramList.get(3)));
        $(By.id("odc-personal__birthDate")).clear();
        $(By.id("odc-personal__birthDate")).setValue(paramList.get(4)).shouldHave(value(paramList.get(4)));
        $(By.xpath("//input[@placeholder='example@example.com']")).setValue(paramList.get(5)).shouldHave(value(paramList.get(5)));
        $(By.id("odc-personal__phone")).scrollTo().click();
        $(By.id("odc-personal__phone")).sendKeys(paramList.get(6));
        $(By.id("odc-personal__phone")).shouldHave(value("+7 " + paramList.get(6)));
        $(By.xpath("//span[text()='Далее']/..")).click();

        $(By.xpath("//label[text()='Серия']/../div[@class='odcui-error__text']")).shouldHave(text("Обязательное поле"));
        $(By.xpath("//label[text()='Номер']/../div[@class='odcui-error__text']")).shouldHave(text("Обязательное поле"));
        $(By.xpath("//label[text()='Дата выдачи']/../div[@class='odcui-error__text']")).shouldHave(text("Обязательное поле"));
        $(By.xpath("//div[text()='Я соглашаюсь на']/div[@class='odcui-error__text']")).shouldHave(text("Обязательное поле"));
        close();
    }
}
