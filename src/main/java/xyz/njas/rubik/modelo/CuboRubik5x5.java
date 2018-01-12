package xyz.njas.rubik.modelo;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Nelson
 */
public class CuboRubik5x5 extends CuboRubik implements Serializable {

	private static final long serialVersionUID = 1L;
	private Celda[][] cubo = new Celda[20][15];

	public CuboRubik5x5(Integer idTipoCubo, String nombre) {
		super(idTipoCubo, nombre);
		for (int i = 0; i < cubo.length; i++) {
			for (int j = 0; j < cubo[i].length; j++) {
				cubo[i][j] = null;
			}
		}
		//TOP
		cubo[0][5] = new Celda("white");
		cubo[0][6] = new Celda("white");
		cubo[0][7] = new Celda("white");
		cubo[0][8] = new Celda("white");
		cubo[0][9] = new Celda("white");
		cubo[1][5] = new Celda("white");
		cubo[1][6] = new Celda("white");
		cubo[1][7] = new Celda("white");
		cubo[1][8] = new Celda("white");
		cubo[1][9] = new Celda("white");
		cubo[2][5] = new Celda("white");
		cubo[2][6] = new Celda("white");
		cubo[2][7] = new Celda("white");
		cubo[2][8] = new Celda("white");
		cubo[2][9] = new Celda("white");
		cubo[3][5] = new Celda("white");
		cubo[3][6] = new Celda("white");
		cubo[3][7] = new Celda("white");
		cubo[3][8] = new Celda("white");
		cubo[3][9] = new Celda("white");
		cubo[4][5] = new Celda("white");
		cubo[4][6] = new Celda("white");
		cubo[4][7] = new Celda("white");
		cubo[4][8] = new Celda("white");
		cubo[4][9] = new Celda("white");
		//LEFT
		cubo[5][0] = new Celda("#ff6702");
		cubo[5][1] = new Celda("#ff6702");
		cubo[5][2] = new Celda("#ff6702");
		cubo[5][3] = new Celda("#ff6702");
		cubo[5][4] = new Celda("#ff6702");
		cubo[6][0] = new Celda("#ff6702");
		cubo[6][1] = new Celda("#ff6702");
		cubo[6][2] = new Celda("#ff6702");
		cubo[6][3] = new Celda("#ff6702");
		cubo[6][4] = new Celda("#ff6702");
		cubo[7][0] = new Celda("#ff6702");
		cubo[7][1] = new Celda("#ff6702");
		cubo[7][2] = new Celda("#ff6702");
		cubo[7][3] = new Celda("#ff6702");
		cubo[7][4] = new Celda("#ff6702");
		cubo[8][0] = new Celda("#ff6702");
		cubo[8][1] = new Celda("#ff6702");
		cubo[8][2] = new Celda("#ff6702");
		cubo[8][3] = new Celda("#ff6702");
		cubo[8][4] = new Celda("#ff6702");
		cubo[9][0] = new Celda("#ff6702");
		cubo[9][1] = new Celda("#ff6702");
		cubo[9][2] = new Celda("#ff6702");
		cubo[9][3] = new Celda("#ff6702");
		cubo[9][4] = new Celda("#ff6702");
		//FRONT
		cubo[5][5] = new Celda("green");
		cubo[5][6] = new Celda("green");
		cubo[5][7] = new Celda("green");
		cubo[5][8] = new Celda("green");
		cubo[5][9] = new Celda("green");
		cubo[6][5] = new Celda("green");
		cubo[6][6] = new Celda("green");
		cubo[6][7] = new Celda("green");
		cubo[6][8] = new Celda("green");
		cubo[6][9] = new Celda("green");
		cubo[7][5] = new Celda("green");
		cubo[7][6] = new Celda("green");
		cubo[7][7] = new Celda("green");
		cubo[7][8] = new Celda("green");
		cubo[7][9] = new Celda("green");
		cubo[8][5] = new Celda("green");
		cubo[8][6] = new Celda("green");
		cubo[8][7] = new Celda("green");
		cubo[8][8] = new Celda("green");
		cubo[8][9] = new Celda("green");
		cubo[9][5] = new Celda("green");
		cubo[9][6] = new Celda("green");
		cubo[9][7] = new Celda("green");
		cubo[9][8] = new Celda("green");
		cubo[9][9] = new Celda("green");
		//RIGHT
		cubo[5][10] = new Celda("red");
		cubo[5][11] = new Celda("red");
		cubo[5][12] = new Celda("red");
		cubo[5][13] = new Celda("red");
		cubo[5][14] = new Celda("red");
		cubo[6][10] = new Celda("red");
		cubo[6][11] = new Celda("red");
		cubo[6][12] = new Celda("red");
		cubo[6][13] = new Celda("red");
		cubo[6][14] = new Celda("red");
		cubo[7][10] = new Celda("red");
		cubo[7][11] = new Celda("red");
		cubo[7][12] = new Celda("red");
		cubo[7][13] = new Celda("red");
		cubo[7][14] = new Celda("red");
		cubo[8][10] = new Celda("red");
		cubo[8][11] = new Celda("red");
		cubo[8][12] = new Celda("red");
		cubo[8][13] = new Celda("red");
		cubo[8][14] = new Celda("red");
		cubo[9][10] = new Celda("red");
		cubo[9][11] = new Celda("red");
		cubo[9][12] = new Celda("red");
		cubo[9][13] = new Celda("red");
		cubo[9][14] = new Celda("red");
		//DOWN
		cubo[10][5] = new Celda("yellow");
		cubo[10][6] = new Celda("yellow");
		cubo[10][7] = new Celda("yellow");
		cubo[10][8] = new Celda("yellow");
		cubo[10][9] = new Celda("yellow");
		cubo[11][5] = new Celda("yellow");
		cubo[11][6] = new Celda("yellow");
		cubo[11][7] = new Celda("yellow");
		cubo[11][8] = new Celda("yellow");
		cubo[11][9] = new Celda("yellow");
		cubo[12][5] = new Celda("yellow");
		cubo[12][6] = new Celda("yellow");
		cubo[12][7] = new Celda("yellow");
		cubo[12][8] = new Celda("yellow");
		cubo[12][9] = new Celda("yellow");
		cubo[13][5] = new Celda("yellow");
		cubo[13][6] = new Celda("yellow");
		cubo[13][7] = new Celda("yellow");
		cubo[13][8] = new Celda("yellow");
		cubo[13][9] = new Celda("yellow");
		cubo[14][5] = new Celda("yellow");
		cubo[14][6] = new Celda("yellow");
		cubo[14][7] = new Celda("yellow");
		cubo[14][8] = new Celda("yellow");
		cubo[14][9] = new Celda("yellow");
		//BACK
		cubo[15][5] = new Celda("blue");
		cubo[15][6] = new Celda("blue");
		cubo[15][7] = new Celda("blue");
		cubo[15][8] = new Celda("blue");
		cubo[15][9] = new Celda("blue");
		cubo[16][5] = new Celda("blue");
		cubo[16][6] = new Celda("blue");
		cubo[16][7] = new Celda("blue");
		cubo[16][8] = new Celda("blue");
		cubo[16][9] = new Celda("blue");
		cubo[17][5] = new Celda("blue");
		cubo[17][6] = new Celda("blue");
		cubo[17][7] = new Celda("blue");
		cubo[17][8] = new Celda("blue");
		cubo[17][9] = new Celda("blue");
		cubo[18][5] = new Celda("blue");
		cubo[18][6] = new Celda("blue");
		cubo[18][7] = new Celda("blue");
		cubo[18][8] = new Celda("blue");
		cubo[18][9] = new Celda("blue");
		cubo[19][5] = new Celda("blue");
		cubo[19][6] = new Celda("blue");
		cubo[19][7] = new Celda("blue");
		cubo[19][8] = new Celda("blue");
		cubo[19][9] = new Celda("blue");
	}

