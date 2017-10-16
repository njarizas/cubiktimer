package com.sigolf.juegos.rubik.modelo;

import java.io.Serializable;

/**
 *
 * @author Nelson
 */
public class Celda implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoColor;
    private String sigla;

    public Celda(String color) {
        this.codigoColor = color;
        this.sigla = color.substring(0, 3);
    }

    public String getCodigoColor() {
        return codigoColor;
    }

    public void setCodigoColor(String codigoColor) {
        this.codigoColor = codigoColor;
        this.sigla = codigoColor.substring(0, 3);
    }

    public String getSigla() {
        return sigla;
    }

    @Override
    public String toString() {
        return sigla;
    }

}
