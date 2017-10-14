<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Conexion</title>
</head>
<body>
<% 
try{
	String url="jdbc:mysql://127.7.244.2:3306/juegos";
	Class.forName("com.mysql.jdbc.Driver");
	Connection con= (Connection)DriverManager.getConnection(url, "adminQQfgpiJ", "sbCeGt4GlKjN");
	if (con!=null){
	out.println("Se conecto");
	}
}
catch (Exception e){
	out.println("error de conexion");

}%>
</body>
</html>