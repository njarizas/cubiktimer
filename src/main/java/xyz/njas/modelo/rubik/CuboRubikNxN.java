package xyz.njas.modelo.rubik;

import java.io.Serializable;

import xyz.njas.util.Constantes;

public abstract class CuboRubikNxN extends CuboRubik implements Serializable {

	private static final long serialVersionUID = 1L;

	protected int n;
	protected CaraRubik top;
	protected CaraRubik left;
	protected CaraRubik front;
	protected CaraRubik right;
	protected CaraRubik bottom;
	protected CaraRubik back;

	public CuboRubikNxN(Integer idTipoCubo, String nombre, int n) {
		super(idTipoCubo, nombre);
		this.n = n;
		top = new CaraRubik(n, Constantes.COLOR_BLANCO);
		left = new CaraRubik(n, Constantes.COLOR_NARANJA);
		front = new CaraRubik(n, Constantes.COLOR_VERDE);
		right = new CaraRubik(n, Constantes.COLOR_ROJO);
		bottom = new CaraRubik(n, Constantes.COLOR_AMARILLO);
		back = new CaraRubik(n, Constantes.COLOR_AZUL);
	}

	@Override
	public void b(int cant) {
		for (int i = 0; i < cant; i++) {
			b();
		}
	}

	@Override
	public void b() {
		back.girarCara();
		for (int i = 0; i < n; i++) {
			Celda aux = top.getCara()[0][i];
			top.getCara()[0][i] = right.getCara()[i][n - 1];
			right.getCara()[i][n - 1] = bottom.getCara()[n - 1][n - 1 - i];
			bottom.getCara()[n - 1][n - 1 - i] = left.getCara()[n - 1 - i][0];
			left.getCara()[n - 1 - i][0] = aux;
		}
	}

	@Override
	public void d(int cant) {
		for (int i = 0; i < cant; i++) {
			d();
		}
	}

	@Override
	public void d() {
		bottom.girarCara();
		for (int i = 0; i < n; i++) {
			Celda aux = back.getCara()[n - 1][i];
			back.getCara()[n - 1][i] = right.getCara()[n - 1][i];
			right.getCara()[n - 1][i] = front.getCara()[n - 1][i];
			front.getCara()[n - 1][i] = left.getCara()[n - 1][i];
			left.getCara()[n - 1][i] = aux;
		}
	}

	@Override
	public void f(int cant) {
		for (int i = 0; i < cant; i++) {
			f();
		}
	}

	@Override
	public void f() {
		front.girarCara();
		for (int i = 0; i < n; i++) {
			Celda aux = top.getCara()[n - 1][i];
			top.getCara()[n - 1][i] = left.getCara()[n - 1 - i][n - 1];
			left.getCara()[n - 1 - i][n - 1] = bottom.getCara()[0][n - 1 - i];
			bottom.getCara()[0][n - 1 - i] = right.getCara()[i][0];
			right.getCara()[i][0] = aux;
		}
	}

	@Override
	public void l(int cant) {
		for (int i = 0; i < cant; i++) {
			l();
		}
	}

	@Override
	public void l() {
		left.girarCara();
		for (int i = 0; i < n; i++) {
			Celda aux = top.getCara()[i][0];
			top.getCara()[i][0] = back.getCara()[n - 1 - i][n - 1];
			back.getCara()[n - 1 - i][n - 1] = bottom.getCara()[i][0];
			bottom.getCara()[i][0] = front.getCara()[i][0];
			front.getCara()[i][0] = aux;
		}
	}

	@Override
	public void r(int cant) {
		for (int i = 0; i < cant; i++) {
			r();
		}
	}

