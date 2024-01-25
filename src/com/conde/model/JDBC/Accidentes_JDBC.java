package com.conde.model.JDBC;

import com.conde.model.ConexionAccess;
import com.conde.model.Model_Accident;
import com.conde.model.Persona;
import com.conde.model.Vehiculo;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class Accidentes_JDBC {

    public Accidentes_JDBC() {
        conexion = ConexionAccess.conectar();
    }

    public ArrayList<Model_Accident> getListAccidents() {
        String sql = "SELECT * FROM Accidentes ORDER BY Fecha";
        try {
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(sql);
            
            while (rs.next()) {
                Model_Accident acc = new Model_Accident();
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
                acc.setNum_Diligencias(rs.getString("Num_Diligencias"));
                acc.setTipo_Siniestro(rs.getInt("Tipo_Accidente"));
                acc.setZona_Atestados(rs.getString("Zona_Atestados"));
                acc.setDescripcion(rs.getString("Descripcion"));

                listAccidents.add(acc);

                
            }

            rs.close();
            st.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return listAccidents;
    }

    public void setListAccidents(ArrayList<Model_Accident> listAccidents) {
        this.listAccidents = listAccidents;
    }

    public Model_Accident getAccidentById(int Id) {
        String sql = "SELECT * FROM Accidentes WHERE Id =" + Id;
        Model_Accident acc = new Model_Accident();
        try {
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
                acc.setNum_Diligencias(rs.getString("Num_Diligencias"));
                acc.setTipo_Siniestro(rs.getInt("Tipo_Accidente"));
                acc.setZona_Atestados(rs.getString("Zona_Atestados"));
                acc.setDescripcion(rs.getString("Descripcion"));
                rs.close();
                st.close();

            } else {
                rs.close();
                st.close();
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
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(sql);

            if (rs.next()) {

                tipo_Accidente = rs.getString("TIPO");

            }
            rs.close();
            st.close();
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return tipo_Accidente;
    }

    public ArrayList<Vehiculo> getVehiculoInAccidentById(int index) {
        ArrayList<Vehiculo> listVeh = new ArrayList<>();
        String sql = "SELECT * FROM Vehiculos WHERE Id=" + index;

        try {
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

            return listVeh;

        } catch (Exception e) {
            System.out.println("Error en la carga de vehículos." + e.getMessage());
        }

        return null;

    }

    public ArrayList<Persona> getPersonasInAccidenteById(int index) {
        ArrayList<Persona> listPer = new ArrayList<>();
        String sql = "SELECT * FROM PERSONAS WHERE Num_Accidente=" + index;

        try {
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

            return listPer;

        } catch (Exception e) {

            System.out.println("Error en la carga de personas." + e.getMessage());
        }

        return null;
    }

    private ArrayList<Model_Accident> listAccidents = new ArrayList<>();
    private Connection conexion;
    private Statement st;
    private ResultSet rs;

    public String getMatriculaById(int Id_Vehiculo) {
        String sql = "SELECT MATRICULA FROM Vehiculos WHERE Id =" + Id_Vehiculo;
        String matriculaStr=null;
        try {
            st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(sql);

            if (rs.next()) {

                matriculaStr = rs.getString("MATRICULA").toString();

            }
            rs.close();
            st.close();
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return matriculaStr;
    }

}
