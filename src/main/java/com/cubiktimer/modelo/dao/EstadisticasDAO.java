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
import com.cubiktimer.modelo.rubik.estadisticas.CuentaPuzzle;
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
		String sql = "SELECT sr.id_usuario,t.nombre_tipo nombre_puzzle, count(*) conteo_puzzle" + " FROM tiempos tr"
				+ " INNER JOIN tipos t" + " ON tr.id_tipo_cubo=t.id_tipo" + " INNER JOIN sesiones_rubik sr"
				+ " ON tr.id_sesion=sr.id_sesion" + " WHERE id_usuario=?" + " GROUP BY t.nombre_tipo";
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
		String sql = "SELECT id_usuario, t2.nombre_tipo, min(tiempo_con_penalizacion) pb_single"
				+ " FROM tiempos t INNER JOIN sesiones_rubik s ON t.id_sesion = s.id_sesion"
				+ " INNER JOIN tipos t2 ON t.id_tipo_cubo = t2.id_tipo"
				+ " WHERE id_usuario=? AND dnf=0 GROUP BY id_usuario, t2.nombre_tipo";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					RecordPBSingle pbSingle = new RecordPBSingle();
					pbSingle.setIdUsuario(rs.getInt("id_usuario"));
					pbSingle.setNombrePuzzle(rs.getString("nombre_tipo"));
					pbSingle.setPbSingle(Util.darFormatoTiempo(rs.getInt("pb_single")));
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
		String sql = "SELECT FLOOR(avg(tr.tiempo_con_penalizacion)) promedio, t.nombre_tipo tipo_cubo,"
				+ " DATE_FORMAT(sr.fecha,\"%d/%m/%Y\") fecha" + " FROM tiempos tr" + " INNER JOIN sesiones_rubik sr"
				+ " ON tr.id_sesion=sr.id_sesion" + " INNER JOIN tipos t" + " ON tr.id_tipo_cubo=t.id_tipo"
				+ " WHERE sr.id_usuario=?" + " AND tr.id_tipo_cubo=?" + " AND tr.estado=1" + " AND sr.estado=1"
				+ " AND tr.dnf=0" + " GROUP BY t.nombre_tipo,DATE_FORMAT(sr.fecha,\"%d/%m/%Y\")" + " ORDER BY sr.fecha";
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
		String sql = "SELECT FLOOR(avg(tr.tiempo_con_penalizacion)) promedio, "
				+ " CONCAT(u.nombres,' - ', t.nombre_tipo) tipo_cubo," + " DATE_FORMAT(sr.fecha,\"%d/%m/%Y\") fecha"
				+ " FROM tiempos tr" + " INNER JOIN sesiones_rubik sr" + " ON tr.id_sesion=sr.id_sesion"
				+ " INNER JOIN tipos t" + " ON tr.id_tipo_cubo=t.id_tipo" + " INNER JOIN usuarios u"
				+ " ON sr.id_usuario = u.id_usuario" + " WHERE sr.id_usuario=?" + " AND tr.id_tipo_cubo=?"
				+ " AND tr.estado=1" + " AND sr.estado=1" + " AND tr.dnf=0"
				+ " GROUP BY t.nombre_tipo,DATE_FORMAT(sr.fecha,\"%d/%m/%Y\")" + " ORDER BY sr.fecha";
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
	 * Metodo que retorna una lista con los id de las categorias que el usuario ha
	 * registrado
	 * 
	 * @param idUsuario
	 * @return
	 */
	public List<Integer> obtenerIdCategoriasRegistradas(Integer idUsuario) {
		log.trace("inicio obtenerIdCategoriasRegistradas");
		List<Integer> lista = new ArrayList<>();
		String sql = "SELECT DISTINCT(tr.id_tipo_cubo)" + " FROM tiempos tr" + " INNER JOIN sesiones_rubik sr"
				+ " ON tr.id_sesion=sr.id_sesion" + " WHERE sr.id_usuario=?" + " AND tr.estado=1" + " AND sr.estado=1";
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

	public Integer consultarIdPuzzleMasPracticado(Integer idUsuario) {
		log.trace("inicio consultarIdPuzzleMasPracticado");
		Integer idTipoCubo = null;
		String sql = "SELECT tr.id_tipo_cubo,COUNT(*) cantidad" + " FROM tiempos tr" + " INNER JOIN sesiones_rubik sr"
				+ " ON tr.id_sesion = sr.id_sesion" + " WHERE sr.id_usuario=?" + " AND tr.estado=1" + " AND sr.estado=1"
				+ " GROUP BY (tr.id_tipo_cubo)" + " ORDER BY COUNT(*)";
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

}
