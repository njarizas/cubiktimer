/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.controlador.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;

import com.cubiktimer.controlador.facade.RubikFacade;
import com.cubiktimer.controlador.factories.RubikFactory;
import com.cubiktimer.controlador.managedbeans.session.ConfiguracionManagedBean;
import com.cubiktimer.controlador.managedbeans.session.SesionManagedBean;
import com.cubiktimer.modelo.dao.TipoDAO;
import com.cubiktimer.modelo.dto.AverageDTO;
import com.cubiktimer.modelo.dto.FewestMovesDTO;
import com.cubiktimer.modelo.dto.TiempoRubikDTO;
import com.cubiktimer.modelo.dto.TipoDTO;
import com.cubiktimer.modelo.rubik.Comprobable;
import com.cubiktimer.modelo.rubik.FewestMovesSolvable;
import com.cubiktimer.modelo.rubik.Puzzle;
import com.cubiktimer.modelo.rubik.SesionRubik;
import com.cubiktimer.modelo.rubik.SolucionFewestMoves;
import com.cubiktimer.modelo.rubik.Tiempo;
import com.cubiktimer.modelo.rubik.estadisticas.ListaRecords;
import com.cubiktimer.util.Constantes;
import com.cubiktimer.util.ScrambleGenerator;
import com.cubiktimer.util.Util;

/**
 *
 * @author Nelson
 */
