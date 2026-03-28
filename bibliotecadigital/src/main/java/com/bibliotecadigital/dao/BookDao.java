package com.bibliotecadigital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bibliotecadigital.entities.Book;
import com.bibliotecadigital.utils.ConnectionMysql;

/**
 * Clase DAO encargado de gestionar el acceso a los datos
 * de los libros en la base de datos.
 * 
 * Proporciona métodos para realizar operaciones CRUD
 * sobre la entidad Book.
 * 
 * Utilizado por la capa de servicio para obtener y persistir
 * la información de los libros.
 * 
 * @author Valeria Oliva
 * @version 1.0
 * 
 */
public class BookDao {
    /**
     * Inserta un nuevo libro en la base de datos.
     * 
     * Establece la conexión con la base de datos y ejecuta una sentencia SQL
     * de tipo INSERT en la tabla books.
     * 
     * Utiliza {@link java.sql.PreparedStatement} para evitar inyecciones SQL.
     * 
     * @param book objeto que contiene los datos del libro a insertar.
     */
    public void insertBook(Book book) {

        String query = "INSERT INTO books (title, author, description, quantity) VALUES (?, ?, ?, ?)";

        try (Connection connection = ConnectionMysql.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getDescription());
            ps.setInt(4, book.getQuantity());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene el listado libros desde la base de datos.
     * 
     * Establece la conexión con la base de datos, ejecuta una sentencia
     * SQL de tipo SELECT sobre la tabla "books" y recorre los resultados
     * utilizando {@link ResultSet} para construir la lista de libros.
     * 
     * Utiliza {@link java.sql.PreparedStatement} para evitar inyecciones SQL.
     * 
     * @return lista de objetos Book con los datos
     *         obtenidos de la base de datos.
     */
    public List<Book> listBooks(String search) {
        List<Book> books = new ArrayList<>();
        String sqlQuery = "SELECT * FROM books";

            if (search != null && !search.isEmpty()) {
                sqlQuery += " WHERE title LIKE ? OR author LIKE ? OR description LIKE ?";
            }

        try (Connection connection = ConnectionMysql.getConnection();
          PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
                  
            if (search != null && !search.isEmpty()) {
                ps.setString(1, "%" + search + "%");
                ps.setString(2, "%" + search + "%");
                ps.setString(3, "%" + search + "%");
            }

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                Book book = new Book();

                book.setBookId(result.getInt("book_id"));
                book.setTitle(result.getString("title"));
                book.setAuthor(result.getString("author"));
                book.setDescription(result.getString("description"));
                book.setQuantity(result.getInt("quantity"));

                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * Obtiene el libro solicitado desde la base de datos.
     * 
     * Establece la conexión con la base de datos, ejecuta una
     * sentencia SQL de tipo SELECT sobre la tabla "books" del
     * identificador entregado y obtiene los resultados utilizando
     * {@link ResultSet} del libro solicitado.
     * 
     * Utiliza {@link java.sql.PreparedStatement} para evitar inyecciones SQL.
     * 
     * @param bookId identificador único del libro
     * @return retorna el libro con los datos obtenidos de
     *         la base de datos
     */
    public Book selectBook(int bookId) {
        Book book = new Book();
        String query = "SELECT * FROM books WHERE book_id = ?";

        try (Connection connection = ConnectionMysql.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, bookId);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                book.setBookId(result.getInt("book_id"));
                book.setTitle(result.getString("title"));
                book.setAuthor(result.getString("author"));
                book.setDescription(result.getString("description"));
                book.setQuantity(result.getInt("quantity"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    /**
     * Actualiza un libro de la base de datos.
     * 
     * Establece la conexión con la base de datos, ejecuta una
     * sentencia SQL de tipo UPDATE sobre la tabla "books" del
     * identificador entregado y actualiza la información en la
     * base de datos.
     * 
     * Utiliza {@link java.sql.PreparedStatement} para evitar inyecciones SQL.
     *
     * @param book objeto que contiene los datos del libro a actualizar.
     */
    public void updateBook(Book book) {
        String query = "UPDATE books set title = ?, author = ?, description = ?, quantity = ? WHERE book_id = ?";

        try (Connection connection = ConnectionMysql.getConnection();
                PreparedStatement ps = connection.prepareStatement(query);) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getDescription());
            ps.setInt(4, book.getQuantity());
            ps.setInt(5, book.getBookId());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un libro en la base de datos.
     * 
     * Establece la conexión con la base de datos y ejecuta una
     * sentencia SQL de tipo DELETE en la tabla "books" del
     * identificador entregado, y realiza la actualización en
     * la base de datos.
     * 
     * Utiliza {@link java.sql.PreparedStatement} para evitar inyecciones SQL.
     * 
     * @param bookId identificador único del libro a eliminar.
     */
    public void deleteBook(int bookId) {
        String query = "DELETE FROM books WHERE book_id = ?";
        try (Connection connection = ConnectionMysql.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, bookId);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