	@Override
	public String mezclar(String[] mezcla) {
		String secuenciaMezclada = "";
		for (String giro : mezcla) {
			if (girar(giro)) {
				secuenciaMezclada += giro.toUpperCase().replaceAll("W", "w") + " ";
			}
		}
		return secuenciaMezclada;
	}

	@Override
	public String[] generarMezcla() {
		String[] movimientos = {"F", "F'", "F2", "Fw", "Fw'", "Fw2", "B", "B'", "B2", "Bw", "Bw'", "Bw2", "U", "U'", "U2", "Uw", "Uw'", "Uw2", "D", "D'", "D2", "Dw", "Dw'", "Dw2", "R", "R'", "R2", "Rw", "Rw'", "Rw2", "L", "L'", "L2", "Lw", "Lw'", "Lw2"};
		int num;
		int[] mezclaInt = new int[60];
		String[] mezcla = new String[mezclaInt.length];
		Random random = new Random();
		for (int i = 0; i < mezcla.length; i++) {
			num = random.nextInt(movimientos.length);
			//para que no se gire dos veces seguidas la misma cara
			if (i != 0) {
				while (mezclaInt[i - 1] / 3 == num / 3) {
					num = random.nextInt(movimientos.length);
				}
			}
			//para que no se hagan tres movimientos que se pueden expresar con solo dos
			if (i > 1) {
				while ((mezclaInt[i - 2] / 9 == mezclaInt[i - 1] / 9) && (mezclaInt[i - 1] / 9 == num / 9)) {
					num = random.nextInt(movimientos.length);
				}
			}
			//TODO implementar lógica para que las mezclas de 5x5 cumplan con los requisitos de WCA
			mezclaInt[i] = num;
			mezcla[i] = movimientos[mezclaInt[i]];
		}
		return mezcla;
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
		} else if (giro.equals("fw")){
			fw(1);
		} else if (giro.equals("fw2")){
			fw(2);
		} else if (giro.equals("fw'")){
			fw(3);
		} else if (giro.equals("u")){
			u(1);
		} else if (giro.equals("u2")){
			u(2);
		} else if (giro.equals("u'")){
			u(3);
		} else if (giro.equals("uw")){
			uw(1);
		} else if (giro.equals("uw2")){
			uw(2);
		} else if (giro.equals("uw'")){
			uw(3);
		} else if (giro.equals("r")){
			r(1);
		} else if (giro.equals("r2")){
			r(2);
		} else if (giro.equals("r'")){
			r(3);
		} else if (giro.equals("rw")){
			rw(1);
		} else if (giro.equals("rw2")){
			rw(2);
		} else if (giro.equals("rw'")){
			rw(3);
		} else if (giro.equals("l")){
			l(1);
		} else if (giro.equals("l2")){
			l(2);
		} else if (giro.equals("l'")){
			l(3);
		} else if (giro.equals("lw")){
			lw(1);
		} else if (giro.equals("lw2")){
			lw(2);
		} else if (giro.equals("lw'")){
			lw(3);
		} else if (giro.equals("b")){
			b(1);
		} else if (giro.equals("b2")){
			b(2);
		} else if (giro.equals("b'")){
			b(3);
		} else if (giro.equals("bw")){
			bw(1);
		} else if (giro.equals("bw2")){
			bw(2);
		} else if (giro.equals("bw'")){
			bw(3);
		} else if (giro.equals("d")){
			d(1);
		} else if (giro.equals("d2")){
			d(2);
		} else if (giro.equals("d'")){
			d(3);
		} else if (giro.equals("dw")){
			dw(1);
		} else if (giro.equals("dw2")){
			dw(2);
		} else if (giro.equals("dw'")){
			dw(3);
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
		celdaTempo = cubo[15][5];
		cubo[15][5] = cubo[19][5];
		cubo[19][5] = cubo[19][9];
		cubo[19][9] = cubo[15][9];
		cubo[15][9] = celdaTempo;
		celdaTempo = cubo[16][5];
		cubo[16][5] = cubo[19][6];
		cubo[19][6] = cubo[18][9];
		cubo[18][9] = cubo[15][8];
		cubo[15][8] = celdaTempo;
		celdaTempo = cubo[17][5];
		cubo[17][5] = cubo[19][7];
		cubo[19][7] = cubo[17][9];
		cubo[17][9] = cubo[15][7];
		cubo[15][7] = celdaTempo;
		celdaTempo = cubo[18][5];
		cubo[18][5] = cubo[19][8];
		cubo[19][8] = cubo[16][9];
		cubo[16][9] = cubo[15][6];
		cubo[15][6] = celdaTempo;
		
		celdaTempo = cubo[16][6];
		cubo[16][6] = cubo[18][6];
		cubo[18][6] = cubo[18][8];
		cubo[18][8] = cubo[16][8];
		cubo[16][8] = celdaTempo;
		celdaTempo = cubo[17][6];
		cubo[17][6] = cubo[18][7];
		cubo[18][7] = cubo[17][8];
		cubo[17][8] = cubo[16][7];
		cubo[16][7] = celdaTempo;
		
		celdaTempo = cubo[14][5];
		cubo[14][5] = cubo[5][0];
		cubo[5][0] = cubo[0][9];
		cubo[0][9] = cubo[9][14];
		cubo[9][14] = celdaTempo;
		celdaTempo = cubo[14][6];
		cubo[14][6] = cubo[6][0];
		cubo[6][0] = cubo[0][8];
		cubo[0][8] = cubo[8][14];
		cubo[8][14] = celdaTempo;
		celdaTempo = cubo[14][7];
		cubo[14][7] = cubo[7][0];
		cubo[7][0] = cubo[0][7];
		cubo[0][7] = cubo[7][14];
		cubo[7][14] = celdaTempo;
		celdaTempo = cubo[14][8];
		cubo[14][8] = cubo[8][0];
		cubo[8][0] = cubo[0][6];
		cubo[0][6] = cubo[6][14];
		cubo[6][14] = celdaTempo;
		celdaTempo = cubo[14][9];
		cubo[14][9] = cubo[9][0];
		cubo[9][0] = cubo[0][5];
		cubo[0][5] = cubo[5][14];
		cubo[5][14] = celdaTempo;
		

	}
	
