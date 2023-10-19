package com.tco.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.tco.database.DatabaseConnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class TestDatabase {
    
    private static final transient Logger log = LoggerFactory.getLogger(TestDatabase.class);

    @Test
    @DisplayName("alexr11: test DatabaseConnector")
    public void testConenctor() {
        Connection conn;
        ResultSet results;
        try {
        conn = DatabaseConnector.connect();
        assertEquals(conn.isValid(500), true);
        results = DatabaseConnector.executeQuery(conn, "select * from world limit 5;");
        assertEquals(results.getInt("found"), 2);
        DatabaseConnector.closeConnection(conn);
        assertEquals(conn.isValid(500), false);
        } catch(Exception e){
            log.error("Error with: " + e.getMessage());
        
        }
    }
}