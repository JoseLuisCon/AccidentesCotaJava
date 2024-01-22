package com.conde.component;

import com.conde.model.Model_Accident;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Card_Accident extends javax.swing.JPanel {

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    private Color color1;
    private Color color2;


    public Card_Accident() {
        initComponents();
        setOpaque(false);
        setLayout(null);
        this.setSize(246,218);
                
        color1 = Color.BLACK;
        color2 = Color.WHITE;

        
    }

    public void setData(Model_Accident data) {

        lblIcon.setIcon(data.getIcon());
        lblDescripcion.setText(data.getDescripcion());
        lblNumAccidente.setText(String.valueOf(data.getNum_Accidente()));
        lblTipoAccidente.setText(String.valueOf(data.getTipo_Siniestro()));
        lblZonaAtestados.setText(data.getZona_Atestados());
       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblIcon = new javax.swing.JLabel();
        lblNumAccidente = new javax.swing.JLabel();
        lblZonaAtestados = new javax.swing.JLabel();
        lblTipoAccidente = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();

        setOpaque(false);
        setLayout(null);

        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/conde/resources/icons/coche.png"))); // NOI18N
        add(lblIcon);
        lblIcon.setBounds(25, 18, 32, 32);

        lblNumAccidente.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblNumAccidente.setForeground(new java.awt.Color(255, 255, 255));
        lblNumAccidente.setText("Núm. Accidente:");
        add(lblNumAccidente);
        lblNumAccidente.setBounds(20, 70, 120, 30);

        lblZonaAtestados.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblZonaAtestados.setForeground(new java.awt.Color(255, 255, 255));
        lblZonaAtestados.setText("Zona atestados");
        add(lblZonaAtestados);
        lblZonaAtestados.setBounds(20, 110, 130, 19);

        lblTipoAccidente.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTipoAccidente.setForeground(new java.awt.Color(255, 255, 255));
        lblTipoAccidente.setText("Tipo accidente");
        add(lblTipoAccidente);
        lblTipoAccidente.setBounds(20, 140, 130, 19);

        lblDescripcion.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        lblDescripcion.setText("Descripción:");
        lblDescripcion.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        add(lblDescripcion);
        lblDescripcion.setBounds(20, 170, 100, 20);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.setColor(new Color(255,255,255,50));
        g2.fillOval(getWidth()-(getHeight()/2), 10, getHeight(), getHeight());
        g2.fillOval(getWidth()-(getHeight()/2)-20, getHeight()/2+20, getHeight(), getHeight());
        super.paintComponent(grphcs);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblNumAccidente;
    private javax.swing.JLabel lblTipoAccidente;
    private javax.swing.JLabel lblZonaAtestados;
    // End of variables declaration//GEN-END:variables
}
