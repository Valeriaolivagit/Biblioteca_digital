package com.bibliotecadigital.entities;

/**
 * Clase que representa la entidad User.
 * 
 * Contiene los atributos que describen a un usuario y que corresponden
 * a los campos de la tabla "users" en la base de datos.
 * 
 * Es utilizada para transferir datos entre las distintas capas
 * de la aplicación (DAO, Services y Controller).
 * 
 * @author Valeria Oliva
 * @version 1.0
 */
public class User {
    /**
     * Identificador único de cada usuario.
     */
    private int id;
    /**
     * Nombre del usuario.
     */
    private String firstName;
    /**
     * Apellido del usuario.
     */
    private String lastName;
    /**
     * Correo electrónico del usuario.
     */
    private String email;
    /**
     * Contraseña del usuario.
     */
    private String password;

    /**
     * Constructor vacío de la entidad User.
     */
    public User() {
    }

    /**
     * Constructor que permite inicializar los atributos de
     * la entidad User.
     * 
     * @param id        identificador único de cada usuario
     * @param firstName nombre del usuario
     * @param lastName  apellido del usuario
     * @param email     correo electrónico del usuario
     * @param password  contraseña del usuario
     */
    public User(int id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    /**
     * Obtiene el identificador del usuario.
     * 
     * @return el identificador del usuario.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del usuario.
     * 
     * @param id identificador único del usuario.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del usuario.
     * 
     * @return nombre del usuario
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Establece el nombre del usuario.
     * 
     * @param firstName nombre del usuario.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Obtiene el apellido del usuario.
     * 
     * @return apellido del usuario
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Establece el apellido del usuario.
     * 
     * @param lastName apellido del usuario.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     * 
     * @return correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     * 
     * @param email correo electrónico del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return contraseña del usuario
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * 
     * @param password contraseña del usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
