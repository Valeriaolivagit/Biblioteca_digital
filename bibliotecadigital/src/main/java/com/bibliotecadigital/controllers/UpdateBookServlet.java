package com.bibliotecadigital.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bibliotecadigital.services.BookService;
import com.bibliotecadigital.services.dtos.BookDto;

/**
 * Servlet controlador encargado de gestionar la edición
 * de libros en la biblioteca digital.
 * 
 * Maneja:
 * - Mostrar el formulario de edición de un libro (GET)
 * - Procesar los datos enviados para actualizar el libro (POST)
 *
 * @author Valeria Oliva Valdes
 * @version 1.0
 */
@WebServlet("/update-book")
public class UpdateBookServlet extends HttpServlet {
    /**
     * Servicio encargado de gestionar las operaciones relacionadas
     * con los libros.
     */
    private BookService service = new BookService();

    /**
     * Procesa la solicitud GET para mostrar el formulario de
     * edición de un libro.
     * 
     * Obtiene el identificador del libro desde la petición y
     * realiza una validación. En caso de que el identificador 
     * venga vacío, no sea un número o sea null, redirige al usuario 
     * a la página de inicio.
     * 
     * Si el identificador es válido, consulta la información
     * del libro mediante el servicio correspondiente
     * (BookService) y envía los datos a la vista para completar
     * el formulario de edición.
     *
     * @param request  Objeto que contiene la petición del cliente
     * @param response Objeto que contiene la respuesta para el cliente
     * @throws ServletException Si ocurre algún error en el servlet
     * @throws IOException      Si ocurre algún error en la entrada o salida
     *
     */
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("bookId");

        if (id == null || id.isEmpty() || !id.matches("\\d+")) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        int bookId = Integer.parseInt(id);

        BookDto bookToEdit = service.searchBookById(bookId);

        request.setAttribute("bookToEdit", bookToEdit);

        request.getRequestDispatcher("/views/add-book.jsp")
                .forward(request, response);
    }

    /**
     * Procesa la solicitud POST para actualizar un libro.
     * 
     * Recibe los datos enviados desde el formulario, valida la cantidad
     * ingresada y el id del libro, y luego convierte estos valores a
     * enteros y delega la operación al servicio correspondiente (BookService).
     * 
     * Si los datos son válidos, redirige al usuario a la página principal;
     * en caso contrario, retorna al formulario con un mensaje de error.
     * 
     * @param request  Objeto que contiene la petición del cliente
     * @param response Objeto que contiene la respuesta para el cliente
     * @throws ServletException Si ocurre algún error en el servlet
     * @throws IOException      Si ocurre algún error en la entrada o salida
     */
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("bookId");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String description = request.getParameter("description");
        String cantidad = request.getParameter("quantity");

        if (id == null || id.isEmpty() || !id.matches("\\d+")) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        int bookId = Integer.parseInt(id);

        if (cantidad == null || cantidad.isEmpty() || !cantidad.matches("\\d+")) {
            request.setAttribute("error", "La cantidad debe ser un número válido.");
            request.getRequestDispatcher("/views/add-book.jsp")
                    .forward(request, response);
            return;
        }

        int quantity = Integer.parseInt(cantidad);

        if (quantity <= 0) {
            request.setAttribute("error", "La cantidad debe ser mayor a 0.");
            request.getRequestDispatcher("/views/add-book.jsp")
                    .forward(request, response);
            return;
        }

        service.updateBook(bookId, title, author, description, quantity);

        response.sendRedirect(request.getContextPath() + "home");
    }
}
