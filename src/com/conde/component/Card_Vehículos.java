package com.conde.component;

import com.conde.model.Model_Accident;
import com.conde.model.Vehiculo;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Card_Vehículos extends javax.swing.JPanel {

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


    public Card_Vehículos() {
        initComponents();
        setOpaque(false);
        setLayout(null);
        this.setSize(246,218);
                
        color1 = Color.BLACK;
        color2 = Color.WHITE;

        
    }

    public void setData(Vehiculo data, String tipo_Accidente) {

//        lblIcon.setIcon(data.getIcon());
//        lblDescripcion.setText(data.getDescripcion());
//        lblNumAccidente.setText(String.valueOf(data.getNum_Accidente()));
//        lblTipoAccidente.setText(tipo_Accidente);
        
       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblIcon = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_Vehiculos1 = new com.conde.swing.Table_Vehiculos();

        setOpaque(false);
        setLayout(null);

        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/conde/resources/icons/people_32.png"))); // NOI18N
        add(lblIcon);
        lblIcon.setBounds(10, 10, 32, 32);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Vehículos implicados");
        add(jLabel1);
        jLabel1.setBounds(50, 10, 230, 30);

        table_Vehiculos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id_Vehiculo", "Matricula", "Marca", "Modelo", "Gestiones", "Observaciones"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table_Vehiculos1);

        add(jScrollPane2);
        jScrollPane2.setBounds(10, 50, 600, 190);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblIcon;
    private com.conde.swing.Table_Vehiculos table_Vehiculos1;
    // End of variables declaration//GEN-END:variables
}
