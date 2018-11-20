package xyz.njas.modelo.rubik;

import java.io.Serializable;

import org.apache.log4j.Logger;

import xyz.njas.util.ScrambleGenerator;

public class CuboRubik5x5 extends CuboRubikNxN implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(CuboRubik5x5.class);

	public CuboRubik5x5(Integer idTipoCubo, String nombre) {
		super(idTipoCubo, nombre, 5);
	}

	@Override
	public String[] generarMezcla() {
		return ScrambleGenerator.generarMezcla("555");
	}

	@Override
	public boolean girosAdicionales(String giro) {
		if (giro.equals("bw")) {
			bw(1);
		} else if (giro.equals("bw2")) {
			bw(2);
		} else if (giro.equals("bw'")) {
			bw(3);
		} else if (giro.equals("dw")) {
			dw(1);
		} else if (giro.equals("dw2")) {
			dw(2);
		} else if (giro.equals("dw'")) {
			dw(3);
		} else if (giro.equals("fw")) {
			fw(1);
		} else if (giro.equals("fw2")) {
			fw(2);
		} else if (giro.equals("fw'")) {
			fw(3);
		} else if (giro.equals("lw")) {
			lw(1);
		} else if (giro.equals("lw2")) {
			lw(2);
		} else if (giro.equals("lw'")) {
			lw(3);
		} else if (giro.equals("rw")) {
			rw(1);
		} else if (giro.equals("rw2")) {
			rw(2);
		} else if (giro.equals("rw'")) {
			rw(3);
		} else if (giro.equals("uw")) {
			uw(1);
		} else if (giro.equals("uw2")) {
			uw(2);
		} else if (giro.equals("uw'")) {
			uw(3);
		} else {
			log.trace("giro no válido");
			return false;
		}
		return true;
	}

	public void bw(int cant) {
		for (int i = 0; i < cant; i++) {
			bw();
		}
	}

	public void bw() {
		b();
		for (int i = 0; i < n; i++) {
			Celda aux = top.getCara()[1][i];
			top.getCara()[1][i] = right.getCara()[i][n - 2];
			right.getCara()[i][n - 2] = bottom.getCara()[n - 2][n - 1 - i];
			bottom.getCara()[n - 2][n - 1 - i] = left.getCara()[n - 1 - i][1];
			left.getCara()[n - 1 - i][1] = aux;
		}
	}

	public void dw(int cant) {
		for (int i = 0; i < cant; i++) {
			dw();
		}
	}

	public void dw() {
		d();
		for (int i = 0; i < n; i++) {
			Celda aux = back.getCara()[n - 2][i];
			back.getCara()[n - 2][i] = right.getCara()[n - 2][i];
			right.getCara()[n - 2][i] = front.getCara()[n - 2][i];
			front.getCara()[n - 2][i] = left.getCara()[n - 2][i];
			left.getCara()[n - 2][i] = aux;
		}
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

	public void lw(int cant) {
		for (int i = 0; i < cant; i++) {
			lw();
		}
	}

	public void lw() {
		l();
		for (int i = 0; i < n; i++) {
			Celda aux = top.getCara()[i][1];
			top.getCara()[i][1] = back.getCara()[n - 1 - i][n - 2];
			back.getCara()[n - 1 - i][n - 2] = bottom.getCara()[i][1];
			bottom.getCara()[i][1] = front.getCara()[i][1];
			front.getCara()[i][1] = aux;
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
