package com.cubiktimer.modelo.dao;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cubiktimer.modelo.rubik.estadisticas.CuentaPuzzle;
import com.cubiktimer.modelo.rubik.estadisticas.ListaPromedioCategoria;
import com.cubiktimer.modelo.rubik.estadisticas.Promedio;

public class EstadisticasDAO extends DAO<CuentaPuzzle, Integer> implements Serializable {

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
		conectar();
		List<CuentaPuzzle> lista = new ArrayList<>();
		String sql = "SELECT sr.id_usuario,t.nombre_tipo nombre_puzzle, count(*) conteo_puzzle"
				+ " FROM tiempos_rubik tr" + " INNER JOIN tipos t" + " ON tr.id_tipo_cubo=t.id_tipo"
				+ " INNER JOIN sesiones_rubik sr" + " ON tr.id_sesion=sr.id_sesion" + " WHERE id_usuario=?"
				+ " GROUP BY t.nombre_tipo";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
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
			desconectar();
		} catch (Exception e) {
			log.warn(e.getMessage());
			desconectar();
		}
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
		conectar();
		List<Promedio> lista = new ArrayList<>();
		String sql = "SELECT FLOOR(avg(tr.tiempo_con_penalizacion)) promedio, t.nombre_tipo tipo_cubo,"
				+ " DATE_FORMAT(sr.fecha,\"%d/%m/%Y\") fecha" + " FROM tiempos tr" + " INNER JOIN sesiones_rubik sr"
				+ " ON tr.id_sesion=sr.id_sesion" + " INNER JOIN tipos t" + " ON tr.id_tipo_cubo=t.id_tipo"
				+ " WHERE sr.id_usuario=?" + " AND tr.id_tipo_cubo=?" + " AND tr.estado=1" + " AND sr.estado=1"
				+ " AND tr.dnf=0" + " GROUP BY t.nombre_tipo,DATE_FORMAT(sr.fecha,\"%d/%m/%Y\")" + " ORDER BY sr.fecha";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
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
			desconectar();
		} catch (Exception e) {
			log.warn(e.getMessage());
			desconectar();
		}
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
		conectar();
		List<Promedio> lista = new ArrayList<>();
		String sql = "SELECT FLOOR(avg(tr.tiempo_con_penalizacion)) promedio, "
				+ " CONCAT(u.nombres,' - ', t.nombre_tipo) tipo_cubo," + " DATE_FORMAT(sr.fecha,\"%d/%m/%Y\") fecha"
				+ " FROM tiempos tr" + " INNER JOIN sesiones_rubik sr" + " ON tr.id_sesion=sr.id_sesion"
				+ " INNER JOIN tipos t" + " ON tr.id_tipo_cubo=t.id_tipo" + " INNER JOIN usuarios u"
				+ " ON sr.id_usuario = u.id_usuario" + " WHERE sr.id_usuario=?" + " AND tr.id_tipo_cubo=?"
				+ " AND tr.estado=1" + " AND sr.estado=1" + " AND tr.dnf=0"
				+ " GROUP BY t.nombre_tipo,DATE_FORMAT(sr.fecha,\"%d/%m/%Y\")" + " ORDER BY sr.fecha";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
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
			desconectar();
		} catch (Exception e) {
			log.warn(e.getMessage());
			desconectar();
		}
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
		conectar();
		List<Integer> lista = new ArrayList<>();
		String sql = "SELECT DISTINCT(tr.id_tipo_cubo)" + " FROM tiempos_rubik tr" + " INNER JOIN sesiones_rubik sr"
				+ " ON tr.id_sesion=sr.id_sesion" + " WHERE sr.id_usuario=?" + " AND tr.estado=1" + " AND sr.estado=1";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					lista.add(rs.getInt("id_tipo_cubo"));
				}
			} finally {
				rs.close();
			}
			desconectar();
		} catch (Exception e) {
			log.warn(e.getMessage());
			desconectar();
		}
		return lista;
	}

	public List<ListaPromedioCategoria> obtenerListaPromediosTotales(Integer idUsuario) {
		List<ListaPromedioCategoria> listaPromediosTotales = new ArrayList<>();
		List<Integer> listaCategorias = obtenerIdCategoriasRegistradas(idUsuario);
		for (Integer idTipoCubo : listaCategorias) {
			List<Promedio> listaPromedio = obtenerListaPromediosCategoria(idUsuario, idTipoCubo);
			ListaPromedioCategoria listaPromedioCategoria = new ListaPromedioCategoria();
			listaPromedioCategoria.setLista(listaPromedio);
			listaPromediosTotales.add(listaPromedioCategoria);
		}
		return listaPromediosTotales;
	}

	public List<ListaPromedioCategoria> obtenerListaPromediosComparacion(Integer idUsuario, Integer idAmigo) {
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
		return listaPromediosTotales;
	}

	public Integer consultarIdPuzzleMasPracticado(Integer idUsuario) {
		Integer idTipoCubo = null;
		conectar();
		String sql = "SELECT tr.id_tipo_cubo,COUNT(*) cantidad" + " FROM tiempos_rubik tr"
				+ " INNER JOIN sesiones_rubik sr" + " ON tr.id_sesion = sr.id_sesion" + " WHERE sr.id_usuario=?"
				+ " AND tr.estado=1" + " AND sr.estado=1" + " GROUP BY (tr.id_tipo_cubo)" + " ORDER BY COUNT(*)";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					idTipoCubo = rs.getInt("id_tipo_cubo");
				}
			} finally {
				rs.close();
			}
			desconectar();
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
			desconectar();
		}
		return idTipoCubo;
	}

	@Override
	public int create(Integer dto) {
		return 0;
	}

}