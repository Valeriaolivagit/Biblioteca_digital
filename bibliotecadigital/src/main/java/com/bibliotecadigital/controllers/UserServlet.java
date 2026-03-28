package com.bibliotecadigital.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bibliotecadigital.services.UserService;
import com.bibliotecadigital.services.dtos.UserDto;

/**
 * Servlet controlador encargado de gestionar el
 * perfil y actualización de datos de los usuarios
 * en la biblioteca digital.
 * 
 * Maneja:
 * - Mostrar los datos del usuario en su perfil (GET)
 * - Procesa la solicitud de actualización de datos (POST)
 * 
 * @author Valeria Oliva
 * @version 1.0
 */
@WebServlet("/profile")
public class UserServlet extends HttpServlet {
    /**
     * Servicio utilizado para gestionar las operaciones relacionadas
     * con los usuarios.
     */
    private UserService service = new UserService();

    /**
     * Procesa la solicitud GET para mostrar la información del
     * perfil del usuario.
     * 
     * Mediante una validación, verifica si existe una sesión activa.
     * Si no existe una sesión activa, retorna al usuario a la página
     * principal.
     * 
     * En caso contrario, obtiene los datos del usuario desde la sesión 
     * y los envia a la vista para mostrar la información del perfil.
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

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        UserDto userDto = (UserDto) session.getAttribute("user");

        request.setAttribute("user", userDto);

        request.getRequestDispatcher("/views/profile.jsp").forward(request, response);
    }

    /**
     * Procesa los datos enviados desde el formulario del perfil
     * de usuario.
     * 
     * Primero valida que exista una sesión activa. Si no existe, 
     * redirige al usuario a la página principal. 
     * 
     * En caso contrario, obtiene los parámetros enviados
     * desde la petición y verifica que los campos de nombre y 
     * apellido contengan información. Si alguno esta vacío,
     * retorna al usuario a la página de perfil junto con un mensaje
     * de error. 
     * 
     * Si los datos son válidos, delega la actualización de los
     * datos del usuario al servicio correspondiente (UserService),
     * actualiza la información del usuario en la sesión y redirige 
     * nuevamente a la página de perfil.
     *  
     * @param request  Objeto que contiene la petición del cliente.
     * @param response Objeto que contiene la respuesta para el cliente.
     * @throws ServletException Si ocurre algún error en el servlet.
     * @throws IOException      Si ocurre algún error en la entrada o salida.
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        String id = request.getParameter("id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        if (firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty()) {
            UserDto userDto = (UserDto) session.getAttribute("user");
            request.setAttribute("user", userDto);
            request.setAttribute("error", "El nombre o apellido no pueden estar vacíos.");
            request.getRequestDispatcher("/views/profile.jsp")
                    .forward(request, response);
            return;
        }

        int userId = Integer.parseInt(id);
        service.updateUser(userId, firstName, lastName);

        UserDto updatedUser = service.getUserById(userId);
        session.setAttribute("user", updatedUser);

        response.sendRedirect(request.getContextPath() + "/profile");

    }
}