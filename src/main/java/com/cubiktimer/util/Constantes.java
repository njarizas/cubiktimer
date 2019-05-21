package com.cubiktimer.util;

public class Constantes {

	private Constantes() {

	}

	// ruta de la aplicación
	public static final String PATH_CUBIKTIMER = "C:\\cubiktimer\\";
	public static final String LINE_SEPARATOR = "line.separator";
	public static final String TIPO_CONEXION = "pool";//["pool","singleton"]

	// colores del cubo de rubik
	public static final String COLOR_BLANCO = "white";
	public static final String COLOR_NARANJA = "#ff8000";
	public static final String COLOR_VERDE = "green";
	public static final String COLOR_ROJO = "red";
	public static final String COLOR_AMARILLO = "yellow";
	public static final String COLOR_AZUL = "blue";

	// indica los colores iniciales de las caras del cubo
	public static final String COLOR_CARA_B = COLOR_AZUL;
	public static final String COLOR_CARA_D = COLOR_AMARILLO;
	public static final String COLOR_CARA_F = COLOR_VERDE;
	public static final String COLOR_CARA_L = COLOR_NARANJA;
	public static final String COLOR_CARA_R = COLOR_ROJO;
	public static final String COLOR_CARA_U = COLOR_BLANCO;

	public static final String LETRA_CARA_B = "b";
	public static final String LETRA_CARA_D = "d";
	public static final String LETRA_CARA_F = "f";
	public static final String LETRA_CARA_L = "l";
	public static final String LETRA_CARA_R = "r";
	public static final String LETRA_CARA_U = "u";

	public static final Integer MILISEGUNDOS_PENALIZACION = 2000;
	public static final Integer ROL_POR_DEFECTO = 2;

	// Constantes de mensajes para sweet alert
	public static final String ATENCION = "Atencion";

	public static final String SUCCESS = "success";
	public static final String INFO = "info";
	public static final String WARNING = "warning";
	public static final String ERROR = "error";

	// parametros de nombres de cubos para utilización en twistysim
	public static final String CUBO_2X2X2 = "222";
	public static final String CUBO_3X3X3 = "333";
	public static final String CUBO_4X4X4 = "444";
	public static final String CUBO_5X5X5 = "555";
	public static final String CUBO_6X6X6 = "666";
	public static final String CUBO_7X7X7 = "777";

	public static final String MEGAMINX = "minx";
	public static final String PYRAMINX = "pyram";
	public static final String SKEWB = "skewb";
	public static final String SQUARE_1 = "sq1";

	// nombres de las tablas
	public static final String TABLA_AHORCADO = "ahorcado";
	public static final String TABLA_AMIGOS = "amigos";
	public static final String TABLA_CONFIGURACION = "configuracion";
	public static final String TABLA_CREDENCIALES = "credenciales";
	public static final String TABLA_ESTADOS = "estados";
	public static final String TABLA_PARAMETROS = "parametros";
	public static final String TABLA_PERMISOS = "permisos";
	public static final String TABLA_ROLES = "roles";
	public static final String TABLA_ROLES_PERMISOS = "roles_permisos";
	public static final String TABLA_SESIONES_RUBIK = "sesiones_rubik";
	public static final String TABLA_SOLUCIONES_RUBIK = "soluciones_rubik";
	public static final String TABLA_TIEMPOS_RUBIK = "tiempos_rubik";
	public static final String TABLA_TIEMPOS_RUBIK_IMPORTADOS = "tiempos_rubik_importados";
	public static final String TABLA_TIPOS = "tipos";
	public static final String TABLA_USUARIOS = "usuarios";
	public static final String TABLA_USUARIOS_ROLES = "usuarios_roles";

	// nombres de las llaves primarias
	public static final String PK_TABLA_AHORCADO = "id_ahorcado";
	public static final String PK_TABLA_AMIGOS = "id_amistad";
	public static final String PK_TABLA_CONFIGURACION = "id_configuracion";
	public static final String PK_TABLA_CREDENCIALES = "id_credencial";
	public static final String PK_TABLA_ESTADOS = "id_estado";
	public static final String PK_TABLA_PARAMETROS = "codigo";// tiene llave primaria tipo texto
	public static final String PK_TABLA_PERMISOS = "id_permiso";
	public static final String PK_TABLA_ROLES = "id_rol";
	public static final String PK_TABLA_SESIONES_RUBIK = "id_sesion";
	public static final String PK_TABLA_SOLUCIONES_RUBIK = "id_solucion";
	public static final String PK_TABLA_TIEMPOS_RUBIK = "id_tiempo";
	public static final String PK_TABLA_TIEMPOS_RUBIK_IMPORTADOS = "id_tiempo";
	public static final String PK_TABLA_TIPOS = "id_tipo";
	public static final String PK_TABLA_USUARIOS = "id_usuario";

}
