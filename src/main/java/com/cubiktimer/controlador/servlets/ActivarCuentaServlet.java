/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.controlador.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cubiktimer.error.ExceptionHandler;
import com.cubiktimer.modelo.dao.ParametroDAO;
import com.cubiktimer.modelo.dao.UsuarioDAO;
import com.cubiktimer.modelo.dto.UsuarioDTO;

/**
 * Servlet implementation class ActivarCuentaManagedBean
 */
@WebServlet("/ActivarCuenta")
public class ActivarCuentaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivarCuentaServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ParametroDAO dao = new ParametroDAO();
        String token = request.getParameter("t") != null ? request.getParameter("t") : "";
        PrintWriter print = response.getWriter();
        print.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" + 
        		"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
        		"    <head>\r\n" + 
        		"        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n" + 
        		"        <title>Gracias por regitrarte en CubikTimer</title>\r\n" + 
        		"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"/>\r\n" + 
        		"        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"></link>\r\n" + 
        		"<style>\r\n" + 
        		"            a:hover{\r\n" + 
        		"                color: #fff;\r\n" + 
        		"                background-color: #286090;\r\n" + 
        		"                border-color: #204d74;\r\n" + 
        		"            }\r\n" + 
        		"        </style>\r\n" + 
        		"    </head>\r\n" + 
        		"    <body style=\"text-align: center;\">\r\n" + 
        		"         <table align=\"center\" style=\"border: solid #cccccc; border-radius: 8px; width: 70%; min-width: 480px; margin-top: 20px;\">\r\n" + 
        		"            <tr>\r\n" + 
        		"                <td align=\"center\" style=\" font-size: 28px; font-weight: bold; font-family: Arial, sans-serif;\">\r\n" + 
        		"                    <img  style=\"margin-top: 20px;\" src=\"" + dao.obtenerValorParametro("host_cubiktimer") + "/cubiktimer/resources/images/logo_cubiktimer.png\" alt=\"cubo rubik\" width=\"240\" align=\"center\"/>\r\n" + 
        		"                </td>\r\n" + 
        		"            </tr>\r\n" + 
        		"            <tr>\r\n" + 
        		"                <td bgcolor=\"#ffffff\" style=\"padding: 40px 30px 40px 30px;\">\r\n" + 
        		"                    \r\n" + 
        		"                            <div style=\"color: #153643; font-family: Arial, sans-serif; font-size: 24px;\">\r\n");
        print.println("<b>" + activarUsuario(token) + "</b><br>");
        print.println("<a href=\" "+ dao.obtenerValorParametro("host_cubiktimer") +"/cubiktimer\">Haz clic aqui para ir al inicio de sesion de Cubik Timer</a>");
        print.println("</div>\r\n" + 
        		"                </td>\r\n" + 
        		"            </tr>\r\n" + 
        		"        </table>\r\n" + 
        		"    </body>\r\n" + 
        		"</html>");
    }

    private String activarUsuario(String token) {
    	UsuarioDAO dao = new UsuarioDAO();
        List<UsuarioDTO> results = dao.consultarUsuarioPorToken(token);
        if (results.size() == 1) {
            switch (results.get(0).getEstado()) {
                case 1:
                    return "Esta cuenta ya ha sido activada";
                case 2:
                    try {
                        UsuarioDTO user = results.get(0);
                        user.setEstado(1);
                        dao.update(user);
                        return "La cuenta ha sido activada";
                    } catch (Exception ex) {
                    	ExceptionHandler.manejarExcepcionGrave(ex);
                        return "No fue posible activar la cuenta";
                        
                    }
                default:
                    return "No es posible activar esta cuenta";
            }
        } else {
            return "La informacion de activacion no es valida";
        }
    }
    
}
