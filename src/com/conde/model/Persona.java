
package com.conde.model;


public class Persona {

    public Persona() {
    }

    public Persona(int id_Persona, int id_Accidente, int id_Vehiculo, String documento, String tipo_persona, String resultado, Boolean trasladado, String lugar_traslado, Boolean prueba_alcoholemia, Boolean alcoholemia_positiva, Boolean prueba_drogas, Boolean drogas_positiva, String Observaciones) {
        this.id_Persona = id_Persona;
        this.id_Accidente = id_Accidente;
        this.id_Vehiculo = id_Vehiculo;
        this.documento = documento;
        this.tipo_persona = tipo_persona;
        this.resultado = resultado;
        this.trasladado = trasladado;
        this.lugar_traslado = lugar_traslado;
        this.prueba_alcoholemia = prueba_alcoholemia;
        this.alcoholemia_positiva = alcoholemia_positiva;
        this.prueba_drogas = prueba_drogas;
        this.drogas_positiva = drogas_positiva;
        this.Observaciones = Observaciones;
    }

    public Persona(int id_Accidente, int id_Vehiculo, String documento, String tipo_persona, String resultado, Boolean trasladado, String lugar_traslado, Boolean prueba_alcoholemia, Boolean alcoholemia_positiva, Boolean prueba_drogas, Boolean drogas_positiva, String Observaciones) {
        this.id_Accidente = id_Accidente;
        this.id_Vehiculo = id_Vehiculo;
        this.documento = documento;
        this.tipo_persona = tipo_persona;
        this.resultado = resultado;
        this.trasladado = trasladado;
        this.lugar_traslado = lugar_traslado;
        this.prueba_alcoholemia = prueba_alcoholemia;
        this.alcoholemia_positiva = alcoholemia_positiva;
        this.prueba_drogas = prueba_drogas;
        this.drogas_positiva = drogas_positiva;
        this.Observaciones = Observaciones;
    }

    public int getId_Persona() {
        return id_Persona;
    }

//    public void setId_Persona(int id_Persona) {
//        this.id_Persona = id_Persona;
//    }

    public int getId_Accidente() {
        return id_Accidente;
    }

    public void setId_Accidente(int id_Accidente) {
        this.id_Accidente = id_Accidente;
    }

    public int getId_Vehiculo() {
        return id_Vehiculo;
    }

    public void setId_Vehiculo(int id_Vehiculo) {
        this.id_Vehiculo = id_Vehiculo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTipo_persona() {
        return tipo_persona;
    }

    public void setTipo_persona(String tipo_persona) {
        this.tipo_persona = tipo_persona;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Boolean getTrasladado() {
        return trasladado;
    }

    public void setTrasladado(Boolean trasladado) {
        this.trasladado = trasladado;
    }

    public String getLugar_traslado() {
        return lugar_traslado;
    }

    public void setLugar_traslado(String lugar_traslado) {
        this.lugar_traslado = lugar_traslado;
    }

    public Boolean getPrueba_alcoholemia() {
        return prueba_alcoholemia;
    }

    public void setPrueba_alcoholemia(Boolean prueba_alcoholemia) {
        this.prueba_alcoholemia = prueba_alcoholemia;
    }

    public Boolean getAlcoholemia_positiva() {
        return alcoholemia_positiva;
    }

    public void setAlcoholemia_positiva(Boolean alcoholemia_positiva) {
        this.alcoholemia_positiva = alcoholemia_positiva;
    }

    public Boolean getPrueba_drogas() {
        return prueba_drogas;
    }

    public void setPrueba_drogas(Boolean prueba_drogas) {
        this.prueba_drogas = prueba_drogas;
    }

    public Boolean getDrogas_positiva() {
        return drogas_positiva;
    }

    public void setDrogas_positiva(Boolean drogas_positiva) {
        this.drogas_positiva = drogas_positiva;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;
    }

   
    
    private int id_Persona;
    private int id_Accidente;
    private int id_Vehiculo;
    private String documento;
    private String tipo_persona;
    private String resultado;
    private Boolean trasladado;
    private String lugar_traslado;
    private Boolean prueba_alcoholemia;
    private Boolean alcoholemia_positiva;
    private Boolean prueba_drogas;
    private Boolean drogas_positiva;
    private String Observaciones;
    
}
