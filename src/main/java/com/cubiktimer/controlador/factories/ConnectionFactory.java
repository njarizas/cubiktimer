/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.controlador.factories;

import java.sql.Connection;
import java.sql.SQLException;

import com.cubiktimer.config.ConexionDatabase;
import com.cubiktimer.config.CubikTimerDataSource;
import com.cubiktimer.util.Constantes;

public class ConnectionFactory {

	private ConnectionFactory() {

	}

	public static Connection getConnection() throws SQLException {
		if ("singleton".equals(Constantes.TIPO_CONEXION)) {
			return ConexionDatabase.getInstance();
		} else if ("pool".equals(Constantes.TIPO_CONEXION)) {
			return CubikTimerDataSource.getConnection();
		}
		return CubikTimerDataSource.getConnection();
	}

}
