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
                + " INNER JOIN continent ON world.continent = continent.id"
                + " INNER JOIN country ON world.iso_country = country.id"
                + " INNER JOIN region ON world.iso_region = region.id"
                + " WHERE name LIKE \"%" + match + "%\" "
                + limit
                + " ;";
    }
}