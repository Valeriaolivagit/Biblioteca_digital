package com.bibliotecadigital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bibliotecadigital.entities.Book;
import com.bibliotecadigital.entities.Loan;
import com.bibliotecadigital.services.dtos.LoanDto;
import com.bibliotecadigital.utils.ConnectionMysql;

/**
 * Clase DAO encargado de gestionar el acceso a los datos
 * de los préstamos de libros en la base de datos.
 * 
 * Proporciona métodos para realizar operaciones CRUD
 * sobre la entidad Loan.
 * 
 * Utilizado por la capa de servicio para obtener y persistir
 * la información de los préstamos.
 * 
 * @author Valeria Oliva
 * @version 1.0
 * 
 */
public class RequestDao {
    /**
     * Registra un nuevo préstamo de un libro en la base de datos.
     * 
     * Primero obtiene la información del libro solicitado utilizando
     * el método {@link BookDao#selectBook(int)} para verificar que el
     * libro exista y tenga unidades disponibles.
     * 
     * Posteriormente se inicia una transacción en la base de datos 
     * que realiza las siguientes operaciones:
     * - Valida si el usuario ya tiene solicitado el mismo libro con
     * estado "Prestado".
     * - Inserta un nuevo registro en la tabla loan.
     * - Actualiza la cantidad disponible del libro en la tabla books
     * disminuyendo su stock en 1.
     * 
     * Si todas las operaciones se ejecutan correctamente se realiza
     * el commit de la transacción. En caso de error se ejecuta un
     * rollback.
     *  
     * Utiliza {@link java.sql.PreparedStatement} para evitar inyecciones SQL.
     * 
     * @param loan objeto que contiene los datos del préstamo a registrar.
     * @return      true si el préstamo fue registrado correctamente, false
     *              en caso contrario.
     */
    public boolean addRequest(Loan loan) {
        BookDao bookDao = new BookDao();
        Book book = bookDao.selectBook(loan.getBookId());

        if (book == null || book.getQuantity() == 0) {
            return false;
        }

        String validateQuery = "SELECT COUNT(*) FROM loan WHERE user_id = ? AND book_id = ? AND status = 'Prestado'";
        String insertQuery = "INSERT INTO loan (book_id, user_id, loan_date, status) VALUES (?, ?, CURRENT_DATE, ?)";
        String updateQuery = "UPDATE books SET quantity = quantity - 1 WHERE book_id = ?";

        try (Connection connection = ConnectionMysql.getConnection()) {
            try {
                connection.setAutoCommit(false);

                try (PreparedStatement psValidate = connection.prepareStatement(validateQuery);
                        PreparedStatement psInsert = connection.prepareStatement(insertQuery);
                        PreparedStatement psUpdate = connection.prepareStatement(updateQuery);) {

                    psValidate.setInt(1, loan.getUserId());
                    psValidate.setInt(2, loan.getBookId());

                    try (ResultSet result = psValidate.executeQuery()) {
                        if (result.next() && result.getInt(1) > 0) {
                            return false;
                        }
                    }

                    psInsert.setInt(1, loan.getBookId());
                    psInsert.setInt(2, loan.getUserId());
                    psInsert.setString(3, loan.getStatus());
                    int rows = psInsert.executeUpdate();

                    psUpdate.setInt(1, loan.getBookId());
                    int rowsUp = psUpdate.executeUpdate();

                    if (rows > 0 && rowsUp > 0) {
                        connection.commit();
                        return true;
                    } else {
                        connection.rollback();
                        return false;
                    }
                }

            } catch (Exception e) {
                if (connection != null) {
                    try {
                        connection.rollback();
                    } catch (Exception rbEx) {
                        rbEx.printStackTrace();
                    }
                }
                e.printStackTrace();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene la lista de préstamos activos de un usuario.
     * 
     * Establece la conexión con la base de datos, ejecuta una consulta
     * SQL que obtiene los préstamos registrados en la tabla loan,
     * realizando un INNER JOIN con la tabla books para obtener la
     * información del libro asociado al préstamo.
     * 
     * Los resultados son procesados mediante {@link ResultSet} para construir 
     * la lista de objetos {@link LoanDto}
     * 
     * Utiliza {@link java.sql.PreparedStatement} para evitar inyecciones SQL.
     *  
     * @param userId identificador único del usuario.
     * @return      lista de préstamos activos asociados al usuario.
     *         
     */
    public List<LoanDto> loanList(int userId) {
        List<LoanDto> loans = new ArrayList<>();

        String sqlQuery = "SELECT l.loan_id, l.book_id, l.user_id, "
                + "DATE_FORMAT(l.loan_date, '%d-%m-%Y') AS loan_date, "
                + "DATE_FORMAT(l.return_date, '%d-%m-%Y') AS return_date, "
                + "l.status, b.title, b.author, b.description " +
                "FROM loan l INNER JOIN books b ON l.book_id = b.book_id" +
                " WHERE l.user_id = ? AND l.status = 'Prestado'";

        try (Connection connection = ConnectionMysql.getConnection();
                PreparedStatement ps = connection.prepareStatement(sqlQuery)) {

            ps.setInt(1, userId);
            try (ResultSet result = ps.executeQuery()) {
                while (result.next()) {
                    int loanId = result.getInt("loan_id");
                    int bookId = result.getInt("book_id");
                    String bookTitle = result.getString("title");
                    String bookAuthor = result.getString("author");
                    String loanDate = result.getString("loan_date");
                    String returnDate = result.getString("return_date");
                    String bookDescription = result.getString("description");
                    int idUser = result.getInt("user_id");
                    String status = result.getString("status");

                    LoanDto loan = new LoanDto(loanId, bookId, bookTitle, bookAuthor, bookDescription, loanDate,
                            returnDate, idUser, status);

                    loans.add(loan);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return loans;
    }

    /**
     * Registra la devolución de un libro prestado.
     * 
     * Inicia una transacción en la base de datos para realizar
     * las siguientes operaciones:
     * - Actualiza el estado del préstamo a "Devuelto".
     * - Establece la fecha de devolución con la fecha actual.
     * - Incrementa en 1 la cantidad disponible del libro en la 
     * tabla books.
     * 
     * Si ambas operaciones se ejecutan correctamente se realiza
     * el commit. En caso contrario se ejecuta el rollback para
     * mantener la consistencia de los datos.
     * 
     * Utiliza {@link java.sql.PreparedStatement} para evitar inyecciones SQL.
     * 
     * @param loanId identificador único del préstamo.
     * @param bookId identificador único del libro.
     * @return  true si la devolución se realizó correctamente,
     *          false en caso contrario.
     */
    public boolean returnBook(int loanId, int bookId) {
        String query = "UPDATE loan SET status = 'Devuelto', return_date = CURRENT_DATE" +
                " WHERE loan_id = ? AND status = 'Prestado'";
        String updateStockQuery = "UPDATE books SET quantity = quantity + 1"
                + " WHERE book_id = ?";
        try (Connection connection = ConnectionMysql.getConnection()) {

            try {
                connection.setAutoCommit(false);

                try (PreparedStatement ps = connection.prepareStatement(query);
                        PreparedStatement psUp = connection.prepareStatement(updateStockQuery)) {
                    ps.setInt(1, loanId);
                    int rows = ps.executeUpdate();

                    psUp.setInt(1, bookId);
                    int rowsUp = psUp.executeUpdate();

                    if (rows > 0 && rowsUp > 0) {
                        connection.commit();
                        return true;
                    } else {
                        connection.rollback();
                        return false;
                    }

                }
            } catch (Exception e) {
                if (connection != null) {
                    try {
                        connection.rollback();
                    } catch (Exception rbEx) {
                        rbEx.printStackTrace();
                    }
                }
                e.printStackTrace();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}