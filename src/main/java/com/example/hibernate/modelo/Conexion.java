package com.example.hibernate.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    private static final String user = "root";
    private static final String pass = "";
    private static final String url = "jdbc:mysql://192.168.85.216:3306/donimusic?characterEncoding=utf8";
    public static Connection con;

    public Connection conectar() {
        con = null;

        try {
            con = (Connection) DriverManager.getConnection(url, user, pass);
            if (con != null) {

            }


        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
