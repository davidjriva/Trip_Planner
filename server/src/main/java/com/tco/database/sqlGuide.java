package com.tco.database;

/* Must add a library for Mariadb before execution. */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Exception;

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
				return count(results);
			} catch (Exception e) {
				throw e;
			}
		}

		private static Integer count(ResultSet results) throws Exception {
			if (results.next()) {
				return results.getInt("count");
			}
			throw new Exception("No count results in found query.");
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
				return convertQueryResultsToPlaces(results, COLUMNS);
			} catch (Exception e) {
				throw e;
			}
		}


		private static Places convertQueryResultsToPlaces(ResultSet results, String columns) throws Exception {
			int count = 0;
			String[] cols = columns.split(",");
			Places places = new Places();
			while (results.next()) {
				Place place = new Place();
				for (String col: cols) {
					place.put(col, results.getString(col));
				}
				place.put("index", String.format("%d",++count));

				//IMPORTANT: BELOW STATEMENT IS USED TO DISPLAY EACH PLACE TO TERMINAL, UNCOMMENT TO SEE:
				//log.error(place.toString());
				places.add(place);
			}
			return places;
		}
	}
}