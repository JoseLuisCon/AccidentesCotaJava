package com.conde.form;

import com.conde.cell.TableActionCellEditor;
import com.conde.cell.TableActionEvent;
import com.conde.component.textfieldSearch.SearchOptinEvent;
import com.conde.component.textfieldSearch.SearchOption;
import com.conde.model.JDBC.Accidentes_JDBC;
import com.conde.model.Accidente;
import com.conde.model.Persona;
import com.conde.model.Vehiculo;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.lang.Object;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Form_Home extends javax.swing.JPanel {

    private ArrayList<Accidente> listAccidents = new ArrayList<>();
    private Accidentes_JDBC datos_model = new Accidentes_JDBC();
    private String cadenaBusqueda = "";

    public Form_Home() {

        initComponents();

        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        s.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        s.getViewport().setBackground(Color.WHITE);

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(JTable t, int row) {
                System.out.println("Editando fila: " + row);
            }

            @Override
            public void onDelete(JTable t, int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }

                int respDelete = JOptionPane.showConfirmDialog(null, "Atención: Se borraran todos los datos del accidente\n (Vehiculos y personas implicadas)\n¿Desea continuar?", "Atención", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

                if (respDelete == JOptionPane.OK_OPTION) {
                    try {
                        int id_Accidente = (int) table.getValueAt(row, 0);
                        datos_model.deleteAccidenteById(id_Accidente);
                    } catch (SQLException e) {
                        System.out.println("Error en el borrado del accidente en la base de datos");
                    }

                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.removeRow(row);
                    vehiculos.clearRows();
                    personas.clearRows();
                    data_Aux_Accidente.deleteData();

                }

            }
        };

        table.getColumnModel().getColumn(1).setCellEditor(new TableActionCellEditor(event));

        table.addEventRowSelected((int index) -> {
            Accidente accidenteOK = new Accidente();
            //Rellenar datos card1
            for (Accidente Accidente : listAccidents) {
                if (Accidente.getNum_Accidente() == index) {
                    accidenteOK = Accidente;
                    System.out.println("Index " + index);
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

            try {
                cargaPersonasAccidente(index);
            } catch (SQLException ex) {
                Logger.getLogger(Form_Home.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        initSearchTextField();

    }

    private void initSearchTextField() {
        txtSearchFiled.addEventOptionSelected(new SearchOptinEvent() {
            @Override
            public void optionSelected(SearchOption option, int index) {
                txtSearchFiled.setHint("Búsqueda por " + option.getName() + "...");
            }
        });

        txtSearchFiled.addOption(new SearchOption("Fecha", new ImageIcon(getClass().getResource("/com/conde/resources/icons/fecha.png"))));
        txtSearchFiled.addOption(new SearchOption("Equipo", new ImageIcon(getClass().getResource("/com/conde/resources/icons/horizonte.png"))));
        txtSearchFiled.addOption(new SearchOption("Carretera", new ImageIcon(getClass().getResource("/com/conde/resources/icons/carretera.png"))));
        txtSearchFiled.addOption(new SearchOption("Diligencias", new ImageIcon(getClass().getResource("/com/conde/resources/icons/diligencias.png"))));
        txtSearchFiled.addOption(new SearchOption("Patrulla", new ImageIcon(getClass().getResource("/com/conde/resources/icons/patrulla.png"))));
        txtSearchFiled.setSelectionEnd(0);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        panel = new javax.swing.JLayeredPane();
        data_Aux_Accidente = new com.conde.component.Card_Accident();
        vehiculos = new com.conde.component.Card_Vehiculos();
        personas = new com.conde.component.Card_Persona();
        panelBorder1 = new com.conde.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        s = new com.conde.swing.ScrollPaneWin11();
        table = new com.conde.swing.Table_Accidentes();
        header = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSearchFiled = new com.conde.component.textfieldSearch.TextFieldSearchOption();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
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
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        setLayout(new java.awt.CardLayout());

        jPanel1.setMaximumSize(new java.awt.Dimension(1424, 968));
        jPanel1.setMinimumSize(new java.awt.Dimension(1424, 968));
        jPanel1.setName(""); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(1424, 968));

        java.awt.GridBagLayout panelLayout = new java.awt.GridBagLayout();
        panelLayout.columnWidths = new int[] {400, 500, 500};
        panelLayout.rowHeights = new int[] {0};
        panelLayout.columnWeights = new double[] {0.0, 0.0, 0.0};
        panelLayout.rowWeights = new double[] {0.0};
        panel.setLayout(panelLayout);

        data_Aux_Accidente.setColor1(new java.awt.Color(191, 78, 116));
        data_Aux_Accidente.setColor2(new java.awt.Color(184, 113, 137));
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

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Acciones", "Fecha", "Hora", "Carretera", "Kilometro", "Núm. Diligencias", "Patrulla", "Zona Atestados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setMaximumSize(null);
        table.setName(""); // NOI18N
        table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableKeyPressed(evt);
            }
        });
        s.setViewportView(table);
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
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(1069, 1198, Short.MAX_VALUE))
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(s, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(s, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        header.setOpaque(false);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/conde/resources/icons/search.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("LISTADO DE ACCIDENTES");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearchFiled, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtSearchFiled, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 1425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        add(jPanel1, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
//        cargarAccidentes();

    }//GEN-LAST:event_formAncestorAdded

    private void tableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyPressed

    }//GEN-LAST:event_tableKeyPressed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
//        cargarAccidentes();
    }//GEN-LAST:event_formFocusGained

    private boolean verificarFecha(String texto) {
        String textoFecha = texto;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        formatoFecha.setLenient(false); // Hace que SimpleDateFormat sea estricto en cuanto al formato de fecha

        try {
            formatoFecha.parse(textoFecha);
            return true;
        } catch (ParseException ex) {
            return false;
        }

    }

    ;
    
    public void cargarAccidentes(String where, Object... search) {

        listAccidents.clear();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        String SqlQuery = "SELECT * FROM Accidentes " + where;
        listAccidents = datos_model.getListAccidents(SqlQuery);

        //Lo mostramos en la tabla
        for (Accidente accidente : listAccidents) {
            table.addRow(new Object[]{accidente.getNum_Accidente(), null, accidente.getFecha(), accidente.getHora(), accidente.getCarretera(), accidente.getKilometro(), accidente.getNum_Diligencias(), accidente.getPatrulla(), accidente.getStattus()});
        }

    }

//    public void cargarAccidentes() {
//
//        //Rellenamos el Arraylist
//        listAccidents.clear();
//
//        listAccidents = datos_model.getListAccidents();
//
//        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
//        dtm.setRowCount(0);
//
//        //Lo mostramos en la tabla
//        for (Accidente accidente : listAccidents) {
//            table.addRow(new Object[]{accidente.getNum_Accidente(), null, accidente.getFecha(), accidente.getHora(), accidente.getCarretera(), accidente.getKilometro(), accidente.getNum_Diligencias(), accidente.getPatrulla(), accidente.getStattus()});
//        }
//    }
    private void cargaVehiculosAccidente(int index) {

        ArrayList<Vehiculo> vehiculosAccidente = new ArrayList<>();

        vehiculosAccidente = datos_model.getVehiculoInAccidentById(index);

        vehiculos.clearRows();
        vehiculos.setToolTipRows(vehiculosAccidente);
        if (vehiculosAccidente != null) {
            vehiculos.setData(vehiculosAccidente);
        }

    }

    private void cargaPersonasAccidente(int index) throws SQLException {
        ArrayList<Persona> personasAccidente = new ArrayList<>();

        personasAccidente = datos_model.getPersonasInAccidenteById(index);

        personas.clearRows();
        personas.setToolTipRows(personasAccidente);
        if (personasAccidente != null) {
            personas.setData(personasAccidente);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.conde.component.Card_Accident data_Aux_Accidente;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLayeredPane panel;
    private com.conde.swing.PanelBorder panelBorder1;
    private com.conde.component.Card_Persona personas;
    private com.conde.swing.ScrollPaneWin11 s;
    private com.conde.swing.Table_Accidentes table;
    private com.conde.component.textfieldSearch.TextFieldSearchOption txtSearchFiled;
    private com.conde.component.Card_Vehiculos vehiculos;
    // End of variables declaration//GEN-END:variables

}
