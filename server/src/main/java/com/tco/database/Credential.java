package com.tco.database;

public class Credential {
    final static String USER = "cs314-db";
    final static String PASSWORD = "eiK5liet1uej";
    
    static String url() {
        String useTunnel = System.getenv("CS314_USE_DATABASE_TUNNEL");
        String onDocker = System.getenv("CS314_DOCKER");
        // Note that if the variable isn't defined, System.getenv will return null
        if(useTunnel != null && useTunnel.equals("true")) {
            return dburl = "jdbc:mariadb://127.0.0.1:56247/cs314";
        }
        else if(onDocker != null && onDocker.equals("true")) {
            return dburl = "jdbc:mariadb://127.0.0.1:3306/cs314";
        }
        else {
            return dburl = "jdbc:mariadb://faure.cs.colostate.edu/cs314";
        }
    }
}