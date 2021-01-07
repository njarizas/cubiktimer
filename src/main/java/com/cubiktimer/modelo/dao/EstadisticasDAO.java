/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cubiktimer.controlador.factories.ConnectionFactory;
import com.cubiktimer.modelo.dto.UsuarioDTO;
import com.cubiktimer.modelo.rubik.estadisticas.ComparacionPB;
import com.cubiktimer.modelo.rubik.estadisticas.CuentaPuzzle;
import com.cubiktimer.modelo.rubik.estadisticas.ListaComparacionPB;
import com.cubiktimer.modelo.rubik.estadisticas.ListaPromedioCategoria;
import com.cubiktimer.modelo.rubik.estadisticas.Promedio;
import com.cubiktimer.modelo.rubik.estadisticas.RecordPBSingle;
import com.cubiktimer.util.Util;

public class EstadisticasDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(EstadisticasDAO.class);

	/**
	 * Método que retorna las categorías que se han registrado y la cantidad de
	 * tiempos que se han registrado en cada categoría, se usa para generar la
	 * gráfica de pastel en el front
	 * 
	 * @param idUsuario
	 * @return
	 */
	public List<CuentaPuzzle> obtenerListaConteoPuzzles(Integer idUsuario) {
		log.trace("inicio obtenerListaConteoPuzzles");
		List<CuentaPuzzle> lista = new ArrayList<>();
		String sql = "SELECT id_usuario, nombre_tipo nombre_puzzle, count(*) conteo_puzzle FROM tiempos"
				+ " WHERE id_usuario=? GROUP BY nombre_tipo";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					CuentaPuzzle cuentaPuzzle = new CuentaPuzzle();
					cuentaPuzzle.setIdUsuario(rs.getInt("id_usuario"));
					cuentaPuzzle.setNombrePuzzle(rs.getString("nombre_puzzle"));
					cuentaPuzzle.setConteoPuzzle(rs.getInt("conteo_puzzle"));
					lista.add(cuentaPuzzle);
				}
			} finally {
				rs.close();
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		log.trace("fin obtenerListaConteoPuzzles");
		return lista;
	}

	/**
	 * Método que retorna los PB por categoría
	 * 
	 * @param idUsuario
	 * @return
	 */
	public List<RecordPBSingle> obtenerListaPBSingle(Integer idUsuario) {
		log.trace("inicio obtenerListaPBSingle");
		List<RecordPBSingle> lista = new ArrayList<>();
		String sql = "SELECT id_usuario, id_tipo_cubo, nombre_tipo, min(tiempo_con_penalizacion) pb_single FROM tiempos t"
				+ " WHERE id_usuario=? AND dnf=0 GROUP BY id_usuario, id_tipo_cubo";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					RecordPBSingle pbSingle = new RecordPBSingle();
					pbSingle.setIdUsuario(rs.getInt("id_usuario"));
					pbSingle.setIdTipoCubo(rs.getInt("id_tipo_cubo"));
					pbSingle.setNombrePuzzle(rs.getString("nombre_tipo"));
					pbSingle.setPbTexto(Util.darFormatoTiempo(rs.getInt("pb_single")));
					pbSingle.setPbNumero(rs.getInt("pb_single"));
					lista.add(pbSingle);
				}
			} finally {
				rs.close();
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		log.trace("fin obtenerListaPBSingle");
		return lista;
	}

	/**
	 * Método que retorna la lista de promedios de un tipo de cubo agrupando por
	 * fecha, se usa para hacer una gráfica de línea
	 * 
	 * @param idUsuario
	 * @param idTipoCubo
	 * @return
	 */
	public List<Promedio> obtenerListaPromediosCategoria(Integer idUsuario, Integer idTipoCubo) {
		log.trace("inicio obtenerListaPromediosCategoria");
		List<Promedio> lista = new ArrayList<>();
		String sql = "SELECT FLOOR(avg(tr.tiempo_con_penalizacion)) promedio, tr.nombre_tipo tipo_cubo,"
				+ " DATE_FORMAT(sr.fecha,\"%d/%m/%Y\") fecha FROM tiempos tr INNER JOIN sesiones_rubik sr"
				+ " ON tr.id_sesion=sr.id_sesion"
				+ " WHERE sr.id_usuario=? AND tr.id_tipo_cubo=? AND tr.estado=1 AND sr.estado=1"
				+ " AND tr.dnf=0 GROUP BY tr.nombre_tipo,DATE_FORMAT(sr.fecha,\"%d/%m/%Y\") ORDER BY sr.fecha";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			ps.setInt(2, idTipoCubo);
			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					Promedio promedio = new Promedio();
					promedio.setProm(rs.getInt("promedio"));
					promedio.setTipoCubo(rs.getString("tipo_cubo"));
					promedio.setFecha(rs.getString("fecha"));
					lista.add(promedio);
				}
			} finally {
				rs.close();
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		log.trace("fin obtenerListaPromediosCategoria");
		return lista;
	}

	/**
	 * Método que retorna la lista de PB de un tipo de cubo agrupando por fecha, se
	 * usa para hacer una gráfica de línea
	 * 
	 * @param idUsuario
	 * @param idTipoCubo
	 * @return
	 */
	public List<Promedio> obtenerListaPBCategoria(Integer idUsuario, Integer idTipoCubo) {
		log.trace("inicio obtenerListaPromediosCategoria");
		List<Promedio> lista = new ArrayList<>();
		String sql = "SELECT tiempo_con_penalizacion pb, nombre_tipo tipo_cubo, DATE_FORMAT(fecha,\"%d/%m/%Y\") fecha"
				+ " FROM tiempos WHERE id_usuario=? AND id_tipo_cubo=? AND pb=1 AND estado=1 AND estado=1"
				+ " AND dnf=0 ORDER BY id_tiempo";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			ps.setInt(2, idTipoCubo);
			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					Promedio promedio = new Promedio();
					promedio.setProm(rs.getInt("pb"));
					promedio.setTipoCubo(rs.getString("tipo_cubo"));
					promedio.setFecha(rs.getString("fecha"));
					lista.add(promedio);
				}
			} finally {
				rs.close();
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		log.trace("fin obtenerListaPromediosCategoria");
		return lista;
	}

	/**
	 * Método que retorna la lista de averages (Ao5) de un tipo de cubo, se usa para
	 * hacer una gráfica de línea
	 * 
	 * @param idUsuario
	 * @param idTipoCubo
	 * @return
	 */
	public List<Promedio> obtenerListaAveragesCategoria(Integer idUsuario, Integer idTipoCubo) {
		log.trace("inicio obtenerListaAveragesCategoria");
		List<Promedio> lista = new ArrayList<>();
		String sql = "SELECT a.ao5, tr.nombre_tipo tipo_cubo, DATE_FORMAT(sr.fecha, \"%d/%m/%Y\") fecha"
				+ " FROM averages a LEFT JOIN tiempos tr ON a.id_average = tr.id_average"
				+ " INNER JOIN sesiones_rubik sr ON tr.id_sesion = sr.id_sesion"
				+ " WHERE sr.id_usuario = ? AND tr.id_tipo_cubo = ? AND tr.estado = 1 AND sr.estado = 1"
				+ " AND tr.id_average IS NOT NULL AND tr.dnf = 0 AND a.ao5 != 'DNF'"
				+ " GROUP BY tr.nombre_tipo, a.id_average ORDER BY sr.fecha, a.id_average";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			ps.setInt(2, idTipoCubo);
			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					Promedio promedio = new Promedio();
					promedio.setProm(rs.getInt("ao5"));
					promedio.setTipoCubo(rs.getString("tipo_cubo"));
					promedio.setFecha(rs.getString("fecha"));
					lista.add(promedio);
				}
			} finally {
				rs.close();
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		log.trace("fin obtenerListaAveragesCategoria");
		return lista;
	}

	/**
	 * Método que retorna la lista de promedios de un tipo de cubo agrupando por
	 * fecha, se usa para hacer una gráfica de línea
	 * 
	 * @param idUsuario
	 * @param idTipoCubo
	 * @return
	 */
	public List<Promedio> obtenerListaPromediosCategoriaComparacion(Integer idUsuario, Integer idTipoCubo) {
		log.trace("inicio obtenerListaPromediosCategoriaComparacion");
		List<Promedio> lista = new ArrayList<>();
		String sql = "SELECT FLOOR(avg(tr.tiempo_con_penalizacion)) promedio,"
				+ " CONCAT(u.nombres, ' - ', tr.nombre_tipo) tipo_cubo, DATE_FORMAT(sr.fecha, \"%d/%m/%Y\") fecha"
				+ " FROM tiempos tr INNER JOIN sesiones_rubik sr ON tr.id_sesion = sr.id_sesion"
				+ " INNER JOIN usuarios u ON tr.id_usuario = u.id_usuario"
				+ " WHERE tr.id_usuario = ? AND tr.id_tipo_cubo = ? AND tr.estado = 1 AND tr.dnf = 0"
				+ " GROUP BY tr.nombre_tipo, DATE_FORMAT(sr.fecha, \"%d/%m/%Y\") ORDER BY sr.fecha";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			ps.setInt(2, idTipoCubo);
			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					Promedio promedio = new Promedio();
					promedio.setProm(rs.getInt("promedio"));
					promedio.setTipoCubo(rs.getString("tipo_cubo"));
					promedio.setFecha(rs.getString("fecha"));
					lista.add(promedio);
				}
			} finally {
				rs.close();
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		log.trace("fin obtenerListaPromediosCategoriaComparacion");
		return lista;
	}

	/**
	 * Método que retorna la lista de promedios de un tipo de cubo agrupando por
	 * fecha, se usa para hacer una gráfica de línea
	 * 
	 * @param idUsuario
	 * @param idTipoCubo
	 * @return
	 */
	public List<Promedio> obtenerListaPBCategoriaComparacion(Integer idUsuario, Integer idTipoCubo) {
		log.trace("inicio obtenerListaPBCategoriaComparacion");
		List<Promedio> lista = new ArrayList<>();
		String sql = "SELECT tr.tiempo_con_penalizacion pb, CONCAT(u.nombres, ' - ', tr.nombre_tipo) tipo_cubo,"
				+ " DATE_FORMAT(sr.fecha, \"%d/%m/%Y\") fecha"
				+ " FROM tiempos tr INNER JOIN sesiones_rubik sr ON tr.id_sesion = sr.id_sesion"
				+ " INNER JOIN usuarios u ON tr.id_usuario = u.id_usuario"
				+ " WHERE tr.id_usuario = ? AND tr.id_tipo_cubo = ? AND pb=1 AND tr.estado = 1 AND tr.dnf = 0"
				+ " ORDER BY id_tiempo";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			ps.setInt(2, idTipoCubo);
			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					Promedio promedio = new Promedio();
					promedio.setProm(rs.getInt("pb"));
					promedio.setTipoCubo(rs.getString("tipo_cubo"));
					promedio.setFecha(rs.getString("fecha"));
					lista.add(promedio);
				}
			} finally {
				rs.close();
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		log.trace("fin obtenerListaPBCategoriaComparacion");
		return lista;
	}

	/**
	 * Método para obtener los datos para dibujar el gráfico de comparación Spider
	 * 
	 * @param idUsuario
	 * @param idAmigo
	 * @return
	 */
	public ListaComparacionPB obtenerListaComparacionPBSingle(Integer idUsuario, Integer idAmigo) {
		log.trace("inicio obtenerListaComparacionPBSingle");
		ListaComparacionPB listaComparacionPB = new ListaComparacionPB();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<UsuarioDTO> listaUsuarioAux;
		listaUsuarioAux = usuarioDAO.consultarUsuarioPorIdUsuario(idUsuario);
		if (listaUsuarioAux.size() == 1) {
			String nombreUsuario = listaUsuarioAux.get(0).getNombres();
			listaComparacionPB.setNombreUsuario(nombreUsuario);
		}
		listaUsuarioAux = usuarioDAO.consultarUsuarioPorIdUsuario(idAmigo);
		if (listaUsuarioAux.size() == 1) {
			String nombreAmigo = listaUsuarioAux.get(0).getNombres();
			listaComparacionPB.setNombreAmigo(nombreAmigo);
		}
		List<ComparacionPB> lista = new ArrayList<ComparacionPB>();
		String sql = "SELECT records.nombre_tipo, IFNULL(yo.pb_single,0) pb_usuario, IFNULL(amigo.pb_single,0) pb_amigo, minimo, maximo"
				+ " FROM (SELECT id_tipo_cubo, nombre_tipo, MIN(tiempo_con_penalizacion) minimo, MAX(tiempo_con_penalizacion) maximo"
				+ " FROM tiempos  WHERE dnf = 0 GROUP BY id_tipo_cubo, nombre_tipo) records"
				+ " LEFT JOIN (SELECT id_usuario, t.nombre_tipo, min(tiempo_con_penalizacion) pb_single"
				+ " FROM tiempos t WHERE id_usuario = ? AND dnf = 0 GROUP BY id_usuario, t.nombre_tipo) yo"
				+ " ON records.nombre_tipo = yo.nombre_tipo"
				+ " LEFT JOIN (SELECT id_usuario, t.nombre_tipo, min(tiempo_con_penalizacion) pb_single"
				+ " FROM tiempos t WHERE id_usuario = ? AND dnf = 0 GROUP BY id_usuario, t.nombre_tipo) amigo"
				+ " ON records.nombre_tipo = amigo.nombre_tipo";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			ps.setInt(2, idAmigo);
			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					ComparacionPB comparacionPB = new ComparacionPB();
					comparacionPB.setNombrePuzzle(rs.getString("nombre_tipo"));
					comparacionPB.setMejorTiempo(rs.getInt("minimo"));
					comparacionPB.setPeorTiempo(rs.getInt("maximo"));
					comparacionPB.getRecordPropio().setIdUsuario(idUsuario);
					comparacionPB.getRecordPropio().setNombrePuzzle(rs.getString("nombre_tipo"));
					comparacionPB.getRecordPropio().setPbTexto(Util.darFormatoTiempo(rs.getInt("pb_usuario")));
					comparacionPB.getRecordPropio().setPbNumero(rs.getInt("pb_usuario"));
					comparacionPB.getRecordAmigo().setIdUsuario(idAmigo);
					comparacionPB.getRecordAmigo().setNombrePuzzle(rs.getString("nombre_tipo"));
					comparacionPB.getRecordAmigo().setPbTexto(Util.darFormatoTiempo(rs.getInt("pb_amigo")));
					comparacionPB.getRecordAmigo().setPbNumero(rs.getInt("pb_amigo"));
					lista.add(comparacionPB);
				}
				listaComparacionPB.setListaComparacionPB(lista);
			} finally {
				rs.close();
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		log.trace("fin obtenerListaComparacionPBSingle");
		return listaComparacionPB;

	}

	/**
	 * Metodo que retorna una lista con los id de las categorias que el usuario ha
	 * registrado
	 * 
	 * @param idUsuario
	 * @return
	 */
	public List<Integer> obtenerIdCategoriasRegistradas(Integer idUsuario) {
		log.trace("inicio obtenerIdCategoriasRegistradas");
		List<Integer> lista = new ArrayList<>();
		String sql = "SELECT DISTINCT(id_tipo_cubo) FROM tiempos WHERE id_usuario=? AND estado=1";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					lista.add(rs.getInt("id_tipo_cubo"));
				}
			} finally {
				rs.close();
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		log.trace("fin obtenerIdCategoriasRegistradas");
		return lista;
	}

	/**
	 * Metodo que retorna una lista con los id de las categorias en las que el
	 * usuario ha registrado ao5
	 * 
	 * @param idUsuario
	 * @return
	 */
	public List<Integer> obtenerIdCategoriasRegistradasAo5(Integer idUsuario) {
		log.trace("inicio obtenerIdCategoriasRegistradasAo5");
		List<Integer> lista = new ArrayList<>();
		String sql = "SELECT DISTINCT(id_tipo_cubo) FROM tiempos WHERE id_usuario=? AND estado=1 AND id_average IS NOT NULL";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					lista.add(rs.getInt("id_tipo_cubo"));
				}
			} finally {
				rs.close();
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		log.trace("fin obtenerIdCategoriasRegistradasAo5");
		return lista;
	}

	public List<ListaPromedioCategoria> obtenerListaPromediosTotales(Integer idUsuario) {
		log.trace("inicio obtenerListaPromediosTotales");
		List<ListaPromedioCategoria> listaPromediosTotales = new ArrayList<>();
		List<Integer> listaCategorias = obtenerIdCategoriasRegistradas(idUsuario);
		for (Integer idTipoCubo : listaCategorias) {
			List<Promedio> listaPromedio = obtenerListaPromediosCategoria(idUsuario, idTipoCubo);
			ListaPromedioCategoria listaPromedioCategoria = new ListaPromedioCategoria();
			listaPromedioCategoria.setLista(listaPromedio);
			listaPromediosTotales.add(listaPromedioCategoria);
		}
		log.trace("fin obtenerListaPromediosTotales");
		return listaPromediosTotales;
	}

	public List<ListaPromedioCategoria> obtenerListaPBTotales(Integer idUsuario) {
		log.trace("inicio obtenerListaPBTotales");
		List<ListaPromedioCategoria> listaPromediosTotales = new ArrayList<>();
		List<Integer> listaCategorias = obtenerIdCategoriasRegistradas(idUsuario);
		for (Integer idTipoCubo : listaCategorias) {
			List<Promedio> listaPromedio = obtenerListaPBCategoria(idUsuario, idTipoCubo);
			ListaPromedioCategoria listaPromedioCategoria = new ListaPromedioCategoria();
			listaPromedioCategoria.setLista(listaPromedio);
			listaPromediosTotales.add(listaPromedioCategoria);
		}
		log.trace("fin obtenerListaPBTotales");
		return listaPromediosTotales;
	}

	public List<ListaPromedioCategoria> obtenerListaAverages(Integer idUsuario) {
		log.trace("inicio obtenerListaAverages");
		List<ListaPromedioCategoria> listaAverages = new ArrayList<>();
		List<Integer> listaCategorias = obtenerIdCategoriasRegistradasAo5(idUsuario);
		for (Integer idTipoCubo : listaCategorias) {
			List<Promedio> listaAverage = obtenerListaAveragesCategoria(idUsuario, idTipoCubo);
			ListaPromedioCategoria listaAveragesCategoria = new ListaPromedioCategoria();
			listaAveragesCategoria.setLista(listaAverage);
			listaAverages.add(listaAveragesCategoria);
		}
		log.trace("fin obtenerListaAverages");
		return listaAverages;
	}

	public List<ListaPromedioCategoria> obtenerListaPromediosComparacion(Integer idUsuario, Integer idAmigo) {
		log.trace("inicio obtenerListaPromediosComparacion");
		List<ListaPromedioCategoria> listaPromediosTotales = new ArrayList<>();
		List<Integer> listaCategorias = obtenerIdCategoriasRegistradas(idUsuario);
		List<Integer> listaCategoriasAmigo = obtenerIdCategoriasRegistradas(idAmigo);
		for (Integer idTipoCubo : listaCategorias) {
			List<Promedio> listaPromedio = obtenerListaPromediosCategoriaComparacion(idUsuario, idTipoCubo);
			ListaPromedioCategoria listaPromedioCategoria = new ListaPromedioCategoria();
			listaPromedioCategoria.setLista(listaPromedio);
			listaPromediosTotales.add(listaPromedioCategoria);
		}
		for (Integer idTipoCubo : listaCategoriasAmigo) {
			List<Promedio> listaPromedio = obtenerListaPromediosCategoriaComparacion(idAmigo, idTipoCubo);
			ListaPromedioCategoria listaPromedioCategoria = new ListaPromedioCategoria();
			listaPromedioCategoria.setLista(listaPromedio);
			listaPromediosTotales.add(listaPromedioCategoria);
		}
		log.trace("fin obtenerListaPromediosComparacion");
		return listaPromediosTotales;
	}

	public List<ListaPromedioCategoria> obtenerListaPBComparacion(Integer idUsuario, Integer idAmigo) {
		log.trace("inicio obtenerListaPBComparacion");
		List<ListaPromedioCategoria> listaPromediosTotales = new ArrayList<>();
		List<Integer> listaCategorias = obtenerIdCategoriasRegistradas(idUsuario);
		List<Integer> listaCategoriasAmigo = obtenerIdCategoriasRegistradas(idAmigo);
		for (Integer idTipoCubo : listaCategorias) {
			List<Promedio> listaPromedio = obtenerListaPBCategoriaComparacion(idUsuario, idTipoCubo);
			ListaPromedioCategoria listaPromedioCategoria = new ListaPromedioCategoria();
			listaPromedioCategoria.setLista(listaPromedio);
			listaPromediosTotales.add(listaPromedioCategoria);
		}
		for (Integer idTipoCubo : listaCategoriasAmigo) {
			List<Promedio> listaPromedio = obtenerListaPBCategoriaComparacion(idAmigo, idTipoCubo);
			ListaPromedioCategoria listaPromedioCategoria = new ListaPromedioCategoria();
			listaPromedioCategoria.setLista(listaPromedio);
			listaPromediosTotales.add(listaPromedioCategoria);
		}
		log.trace("fin obtenerListaPBComparacion");
		return listaPromediosTotales;
	}

	public Integer consultarIdPuzzleMasPracticado(Integer idUsuario) {
		log.trace("inicio consultarIdPuzzleMasPracticado");
		Integer idTipoCubo = null;
		String sql = "SELECT id_tipo_cubo,COUNT(*) cantidad FROM tiempos WHERE id_usuario=? AND estado=1 GROUP BY (id_tipo_cubo)"
				+ " ORDER BY COUNT(*)";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					idTipoCubo = rs.getInt("id_tipo_cubo");
				}
			} finally {
				rs.close();
			}
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		}
		log.trace("fin consultarIdPuzzleMasPracticado");
		return idTipoCubo;
	}

	public static void main(String[] args) {
		EstadisticasDAO estadisticasDAO = new EstadisticasDAO();
		System.out.println(estadisticasDAO.obtenerListaPBTotales(2));
	}

}
