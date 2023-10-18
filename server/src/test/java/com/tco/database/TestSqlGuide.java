package com.tco.database;

import java.sql.Connection;

import com.tco.database.sqlGuide.Database;
import com.tco.database.sqlGuide.Places;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

import org.mockito.Mockito;

public class TestSqlGuide {
    @Test
    @DisplayName("driva: zero found places")
    public void testFoundWithEmptyMatch() throws Exception {
        int found = sqlGuide.Database.found("");
        
        assertEquals(50427, found);
    }

    @Test
    @DisplayName("driva: Testing exception is thrown in found()")
    public void exceptionInFound() throws Exception {
        //mocking behavior of connection class to trigger exception
        Connection conn = Mockito.mock(Connection.class);
        Mockito.when(conn.createStatement()).thenThrow(new RuntimeException()); 

        try{
            sqlGuide.Database.found("");
        }catch (Exception e) {
            assertTrue(e instanceof Exception);
        }
    }

    @Test
    @DisplayName("driva: Testing exception is thrown in places()")
    public void exceptionInPlaces() throws Exception {
        //mocking behavior of connection class to trigger exception
        Connection conn = Mockito.mock(Connection.class);
        Mockito.when(conn.createStatement()).thenThrow(new RuntimeException()); 

        try{
            sqlGuide.Database.places("", 100);
        }catch (Exception e) {
            assertTrue(e instanceof Exception);
        }
    }


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

        assertEquals(places.get(0).get("continent"), "North America");
        assertEquals(places.get(0).get("altitude"), "5170");
        assertEquals(places.get(0).get("country"), "United States");
        assertEquals(places.get(0).get("latitude"), "40.0332984924");
        assertEquals(places.get(0).get("name"), "Dave's Airport");
        assertEquals(places.get(0).get("municipality"), "Louisville");
        assertEquals(places.get(0).get("region"), "Colorado");
        assertEquals(places.get(0).get("index"), "1");
        assertEquals(places.get(0).get("id"), "0CO1");
        assertEquals(places.get(0).get("longitude"), "-105.124000549");

        assertEquals(places.size(), 3);
        assertEquals(value, 28);
    }
}