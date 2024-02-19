
package com.conde.component;

import com.conde.chart.ModelChart;
import com.conde.chart.PieChart.ModelPieChart;
import com.conde.chart.PieChart.PieChart;
import com.conde.chart.polarArea.ModelPolarAreaChart;
import com.conde.datechooserbtw.DateBetween;
import com.conde.datechooserbtw.DateChooser;
import com.conde.datechooserbtw.DateSelectable;
import com.conde.datechooserbtw.listener.DateChooserAction;
import com.conde.datechooserbtw.listener.DateChooserAdapter;
import com.conde.model.CountAccidentsByTipe;
import com.conde.model.JDBC.Accidentes_JDBC;
import com.conde.model.Tipo_Siniestro;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Estadistica extends javax.swing.JPanel {
    
    private final Accidentes_JDBC  dataAccidentes;
    
    public Estadistica() {
        this.dataAccidentes = new Accidentes_JDBC();
        
        initComponents();
        
        initRangoFechas();
        graficHeridos.addLegend("Fallecidos", new Color(245, 189, 135));
        graficHeridos.addLegend("Heridos graves", new Color(135, 189, 245));
        graficHeridos.addLegend("Heridos leves", new Color(189, 135, 245));
        graficHeridos.addLegend("Ilesos", new Color(139, 229, 222));
        
        

        
        loadDateGrafBar(selectorRangoFechas.getSelectedDateBetween());
        loadDateGrafPolar(selectorRangoFechas.getSelectedDateBetween());
        
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
               
                loadDateGrafBar(date);
                loadDateGrafPolar(date);
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
   
   
     private void loadDateGrafBar(DateBetween date) {
         

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
     
     
    private void loadDateGrafPolar(DateBetween selectedDateBetween) {
        
        ArrayList<CountAccidentsByTipe> tipoAccidentes = dataAccidentes.getListTipoAccdiente(selectedDateBetween);
        
        int  sumaTotalAccidentes = 0;    
        
        for(CountAccidentsByTipe item: tipoAccidentes){
            sumaTotalAccidentes += item.getSumaPorTipoAccidente();
        }
        
//        polarAreaChart.clear();
        pieChart.clearData();
        
        for(CountAccidentsByTipe item: tipoAccidentes){
            int porcentajePorTipoAccidente= Math.abs((item.getSumaPorTipoAccidente()*100)/sumaTotalAccidentes);
            int red = (int) (Math.random() * 256);
            int green = (int) (Math.random() * 256);
            int blue = (int) (Math.random() * 256);
        
            Color colorAleatorio = new Color(red, green, blue);
        
//            polarAreaChart.addItem(new ModelPolarAreaChart(colorAleatorio, dataAccidentes.getTipoAccidenteById(item.getTipoAccidente()),porcentajePorTipoAccidente));
            
            pieChart.addData(new ModelPieChart(dataAccidentes.getTipoAccidenteById(item.getTipoAccidente()), porcentajePorTipoAccidente, colorAleatorio));
        }
        
//        polarAreaChart.start();
        
     
    }

     
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selectorRangoFechas = new com.conde.datechooserbtw.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        txtRangoFechas = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        graficHeridos = new com.conde.chart.Chart();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        pieChart = new com.conde.chart.PieChart.PieChart();

        selectorRangoFechas.setTextField(txtRangoFechas);

        setLayout(new java.awt.BorderLayout(5, 5));

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
                .addContainerGap(751, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.EAST);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));

        jPanel5.setMaximumSize(new java.awt.Dimension(288, 22));
        jPanel5.setLayout(new java.awt.CardLayout());

        jLabel3.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Resultado accidentes");
        jLabel3.setToolTipText("");
        jLabel3.setMaximumSize(new java.awt.Dimension(288, 22));
        jLabel3.setMinimumSize(new java.awt.Dimension(288, 22));
        jLabel3.setPreferredSize(new java.awt.Dimension(288, 22));
        jLabel3.setRequestFocusEnabled(false);
        jPanel5.add(jLabel3, "card2");

        jPanel2.add(jPanel5);

        graficHeridos.setMinimumSize(new java.awt.Dimension(585, 350));
        graficHeridos.setPreferredSize(new java.awt.Dimension(585, 350));
        jPanel2.add(graficHeridos);

        jPanel3.setMaximumSize(new java.awt.Dimension(2147, 32));
        jPanel3.setLayout(new java.awt.CardLayout());

        jLabel2.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Clasificación por tipo de accidentes");
        jLabel2.setToolTipText("");
        jLabel2.setMinimumSize(new java.awt.Dimension(288, 32));
        jLabel2.setPreferredSize(new java.awt.Dimension(288, 32));
        jLabel2.setRequestFocusEnabled(false);
        jPanel3.add(jLabel2, "card2");

        jPanel2.add(jPanel3);

        jPanel4.setMinimumSize(new java.awt.Dimension(600, 400));
        jPanel4.setPreferredSize(new java.awt.Dimension(600, 400));
        jPanel4.setLayout(new java.awt.CardLayout());
        jPanel4.add(pieChart, "card2");

        jPanel2.add(jPanel4);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.conde.chart.Chart graficHeridos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private com.conde.chart.PieChart.PieChart pieChart;
    private com.conde.datechooserbtw.DateChooser selectorRangoFechas;
    private javax.swing.JTextField txtRangoFechas;
    // End of variables declaration//GEN-END:variables

}
