package com.conde.model;

import java.io.*;
import java.util.Properties;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PropertiesApp {

    private static final String ARCHIVO_CONFIGURACION = "config.properties";
    private static final String CLAVE_RUTA_BBDD = "ruta_bbdd";
    private static final String NOMBRE_BBDD = "Reg-Accidentes-Sector-Navarra.accdb";
    private String rutaArchivo = NOMBRE_BBDD;

    public String obtenerRutaBBDD() {
        // Leer la configuración desde el archivo
        Properties propiedades = leerConfiguracion();

        // Obtener la ruta de la base de datos desde las propiedades
        String rutaBBDDprop = propiedades.getProperty(CLAVE_RUTA_BBDD);
        // Ademas comprobaremos que la base de datos esté en la ruta almacenada en propiedades

        if (rutaBBDDprop == null || rutaBBDDprop.isEmpty()) {
            // Si la ruta de la base de datos no está configurada, mostrar un JFileChooser para seleccionarla
            String rutaBBDD = seleccionarRutaBBDD();
            
            if (rutaBBDD == null) {
                // Si el usuario cancela la selección, puedes manejarlo según tus necesidades
                System.out.println("No se seleccionó una ruta de base de datos.");
                try {
                    throw new RuntimeException("No se ha configurado correctamente la ruta de la base de datos.");
                } catch (RuntimeException e) {
                    System.out.println("Se produjo una excepción: " + e.getMessage());
                    System.exit(1);
                }
            }

            // Guardar la nueva ruta de la base de datos en el archivo de configuración
            propiedades.setProperty(CLAVE_RUTA_BBDD, rutaBBDD);
            guardarConfiguracion(propiedades);
            return rutaBBDD;
        }
        
        File rutaBBDDSearch = new File (rutaBBDDprop);
        if (!rutaBBDDSearch.exists()){
            String rutaBBDD = seleccionarRutaBBDD();
            if (rutaBBDD != null){
                propiedades.setProperty(CLAVE_RUTA_BBDD, rutaBBDD);
                guardarConfiguracion(propiedades);
                return rutaBBDD;
            }
        
        }
        
        return rutaBBDDprop;
        
       
    }

    private Properties leerConfiguracion() {
        Properties propiedades = new Properties();
        try (InputStream entrada = new FileInputStream(ARCHIVO_CONFIGURACION)) {
            propiedades.load(entrada);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return propiedades;
    }

    private void guardarConfiguracion(Properties propiedades) {
        try (OutputStream salida = new FileOutputStream(ARCHIVO_CONFIGURACION)) {
            propiedades.store(salida, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String seleccionarRutaBBDD() {

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(NOMBRE_BBDD, "accdb");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(filter);
        File directorio = new File(System.getProperty("user.dir"));
        fileChooser.setCurrentDirectory(directorio);
        fileChooser.setDialogTitle("Seleccionar la base de datos de la aplicación: " + NOMBRE_BBDD);

        int seleccion = fileChooser.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath();

        } else {
            JOptionPane.showMessageDialog(null, "No se seleccionó ningún archivo. La aplicación puede no funcionar correctamente.");
            try {
                    throw new RuntimeException("No se ha configurado correctamente la ruta de la base de datos.");
                } catch (RuntimeException e) {
                    System.out.println("Se produjo una excepción: " + e.getMessage());
                    System.exit(1);
                }
        }

        return rutaArchivo;
    }
}