	@Override
	public void r() {
		right.girarCara();
		for (int i = 0; i < n; i++) {
			Celda aux = top.getCara()[i][n-1];
			top.getCara()[i][n-1] = front.getCara()[i][n-1];
			front.getCara()[i][n-1] = bottom.getCara()[i][n-1];
			bottom.getCara()[i][n-1] = back.getCara()[n - 1 - i][0];
			back.getCara()[n - 1 - i][0] = aux;
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
		top.girarCara();
		for (int i = 0; i < n; i++) {
			Celda aux = left.getCara()[0][i];
			left.getCara()[0][i] = front.getCara()[0][i];
			front.getCara()[0][i] = right.getCara()[0][i];
			right.getCara()[0][i] = back.getCara()[0][i];
			back.getCara()[0][i] = aux;
		}
	}

	@Override
	public void x(int cant) {
		for (int i = 0; i < cant; i++) {
			x();
		}
	}

	@Override
	public void x() {
		left.girarCara();
		right.girarCara();
		right.girarCara();
		right.girarCara();
		back.girarCara();
		back.girarCara();
		bottom.girarCara();
		bottom.girarCara();
		CaraRubik aux = top;
		top = back;
		back = bottom;
		bottom = front;
		front = aux;
	}

	@Override
	public void y(int cant) {
		for (int i = 0; i < cant; i++) {
			y();
		}
	}

	@Override
	public void y() {
		top.girarCara();
		bottom.girarCara();
		bottom.girarCara();
		bottom.girarCara();
		CaraRubik aux = left;
		left = front;
		front = right;
		right = back;
		back = aux;
	}

	@Override
	public void z(int cant) {
		for (int i = 0; i < cant; i++) {
			z();
		}
	}

	@Override
	public void z() {
		front.girarCara();
		back.girarCara();
		back.girarCara();
		back.girarCara();
		left.girarCara();
		top.girarCara();
		right.girarCara();
		bottom.girarCara();
		CaraRubik aux = left;
		left = bottom;
		bottom = right;
		right = top;
		top = aux;
	}

	@Override
	public synchronized boolean girar(String giro) {
		giro = giro.toLowerCase().trim();
		if (giro.equals("f")) {
			f(1);
		} else if (giro.equals("f2")) {
			f(2);
		} else if (giro.equals("f'")) {
			f(3);
		} else if (giro.equals("u")) {
			u(1);
		} else if (giro.equals("u2")) {
			u(2);
		} else if (giro.equals("u'")) {
			u(3);
		} else if (giro.equals("r")) {
			r(1);
		} else if (giro.equals("r2")) {
			r(2);
		} else if (giro.equals("r'")) {
			r(3);
		} else if (giro.equals("l")) {
			l(1);
		} else if (giro.equals("l2")) {
			l(2);
		} else if (giro.equals("l'")) {
			l(3);
		} else if (giro.equals("b")) {
			b(1);
		} else if (giro.equals("b2")) {
			b(2);
		} else if (giro.equals("b'")) {
			b(3);
		} else if (giro.equals("d")) {
			d(1);
		} else if (giro.equals("d2")) {
			d(2);
		} else if (giro.equals("d'")) {
			d(3);
		} else if (giro.equals("z")) {
			z(1);
		} else if (giro.equals("z2")) {
			z(2);
		} else if (giro.equals("z'")) {
			z(3);
		} else if (giro.equals("x")) {
			x(1);
		} else if (giro.equals("x2")) {
			x(2);
		} else if (giro.equals("x'")) {
			x(3);
		} else if (giro.equals("y")) {
			y(1);
		} else if (giro.equals("y2")) {
			y(2);
		} else if (giro.equals("y'")) {
			y(3);
		} else {
			return girosAdicionales(giro);
		}
		return true;
	}
	
	public abstract boolean girosAdicionales(String giro);

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

	public CaraRubik getTop() {
		return top;
	}

	public void setTop(CaraRubik top) {
		this.top = top;
	}

	public CaraRubik getLeft() {
		return left;
	}

	public void setLeft(CaraRubik left) {
		this.left = left;
	}

	public CaraRubik getFront() {
		return front;
	}

	public void setFront(CaraRubik front) {
		this.front = front;
	}

	public CaraRubik getRight() {
		return right;
	}

	public void setRight(CaraRubik right) {
		this.right = right;
	}

	public CaraRubik getBottom() {
		return bottom;
	}

	public void setBottom(CaraRubik bottom) {
		this.bottom = bottom;
	}

	public CaraRubik getBack() {
		return back;
	}

	public void setBack(CaraRubik back) {
		this.back = back;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
	
	@Override
	public String toString() {
		System.out.println(getTop());
		System.out.print(getLeft());
		System.out.print(getFront());
		System.out.print(getRight());
		System.out.println(getBack());
		System.out.println(getBottom());
		return "";
	}

}
