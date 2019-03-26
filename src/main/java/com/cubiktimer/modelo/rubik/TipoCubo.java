package com.cubiktimer.modelo.rubik;

public enum TipoCubo {

	CUBO_2X2X2(5, "2X2X2"), CUBO_3X3X3(6, "3X3X3"), CUBO_4X4X4(7, "4X4X4"), CUBO_5X5X5(8, "5X5X5"),
	CUBO_6X6X6(9, "6X6X6"), CUBO_7X7X7(10, "7X7X7"), CUBO_3X3X3_BLD(11, "3X3X3 BLD"),
	CUBO_3X3X3_WITH_FEET(12, "3X3X3 With Feet"), CUBO_3X3X3_OH(13, "3X3X3 OH"), CUBO_4X4X4_BLD(14, "4X4X4 BLD"),
	CUBO_5X5X5_BLD(15, "5X5X5 BLD"), SKEWB(24, "SKEWB"), MEGAMINX(25, "MEGAMINX"), PYRAMINX(26, "PYRAMINX"),
	SQUARE_1(27, "SQUARE_1");

	private final Integer id;
	private final String nombre;

	TipoCubo(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

}