	public void bw(int cant) {
		for (int i = 0; i < cant; i++) {
			bw();
		}
	}

	public void bw() {
		b();
		Celda celdaTempo;
		celdaTempo = cubo[13][5];
		cubo[13][5] = cubo[5][1];
		cubo[5][1] = cubo[1][9];
		cubo[1][9] = cubo[9][13];
		cubo[9][13] = celdaTempo;
		celdaTempo = cubo[13][6];
		cubo[13][6] = cubo[6][1];
		cubo[6][1] = cubo[1][8];
		cubo[1][8] = cubo[8][13];
		cubo[8][13] = celdaTempo;
		celdaTempo = cubo[13][7];
		cubo[13][7] = cubo[7][1];
		cubo[7][1] = cubo[1][7];
		cubo[1][7] = cubo[7][13];
		cubo[7][13] = celdaTempo;
		celdaTempo = cubo[13][8];
		cubo[13][8] = cubo[8][1];
		cubo[8][1] = cubo[1][6];
		cubo[1][6] = cubo[6][13];
		cubo[6][13] = celdaTempo;
		celdaTempo = cubo[13][9];
		cubo[13][9] = cubo[9][1];
		cubo[9][1] = cubo[1][5];
		cubo[1][5] = cubo[5][13];
		cubo[5][13] = celdaTempo;

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
		celdaTempo = cubo[10][5];
		cubo[10][5] = cubo[14][5];
		cubo[14][5] = cubo[14][9];
		cubo[14][9] = cubo[10][9];
		cubo[10][9] = celdaTempo;
		celdaTempo = cubo[11][5];
		cubo[11][5] = cubo[14][6];
		cubo[14][6] = cubo[13][9];
		cubo[13][9] = cubo[10][8];
		cubo[10][8] = celdaTempo;
		celdaTempo = cubo[12][5];
		cubo[12][5] = cubo[14][7];
		cubo[14][7] = cubo[12][9];
		cubo[12][9] = cubo[10][7];
		cubo[10][7] = celdaTempo;
		celdaTempo = cubo[13][5];
		cubo[13][5] = cubo[14][8];
		cubo[14][8] = cubo[11][9];
		cubo[11][9] = cubo[10][6];
		cubo[10][6] = celdaTempo;
		
		celdaTempo = cubo[11][6];
		cubo[11][6] = cubo[13][6];
		cubo[13][6] = cubo[13][8];
		cubo[13][8] = cubo[11][8];
		cubo[11][8] = celdaTempo;
		celdaTempo = cubo[12][6];
		cubo[12][6] = cubo[13][7];
		cubo[13][7] = cubo[12][8];
		cubo[12][8] = cubo[11][7];
		cubo[11][7] = celdaTempo;
		
		celdaTempo = cubo[9][14];
		cubo[9][14] = cubo[9][9];
		cubo[9][9] = cubo[9][4];
		cubo[9][4] = cubo[15][5];
		cubo[15][5] = celdaTempo;
		celdaTempo = cubo[9][13];
		cubo[9][13] = cubo[9][8];
		cubo[9][8] = cubo[9][3];
		cubo[9][3] = cubo[15][6];
		cubo[15][6] = celdaTempo;
		celdaTempo = cubo[9][12];
		cubo[9][12] = cubo[9][7];
		cubo[9][7] = cubo[9][2];
		cubo[9][2] = cubo[15][7];
		cubo[15][7] = celdaTempo;
		celdaTempo = cubo[9][11];
		cubo[9][11] = cubo[9][6];
		cubo[9][6] = cubo[9][1];
		cubo[9][1] = cubo[15][8];
		cubo[15][8] = celdaTempo;
		celdaTempo = cubo[9][10];
		cubo[9][10] = cubo[9][5];
		cubo[9][5] = cubo[9][0];
		cubo[9][0] = cubo[15][9];
		cubo[15][9] = celdaTempo;
	}
	
