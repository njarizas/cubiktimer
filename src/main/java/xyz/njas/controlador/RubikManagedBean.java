package xyz.njas.controlador;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import xyz.njas.controlador.session.ConfiguracionManagedBean;
import xyz.njas.controlador.session.SesionManagedBean;
import xyz.njas.dao.TipoDAO;
import xyz.njas.dto.TiempoRubikDTO;
import xyz.njas.dto.TipoDTO;
import xyz.njas.facade.RubikFacade;
import xyz.njas.factories.RubikFactory;
import xyz.njas.rubik.modelo.Puzzle;
import xyz.njas.rubik.modelo.SesionRubik;
import xyz.njas.rubik.modelo.Tiempo;
import xyz.njas.util.Util;

/**
 *
 * @author Nelson
 */
@ManagedBean
@ViewScoped
public class RubikManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{configuracionManagedBean}")
	private ConfiguracionManagedBean configuracionManagedBean;

	@ManagedProperty(value = "#{sesionManagedBean}")
	private SesionManagedBean sesionManagedBean;

	private RubikFacade rubikFacade;

	private TipoDAO tipoDAO;
	
	private List<SesionRubik> listaSesiones;
	private SesionRubik sesionRubikActual;

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
	private String video;
	private String ip;

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
	}

	@PostConstruct
	public void init(){
		if (sesionManagedBean.getUsuarioLogueado()==null){
			this.sesionRubikActual= new SesionRubik(new Date());
		} else{
			this.sesionRubikActual= new SesionRubik(new Date(),sesionManagedBean.getUsuarioLogueado().getIdUsuario());
		}
		this.tipoCubo = configuracionManagedBean.getTipoCubo().getValorEntero();
		this.cubo = RubikFactory.crearCubo(this.tipoCubo);
		mezclaAleatoria();
	}

	public void cambioCubo(ValueChangeEvent event) {
		this.tipoCubo = Integer.parseInt(event.getNewValue().toString());
		this.configuracionManagedBean.getTipoCubo().setValorEntero(this.tipoCubo);
		this.cubo = RubikFactory.crearCubo(this.tipoCubo);
		mezclaAleatoria();
	}
	
	public List<TipoDTO> listarCubos(){
		List<TipoDTO> lista = tipoDAO.listarTiposDeCubo();
		return lista;
	}

	public String[] generarMezcla() {
		return cubo.generarMezcla();
	}

	public String resetearCubo() {
		//TODO usar factory
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
		for (String string : mezcla) {
			this.secuenciaMezcla += string + " ";
		}
		secuenciaMezcla = cubo.mezclar(mezcla);
		System.out.println(cubo);
		return "";
	}

	public String mezclaPersonalizada() {
		resetearCubo();
		this.secuenciaMezcla = this.secuenciaMezcla.toLowerCase()
				.replace("f", " f").replace("b", " b").replace("r", " r")
				.replace("l", " l").replace("u", " u").replace("d", " d")
				.replace("x", " x").replace("y", " y").replace("z", " z").replace("  ", " ");
		System.out.println(this.secuenciaMezcla);
		mezcla = this.secuenciaMezcla.trim().split(" ");
		secuenciaMezcla = cubo.mezclar(mezcla);
		System.out.println(cubo);
		return "";
	}

	public String agregarTiempo() {
		TiempoRubikDTO tiempoRubikDTO = new TiempoRubikDTO(cubo.getIdTipoCubo(), secuenciaMezcla,
				tiempoInspeccionSegundos, tiempoInspeccionUsadoMilisegundos, tiempoInspeccionUsadoTexto,
				tiempoMilisegundos, duracion, dnf, penalizacion);
		Tiempo t = new Tiempo(cubo, tiempoRubikDTO);
		sesionRubikActual.getTiempos().add(t);
		mezclaAleatoria();
		return "";
	}

	public String guardarSesionRubik() {
		if(rubikFacade.guardarRubik(sesionRubikActual.getSesionRubikDTO(), sesionRubikActual.getTiempos())>0){
			sesionManagedBean.getMensaje().setTitle("¡Información!");
			sesionManagedBean.getMensaje().setText("Se han guardado los tiempos actuales");
			sesionManagedBean.getMensaje().setType("success");
			sesionManagedBean.getMensaje().setMensajePendiente(true);
		} else {
			sesionManagedBean.getMensaje().setTitle("¡Atención!");
			sesionManagedBean.getMensaje().setText("Se presentó un error al tratar de guardar");
			sesionManagedBean.getMensaje().setType("error");
			sesionManagedBean.getMensaje().setMensajePendiente(true);
		}
		return "";
	}

	public String eliminarTiempo(Tiempo t) {
		if (sesionRubikActual.getTiempos().contains(t)) {
			sesionRubikActual.getTiempos().remove(t);
		}
		return "";
	}

	public String dnf(Tiempo t) {
		if (sesionRubikActual.getTiempos().contains(t)) {
			if (!sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO().getDnf()) {
				sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO().setTiempoTexto("DNF("+Util.darFormatoTiempo(sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t))
						.getTiempoRubikDTO().getTiempoMilisegundos())+")");
				sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO().setDnf(true);
			} else {
				if (sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO().getPenalizacion()) {
					sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO()
					.setTiempoTexto(Util.darFormatoTiempo(sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t))
							.getTiempoRubikDTO().getTiempoMilisegundos()) + " +2");
				} else {
					sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO()
					.setTiempoTexto(Util.darFormatoTiempo(sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t))
							.getTiempoRubikDTO().getTiempoMilisegundos()));
				}
				sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO().setDnf(false);
			}
		}
		return "";
	}

	public String penalizacion(Tiempo t) {
		if (sesionRubikActual.getTiempos().contains(t)) {
			if (!sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO().getDnf()) {
				if (!sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO().getPenalizacion()) {
					sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO()
					.setTiempoTexto(Util.darFormatoTiempo(sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t))
							.getTiempoRubikDTO().getTiempoMilisegundos()) + " +2");
					sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO().setPenalizacion(true);
				} else {
					sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO()
					.setTiempoTexto(Util.darFormatoTiempo(sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t))
							.getTiempoRubikDTO().getTiempoMilisegundos()));
					sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO().setPenalizacion(false);
				}
			} else {
				if (!sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO().getPenalizacion()) {
					sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO().setPenalizacion(true);
				} else {
					sesionRubikActual.getTiempos().get(sesionRubikActual.getTiempos().indexOf(t)).getTiempoRubikDTO().setPenalizacion(false);
				}
			}
		}
		return "";
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

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getTipoCubo() {
		return tipoCubo;
	}

	public void setTipoCubo(int tipoCubo) {
		this.tipoCubo = tipoCubo;
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
		if(this.configuracionManagedBean!=null){
			return configuracionManagedBean;
		} else{
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
