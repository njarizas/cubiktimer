package xyz.njas.rubik.modelo;

import java.io.Serializable;
import java.util.Random;

public class Skewb extends Puzzle implements Serializable{

	private static final long serialVersionUID = 1L;

	private CaraSkewb top;
	private CaraSkewb left;
	private CaraSkewb front;
	private CaraSkewb right;
	private CaraSkewb bottom;
	private CaraSkewb back;

	public Skewb(Integer idTipoCubo, String nombre) {
		super(idTipoCubo, nombre);
		top = new CaraSkewb("white");
		left = new CaraSkewb("#ff6702");
		front = new CaraSkewb("green");
		right = new CaraSkewb("red");
		bottom = new CaraSkewb("yellow");
		back = new CaraSkewb("blue");
	}

	@Override
	public boolean girar(String giro) {
		giro = giro.toLowerCase().trim();
		if (giro.equals("u")){
			u(1);
		} else if (giro.equals("u'")){
			u(2);
		} else if (giro.equals("b")){
			b(1);
		} else if (giro.equals("b'")){
			b(2);
		} else if (giro.equals("r")){
			r(1);
		} else if (giro.equals("r'")){
			r(2);
		} else if (giro.equals("l")){
			l(1);
		} else if (giro.equals("l'")){
			l(2);
		} else{
			System.out.println("giro no válido");
			return false;
		}
		return true;
	}

	public void u(int cant) {
		for (int i = 0; i < cant; i++) {
			u();
		}
	}

	public void u() {
		//TODO implementar giro
	}

	public void b(int cant) {
		for (int i = 0; i < cant; i++) {
			b();
		}
	}

	public void b() {
		Celda celdaTempo;
		celdaTempo = back.getCeldaNoroccidente();
		back.setCeldaNoroccidente(bottom.getCeldaSuroccidente());
		bottom.setCeldaSuroccidente(left.getCeldaSuroccidente());
		left.setCeldaSuroccidente(celdaTempo);
		
		celdaTempo = back.getCeldaCentro();
		back.setCeldaCentro(bottom.getCeldaCentro());
		bottom.setCeldaCentro(left.getCeldaCentro());
		left.setCeldaCentro(celdaTempo);
		
		celdaTempo = top.getCeldaNoroccidente();
		top.setCeldaNoroccidente(right.getCeldaSuroriente());
		right.setCeldaSuroriente(front.getCeldaSuroccidente());
		front.setCeldaSuroccidente(celdaTempo);
		
		celdaTempo = back.getCeldaSuroccidente();
		back.setCeldaSuroccidente(bottom.getCeldaSuroriente());
		bottom.setCeldaSuroriente(left.getCeldaSuroriente());
		left.setCeldaSuroriente(celdaTempo);
		
		celdaTempo = left.getCeldaNoroccidente();
		left.setCeldaNoroccidente(back.getCeldaNororiente());
		back.setCeldaNororiente(bottom.getCeldaNororiente());
		bottom.setCeldaNororiente(celdaTempo);
	}

	public void r(int cant) {
		for (int i = 0; i < cant; i++) {
			r();
		}
	}

	public void r() {
		//TODO implementar giro
	}

	public void l(int cant) {
		for (int i = 0; i < cant; i++) {
			l();
		}
	}

	public void l() {
		//TODO implementar giro
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

	@Override
	public String[] generarMezcla() {
		String[] movimientos = {"U", "U'", "B", "B'", "R", "R'", "L", "L'"};
		int num;
		int[] mezclaInt = new int[15];
		String[] mezcla = new String[mezclaInt.length];
		Random random = new Random();
		for (int i = 0; i < mezcla.length; i++) {
			num = random.nextInt(movimientos.length);
			//para que no se gire dos veces seguidas la misma cara
			if (i != 0) {
				while (mezclaInt[i - 1] / 2 == num / 2) {
					num = random.nextInt(movimientos.length);
				}
			}
			mezclaInt[i] = num;
			mezcla[i] = movimientos[mezclaInt[i]];
		}
		return mezcla;
	}
	
	

	@Override
	public String toString() {
		System.out.println(top);
		System.out.print(left);
		System.out.print(front);
		System.out.println(right);
		System.out.println(bottom);
		System.out.println(back);
		return "";
	}

	public CaraSkewb getTop() {
		return top;
	}

	public void setTop(CaraSkewb top) {
		this.top = top;
	}

	public CaraSkewb getLeft() {
		return left;
	}

	public void setLeft(CaraSkewb left) {
		this.left = left;
	}

	public CaraSkewb getFront() {
		return front;
	}

	public void setFront(CaraSkewb front) {
		this.front = front;
	}

	public CaraSkewb getRight() {
		return right;
	}

	public void setRight(CaraSkewb right) {
		this.right = right;
	}

	public CaraSkewb getBottom() {
		return bottom;
	}

	public void setBottom(CaraSkewb bottom) {
		this.bottom = bottom;
	}

	public CaraSkewb getBack() {
		return back;
	}

	public void setBack(CaraSkewb back) {
		this.back = back;
	}

}