	public void dw(int cant) {
		for (int i = 0; i < cant; i++) {
			dw();
		}
	}

	public void dw() {
		d();
		Celda celdaTempo;
		celdaTempo = cubo[8][14];
		cubo[8][14] = cubo[8][9];
		cubo[8][9] = cubo[8][4];
		cubo[8][4] = cubo[16][5];
		cubo[16][5] = celdaTempo;
		celdaTempo = cubo[8][13];
		cubo[8][13] = cubo[8][8];
		cubo[8][8] = cubo[8][3];
		cubo[8][3] = cubo[16][6];
		cubo[16][6] = celdaTempo;
		celdaTempo = cubo[8][12];
		cubo[8][12] = cubo[8][7];
		cubo[8][7] = cubo[8][2];
		cubo[8][2] = cubo[16][7];
		cubo[16][7] = celdaTempo;
		celdaTempo = cubo[8][11];
		cubo[8][11] = cubo[8][6];
		cubo[8][6] = cubo[8][1];
		cubo[8][1] = cubo[16][8];
		cubo[16][8] = celdaTempo;
		celdaTempo = cubo[8][10];
		cubo[8][10] = cubo[8][5];
		cubo[8][5] = cubo[8][0];
		cubo[8][0] = cubo[16][9];
		cubo[16][9] = celdaTempo;
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
		celdaTempo = cubo[5][5];
		cubo[5][5] = cubo[9][5];
		cubo[9][5] = cubo[9][9];
		cubo[9][9] = cubo[5][9];
		cubo[5][9] = celdaTempo;
		celdaTempo = cubo[6][5];
		cubo[6][5] = cubo[9][6];
		cubo[9][6] = cubo[8][9];
		cubo[8][9] = cubo[5][8];
		cubo[5][8] = celdaTempo;
		celdaTempo = cubo[7][5];
		cubo[7][5] = cubo[9][7];
		cubo[9][7] = cubo[7][9];
		cubo[7][9] = cubo[5][7];
		cubo[5][7] = celdaTempo;
		celdaTempo = cubo[8][5];
		cubo[8][5] = cubo[9][8];
		cubo[9][8] = cubo[6][9];
		cubo[6][9] = cubo[5][6];
		cubo[5][6] = celdaTempo;
		
		celdaTempo = cubo[6][6];
		cubo[6][6] = cubo[8][6];
		cubo[8][6] = cubo[8][8];
		cubo[8][8] = cubo[6][8];
		cubo[6][8] = celdaTempo;
		celdaTempo = cubo[7][6];
		cubo[7][6] = cubo[8][7];
		cubo[8][7] = cubo[7][8];
		cubo[7][8] = cubo[6][7];
		cubo[6][7] = celdaTempo;
		
		celdaTempo = cubo[4][9];
		cubo[4][9] = cubo[5][4];
		cubo[5][4] = cubo[10][5];
		cubo[10][5] = cubo[9][10];
		cubo[9][10] = celdaTempo;
		celdaTempo = cubo[4][8];
		cubo[4][8] = cubo[6][4];
		cubo[6][4] = cubo[10][6];
		cubo[10][6] = cubo[8][10];
		cubo[8][10] = celdaTempo;
		celdaTempo = cubo[4][7];
		cubo[4][7] = cubo[7][4];
		cubo[7][4] = cubo[10][7];
		cubo[10][7] = cubo[7][10];
		cubo[7][10] = celdaTempo;
		celdaTempo = cubo[4][6];
		cubo[4][6] = cubo[8][4];
		cubo[8][4] = cubo[10][8];
		cubo[10][8] = cubo[6][10];
		cubo[6][10] = celdaTempo;
		celdaTempo = cubo[4][5];
		cubo[4][5] = cubo[9][4];
		cubo[9][4] = cubo[10][9];
		cubo[10][9] = cubo[5][10];
		cubo[5][10] = celdaTempo;
	}

