package xyz.njas.modelo.rubik;

import java.io.Serializable;

import xyz.njas.util.ScrambleGenerator;

public class CuboRubik3x3 extends CuboRubikNxN implements Serializable {

	private static final long serialVersionUID = 1L;

	public CuboRubik3x3(Integer idTipoCubo, String nombre) {
		super(idTipoCubo, nombre, 3);
	}

	@Override
	public String[] generarMezcla() {
		return ScrambleGenerator.generarMezcla("333");
	}

	@Override
	public boolean girosAdicionales(String giro) {
		System.out.println("giro no válido para 3x3x3");
		return false;
	}

}
