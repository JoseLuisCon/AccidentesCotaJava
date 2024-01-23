package com.conde.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;

public class ConexionAccess {

    private static Connection conexion = null;


    private ConexionAccess() {};
        
    public static Connection conectar() {

        PropertiesApp prop = new PropertiesApp();
        //Obtenemos la ruta de la base de datos con ayuda de la clase PropertiesApp 
        //Properties almacena la ruta en un archibo config.properties
        File rutaBBDD = new File(prop.obtenerRutaBBDD());

         try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String surl = "jdbc:ucanaccess://" + rutaBBDD;

           conexion = DriverManager.getConnection(surl);

        } catch (ClassNotFoundException | SQLException ex) {

            Logger.getLogger(ConexionAccess.class.getName()).log(Level.SEVERE, null, ex);

        }

        return conexion;

    }


    public static void desConnection() {

        if (conexion != null) {
            conexion = null;

        }
    }

}
