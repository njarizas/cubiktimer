package xyz.njas.controlador.managedbeans.session;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import xyz.njas.modelo.dao.CredencialDAO;
import xyz.njas.modelo.dao.UsuarioDAO;
import xyz.njas.modelo.dto.CredencialDTO;
import xyz.njas.modelo.dto.UsuarioDTO;
import xyz.njas.util.EmailSenderInterface;
import xyz.njas.util.EmailSenderService;
import xyz.njas.util.EncryptService;

@ManagedBean
@ViewScoped
public class RegistroManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String clave;
    private String confirmarClave;

    private UsuarioDTO usuario;
    private UsuarioDAO usuarioDAO;
    private CredencialDAO credencialDAO;

    private EmailSenderInterface email;

    @ManagedProperty(value = "#{sesionManagedBean}")
    private SesionManagedBean sesionManagedBean;

    public RegistroManagedBean() {
    	usuario = new UsuarioDTO();
    	usuarioDAO = new UsuarioDAO();
    	credencialDAO = new CredencialDAO();
    }

//    @PostConstruct
//    public void init() {
//        listaRoles = listarRoles();
//    }

    public String registrarUsuario() {
        if (validarusuario()) {
            usuario.setFechaCreacion(new Date());
            usuario.setFechaModificacion(new Date());
            if (persistirUsuario()) {
                sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("UsuarioRegistradoExitosamente"));
                sesionManagedBean.getMensaje().setText(sesionManagedBean.getRecursos().getString("MensajeCuentaCreada"));
                sesionManagedBean.getMensaje().setType("success");
                sesionManagedBean.getMensaje().setMensajePendiente(true);
                email = new EmailSenderService();
                email.enviarMensajeActivacionCuenta(usuario);
                usuario = new UsuarioDTO();
                clave = "";
                confirmarClave = "";
                return "index";
            }
        }
        return "";
    }

    /**
     * Método que se encarga de verificar que los datos de un usuario sean
     * coherentes para realizar el registro en la base de datos
     *
     * @return <code>true</code> si los datos son válidos<br>
     * <code>false</code>si los datos no son válidos
     */
    public boolean validarusuario() {
        if (clave.equals(confirmarClave)) {
            usuario.setClave(EncryptService.encriptarClave(clave));
        } else {
            sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
            sesionManagedBean.getMensaje().setText(sesionManagedBean.getRecursos().getString("LasClavesNoCoinciden"));
            sesionManagedBean.getMensaje().setType("error");
            sesionManagedBean.getMensaje().setMensajePendiente(true);
            return false;
        }
        List<UsuarioDTO> lu=usuarioDAO.consultarUsuarioPorCorreo(usuario.getCorreo().trim());
        List<CredencialDTO> lc =credencialDAO.consultarCredencialPorCorreo(usuario.getCorreo().trim());
        if (!lu.isEmpty() || !lc.isEmpty()){
            sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
            sesionManagedBean.getMensaje().setText(sesionManagedBean.getRecursos().getString("NoSePuedeCrearUnUsuarioConCorreo")+" "+usuario.getCorreo().trim());
            sesionManagedBean.getMensaje().setType("warning");
            sesionManagedBean.getMensaje().setMensajePendiente(true);
            return false; 
        }
        return true;
    }

    /**
     * Método que se encarga de realizar la persistencia del usuario en la base
     * de datos
     * @return <code>true</code> si se pudo guardar<br>
     * <code>false</code> si no se pudo guardar
     */
    public boolean persistirUsuario() {
        try {
            List<UsuarioDTO> l = usuarioDAO.consultarUsuarioPorCorreoYEstado(usuario.getCorreo().trim(),1);
            List<CredencialDTO> lia = credencialDAO.consultarCredencialPorCorreoYEstado(usuario.getCorreo().trim(),1);
            if (!l.isEmpty()) {
                sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
                sesionManagedBean.getMensaje().setText(sesionManagedBean.getRecursos().getString("YaExisteUnUsuarioRegistradoConCorreo")+": " + usuario.getCorreo());
                sesionManagedBean.getMensaje().setType("warning");
                sesionManagedBean.getMensaje().setMensajePendiente(true);
                return false;
            }
            if (!lia.isEmpty()) { 
                sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
                sesionManagedBean.getMensaje().setText(sesionManagedBean.getRecursos().getString("NoSePuedeCrearUnUsuarioConCorreo")+": "+usuario.getCorreo()+" "+sesionManagedBean.getRecursos().getString("PorQueYaHaSidoUsadoPorAlguien"));
                sesionManagedBean.getMensaje().setType("warning");
                sesionManagedBean.getMensaje().setMensajePendiente(true);
                return false;
            } else {
            	usuario.setIdUsuario(usuarioDAO.create(usuario));
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
            sesionManagedBean.getMensaje().setText(sesionManagedBean.getRecursos().getString("OcurrioUnErrorAlIntentarRegistrarElUsuario"));
            sesionManagedBean.getMensaje().setType("error");
            sesionManagedBean.getMensaje().setMensajePendiente(true);
        }
        return false;
    }

//    public UsuarioEntity buscarUsuario(String correo) {
//        List<UsuarioEntity> l = usFacade.findByCorreo(correo);
//        if (l.size() == 1) {
//            return l.get(0);
//        }
//        return null;
//    }
//
//    public List<UsuarioEntity> listarUsuarios() {
//        return usFacade.findAll();
//    }
//
//    public List<InfoRolEntity> listarRoles() {
//        return iRFacade.findAll();
//    }
//    
//     public List<InfoRolEntity> listarRolesMenosAdmin() {
//        List<InfoRolEntity> lr;
//        lr = listarRoles();
//        InfoRolEntity ireAdmin = new InfoRolEntity(3);
//        lr.remove(ireAdmin);
//        return lr;
//    }
//
//    public List<InfoRolEntity> getListaRoles() {
//        return listaRoles;
//    }
//
//    public void setListaRoles(List<InfoRolEntity> listaRoles) {
//        this.listaRoles = listaRoles;
//    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getConfirmarClave() {
        return confirmarClave;
    }

    public void setConfirmarClave(String confirmarClave) {
        this.confirmarClave = confirmarClave;
    }
    
    public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
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

    public EmailSenderInterface getEmail() {
        return email;
    }

    public void setEmail(EmailSenderInterface email) {
        this.email = email;
    }

}