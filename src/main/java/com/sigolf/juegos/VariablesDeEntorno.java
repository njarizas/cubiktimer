package com.sigolf.juegos;

/**
 *
 * @author Nelson
 */
public class VariablesDeEntorno {

    public VariablesDeEntorno() {
    
    }

    public static String obtenerHost() {
        return System.getenv("OPENSHIFT_MYSQL_DB_HOST");
    }
    
    public static String obtenerPort() {
        return System.getenv("OPENSHIFT_MYSQL_DB_PORT");
    }

    public static String obtenerUser() {
        return System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
    }

    public static String obtenerPassword() {
        return System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
    }

    public static String obtenerDatabase() {
        return System.getenv("OPENSHIFT_APP_NAME");
    }

    public static String obtenerUrl() {
        return System.getenv("OPENSHIFT_MYSQL_DB_URL");
    }

}