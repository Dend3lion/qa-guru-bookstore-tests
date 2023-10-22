package com.demoqa.api;

import com.demoqa.models.AddBooksListModel;
import com.demoqa.models.DeleteBookModel;
import com.demoqa.models.LoginResponseModel;
import io.qameta.allure.Step;

import static com.demoqa.specs.BooksSpec.*;
import static io.restassured.RestAssured.given;

public class BooksApi {
    @Step("Добавляем книгу")
    public void addBook(LoginResponseModel loginResponse, AddBooksListModel booksList) {
        given(booksRequestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(booksList)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(books201ResponseSpec);
    }

    @Step("Удаляем книгу пользователя")
    public void deleteBook(LoginResponseModel loginResponse, DeleteBookModel deleteBook) {
        given(booksRequestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .body(deleteBook)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(books204ResponseSpec);
    }

    @Step("Удаляем все книги пользователя")
    public void deleteAllBooks(LoginResponseModel loginResponse) {
        given(booksRequestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(books204ResponseSpec);
    }
}
