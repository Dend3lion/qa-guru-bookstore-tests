package com.demoqa.api;

import com.demoqa.models.CredentialsModel;
import com.demoqa.models.LoginResponseModel;
import io.qameta.allure.Step;

import static com.demoqa.specs.LoginSpec.loginRequestSpec;
import static com.demoqa.specs.LoginSpec.login200ResponseSpec;
import static io.restassured.RestAssured.given;


public class AuthorizationApi {
    @Step("Авторизируем пользователя")
    public LoginResponseModel login(CredentialsModel credentials) {
        return given(loginRequestSpec)
                .body(credentials)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(login200ResponseSpec)
                .extract().as(LoginResponseModel.class);
    }
}
