package com.bibliotecadigital.services;

import java.util.ArrayList;
import java.util.List;

import com.bibliotecadigital.dao.UserDao;
import com.bibliotecadigital.entities.User;
import com.bibliotecadigital.services.dtos.UserDto;

/**
 * Clase servicio encargada de gestionar la lógica de
 * negocio relacionada con la entidad User.
 * 
 * Actúa como intermediario entre el controlador (Servlet)
 * y la capa de acceso a datos (DAO).
 * 
 * Utiliza el DAO para realizar operaciones sobre los usuarios.
 * 
 * @author Valeria Oliva Valdes
 * @version 1.0
 */
public class UserService {
  /**
   * Clase DAO utilizada para acceder y gestionar
   * los datos de los usuarios.
   */
  private UserDao dao = new UserDao();

  /**
   * Registra un nuevo usuario en la base de datos.
   *
   * Recibe los datos del usuario, construye un objeto User
   * y delega la operación de inserción al DAO.
   * 
   * @param firstName nombre del usuario
   * @param lastName  apellido del usuario
   * @param email     correo electrónico del usuario
   * @param password  contraseña del usuario
   */
  public void registerUser(String firstName, String lastName, String email, String password) {
    User user = new User();

    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);
    user.setPassword(password);

    dao.insertUser(user);
  }

  /**
   * Obtiene el listado de los usuarios desde la base
   * de datos.
   * 
   * Convierte las entidades User obtenidas en el DAO
   * en objetos UserDto para su uso en otras capas de
   * la aplicación
   * 
   * @return lista de objetos UserDto
   */
  public List<UserDto> getUsers() {
    List<User> users = dao.listUsers();

    List<UserDto> usersDto = new ArrayList<>();

    for (User u : users) {
      UserDto user = new UserDto(u.getId(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getPassword());

      usersDto.add(user);
    }
    return usersDto;
  }

  /**
   * Obtiene los datos del usuario a través del correo
   * electrónico y la contraseña.
   * 
   * Consulta al DAO correspondiente (UserDao) para verificar
   * si existe un usuario con las credenciales proporcionadas.
   * Si el usuario existe, se crea un objeto UserDto con la
   * información correspondiente..
   * 
   * @param email    correo electrónico del usuario
   * @param password contraseña del usuario
   * @return objeto UserDto si las credenciales son correctas, 
   *         null en caso contrario.
   */
  public UserDto selectUser(String email, String password) {
    User user = dao.selectUser(email, password);

    if (user != null) {
      UserDto dto = new UserDto(
          user.getId(),
          user.getFirstName(),
          user.getLastName(),
          user.getEmail(),
          null);
      return dto;
    }
    return null;
  }

  /**
   * Verifica si un correo electrónico ya se
   * encuentra registrado en la base de datos.
   * 
   * Delega la operación al DAO correspondiente (UserDao)
   * y retorna el valor.
   * 
   * @param email correo electrónico del usuario
   * @return true si el correo ya está registrado,
   *         false en caso contrario
   */
  public boolean emailExists(String email) {
    return dao.emailExists(email);
  }

  /**
   * Actualiza la información de un usuario existente.
   * 
   * Crea una instancia de User, establece los parámetros
   * recibidos y delega la operación de actualización al DAO
   * correspondiente (UserDao)
   * 
   * @param userId    identificador único del usuario
   * @param firstName nombre del usuario
   * @param lastName  apellido del usuario.
   */
  public void updateUser(int userId, String firstName, String lastName) {
    User user = new User();

    user.setId(userId);
    user.setFirstName(firstName);
    user.setLastName(lastName);

    dao.updateUser(user);
  }

  /**
   * Obtiene los datos del usuario a través
   * del identificador único.
   * 
 * Consulta al DAO correspondiente y convierte
 * el resultado en un objeto UserDto.
   * 
   * @param userId identificador único del usuario.
   * @return objeto UserDto si el usuario existe, en caso
   *         contrario retorna null
   */
  public UserDto getUserById(int userId) {
    User user = dao.getUserById(userId);

    if (user != null) {
      return new UserDto(user.getId(), user.getFirstName(),
          user.getLastName(), user.getEmail(), null);
    }
    return null;
  }
}
