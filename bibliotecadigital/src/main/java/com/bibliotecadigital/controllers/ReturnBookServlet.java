package com.bibliotecadigital.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bibliotecadigital.services.RequestService;
import com.bibliotecadigital.services.dtos.LoanDto;
import com.bibliotecadigital.services.dtos.UserDto;

/**
 * Servlet controlador encargado de gestionar la solicitud
 * de devolución de un libro a la biblioteca digital.
 * 
 * Permite:
 * - Mostrar los libros prestados del usuario (GET)
 * - Procesar la devolución de un libro (POST)
 *
 * @author Valeria Oliva Valdes
 * @version 1.0
 */
@WebServlet("/return-book")
public class ReturnBookServlet extends HttpServlet {
    /**
     * Servicio encargado de gestionar las operaciones relacionadas
     * con el préstamo y devolución de los libros.
     */
    private RequestService service = new RequestService();

    /**
     * Procesa la solicitud GET para mostrar los libros
     * que el usuario tiene actualmente en préstamo.
     * 
     * Primero valida la sesión del usuario. Si no existe,
     * redirige a la página principal. En caso contrario,
     * obtiene los préstamos asoaciados al usuario y los
     * envía a la vista para su visualización.
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
        int userId = userDto.getId();

        List<LoanDto> loans = service.getLoansByUser(userId);

        request.setAttribute("loans", loans);
        request.getRequestDispatcher("/views/MyBooks.jsp")
                .forward(request, response);
    }

    /**
     * Procesa la solicitud POST para devolver un libro.
     * 
     * Primero valida la sesión del usuario. Si no existe,
     * redirige al usuario a la página principal. En caso contrario,
     * obtiene el identificador del usuario desde la sesión y
     * el identificador del libro desde la petición y delega
     * la operación al servicio correspondiente (RequestService).
     * 
     * Finalmente, redirige al listado de libros prestados del usuario.
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

        int loanId = Integer.parseInt(request.getParameter("loanId"));
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        service.returnLoan(loanId, bookId);

        response.sendRedirect(request.getContextPath() + "/return-book");
    }
}
