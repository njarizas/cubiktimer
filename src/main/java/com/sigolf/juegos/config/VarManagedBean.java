package com.sigolf.juegos.config;

import java.sql.Connection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Nelson
 */
@ManagedBean
@ViewScoped
public class VarManagedBean {

    private String host;
    private String user;
    private String password;
    private String name;
    private String url;
    
    private Connection connection;

    public VarManagedBean() {
        host = obtenerHost();
        user = obtenerUser();
        password = obtenerPassword();
        name = obtenerName();
        url = jdkVersion();
        System.out.println("host.............: " + host);
        System.out.println("user.............: " + user);
        System.out.println("password.........: " + password);
        System.out.println("name.............: " + name);
        System.out.println("url..............: " + url);
        connection = ConexionDatabase.getInstance();
    }

    public static String obtenerHost() {
        return System.getenv("OPENSHIFT_MYSQL_DB_HOST");
    }

    public static String obtenerUser() {
        return System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
    }

    public static String obtenerPassword() {
        return System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
    }

    public static String obtenerName() {
        return System.getenv("OPENSHIFT_APP_NAME");
    }

    public static String jdkVersion() {
        return System.getenv("OPENSHIFT_MYSQL_DB_URL");
    }

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
