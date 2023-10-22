package com.demoqa.pages;

import com.demoqa.models.LoginResponseModel;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProfilePage {
    @Step("Устанавливаем авторизационные куки")
    public static void appendProfileCookies(LoginResponseModel loginResponse) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));
    }

    @Step("Открываем профиль пользователя")
    public static void openProfile() {
        open("/profile");
    }

    @Step("В профиле отсутсвует удаленная книга")
    public static void checkDeletedBookNotPresent(String bookId) {
        $("[id='" + bookId + "']").shouldNotBe(visible);
    }
}
