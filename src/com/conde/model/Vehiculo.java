
package com.conde.model;


public class Vehiculo {

    public Vehiculo() {
    }

    public Vehiculo(int id_Vehiculo, int num_Accidente, String matricula, String marca, String modelo, String gestion, String observaciones) {
        this.id_Vehiculo = id_Vehiculo;
        this.num_Accidente = num_Accidente;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.gestion = gestion;
        this.observaciones = observaciones;
    }

    public Vehiculo(int num_Accidente, String matricula, String marca, String modelo, String gestion, String observaciones) {
        this.num_Accidente = num_Accidente;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.gestion = gestion;
        this.observaciones = observaciones;
    }

    public int getId_Vehiculo() {
        return id_Vehiculo;
    }

    public int getNum_Accidente() {
        return num_Accidente;
    }

    public void setNum_Accidente(int num_Accidente) {
        this.num_Accidente = num_Accidente;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getGestion() {
        return gestion;
    }

    public void setGestion(String gestion) {
        this.gestion = gestion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
    
    private int id_Vehiculo;
    private int num_Accidente;
    private String matricula;
    private String marca;
    private String modelo;
    private String gestion;
    private String observaciones;
}