	public void fw(int cant) {
		for (int i = 0; i < cant; i++) {
			fw();
		}
	}

	public void fw() {
		f();
		Celda celdaTempo;
		celdaTempo = cubo[3][9];
		cubo[3][9] = cubo[5][3];
		cubo[5][3] = cubo[11][5];
		cubo[11][5] = cubo[9][11];
		cubo[9][11] = celdaTempo;
		celdaTempo = cubo[3][8];
		cubo[3][8] = cubo[6][3];
		cubo[6][3] = cubo[11][6];
		cubo[11][6] = cubo[8][11];
		cubo[8][11] = celdaTempo;
		celdaTempo = cubo[3][7];
		cubo[3][7] = cubo[7][3];
		cubo[7][3] = cubo[11][7];
		cubo[11][7] = cubo[7][11];
		cubo[7][11] = celdaTempo;
		celdaTempo = cubo[3][6];
		cubo[3][6] = cubo[8][3];
		cubo[8][3] = cubo[11][8];
		cubo[11][8] = cubo[6][11];
		cubo[6][11] = celdaTempo;
		celdaTempo = cubo[3][5];
		cubo[3][5] = cubo[9][3];
		cubo[9][3] = cubo[11][9];
		cubo[11][9] = cubo[5][11];
		cubo[5][11] = celdaTempo;
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
		celdaTempo = cubo[5][0];
		cubo[5][0] = cubo[9][0];
		cubo[9][0] = cubo[9][4];
		cubo[9][4] = cubo[5][4];
		cubo[5][4] = celdaTempo;
		celdaTempo = cubo[6][0];
		cubo[6][0] = cubo[9][1];
		cubo[9][1] = cubo[8][4];
		cubo[8][4] = cubo[5][3];
		cubo[5][3] = celdaTempo;
		celdaTempo = cubo[7][0];
		cubo[7][0] = cubo[9][2];
		cubo[9][2] = cubo[7][4];
		cubo[7][4] = cubo[5][2];
		cubo[5][2] = celdaTempo;
		celdaTempo = cubo[8][0];
		cubo[8][0] = cubo[9][3];
		cubo[9][3] = cubo[6][4];
		cubo[6][4] = cubo[5][1];
		cubo[5][1] = celdaTempo;
		
		celdaTempo = cubo[6][1];
		cubo[6][1] = cubo[8][1];
		cubo[8][1] = cubo[8][3];
		cubo[8][3] = cubo[6][3];
		cubo[6][3] = celdaTempo;
		celdaTempo = cubo[7][1];
		cubo[7][1] = cubo[8][2];
		cubo[8][2] = cubo[7][3];
		cubo[7][3] = cubo[6][2];
		cubo[6][2] = celdaTempo;
		
		celdaTempo = cubo[19][5];
		cubo[19][5] = cubo[14][5];
		cubo[14][5] = cubo[9][5];
		cubo[9][5] = cubo[4][5];
		cubo[4][5] = celdaTempo;
		celdaTempo = cubo[18][5];
		cubo[18][5] = cubo[13][5];
		cubo[13][5] = cubo[8][5];
		cubo[8][5] = cubo[3][5];
		cubo[3][5] = celdaTempo;
		celdaTempo = cubo[17][5];
		cubo[17][5] = cubo[12][5];
		cubo[12][5] = cubo[7][5];
		cubo[7][5] = cubo[2][5];
		cubo[2][5] = celdaTempo;
		celdaTempo = cubo[16][5];
		cubo[16][5] = cubo[11][5];
		cubo[11][5] = cubo[6][5];
		cubo[6][5] = cubo[1][5];
		cubo[1][5] = celdaTempo;
		celdaTempo = cubo[15][5];
		cubo[15][5] = cubo[10][5];
		cubo[10][5] = cubo[5][5];
		cubo[5][5] = cubo[0][5];
		cubo[0][5] = celdaTempo;
	}
	
