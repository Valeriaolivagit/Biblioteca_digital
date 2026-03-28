package com.bibliotecadigital.services.dtos;

/**
 * Clase DTO que representa la información de 
 * un usuario utilizada para transferir datos entre 
 * la capa de servicios y otras capas de la aplicación.
 * 
 * A diferencia de la entidad User, esta clase es inmutable
 * y solo expone métodos de lectura.
 * 
 * @author Valeria Oliva
 * @version 1.0
 */
public class UserDto {
    /**
     * Identificador único de cada usuario.
     */
    private final int id;
    /**
     * Nombre del usuario.
     */
    private final String firstName;
    /**
     * Apellido del usuario.
     */
    private final String lastName;
    /**
     * Correo electrónico del usuario.
     */
    private final String email;
    /**
     * Contraseña del usuario.
     */
    private final String password;

    /**
     * Constructor que permite inicializar los atributos de
     * del objeto UserDTO.
     * 
     * @param id        identificador único de cada usuario
     * @param firstName nombre del usuario
     * @param lastName  apellido del usuario
     * @param email     correo electrónico del usuario
     * @param password  contraseña del usuario
     */
    public UserDto(int id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    /**
     * Obtiene el identificador del usuario.
     * 
     * @return identificador del usuario.
     */
    public int getId() {
        return id;
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
     * Obtiene el apellido del usuario.
     * 
     * @return apellido del usuario
     */
    public String getLastName() {
        return lastName;
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
     * Obtiene la contraseña del usuario.
     * 
     * @return contraseña del usuario
     */
    public String getPassword() {
        return password;
    }

}
