package com.pasteleria.velazquez.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String HOST_URL = "jdbc:mysql://localhost:3306/?useSSL=false&serverTimezone=UTC";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pasteleriaDB?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se ha encontrado el driver de MySQL.", e);
        }
    }

    public static Connection getConexionServidor() throws SQLException {
        return DriverManager.getConnection(HOST_URL, USER, PASS);
    }

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}