	public void lw(int cant) {
		for (int i = 0; i < cant; i++) {
			lw();
		}
	}

	public void lw() {
		l();
		Celda celdaTempo;
		celdaTempo = cubo[19][6];
		cubo[19][6] = cubo[14][6];
		cubo[14][6] = cubo[9][6];
		cubo[9][6] = cubo[4][6];
		cubo[4][6] = celdaTempo;
		celdaTempo = cubo[18][6];
		cubo[18][6] = cubo[13][6];
		cubo[13][6] = cubo[8][6];
		cubo[8][6] = cubo[3][6];
		cubo[3][6] = celdaTempo;
		celdaTempo = cubo[17][6];
		cubo[17][6] = cubo[12][6];
		cubo[12][6] = cubo[7][6];
		cubo[7][6] = cubo[2][6];
		cubo[2][6] = celdaTempo;
		celdaTempo = cubo[16][6];
		cubo[16][6] = cubo[11][6];
		cubo[11][6] = cubo[6][6];
		cubo[6][6] = cubo[1][6];
		cubo[1][6] = celdaTempo;
		celdaTempo = cubo[15][6];
		cubo[15][6] = cubo[10][6];
		cubo[10][6] = cubo[5][6];
		cubo[5][6] = cubo[0][6];
		cubo[0][6] = celdaTempo;
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
		celdaTempo = cubo[5][10];
		cubo[5][10] = cubo[9][10];
		cubo[9][10] = cubo[9][14];
		cubo[9][14] = cubo[5][14];
		cubo[5][14] = celdaTempo;
		celdaTempo = cubo[6][10];
		cubo[6][10] = cubo[9][11];
		cubo[9][11] = cubo[8][14];
		cubo[8][14] = cubo[5][13];
		cubo[5][13] = celdaTempo;
		celdaTempo = cubo[7][10];
		cubo[7][10] = cubo[9][12];
		cubo[9][12] = cubo[7][14];
		cubo[7][14] = cubo[5][12];
		cubo[5][12] = celdaTempo;
		celdaTempo = cubo[8][10];
		cubo[8][10] = cubo[9][13];
		cubo[9][13] = cubo[6][14];
		cubo[6][14] = cubo[5][11];
		cubo[5][11] = celdaTempo;
		
		celdaTempo = cubo[6][11];
		cubo[6][11] = cubo[8][11];
		cubo[8][11] = cubo[8][13];
		cubo[8][13] = cubo[6][13];
		cubo[6][13] = celdaTempo;
		celdaTempo = cubo[7][11];
		cubo[7][11] = cubo[8][12];
		cubo[8][12] = cubo[7][13];
		cubo[7][13] = cubo[6][12];
		cubo[6][12] = celdaTempo;
		
		celdaTempo = cubo[0][9];
		cubo[0][9] = cubo[5][9];
		cubo[5][9] = cubo[10][9];
		cubo[10][9] = cubo[15][9];
		cubo[15][9] = celdaTempo;
		celdaTempo = cubo[1][9];
		cubo[1][9] = cubo[6][9];
		cubo[6][9] = cubo[11][9];
		cubo[11][9] = cubo[16][9];
		cubo[16][9] = celdaTempo;
		celdaTempo = cubo[2][9];
		cubo[2][9] = cubo[7][9];
		cubo[7][9] = cubo[12][9];
		cubo[12][9] = cubo[17][9];
		cubo[17][9] = celdaTempo;
		celdaTempo = cubo[3][9];
		cubo[3][9] = cubo[8][9];
		cubo[8][9] = cubo[13][9];
		cubo[13][9] = cubo[18][9];
		cubo[18][9] = celdaTempo;
		celdaTempo = cubo[4][9];
		cubo[4][9] = cubo[9][9];
		cubo[9][9] = cubo[14][9];
		cubo[14][9] = cubo[19][9];
		cubo[19][9] = celdaTempo;
	}
	
