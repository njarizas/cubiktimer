package xyz.njas.config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDatabase {
	
	public static final String DRIVER="com.mysql.jdbc.Driver";
	
	private static Connection conn;
	
//    private String host;
    private String user;
    private String password;
    private String database;
    private String url;
	
	private ConexionDatabase(){
		
		//Version de produccion
//		url="jdbc:mysql://"+VariablesDeEntorno.obtenerHost()+":"+VariablesDeEntorno.obtenerPort()+"/";
//		user=VariablesDeEntorno.obtenerUser();
//		password=VariablesDeEntorno.obtenerPassword();
//		database=VariablesDeEntorno.obtenerDatabase();
		
		
		//Version de desarrollo
		url="jdbc:mysql://localhost:3306/";
		user="root";
		password="";
		database="juegos";
		
		
		try{
		Class.forName(DRIVER).newInstance();
		conn = DriverManager.getConnection(url+database, user, password);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static Connection getInstance(){
		if (conn == null){
			new ConexionDatabase();
		}
		return conn;
	}
	
	public static void close(){
		try {
			conn.close();
			conn=null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
