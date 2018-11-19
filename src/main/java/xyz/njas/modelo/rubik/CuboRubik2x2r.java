package xyz.njas.modelo.rubik;

import java.io.Serializable;

import xyz.njas.util.ScrambleGenerator;

public class CuboRubik2x2r extends CuboRubikNxN implements Serializable {

	private static final long serialVersionUID = 1L;

	public CuboRubik2x2r(Integer idTipoCubo, String nombre) {
		super(idTipoCubo, nombre, 2);
	}

	@Override
	public String[] generarMezcla() {
		return ScrambleGenerator.generarMezcla("222");
	}

	@Override
	public boolean girosAdicionales(String giro) {
		System.out.println("giro no válido para 2x2x2");
		return false;
	}

}
