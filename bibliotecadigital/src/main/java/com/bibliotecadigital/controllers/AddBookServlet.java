package com.bibliotecadigital.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bibliotecadigital.services.BookService;

/**
 * Servlet controlador encargado de gestionar la funcionalidad
 * de agregar un nuevo libro a la biblioteca digital.
 * 
 * Maneja:
 * - Mostrar el formulario para agregar un libro (GET)
 * - Procesar los datos enviados para agregar un libro (POST)
 *
 * @author Valeria Oliva Valdes
 * @version 1.0
 */
@WebServlet("/add-book")
public class AddBookServlet extends HttpServlet {
    /**
     * Servicio encargado de gestionar las operaciones relacionadas
     * con los libros.
     */
    private BookService service = new BookService();

    /**
     * Procesa la solicitud GET para mostrar el formulario de
     * registro de un nuevo libro.
     * 
     * Reenvia la petición a la vista correspondiente (add-book.jsp)
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
        request.getRequestDispatcher("/views/add-book.jsp")
                .forward(request, response);
    }

    /**
     * Procesa la solicitud POST para agregar un nuevo libro.
     * 
     * Recibe los datos enviados desde el formulario, valida la cantidad
     * ingresada, transforma la cantidad a entero, y delega la operación
     * al servicio correspondiente (BookService). Si los datos son válidos,
     * redirige al usuario a la página principal; en caso contrario, retorna
     * al formulario con un mensaje de error.
     * 
     * @param request  Objeto que contiene la petición del cliente
     * @param response Objeto que contiene la respuesta para el cliente
     * @throws ServletException Si ocurre algún error en el servlet
     * @throws IOException      Si ocurre algún error en la entrada o salida
     */
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String description = request.getParameter("description");
        String cantidad = request.getParameter("quantity");

        if (cantidad == null || cantidad.isEmpty() || !cantidad.matches("\\d+")) {
            request.setAttribute("error", "La cantidad debe ser un número válido.");
            request.setAttribute("title", title);
            request.setAttribute("author", author);
            request.setAttribute("description", description);
            request.getRequestDispatcher("/views/add-book.jsp")
                    .forward(request, response);
            return;
        }

        int quantity = Integer.parseInt(cantidad);

        if (quantity <= 0) {
            request.setAttribute("error", "La cantidad debe ser mayor a 0.");
            request.setAttribute("title", title);
            request.setAttribute("author", author);
            request.setAttribute("description", description);
            request.getRequestDispatcher("/views/add-book.jsp")
                    .forward(request, response);
            return;
        }

        service.addBook(title, author, description, quantity);

        response.sendRedirect("/home");
    }
}
