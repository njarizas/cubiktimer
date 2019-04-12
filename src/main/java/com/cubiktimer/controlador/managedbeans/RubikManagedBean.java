package com.cubiktimer.controlador.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.cubiktimer.modelo.dto.FewestMovesDTO;
import com.cubiktimer.modelo.dto.TiempoRubikDTO;
import com.cubiktimer.modelo.dto.TipoDTO;
import com.cubiktimer.modelo.rubik.Puzzle;
import com.cubiktimer.modelo.rubik.SesionRubik;
import com.cubiktimer.modelo.rubik.SolucionFewestMoves;
import com.cubiktimer.modelo.rubik.Tiempo;
import com.cubiktimer.modelo.rubik.TipoCubo;
import com.cubiktimer.util.Constantes;
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

	// Para cuando se compite por movimientos
	private int tipoCuboFewest;
	private Puzzle cuboFewestMoves;

	private String[] mezclaFewest;
	private String secuenciaMezclaFewest;

	private Integer tiempoMilisegundosFewest;
	private String tiempoRestanteTexto;
	private String solucion;
	private Boolean dnfFewest;
	private String comentarioFewest;

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

		this.secuenciaMezclaFewest = "";
		this.tiempoMilisegundosFewest = 0;
		this.solucion = "";
		this.tiempoRestanteTexto = "59:59";
		this.dnfFewest = false;
		this.comentarioFewest = "";
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
		this.tipoCuboFewest = TipoCubo.CUBO_3X3X3_FEWEST_MOVES.getId();
		this.cuboFewestMoves = RubikFactory.crearCubo(this.tipoCuboFewest);
		mezclaAleatoria();
		mezclaAleatoriaFewest();
	}

	public void cambioCubo(ValueChangeEvent event) {
		this.tipoCubo = Integer.parseInt(event.getNewValue().toString());
		this.configuracionManagedBean.getTipoCubo().setValorEntero(this.tipoCubo);
		this.cubo = RubikFactory.crearCubo(this.tipoCubo);
		mezclaAleatoria();
	}

	public void cambioCuboFewest(ValueChangeEvent event) {
		this.tipoCuboFewest = Integer.parseInt(event.getNewValue().toString());
		this.configuracionManagedBean.getTipoCuboFewest().setValorEntero(this.tipoCuboFewest);
		this.cuboFewestMoves = RubikFactory.crearCubo(this.tipoCuboFewest);
		mezclaAleatoriaFewest();
	}

	public List<TipoDTO> listarCubos() {
		return tipoDAO.listarTiposDeCubo();
	}

	public List<TipoDTO> listarCubosFewest() {
		return tipoDAO.listarTiposDeCuboFewest();
	}

	public String[] generarMezcla() {
		return cubo.generarMezcla();
	}

	public String[] generarMezclaFewest() {
		return cuboFewestMoves.generarMezcla();
	}

	public String resetearCubo() {
		cubo = RubikFactory.crearCubo(this.tipoCubo);
		return "";
	}

	public String resetearCuboFewest() {
		cuboFewestMoves = RubikFactory.crearCubo(this.tipoCuboFewest);
		return "";
	}

	public String resetearTodo() {
		resetearCubo();
		resetearCuboFewest();
		this.secuenciaMezcla = "";
		this.mezcla = null;
		this.secuenciaMezclaFewest = "";
		this.mezclaFewest = null;
		return "";
	}

	public String mezclaAleatoria() {
		resetearCubo();
		this.secuenciaMezcla = "";
		this.mezcla = generarMezcla();
		secuenciaMezcla = cubo.mezclar(mezcla);
		log.trace(cubo);
		log.debug("secuencia mezcla aleatoria (" + cubo.getNombre() + "): " + secuenciaMezcla);
		return "";
	}

	public String mezclaAleatoriaFewest() {
		resetearCuboFewest();
		this.secuenciaMezclaFewest = "";
		this.solucion = "";
		this.mezclaFewest = generarMezclaFewest();
		secuenciaMezclaFewest = cuboFewestMoves.mezclar(mezclaFewest);
		log.trace(cuboFewestMoves);
		log.debug("secuencia mezcla aleatoria (" + cuboFewestMoves.getNombre() + "): " + secuenciaMezclaFewest);
		return "";
	}

	public String mezclaPersonalizada() {
		resetearCubo();
		if (this.tipoCubo != 26) {// Diferente a Pyraminx
			this.secuenciaMezcla = this.secuenciaMezcla.toLowerCase();
		}
		this.secuenciaMezcla = this.secuenciaMezcla.replace("f", " f").replace("b", " b").replace("r", " r")
				.replace("l", " l").replace("u", " u").replace("d", " d").replace("x", " x").replace("y", " y")
				.replace("z", " z").replace("3 b", " 3b").replace("3 d", " 3d").replace("3 f", " 3f")
				.replace("3 l", " 3l").replace("3 r", " 3r").replace("3 u", " 3u").replace("  ", " ");
		if (this.tipoCubo == 27) {// Square 1
			this.secuenciaMezcla = this.secuenciaMezcla.replace(" ", "").replace("/", " / ");
		}
		mezcla = this.secuenciaMezcla.trim().split(" ");
		secuenciaMezcla = cubo.mezclar(mezcla);
		log.trace(cubo);
		log.debug("secuencia mezcla personalizada (" + cubo.getNombre() + "): " + secuenciaMezcla);
		return "";
	}

	public String mezclaPersonalizadaFewest() {
		resetearCuboFewest();
		if (this.tipoCuboFewest != 26) {// Diferente a Pyraminx
			this.secuenciaMezclaFewest = this.secuenciaMezclaFewest.toLowerCase();
		}
		this.secuenciaMezclaFewest = this.secuenciaMezclaFewest.replace("f", " f").replace("b", " b").replace("r", " r")
				.replace("l", " l").replace("u", " u").replace("d", " d").replace("x", " x").replace("y", " y")
				.replace("z", " z").replace("3 b", " 3b").replace("3 d", " 3d").replace("3 f", " 3f")
				.replace("3 l", " 3l").replace("3 r", " 3r").replace("3 u", " 3u").replace("  ", " ");
		if (this.tipoCuboFewest == 27) {// Square 1
			this.secuenciaMezclaFewest = this.secuenciaMezclaFewest.replace(" ", "").replace("/", " / ");
		}
		mezclaFewest = this.secuenciaMezclaFewest.trim().split(" ");
		secuenciaMezclaFewest = cuboFewestMoves.mezclar(mezclaFewest);
		log.trace(cuboFewestMoves);
		log.debug("secuencia mezcla personalizada Fewest Moves (" + cuboFewestMoves.getNombre() + "): "
				+ secuenciaMezclaFewest);
		return "";
	}

	public String agregarTiempo() {
		TiempoRubikDTO tiempoRubikDTO = new TiempoRubikDTO(cubo.getIdTipoCubo(), secuenciaMezcla,
				tiempoInspeccionSegundos, tiempoInspeccionUsadoMilisegundos, tiempoInspeccionUsadoTexto,
				tiempoMilisegundos, duracion, dnf, penalizacion, comentario);
		Tiempo t = new Tiempo(cubo, tiempoRubikDTO);
		sesionRubikActual.getTiempos().add(t);
		mezclaAleatoria();
		return "";
	}

	public String agregarSolucion() {
		String[] giros = solucion.split(" ");
		cuboFewestMoves.mezclar(giros);
		FewestMovesDTO fewestMovesDTO = new FewestMovesDTO(cuboFewestMoves.getIdTipoCubo(), secuenciaMezclaFewest,
				tiempoMilisegundosFewest, tiempoRestanteTexto, solucion, dnfFewest, comentarioFewest);
		fewestMovesDTO.setLongitudSolucion(giros.length);
		if (cuboFewestMoves.estaResuelto()) {
			fewestMovesDTO.setSolucionValida(true);
		} else {
			fewestMovesDTO.setSolucionValida(false);
			fewestMovesDTO.setDnf(true);
		}
		SolucionFewestMoves s = new SolucionFewestMoves(cuboFewestMoves, fewestMovesDTO);
		sesionRubikActual.getSoluciones().add(s);
		mezclaAleatoriaFewest();
		return "";
	}

	/**
	 * Método que se encarga de guardar los tiempos empleados para solucionar el
	 * cubo de rubik en la sesión actual
	 * 
	 * @return
	 */
	public String guardarSesionRubik() {
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
			if (rubikFacade.guardarRubik(sesionRubikActual.getSesionRubikDTO(), sesionRubikActual.getTiempos(), sesionRubikActual.getSoluciones()) > 0) {
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
		return "";
	}

	public String limpiarSoluciones() {
		sesionRubikActual.setSoluciones(new ArrayList<SolucionFewestMoves>());
		return "";
	}

	public String eliminarTiempo(Tiempo t) {
		if (sesionRubikActual.getTiempos().contains(t)) {
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

	public String mezclaTraducidaFewest() {
		return Util.traducirSecuenciaWCA(this.tipoCuboFewest, this.secuenciaMezclaFewest);
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

	public int getTipoCuboFewest() {
		return tipoCuboFewest;
	}

	public void setTipoCuboFewest(int tipoCuboFewest) {
		this.tipoCuboFewest = tipoCuboFewest;
	}

	public Puzzle getCuboFewestMoves() {
		return cuboFewestMoves;
	}

	public void setCuboFewestMoves(Puzzle cuboFewestMoves) {
		this.cuboFewestMoves = cuboFewestMoves;
	}

	public String[] getMezclaFewest() {
		return mezclaFewest;
	}

	public void setMezclaFewest(String[] mezclaFewest) {
		this.mezclaFewest = mezclaFewest;
	}

	public String getSecuenciaMezclaFewest() {
		return secuenciaMezclaFewest;
	}

	public void setSecuenciaMezclaFewest(String secuenciaMezclaFewest) {
		this.secuenciaMezclaFewest = secuenciaMezclaFewest;
	}

	public Integer getTiempoMilisegundosFewest() {
		return tiempoMilisegundosFewest;
	}

	public void setTiempoMilisegundosFewest(Integer tiempoMilisegundosFewest) {
		this.tiempoMilisegundosFewest = tiempoMilisegundosFewest;
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

	public Boolean getDnfFewest() {
		return dnfFewest;
	}

	public void setDnfFewest(Boolean dnfFewest) {
		this.dnfFewest = dnfFewest;
	}

	public String getComentarioFewest() {
		return comentarioFewest;
	}

	public void setComentarioFewest(String comentarioFewest) {
		this.comentarioFewest = comentarioFewest;
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

}
