package com.bibliotecadigital.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bibliotecadigital.services.RequestService;
import com.bibliotecadigital.services.dtos.UserDto;

/**
 * Servlet controlador encargado de gestionar la solicitud
 * de préstamo de libros a la biblioteca digital.
 * 
 * Procesa los datos enviados para ingresar la solicitud
 * de préstamo mediante una petición POST.
 *
 * @author Valeria Oliva Valdes
 * @version 1.0
 */
@WebServlet("/request-book")
public class RequestBookServlet extends HttpServlet {
    /**
     * Servicio encargado de gestionar las operaciones relacionadas
     * con el préstamo y devolución de los libros.
     */
    private RequestService service = new RequestService();

    /**
     * Procesa la solicitud POST para solicitar un libro.
     * 
     * Primero valida la sesión del usuario. Si no existe,
     * redirige al usuario a la página principal. En caso contrario,
     * obtiene el identificador del usuario desde la sesión y
     * el identificador del libro desde la petición y delega
     * la operación al servicio correspondiente (RequestService).
     * 
     * Si la operación es exitosa, registra el préstamo y redirige al
     * usuario a la página principal con un mensaje de éxito;
     * En caso contrario, redirige con un mensaje de error.
     * 
     * @param request  Objeto que contiene la petición del cliente
     * @param response Objeto que contiene la respuesta para el cliente
     * @throws ServletException Si ocurre algún error en el servlet
     * @throws IOException      Si ocurre algún error en la entrada o salida
     */
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        UserDto userDto = (UserDto) session.getAttribute("user");
        int userId = userDto.getId();
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        boolean exito = service.requestBook(userId, bookId);

        if (exito) {
            String successBook = "Prestamo exitoso";

            session.setAttribute("successBook", successBook);
            response.sendRedirect("/home");
        } else {
            String errorBook = "Ya tienes este libro";

            session.setAttribute("errorBook", errorBook);
            response.sendRedirect("/home");
        }
    }

}
