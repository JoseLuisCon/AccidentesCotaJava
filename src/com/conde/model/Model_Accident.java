package com.conde.model;

import javax.swing.Icon;

public class Model_Accident {

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public int getNum_Accidente() {
        return Num_Accidente;
    }

    public void setNum_Accidente(int Num_Accidente) {
        this.Num_Accidente = Num_Accidente;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    public String getCarretera() {
        return Carretera;
    }

    public void setCarretera(String Carretera) {
        this.Carretera = Carretera;
    }

    public String getKilometro() {
        return Kilometro;
    }

    public void setKilometro(String Kilometro) {
        this.Kilometro = Kilometro;
    }

    public int getNum_Diligencias() {
        return Num_Diligencias;
    }

    public void setNum_Diligencias(int Num_Diligencias) {
        this.Num_Diligencias = Num_Diligencias;
    }

    public String getPatrulla() {
        return Patrulla;
    }

    public void setPatrulla(String Patrulla) {
        this.Patrulla = Patrulla;
    }

    public String getZona_Atestados() {
        return Zona_Atestados;
    }

    public void setZona_Atestados(String Zona_Atestados) {
        this.Zona_Atestados = Zona_Atestados;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getTipo_Siniestro() {
        return Tipo_Siniestro;
    }

    public void setTipo_Siniestro(int Tipo_Siniestro) {
        this.Tipo_Siniestro = Tipo_Siniestro;
    }

    public StatusType getStattus() {
        
       switch (Zona_Atestados) {
            case "Pamplona":
                stattus = StatusType.PAMPLONA;
                break;
            case "Tudela":
                stattus = StatusType.TUDELA;
                break;
            default:
                stattus=StatusType.OTRO;
                break;
        }
            
        return stattus;
    }

    public void setStattus(StatusType stattus) {
        
        
        
        this.stattus = stattus;
    }

    public Model_Accident(Icon icon, int Num_Accidente, String Fecha, String Hora, String Carretera, String Kilometro, int Num_Diligencias, String Patrulla, String Zona_Atestados, String Descripcion, int Tipo_Siniestro, StatusType status) {
        this.icon = icon;
        this.Num_Accidente = Num_Accidente;
        this.Fecha = Fecha;
        this.Hora = Hora;
        this.Carretera = Carretera;
        this.Kilometro = Kilometro;
        this.Num_Diligencias = Num_Diligencias;
        this.Patrulla = Patrulla;
        this.Zona_Atestados = Zona_Atestados;
        this.Descripcion = Descripcion;
        this.Tipo_Siniestro = Tipo_Siniestro;
        this.stattus = status;

    }

    public Model_Accident(Icon icon, int Num_Accidente, String Zona_Atestados, String Descripcion, int Tipo_Siniestro) {
        this.icon = icon;
        this.Num_Accidente = Num_Accidente;
        this.Zona_Atestados = Zona_Atestados;
        this.Descripcion = Descripcion;
        this.Tipo_Siniestro = Tipo_Siniestro;
    }

    public Model_Accident() {
    }

    private Icon icon;
    private int Num_Accidente;
    private String Fecha;
    private String Hora;
    private String Carretera;
    private String Kilometro;
    private int Num_Diligencias;
    private String Patrulla;
    private String Zona_Atestados;
    private String Descripcion;
    private int Tipo_Siniestro;
    private StatusType stattus;

}
