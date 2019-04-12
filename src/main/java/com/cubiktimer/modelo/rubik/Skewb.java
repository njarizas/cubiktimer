package com.cubiktimer.modelo.rubik;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.cubiktimer.util.Constantes;

public class Skewb extends Puzzle implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(Skewb.class);

	private CaraSkewb top;
	private CaraSkewb left;
	private CaraSkewb front;
	private CaraSkewb right;
	private CaraSkewb bottom;
	private CaraSkewb back;

	public Skewb(Integer idTipoCubo, String nombre) {
		super(idTipoCubo, nombre, Constantes.SKEWB);
		top = new CaraSkewb(Constantes.COLOR_BLANCO);
		left = new CaraSkewb(Constantes.COLOR_NARANJA);
		front = new CaraSkewb(Constantes.COLOR_VERDE);
		right = new CaraSkewb(Constantes.COLOR_ROJO);
		bottom = new CaraSkewb(Constantes.COLOR_AMARILLO);
		back = new CaraSkewb(Constantes.COLOR_AZUL);
	}

	@Override
	public boolean girar(String giro) {
		giro = giro.toLowerCase().trim();
		if (giro.equals("u")) {
			u(1);
		} else if (giro.equals("u'")) {
			u(2);
		} else if (giro.equals("b")) {
			b(1);
		} else if (giro.equals("b'")) {
			b(2);
		} else if (giro.equals("r")) {
			r(1);
		} else if (giro.equals("r'")) {
			r(2);
		} else if (giro.equals("l")) {
			l(1);
		} else if (giro.equals("l'")) {
			l(2);
		} else {
			log.trace("giro no válido: " + giro);
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
		Celda celdaTempo;
		celdaTempo = top.getCeldaCentro();
		top.setCeldaCentro(back.getCeldaCentro());
		back.setCeldaCentro(left.getCeldaCentro());
		left.setCeldaCentro(celdaTempo);

		celdaTempo = top.getCeldaNororiente();
		top.setCeldaNororiente(back.getCeldaNoroccidente());
		back.setCeldaNoroccidente(left.getCeldaNororiente());
		left.setCeldaNororiente(celdaTempo);

		celdaTempo = back.getCeldaSuroriente();
		back.setCeldaSuroriente(left.getCeldaSuroccidente());
		left.setCeldaSuroccidente(top.getCeldaSuroccidente());
		top.setCeldaSuroccidente(celdaTempo);

		celdaTempo = right.getCeldaNororiente();
		right.setCeldaNororiente(bottom.getCeldaSuroccidente());
		bottom.setCeldaSuroccidente(front.getCeldaNoroccidente());
		front.setCeldaNoroccidente(celdaTempo);

		celdaTempo = top.getCeldaNoroccidente();
		top.setCeldaNoroccidente(back.getCeldaSuroccidente());
		back.setCeldaSuroccidente(left.getCeldaNoroccidente());
		left.setCeldaNoroccidente(celdaTempo);
	}

	public void b(int cant) {
		for (int i = 0; i < cant; i++) {
			b();
		}
	}

	public void b() {
		Celda celdaTempo;
		celdaTempo = back.getCeldaCentro();
		back.setCeldaCentro(bottom.getCeldaCentro());
		bottom.setCeldaCentro(left.getCeldaCentro());
		left.setCeldaCentro(celdaTempo);

		celdaTempo = back.getCeldaNoroccidente();
		back.setCeldaNoroccidente(bottom.getCeldaSuroccidente());
		bottom.setCeldaSuroccidente(left.getCeldaSuroccidente());
		left.setCeldaSuroccidente(celdaTempo);

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
		back.setCeldaNororiente(bottom.getCeldaNoroccidente());
		bottom.setCeldaNoroccidente(celdaTempo);
	}

	public void r(int cant) {
		for (int i = 0; i < cant; i++) {
			r();
		}
	}

	public void r() {
		Celda celdaTempo;
		celdaTempo = right.getCeldaCentro();
		right.setCeldaCentro(bottom.getCeldaCentro());
		bottom.setCeldaCentro(back.getCeldaCentro());
		back.setCeldaCentro(celdaTempo);

		celdaTempo = right.getCeldaSuroccidente();
		right.setCeldaSuroccidente(bottom.getCeldaSuroccidente());
		bottom.setCeldaSuroccidente(back.getCeldaSuroriente());
		back.setCeldaSuroriente(celdaTempo);

		celdaTempo = front.getCeldaSuroriente();
		front.setCeldaSuroriente(left.getCeldaSuroccidente());
		left.setCeldaSuroccidente(top.getCeldaNororiente());
		top.setCeldaNororiente(celdaTempo);

		celdaTempo = bottom.getCeldaNororiente();
		bottom.setCeldaNororiente(back.getCeldaNoroccidente());
		back.setCeldaNoroccidente(right.getCeldaNororiente());
		right.setCeldaNororiente(celdaTempo);

		celdaTempo = right.getCeldaSuroriente();
		right.setCeldaSuroriente(bottom.getCeldaSuroriente());
		bottom.setCeldaSuroriente(back.getCeldaNororiente());
		back.setCeldaNororiente(celdaTempo);
	}

	public void l(int cant) {
		for (int i = 0; i < cant; i++) {
			l();
		}
	}

	public void l() {
		Celda celdaTempo;
		celdaTempo = front.getCeldaCentro();
		front.setCeldaCentro(left.getCeldaCentro());
		left.setCeldaCentro(bottom.getCeldaCentro());
		bottom.setCeldaCentro(celdaTempo);

		celdaTempo = front.getCeldaNoroccidente();
		front.setCeldaNoroccidente(left.getCeldaSuroccidente());
		left.setCeldaSuroccidente(bottom.getCeldaNororiente());
		bottom.setCeldaNororiente(celdaTempo);

		celdaTempo = top.getCeldaSuroccidente();
		top.setCeldaSuroccidente(back.getCeldaNoroccidente());
		back.setCeldaNoroccidente(right.getCeldaSuroccidente());
		right.setCeldaSuroccidente(celdaTempo);

		celdaTempo = left.getCeldaNororiente();
		left.setCeldaNororiente(bottom.getCeldaSuroccidente());
		bottom.setCeldaSuroccidente(front.getCeldaSuroriente());
		front.setCeldaSuroriente(celdaTempo);

		celdaTempo = front.getCeldaSuroccidente();
		front.setCeldaSuroccidente(left.getCeldaSuroriente());
		left.setCeldaSuroriente(bottom.getCeldaNoroccidente());
		bottom.setCeldaNoroccidente(celdaTempo);
	}

	@Override
	public String mezclar(String[] mezcla) {
		StringBuilder secuenciaMezclada = new StringBuilder("");
		for (String giro : mezcla) {
			if (girar(giro)) {
				secuenciaMezclada.append(giro.toUpperCase() + " ");
			}
		}
		return secuenciaMezclada.toString();
	}

	@Override
	public String toString() {
		StringBuilder retorno = new StringBuilder("");
		retorno.append(getTop());
		retorno.append("\n");
		retorno.append(getLeft());
		retorno.append("\n");
		retorno.append(getFront());
		retorno.append("\n");
		retorno.append(getRight());
		retorno.append("\n");
		retorno.append(getBack());
		retorno.append("\n");
		retorno.append(getBottom());
		retorno.append("\n");
		return retorno.toString();
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

	@Override
	public boolean estaResuelto() {
		return (back.estaResuelto() && bottom.estaResuelto() && front.estaResuelto() && left.estaResuelto()
				&& right.estaResuelto() && top.estaResuelto());
	}

}
