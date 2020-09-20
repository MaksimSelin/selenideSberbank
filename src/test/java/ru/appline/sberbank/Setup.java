package ru.appline.sberbank;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class Setup {

    @BeforeEach
    void setup(){
        Configuration.browser = "chrome";
        Configuration.startMaximized=true;
        open("http://www.sberbank.ru/ru/person");
    }

}
