package com.conde.model.JDBC;

import com.conde.model.ConexionAccess;
import com.conde.model.Accidente;
import com.conde.model.Persona;
import com.conde.model.Vehiculo;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Accidentes_JDBC {

    public Accidentes_JDBC() {
    }

    public ArrayList<Accidente> getListAccidents(String sql) {
//        String sql = "SELECT TOP 100 * FROM Accidentes ORDER BY Fecha";
        try {
            conexion = ConexionAccess.conectar();
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Accidente acc = new Accidente();
                acc.setNum_Accidente(rs.getInt("Id"));
                //Fecha
                Date fecha = rs.getDate("Fecha");
                acc.setFecha(new SimpleDateFormat("dd/MM/YYYY").format(fecha));
                //Hora
                Time hora = rs.getTime("Hora");
                acc.setHora(new SimpleDateFormat("HH:mm").format(hora));
                acc.setCarretera(rs.getString("Carretera"));
                acc.setKilometro(rs.getString("Kilometro"));
                acc.setPatrulla(rs.getString("Patrulla"));
                acc.setNum_Diligencias(rs.getInt("Num_Diligencias"));
                acc.setTipo_Siniestro(rs.getInt("Tipo_Accidente"));
                acc.setZona_Atestados(rs.getString("Zona_Atestados"));
                acc.setDescripcion(rs.getString("Descripcion"));

                listAccidents.add(acc);

            }

            rs.close();
            st.close();
            ConexionAccess.desConnection();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return listAccidents;
    }

    public void setListAccidents(ArrayList<Accidente> listAccidents) {
        this.listAccidents = listAccidents;
    }

    public Accidente getAccidentById(int Id) {
        String sql = "SELECT * FROM Accidentes WHERE Id =" + Id;
        Accidente acc = new Accidente();
        try {
            conexion = ConexionAccess.conectar();
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(sql);

            if (rs.next()) {

                acc.setNum_Accidente(rs.getInt("Id"));
                //Fecha
                Date fecha = rs.getDate("Fecha");
                acc.setFecha(new SimpleDateFormat("dd/MM/YYYY").format(fecha));
                //Hora
                Time hora = rs.getTime("Hora");
                acc.setHora(new SimpleDateFormat("HH:mm").format(hora));
                acc.setCarretera(rs.getString("Carretera"));
                acc.setKilometro(rs.getString("Kilometro"));
                acc.setPatrulla(rs.getString("Patrulla"));
                acc.setNum_Diligencias(rs.getInt("Num_Diligencias"));
                acc.setTipo_Siniestro(rs.getInt("Tipo_Accidente"));
                acc.setZona_Atestados(rs.getString("Zona_Atestados"));
                acc.setDescripcion(rs.getString("Descripcion"));
                rs.close();
                st.close();
                conexion.close();
                ConexionAccess.desConnection();
            } else {
                rs.close();
                st.close();
                conexion.close();
                ConexionAccess.desConnection();
                return null;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return acc;
    }

    public String getTipoAccidenteById(int Id) {

        String sql = "SELECT TIPO FROM TIPO_SINIESTRO WHERE Id =" + Id;
        String tipo_Accidente = "";

        try {
            conexion = ConexionAccess.conectar();
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(sql);

            if (rs.next()) {

                tipo_Accidente = rs.getString("TIPO");

            }
            rs.close();
            st.close();
            conexion.close();
            ConexionAccess.desConnection();
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return tipo_Accidente;
    }

    public int getTipoAccidenteByTYPE(Object selectedItem) {

        String sql = "SELECT Id FROM TIPO_SINIESTRO WHERE  TIPO='" + selectedItem + "'";

        int tipo_Accidente = -1;

        try {
            conexion = ConexionAccess.conectar();
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(sql);

            if (rs.next()) {

                tipo_Accidente = rs.getInt("Id");

            }
            rs.close();
            st.close();
            conexion.close();
            ConexionAccess.desConnection();

            return tipo_Accidente;

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return tipo_Accidente;

    }

    public ArrayList<String> getTiposAccidentes() {
        ArrayList<String> tipoAccidentes = new ArrayList<>();

        String sql = "SELECT * FROM TIPO_SINIESTRO";

        try {
            conexion = ConexionAccess.conectar();
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(sql);

            while (rs.next()) {

                tipoAccidentes.add(rs.getString("TIPO"));

            }
            rs.close();
            st.close();
            conexion.close();
            ConexionAccess.desConnection();
            return tipoAccidentes;

        } catch (Exception e) {
            System.out.println("Error en la carga de vehículos." + e.getMessage());
        }

        return null;

    }

//    public ArrayList<Vehiculo> getVehiculoInAccidentById(int index) {
//        ArrayList<Vehiculo> listVeh = new ArrayList<>();
//        String sql = "SELECT * FROM Vehiculos WHERE NUM_ACCI=" + index;
//
//        try {
//            conexion = ConexionAccess.conectar();
//            st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            rs = st.executeQuery(sql);
//
//            while (rs.next()) {
//                Vehiculo veh = new Vehiculo(
//                        rs.getInt("Id"),
//                        rs.getInt("NUM_ACCI"),
//                        rs.getString("MATRICULA"),
//                        rs.getString("MARCA"),
//                        rs.getString("MODELO"),
//                        rs.getString("GESTION"),
//                        rs.getString("OBSERVACIONES")
//                );
//
//                listVeh.add(veh);
//
//            }
//            rs.close();
//            st.close();
//            conexion.close();
//            ConexionAccess.desConnection();
//            return listVeh;
//
//        } catch (Exception e) {
//            System.out.println("Error en la carga de vehículos." + e.getMessage());
//        }
//
//        return null;
//
//    }
    public int getNumDiligencias(String equipo, String fecha) {

        try {

            String sql = "SELECT Max(Num_Diligencias) AS NUM FROM Accidentes WHERE Zona_Atestados='" + equipo + "' AND Year(FECHA) = YEAR(#" + fecha + "#)";
            Integer num_dilig = null;
            try {
                conexion = ConexionAccess.conectar();
                st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs = st.executeQuery(sql);

                if (rs.next()) {

                    num_dilig = rs.getInt("NUM");
                }

                rs.close();
                st.close();

                conexion.close();
                ConexionAccess.desConnection();
                return num_dilig;

            } catch (Exception e) {
                System.out.println("Error en la búsqueda del número de diligencias." + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return 1;

    }

    public ArrayList<Persona> getPersonasInAccidenteByIdAccident(int index) {
        ArrayList<Persona> listPer = new ArrayList<>();
        String sql = "SELECT * FROM PERSONAS WHERE Num_Accidente=" + index;

        try {
            conexion = ConexionAccess.conectar();
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Persona per = new Persona(
                        rs.getInt("Id"),
                        rs.getInt("Num_Accidente"),
                        rs.getInt("Num_Vehiculo"),
                        rs.getString("Documento"),
                        rs.getString("Tipo_Persona"),
                        rs.getString("Resultado"),
                        rs.getBoolean("Trasladado"),
                        rs.getString("Lugar_Traslado"),
                        rs.getBoolean("Alcoholemia"),
                        rs.getBoolean("Alcoholemia_Positiva"),
                        rs.getBoolean("Drogas"),
                        rs.getBoolean("Drogas_Positiva"),
                        rs.getString("Observaciones")
                );

                listPer.add(per);

            }

            rs.close();
            st.close();
            conexion.close();
            ConexionAccess.desConnection();
            return listPer;

        } catch (Exception e) {

            System.out.println("Error en la carga de personas." + e.getMessage());
        }

        return null;
    }

    public String getMatriculaById(int Id_Vehiculo) throws SQLException {
        String sql = "SELECT MATRICULA FROM Vehiculos WHERE Id =" + Id_Vehiculo;
        String matriculaStr = null;
        conexion = ConexionAccess.conectar();
        st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = st.executeQuery(sql);

        if (rs.next()) {

            matriculaStr = rs.getString("MATRICULA");

        }
        rs.close();
        st.close();
        conexion.close();
        ConexionAccess.desConnection();
        return matriculaStr;
    }

    public ArrayList<String> getCarreteras() {
        ArrayList<String> listadoCarreteras = new ArrayList<>();

        String sql = "SELECT DENOMINACION FROM CARRETERAS ORDER BY DENOMINACION";
        try {
            conexion = ConexionAccess.conectar();
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(sql);

            while (rs.next()) {

                listadoCarreteras.add(rs.getString("DENOMINACION"));

            }
            rs.close();
            st.close();
            conexion.close();
            ConexionAccess.desConnection();
            return listadoCarreteras;

        } catch (Exception e) {
            System.out.println("Error en la carga del tipo de personas." + e.getMessage());
        }

        return null;
    }
    
    public void getRangoAnyos(List<String> years) {
        
        String sql = "SELECT DISTINCT Year(Fecha) AS Anio FROM Accidentes ORDER BY Anio ASC";
        try {
            conexion = ConexionAccess.conectar();
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(sql);
            
            years.add("");
            while (rs.next()) {
                years.add(rs.getString("Anio"));
            }
            rs.close();
            st.close();
            conexion.close();
            ConexionAccess.desConnection();

        } catch (Exception e) {
            System.out.println("Error en la carga del tipo de personas." + e.getMessage());
        }

    }

    public ArrayList<String> getTiposPersonas() {

        ArrayList<String> tipoPersonas = new ArrayList<>();

        String sql = "SELECT TIPO_PERSONA FROM TIPO_PERSONA";
        try {
            conexion = ConexionAccess.conectar();
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(sql);

            while (rs.next()) {

                tipoPersonas.add(rs.getString("TIPO_PERSONA"));

            }
            rs.close();
            st.close();
            conexion.close();
            ConexionAccess.desConnection();
            return tipoPersonas;

        } catch (Exception e) {
            System.out.println("Error en la carga del tipo de personas." + e.getMessage());
        }

        return null;

    }

    public int getVehiculoByMatriculaAndNumAccidente(Object matricula, int num_accidente) {

        String sql = "SELECT * FROM Vehiculos WHERE MATRICULA=? AND NUM_ACCI=?";

        int IdVehiculo = -1;

        try {
            conexion = ConexionAccess.conectar();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, (String) matricula);
            ps.setInt(2, num_accidente);

            rs = ps.executeQuery();

            if (rs.next()) {

                IdVehiculo = rs.getInt("Id");

            }
            rs.close();
            ps.close();
            conexion = null;
            ConexionAccess.desConnection();

        } catch (SQLException e) {
            Logger.getLogger(Accidentes_JDBC.class.getName()).log(Level.SEVERE, null, e);
        }

        return IdVehiculo;

    }

    public ArrayList<Vehiculo> getVehiculosInAccident(int idAccidente) {

        ArrayList<Vehiculo> listVehiculos = new ArrayList<>();

        String sql = "SELECT * FROM Vehiculos WHERE NUM_ACCI=?";

        try {
            conexion = ConexionAccess.conectar();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idAccidente);

            rs = ps.executeQuery();

            while (rs.next()) {
                Vehiculo veh = new Vehiculo();
                veh.setId_Vehiculo(rs.getInt("Id"));
                veh.setNum_Accidente(idAccidente);
                veh.setMatricula((String) rs.getObject("MATRICULA"));
                veh.setMarca((String) rs.getObject("MARCA"));
                veh.setModelo((String) rs.getObject("MODELO"));
                veh.setGestion((String) rs.getObject("GESTION"));
                veh.setObservaciones((String) rs.getObject("OBSERVACIONES"));

                listVehiculos.add(veh);
            }
            rs.close();
            ps.close();
            conexion = null;
            ConexionAccess.desConnection();
            return listVehiculos;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listVehiculos;

    }

    public int AddNuevoAccidente(Accidente newAccidente) {
        String sql = "INSERT INTO Accidentes (Id,Fecha,Hora,Carretera,Kilometro,Patrulla,Num_Diligencias, Tipo_Accidente, Zona_Atestados, Descripcion) VALUES (?,?,?,?,?,?,?,?,?,?)";

        Integer numAccidente = 0;

        try {
            conexion = ConexionAccess.conectar();
            ps = conexion.prepareStatement(sql);
            ps.setNull(1, 0);
            // Fecha
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date fechaUtil = sdf.parse(newAccidente.getFecha());
            java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
            ps.setDate(2, fechaSql);
            //Hora
            SimpleDateFormat stf = new SimpleDateFormat("HH:mm");
            java.util.Date horaUtil = stf.parse(newAccidente.getHora());
            java.sql.Time horaSql = new java.sql.Time(horaUtil.getTime());
            ps.setTime(3, horaSql);

            ps.setString(4, newAccidente.getCarretera());
            ps.setString(5, newAccidente.getKilometro());
            ps.setString(6, newAccidente.getPatrulla());
            ps.setInt(7, newAccidente.getNum_Diligencias());
            ps.setInt(8, newAccidente.getTipo_Siniestro());
            ps.setString(9, newAccidente.getZona_Atestados());
            ps.setString(10, newAccidente.getDescripcion());

            numAccidente = ps.executeUpdate();

            ps.close();

            sql = "SELECT * FROM Accidentes WHERE Fecha=? AND Hora=? AND Zona_Atestados=?";

            ps = conexion.prepareStatement(sql);

            ps.setDate(1, fechaSql);
            ps.setTime(2, horaSql);
            ps.setString(3, newAccidente.getZona_Atestados());

            rs = ps.executeQuery();

            if (rs.next()) {
                numAccidente = rs.getInt("Id");
            }

            ps.close();

            conexion.close();
            ConexionAccess.desConnection();
            return numAccidente;

        } catch (SQLException | ParseException ex) {
            Logger.getLogger(Accidentes_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numAccidente;

    }

    public int modificarAccidenteById(Accidente newAccidente) {
        String sql = "UPDATE Accidentes SET Fecha=?,Hora=?,Carretera=?,Kilometro=?,Patrulla=?, Tipo_Accidente=?, Descripcion=? WHERE Id=?";

        Integer numAccidente = 0;

        try {
            conexion = ConexionAccess.conectar();
            ps = conexion.prepareStatement(sql);

            // Fecha
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date fechaUtil = sdf.parse(newAccidente.getFecha());
            java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
            ps.setDate(1, fechaSql);
            //Hora
            SimpleDateFormat stf = new SimpleDateFormat("HH:mm");
            java.util.Date horaUtil = stf.parse(newAccidente.getHora());
            java.sql.Time horaSql = new java.sql.Time(horaUtil.getTime());
            ps.setTime(2, horaSql);

            ps.setString(3, newAccidente.getCarretera());
            ps.setString(4, newAccidente.getKilometro());
            ps.setString(5, newAccidente.getPatrulla());
            ps.setInt(6, newAccidente.getTipo_Siniestro());
            ps.setString(7, newAccidente.getDescripcion());
            ps.setInt(8, newAccidente.getNum_Accidente());

            numAccidente = ps.executeUpdate();

            ps.close();

            conexion.close();
            ConexionAccess.desConnection();
            return numAccidente;

        } catch (SQLException | ParseException ex) {
            Logger.getLogger(Accidentes_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numAccidente;

    }

    public void modificaListaVehiculos(ArrayList<Vehiculo> listaVehiculos, int idAccidente) {

        conexion = ConexionAccess.conectar();
        try {
            conexion.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(Accidentes_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<Vehiculo> listaVehiculosBBDD = new ArrayList<>();
        ArrayList<Vehiculo> listaVehiculosModificados = new ArrayList<>();

        listaVehiculosBBDD = getVehiculosInAccident(idAccidente);

        if (listaVehiculosBBDD.size() > listaVehiculos.size()) {
            //si es mayor la lista de vehiculos en la base de datos significa que hemos elimado uno

            for (int cont = 0; cont < listaVehiculosBBDD.size(); cont++) {
                boolean encontrado = false;
                for (int cont2 = 0; cont2 < listaVehiculos.size(); cont2++) {
                    if (listaVehiculosBBDD.get(cont).getId_Vehiculo() == listaVehiculos.get(cont2).getId_Vehiculo()) {
                        encontrado = true;
                    }
                }
                if (!encontrado) {
                    //eliminamos el vehiculo de la base de datos
                    deleteVehiculoById(listaVehiculosBBDD.get(cont).getId_Vehiculo());

                }

            }

            listaVehiculosBBDD = getVehiculosInAccident(idAccidente);

        } else if (listaVehiculos.size() > listaVehiculosBBDD.size()) {
            // Si la lista nueva tiene más vehículos que los que había en la base de datos tenemos que añadirlos primero
            for (int cont = 0; cont < listaVehiculos.size(); cont++) {
                boolean encontrado = false;
                for (int cont2 = 0; cont2 < listaVehiculosBBDD.size(); cont2++) {
                    if (listaVehiculos.get(cont).getId_Vehiculo() == listaVehiculosBBDD.get(cont2).getId_Vehiculo()) {
                        encontrado = true;
                    }
                }
                if (!encontrado) {
                    //eliminamos el vehiculo de la base de datos
                    addVehiculoById(listaVehiculos.get(cont));
                }
            }
            listaVehiculosBBDD = getVehiculosInAccident(idAccidente);
        }

        //La lista de vehículos de la base de datos y la local ya coinciden en número así que ahora comprobamos si se han hecho cambios en el resto
        //ya que si se han eliminado serán iguales y si se han quitado ya no figuran en la base de datos.
        listaVehiculosModificados = comparaListadoVehiculos(listaVehiculosBBDD, listaVehiculos);

        if (!listaVehiculosModificados.isEmpty()) {

            listaVehiculosModificados.forEach((veh) -> {

                modificarVehiculo(veh);

            });

        }

        try {
            if (conexion == null) {
                conexion = ConexionAccess.conectar();
            };
            conexion.commit();
            conexion.close();
        } catch (SQLException ex) {
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(Accidentes_JDBC.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(Accidentes_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ArrayList<Persona> comparaListadoPersonas(ArrayList<Persona> old, ArrayList<Persona> newLista) {
        // Convertir ArrayList a conjuntos
        HashSet<Persona> set1 = new HashSet<>(old);

        HashSet<Persona> set2 = new HashSet<>(newLista);

        // Copiar set1 para retener sus elementos originales
        HashSet<Persona> set1Copy = new HashSet<>(set1);

        // Eliminar elementos comunes
        set1.removeAll(set2);

        // Eliminar elementos comunes del conjunto copia  do
        set2.removeAll(set1Copy);

        // Si ambos conjuntos están vacíos, los ArrayList son iguales
        if (set1.isEmpty() && set2.isEmpty()) {
            ArrayList<Persona> setArray = new ArrayList<Persona>();
            return setArray;
        } else {

            ArrayList<Persona> setArray = new ArrayList<Persona>(set2);
            return setArray;
        }

    }

    private ArrayList<Vehiculo> comparaListadoVehiculos(ArrayList<Vehiculo> old, ArrayList<Vehiculo> newLista) {

        // Convertir ArrayList a conjuntos
        HashSet<Vehiculo> set1 = new HashSet<>(old);

        HashSet<Vehiculo> set2 = new HashSet<>(newLista);

        // Copiar set1 para retener sus elementos originales
        HashSet<Vehiculo> set1Copy = new HashSet<>(set1);

        // Eliminar elementos comunes
        set1.removeAll(set2);

        // Eliminar elementos comunes del conjunto copia  do
        set2.removeAll(set1Copy);

        // Si ambos conjuntos están vacíos, los ArrayList son iguales
        if (set1.isEmpty() && set2.isEmpty()) {
            ArrayList<Vehiculo> setArray = new ArrayList<Vehiculo>();
            return setArray;
        } else {

            ArrayList<Vehiculo> setArray = new ArrayList<Vehiculo>(set2);
            return setArray;
        }
    }

    public void modificarVehiculo(Vehiculo veh) {

        String sql = "UPDATE Vehiculos SET MATRICULA=?,MARCA=?,MODELO=?,GESTION=?,OBSERVACIONES=? WHERE Id=?";

        try {
            conexion = ConexionAccess.conectar();
            ps = conexion.prepareStatement(sql);

            ps.setString(1, veh.getMatricula());
            ps.setString(2, veh.getMarca());
            ps.setString(3, veh.getModelo());
            ps.setString(4, veh.getGestion());
            ps.setString(5, veh.getObservaciones());
            ps.setInt(6, veh.getId_Vehiculo());

            ps.executeUpdate();

            ps.close();

        } catch (SQLException e) {
        }

    }

    public void modificarPersona(Persona per) {
        String sql = "UPDATE Personas SET Num_Vehiculo=?, Documento=?, Tipo_Persona=?,Resultado=?,Trasladado=?,Lugar_traslado=?,Alcoholemia=?,Alcoholemia_Positiva=?,Drogas=?,Drogas_Positiva=?,Observaciones=? WHERE Id=?";

        try {
            conexion = ConexionAccess.conectar();
            ps = conexion.prepareStatement(sql);

            ps.setInt(1, per.getId_Vehiculo());
            ps.setString(2, per.getDocumento());
            ps.setString(3, per.getTipo_persona());
            ps.setString(4, per.getResultado());
            ps.setBoolean(5, per.getTrasladado());
            ps.setString(6, per.getLugar_traslado());
            ps.setBoolean(7, per.getPrueba_alcoholemia());
            ps.setBoolean(8, per.getAlcoholemia_positiva());
            ps.setBoolean(9, per.getPrueba_drogas());
            ps.setBoolean(10, per.getDrogas_positiva());
            ps.setString(11, per.getObservaciones());
            ps.setInt(12, per.getId_Persona());

            ps.executeUpdate();

            ps.close();

        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+ " "+e.getMessage());
            
        }

    }

    public void modificaListaPersonas(ArrayList<Persona> listaPersonas, int idAccidente) {
        conexion = ConexionAccess.conectar();
        try {
            conexion.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(Accidentes_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<Persona> listaPersonaBBDD = new ArrayList<>();
        ArrayList<Persona> listaPersonasModificados = new ArrayList<>();

        listaPersonaBBDD = getPersonasInAccidenteByIdAccident(idAccidente);

        if (listaPersonaBBDD.size() > listaPersonas.size()) {
            //si es mayor la lista de vehiculos en la base de datos significa que hemos elimado uno

            for (int cont = 0; cont < listaPersonaBBDD.size(); cont++) {
                boolean encontrado = false;
                for (int cont2 = 0; cont2 < listaPersonas.size(); cont2++) {
                    if (listaPersonaBBDD.get(cont).getId_Persona() == listaPersonas.get(cont2).getId_Persona()) {
                        encontrado = true;
                    }
                }
                if (!encontrado) {
                    //eliminamos la persona de la base de datos
                    deletePersonasById(listaPersonaBBDD.get(cont).getId_Persona());
                }

            }

            listaPersonaBBDD = getPersonasInAccidenteByIdAccident(idAccidente);

        } else if (listaPersonas.size() > listaPersonaBBDD.size()) {
            // Si la lista nueva tiene más vehículos que los que había en la base de datos tenemos que añadirlos primero
            for (int cont = 0; cont < listaPersonas.size(); cont++) {
                boolean encontrado = false;
                for (int cont2 = 0; cont2 < listaPersonaBBDD.size(); cont2++) {
                    if (listaPersonas.get(cont).getId_Persona() == listaPersonaBBDD.get(cont2).getId_Persona()) {
                        encontrado = true;
                    }
                }
                if (!encontrado) {
                    //eliminamos la persona de la base de datos

                    addPersonaById(listaPersonas.get(cont));
                }
            }
            listaPersonaBBDD = getPersonasInAccidenteByIdAccident(idAccidente);
        }

        //La lista de vehículos de la base de datos y la local ya coinciden en número así que ahora comprobamos si se han hecho cambios en el resto
        //ya que si se han eliminado serán iguales y si se han quitado ya no figuran en la base de datos.
        listaPersonasModificados = comparaListadoPersonas(listaPersonaBBDD, listaPersonas);

        if (!listaPersonasModificados.isEmpty()) {

            listaPersonasModificados.forEach((per) -> {

                modificarPersona(per);

            });

        }

        try {
            if (conexion == null) {
                conexion = ConexionAccess.conectar();
            };
            conexion.commit();
            conexion.close();
        } catch (SQLException ex) {
            try {
                if(conexion==null){
                    conexion= ConexionAccess.conectar();
                }
                conexion.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(Accidentes_JDBC.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(Accidentes_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addListadoVehiculos(ArrayList<Vehiculo> listaVehiculos) {

        String sql = "INSERT INTO Vehiculos (Id,NUM_ACCI,MATRICULA,MARCA,MODELO,GESTION,OBSERVACIONES) VALUES (?,?,?,?,?,?,?)";

        try {
            conexion = ConexionAccess.conectar();
            ps = conexion.prepareStatement(sql);
            listaVehiculos.forEach((veh) -> {
                try {
                    //TODO CUANDO RECORRO LA LISTA NUEVA, COMPRUEBA SI EL COCHE EXISTE Y MODIFICO LOS DATOS (UPDATE), SI NO, TENGO QUE CREAR UNO (INSERT) M

                    ps.setNull(1, 0);
                    ps.setInt(2, veh.getNum_Accidente());
                    ps.setString(3, veh.getMatricula());
                    ps.setString(4, veh.getMarca());
                    ps.setString(5, veh.getModelo());
                    ps.setString(6, veh.getGestion());
                    ps.setString(7, veh.getObservaciones());
                    ps.addBatch();

                } catch (SQLException ex) {
                    Logger.getLogger(Accidentes_JDBC.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
            ps.executeBatch();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(Accidentes_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addListadoPersonas(ArrayList<Persona> listaPersonas) {

        String sql = "INSERT INTO Personas (Id,Num_Accidente,Num_Vehiculo,Documento,Tipo_Persona,Resultado,Trasladado,Lugar_Traslado,Alcoholemia,Alcoholemia_Positiva,Drogas,Drogas_Positiva,Observaciones) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

       
        try {
             conexion = ConexionAccess.conectar();
            ps = conexion.prepareStatement(sql);
            listaPersonas.forEach((per) -> {
                try {
                    ps.setNull(1, 0);
                    ps.setInt(2, per.getId_Accidente());
                    ps.setInt(3, per.getId_Vehiculo());
                    ps.setString(4, per.getDocumento());
                    ps.setString(5, per.getTipo_persona());
                    ps.setString(6, per.getResultado());
                    ps.setBoolean(7, per.getTrasladado());
                    ps.setString(8, per.getLugar_traslado());
                    ps.setBoolean(9, per.getPrueba_alcoholemia());
                    ps.setBoolean(10, per.getAlcoholemia_positiva());
                    ps.setBoolean(11, per.getPrueba_drogas());
                    ps.setBoolean(12, per.getDrogas_positiva());
                    ps.setString(13, per.getObservaciones());
                    ps.addBatch();

                } catch (SQLException ex) {
                    Logger.getLogger(Accidentes_JDBC.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
            ps.executeBatch();
            ps.close();
            conexion = null;
            ConexionAccess.desConnection();

        } catch (SQLException ex) {
            Logger.getLogger(Accidentes_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void AddNuevoLugarAccidente(String nuevoLugar) {
        String sql = "INSERT INTO CARRETERAS (DENOMINACION) VALUES (?)";

        try {
            conexion = ConexionAccess.conectar();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nuevoLugar);
            ps.executeUpdate();

            ps.close();
            conexion.close();
            ConexionAccess.desConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Accidentes_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addPersonaById(Persona per) {
        String sql = "INSERT INTO Personas (Id,Num_Accidente,Num_Vehiculo,Documento,Tipo_Persona,Resultado,Trasladado,Lugar_Traslado,Alcoholemia,Alcoholemia_Positiva,Drogas,Drogas_Positiva,Observaciones) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        
        try {
            conexion = ConexionAccess.conectar();
            ps = conexion.prepareStatement(sql);

            try {
                ps.setNull(1, 0);
                ps.setInt(2, per.getId_Accidente());
                ps.setInt(3, per.getId_Vehiculo());
                ps.setString(4, per.getDocumento());
                ps.setString(5, per.getTipo_persona());
                ps.setString(6, per.getResultado());
                ps.setBoolean(7, per.getTrasladado());
                ps.setString(8, per.getLugar_traslado());
                ps.setBoolean(9, per.getPrueba_alcoholemia());
                ps.setBoolean(10, per.getAlcoholemia_positiva());
                ps.setBoolean(11, per.getPrueba_drogas());
                ps.setBoolean(12, per.getDrogas_positiva());
                ps.setString(13, per.getObservaciones());

            } catch (SQLException ex) {
                Logger.getLogger(Accidentes_JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }

            ps.executeUpdate();
            ps.close();
            conexion = null;
            ConexionAccess.desConnection();

        } catch (SQLException ex) {
            Logger.getLogger(Accidentes_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addVehiculoById(Vehiculo newVehiculo) {

        String sql = "INSERT INTO Vehiculos (Id,NUM_ACCI,MATRICULA,MARCA,MODELO,GESTION,OBSERVACIONES) VALUES (?,?,?,?,?,?,?)";

        try {
            conexion = ConexionAccess.conectar();
            ps = conexion.prepareStatement(sql);

            try {
                //TODO CUANDO RECORRO LA LISTA NUEVA, COMPRUEBA SI EL COCHE EXISTE Y MODIFICO LOS DATOS (UPDATE), SI NO, TENGO QUE CREAR UNO (INSERT) M

                ps.setNull(1, 0);
                ps.setInt(2, newVehiculo.getNum_Accidente());
                ps.setString(3, newVehiculo.getMatricula());
                ps.setString(4, newVehiculo.getMarca());
                ps.setString(5, newVehiculo.getModelo());
                ps.setString(6, newVehiculo.getGestion());
                ps.setString(7, newVehiculo.getObservaciones());

            } catch (SQLException ex) {
                Logger.getLogger(Accidentes_JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }

            int executeUpdate = ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(Accidentes_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void AddNuevoTipoDelictivo(String nuevoTipo) {
        String sql = "INSERT INTO TIPO_SINIESTRO (TIPO) VALUES (?)";

        try {
            conexion = ConexionAccess.conectar();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nuevoTipo);
            ps.executeUpdate();
            ps.close();
            conexion.close();
            ConexionAccess.desConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Accidentes_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteVehiculosByIdAccidente(int Id_Accdient) throws SQLException {

        String sql = "Delete FROM Vehiculos WHERE NUM_ACCI=?";
        conexion = ConexionAccess.conectar();
        ps = conexion.prepareStatement(sql);
        ps.setInt(1, Id_Accdient);
        ps.executeUpdate();
        ps.close();

    }

    public void deleteVehiculoById(int Id_Vehiculo) {

        String sql = "Delete FROM Vehiculos WHERE Id=?";
        conexion = ConexionAccess.conectar();

        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, Id_Vehiculo);
            ps.executeUpdate();
            ps.close();
            //Ahora borramos las personas que estén relacionadas con el vehículo
            deletePersonaInVehicle(Id_Vehiculo);

        } catch (SQLException ex) {
            Logger.getLogger(Accidentes_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deletePersonaInVehicle(int id_vehiculo) {
        String sql = "Delete FROM Personas WHERE Num_Vehiculo=?";
        conexion = ConexionAccess.conectar();
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id_vehiculo);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Accidentes_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deletePersonasById(int Id_Persona) {
        String sql = "Delete FROM Personas WHERE Id=?";
        conexion = ConexionAccess.conectar();
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, Id_Persona);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Accidentes_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deletePersonasByIdAccidente(int Id_Accdient) throws SQLException {

        String sql = "Delete FROM Personas WHERE Num_Accidente=?";
        conexion = ConexionAccess.conectar();
        ps = conexion.prepareStatement(sql);
        ps.setInt(1, Id_Accdient);
        ps.executeUpdate();
        ps.close();

    }

    public void deleteAccidenteById(int Id_Accidente) throws SQLException {

        //iniciamos transacción de borrado de accidentes
        try {
            conexion = ConexionAccess.conectar();
            conexion.setAutoCommit(false);
            deleteVehiculosByIdAccidente(Id_Accidente);
            deletePersonasByIdAccidente(Id_Accidente);

            String sql = "Delete FROM Accidentes WHERE Id=?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, Id_Accidente);
            ps.executeUpdate();
            ps.close();

            conexion.commit();
            conexion.setAutoCommit(true);
            conexion.close();

            ConexionAccess.desConnection();

        } catch (SQLException e) {
              if(conexion==null){
                    conexion= ConexionAccess.conectar();
                }
            conexion.rollback();
            System.out.println(e.getMessage());
        }

    }

    private ArrayList<Accidente> listAccidents = new ArrayList<>();
    private Connection conexion;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;



    

}
