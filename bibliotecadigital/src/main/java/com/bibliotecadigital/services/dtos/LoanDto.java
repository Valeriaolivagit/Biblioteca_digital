package com.bibliotecadigital.services.dtos;

/**
 * Clase DTO que representa la información de
 * los préstamos utilizada para transferir datos entre
 * la capa de servicios y otras capas de la aplicación.
 * 
 * A diferencia de la entidad Loan, esta clase es inmutable
 * y solo expone métodos de lectura.
 * 
 * @author Valeria Oliva
 * @version 1.0
 */
public class LoanDto {
    /**
     * Identificador único de cada préstamo.
     */
    private final int loanId;
    /**
     * Identificador único de cada libro.
     */
    private final int bookId;
    /**
     * Nombre o título del libro.
     */
    private final String bookTitle;
    /**
     * Autor del libro.
     */
    private final String bookAuthor;
    /**
     * Descripción breve del libro.
     */
    private final String bookDescription;
    /**
     * Fecha del préstamo.
     */
    private final String loanDate;
    /**
     * Fecha de devolución.
     */
    private final String returnDate;
    /**
     * Identificador único de cada usuario.
     */
    private final int userId;
    /**
     * Estado de cada préstamo.
     */
    private final String status;

    /**
     * Constructor que permite inicializar los atributos del
     * objeto LoanDto.
     * 
     * @param loanId          identificador de cada préstamo
     * @param bookId          identificador de cada libro
     * @param bookTitle       título del libro
     * @param bookAuthor      nombre del autor
     * @param bookDescription descripción del libro
     * @param loanDate        fecha de préstamo
     * @param returnDate      fecha de devolución
     * @param userId          identificador de cada usuario
     * @param status          estado de cada préstamo
     */
    public LoanDto(int loanId, int bookId, String bookTitle, String bookAuthor,
            String bookDescription, String loanDate, String returnDate, int userId,
            String status) {
        this.loanId = loanId;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookDescription = bookDescription;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.userId = userId;
        this.status = status;
    }

    /**
     * Obtiene el identificador del préstamo.
     * 
     * @return identificador del préstamo
     */
    public int getLoanId() {
        return loanId;
    }

    /**
     * Obtiene el identificador del libro.
     * 
     * @return identificador del libro
     */
    public int getBookId() {
        return bookId;
    }

    /**
     * Obtiene el título del libro.
     * 
     * @return el título del libro
     */
    public String getBookTitle() {
        return bookTitle;
    }

    /**
     * Obtiene el nombre del autor del libro.
     * 
     * @return nombre del autor del libro
     */
    public String getBookAuthor() {
        return bookAuthor;
    }

    /**
     * Obtiene la descripción del libro.
     * 
     * @return descripción del libro
     */
    public String getBookDescription() {
        return bookDescription;
    }

    /**
     * Obtiene la fecha del préstamo.
     * 
     * @return fecha del préstamo
     */
    public String getLoanDate() {
        return loanDate;
    }

    /**
     * Obtiene la fecha de devolución.
     * 
     * @return fecha de devolución
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * Obtiene el identificador del usuario.
     * 
     * @return identificador del usuario
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Obtiene el estado del préstamo.
     * 
     * @return estado del préstamo
     */
    public String getStatus() {
        return status;
    }

}