	public void rw(int cant) {
		for (int i = 0; i < cant; i++) {
			rw();
		}
	}

	public void rw() {
		r();
		Celda celdaTempo;
		celdaTempo = cubo[0][8];
		cubo[0][8] = cubo[5][8];
		cubo[5][8] = cubo[10][8];
		cubo[10][8] = cubo[15][8];
		cubo[15][8] = celdaTempo;
		celdaTempo = cubo[1][8];
		cubo[1][8] = cubo[6][8];
		cubo[6][8] = cubo[11][8];
		cubo[11][8] = cubo[16][8];
		cubo[16][8] = celdaTempo;
		celdaTempo = cubo[2][8];
		cubo[2][8] = cubo[7][8];
		cubo[7][8] = cubo[12][8];
		cubo[12][8] = cubo[17][8];
		cubo[17][8] = celdaTempo;
		celdaTempo = cubo[3][8];
		cubo[3][8] = cubo[8][8];
		cubo[8][8] = cubo[13][8];
		cubo[13][8] = cubo[18][8];
		cubo[18][8] = celdaTempo;
		celdaTempo = cubo[4][8];
		cubo[4][8] = cubo[9][8];
		cubo[9][8] = cubo[14][8];
		cubo[14][8] = cubo[19][8];
		cubo[19][8] = celdaTempo;
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
		celdaTempo = cubo[0][5];
		cubo[0][5] = cubo[4][5];
		cubo[4][5] = cubo[4][9];
		cubo[4][9] = cubo[0][9];
		cubo[0][9] = celdaTempo;
		celdaTempo = cubo[1][5];
		cubo[1][5] = cubo[4][6];
		cubo[4][6] = cubo[3][9];
		cubo[3][9] = cubo[0][8];
		cubo[0][8] = celdaTempo;
		celdaTempo = cubo[2][5];
		cubo[2][5] = cubo[4][7];
		cubo[4][7] = cubo[2][9];
		cubo[2][9] = cubo[0][7];
		cubo[0][7] = celdaTempo;
		celdaTempo = cubo[3][5];
		cubo[3][5] = cubo[4][8];
		cubo[4][8] = cubo[1][9];
		cubo[1][9] = cubo[0][6];
		cubo[0][6] = celdaTempo;
		
		celdaTempo = cubo[1][6];
		cubo[1][6] = cubo[3][6];
		cubo[3][6] = cubo[3][8];
		cubo[3][8] = cubo[1][8];
		cubo[1][8] = celdaTempo;
		celdaTempo = cubo[2][6];
		cubo[2][6] = cubo[3][7];
		cubo[3][7] = cubo[2][8];
		cubo[2][8] = cubo[1][7];
		cubo[1][7] = celdaTempo;

		celdaTempo = cubo[5][0];
		cubo[5][0] = cubo[5][5];
		cubo[5][5] = cubo[5][10];
		cubo[5][10] = cubo[19][9];
		cubo[19][9] = celdaTempo;
		celdaTempo = cubo[5][1];
		cubo[5][1] = cubo[5][6];
		cubo[5][6] = cubo[5][11];
		cubo[5][11] = cubo[19][8];
		cubo[19][8] = celdaTempo;
		celdaTempo = cubo[5][2];
		cubo[5][2] = cubo[5][7];
		cubo[5][7] = cubo[5][12];
		cubo[5][12] = cubo[19][7];
		cubo[19][7] = celdaTempo;
		celdaTempo = cubo[5][3];
		cubo[5][3] = cubo[5][8];
		cubo[5][8] = cubo[5][13];
		cubo[5][13] = cubo[19][6];
		cubo[19][6] = celdaTempo;
		celdaTempo = cubo[5][4];
		cubo[5][4] = cubo[5][9];
		cubo[5][9] = cubo[5][14];
		cubo[5][14] = cubo[19][5];
		cubo[19][5] = celdaTempo;
	}

	public void uw(int cant) {
		for (int i = 0; i < cant; i++) {
			uw();
		}
	}

