package com.conde.model.JDBC;

import com.conde.model.ConexionAccess;
import com.conde.model.Accidente;
import com.conde.model.Persona;
import com.conde.model.Vehiculo;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Accidentes_JDBC {

    public Accidentes_JDBC() {
    }

    public ArrayList<Accidente> getListAccidents() {
        String sql = "SELECT TOP 100 * FROM Accidentes ORDER BY Fecha";
        try {
            conexion = ConexionAccess.conectar();
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Accidente acc = new Accidente();
                acc.setNum_Accidente(rs.getInt("Id"));
                //Fecha
                Date fecha = rs.getDate("Fecha");
                acc.setFecha(new SimpleDateFormat("dd/MM/YY").format(fecha));
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
                acc.setFecha(new SimpleDateFormat("dd/MM/YY").format(fecha));
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

    public ArrayList<Vehiculo> getVehiculoInAccidentById(int index) {
        ArrayList<Vehiculo> listVeh = new ArrayList<>();
        String sql = "SELECT * FROM Vehiculos WHERE Id=" + index;

        try {
            conexion = ConexionAccess.conectar();
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Vehiculo veh = new Vehiculo(
                        rs.getInt("Id"),
                        rs.getInt("NUM_ACCI"),
                        rs.getString("MATRICULA"),
                        rs.getString("MARCA"),
                        rs.getString("MODELO"),
                        rs.getString("GESTION"),
                        rs.getString("OBSERVACIONES")
                );

                listVeh.add(veh);

            }
            rs.close();
            st.close();
            conexion.close();
            ConexionAccess.desConnection();
            return listVeh;

        } catch (Exception e) {
            System.out.println("Error en la carga de vehículos." + e.getMessage());
        }

        return null;

    }

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

    public ArrayList<Persona> getPersonasInAccidenteById(int index) {
        ArrayList<Persona> listPer = new ArrayList<>();
        String sql = "SELECT * FROM PERSONAS WHERE Num_Accidente=" + index;

        try {
            conexion = ConexionAccess.conectar();
            st = conexion.createStatement();
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

    public int getNumAccidenteByNumDiligencias(int numDiligencias) {

        String sql = "SELECT Id FROM Accidente WHERE Num_Diligencias="+numDiligencias;
        int NumAccidente=0;
        try {
            conexion = ConexionAccess.conectar();
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(sql);

            if (rs.next()) {

              NumAccidente = rs.getInt("Id");

            }
            rs.close();
            st.close();
            conexion.close();
            ConexionAccess.desConnection();
            return NumAccidente;

        } catch (Exception e) {
            System.out.println("Error en la carga del tipo de personas." + e.getMessage());
        }

        return NumAccidente;

    }

    public int AddNuevoAccidente(Accidente newAccidente) {
        String sql = "INSERT INTO Accidentes (Id,Fecha,Hora,Carretera,Kilometro,Patrulla,Num_Diligencias, Tipo_Accidente, Zona_Atestados, Descripcion) VALUES (?,?,?,?,?,?,?,?,?,?)";

        Integer filasInsertadas = 0;

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

            filasInsertadas = ps.executeUpdate();

            ps.close();
            conexion.close();
            ConexionAccess.desConnection();
            return filasInsertadas;

        } catch (SQLException ex) {
            Logger.getLogger(Accidentes_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Accidentes_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return filasInsertadas;

    }
    
     public void addListadoVehiculos(ArrayList<Vehiculo> listaVehiculos) {
         
         String sql = "INSERT INTO Vehiculos (Id,NUM_ACCI,MATRICULA,MARCA,MODELO,GESTION,OBSERVACIONES) VALUES (?,?,?,?,?,?,?)";
         
         try {
             listaVehiculos.forEach((veh)->{
                 
                 
             });
             
             
         } catch (Exception e) {
         }
         
         
    }

    public void AddNuevoLugarAccidente(String nuevoLugar) {
        String sql = "INSERT INTO CARRETERAS (DENOMINACION) VALUES (?)";

        try {
            conexion = ConexionAccess.conectar();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nuevoLugar);
            int numRows = ps.executeUpdate();

            ps.close();
            conexion.close();
            ConexionAccess.desConnection();
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
        conexion.close();
        ConexionAccess.desConnection();

    }

    public void deletePersonasByIdAccidente(int Id_Accdient) throws SQLException {

        String sql = "Delete FROM Personas WHERE Num_Accidente=?";

        conexion = ConexionAccess.conectar();
        ps = conexion.prepareStatement(sql);
        ps.setInt(1, Id_Accdient);
        ps.executeUpdate();
        ps.close();
        st.close();
        conexion.close();
        ConexionAccess.desConnection();
    }

    public void deleteAccidenteById(int Id_Accidente) throws SQLException {
        conexion = ConexionAccess.conectar();
        conexion.setAutoCommit(false);
        //iniciamos transacción de borrado de accidentes
        try {

            deleteVehiculosByIdAccidente(Id_Accidente);
            deletePersonasByIdAccidente(Id_Accidente);

            conexion = ConexionAccess.conectar();
            String sql = "Delete FROM Accidentes WHERE Id=?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, Id_Accidente);
            ps.executeUpdate();
            ps.close();
            st.close();

            ConexionAccess.desConnection();

        } catch (SQLException e) {
            conexion.rollback();
            System.out.println(e.getMessage());
        }
        conexion.setAutoCommit(true);
        conexion.close();
        ConexionAccess.desConnection();
    }

    private ArrayList<Accidente> listAccidents = new ArrayList<>();
    private Connection conexion;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;

   

}
