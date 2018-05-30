package xyz.njas.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ScrambleGenerator {

	public static String[] generarMezcla(String parametro) {
		System.out.println("Va a generar la mezcla con el Software oficial de la WCA");
		URL url;
		String linea;
		StringBuffer stringBuffer = new StringBuffer();
		boolean esConnectException=false;
		try {
			//se intenta obtener la conexion por metodo get
			url = new URL("http://localhost:2014/scramble/.txt?="+parametro);
			URLConnection con = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			stringBuffer = new StringBuffer();
			while ((linea = in.readLine()) != null) {
				System.out.println("mezcla generada en el primer intento: " + linea);
				stringBuffer.append(linea);
			}
			//si se genera ConnectException tal vez es por que no se esta ejecutando el TNoodle
		} catch (ConnectException ce) {
			esConnectException = true;
			System.out.println(ce.getMessage());
		} catch (MalformedURLException murle) {
			System.out.println(murle.getMessage());
			murle.printStackTrace();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
			ioe.printStackTrace();
		}
		try {
			if(esConnectException) {
				//se trata de ejecutar el TNoodle
				String realPath = Util.getRealPath();
				System.out.println("Se hace el llamado para que se ejecute el jar java -jar "+realPath+"resources/jars/TNoodle-WCA-0.13.3.jar");
				Runtime.getRuntime().exec("java -jar "+realPath+"resources/jars/TNoodle-WCA-0.13.3.jar");
				// Creando un objeto URL
				url = new URL("http://localhost:2014/scramble/.txt?="+parametro);
				// Realizando la petición GET
				URLConnection con = url.openConnection();
				// Leyendo el resultado
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				stringBuffer = new StringBuffer();
				while ((linea = in.readLine()) != null) {
					System.out.println("mezcla generada en el segundo intento: " + linea);
					stringBuffer.append(linea);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringBuffer.toString().split(" ");
	}
	
}
