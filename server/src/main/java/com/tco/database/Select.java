package com.tco.database;

public class Select {

    public final static String RANDOMOPTIONS = " order by rand()";

    public static String match(String match, int limit) {
        if (match.equals(""))
        {
            return statement(match, "DISTINCT " + sqlGuide.COLUMNS, "LIMIT " + checkLimit(limit), RANDOMOPTIONS);
        }
        else
        {
            return statement(match, "DISTINCT " + sqlGuide.COLUMNS, "LIMIT " + checkLimit(limit), "");
        }
    }

    public static String found(String match) {
        return statement(match, "COUNT(*) AS count ", "", "");
    }

    public static String statement(String match, String data, String limit, String options) {
        return "SELECT "
                + data
                + " FROM " + sqlGuide.TABLE
                + " INNER JOIN continent ON world.continent = continent.id"
                + " INNER JOIN country ON world.iso_country = country.id"
                + " INNER JOIN region ON world.iso_region = region.id"
                + " WHERE world.name LIKE \"%" + match + "%\""
                + " OR world.id LIKE \"%" + match + "%\""
                + " OR world.municipality LIKE \"%" + match + "%\""
                + " OR region.name LIKE \"%" + match + "%\""
                + " OR country.name LIKE \"%" + match + "%\""
                + options
                + limit
                + " ;";
    }

    public static int checkLimit(int inputLimit)
    {
        if ((inputLimit > 0) && (inputLimit < 100))
        {
            return inputLimit;
        }
        else
        {
            return 100;
        }
    }
}