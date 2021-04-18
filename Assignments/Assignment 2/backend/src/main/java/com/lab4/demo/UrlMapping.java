package com.lab4.demo;

public class UrlMapping {
    public static final String API_PATH = "/api";
    public static final String BOOKS = API_PATH + "/books";
    public static final String EXPORT_REPORT = "/export/{type}";

    public static final String ENTITY = "/{id}";
    public static final String DESCRIPTION = "/{description}";


    public static final String CHANGE_NAME = "/{id}/name";
    public static final String CHANGE_DESCRIPTION = "/{id}/description";
    public static final String CHANGE_AUTHOR = "/{id}/author";
    public static final String CHANGE_AMOUNT = "/{id}/amount";
    public static final String CHANGE_GENRE = "/{id}/genre";

    public static final String SELL_BOOK = "/sell/{amount}";

    public static final String AUTH = API_PATH + "/auth";
    public static final String SIGN_IN = "/sign-in";
    public static final String SIGN_UP = "/sign-up";

    public static final String USERS = API_PATH + "/users";

}