@ManagedBean
@SessionScoped
public class RubikManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(RubikManagedBean.class);

	@ManagedProperty(value = "#{configuracionManagedBean}")
	private ConfiguracionManagedBean configuracionManagedBean;

	@ManagedProperty(value = "#{sesionManagedBean}")
	private SesionManagedBean sesionManagedBean;

	private RubikFacade rubikFacade;

	private TipoDAO tipoDAO;

	private List<SesionRubik> listaSesiones;
	private SesionRubik sesionRubikActual;

	// Para cuando se compite por tiempo
	private int tipoCubo;
	private Puzzle cubo;

	private String[] mezcla;
	private String secuenciaMezcla;

	private Integer tiempoInspeccionSegundos;
	private Integer tiempoInspeccionUsadoMilisegundos;
	private String tiempoInspeccionUsadoTexto;
	private Integer tiempoMilisegundos;
	private String duracion;// tiempoTexto
	private Boolean dnf;
	private Boolean penalizacion;
	private String comentario;

	private boolean validoParaAO5;

	// Para cuando se compite por movimientos
	private String tiempoRestanteTexto;
	private String solucion;

	private ListaRecords listaRecords;

	private Integer pb;

	public RubikManagedBean() {
		this.tipoDAO = new TipoDAO();
		this.rubikFacade = new RubikFacade();
		this.secuenciaMezcla = "";
		this.tiempoInspeccionSegundos = 0;
		this.tiempoInspeccionUsadoMilisegundos = 0;
		this.tiempoInspeccionUsadoTexto = "00:00.00";
		this.tiempoMilisegundos = 0;
		this.duracion = "00:00.00";// tiempoTexto
		this.dnf = false;
		this.penalizacion = false;
		this.comentario = "";

		this.solucion = "";
		this.tiempoRestanteTexto = "59:59";

		this.validoParaAO5 = true;// empieza en verdadero y se vuelve falso al borrar algun tiempo

		listaRecords = new ListaRecords();
	}

	@PostConstruct
	public void init() {
		if (sesionManagedBean.getUsuarioLogueado() == null) {
			this.sesionRubikActual = new SesionRubik(new Date());
		} else {
			this.sesionRubikActual = new SesionRubik(new Date(), sesionManagedBean.getUsuarioLogueado().getIdUsuario());
		}
		this.sesionRubikActual.getSesionRubikDTO().setIp(sesionManagedBean.getIpConsultante());
		this.tipoCubo = configuracionManagedBean.getTipoCubo().getValorEntero();
		this.cubo = RubikFactory.crearCubo(this.tipoCubo);
		mezclaAleatoria();
	}

	public void cambioCubo(ValueChangeEvent event) {
		this.tipoCubo = Integer.parseInt(event.getNewValue().toString());
		this.configuracionManagedBean.getTipoCubo().setValorEntero(this.tipoCubo);
		this.cubo = RubikFactory.crearCubo(this.tipoCubo);
		mezclaAleatoria();
		if (!this.sesionRubikActual.getTiempos().isEmpty()) {
			this.validoParaAO5 = false;
		}
		try {
			this.pb = listaRecords.getListaPBSingle().stream().filter(val -> val.getIdTipoCubo().equals(this.tipoCubo))
					.mapToInt(t -> t.getPbNumero()).min().getAsInt();
		} catch (NoSuchElementException e) {
			this.pb = Integer.MAX_VALUE;
		}
		System.out.println("PB para " + cubo.getNombre() + ": " + pb);
		System.out.println(listaRecords);
	}

	public List<TipoDTO> listarCubos() {
		return tipoDAO.listarTiposDeCubo();
	}

	public String[] generarMezcla() {
		return cubo.generarMezcla();
	}

	public String resetearCubo() {
		cubo = RubikFactory.crearCubo(this.tipoCubo);
		return "";
	}

	public String resetearTodo() {
		resetearCubo();
		this.secuenciaMezcla = "";
		this.mezcla = null;
		return "";
	}

	public String mezclaAleatoria() {
		resetearCubo();
		this.secuenciaMezcla = "";
		this.mezcla = generarMezcla();
		secuenciaMezcla = cubo.mezclar(mezcla);
		if (cubo instanceof FewestMovesSolvable && cubo.getIdTipoCubo() == 17) {
			this.solucion = "";
			log.info("secuencia mezcla aleatoria Fewest Moves (" + cubo.getNombre() + "): " + secuenciaMezcla);
			log.debug(((FewestMovesSolvable) cubo).faceletToString());
		} else {
			log.info("secuencia mezcla aleatoria (" + cubo.getNombre() + "): " + secuenciaMezcla);
		}
		log.trace(cubo);
		return "";
	}

	public String mezclaPersonalizada() {
		resetearCubo();
		if (cubo instanceof FewestMovesSolvable && cubo.getIdTipoCubo() == 17) {
			log.info("Secuencia ingresada Fewest moves: " + secuenciaMezcla);
			secuenciaMezcla = corregirMezclaFewest(secuenciaMezcla);
			log.info("Secuencia corregida Fewest moves: " + cubo.getNombre() + secuenciaMezcla);
			mezcla = secuenciaMezcla.split(" ");
			secuenciaMezcla = cubo.mezclar(mezcla);
			log.debug(((FewestMovesSolvable) cubo).faceletToString());
		} else {
			log.info("Secuencia ingresada (" + cubo.getNombre() + "): " + secuenciaMezcla);
			secuenciaMezcla = corregirMezcla(secuenciaMezcla);
			log.info("Secuencia corregida (" + cubo.getNombre() + "): " + secuenciaMezcla);
			mezcla = secuenciaMezcla.split(" ");
			secuenciaMezcla = cubo.mezclar(mezcla);
		}
		log.info("Secuencia aplicada (" + cubo.getNombre() + "):  " + secuenciaMezcla);
		log.trace(cubo);
		this.validoParaAO5 = false;
		return "";
	}

	/**
	 * Método que realiza la limpieza de las mezclas que se ingresan en el modo de
	 * mezcla personalizada para todos los cubos disponibles
	 * 
	 * @return
	 */
	private String corregirMezcla(String secuencia) {
		String retorno = secuencia.replaceAll(System.getProperty(Constantes.LINE_SEPARATOR), " ").replaceAll(" ", "");
		if (tipoCubo == 26) { // Pyramix
			retorno = retorno.replace("F", " F ").replace("B", " B ").replace("R", " R ").replace("L", " L ")
					.replace("U", " U ").replace("D", " D ");
		} else {// Diferente a Pyraminx
			retorno = retorno.toLowerCase();
		}
		retorno = retorno.replace("f", " f ").replace("b", " b ").replace("r", " r ").replace("l", " l ")
				.replace("u", " u ").replace("d", " d ").replace("x", " x ").replace("y", " y ").replace("z", " z ")
				.replace("3 b", " 3b ").replace("3 d", " 3d ").replace("3 f", " 3f ").replace("3 l", " 3l ")
				.replace("3 r", " 3r ").replace("3 u", " 3u ").replace(" '", "' ").replace(" 2", "2 ")
				.replaceAll("\\[ ", " [");
		retorno = retorno.replace("  ", " ");
		retorno = retorno.replace(" w", "w").replace(" ++", "++").replace(" --", "--");
		if (tipoCubo == 27) {// Square 1
			retorno = retorno.replace(" ", "").replace("/", " / ");
		}
		retorno = retorno.trim();
		log.info("Secuencia corregida (" + cubo.getNombre() + "): " + retorno);
		return retorno;
	}

	private String corregirMezclaFewest(String mezcla) {
		return mezcla.replaceAll(System.getProperty(Constantes.LINE_SEPARATOR), " ").toUpperCase().replaceAll(" ", "")
				.replace("F", " F").replace("B", " B").replace("R", " R").replace("L", " L").replace("U", " U")
				.replace("D", " D").replace("X", " X").replace("Y", " Y").replace("Z", " Z").replaceAll("\\[ ", " [")
				.replaceAll("\\[B", " [b").replaceAll("\\[R", " [r").replaceAll("\\[D", " [d").replaceAll("\\[F", " [f")
				.replaceAll("\\[L", " [l").replaceAll("\\[U", " [u").trim();
	}

	public String agregarTiempo() {
		try {
			this.pb = listaRecords.getListaPBSingle().stream()
					.filter(val -> val.getIdTipoCubo().equals(cubo.getIdTipoCubo())).mapToInt(t -> t.getPbNumero())
					.min().getAsInt();
		} catch (NoSuchElementException e) {
			this.pb = Integer.MAX_VALUE;
		}
		Integer pbSesion;
		try {
			pbSesion = sesionRubikActual.getTiempos().stream()
					.filter(val -> val.getTiempoRubikDTO().getIdTipoCubo().equals(cubo.getIdTipoCubo()))
					.mapToInt(t -> t.getTiempoRubikDTO().getTiempoRealMilisegundos()).min().getAsInt();
		} catch (NoSuchElementException e) {
			pbSesion = Integer.MAX_VALUE;
		}
		TiempoRubikDTO tiempoRubikDTO = new TiempoRubikDTO(cubo.getIdTipoCubo(), secuenciaMezcla,
				tiempoInspeccionSegundos, tiempoInspeccionUsadoMilisegundos, tiempoInspeccionUsadoTexto,
				tiempoMilisegundos, duracion, dnf, penalizacion, comentario);
		if (tiempoRubikDTO.getTiempoRealMilisegundos() < pb && tiempoRubikDTO.getTiempoRealMilisegundos() < pbSesion) {
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
			sesionManagedBean.getMensaje().setText("Nuevo PB en categoría " + cubo.getNombre());
			sesionManagedBean.getMensaje().setType(Constantes.SUCCESS);
			sesionManagedBean.getMensaje().setMensajePendiente(true);
			tiempoRubikDTO.setPb(true);
		}
		Tiempo t = new Tiempo(cubo, tiempoRubikDTO);
		sesionRubikActual.getTiempos().add(t);
		mezclaAleatoria();
		return "";
	}

	public String agregarSolucion() {
		if (cubo instanceof Comprobable) {
			solucion = solucion.replaceAll(System.getProperty(Constantes.LINE_SEPARATOR), " ");
			solucion = corregirMezclaFewest(solucion);
			log.info("Mezcla de solucion ingresada: " + solucion);
			String[] giros = solucion.trim().split(" ");
			cubo.mezclar(giros);
			FewestMovesDTO fewestMovesDTO = new FewestMovesDTO(cubo.getIdTipoCubo(), secuenciaMezcla,
					tiempoMilisegundos, tiempoRestanteTexto, solucion, dnf, comentario);
			fewestMovesDTO.setLongitudSolucion((int) contarGiros(giros));
			if (((Comprobable) cubo).estaResuelto() && !ScrambleGenerator.generarSecuenciaMezclaInversa(secuenciaMezcla)
					.trim().equals(solucion.trim())) {
				fewestMovesDTO.setSolucionValida(true);
				sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
				sesionManagedBean.getMensaje()
						.setText(sesionManagedBean.getRecursos().getString("MensajePuzzleSolucionado"));
				sesionManagedBean.getMensaje().setType(Constantes.SUCCESS);
				sesionManagedBean.getMensaje().setMensajePendiente(true);
			} else if (((Comprobable) cubo).estaResuelto() && ScrambleGenerator
					.generarSecuenciaMezclaInversa(secuenciaMezcla).trim().equals(solucion.trim())) {
				fewestMovesDTO.setSolucionValida(false);
				fewestMovesDTO.setDnf(true);
				sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
				sesionManagedBean.getMensaje()
						.setText(sesionManagedBean.getRecursos().getString("MensajeSolucionNoValida"));
				sesionManagedBean.getMensaje().setType(Constantes.ERROR);
				sesionManagedBean.getMensaje().setMensajePendiente(true);
			} else {
				fewestMovesDTO.setSolucionValida(false);
				fewestMovesDTO.setDnf(true);
				sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
				sesionManagedBean.getMensaje()
						.setText(sesionManagedBean.getRecursos().getString("MensajePuzzleNoSolucionado"));
				sesionManagedBean.getMensaje().setType(Constantes.ERROR);
				sesionManagedBean.getMensaje().setMensajePendiente(true);
			}
			SolucionFewestMoves s = new SolucionFewestMoves(cubo, fewestMovesDTO);
			sesionRubikActual.getSoluciones().add(s);
			mezclaAleatoria();
		} else {
			log.debug("El cubo " + cubo.getNombre() + " no se soluciona en Fewest Moves");
		}
		return "";
	}

	private long contarGiros(String[] giros) {
		return Arrays.asList(giros).stream().filter(x -> x.toUpperCase().matches("^[B|R|D|F|L|U]{1}[2|']*$")).count();
	}

	/**
	 * Método que se encarga de guardar los Average of 5 es básicamente guardar
	 * cinco tiempos con un identificador que los agrupa
	 * 
	 * @return
	 */
	public String guardarAO5() {
		return guardarSesionRubik(true);
	}

	public String guardarNormal() {
		return guardarSesionRubik(false);
	}

	/**
	 * Método que se encarga de guardar los tiempos empleados para solucionar el
	 * cubo de rubik en la sesión actual
	 * 
	 * @return
	 */
	public String guardarSesionRubik(boolean guardarAverages) {
		// Si tiene sesion iniciada se verifica que el id de usuario se encuentre
		// asignado correctamente
		if (sesionRubikActual.getSesionRubikDTO().getIdUsuario() == null
				&& sesionManagedBean.getUsuarioLogueado() != null
				&& sesionManagedBean.getUsuarioLogueado().getIdUsuario() != null) {
			sesionRubikActual.getSesionRubikDTO().setIdUsuario(sesionManagedBean.getUsuarioLogueado().getIdUsuario());
		}
		// si el usuario se encuentra logueado tiene sentido guardar los tiempos, de lo
		// contrario no
		if (sesionRubikActual.getSesionRubikDTO().getIdUsuario() != null) {
			if (guardarAverages) {
				AverageDTO averageDTO = new AverageDTO();
				averageDTO.setMejor(Util.calcularMilesimasDeSegundos(sesionRubikActual.mejor()));
				averageDTO.setMejorTexto(sesionRubikActual.mejor());
				averageDTO.setPeor(Util.calcularMilesimasDeSegundos(sesionRubikActual.peor()));
				averageDTO.setPeorTexto(sesionRubikActual.peor());
				averageDTO.setMedia(Util.calcularMilesimasDeSegundos(sesionRubikActual.media()));
				averageDTO.setMediaTexto(sesionRubikActual.media());
				averageDTO.setAo5(Util.calcularMilesimasDeSegundos(sesionRubikActual.ao5actual()));
				averageDTO.setAo5Texto(sesionRubikActual.ao5actual());
				if (rubikFacade.guardarAO5(averageDTO, sesionRubikActual.getTiempos()) > 0) {
					validoParaAO5 = false;
				}
			}
			if (rubikFacade.guardarRubik(sesionRubikActual.getSesionRubikDTO(), sesionRubikActual.getTiempos(),
					sesionRubikActual.getSoluciones()) > 0) {
				sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Informacion"));
				sesionManagedBean.getMensaje()
						.setText(sesionManagedBean.getRecursos().getString("SeHanGuardadoLosTiemposActuales"));
				sesionManagedBean.getMensaje().setType(Constantes.SUCCESS);
				sesionManagedBean.getMensaje().setMensajePendiente(true);
				log.info("El usuario: " + sesionRubikActual.getSesionRubikDTO().getIdUsuario() + " guardó "
						+ sesionRubikActual.getTiempos().size() + " tiempos con id_sesion: "
						+ sesionRubikActual.getSesionRubikDTO().getIdSesion());
				log.info("El usuario: " + sesionRubikActual.getSesionRubikDTO().getIdUsuario() + " guardó "
						+ sesionRubikActual.getSoluciones().size() + " soluciones de fewest moves con id_sesion: "
						+ sesionRubikActual.getSesionRubikDTO().getIdSesion());
			} else {
				sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
				sesionManagedBean.getMensaje()
						.setText(sesionManagedBean.getRecursos().getString("SePresentoUnErrorAlTratarDeGuardar"));
				sesionManagedBean.getMensaje().setType(Constantes.ERROR);
				sesionManagedBean.getMensaje().setMensajePendiente(true);
			}
		} else {
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
			sesionManagedBean.getMensaje().setText(sesionManagedBean.getRecursos()
					.getString("NoSePuedeGuardarTiemposDeUnUsuarioQueNoHaIniciadoSesion"));
			sesionManagedBean.getMensaje().setType(Constantes.WARNING);
			sesionManagedBean.getMensaje().setMensajePendiente(true);
		}
		return "";
	}

	public String limpiarTiempos() {
		sesionRubikActual.setTiempos(new ArrayList<Tiempo>());
		this.validoParaAO5 = true;
		return "";
	}

	public String limpiarSoluciones() {
		sesionRubikActual.setSoluciones(new ArrayList<SolucionFewestMoves>());
		return "";
	}

	public String limpiarTodo() {
		limpiarTiempos();
		limpiarSoluciones();
		return "";
	}

	public String eliminarTiempo(Tiempo t) {
		if (sesionRubikActual.getTiempos().contains(t)) {
			this.validoParaAO5 = false;
			sesionRubikActual.getTiempos().remove(t);
		}
		return "";
	}

	public String eliminarSolucion(SolucionFewestMoves s) {
		if (sesionRubikActual.getSoluciones().contains(s)) {
			sesionRubikActual.getSoluciones().remove(s);
		}
		return "";
	}

	public String mostrarDNF(Tiempo t) {
		if (sesionRubikActual.getTiempos().contains(t)) {// Si la sesion contiene el tiempo
			if (!sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO()
					.getDnf()) {
				sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO()
						.setTiempoTexto("DNF(" + Util.darFormatoTiempo(
								sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t))
										.getTiempoRubikDTO().getTiempoMilisegundos())
								+ ")");
				sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO()
						.setDnf(true);
			} else {
				if (sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO()
						.getPenalizacion()) {
					sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO()
							.setTiempoTexto(Util.darFormatoTiempo(
									sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t))
											.getTiempoRubikDTO().getTiempoMilisegundos())
									+ " +2");
				} else {
					sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO()
							.setTiempoTexto(Util.darFormatoTiempo(
									sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t))
											.getTiempoRubikDTO().getTiempoMilisegundos()));
				}
				sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO()
						.setDnf(false);
			}
		}
		return "";
	}

	public String mostrarDNF(SolucionFewestMoves s) {
		if (sesionRubikActual.getSoluciones().contains(s)) {
			if (!sesionRubikActual.getSoluciones().get(sesionRubikActual.getSoluciones().indexOf(s)).getFewestMoveDTO()
					.getDnf()) {
				sesionRubikActual.getSoluciones().get(sesionRubikActual.getSoluciones().indexOf(s)).getFewestMoveDTO()
						.setTiempoRestanteTexto("DNF");
				sesionRubikActual.getSoluciones().get(sesionRubikActual.getSoluciones().indexOf(s)).getFewestMoveDTO()
						.setDnf(true);
			} else {
				sesionRubikActual.getSoluciones().get(sesionRubikActual.getSoluciones().indexOf(s)).getFewestMoveDTO()
						.setTiempoRestanteTexto(Util.darFormatoTiempo(
								sesionRubikActual.getSoluciones().get(sesionRubikActual.getSoluciones().indexOf(s))
										.getFewestMoveDTO().getTiempoUsadoMilisegundos()));
				sesionRubikActual.getSoluciones().get(sesionRubikActual.getSoluciones().indexOf(s)).getFewestMoveDTO()
						.setDnf(false);
			}
		}
		return "";
	}

	public String mostrarPenalizacion(Tiempo t) {
		if (sesionRubikActual.getTiempos().contains(t)) {
			if (!sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO()
					.getDnf()) {
				if (!sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO()
						.getPenalizacion()) {
					sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO()
							.setTiempoTexto(Util.darFormatoTiempo(
									sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t))
											.getTiempoRubikDTO().getTiempoMilisegundos())
									+ " +2");
					sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO()
							.setPenalizacion(true);
				} else {
					sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO()
							.setTiempoTexto(Util.darFormatoTiempo(
									sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t))
											.getTiempoRubikDTO().getTiempoMilisegundos()));
					sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO()
							.setPenalizacion(false);
				}
			} else {
				sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO()
						.setPenalizacion(!sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t))
								.getTiempoRubikDTO().getPenalizacion());
			}
		}
		return "";
	}

	public String mezclaTraducida() {
		return Util.traducirSecuenciaWCA(this.tipoCubo, this.secuenciaMezcla);
	}

	public Puzzle getCubo() {
		return cubo;
	}

	public void setCubo(Puzzle cubo) {
		this.cubo = cubo;
	}

	public String[] getMezcla() {
		return mezcla;
	}

	public void setMezcla(String[] mezcla) {
		this.mezcla = mezcla;
	}

	public String getSecuenciaMezcla() {
		return secuenciaMezcla;
	}

	public void setSecuenciaMezcla(String secuenciaMezcla) {
		this.secuenciaMezcla = secuenciaMezcla;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public Integer getTiempoInspeccionSegundos() {
		return tiempoInspeccionSegundos;
	}

	public void setTiempoInspeccionSegundos(Integer tiempoInspeccionSegundos) {
		this.tiempoInspeccionSegundos = tiempoInspeccionSegundos;
	}

	public Integer getTiempoInspeccionUsadoMilisegundos() {
		return tiempoInspeccionUsadoMilisegundos;
	}

	public void setTiempoInspeccionUsadoMilisegundos(Integer tiempoInspeccionUsadoMilisegundos) {
		this.tiempoInspeccionUsadoMilisegundos = tiempoInspeccionUsadoMilisegundos;
	}

	public String getTiempoInspeccionUsadoTexto() {
		return tiempoInspeccionUsadoTexto;
	}

	public void setTiempoInspeccionUsadoTexto(String tiempoInspeccionUsadoTexto) {
		this.tiempoInspeccionUsadoTexto = tiempoInspeccionUsadoTexto;
	}

	public Integer getTiempoMilisegundos() {
		return tiempoMilisegundos;
	}

	public void setTiempoMilisegundos(Integer tiempoMilisegundos) {
		this.tiempoMilisegundos = tiempoMilisegundos;
	}

	public Boolean getDnf() {
		return dnf;
	}

	public void setDnf(Boolean dnf) {
		this.dnf = dnf;
	}

	public Boolean getPenalizacion() {
		return penalizacion;
	}

	public void setPenalizacion(Boolean penalizacion) {
		this.penalizacion = penalizacion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getTipoCubo() {
		return tipoCubo;
	}

	public void setTipoCubo(int tipoCubo) {
		this.tipoCubo = tipoCubo;
	}

	public String getTiempoRestanteTexto() {
		return tiempoRestanteTexto;
	}

	public void setTiempoRestanteTexto(String tiempoRestanteTexto) {
		this.tiempoRestanteTexto = tiempoRestanteTexto;
	}

	public String getSolucion() {
		return solucion;
	}

	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}

	public List<SesionRubik> getListaSesiones() {
		return listaSesiones;
	}

	public void setListaSesiones(List<SesionRubik> listaSesiones) {
		this.listaSesiones = listaSesiones;
	}

	public SesionRubik getSesionRubikActual() {
		return sesionRubikActual;
	}

	public void setSesionRubikActual(SesionRubik sesionRubikActual) {
		this.sesionRubikActual = sesionRubikActual;
	}

	public ConfiguracionManagedBean getConfiguracionManagedBean() {
		if (this.configuracionManagedBean != null) {
			return configuracionManagedBean;
		} else {
			return new ConfiguracionManagedBean();
		}
	}

	public void setConfiguracionManagedBean(ConfiguracionManagedBean configuracionManagedBean) {
		this.configuracionManagedBean = configuracionManagedBean;
	}

	public SesionManagedBean getSesionManagedBean() {
		if (this.sesionManagedBean != null) {
			return this.sesionManagedBean;
		} else {
			return new SesionManagedBean();
		}
	}

	public void setSesionManagedBean(SesionManagedBean sesionManagedBean) {
		this.sesionManagedBean = sesionManagedBean;
	}

	public boolean isValidoParaAO5() {
		return validoParaAO5;
	}

	public void setValidoParaAO5(boolean validoParaAO5) {
		this.validoParaAO5 = validoParaAO5;
	}

	public ListaRecords getListaRecords() {
		return listaRecords;
	}

	public void setListaRecords(ListaRecords listaRecords) {
		this.listaRecords = listaRecords;
	}

}
