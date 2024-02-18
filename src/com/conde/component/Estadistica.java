
package com.conde.component;

import com.conde.chart.ModelChart;
import com.conde.datechooserbtw.DateBetween;
import com.conde.datechooserbtw.DateChooser;
import com.conde.datechooserbtw.DateSelectable;
import com.conde.datechooserbtw.listener.DateChooserAction;
import com.conde.datechooserbtw.listener.DateChooserAdapter;
import com.conde.model.JDBC.Accidentes_JDBC;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Estadistica extends javax.swing.JPanel {
    
    private final Accidentes_JDBC  dataAccidentes;
    private DateBetween dateSelected;

    public Estadistica() {
        this.dataAccidentes = new Accidentes_JDBC();
        
        initComponents();
        
        initRangoFechas();
        graficHeridos.addLegend("Fallecidos", new Color(245, 189, 135));
        graficHeridos.addLegend("Heridos graves", new Color(135, 189, 245));
        graficHeridos.addLegend("Heridos leves", new Color(189, 135, 245));
        graficHeridos.addLegend("Ilesos", new Color(139, 229, 222));

        
        
        loadDateGraf(selectorRangoFechas.getSelectedDateBetween());
        
    }

   private void initRangoFechas() {
        
        selectorRangoFechas.setDateSelectable(new DateSelectable() {
            @Override
            public boolean isDateSelectable(Date date) {
                return date.before(new Date());
            }
        });

        selectorRangoFechas.addActionDateChooserListener(new DateChooserAdapter() {

            @Override
            public void dateBetweenChanged(DateBetween date, DateChooserAction action) {
               
                loadDateGraf(date);
            }

        });

        selectorRangoFechas.setDateSelectionMode(DateChooser.DateSelectionMode.BETWEEN_DATE_SELECTED);
        selectorRangoFechas.setLabelCurrentDayVisible(false);
        selectorRangoFechas.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        DateBetween date= new DateBetween();
        Calendar calendar=Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), 0, 1);
        Date dateFrom= calendar.getTime();
        date.setFromDate(dateFrom);
        date.setToDate(new Date());
        
        if (date==null) date= selectorRangoFechas.getSelectedDateBetween();
        selectorRangoFechas.setSelectedDateBetween(date);
        
        
    }
   
   
     private void loadDateGraf(DateBetween date) {
         
      
        
        
//        if (dateSelected==null) dateSelected=selectorRangoFechas.getSelectedDateBetween();
        
       graficHeridos.clear();
        // Obtener los meses comprendidos entre las dos fechas
        ArrayList<Integer> meses = dataAccidentes.getNameMonth(date);

        if (meses.isEmpty()) {
            graficHeridos.clear();
            return;
        }
        
        // Obtener accidentes de cada mes con los fallecidos, heridos graves, heridos leves e ilesos
        ArrayList<ModelChart> dataMonth = new ArrayList<>();
        meses.forEach(mes ->{
            
                dataMonth.add(dataAccidentes.getDataGraphByMonth(mes));
        
        
        });
        
        dataMonth.forEach(data -> graficHeridos.addData(data));
         
        graficHeridos.start();
         
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selectorRangoFechas = new com.conde.datechooserbtw.DateChooser();
        graficHeridos = new com.conde.chart.Chart();
        jPanel1 = new javax.swing.JPanel();
        txtRangoFechas = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        selectorRangoFechas.setTextField(txtRangoFechas);

        setLayout(new java.awt.BorderLayout(5, 5));
        add(graficHeridos, java.awt.BorderLayout.CENTER);

        jPanel1.setMinimumSize(new java.awt.Dimension(160, 100));

        txtRangoFechas.setPreferredSize(new java.awt.Dimension(220, 35));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Selección de fechas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtRangoFechas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtRangoFechas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(588, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.conde.chart.Chart graficHeridos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private com.conde.datechooserbtw.DateChooser selectorRangoFechas;
    private javax.swing.JTextField txtRangoFechas;
    // End of variables declaration//GEN-END:variables

  
}
