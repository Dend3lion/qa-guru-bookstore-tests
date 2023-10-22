package com.demoqa.tests;

import com.demoqa.models.CredentialsModel;

public class TestData {
    public static String username = "TesterTestovihTestov",
            password = "TesterTestovihTestov!2",
            bookIsbn = "9781449325862",
            bookId = "see-book-Git Pocket Guide";

    public static CredentialsModel credentials = new CredentialsModel(username, password);
}
