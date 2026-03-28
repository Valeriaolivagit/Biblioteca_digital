package com.bibliotecadigital.services;

import java.util.ArrayList;
import java.util.List;

import com.bibliotecadigital.dao.BookDao;
import com.bibliotecadigital.entities.Book;
import com.bibliotecadigital.services.dtos.BookDto;

/**
 * Clase servicio encargada de gestionar la lógica de
 * negocio relacionada con la entidad Book.
 * 
 * Actúa como intermediario entre el controlador (Servlet)
 * y la capa de acceso a datos (DAO).
 * 
 * Utiliza el DAO para realizar operaciones sobre los libros.
 * 
 * @author Valeria Oliva Valdes
 * @version 1.0
 */
public class BookService {
    /**
     * Clase DAO utilizada para acceder y gestionar
     * los datos de los libros.
     */
    private BookDao dao = new BookDao();

    /**
     * Registra un nuevo libro en la biblioteca.
     *
     * Recibe la información del libro y delega la
     * operación de inserción al DAO correspondiente.
     * 
     * @param title       título del libro
     * @param author      autor del libro
     * @param description descripción breve del libro
     * @param quantity    cantidad disponible del libro
     */
    public void addBook(String title, String author, String description, int quantity) {
        Book book = new Book();

        book.setTitle(title);
        book.setAuthor(author);
        book.setDescription(description);
        book.setQuantity(quantity);

        dao.insertBook(book);
    }

    /**
     * Obtiene el listado de libros desde la base de datos
     * a través del DAO.
     * 
     * Convierte las entidades Book obtenidas del DAO en
     * objetos BookDto para su uso en otras capas de la
     * aplicación.
     * 
     * @return lista de objetos BookDto obtenidos de la
     *         base de datos.
     */
    public List<BookDto> getBooks(String search) {
        List<Book> books = dao.listBooks(search);

        List<BookDto> booksDto = new ArrayList<>();

        for (Book b : books) {
            BookDto book = new BookDto(b.getBookId(), b.getTitle(), b.getAuthor(), b.getDescription(), b.getQuantity());

            booksDto.add(book);
        }
        return booksDto;
    }

    /**
     * Obtiene los datos del libro a través del
     * identificador único del libro.
     * 
     * Consulta el libro en el DAO y convierte el
     * resultado en un objeto BookDto.
     * 
     * @param bookId identificador único del libro
     * @return objeto BookDto si el libro existe, en caso
     *         contrario retorna null
     */
    public BookDto searchBookById(int bookId) {
        Book book = dao.selectBook(bookId);

        if (book != null) {
            return new BookDto(book.getBookId(), book.getTitle(),
                    book.getAuthor(), book.getDescription(), book.getQuantity());
        }
        return null;
    }

    /**
     * Actualiza la información de un libro existente.
     * 
     * Crea una instancia de Book, introduce los parámetros
     * y delega la operación de actualización al DAO
     * correspondiente (BookDao)
     * 
     * @param bookId      identificador único del libro
     * @param title       título del libro
     * @param author      autor del libro
     * @param description descripción del libro
     * @param quantity    cantidad del libro
     */
    public void updateBook(int bookId, String title, String author, String description, int quantity) {
        Book book = new Book();

        book.setBookId(bookId);
        book.setTitle(title);
        book.setAuthor(author);
        book.setDescription(description);
        book.setQuantity(quantity);

        dao.updateBook(book);
    }

    /**
     * Gestiona la eliminación de un libro a través del
     * identificador único del libro.
     * 
     * Delega la operación al DAO correspondiente (BookDao)
     * 
     * @param bookId identificador único del libro
     */
    public void deleteBook(int bookId) {
        dao.deleteBook(bookId);
    }

}