	public void uw() {
		u();
		Celda celdaTempo;
		celdaTempo = cubo[6][0];
		cubo[6][0] = cubo[6][5];
		cubo[6][5] = cubo[6][10];
		cubo[6][10] = cubo[18][9];
		cubo[18][9] = celdaTempo;
		celdaTempo = cubo[6][1];
		cubo[6][1] = cubo[6][6];
		cubo[6][6] = cubo[6][11];
		cubo[6][11] = cubo[18][8];
		cubo[18][8] = celdaTempo;
		celdaTempo = cubo[6][2];
		cubo[6][2] = cubo[6][7];
		cubo[6][7] = cubo[6][12];
		cubo[6][12] = cubo[18][7];
		cubo[18][7] = celdaTempo;
		celdaTempo = cubo[6][3];
		cubo[6][3] = cubo[6][8];
		cubo[6][8] = cubo[6][13];
		cubo[6][13] = cubo[18][6];
		cubo[18][6] = celdaTempo;
		celdaTempo = cubo[6][4];
		cubo[6][4] = cubo[6][9];
		cubo[6][9] = cubo[6][14];
		cubo[6][14] = cubo[18][5];
		cubo[18][5] = celdaTempo;
	}

	@Override
	public void x(int cant) {
		for (int i = 0; i < cant; i++) {
			x();
		}
	}

	@Override
	public void x() {
		lw();
		rw(3);
		Celda celdaTempo;
		celdaTempo = cubo[19][7];
		cubo[19][7] = cubo[14][7];
		cubo[14][7] = cubo[9][7];
		cubo[9][7] = cubo[4][7];
		cubo[4][7] = celdaTempo;
		celdaTempo = cubo[18][7];
		cubo[18][7] = cubo[13][7];
		cubo[13][7] = cubo[8][7];
		cubo[8][7] = cubo[3][7];
		cubo[3][7] = celdaTempo;
		celdaTempo = cubo[17][7];
		cubo[17][7] = cubo[12][7];
		cubo[12][7] = cubo[7][7];
		cubo[7][7] = cubo[2][7];
		cubo[2][7] = celdaTempo;
		celdaTempo = cubo[16][7];
		cubo[16][7] = cubo[11][7];
		cubo[11][7] = cubo[6][7];
		cubo[6][7] = cubo[1][7];
		cubo[1][7] = celdaTempo;
		celdaTempo = cubo[15][7];
		cubo[15][7] = cubo[10][7];
		cubo[10][7] = cubo[5][7];
		cubo[5][7] = cubo[0][7];
		cubo[0][7] = celdaTempo;
	}

	@Override
	public void y(int cant) {
		for (int i = 0; i < cant; i++) {
			y();
		}
	}

	@Override
	public void y() {
		uw();
		dw(3);
		Celda celdaTempo;
		celdaTempo = cubo[7][0];
		cubo[7][0] = cubo[7][5];
		cubo[7][5] = cubo[7][10];
		cubo[7][10] = cubo[17][9];
		cubo[17][9] = celdaTempo;
		celdaTempo = cubo[7][1];
		cubo[7][1] = cubo[7][6];
		cubo[7][6] = cubo[7][11];
		cubo[7][11] = cubo[17][8];
		cubo[17][8] = celdaTempo;
		celdaTempo = cubo[7][2];
		cubo[7][2] = cubo[7][7];
		cubo[7][7] = cubo[7][12];
		cubo[7][12] = cubo[17][7];
		cubo[17][7] = celdaTempo;
		celdaTempo = cubo[7][3];
		cubo[7][3] = cubo[7][8];
		cubo[7][8] = cubo[7][13];
		cubo[7][13] = cubo[17][6];
		cubo[17][6] = celdaTempo;
		celdaTempo = cubo[7][4];
		cubo[7][4] = cubo[7][9];
		cubo[7][9] = cubo[7][14];
		cubo[7][14] = cubo[17][5];
		cubo[17][5] = celdaTempo;
	}

	@Override
	public void z(int cant) {
		for (int i = 0; i < cant; i++) {
			z();
		}
	}

	@Override
	public void z() {
		fw();
		bw(3);
		Celda celdaTempo;
		celdaTempo = cubo[2][9];
		cubo[2][9] = cubo[5][2];
		cubo[5][2] = cubo[12][5];
		cubo[12][5] = cubo[9][12];
		cubo[9][12] = celdaTempo;
		celdaTempo = cubo[2][8];
		cubo[2][8] = cubo[6][2];
		cubo[6][2] = cubo[12][6];
		cubo[12][6] = cubo[8][12];
		cubo[8][12] = celdaTempo;
		celdaTempo = cubo[2][7];
		cubo[2][7] = cubo[7][2];
		cubo[7][2] = cubo[12][7];
		cubo[12][7] = cubo[7][12];
		cubo[7][12] = celdaTempo;
		celdaTempo = cubo[2][6];
		cubo[2][6] = cubo[8][2];
		cubo[8][2] = cubo[12][8];
		cubo[12][8] = cubo[6][12];
		cubo[6][12] = celdaTempo;
		celdaTempo = cubo[2][5];
		cubo[2][5] = cubo[9][2];
		cubo[9][2] = cubo[12][9];
		cubo[12][9] = cubo[5][12];
		cubo[5][12] = celdaTempo;
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
