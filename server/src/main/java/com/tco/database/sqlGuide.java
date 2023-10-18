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
	public final static String COLUMNS = "world.id as id,world.name as name,municipality,continent.name as continent,country.name as country,region.name as region,latitude,longitude,altitude";
	public final static String DISPLAYCOLUMNS = "id,name,municipality,continent,country,region,latitude,longitude,altitude";

	public static class Place extends HashMap<String,String> {}
	public static class Places extends ArrayList<Place> {}

	public static class Database {
		public static Integer found(String match) throws Exception {
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

		public static Places places(String match, Integer limit) throws Exception {
			String sql = Select.match(match, limit);
			String url = Credential.url();
			String user = Credential.USER;
			String password = Credential.PASSWORD;
			
			try (
				// connect to the database and query
				Connection conn = DriverManager.getConnection(url, user, password);
				Statement query = conn.createStatement();
				ResultSet results = query.executeQuery(sql);
			) {
				return DatabaseOperations.convertQueryResultsToPlaces(results, DISPLAYCOLUMNS);
			} catch (Exception e) {
				throw e;
			}
		}
	}
}
