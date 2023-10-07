package com.tco.database;

/* Must add a library for Mariadb before execution. */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Exception;

import com.tco.database.DatabaseOperations; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class sqlGuide {
	private static final transient Logger log = LoggerFactory.getLogger(sqlGuide.class);

	public final static String TABLE = "world";
	public final static String COLUMNS = "id,name,municipality,iso_region,iso_country,continent,latitude,longitude,altitude";

	static class Place extends HashMap<String,String> {}
	static class Places extends ArrayList<Place> {}

	static class Database {
		static Integer found(String match) throws Exception {
			String sql = Select.found(match);
			try (
				// connect to the database and query
				Connection conn = DriverManager.getConnection(Credential.url(), Credential.USER, Credential.PASSWORD);
				Statement query = conn.createStatement();
				ResultSet results = query.executeQuery(sql)
			) {
				return DatabaseOperations.count(results);
			} catch (Exception e) {
				throw e;
			}
		}


		static Places places(String match, Integer limit) throws Exception {
			String sql      = Select.match(match, limit);
			String url      = Credential.url();
			String user     = Credential.USER;
			String password = Credential.PASSWORD;
			try (
				// connect to the database and query
				Connection conn    = DriverManager.getConnection(url, user, password);
				Statement  query   = conn.createStatement();
				ResultSet  results = query.executeQuery(sql);
			) {
				return DatabaseOperations.convertQueryResultsToPlaces(results, COLUMNS);
			} catch (Exception e) {
				throw e;
			}
		}
	}
}