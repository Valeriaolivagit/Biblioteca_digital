package com.bibliotecadigital.entities;

/**
 * Clase que representa la entidad Loan.
 * 
 * Contiene los atributos que describen a un préstamo y que 
 * corresponden a los campos de la tabla "loan" en la base 
 * de datos.
 * 
 * Es utilizada para transferir datos entre las distintas capas
 * de la aplicación (Dao, Services y Controller).
 * 
 * @author Valeria Oliva
 * @version 1.0
 */
public class Loan {
    /**
     * Identificador único de cada préstamo.
     */
    private int loanId;
    /**
     * Identificador único de cada libro.
     */
    private int bookId;
    /**
     * Identificador único de cada usuario.
     */
    private int userId;
    /**
     * Estado de cada préstamo. Por defecto
     * está asignado como "Prestado"
     */
    private String status = "Prestado";

    /**
     * Constructor vacío de la entidad Loan.
     */
    public Loan() {
    }

    /**
     * Constructor que permite inicializar los atributos de
     * la entidad Loan.
     * 
     * @param loanId identificador único de cada préstamo
     * @param bookId identificador único de cada libro
     * @param userId identificador único de cada usuario
     * @param status Estado de cada préstamo. Por defecto esta asignado como
     *               "Prestado"
     */
    public Loan(int loanId, int bookId, int userId, String status) {
        this.loanId = loanId;
        this.bookId = bookId;
        this.userId = userId;
        this.status = status;
    }

    /**
     * Obtiene el identificador del préstamo.
     * 
     * @return el identificador del préstamo
     */
    public int getLoanId() {
        return loanId;
    }

    /**
     * Establece el identificador del préstamo.
     * 
     * @param loanId identificador del préstamo
     */
    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    /**
     * Obtiene el identificador del libro.
     * 
     * @return identificador del libro.
     */
    public int getBookId() {
        return bookId;
    }

    /**
     * Establece el identificador del libro.
     * 
     * @param bookId identificador del libro.
     */
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    /**
     * Obtiene el identificador del usuario.
     * 
     * @return identificador del usuario.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Establece el identificador del usuario.
     * 
     * @param userId identificador del usuario.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Obtiene el estado del préstamo.
     * 
     * @return el estado del préstamo.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Establece el estado del préstamo.
     * 
     * @param status el estado del préstamo.
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
