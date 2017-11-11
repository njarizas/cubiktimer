package xyz.njas.rubik.modelo;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Nelson
 */
public class CuboRubik4x4 extends CuboRubik implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Celda[][] cubo = new Celda[16][12];

    public CuboRubik4x4(Integer idTipoCubo, String nombre) {
    	super(idTipoCubo, nombre);
        for (int i = 0; i < cubo.length; i++) {
            for (int j = 0; j < cubo[i].length; j++) {
                cubo[i][j] = null;
            }
        }
        //TOP
        cubo[0][4] = new Celda("white");
        cubo[0][5] = new Celda("white");
        cubo[0][6] = new Celda("white");
        cubo[0][7] = new Celda("white");
        cubo[1][4] = new Celda("white");
        cubo[1][5] = new Celda("white");
        cubo[1][6] = new Celda("white");
        cubo[1][7] = new Celda("white");
        cubo[2][4] = new Celda("white");
        cubo[2][5] = new Celda("white");
        cubo[2][6] = new Celda("white");
        cubo[2][7] = new Celda("white");
        cubo[3][4] = new Celda("white");
        cubo[3][5] = new Celda("white");
        cubo[3][6] = new Celda("white");
        cubo[3][7] = new Celda("white");
        //LEFT
        cubo[4][0] = new Celda("#ff6702");
        cubo[4][1] = new Celda("#ff6702");
        cubo[4][2] = new Celda("#ff6702");
        cubo[4][3] = new Celda("#ff6702");
        cubo[5][0] = new Celda("#ff6702");
        cubo[5][1] = new Celda("#ff6702");
        cubo[5][2] = new Celda("#ff6702");
        cubo[5][3] = new Celda("#ff6702");
        cubo[6][0] = new Celda("#ff6702");
        cubo[6][1] = new Celda("#ff6702");
        cubo[6][2] = new Celda("#ff6702");
        cubo[6][3] = new Celda("#ff6702");
        cubo[7][0] = new Celda("#ff6702");
        cubo[7][1] = new Celda("#ff6702");
        cubo[7][2] = new Celda("#ff6702");
        cubo[7][3] = new Celda("#ff6702");
        //FRONT
        cubo[4][4] = new Celda("green");
        cubo[4][5] = new Celda("green");
        cubo[4][6] = new Celda("green");
        cubo[4][7] = new Celda("green");
        cubo[5][4] = new Celda("green");
        cubo[5][5] = new Celda("green");
        cubo[5][6] = new Celda("green");
        cubo[5][7] = new Celda("green");
        cubo[6][4] = new Celda("green");
        cubo[6][5] = new Celda("green");
        cubo[6][6] = new Celda("green");
        cubo[6][7] = new Celda("green");
        cubo[7][4] = new Celda("green");
        cubo[7][5] = new Celda("green");
        cubo[7][6] = new Celda("green");
        cubo[7][7] = new Celda("green");
        //RIGHT
        cubo[4][8] = new Celda("red");
        cubo[4][9] = new Celda("red");
        cubo[4][10] = new Celda("red");
        cubo[4][11] = new Celda("red");
        cubo[5][8] = new Celda("red");
        cubo[5][9] = new Celda("red");
        cubo[5][10] = new Celda("red");
        cubo[5][11] = new Celda("red");
        cubo[6][8] = new Celda("red");
        cubo[6][9] = new Celda("red");
        cubo[6][10] = new Celda("red");
        cubo[6][11] = new Celda("red");
        cubo[7][8] = new Celda("red");
        cubo[7][9] = new Celda("red");
        cubo[7][10] = new Celda("red");
        cubo[7][11] = new Celda("red");
        //DOWN
        cubo[8][4] = new Celda("yellow");
        cubo[8][5] = new Celda("yellow");
        cubo[8][6] = new Celda("yellow");
        cubo[8][7] = new Celda("yellow");
        cubo[9][4] = new Celda("yellow");
        cubo[9][5] = new Celda("yellow");
        cubo[9][6] = new Celda("yellow");
        cubo[9][7] = new Celda("yellow");
        cubo[10][4] = new Celda("yellow");
        cubo[10][5] = new Celda("yellow");
        cubo[10][6] = new Celda("yellow");
        cubo[10][7] = new Celda("yellow");
        cubo[11][4] = new Celda("yellow");
        cubo[11][5] = new Celda("yellow");
        cubo[11][6] = new Celda("yellow");
        cubo[11][7] = new Celda("yellow");
        //BACK
        cubo[12][4] = new Celda("blue");
        cubo[12][5] = new Celda("blue");
        cubo[12][6] = new Celda("blue");
        cubo[12][7] = new Celda("blue");
        cubo[13][4] = new Celda("blue");
        cubo[13][5] = new Celda("blue");
        cubo[13][6] = new Celda("blue");
        cubo[13][7] = new Celda("blue");
        cubo[14][4] = new Celda("blue");
        cubo[14][5] = new Celda("blue");
        cubo[14][6] = new Celda("blue");
        cubo[14][7] = new Celda("blue");
        cubo[15][4] = new Celda("blue");
        cubo[15][5] = new Celda("blue");
        cubo[15][6] = new Celda("blue");
        cubo[15][7] = new Celda("blue");
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
        String[] movimientos = {"F", "F'", "F2", "Fw", "Fw'", "Fw2", "B", "B'", "B2", "U", "U'", "U2", "Uw", "Uw'", "Uw2", "D", "D'", "D2", "R", "R'", "R2", "Rw", "Rw'", "Rw2", "L", "L'", "L2"};
        int num;
        int[] mezclaInt = new int[40];
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
            //TODO implementar lógica para que las mezclas de 4x4 cumplan con los requisitos de WCA
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
        celdaTempo = cubo[12][4];
        cubo[12][4] = cubo[15][4];
        cubo[15][4] = cubo[15][7];
        cubo[15][7] = cubo[12][7];
        cubo[12][7] = celdaTempo;
        celdaTempo = cubo[12][5];
        cubo[12][5] = cubo[14][4];
        cubo[14][4] = cubo[15][6];
        cubo[15][6] = cubo[13][7];
        cubo[13][7] = celdaTempo;
        celdaTempo = cubo[12][6];
        cubo[12][6] = cubo[13][4];
        cubo[13][4] = cubo[15][5];
        cubo[15][5] = cubo[14][7];
        cubo[14][7] = celdaTempo;
        celdaTempo = cubo[13][5];
        cubo[13][5] = cubo[14][5];
        cubo[14][5] = cubo[14][6];
        cubo[14][6] = cubo[13][6];
        cubo[13][6] = celdaTempo;
        
        celdaTempo = cubo[11][7];
        cubo[11][7] = cubo[7][0];
        cubo[7][0] = cubo[0][4];
        cubo[0][4] = cubo[4][11];
        cubo[4][11] = celdaTempo;
        celdaTempo = cubo[11][6];
        cubo[11][6] = cubo[6][0];
        cubo[6][0] = cubo[0][5];
        cubo[0][5] = cubo[5][11];
        cubo[5][11] = celdaTempo;
        celdaTempo = cubo[11][5];
        cubo[11][5] = cubo[5][0];
        cubo[5][0] = cubo[0][6];
        cubo[0][6] = cubo[6][11];
        cubo[6][11] = celdaTempo;
        celdaTempo = cubo[11][4];
        cubo[11][4] = cubo[4][0];
        cubo[4][0] = cubo[0][7];
        cubo[0][7] = cubo[7][11];
        cubo[7][11] = celdaTempo;
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
        celdaTempo = cubo[8][4];
        cubo[8][4] = cubo[11][4];
        cubo[11][4] = cubo[11][7];
        cubo[11][7] = cubo[8][7];
        cubo[8][7] = celdaTempo;
        celdaTempo = cubo[8][5];
        cubo[8][5] = cubo[10][4];
        cubo[10][4] = cubo[11][6];
        cubo[11][6] = cubo[9][7];
        cubo[9][7] = celdaTempo;
        celdaTempo = cubo[8][6];
        cubo[8][6] = cubo[9][4];
        cubo[9][4] = cubo[11][5];
        cubo[11][5] = cubo[10][7];
        cubo[10][7] = celdaTempo;
        celdaTempo = cubo[9][5];
        cubo[9][5] = cubo[10][5];
        cubo[10][5] = cubo[10][6];
        cubo[10][6] = cubo[9][6];
        cubo[9][6] = celdaTempo;
        
        celdaTempo = cubo[12][4];
        cubo[12][4] = cubo[7][11];
        cubo[7][11] = cubo[7][7];
        cubo[7][7] = cubo[7][3];
        cubo[7][3] = celdaTempo;
        celdaTempo = cubo[12][5];
        cubo[12][5] = cubo[7][10];
        cubo[7][10] = cubo[7][6];
        cubo[7][6] = cubo[7][2];
        cubo[7][2] = celdaTempo;
        celdaTempo = cubo[12][6];
        cubo[12][6] = cubo[7][9];
        cubo[7][9] = cubo[7][5];
        cubo[7][5] = cubo[7][1];
        cubo[7][1] = celdaTempo;
        celdaTempo = cubo[12][7];
        cubo[12][7] = cubo[7][8];
        cubo[7][8] = cubo[7][4];
        cubo[7][4] = cubo[7][0];
        cubo[7][0] = celdaTempo;
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
        celdaTempo = cubo[4][4];
        cubo[4][4] = cubo[7][4];
        cubo[7][4] = cubo[7][7];
        cubo[7][7] = cubo[4][7];
        cubo[4][7] = celdaTempo;
        celdaTempo = cubo[4][5];
        cubo[4][5] = cubo[6][4];
        cubo[6][4] = cubo[7][6];
        cubo[7][6] = cubo[5][7];
        cubo[5][7] = celdaTempo;
        celdaTempo = cubo[4][6];
        cubo[4][6] = cubo[5][4];
        cubo[5][4] = cubo[7][5];
        cubo[7][5] = cubo[6][7];
        cubo[6][7] = celdaTempo;
        celdaTempo = cubo[5][5];
        cubo[5][5] = cubo[6][5];
        cubo[6][5] = cubo[6][6];
        cubo[6][6] = cubo[5][6];
        cubo[5][6] = celdaTempo;
        
        celdaTempo = cubo[3][7];
        cubo[3][7] = cubo[4][3];
        cubo[4][3] = cubo[8][4];
        cubo[8][4] = cubo[7][8];
        cubo[7][8] = celdaTempo;
        celdaTempo = cubo[3][6];
        cubo[3][6] = cubo[5][3];
        cubo[5][3] = cubo[8][5];
        cubo[8][5] = cubo[6][8];
        cubo[6][8] = celdaTempo;
        celdaTempo = cubo[3][5];
        cubo[3][5] = cubo[6][3];
        cubo[6][3] = cubo[8][6];
        cubo[8][6] = cubo[5][8];
        cubo[5][8] = celdaTempo;
        celdaTempo = cubo[3][4];
        cubo[3][4] = cubo[7][3];
        cubo[7][3] = cubo[8][7];
        cubo[8][7] = cubo[4][8];
        cubo[4][8] = celdaTempo;
    }

    public void fw(int cant) {
        for (int i = 0; i < cant; i++) {
            fw();
        }
    }

    public void fw() {
        f();
        Celda celdaTempo;
        celdaTempo = cubo[2][7];
        cubo[2][7] = cubo[4][2];
        cubo[4][2] = cubo[9][4];
        cubo[9][4] = cubo[7][9];
        cubo[7][9] = celdaTempo;
        celdaTempo = cubo[2][6];
        cubo[2][6] = cubo[5][2];
        cubo[5][2] = cubo[9][5];
        cubo[9][5] = cubo[6][9];
        cubo[6][9] = celdaTempo;
        celdaTempo = cubo[2][5];
        cubo[2][5] = cubo[6][2];
        cubo[6][2] = cubo[9][6];
        cubo[9][6] = cubo[5][9];
        cubo[5][9] = celdaTempo;
        celdaTempo = cubo[2][4];
        cubo[2][4] = cubo[7][2];
        cubo[7][2] = cubo[9][7];
        cubo[9][7] = cubo[4][9];
        cubo[4][9] = celdaTempo;
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
        celdaTempo = cubo[4][0];
        cubo[4][0] = cubo[7][0];
        cubo[7][0] = cubo[7][3];
        cubo[7][3] = cubo[4][3];
        cubo[4][3] = celdaTempo;
        celdaTempo = cubo[4][1];
        cubo[4][1] = cubo[6][0];
        cubo[6][0] = cubo[7][2];
        cubo[7][2] = cubo[5][3];
        cubo[5][3] = celdaTempo;
        celdaTempo = cubo[4][2];
        cubo[4][2] = cubo[5][0];
        cubo[5][0] = cubo[7][1];
        cubo[7][1] = cubo[6][3];
        cubo[6][3] = celdaTempo;
        celdaTempo = cubo[5][1];
        cubo[5][1] = cubo[6][1];
        cubo[6][1] = cubo[6][2];
        cubo[6][2] = cubo[5][2];
        cubo[5][2] = celdaTempo;
        
        celdaTempo = cubo[12][4];
        cubo[12][4] = cubo[8][4];
        cubo[8][4] = cubo[4][4];
        cubo[4][4] = cubo[0][4];
        cubo[0][4] = celdaTempo;
        celdaTempo = cubo[13][4];
        cubo[13][4] = cubo[9][4];
        cubo[9][4] = cubo[5][4];
        cubo[5][4] = cubo[1][4];
        cubo[1][4] = celdaTempo;
        celdaTempo = cubo[14][4];
        cubo[14][4] = cubo[10][4];
        cubo[10][4] = cubo[6][4];
        cubo[6][4] = cubo[2][4];
        cubo[2][4] = celdaTempo;
        celdaTempo = cubo[15][4];
        cubo[15][4] = cubo[11][4];
        cubo[11][4] = cubo[7][4];
        cubo[7][4] = cubo[3][4];
        cubo[3][4] = celdaTempo;
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
        celdaTempo = cubo[4][8];
        cubo[4][8] = cubo[7][8];
        cubo[7][8] = cubo[7][11];
        cubo[7][11] = cubo[4][11];
        cubo[4][11] = celdaTempo;
        celdaTempo = cubo[4][9];
        cubo[4][9] = cubo[6][8];
        cubo[6][8] = cubo[7][10];
        cubo[7][10] = cubo[5][11];
        cubo[5][11] = celdaTempo;
        celdaTempo = cubo[4][10];
        cubo[4][10] = cubo[5][8];
        cubo[5][8] = cubo[7][9];
        cubo[7][9] = cubo[6][11];
        cubo[6][11] = celdaTempo;
        celdaTempo = cubo[5][9];
        cubo[5][9] = cubo[6][9];
        cubo[6][9] = cubo[6][10];
        cubo[6][10] = cubo[5][10];
        cubo[5][10] = celdaTempo;
        
        celdaTempo = cubo[0][7];
        cubo[0][7] = cubo[4][7];
        cubo[4][7] = cubo[8][7];
        cubo[8][7] = cubo[12][7];
        cubo[12][7] = celdaTempo;
        celdaTempo = cubo[1][7];
        cubo[1][7] = cubo[5][7];
        cubo[5][7] = cubo[9][7];
        cubo[9][7] = cubo[13][7];
        cubo[13][7] = celdaTempo;
        celdaTempo = cubo[2][7];
        cubo[2][7] = cubo[6][7];
        cubo[6][7] = cubo[10][7];
        cubo[10][7] = cubo[14][7];
        cubo[14][7] = celdaTempo;
        celdaTempo = cubo[3][7];
        cubo[3][7] = cubo[7][7];
        cubo[7][7] = cubo[11][7];
        cubo[11][7] = cubo[15][7];
        cubo[15][7] = celdaTempo;
    }
    
    public void rw() {
    	r();
        Celda celdaTempo;
        celdaTempo = cubo[0][6];
        cubo[0][6] = cubo[4][6];
        cubo[4][6] = cubo[8][6];
        cubo[8][6] = cubo[12][6];
        cubo[12][6] = celdaTempo;
        celdaTempo = cubo[1][6];
        cubo[1][6] = cubo[5][6];
        cubo[5][6] = cubo[9][6];
        cubo[9][6] = cubo[13][6];
        cubo[13][6] = celdaTempo;
        celdaTempo = cubo[2][6];
        cubo[2][6] = cubo[6][6];
        cubo[6][6] = cubo[10][6];
        cubo[10][6] = cubo[14][6];
        cubo[14][6] = celdaTempo;
        celdaTempo = cubo[3][6];
        cubo[3][6] = cubo[7][6];
        cubo[7][6] = cubo[11][6];
        cubo[11][6] = cubo[15][6];
        cubo[15][6] = celdaTempo;
    }
    
    public void rw(int cant) {
        for (int i = 0; i < cant; i++) {
            rw();
        }
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
        celdaTempo = cubo[0][4];
        cubo[0][4] = cubo[3][4];
        cubo[3][4] = cubo[3][7];
        cubo[3][7] = cubo[0][7];
        cubo[0][7] = celdaTempo;
        celdaTempo = cubo[0][5];
        cubo[0][5] = cubo[2][4];
        cubo[2][4] = cubo[3][6];
        cubo[3][6] = cubo[1][7];
        cubo[1][7] = celdaTempo;
        celdaTempo = cubo[0][6];
        cubo[0][6] = cubo[1][4];
        cubo[1][4] = cubo[3][5];
        cubo[3][5] = cubo[2][7];
        cubo[2][7] = celdaTempo;
        celdaTempo = cubo[1][5];
        cubo[1][5] = cubo[2][5];
        cubo[2][5] = cubo[2][6];
        cubo[2][6] = cubo[1][6];
        cubo[1][6] = celdaTempo;
        
        celdaTempo = cubo[4][0];
        cubo[4][0] = cubo[4][4];
        cubo[4][4] = cubo[4][8];
        cubo[4][8] = cubo[15][7];
        cubo[15][7] = celdaTempo;
        celdaTempo = cubo[4][1];
        cubo[4][1] = cubo[4][5];
        cubo[4][5] = cubo[4][9];
        cubo[4][9] = cubo[15][6];
        cubo[15][6] = celdaTempo;
        celdaTempo = cubo[4][2];
        cubo[4][2] = cubo[4][6];
        cubo[4][6] = cubo[4][10];
        cubo[4][10] = cubo[15][5];
        cubo[15][5] = celdaTempo;
        celdaTempo = cubo[4][3];
        cubo[4][3] = cubo[4][7];
        cubo[4][7] = cubo[4][11];
        cubo[4][11] = cubo[15][4];
        cubo[15][4] = celdaTempo;
    }

    public void uw(int cant) {
        for (int i = 0; i < cant; i++) {
            uw();
        }
    }

    public void uw() {
    	u();
        Celda celdaTempo;
        celdaTempo = cubo[5][0];
        cubo[5][0] = cubo[5][4];
        cubo[5][4] = cubo[5][8];
        cubo[5][8] = cubo[14][7];
        cubo[14][7] = celdaTempo;
        celdaTempo = cubo[5][1];
        cubo[5][1] = cubo[5][5];
        cubo[5][5] = cubo[5][9];
        cubo[5][9] = cubo[14][6];
        cubo[14][6] = celdaTempo;
        celdaTempo = cubo[5][2];
        cubo[5][2] = cubo[5][6];
        cubo[5][6] = cubo[5][10];
        cubo[5][10] = cubo[14][5];
        cubo[14][5] = celdaTempo;
        celdaTempo = cubo[5][3];
        cubo[5][3] = cubo[5][7];
        cubo[5][7] = cubo[5][11];
        cubo[5][11] = cubo[14][4];
        cubo[14][4] = celdaTempo;
    }

    @Override
    public void x(int cant) {
        for (int i = 0; i < cant; i++) {
            x();
        }
    }

    @Override
    public void x() {
    	//TODO implementar rotacion del cubo
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void y(int cant) {
        for (int i = 0; i < cant; i++) {
            y();
        }
    }

    @Override
    public void y() {
    	//TODO implementar rotacion del cubo
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void z(int cant) {
        for (int i = 0; i < cant; i++) {
            z();
        }
    }

    @Override
    public void z() {
    	//TODO implementar rotacion del cubo
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
