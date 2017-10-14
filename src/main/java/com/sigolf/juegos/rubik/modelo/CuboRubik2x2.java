package com.sigolf.juegos.rubik.modelo;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Nelson
 */
public class CuboRubik2x2 extends CuboRubik implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Celda[][] cubo = new Celda[8][6];

	public CuboRubik2x2(Integer idTipoCubo, String nombre) {
		super(idTipoCubo, nombre);
		this.setIdTipoCubo(5);
		for (int i = 0; i < cubo.length; i++) {
			for (int j = 0; j < cubo[i].length; j++) {
				cubo[i][j] = null;
			}
		}
		//TOP
		cubo[0][2] = new Celda("white");
		cubo[0][3] = new Celda("white");
		cubo[1][2] = new Celda("white");
		cubo[1][3] = new Celda("white");
		//LEFT
		cubo[2][0] = new Celda("#ff6702");
		cubo[2][1] = new Celda("#ff6702");
		cubo[3][0] = new Celda("#ff6702");
		cubo[3][1] = new Celda("#ff6702");
		//FRONT
		cubo[2][2] = new Celda("green");
		cubo[2][3] = new Celda("green");
		cubo[3][2] = new Celda("green");
		cubo[3][3] = new Celda("green");
		//RIGHT
		cubo[2][4] = new Celda("red");
		cubo[2][5] = new Celda("red");
		cubo[3][4] = new Celda("red");
		cubo[3][5] = new Celda("red");
		//DOWN
		cubo[4][2] = new Celda("yellow");
		cubo[4][3] = new Celda("yellow");
		cubo[5][2] = new Celda("yellow");
		cubo[5][3] = new Celda("yellow");
		//BACK
		cubo[6][2] = new Celda("blue");
		cubo[6][3] = new Celda("blue");
		cubo[7][2] = new Celda("blue");
		cubo[7][3] = new Celda("blue");

	}
	
	@Override
    public String[] generarMezcla() {
        String[] movimientos = {"F", "F'", "F2", "U", "U'", "U2", "R", "R'", "R2"};
        int num;
        int[] mezclaInt = new int[9];
        String[] mezcla = new String[mezclaInt.length];
        Random random = new Random();
        for (int i = 0; i < mezcla.length; i++) {
            num = random.nextInt(movimientos.length);
            if (i != 0) {
                while (mezclaInt[i - 1] / 3 == num / 3) {
                    num = random.nextInt(movimientos.length);
                }
            }
            mezclaInt[i] = num;
            mezcla[i] = movimientos[mezclaInt[i]];
        }
        return mezcla;
    }

	@Override
	public String mezclar(String[] mezcla) {
		String secuenciaMezclada="";
		for (String giro : mezcla) {
			if (girar(giro)){
				secuenciaMezclada+=giro.toUpperCase()+" ";
			}
		}
		return secuenciaMezclada;
	}

	@Override
	public synchronized boolean girar(String giro) {
		giro = giro.toLowerCase().trim();
		if (giro.equals("f")){
			f(1);
		} else if (giro.equals("f2")){
			f(2);
		} else if (giro.equals("f'")){
			f(3);
		} else if (giro.equals("u")){
			u(1);
		} else if (giro.equals("u2")){
			u(2);
		} else if (giro.equals("u'")){
			u(3);
		} else if (giro.equals("r")){
			r(1);
		} else if (giro.equals("r2")){
			r(2);
		} else if (giro.equals("r'")){
			r(3);
		} else if (giro.equals("l")){
			l(1);
		} else if (giro.equals("l2")){
			l(2);
		} else if (giro.equals("l'")){
			l(3);
		} else if (giro.equals("b")){
			b(1);
		} else if (giro.equals("b2")){
			b(2);
		} else if (giro.equals("b'")){
			b(3);
		} else if (giro.equals("d")){
			d(1);
		} else if (giro.equals("d2")){
			d(2);
		} else if (giro.equals("d'")){
			d(3);
		} else if (giro.equals("z")){
			z(1);
		} else if (giro.equals("z2")){
			z(2);
		} else if (giro.equals("z'")){
			z(3);
		} else if (giro.equals("x")){
			x(1);
		} else if (giro.equals("x2")){
			x(2);
		} else if (giro.equals("x'")){
			x(3);
		} else if (giro.equals("y")){
			y(1);
		} else if (giro.equals("y2")){
			y(2);
		} else if (giro.equals("y'")){
			y(3);
		} else{
			System.out.println("giro no válido");
			return false;
		}
		return true;
	}

	@Override
	public void f(int cant) {
		for (int i = 0; i < cant; i++) {
			f();
		}
	}

	@Override
	public void f() {
		Celda celdaTempo;
		celdaTempo = cubo[2][2];
		cubo[2][2] = cubo[3][2];
		cubo[3][2] = cubo[3][3];
		cubo[3][3] = cubo[2][3];
		cubo[2][3] = celdaTempo;
		celdaTempo = cubo[1][2];
		cubo[1][2] = cubo[3][1];
		cubo[3][1] = cubo[4][3];
		cubo[4][3] = cubo[2][4];
		cubo[2][4] = celdaTempo;
		celdaTempo = cubo[1][3];
		cubo[1][3] = cubo[2][1];
		cubo[2][1] = cubo[4][2];
		cubo[4][2] = cubo[3][4];
		cubo[3][4] = celdaTempo;
	}

	@Override
	public void u(int cant) {
		for (int i = 0; i < cant; i++) {
			u();
		}
	}

	@Override
	public void u() {
		Celda celdaTempo;
		celdaTempo = cubo[0][2];
		cubo[0][2] = cubo[1][2];
		cubo[1][2] = cubo[1][3];
		cubo[1][3] = cubo[0][3];
		cubo[0][3] = celdaTempo;
		celdaTempo = cubo[2][0];
		cubo[2][0] = cubo[2][2];
		cubo[2][2] = cubo[2][4];
		cubo[2][4] = cubo[7][3];
		cubo[7][3] = celdaTempo;
		celdaTempo = cubo[2][1];
		cubo[2][1] = cubo[2][3];
		cubo[2][3] = cubo[2][5];
		cubo[2][5] = cubo[7][2];
		cubo[7][2] = celdaTempo;
	}

	@Override
	public void r(int cant) {
		for (int i = 0; i < cant; i++) {
			r();
		}
	}

	@Override
	public void r() {
		Celda celdaTempo;
		celdaTempo = cubo[2][4];
		cubo[2][4] = cubo[3][4];
		cubo[3][4] = cubo[3][5];
		cubo[3][5] = cubo[2][5];
		cubo[2][5] = celdaTempo;
		celdaTempo = cubo[0][3];
		cubo[0][3] = cubo[2][3];
		cubo[2][3] = cubo[4][3];
		cubo[4][3] = cubo[6][3];
		cubo[6][3] = celdaTempo;
		celdaTempo = cubo[1][3];
		cubo[1][3] = cubo[3][3];
		cubo[3][3] = cubo[5][3];
		cubo[5][3] = cubo[7][3];
		cubo[7][3] = celdaTempo;
	}

	@Override
	public void l(int cant) {
		for (int i = 0; i < cant; i++) {
			l();
		}
	}

	@Override
	public void l() {
		Celda celdaTempo;
		celdaTempo = cubo[2][0];
		cubo[2][0] = cubo[3][0];
		cubo[3][0] = cubo[3][1];
		cubo[3][1] = cubo[2][1];
		cubo[2][1] = celdaTempo;
		celdaTempo = cubo[7][2];
		cubo[7][2] = cubo[5][2];
		cubo[5][2] = cubo[3][2];
		cubo[3][2] = cubo[1][2];
		cubo[1][2] = celdaTempo;
		celdaTempo = cubo[6][2];
		cubo[6][2] = cubo[4][2];
		cubo[4][2] = cubo[2][2];
		cubo[2][2] = cubo[0][2];
		cubo[0][2] = celdaTempo;
	}

	@Override
	public void b(int cant) {
		for (int i = 0; i < cant; i++) {
			b();
		}
	}

	@Override
	public void b() {
		Celda celdaTempo;
		celdaTempo = cubo[6][2];
		cubo[6][2] = cubo[7][2];
		cubo[7][2] = cubo[7][3];
		cubo[7][3] = cubo[6][3];
		cubo[6][3] = celdaTempo;
		celdaTempo = cubo[0][2];
		cubo[0][2] = cubo[2][5];
		cubo[2][5] = cubo[5][3];
		cubo[5][3] = cubo[3][0];
		cubo[3][0] = celdaTempo;
		celdaTempo = cubo[0][3];
		cubo[0][3] = cubo[3][5];
		cubo[3][5] = cubo[5][2];
		cubo[5][2] = cubo[2][0];
		cubo[2][0] = celdaTempo;
	}

	@Override
	public void d(int cant) {
		for (int i = 0; i < cant; i++) {
			d();
		}
	}

	@Override
	public void d() {
		Celda celdaTempo;
		celdaTempo = cubo[4][2];
		cubo[4][2] = cubo[5][2];
		cubo[5][2] = cubo[5][3];
		cubo[5][3] = cubo[4][3];
		cubo[4][3] = celdaTempo;
		celdaTempo = cubo[3][5];
		cubo[3][5] = cubo[3][3];
		cubo[3][3] = cubo[3][1];
		cubo[3][1] = cubo[6][2];
		cubo[6][2] = celdaTempo;
		celdaTempo = cubo[3][4];
		cubo[3][4] = cubo[3][2];
		cubo[3][2] = cubo[3][0];
		cubo[3][0] = cubo[6][3];
		cubo[6][3] = celdaTempo;
	}

	@Override
	public void z(int cant) {
		for (int i = 0; i < cant; i++) {
			z();
		}
	}

	@Override
	public void z() {
		girar("f");
		girar("b'");
	}

	@Override
	public void x(int cant) {
		for (int i = 0; i < cant; i++) {
			x();
		}
	}

	@Override
	public void x() {
		girar("l");
		girar("r'");
	}

	@Override
	public void y(int cant) {
		for (int i = 0; i < cant; i++) {
			y();
		}
	}

	@Override
	public void y() {
		girar("u");
		girar("d'");
	}

	public Celda[][] getCubo() {
		return cubo;
	}

	public void setCubo(Celda[][] cubo) {
		this.cubo = cubo;
	}

	@Override
	public String toString() {
		for (int i = 0; i < cubo.length; i++) {
			for (int j = 0; j < cubo[i].length; j++) {
				System.out.print((cubo[i][j] != null) ? cubo[i][j] + "\t" : "\t");
			}
			System.out.println();
		}
		return "";
	}

}
