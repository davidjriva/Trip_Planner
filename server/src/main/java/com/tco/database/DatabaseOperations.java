package com.tco.database;

import java.sql.ResultSet;
import com.tco.database.sqlGuide.Place;
import com.tco.database.sqlGuide.Places;

public class DatabaseOperations {

    public static Integer count(ResultSet results) throws Exception {
        if (results.next()) {
            return results.getInt("count");
        }
        throw new Exception("No count results in the found query.");
    }

    public static Places convertQueryResultsToPlaces(ResultSet results, String columns) throws Exception {
        int count = 0;
        String[] cols = columns.split(",");
        Places places = new Places();
        while (results.next()) {
            Place place = new Place();
            for (String col : cols) {
                place.put(col, results.getString(col));
            }
            //IMPORTANT: BELOW STATEMENT IS USED TO DISPLAY EACH PLACE TO TERMINAL, UNCOMMENT TO SEE:
			//log.error(place.toString());
            place.put("index", String.format("%d", ++count));
            places.add(place);
        }
        return places;
    }
}