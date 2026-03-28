package com.bibliotecadigital.entities;

/**
 * Clase que representa la entidad Book.
 * 
 * Contiene los atributos que describen un libro y que corresponden
 * a los campos de la tabla "books" en la base de datos.
 * 
 * Es utilizada para transferir datos entre las distintas capas
 * de la aplicación (Dao, Services y Controller).
 * 
 * @author Valeria Oliva
 * @version 1.0 
 */
public class Book {
    /**
     * Identificador único que representa a cada libro.
     */
    private int bookId;
    /**
     * Nombre o título del libro.
     */
    private String title;
    /**
     * Autor del libro.
     */
    private String author;
    /**
     * Descripción breve del libro.
     */
    private String description;
    /**
     * Cantidad disponible del libro en la biblioteca.
     */
    private int quantity;

    /**
     * Constructor vacío de la entidad Book.
     */
    public Book() {
    }

    /**
     * Constructor que permite inicializar los atributos de
     * la entidad book.
     * 
     * @param bookId identificador único que representa cada libro
     * @param title título del libro
     * @param author autor del libro
     * @param description breve descripción del libro
     * @param quantity cantidad disponible del libro en la biblioteca
     */
    public Book(int bookId, String title, String author, String description, int quantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.description = description;
        this.quantity = quantity;
    }

    /**
     * Obtiene el identificador del libro.
     * 
     * @return retorna el identificador único que representa cada libro
     */
    public int getBookId() {
        return bookId;
    }

    /**
     * Establece el identificador de cada libro.
     * 
     * @param bookId identificador único que representa cada libro.
     */
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    /**
     * Obtiene el título del libro.
     * 
     * @return título del libro
     */
    public String getTitle() {
        return title;
    }
 
    /**
     * Establece el título del libro.
     * 
     * @param title Nombre o título del libro.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Obtiene el nombre del autor del libro.
     * 
     * @return nombre del autor del libro.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Establece el nombre del autor del libro.
     * 
     * @param author nombre del autor del libro.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Obtiene la descripción del libro.
     * 
     * @return descripción del libro
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción del libro
     * 
     * @param description la descripción del libro.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene la cantidad del libro.
     * 
     * @return la cantidad del libro.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Establece la cantidad del libro.
     * 
     * @param quantity la cantidad del libro
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
