package com.bibliotecadigital.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase utilitaria encargada de gestionar 
 * la conexión a la base de datos MySQL.
 * 
 * Contiene la configuración necesaria (URL, usuario y contraseña)
 * y expone métodos para obtener conexiones activas.
 * 
 * @author Valeria Oliva
 * @version 1.0
 */
public class ConnectionMysql {
    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/virtual_library";
    private static final String USER_DB = "root";
    private static final String PASSWORD_DB = "";

    /**
     * Constructor privado para evitar la creación
     * de instancias de la clase ConnectionMysql.
     */
    private ConnectionMysql(){
    }

    /**
     * Obtiene la conexión con la base de datos.
     * 
     * Implementa el patrón singleton para reutilizar una única
     * instancia de conexión. Si la conexión no existe o está
     * cerrada, se crea una nueva.
     * 
     * @return objeto Connection con la conexión a la base de datos.
     */
    public static Connection getConnection(){
          try {
            if (connection == null || connection.isClosed()){
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                connection = DriverManager.getConnection(URL, USER_DB, PASSWORD_DB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;  
    }
}
