package com.bibliotecadigital.services.dtos;

/**
 * Clase DTO que representa la información de
 * un libro utilizada para transferir datos entre
 * la capa de servicios y otras capas de la aplicación.
 * 
 * A diferencia de la entidad Book, esta clase es inmutable
 * y solo expone métodos de lectura.
 * 
 * @author Valeria Oliva
 * @version 1.0
 */
public class BookDto {
    /**
     * Identificador único que representa a cada libro.
     */
    private final int bookId;
    /**
     * Nombre o título del libro.
     */
    private final String title;
    /**
     * Autor del libro.
     */
    private final String author;
    /**
     * Descripción breve del libro.
     */
    private final String description;
    /**
     * Cantidad disponible del libro en la biblioteca.
     */
    private final int quantity;

    /**
     * Constructor que permite inicializar los atributos de
     * del objeto BookDTO.
     * 
     * @param bookId      identificador único que representa cada libro
     * @param title       título del libro
     * @param author      autor del libro
     * @param description breve descripción del libro
     * @param quantity    cantidad disponible del libro en la biblioteca
     */
    public BookDto(int bookId, String title, String author, String description, int quantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.description = description;
        this.quantity = quantity;
    }

    /**
     * Obtiene el identificador del libro.
     * 
     * @return identificador único que representa cada libro
     */
    public int getBookId() {
        return bookId;
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
     * Obtiene el nombre del autor del libro.
     * 
     * @return nombre del autor del libro.
     */
    public String getAuthor() {
        return author;
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
     * Obtiene la cantidad del libro.
     * 
     * @return cantidad del libro.
     */
    public int getQuantity() {
        return quantity;
    }

}
