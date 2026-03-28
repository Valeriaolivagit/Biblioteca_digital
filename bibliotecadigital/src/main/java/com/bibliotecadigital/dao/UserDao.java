package com.bibliotecadigital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bibliotecadigital.entities.User;
import com.bibliotecadigital.utils.ConnectionMysql;

/**
 * Clase DAO encargada de gestionar el acceso a los
 * datos de los usuarios en la base de datos.
 * 
 * Proporciona métodos para realizar operaciones CRUD
 * sobre la entidad User.
 * 
 * Es utilizada por la capa de servicio para obtener y persistir
 * la información de los usuarios.
 * 
 * @author Valeria Oliva
 * @version 1.0
 */
public class UserDao {
    /**
     * Inserta un nuevo usuario en la base de datos.
     * 
     * Establece la conexión con la base de datos y ejecuta una sentencia SQL
     * de tipo INSERT en la tabla users.
     * 
     * Utiliza {@link java.sql.PreparedStatement} para evitar inyecciones SQL.
     * 
     * @param user objeto que contiene los datos del usuario a insertar
     */
    public void insertUser(User user) {
        String query = "INSERT INTO users (firstName, lastName, email, password) VALUES (?, ?, ?, ?)";

        try (Connection connection = ConnectionMysql.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene el listado de usuarios desde la base de datos.
     * 
     * Establece la conexión con la base de datos y ejecuta una sentencia
     * SQL de tipo SELECT sobre la tabla "users".
     * 
     * Los resultados se recorren utilizando
     * {@link ResultSet} para construir la lista de usuarios.
     * 
     * Utiliza {@link java.sql.PreparedStatement} para evitar inyecciones SQL.
     * 
     * @return lista de usuarios obtenidos de la base de datos
     */
    public List<User> listUsers() {
        List<User> users = new ArrayList<>();

        String sqlQuery = "SELECT * FROM users";

        try (Connection connection = ConnectionMysql.getConnection();
                PreparedStatement ps = connection.prepareCall(sqlQuery);) {

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                User user = new User();

                user.setId(result.getInt("id"));
                user.setFirstName(result.getString("firstName"));
                user.setLastName(result.getString("lastName"));
                user.setEmail(result.getString("email"));

                users.add(user);

            }
        } catch (Exception e) {

        }
        return users;
    }

    /**
     * Obtiene el usuario a partir del correo electrónico.
     * 
     * Establece la conexión con la base de datos, ejecuta una
     * sentencia SQL de tipo SELECT sobre la tabla "users"
     * utilizando el email entregado.
     * 
     * Posteriormente valida si la contraseña ingresada coincide
     * con la almacenada en la base de datos.
     * 
     * @param email    correo electrónico del usuario
     * @param password contraseña ingresada por el usuario
     * @return objeto user con los datos del usuario si existe,
     *         o un objeto vacío si no se encuentra coincidencia.
     */
    public User selectUser(String email, String password) {
        User user = null;
        String query = "SELECT * FROM users WHERE email = ?";

        try (Connection connection = ConnectionMysql.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, email);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                user = new User();

                user.setId(result.getInt("user_id"));
                user.setFirstName(result.getString("firstName"));
                user.setLastName(result.getString("lastName"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));

                if (user.getPassword().equals(password)) {
                    return user;
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Valida si el email ya se encuentra registrado.
     * 
     * Establece la conexión con la base de datos, ejecuta una
     * sentencia SQL de tipo SELECT COUNT(*) sobre la tabla "users"
     * para determinar si el email existe.
     * 
     * @param email correo electrónico a validar
     * @return true si el correo electrónico ya se encuentra registrado,
     *         false en caso contrario
     */
    public boolean emailExists(String email) {
        String query = "SELECT COUNT(*) FROM users WHERE email = ?";

        try (Connection connection = ConnectionMysql.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, email);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                return result.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Actualiza el nombre y apellido de un usuario.
     * 
     * Establece la conexión a la base de datos, ejecuta una sentencia
     * SQL de tipo UPDATE en la tabla users utilizando el
     * correo electrónico ingresado.
     * 
     * @param user objeto que contiene los datos actualizados del usuario.
     */
    public void updateUser(User user) {
        String query = "UPDATE users set firstName = ?, lastName = ? WHERE user_id = ?";

        try (Connection connection = ConnectionMysql.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setInt(3, user.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene un usuario a partir del identificador.
     * 
     * Establece la conexión a la base de datos, ejecuta una sentencia
     * SQL de tipo SELECT en la tabla users utilizando el
     * identificador de usuarioy obtiene los resultados utilizando
     * {@link ResultSet}
     * 
     * @param userId identificador único del usuario
     * @return objeto user con los datos del usuario encontrado
     *         en la base de datos.
     */
    public User getUserById(int userId) {
        User user = null;
        String query = "SELECT * FROM users WHERE user_id = ?";

        try (Connection connection = ConnectionMysql.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, userId);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                user = new User();
                user.setId(result.getInt("user_id"));
                user.setFirstName(result.getString("firstName"));
                user.setLastName(result.getString("lastName"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));

                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
