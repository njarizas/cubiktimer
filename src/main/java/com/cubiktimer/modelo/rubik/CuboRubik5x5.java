package com.cubiktimer.modelo.rubik;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.cubiktimer.util.Constantes;

public class CuboRubik5x5 extends CuboRubik4x4 implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(CuboRubik5x5.class);

	public CuboRubik5x5(Integer idTipoCubo, String nombre) {
		this(idTipoCubo, nombre, Constantes.CUBO_5X5X5, 5);
	}

	protected CuboRubik5x5(Integer idTipoCubo, String nombre, String parametro, int n) {
		super(idTipoCubo, nombre, parametro, n);
	}

	@Override
	public boolean girar(String giro) {
		giro = giro.toLowerCase().trim();
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
		} else if (giro.equals("lw")) {
			lw(1);
		} else if (giro.equals("lw2")) {
			lw(2);
		} else if (giro.equals("lw'")) {
			lw(3);
		} else {
			log.trace("verificando si es giro de 4x4");
			return super.girar(giro);
		}
		log.trace("es un giro válido de 5x5");
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

}
