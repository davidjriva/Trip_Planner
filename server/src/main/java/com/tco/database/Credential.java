package com.tco.database;

public class Credential {
    final static String USER = "cs314-db";
    final static String PASSWORD = "eiK5liet1uej";
    final static String URL = "jdbc:mariadb://127.0.0.1:56247/cs314";

    static String url() {
        return URL;
    }
}