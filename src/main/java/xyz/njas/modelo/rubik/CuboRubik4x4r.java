package xyz.njas.modelo.rubik;

import java.io.Serializable;

import xyz.njas.util.ScrambleGenerator;

public class CuboRubik4x4r extends CuboRubikNxN implements Serializable {

	private static final long serialVersionUID = 1L;

	public CuboRubik4x4r(Integer idTipoCubo, String nombre) {
		super(idTipoCubo, nombre, 4);
	}

	@Override
	public String[] generarMezcla() {
		return ScrambleGenerator.generarMezcla("444");
	}

	@Override
	public boolean girosAdicionales(String giro) {
		if (giro.equals("fw")) {
			fw(1);
		} else if (giro.equals("fw2")) {
			fw(2);
		} else if (giro.equals("fw'")) {
			fw(3);
		} else if (giro.equals("uw")) {
			uw(1);
		} else if (giro.equals("uw2")) {
			uw(2);
		} else if (giro.equals("uw'")) {
			uw(3);
		} else if (giro.equals("rw")) {
			rw(1);
		} else if (giro.equals("rw2")) {
			rw(2);
		} else if (giro.equals("rw'")) {
			rw(3);
		} else {
			System.out.println("giro no válido");
			return false;
		}
		return true;
	}

	public void fw(int cant) {
		for (int i = 0; i < cant; i++) {
			fw();
		}
	}

	public void fw() {
		f();
		for (int i = 0; i < n; i++) {
			Celda aux = top.getCara()[n - 2][i];
			top.getCara()[n - 2][i] = left.getCara()[n - 1 - i][n - 2];
			left.getCara()[n - 1 - i][n - 2] = bottom.getCara()[1][n - 1 - i];
			bottom.getCara()[1][n - 1 - i] = right.getCara()[i][1];
			right.getCara()[i][1] = aux;
		}
	}

	public void rw(int cant) {
		for (int i = 0; i < cant; i++) {
			rw();
		}
	}

	public void rw() {
		r();
		for (int i = 0; i < n; i++) {
			Celda aux = top.getCara()[i][n - 2];
			top.getCara()[i][n - 2] = front.getCara()[i][n - 2];
			front.getCara()[i][n - 2] = bottom.getCara()[i][n - 2];
			bottom.getCara()[i][n - 2] = back.getCara()[n - 1 - i][1];
			back.getCara()[n - 1 - i][1] = aux;
		}
	}

	public void uw(int cant) {
		for (int i = 0; i < cant; i++) {
			uw();
		}
	}

	public void uw() {
		u();
		for (int i = 0; i < n; i++) {
			Celda aux = left.getCara()[1][i];
			left.getCara()[1][i] = front.getCara()[1][i];
			front.getCara()[1][i] = right.getCara()[1][i];
			right.getCara()[1][i] = back.getCara()[1][i];
			back.getCara()[1][i] = aux;
		}
	}

}
