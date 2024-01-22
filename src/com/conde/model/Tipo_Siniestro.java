
package com.conde.model;


public class Tipo_Siniestro {

    public Tipo_Siniestro() {
    }

    public Tipo_Siniestro(int id_Siniestro, String Tipo) {
        this.id_Siniestro = id_Siniestro;
        this.Tipo = Tipo;
    }

    public Tipo_Siniestro(String Tipo) {
        this.Tipo = Tipo;
    }

    public int getId_Siniestro() {
        return id_Siniestro;
    }

//    public void setId_Siniestro(int id_Siniestro) {
//        this.id_Siniestro = id_Siniestro;
//    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
    

    
    private int id_Siniestro;
    private String Tipo;
}
