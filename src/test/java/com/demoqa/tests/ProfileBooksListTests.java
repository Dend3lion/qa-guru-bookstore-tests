package com.demoqa.tests;

import com.demoqa.models.AddBooksListModel;
import com.demoqa.models.DeleteBookModel;
import com.demoqa.models.IsbnModel;
import com.demoqa.models.LoginResponseModel;
import com.demoqa.pages.ProfilePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.demoqa.tests.TestData.*;

public class ProfileBooksListTests extends TestBase {
    @Test
    @DisplayName("Добавление книги в профиле")
    void addBookToProfileTest() {
        LoginResponseModel loginResponse = authorizationApi.login(credentials);
        booksApi.deleteAllBooks(loginResponse);

        IsbnModel isbnModel = new IsbnModel();
        isbnModel.setIsbn(bookIsbn);
        List<IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbnModel);

        AddBooksListModel booksList = new AddBooksListModel();
        booksList.setUserId(loginResponse.getUserId());
        booksList.setCollectionOfIsbns(isbnList);

        booksApi.addBook(loginResponse, booksList);

        ProfilePage.openProfileWithCookies(loginResponse);
        ProfilePage.checkAddedBookIsPresent(bookId);
    }

    @Test
    @DisplayName("Удаление книги в профиле")
    void deleteBookFromProfileTest() {
        LoginResponseModel loginResponse = authorizationApi.login(credentials);

        IsbnModel isbnModel = new IsbnModel();
        isbnModel.setIsbn(bookIsbn);

        DeleteBookModel deleteBook = new DeleteBookModel();
        deleteBook.setIsbn(isbnModel);
        deleteBook.setUserId(loginResponse.getUserId());

        booksApi.deleteBook(loginResponse, deleteBook);

        ProfilePage.openProfileWithCookies(loginResponse);
        ProfilePage.checkDeletedBookNotPresent(bookId);
    }
}
