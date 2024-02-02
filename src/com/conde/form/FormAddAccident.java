package com.conde.form;

import com.conde.cell.PanelActionCell;
import com.conde.cell.TableActionEvent;
import com.conde.datechooser.SelectedDate;
import com.conde.event.EventRowSelected;
import com.conde.model.JDBC.Accidentes_JDBC;
import com.conde.swing.Table_Vehiculo_Form_Add;
import java.awt.Color;
import java.awt.Component;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class FormAddAccident extends javax.swing.JPanel {

    Accidentes_JDBC modelAcc = new Accidentes_JDBC();

    public FormAddAccident() {
        initComponents();

        setDataFields();

//        DefaultTableModel modelo = new DefaultTableModel(4, 5);
//        
//        modelo.setValueAt(1, 0, 0);
//        modelo.setValueAt("1234asd", 0, 2);
//        modelo.setValueAt("Renault", 0,3);
//       modelo.setValueAt("Vectra", 0,4);
//       
//       modelo.setValueAt(2, 1, 0);
//        modelo.setValueAt("1234asd",1, 2);
//        modelo.setValueAt("Renault", 1,3);
//       modelo.setValueAt("Vectra", 1,4);
//       
//       modelo.setValueAt(3, 2, 0);
//        modelo.setValueAt("1234asd",2, 2);
//        modelo.setValueAt("Renault", 2,3);
//       modelo.setValueAt("Vectra", 2,4);
//       
//       modelo.setValueAt(4, 3, 0);
//        modelo.setValueAt("1234asd", 3, 2);
//        modelo.setValueAt("Renault", 3,3);
//       modelo.setValueAt("Vectra", 3,4);
//       
//       table_Vehiculo_Form_Add.setModel(modelo);
//        
//       table_Vehiculo_Form_Add.addEventRowSelected((int e)->{
//       
//            System.out.println("Fila selecionada: "+e);
//            
//            
//            
//       
//       });

        TableActionEvent event = new TableActionEvent() {
            
            @Override
            public void onEdit(int row) {
                DefaultTableModel model = (DefaultTableModel) table_Vehiculo_Form_Add.getModel();
                txtMatricula.setText((String) model.getValueAt(row, 2));
                txtMarca.setText((String) model.getValueAt(row, 3));
                txtModelo.setText((String) model.getValueAt(row, 4));
                txtGestion.setText((String) model.getValueAt(row, 5));
                txtAObservaciones.setText((String) model.getValueAt(row, 6));
            }

            @Override
            public void onDelete(int row) {
                if (table_Vehiculo_Form_Add.isEditing()) {
                    table_Vehiculo_Form_Add.getCellEditor().stopCellEditing();
                }

                DefaultTableModel model = (DefaultTableModel) table_Vehiculo_Form_Add.getModel();
                
                int index = buscarValorEnComboBox(cmbVehiculoPer, (String) model.getValueAt(row, 2));
                cmbVehiculoPer.removeItemAt(index);
                model.removeRow(row);
                

            }
        };

        
        table_Vehiculo_Form_Add.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JCheckBox()) {

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

                PanelActionCell action = new PanelActionCell();
                action.initEvent(event, row);
                
                if (isSelected) {
                    action.setBackground(new Color(148, 210, 95));
                } else {
                    action.setBackground(table.getBackground());
                }

                return action;

            }

        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Hora = new raven.datetime.component.time.TimePicker();
        bntGroupLibroDiligencias = new javax.swing.ButtonGroup();
        Fecha = new com.conde.datechooser.DateChooser();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        txtNumDiligencias = new javax.swing.JTextField();
        txtPatrulla = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCarretera = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cmbBoxTipoAccid = new javax.swing.JComboBox<>();
        rbPamplona = new javax.swing.JRadioButton();
        rbTudela = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btnAnyadir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtHora = new javax.swing.JFormattedTextField();
        btnSelectFecha = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtMatricula = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        txtGestion = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        Observaciones = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAObservaciones = new javax.swing.JTextArea();
        cmbAddVehiculo = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_Vehiculo_Form_Add = new com.conde.swing.Table_Vehiculo_Form_Add();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtLugarTraslado = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        Observaciones1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtAObserv_Personas = new javax.swing.JTextArea();
        cmbVehiculoPer = new javax.swing.JComboBox<>();
        cmbResultadoPers = new javax.swing.JComboBox<>();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        cmbAddVehiculo1 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        cmbVehiculoPer1 = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTblPersonasAdd = new javax.swing.JTable();

        Hora.setEditor(txtHora);

        Fecha.setDateFormat("dd/MM/yyyy");
        Fecha.setTextRefernce(txtFecha);

        setToolTipText("");
        setMaximumSize(new java.awt.Dimension(1172, 900));
        setMinimumSize(new java.awt.Dimension(1172, 800));
        setName("frmAddAccidente"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1172, 800));
        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        setLayout(new java.awt.GridLayout(3, 1, 0, 5));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Accidente"));

        jLabel1.setText("Hora");

        jLabel2.setText("Fecha");

        jLabel4.setText("Carretera");

        jLabel5.setText("Kilómetro");

        jLabel6.setText("Patrulla");

        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFecha.setToolTipText("Fecha de ocurrencia del accidente");
        txtFecha.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtFecha.setFocusTraversalPolicyProvider(true);
        txtFecha.setName(""); // NOI18N

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtNumDiligencias.setEditable(false);
        txtNumDiligencias.setFont(new java.awt.Font("Roboto Black", 2, 14)); // NOI18N
        txtNumDiligencias.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtPatrulla.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setText("Núm. Diligencias");

        txtCarretera.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setText("Tipo accidente");

        cmbBoxTipoAccid.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        bntGroupLibroDiligencias.add(rbPamplona);
        rbPamplona.setSelected(true);
        rbPamplona.setText("Pamplona");
        rbPamplona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPamplonaActionPerformed(evt);
            }
        });

        bntGroupLibroDiligencias.add(rbTudela);
        rbTudela.setText("Tudela");
        rbTudela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTudelaActionPerformed(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Descripción");

        jLabel10.setText("Libro Diligencias");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        btnAnyadir.setText("Añadir");
        btnAnyadir.setEnabled(false);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnSelectFecha.setText("...");
        btnSelectFecha.setToolTipText("Seleccionar fecha");
        btnSelectFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectFechaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtHora, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addComponent(txtFecha)
                    .addComponent(txtPatrulla))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSelectFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                            .addComponent(txtCarretera, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtNumDiligencias, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbPamplona)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbTudela))
                            .addComponent(cmbBoxTipoAccid, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1))
                .addGap(18, 48, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(btnAnyadir, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSelectFecha)
                    .addComponent(jLabel4)
                    .addComponent(txtCarretera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtNumDiligencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(rbPamplona)
                    .addComponent(rbTudela))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cmbBoxTipoAccid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(btnAnyadir)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancelar)
                            .addGap(146, 146, 146)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel6)
                            .addComponent(txtPatrulla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(106, 106, 106))))
        );

        add(jPanel4);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos vehículos"));
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridLayout(1, 3, 0, 5));

        jLabel12.setText("Matrícula");

        jLabel13.setText("Marca");

        jLabel14.setText("Modelo");

        jLabel15.setText("Gestión");

        Observaciones.setText("Observaciones");

        txtAObservaciones.setColumns(20);
        txtAObservaciones.setRows(5);
        jScrollPane2.setViewportView(txtAObservaciones);

        cmbAddVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/conde/resources/icons/Double Right.png"))); // NOI18N
        cmbAddVehiculo.setToolTipText("Añadir vehículos a la lista");
        cmbAddVehiculo.setOpaque(true);
        cmbAddVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAddVehiculoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtGestion))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(Observaciones)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)))
                .addGap(18, 18, 18)
                .addComponent(cmbAddVehiculo)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtGestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Observaciones)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(14, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbAddVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))))
        );

        jPanel3.add(jPanel5);

        jPanel6.setLayout(new java.awt.CardLayout(5, 5));

        table_Vehiculo_Form_Add.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Acciones", "Matricula", "Marca", "Modelo", "Gestiones", "Observaciones"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_Vehiculo_Form_Add.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table_Vehiculo_Form_Add.setRowHeight(40);
        table_Vehiculo_Form_Add.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(table_Vehiculo_Form_Add);
        if (table_Vehiculo_Form_Add.getColumnModel().getColumnCount() > 0) {
            table_Vehiculo_Form_Add.getColumnModel().getColumn(0).setMinWidth(0);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(0).setPreferredWidth(0);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(0).setMaxWidth(0);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(1).setMinWidth(100);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(1).setPreferredWidth(100);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(1).setMaxWidth(100);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(2).setMinWidth(150);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(2).setPreferredWidth(150);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(2).setMaxWidth(150);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(3).setMinWidth(150);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(3).setPreferredWidth(150);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(3).setMaxWidth(150);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(4).setMinWidth(150);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(4).setPreferredWidth(150);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(4).setMaxWidth(150);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(5).setMinWidth(0);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(5).setPreferredWidth(0);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(5).setMaxWidth(0);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(6).setMinWidth(0);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(6).setPreferredWidth(0);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(6).setMaxWidth(0);
        }

        jPanel6.add(jScrollPane3, "card2");

        jPanel3.add(jPanel6);

        add(jPanel3);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos personas"));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jLabel16.setText("DNI/NIE/PASS");

        jLabel17.setText("Resultado");

        jLabel18.setText("Vehículo");

        jLabel19.setText("Lugar traslado");

        Observaciones1.setText("Observaciones");

        txtAObserv_Personas.setColumns(20);
        txtAObserv_Personas.setRows(5);
        jScrollPane5.setViewportView(txtAObserv_Personas);

        cmbResultadoPers.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ileso", "Herido Leve", "Herido Grave", "Fallecido" }));

        jCheckBox2.setText("Trasladado");

        jCheckBox1.setText("Prueba de alcohol");

        jCheckBox4.setText("Prueba de drogas");
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        jCheckBox5.setText("Positivo en drogas");
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        jCheckBox3.setText("Positivo en alcohol");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        cmbAddVehiculo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/conde/resources/icons/Double Right.png"))); // NOI18N
        cmbAddVehiculo1.setOpaque(true);

        jLabel20.setText("Tipo Persona: ");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbVehiculoPer, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbVehiculoPer1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel10Layout.createSequentialGroup()
                                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jCheckBox4)
                                                .addComponent(jCheckBox1))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jCheckBox5)
                                                .addComponent(jCheckBox3)))
                                        .addGroup(jPanel10Layout.createSequentialGroup()
                                            .addComponent(jLabel17)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cmbResultadoPers, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(72, 72, 72)))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jCheckBox2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel19))))
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtLugarTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addComponent(Observaciones1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(cmbAddVehiculo1)
                        .addGap(5, 5, 5))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cmbVehiculoPer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(cmbVehiculoPer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cmbResultadoPers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox2)
                            .addComponent(jLabel19)
                            .addComponent(txtLugarTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox3)
                            .addComponent(jCheckBox1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox4)
                            .addComponent(jCheckBox5))
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbAddVehiculo1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Observaciones1))
                .addContainerGap())
        );

        jPanel2.add(jPanel10);

        jPanel9.setLayout(new java.awt.CardLayout(5, 5));

        jTblPersonasAdd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTblPersonasAdd);

        jPanel9.add(jScrollPane4, "card2");

        jPanel2.add(jPanel9);

        add(jPanel2);
    }// </editor-fold>//GEN-END:initComponents

    private void rbTudelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTudelaActionPerformed

        setNumeroDiligencias("TUDELA");
    }//GEN-LAST:event_rbTudelaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSelectFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectFechaActionPerformed
        Fecha.showPopup();
    }//GEN-LAST:event_btnSelectFechaActionPerformed

    private void rbPamplonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPamplonaActionPerformed
        // Obtenemos el número de diligencias más alto del Equipo de Pamplona

        //Primero comparamos fecha seleccionada no sea posterior a la de hoy, no somos adivinos
        setNumeroDiligencias("PAMPLONA");

    }//GEN-LAST:event_rbPamplonaActionPerformed

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        setNumeroDiligencias("PAMPLONA");
    }//GEN-LAST:event_formAncestorAdded

    private void cmbAddVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAddVehiculoActionPerformed
        DefaultTableModel tblModel = (DefaultTableModel) table_Vehiculo_Form_Add.getModel();

        //COMPROBACIONES
        //Minimo matrícula y 6 caracteres
        if (txtMatricula.getText().isEmpty() || txtMatricula.getText().length() < 7) {
            JOptionPane.showMessageDialog(null, "Como mínimo poner una matrícula o nº serie\n y debe tener mínimo 6 caracteres");
            return;
        }
        
        // Comprobramos que no esté duplicada la matrícula
        
        if (tieneDuplicado(tblModel, 2, (Object) txtMatricula.getText().toUpperCase())){
            JOptionPane.showMessageDialog(null, "Matrícula repetida,\n compruebe los datos");
            return;
        
        }
        

        int num_vehiculos = tblModel.getRowCount() + 1;

        Object[] rowData = {num_vehiculos, null, txtMatricula.getText().toUpperCase(), txtMarca.getText(), txtModelo.getText(), txtGestion.getText(), txtAObservaciones.getText()};
        tblModel.addRow(rowData);
        
        cmbVehiculoPer.addItem(txtMatricula.getText().toUpperCase());
        
        txtMatricula.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtGestion.setText("");
        txtAObservaciones.setText("");

    }//GEN-LAST:event_cmbAddVehiculoActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void setNumeroDiligencias(String equipo) {
        SelectedDate fecha = Fecha.getSelectedDate();

        Calendar fechaSelecionada = Calendar.getInstance();
        fechaSelecionada.set(fecha.getYear(), fecha.getMonth() - 1, fecha.getDay());

        Calendar hoy = Calendar.getInstance();

        if (fechaSelecionada.after(hoy)) {
            System.out.println("Se ha selecionado una fecha posterior a la actual");

        } else {
            String fechaFormateadaAccess = fecha.getMonth() + "/" + fecha.getDay() + "/" + fecha.getYear();
            int Num_Diligencias = modelAcc.getNumDiligencias(equipo, fechaFormateadaAccess);
            txtNumDiligencias.setText(String.valueOf(Num_Diligencias + 1));
        }

    }
    
    

    private void setDataFields() {

        Hora.set24HourView(true);
        Hora.now();
        Hora.setOrientation(SwingConstants.HORIZONTAL);
        Hora.setEditor(txtHora);

        cmbBoxTipoAccid.setModel(new DefaultComboBoxModel<>((String[]) modelAcc.getTiposAccidentes().toArray(new String[0])));
    }
    
    
    private static boolean tieneDuplicado(DefaultTableModel model, int columnaABuscar, Object nuevoValor) {
        for (int fila = 0; fila < model.getRowCount(); fila++) {
            Object valorEnFila = model.getValueAt(fila, columnaABuscar);

            // Si el nuevo valor coincide con algún valor existente, hay un duplicado
            if (nuevoValor.equals(valorEnFila)) {
                return true;
            }
        }

        // No se encontraron duplicados
        return false;
    }
    
    private int buscarValorEnComboBox(JComboBox<String> comboBox, String valorABuscar) {
        int itemCount = comboBox.getItemCount();

        for (int i = 0; i < itemCount; i++) {
            String elemento = comboBox.getItemAt(i);

            // Verificar si el valor coincide
            if (valorABuscar.equals(elemento)) {
                System.out.println("Valor encontrado en la posición " + i);
                return i; // Se encontró el valor, se puede salir del bucle
            }
        }

        // Si el valor no se encuentra
        return -1;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.conde.datechooser.DateChooser Fecha;
    private raven.datetime.component.time.TimePicker Hora;
    private javax.swing.JLabel Observaciones;
    private javax.swing.JLabel Observaciones1;
    private javax.swing.ButtonGroup bntGroupLibroDiligencias;
    private javax.swing.JButton btnAnyadir;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSelectFecha;
    private javax.swing.JButton cmbAddVehiculo;
    private javax.swing.JButton cmbAddVehiculo1;
    private javax.swing.JComboBox<String> cmbBoxTipoAccid;
    private javax.swing.JComboBox<String> cmbResultadoPers;
    private javax.swing.JComboBox<String> cmbVehiculoPer;
    private javax.swing.JComboBox<String> cmbVehiculoPer1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTblPersonasAdd;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JRadioButton rbPamplona;
    private javax.swing.JRadioButton rbTudela;
    private com.conde.swing.Table_Vehiculo_Form_Add table_Vehiculo_Form_Add;
    private javax.swing.JTextArea txtAObserv_Personas;
    private javax.swing.JTextArea txtAObservaciones;
    private javax.swing.JTextField txtCarretera;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtGestion;
    private javax.swing.JFormattedTextField txtHora;
    private javax.swing.JTextField txtLugarTraslado;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtNumDiligencias;
    private javax.swing.JTextField txtPatrulla;
    // End of variables declaration//GEN-END:variables

}
