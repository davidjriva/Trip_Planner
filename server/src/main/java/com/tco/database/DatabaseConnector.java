package com.tco.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnector {

    public static Connection connect() throws Exception {
        return DriverManager.getConnection(Credential.url(), Credential.USER, Credential.PASSWORD);
    }

    public static void closeConnection(Connection conn) throws Exception {
        if (conn != null) {
            conn.close();
        }
    }

    public static ResultSet executeQuery(Connection conn, String sql) throws Exception {
        Statement query = conn.createStatement();
        return query.executeQuery(sql);
    }
}