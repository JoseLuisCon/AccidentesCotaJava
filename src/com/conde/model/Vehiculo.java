
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
    
     public void setId_Vehiculo(int id_Vehiculo) {
        this.id_Vehiculo = id_Vehiculo;
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
    
    
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vehiculo veh = (Vehiculo) obj;
        return id_Vehiculo == veh.id_Vehiculo && num_Accidente == veh.num_Accidente && matricula == veh.matricula && marca == veh.marca && modelo == veh.modelo && gestion == veh.gestion && observaciones == veh.observaciones;
    
    }

    @Override
    public int hashCode() {
        
        int resultado = matricula.hashCode();
        
        resultado = 31 * id_Vehiculo + num_Accidente;
        
        return resultado;
           
    }

    @Override
    public String toString() {
        String salida = "Id Vehiculo: " + String.valueOf(id_Vehiculo)+"\n Id Accidente"+String.valueOf(num_Accidente)+"\n Matrícula "+matricula +
                                "\n Marca: " + marca + "\n Modelo" + modelo + "\n Gestion" + gestion + "\n Observaciones" + observaciones;
        
        return salida;
    }
    
    
    
    
    private int id_Vehiculo;
    private int num_Accidente;
    private String matricula;
    private String marca;
    private String modelo;
    private String gestion;
    private String observaciones;
}
