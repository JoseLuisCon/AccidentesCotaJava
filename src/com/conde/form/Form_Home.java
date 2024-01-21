package com.conde.form;

import com.conde.model.Model_Accident;
import com.conde.model.StatusType;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;


public class Form_Home extends javax.swing.JPanel {

    private ArrayList<Model_Accident> listAccidents = new ArrayList<>();

    private Model_Accident accident;

    public Form_Home() {
        this.accident = new Model_Accident();
        initComponents();
        Model_Accident accidente1 = new Model_Accident();
        accidente1.setIcon(new ImageIcon(this.getClass().getResource("/com/conde/resources/icons/coche.png")));
        accidente1.setNum_Accidente("AF-1");
        accidente1.setDescripcion("Atropello a Jabalí y posterior salida de vía margen derecho, ocasionando daños múltiples el vehículo con paragolpes destrozado");
        accidente1.setTipo_Siniestro("Atropello a animal");
        accidente1.setZona_Atestados("Pamplona");

        card1.setData(accidente1);

        fill_data_listAccidents();
        
        
        spTable.setVerticalScrollBar(new JScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);

//           Add row table
        for (Model_Accident accidente : listAccidents) {
            
            table.addRow(new Object[]{accidente.getNum_Accidente(), accidente.getFecha(), accidente.getHora(), accidente.getCarretera(), accidente.getKilometro(), accidente.getNum_Diligencias(), accidente.getPatrulla(), accidente.getStattus()});
        }

    }

    private void fill_data_listAccidents() {

        accident.setNum_Accidente("1");
        accident.setFecha("12/12/2023");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.REJECT);

        this.listAccidents.add(accident);
        accident = new Model_Accident();

        accident.setNum_Accidente("2");
        accident.setFecha("13/12/2023");
        accident.setHora("05:20");
        accident.setCarretera("N-121-A");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-510");
//        accident.setZona_Atestados("Atestadis");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
        accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
        
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);
         accident = new Model_Accident();

        accident.setNum_Accidente("1");
        accident.setFecha("15/01/2024");
        accident.setHora("12:00");
        accident.setCarretera("N-625");
        accident.setKilometro("12,5");
        accident.setNum_Diligencias("123");
        accident.setPatrulla("NA-120");
//        accident.setZona_Atestados("Pamplona");
        accident.setStattus(StatusType.APPROBED);

        this.listAccidents.add(accident);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        card1 = new com.conde.component.Card_Accident();
        card2 = new com.conde.component.Card_Accident();
        card3 = new com.conde.component.Card_Accident();
        panelBorder1 = new com.conde.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new com.conde.swing.Table();

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(142, 142, 250));
        card1.setColor2(new java.awt.Color(123, 123, 245));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(186, 123, 247));
        card2.setColor2(new java.awt.Color(167, 94, 236));
        panel.add(card2);

        card3.setColor1(new java.awt.Color(241, 208, 62));
        card3.setColor2(new java.awt.Color(211, 184, 61));
        panel.add(card3);

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
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addGap(20, 20, 20))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.conde.component.Card_Accident card1;
    private com.conde.component.Card_Accident card2;
    private com.conde.component.Card_Accident card3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane panel;
    private com.conde.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private com.conde.swing.Table table;
    // End of variables declaration//GEN-END:variables
}
