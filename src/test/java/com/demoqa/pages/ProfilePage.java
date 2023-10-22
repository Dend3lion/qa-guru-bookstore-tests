package com.demoqa.pages;

import com.demoqa.models.LoginResponseModel;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProfilePage {
    public static void openProfileWithCookies(LoginResponseModel loginResponse) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));
    }

    @Step("В профиле отсутсвует удаленная книга")
    public static void checkDeletedBookNotPresent(String bookId) {
        open("/profile");
        $("[id='" + bookId + "']").shouldNotBe(visible);
    }

    @Step("В профиле присутсвует добавленная книга")
    public static void checkAddedBookIsPresent(String bookId) {
        open("/profile");
        $("[id='" + bookId + "']").shouldBe(visible);
    }
}
