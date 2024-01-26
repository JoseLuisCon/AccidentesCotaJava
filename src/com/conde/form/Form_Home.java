package com.conde.form;

import com.conde.event.EventRowSelected;
import com.conde.model.JDBC.Accidentes_JDBC;
import com.conde.model.Model_Accident;
import com.conde.model.Persona;
import com.conde.model.StatusType;
import com.conde.model.Vehiculo;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.lang.Object;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;


public class Form_Home extends javax.swing.JPanel {

    private ArrayList<Model_Accident> listAccidents = new ArrayList<>();
    private Accidentes_JDBC datos_model = new Accidentes_JDBC();
    

    public Form_Home() {
  
        initComponents();
        
        spTable.setVerticalScrollBar(new JScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        
        table.addEventRowSelected((int index)->{
            Model_Accident accidenteOK = new Model_Accident();
            //Rellenar datos card1
            for (Model_Accident Accidente : listAccidents) {
                if (Accidente.getNum_Accidente()==index){
                    accidenteOK = Accidente;
                    break;
                }
 
            }
            // Cargamos datos del accidente seleccionado en el card1
            ImageIcon image = new ImageIcon("src/com/conde/resources/icons/accidente.png");
            Icon icon = new ImageIcon(image.getImage());
            accidenteOK.setIcon(icon);
            String tipoAccidenteString = datos_model.getTipoAccidenteById(accidenteOK.getTipo_Siniestro());
            data_Aux_Accidente.setData(accidenteOK, tipoAccidenteString);
            
            cargaVehiculosAccidente(index);
            cargaPersonasAccidente(index);
            
        });
      
        
    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        header1 = new com.conde.component.Header();
        panel = new javax.swing.JLayeredPane();
        data_Aux_Accidente = new com.conde.component.Card_Accident();
        vehiculos = new com.conde.component.Card_Vehiculos();
        personas = new com.conde.component.Card_Persona();
        panelBorder1 = new com.conde.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new com.conde.swing.Table();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1750, 840));
        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        java.awt.GridBagLayout panelLayout = new java.awt.GridBagLayout();
        panelLayout.columnWidths = new int[] {400, 500, 500};
        panelLayout.rowHeights = new int[] {0};
        panelLayout.columnWeights = new double[] {0.0, 0.0, 0.0};
        panelLayout.rowWeights = new double[] {0.0};
        panel.setLayout(panelLayout);

        data_Aux_Accidente.setColor1(new java.awt.Color(142, 142, 250));
        data_Aux_Accidente.setColor2(new java.awt.Color(123, 123, 245));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        panel.add(data_Aux_Accidente, gridBagConstraints);

        vehiculos.setColor1(new java.awt.Color(101, 157, 15));
        vehiculos.setColor2(new java.awt.Color(191, 226, 137));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panel.add(vehiculos, gridBagConstraints);

        personas.setColor1(new java.awt.Color(0, 180, 208));
        personas.setColor2(new java.awt.Color(0, 131, 176));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panel.add(personas, gridBagConstraints);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Listado de accidentes");

        spTable.setBorder(null);
        spTable.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Num. Accidente", "Fecha", "Hora", "Carretera", "Kilometro", "Num. Diligencias", "Patrulla", "Zona Atestados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        spTable.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMinWidth(0);
            table.getColumnModel().getColumn(0).setPreferredWidth(0);
            table.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 1393, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(header1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 1406, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        cargarAccidentes();
    }//GEN-LAST:event_formAncestorAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.conde.component.Card_Accident data_Aux_Accidente;
    private com.conde.component.Header header1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane panel;
    private com.conde.swing.PanelBorder panelBorder1;
    private com.conde.component.Card_Persona personas;
    private javax.swing.JScrollPane spTable;
    private com.conde.swing.Table table;
    private com.conde.component.Card_Vehiculos vehiculos;
    // End of variables declaration//GEN-END:variables

    private void cargarAccidentes() {
       
      //Rellenamos el Arraylist
      listAccidents.clear();
      
      listAccidents  = datos_model.getListAccidents();
             
       DefaultTableModel dtm = (DefaultTableModel) table.getModel();
       dtm.setRowCount(0);
        
        //Lo mostramos en la tabla
       
        for (Model_Accident accidente : listAccidents) {    
            table.addRow(new Object[]{accidente.getNum_Accidente(), accidente.getFecha(), accidente.getHora(), accidente.getCarretera(), accidente.getKilometro(), accidente.getNum_Diligencias(), accidente.getPatrulla(), accidente.getStattus()});
        }
    }

    private void cargaVehiculosAccidente(int index) {
        
        ArrayList<Vehiculo> vehiculosAccidente = new ArrayList<>();
       
        vehiculosAccidente = datos_model.getVehiculoInAccidentById(index);
        
        vehiculos.clearRows();
        
        if (vehiculosAccidente != null){
            vehiculos.setData(vehiculosAccidente);
        }
        
    }

    private void cargaPersonasAccidente(int index) {
        ArrayList<Persona> personasAccidente = new ArrayList<>();
        
        personasAccidente = datos_model.getPersonasInAccidenteById(index);
        
        personas.clearRows();
        personas.setToolTipRows(personasAccidente);
        if (personasAccidente != null){
            personas.setData(personasAccidente);
        }
        
        
    }
}
