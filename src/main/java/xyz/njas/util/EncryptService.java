/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.njas.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

/**
 *
 * @author Nelson
 */
public class EncryptService {
	
	private static final Logger log = Logger.getLogger(EncryptService.class);
	
	private EncryptService() {
		
	}

    //algoritmos
    public static final String MD2 = "MD2";
    public static final String MD5 = "MD5";
    public static final String SHA1 = "SHA-1";
    public static final String SHA256 = "SHA-256";
    public static final String SHA384 = "SHA-384";
    public static final String SHA512 = "SHA-512";

    /**
     * Convierte un arreglo de bytes a String usando valores hexadecimales
     *
     * @param digest arreglo de bytes a convertir
     * @return String creado a partir de <code>digest</code>
     */
    private static String toHexadecimal(byte[] digest) {
        StringBuilder hash = new StringBuilder("");
        for (byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) {
                hash.append("0");
            }
            hash.append(Integer.toHexString(b));
        }
        return hash.toString();
    }

    /**
     * Encripta un mensaje de texto mediante algoritmo de resumen de mensaje.
     *
     * @param message texto a encriptar
     * @param algorithm algoritmo de encriptacion, puede ser: MD2, MD5, SHA-1,
     * SHA-256, SHA-384, SHA-512
     * @return mensaje encriptado
     */
    public static String getStringMessageDigest(String message, String algorithm) {
        byte[] digest = null;
        byte[] buffer = message.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.reset();
            messageDigest.update(buffer);
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException ex) {
            log.warn("Error creando Digest" + ex);
        }
        return toHexadecimal(digest);
    }
    
    /**
     * Método que encripta un String con un algoritmo personalizado usando SHA1 y MD5<br />
     * si se recibe una cadena vacía retorna null
     * @param clave cadena de texto que se desea encriptar
     * @return <code>String</code> cadena de texto encriptada con longitud fija de 32 caracteres
     */
    public static String encriptarClave(String clave){
        if (!clave.isEmpty()) {
            StringBuilder cadena = new StringBuilder(EncryptService.getStringMessageDigest(clave, EncryptService.SHA1));
            return EncryptService.getStringMessageDigest(cadena.toString()+cadena.reverse().toString(), EncryptService.MD5);
        } else {
            return null;
        }
    }
}