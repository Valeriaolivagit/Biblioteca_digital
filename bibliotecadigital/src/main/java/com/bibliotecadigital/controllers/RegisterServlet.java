package com.bibliotecadigital.controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bibliotecadigital.services.UserService;

import java.io.IOException;

import javax.servlet.ServletException;

/**
 * Servlet controlador encargado de gestionar el
 * registro de nuevos usuarios en la biblioteca digital.
 * 
 * Encargado de procesar los datos enviados desde el formulario
 * de registro y delega la creación al servicio correspondiente.
 * 
 * @author Valeria Oliva
 * @version 1.0
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    /**
     * Servicio utilizado para gestionar las operaciones relacionadas
     * con los usuarios.
     */
    private UserService service = new UserService();

    /**
     * Procesa los datos enviados desde el formulario de registro
     * de usuarios.
     * 
     * Obtiene los parámetros enviados en la petición, y valida que
     * el correo electrónico tenga un formato válido y que las contraseñas
     * coincidan. Si alguna validación falla, retorna al formulario de
     * registro junto con el mensaje de error correspondiente.
     * 
     * Posteriormente, verifica si el correo ingresado ya se encuentra
     * registrado, delegando la operacion al servicio correspondiente
     * (UserService). Si el correo ya existe, retorna al nuevamente
     * al formulario de registro mostrando un mensaje de error.
     * 
     * En caso contrario, registra al nuevo usuario en el sistema y
     * redirige al usuario a la página de inicio de sesión.
     * 
     * @param request  Objeto que contiene la petición del cliente.
     * @param response Objeto que contiene la respuesta para el cliente.
     * @throws ServletException Si ocurre algún error en el servlet.
     * @throws IOException      Si ocurre algún error en la entrada o salida.
     */
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPass = request.getParameter("confirmPass");

        if (email == null || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            request.setAttribute("errorEmail", "⚠️ Ingrese un correo válido. (ej. correo@email.com)");
            request.getRequestDispatcher("/views/register.jsp")
                    .forward(request, response);
            return;
        }

        if (!password.equals(confirmPass)) {
            request.setAttribute("errorPassword", "⚠️ Las contraseñas no coinciden");
            request.getRequestDispatcher("/views/register.jsp")
                    .forward(request, response);
            return;
        }
        boolean exists = service.emailExists(email);

        if (exists) {
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("email", email);
            request.setAttribute("errorEmail", "⚠️ Este correo ya se encuentra registrado");
            request.getRequestDispatcher("/views/register.jsp")
                    .forward(request, response);
            return;
        }
        service.registerUser(firstName, lastName, email, password);

        response.sendRedirect(request.getContextPath() + "/views/login.jsp");
    }
}
