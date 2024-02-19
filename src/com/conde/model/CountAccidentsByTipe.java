
package com.conde.model;

public class CountAccidentsByTipe {
    
    private int tipoAccidente;
    private int sumaPorTipoAccidente;

    public int getTipoAccidente() {
        return tipoAccidente;
    }

    public void setTipoAccidente(int tipoAccidente) {
        this.tipoAccidente = tipoAccidente;
    }

    public int getSumaPorTipoAccidente() {
        return sumaPorTipoAccidente;
    }

    public void setSumaPorTipoAccidente(int sumaPorTipoAccidente) {
        this.sumaPorTipoAccidente = sumaPorTipoAccidente;
    }

    public CountAccidentsByTipe(int tipoAccidente, int sumaPorTipoAccidente) {
        this.tipoAccidente = tipoAccidente;
        this.sumaPorTipoAccidente = sumaPorTipoAccidente;
    }

    public CountAccidentsByTipe() {
    }
    
    
    
}
