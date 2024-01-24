package com.conde.component;

import com.conde.model.Model_Accident;
import com.conde.model.Persona;
import com.conde.model.Vehiculo;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class Card_Persona extends javax.swing.JPanel {

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


    public Card_Persona() {
        initComponents();
        setOpaque(false);
              
        color1 = Color.BLACK;
        color2 = Color.WHITE;
        
        sp_tbl_personas.getViewport().setBackground(Color.WHITE);
        
    }

    public void setData(ArrayList<Persona> data) {
        
        clearRows();

//          for (Vehiculo vehic : data) {
//            
//            tbl_Personas.addRow(
//                    new Object[]{
//                        vehic.getId_Vehiculo(),
//                        vehic.getMatricula(),
//                        vehic.getMarca(),
//                        vehic.getModelo(),
//                        vehic.getGestion(),
//                        vehic.getObservaciones()
//                    });
//        }
       
    }
    
    public void clearRows() {
        DefaultTableModel dtm = (DefaultTableModel) tbl_Personas.getModel();
        dtm.setRowCount(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblIcon = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        sp_tbl_personas = new javax.swing.JScrollPane();
        tbl_Personas = new com.conde.swing.Table_Personas();

        setOpaque(false);

        jPanel2.setOpaque(false);

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.X_AXIS));

        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/conde/resources/icons/people_32.png"))); // NOI18N
        jPanel1.add(lblIcon);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("  Personas implicadas");
        jLabel1.setIconTextGap(10);
        jPanel1.add(jLabel1);

        jPanel3.add(jPanel1, java.awt.BorderLayout.NORTH);

        tbl_Personas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Num_Accidente", "Matrícula", "Documento", "Tipo", "Resultado", "Observaciones", "Detalles"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sp_tbl_personas.setViewportView(tbl_Personas);
        if (tbl_Personas.getColumnModel().getColumnCount() > 0) {
            tbl_Personas.getColumnModel().getColumn(0).setMinWidth(0);
            tbl_Personas.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbl_Personas.getColumnModel().getColumn(0).setMaxWidth(0);
            tbl_Personas.getColumnModel().getColumn(1).setMinWidth(0);
            tbl_Personas.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbl_Personas.getColumnModel().getColumn(1).setMaxWidth(0);
        }

        jPanel3.add(sp_tbl_personas, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.setColor(new Color(255,255,255,50));
        super.paintComponent(grphcs);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JScrollPane sp_tbl_personas;
    private com.conde.swing.Table_Personas tbl_Personas;
    // End of variables declaration//GEN-END:variables

 
}
