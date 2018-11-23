package xyz.njas;

import com.cubiktimer.modelo.rubik.CaraRubik;
import com.cubiktimer.modelo.rubik.Celda;
import com.cubiktimer.util.Constantes;

public class TestCaraRubik {

	public static void main(String[] args) {
		CaraRubik cara = new CaraRubik(10, Constantes.COLOR_BLANCO);
		for (int i = 0; i < cara.getN(); i++) {
			for (int j = 0; j < cara.getN(); j++) {
				cara.getCara()[i][j] = new Celda(String.valueOf((i * 10) + j + "   "));
			}
		}
		mostrar(cara);
		cara.girarCara();
		System.out.println("------------------------------");
		mostrar(cara);
	}

	private static void mostrar(CaraRubik cara) {
		for (int i = 0; i < cara.getN(); i++) {
			for (int j = 0; j < cara.getN(); j++) {
				System.out.print(cara.getCara()[i][j].getSigla());
			}
			System.out.println();
		}
	}

}
