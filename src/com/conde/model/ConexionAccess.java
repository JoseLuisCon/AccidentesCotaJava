package com.conde.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionAccess {

    private static Connection conexion = null;
   

    private ConexionAccess() { } ;
    
    public static Connection conectar() {

        if (conexion == null) {

            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                String surl = "jdbc:ucanaccess://" +"BBDD//Reg-Accidentes-Sector-Navarra.accdb";

                conexion = DriverManager.getConnection(surl);

                return conexion;
            } catch (ClassNotFoundException | SQLException ex) {
                
             Logger.getLogger(ConexionAccess.class.getName()).log(Level.SEVERE, null, ex);

            }

        }
        return conexion;

    }

    public static void desConnection() {

        if (conexion != null) {
            conexion = null;

        }
    }

}
