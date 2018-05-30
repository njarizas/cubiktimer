package xyz.njas.modelo.rubik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import xyz.njas.util.ScrambleGenerator;

/**
 *
 * @author Nelson
 */
public class CuboRubik3x3 extends CuboRubik implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Celda[][] cubo = new Celda[12][9];

	public CuboRubik3x3(Integer idTipoCubo, String nombre) {
		super(idTipoCubo, nombre);
		for (int i = 0; i < cubo.length; i++) {
			for (int j = 0; j < cubo[i].length; j++) {
				cubo[i][j] = null;
			}
		}
		//TOP
		cubo[0][3] = new Celda("white");
		cubo[0][4] = new Celda("white");
		cubo[0][5] = new Celda("white");
		cubo[1][3] = new Celda("white");
		cubo[1][4] = new Celda("white");
		cubo[1][5] = new Celda("white");
		cubo[2][3] = new Celda("white");
		cubo[2][4] = new Celda("white");
		cubo[2][5] = new Celda("white");
		//LEFT
		cubo[3][0] = new Celda("#ff6702");
		cubo[3][1] = new Celda("#ff6702");
		cubo[3][2] = new Celda("#ff6702");
		cubo[4][0] = new Celda("#ff6702");
		cubo[4][1] = new Celda("#ff6702");
		cubo[4][2] = new Celda("#ff6702");
		cubo[5][0] = new Celda("#ff6702");
		cubo[5][1] = new Celda("#ff6702");
		cubo[5][2] = new Celda("#ff6702");
		//FRONT
		cubo[3][3] = new Celda("green");
		cubo[3][4] = new Celda("green");
		cubo[3][5] = new Celda("green");
		cubo[4][3] = new Celda("green");
		cubo[4][4] = new Celda("green");
		cubo[4][5] = new Celda("green");
		cubo[5][3] = new Celda("green");
		cubo[5][4] = new Celda("green");
		cubo[5][5] = new Celda("green");
		//RIGHT
		cubo[3][6] = new Celda("red");
		cubo[3][7] = new Celda("red");
		cubo[3][8] = new Celda("red");
		cubo[4][6] = new Celda("red");
		cubo[4][7] = new Celda("red");
		cubo[4][8] = new Celda("red");
		cubo[5][6] = new Celda("red");
		cubo[5][7] = new Celda("red");
		cubo[5][8] = new Celda("red");
		//DOWN
		cubo[6][3] = new Celda("yellow");
		cubo[6][4] = new Celda("yellow");
		cubo[6][5] = new Celda("yellow");
		cubo[7][3] = new Celda("yellow");
		cubo[7][4] = new Celda("yellow");
		cubo[7][5] = new Celda("yellow");
		cubo[8][3] = new Celda("yellow");
		cubo[8][4] = new Celda("yellow");
		cubo[8][5] = new Celda("yellow");
		//BACK
		cubo[9][3] = new Celda("blue");
		cubo[9][4] = new Celda("blue");
		cubo[9][5] = new Celda("blue");
		cubo[10][3] = new Celda("blue");
		cubo[10][4] = new Celda("blue");
		cubo[10][5] = new Celda("blue");
		cubo[11][3] = new Celda("blue");
		cubo[11][4] = new Celda("blue");
		cubo[11][5] = new Celda("blue");
	}

	@Override
	public String mezclar(String[] mezcla) {
		String secuenciaMezclada = "";
		for (String giro : mezcla) {
			if (girar(giro)) {
				secuenciaMezclada += giro.toUpperCase() + " ";
			}
		}
		return secuenciaMezclada;
	}

	//    @Override
	//    public String[] generarMezcla() {
	//        String[] movimientos = {"F", "F'", "F2", "B", "B'", "B2", "U", "U'", "U2", "D", "D'", "D2", "R", "R'", "R2", "L", "L'", "L2"};
	//        int num;
	//        int[] mezclaInt = new int[21];
	//        String[] mezcla = new String[mezclaInt.length];
	//        Random random = new Random();
	//        for (int i = 0; i < mezcla.length; i++) {
	//            num = random.nextInt(movimientos.length);
	//            //Para que no se gire dos veces seguidas la misma cara
	//            if (i != 0) {
	//                while (mezclaInt[i - 1] / 3 == num / 3) {
	//                    num = random.nextInt(movimientos.length);
	//                }
	//            }
	//            //para que no se hagan tres movimientos que se pueden expresar con solo dos
	//            if (i > 1) {
	//                while ((mezclaInt[i - 2] / 6 == mezclaInt[i - 1] / 6) && (mezclaInt[i - 1] / 6 == num / 6)) {
	//                    num = random.nextInt(movimientos.length);
	//                }
	//            }
	//            mezclaInt[i] = num;
	//            mezcla[i] = movimientos[mezclaInt[i]];
	//        }
	//        return mezcla;
	//    }

	@Override
	public String[] generarMezcla() {
		return ScrambleGenerator.generarMezcla("333");
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
	public void b(int cant) {
		for (int i = 0; i < cant; i++) {
			b();
		}
	}

	@Override
	public void b() {
		Celda celdaTempo;
		celdaTempo = cubo[9][3];
		cubo[9][3] = cubo[11][3];
		cubo[11][3] = cubo[11][5];
		cubo[11][5] = cubo[9][5];
		cubo[9][5] = celdaTempo;
		celdaTempo = cubo[10][3];
		cubo[10][3] = cubo[11][4];
		cubo[11][4] = cubo[10][5];
		cubo[10][5] = cubo[9][4];
		cubo[9][4] = celdaTempo;
		celdaTempo = cubo[8][3];
		cubo[8][3] = cubo[3][0];
		cubo[3][0] = cubo[0][5];
		cubo[0][5] = cubo[5][8];
		cubo[5][8] = celdaTempo;
		celdaTempo = cubo[8][4];
		cubo[8][4] = cubo[4][0];
		cubo[4][0] = cubo[0][4];
		cubo[0][4] = cubo[4][8];
		cubo[4][8] = celdaTempo;
		celdaTempo = cubo[8][5];
		cubo[8][5] = cubo[5][0];
		cubo[5][0] = cubo[0][3];
		cubo[0][3] = cubo[3][8];
		cubo[3][8] = celdaTempo;
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
		celdaTempo = cubo[6][3];
		cubo[6][3] = cubo[8][3];
		cubo[8][3] = cubo[8][5];
		cubo[8][5] = cubo[6][5];
		cubo[6][5] = celdaTempo;
		celdaTempo = cubo[7][3];
		cubo[7][3] = cubo[8][4];
		cubo[8][4] = cubo[7][5];
		cubo[7][5] = cubo[6][4];
		cubo[6][4] = celdaTempo;
		celdaTempo = cubo[5][8];
		cubo[5][8] = cubo[5][5];
		cubo[5][5] = cubo[5][2];
		cubo[5][2] = cubo[9][3];
		cubo[9][3] = celdaTempo;
		celdaTempo = cubo[5][7];
		cubo[5][7] = cubo[5][4];
		cubo[5][4] = cubo[5][1];
		cubo[5][1] = cubo[9][4];
		cubo[9][4] = celdaTempo;
		celdaTempo = cubo[5][6];
		cubo[5][6] = cubo[5][3];
		cubo[5][3] = cubo[5][0];
		cubo[5][0] = cubo[9][5];
		cubo[9][5] = celdaTempo;
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
		celdaTempo = cubo[3][3];
		cubo[3][3] = cubo[5][3];
		cubo[5][3] = cubo[5][5];
		cubo[5][5] = cubo[3][5];
		cubo[3][5] = celdaTempo;
		celdaTempo = cubo[4][3];
		cubo[4][3] = cubo[5][4];
		cubo[5][4] = cubo[4][5];
		cubo[4][5] = cubo[3][4];
		cubo[3][4] = celdaTempo;
		celdaTempo = cubo[2][3];
		cubo[2][3] = cubo[5][2];
		cubo[5][2] = cubo[6][5];
		cubo[6][5] = cubo[3][6];
		cubo[3][6] = celdaTempo;
		celdaTempo = cubo[2][4];
		cubo[2][4] = cubo[4][2];
		cubo[4][2] = cubo[6][4];
		cubo[6][4] = cubo[4][6];
		cubo[4][6] = celdaTempo;
		celdaTempo = cubo[2][5];
		cubo[2][5] = cubo[3][2];
		cubo[3][2] = cubo[6][3];
		cubo[6][3] = cubo[5][6];
		cubo[5][6] = celdaTempo;
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
		celdaTempo = cubo[3][0];
		cubo[3][0] = cubo[5][0];
		cubo[5][0] = cubo[5][2];
		cubo[5][2] = cubo[3][2];
		cubo[3][2] = celdaTempo;
		celdaTempo = cubo[4][0];
		cubo[4][0] = cubo[5][1];
		cubo[5][1] = cubo[4][2];
		cubo[4][2] = cubo[3][1];
		cubo[3][1] = celdaTempo;
		celdaTempo = cubo[11][3];
		cubo[11][3] = cubo[8][3];
		cubo[8][3] = cubo[5][3];
		cubo[5][3] = cubo[2][3];
		cubo[2][3] = celdaTempo;
		celdaTempo = cubo[10][3];
		cubo[10][3] = cubo[7][3];
		cubo[7][3] = cubo[4][3];
		cubo[4][3] = cubo[1][3];
		cubo[1][3] = celdaTempo;
		celdaTempo = cubo[9][3];
		cubo[9][3] = cubo[6][3];
		cubo[6][3] = cubo[3][3];
		cubo[3][3] = cubo[0][3];
		cubo[0][3] = celdaTempo;
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
		celdaTempo = cubo[3][6];
		cubo[3][6] = cubo[5][6];
		cubo[5][6] = cubo[5][8];
		cubo[5][8] = cubo[3][8];
		cubo[3][8] = celdaTempo;
		celdaTempo = cubo[4][6];
		cubo[4][6] = cubo[5][7];
		cubo[5][7] = cubo[4][8];
		cubo[4][8] = cubo[3][7];
		cubo[3][7] = celdaTempo;
		celdaTempo = cubo[0][5];
		cubo[0][5] = cubo[3][5];
		cubo[3][5] = cubo[6][5];
		cubo[6][5] = cubo[9][5];
		cubo[9][5] = celdaTempo;
		celdaTempo = cubo[1][5];
		cubo[1][5] = cubo[4][5];
		cubo[4][5] = cubo[7][5];
		cubo[7][5] = cubo[10][5];
		cubo[10][5] = celdaTempo;
		celdaTempo = cubo[2][5];
		cubo[2][5] = cubo[5][5];
		cubo[5][5] = cubo[8][5];
		cubo[8][5] = cubo[11][5];
		cubo[11][5] = celdaTempo;
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
		celdaTempo = cubo[0][3];
		cubo[0][3] = cubo[2][3];
		cubo[2][3] = cubo[2][5];
		cubo[2][5] = cubo[0][5];
		cubo[0][5] = celdaTempo;
		celdaTempo = cubo[1][3];
		cubo[1][3] = cubo[2][4];
		cubo[2][4] = cubo[1][5];
		cubo[1][5] = cubo[0][4];
		cubo[0][4] = celdaTempo;
		celdaTempo = cubo[3][0];
		cubo[3][0] = cubo[3][3];
		cubo[3][3] = cubo[3][6];
		cubo[3][6] = cubo[11][5];
		cubo[11][5] = celdaTempo;
		celdaTempo = cubo[3][1];
		cubo[3][1] = cubo[3][4];
		cubo[3][4] = cubo[3][7];
		cubo[3][7] = cubo[11][4];
		cubo[11][4] = celdaTempo;
		celdaTempo = cubo[3][2];
		cubo[3][2] = cubo[3][5];
		cubo[3][5] = cubo[3][8];
		cubo[3][8] = cubo[11][3];
		cubo[11][3] = celdaTempo;
	}

	@Override
	public void x(int cant) {
		for (int i = 0; i < cant; i++) {
			x();
		}
	}

	@Override
	public void x() {
		l();
		r(3);
		Celda celdaTempo;
		celdaTempo = cubo[11][4];
		cubo[11][4] = cubo[8][4];
		cubo[8][4] = cubo[5][4];
		cubo[5][4] = cubo[2][4];
		cubo[2][4] = celdaTempo;
		celdaTempo = cubo[10][4];
		cubo[10][4] = cubo[7][4];
		cubo[7][4] = cubo[4][4];
		cubo[4][4] = cubo[1][4];
		cubo[1][4] = celdaTempo;
		celdaTempo = cubo[9][4];
		cubo[9][4] = cubo[6][4];
		cubo[6][4] = cubo[3][4];
		cubo[3][4] = cubo[0][4];
		cubo[0][4] = celdaTempo;
	}

	@Override
	public void y(int cant) {
		for (int i = 0; i < cant; i++) {
			y();
		}
	}

	@Override
	public void y() {
		u();
		d(3);
		Celda celdaTempo;
		celdaTempo = cubo[4][0];
		cubo[4][0] = cubo[4][3];
		cubo[4][3] = cubo[4][6];
		cubo[4][6] = cubo[10][5];
		cubo[10][5] = celdaTempo;
		celdaTempo = cubo[4][1];
		cubo[4][1] = cubo[4][4];
		cubo[4][4] = cubo[4][7];
		cubo[4][7] = cubo[10][4];
		cubo[10][4] = celdaTempo;
		celdaTempo = cubo[4][2];
		cubo[4][2] = cubo[4][5];
		cubo[4][5] = cubo[4][8];
		cubo[4][8] = cubo[10][3];
		cubo[10][3] = celdaTempo;
	}

	@Override
	public void z(int cant) {
		for (int i = 0; i < cant; i++) {
			z();
		}
	}

	@Override
	public void z() {
		f();
		b(3);
		Celda celdaTempo;
		celdaTempo = cubo[1][3];
		cubo[1][3] = cubo[5][1];
		cubo[5][1] = cubo[7][5];
		cubo[7][5] = cubo[3][7];
		cubo[3][7] = celdaTempo;
		celdaTempo = cubo[1][4];
		cubo[1][4] = cubo[4][1];
		cubo[4][1] = cubo[7][4];
		cubo[7][4] = cubo[4][7];
		cubo[4][7] = celdaTempo;
		celdaTempo = cubo[1][5];
		cubo[1][5] = cubo[3][1];
		cubo[3][1] = cubo[7][3];
		cubo[7][3] = cubo[5][7];
		cubo[5][7] = celdaTempo;
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
