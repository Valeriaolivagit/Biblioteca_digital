package com.bibliotecadigital.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bibliotecadigital.services.BookService;
import com.bibliotecadigital.services.dtos.BookDto;

/**
 * Servlet controlador encargado de gestionar el listado
 * de libros en la biblioteca digital.
 * 
 * Se encarga de obtener los libros desde el servicio y enviarlos
 * a la vista para su visualización.
 * 
 * @author Valeria Oliva Valdes
 * @version 1.0
 */
@WebServlet("/home")
public class BookServlet extends HttpServlet {
    /**
     * Servicio encargado de gestionar las operaciones relacionadas 
     * con los libros.
     */
    private BookService service = new BookService();

    /**
     * Procesa la solicitud GET para mostrar el listado de libros.
     * 
     * Verifica que exista una sesión activa; en caso contrario,
     * redirige al usuario a la página de inicio. Si la sesión es válida,
     * obtiene el parámetro de búsqueda, consulta los libros desde el
     * servicio y los envía a la vista para su visualización.
     * 
     * @param request Objeto que contiene la petición del cliente
     * @param response Objeto que contiene la respuesta para el cliente
     * @throws ServletException Si ocurre algún error en el servlet
     * @throws IOException Si ocurre algún error en la entrada o salida
     */
    protected void doGet(
    HttpServletRequest request, 
    HttpServletResponse response)throws ServletException,IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null){
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        String search = request.getParameter("search");
        List<BookDto> books = service.getBooks(search);

        request.setAttribute("books", books);
        request.setAttribute("search", search);

        request.getRequestDispatcher("/views/inicio.jsp").forward(request, response);
    }
}
