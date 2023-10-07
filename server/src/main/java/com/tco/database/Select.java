package com.tco.database;

public class Select {

    public static String match(String match, int limit) {
        return statement(match, "DISTINCT " + sqlGuide.COLUMNS, "LIMIT " + limit);
    }

    public static String found(String match) {
        return statement(match, "COUNT(*) AS count ", "");
    }

    public static String statement(String match, String data, String limit) {
        return "SELECT "
                + data
                + " FROM " + sqlGuide.TABLE
                + " WHERE name LIKE \"%" + match + "%\" "
                + limit
                + " ;";
    }
}