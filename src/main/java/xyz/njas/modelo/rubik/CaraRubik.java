package xyz.njas.modelo.rubik;

import java.io.Serializable;

/**
 * Clase que representa una cara de cubo rubik nxn
 * @author Nelson Ariza
 *
 */
public class CaraRubik implements Serializable {

	private static final long serialVersionUID = 1L;

	private int n;
	private Celda[][] cara;

	public CaraRubik(int n, String color) {
		super();
		this.n = n;
		this.cara = new Celda[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cara[i][j] = new Celda(color);
			}
		}
	}
	
	/**
	 * Metodo que se encarga de girar la cara una sola vez en sentido de las agujas del reloj
	 */
	public void girarCara() {
		for (int i = 0; i < this.n / 2; i++) {
			for (int j = i; j < this.n - 1 - i; j++) {
				Celda celdaTempo;
				celdaTempo = cara[i][j];
				cara[i][j] = cara[n - 1 - j][i];
				cara[n - 1 - j][i] = cara[n - i - 1][n - 1 - j];
				cara[n - i - 1][n - 1 - j] = cara[j][n - i - 1];
				cara[j][n - i - 1] = celdaTempo;
			}
		}
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public Celda[][] getCara() {
		return cara;
	}

	public void setCara(Celda[][] cara) {
		this.cara = cara;
	}
	
	@Override
	public String toString() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < cara[i].length; j++) {
				System.out.print((cara[i][j] != null) ? cara[i][j] + "\t" : "\t");
			}
			System.out.println();
		}
		return "";
	}

}
