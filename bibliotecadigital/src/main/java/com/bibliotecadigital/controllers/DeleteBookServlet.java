package com.bibliotecadigital.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bibliotecadigital.services.BookService;

/**
 * Servlet controlador encargado de gestionar la 
 * eliminación de libros en la biblioteca digital.
 * 
 * Procesa los datos enviados para eliminar un libro (POST).
 * 
 * @author Valeria Oliva Valdes
 * @version 1.0
 */
@WebServlet("/delete-book")
public class DeleteBookServlet extends HttpServlet {
    /**
     * Servicio encargado de gestionar las operaciones relacionadas
     * con los libros.
     */
    private BookService service = new BookService();

     /**
     * Procesa la solicitud POST para eliminar un libro.
     * 
     * Recibe los datos enviados desde el formulario, valida el identificador
     * del libro, lo convierte a un valor entero, y delega la operación 
     * al servicio correspondiente (BookService). Si el dato es válido,
     * redirige al usuario a la página principal; en caso contrario, retorna
     * al formulario con un mensaje de error.
     * 
     * @param request  Objeto que contiene la petición del cliente
     * @param response Objeto que contiene la respuesta para el cliente
     * @throws ServletException Si ocurre algún error en el servlet
     * @throws IOException      Si ocurre algún error en la entrada o salida
     */
    public void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("bookId");

          if (id == null || id.isEmpty() || !id.matches("\\d+")) {
            response.sendRedirect("/home");
            return;
        }

        int bookId = Integer.parseInt(id);

        service.deleteBook(bookId);

        response.sendRedirect("/home");
    }

}
