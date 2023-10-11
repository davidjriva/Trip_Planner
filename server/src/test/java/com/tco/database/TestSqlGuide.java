package com.tco.database;

import com.tco.database.sqlGuide.Database;
import com.tco.database.sqlGuide.Places;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

public class TestSqlGuide {
    @Test
    @DisplayName("driva: Check DB Search")
    public void testSearch() throws Exception {
        int value;
        Places places;
        try{
            value = Database.found("dave");
            places = Database.places("dave", 3);

            assertNotNull(places);
            assertFalse(places.isEmpty());
        } catch(Exception e){
            throw new Exception("Error with: " + e.getMessage());
        }


        assertEquals(places.get(0).get("world.continent"), "NA");
        assertEquals(places.get(0).get("altitude"), "5170");
        assertEquals(places.get(0).get("world.iso_country"), "US");
        assertEquals(places.get(0).get("latitude"), "40.0332984924");
        assertEquals(places.get(0).get("world.name"), "Dave's Airport");
        assertEquals(places.get(0).get("municipality"), "Louisville");
        assertEquals(places.get(0).get("iso_region"), "US-CO");
        assertEquals(places.get(0).get("index"), "1");
        assertEquals(places.get(0).get("world.id"), "0CO1");
        assertEquals(places.get(0).get("longitude"), "-105.124000549");

        assertEquals(places.size(), 3);
        assertEquals(value, 16);
    }
}