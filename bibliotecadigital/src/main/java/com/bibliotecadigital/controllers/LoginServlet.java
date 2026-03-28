package com.bibliotecadigital.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bibliotecadigital.services.dtos.UserDto;
import com.bibliotecadigital.services.UserService;

/**
 * Servlet controlador encargado de gestionar el
 * ingreso de usuarios a la biblioteca digital.
 * 
 * Se encarga de autenticar el usuario utilizando el servicio
 * correspondiente, y, si las credenciales válidas,
 * crea y mantiene la sesión del usuario.
 * 
 * @author Valeria Oliva
 * @version 1.0
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    /**
     * Servicio utilizado para gestionar las operaciones relacionadas
     * con los usuarios.
     */
    UserService service = new UserService();

    /**
     * Procesa los datos enviados desde el formulario de inicio
     * de sesión (login).
     * 
     * Obtiene los parámetros de la petición, llama al servicio
     * correspondiente (UserService) para validar las credenciales
     * del usuario.
     * 
     * Si el usuario es válido, crea una sesión y almacena el usuario
     * autenticado, redirigiendo a la página principal. En caso contrario,
     * retorna al formulario de login junto con un mensaje de error y
     * el email ingresado.
     * 
     * @param request  Objeto que contiene la petición del cliente.
     * @param response Objeto que contiene la respuesta para el cliente.
     * @throws ServletException Si ocurre algún error en el servlet.
     * @throws IOException      Si ocurre algún error en la entrada o salida.
     */
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDto userToLogin = service.selectUser(email, password);

        if (userToLogin != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", userToLogin);
            Cookie cookie = new Cookie("email", email);
            cookie.setMaxAge(3600);

            response.addCookie(cookie);

            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            request.setAttribute("email", email);
            request.setAttribute("error", "Credenciales inválidas.");

            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }

    /**
     * Procesa la solicitud GET para cerrar la sesión del usuario.
     * 
     * Mediante una validación, verifica si existe una sesión activa.
     * Si existe una sesión activa, realiza un cierre de sesión. Y
     * redirige al usuario hacia la página principal.
     * 
     * @param request  Objeto que contiene la petición del cliente
     * @param response Objeto que contiene la respuesta para el cliente
     * @throws ServletException Si ocurre algún error en el servlet
     * @throws IOException      Si ocurre algún error en la entrada o salida
     */
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

}
