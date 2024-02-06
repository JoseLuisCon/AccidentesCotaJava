package com.conde.form;

import com.conde.cell.PanelActionCell;
import com.conde.cell.TableActionEvent;
import com.conde.datechooser.SelectedDate;
import com.conde.model.Accidente;
import com.conde.model.JDBC.Accidentes_JDBC;
import com.conde.model.Vehiculo;
import com.conde.swing.AddCarretera;
import com.conde.swing.AddMotivoDiligencias;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class FormAddAccident extends javax.swing.JPanel {

    Accidentes_JDBC modelAcc = new Accidentes_JDBC();
    private Boolean EditOnVehiculo = false;
    private int rowVehiculoEditado;
    private Boolean EditOnPersona = false;
    private int rowPersonaEditada;

    public FormAddAccident() {
        initComponents();

        setPreferredSize(new Dimension(1920, 968));
        setDataFields();
        setListenersDocument();

        lblLugarTraslado.setVisible(false);
        txtLugarTraslado.setVisible(false);

        TableActionEvent event = new TableActionEvent() {

            @Override
            public void onEdit(JTable table, int row) {

                if (table.getName().equals("TableVehiculos")) {
                    EditOnVehiculo = true;
                    rowVehiculoEditado = row;
                    DefaultTableModel model = (DefaultTableModel) table_Vehiculo_Form_Add.getModel();
                    txtMatricula.setText((String) model.getValueAt(row, 2));
                    txtMarca.setText((String) model.getValueAt(row, 3));
                    txtModelo.setText((String) model.getValueAt(row, 4));
                    txtGestion.setText((String) model.getValueAt(row, 5));
                    txtAObservaciones.setText((String) model.getValueAt(row, 6));

                } else {

                    EditOnPersona = true;
                    rowPersonaEditada = row;
                    DefaultTableModel model = (DefaultTableModel) table_Persona_Form_Add.getModel();
                    txtDni.setText((String) model.getValueAt(row, 2));
                    cmbTipoPersona.setSelectedItem(model.getValueAt(row, 3));
                    cmbVehiculoPer.setSelectedItem(model.getValueAt(row, 4));
                    cmbResultadoPers.setSelectedItem(model.getValueAt(row, 5));
                    if (model.getValueAt(row, 6).equals("")) {
                        chkbTrasladado.setSelected(false);
                        txtLugarTraslado.setText("");
                    } else {
                        chkbTrasladado.setSelected(true);
                        txtLugarTraslado.setText((String) model.getValueAt(row, 6));
                    }

                    chkbPAlcohol.setSelected((boolean) model.getValueAt(row, 7));
                    chkbPAlcoholPos.setSelected((boolean) model.getValueAt(row, 8));
                    chkbPDrogas.setSelected((boolean) model.getValueAt(row, 9));
                    chkbPDrogasPos.setSelected((boolean) model.getValueAt(row, 10));
                    txtAObserv_Personas.setText((String) model.getValueAt(row, 11));

                }

            }

            @Override
            public void onDelete(JTable table, int row) {

                if (table.getName().equals("TableVehiculos")) {
                    if (table_Vehiculo_Form_Add.isEditing()) {
                        table_Vehiculo_Form_Add.getCellEditor().stopCellEditing();
                    }

                    DefaultTableModel model = (DefaultTableModel) table_Vehiculo_Form_Add.getModel();

                    int index = buscarValorEnComboBox(cmbVehiculoPer, (String) model.getValueAt(row, 2));
                    cmbVehiculoPer.removeItemAt(index);
                    model.removeRow(row);
                    limpiarDatosVehiculo();

                } else {
                    if (table_Persona_Form_Add.isEditing()) {
                        table_Persona_Form_Add.getCellEditor().stopCellEditing();
                    }

                    DefaultTableModel model = (DefaultTableModel) table_Persona_Form_Add.getModel();

                    model.removeRow(row);
                    limpiarDatosPersonas();

                }

            }
        };

        table_Vehiculo_Form_Add.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JCheckBox()) {

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

                PanelActionCell action = new PanelActionCell();
                action.initEvent(event, table, row);

                if (isSelected) {
                    action.setBackground(new Color(148, 210, 95));
                } else {
                    action.setBackground(table.getBackground());
                }

                return action;

            }

        });

        table_Persona_Form_Add.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JCheckBox()) {

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

                PanelActionCell action = new PanelActionCell();
                action.initEvent(event, table, row);

                if (isSelected) {
                    action.setBackground(new Color(148, 210, 95));
                } else {
                    action.setBackground(table.getBackground());
                }

                return action;

            }

        });

        panelDatos.setPreferredSize(new Dimension(getWidth(), 200));
        panelVehiculos.setPreferredSize(new Dimension(getWidth(), 300));
        panelPersonas.setPreferredSize(new Dimension(getWidth(), 400));

        panelVeh1.setPreferredSize(new Dimension(900, 300));
        panelVeh1.setPreferredSize(new Dimension(500, 300));

        panelPer1.setPreferredSize(new Dimension(900, 400));
        panelPer2.setPreferredSize(new Dimension(500, 400));

    }

    private void setListenersDocument() {

        txtKilometro.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkCampos();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkCampos();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkCampos();
            }
        });

        txtPatrulla.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkCampos();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkCampos();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkCampos();
            }
        });

    }

    private void checkCampos() {
        String texto1 = txtKilometro.getText();
        String texto2 = txtPatrulla.getText();

        // Habilitar el botón si ambos campos tienen contenido
        btnAnyadir.setEnabled(!texto1.isEmpty() && !texto2.isEmpty());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Hora = new raven.datetime.component.time.TimePicker();
        bntGroupLibroDiligencias = new javax.swing.ButtonGroup();
        Fecha = new com.conde.datechooser.DateChooser();
        panelDatos = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtKilometro = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtADescripcion = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        cmbBoxTipoAccid = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        rbPamplona = new javax.swing.JRadioButton();
        rbTudela = new javax.swing.JRadioButton();
        txtNumDiligencias = new javax.swing.JTextField();
        btnSelectFecha = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        txtPatrulla = new javax.swing.JTextField();
        txtHora = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbCarretera = new javax.swing.JComboBox<>();
        btnAddCarretera = new javax.swing.JButton();
        btnAddMotivoDiligencias = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        btnAnyadir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        panelVehiculos = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        panelVeh1 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
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
        panelVeh2 = new javax.swing.JPanel();
        spWin = new com.conde.swing.ScrollPaneWin11();
        table_Vehiculo_Form_Add = new com.conde.swing.Table_Vehiculo_Form_Add();
        panelPersonas = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        panelPer1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtLugarTraslado = new javax.swing.JTextField();
        lblLugarTraslado = new javax.swing.JLabel();
        Observaciones1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtAObserv_Personas = new javax.swing.JTextArea();
        cmbVehiculoPer = new javax.swing.JComboBox<>();
        cmbResultadoPers = new javax.swing.JComboBox<>();
        chkbTrasladado = new javax.swing.JCheckBox();
        btnAddPersona = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        cmbTipoPersona = new javax.swing.JComboBox<>();
        chkbPAlcohol = new javax.swing.JCheckBox();
        chkbPAlcoholPos = new javax.swing.JCheckBox();
        chkbPDrogas = new javax.swing.JCheckBox();
        chkbPDrogasPos = new javax.swing.JCheckBox();
        panelPer2 = new javax.swing.JPanel();
        spWinPer = new com.conde.swing.ScrollPaneWin11();
        table_Persona_Form_Add = new com.conde.swing.Table_Persona_Form_Add();

        Hora.setEditor(txtHora);

        Fecha.setDateFormat("dd/MM/yyyy");
        Fecha.setTextRefernce(txtFecha);

        setToolTipText("");
        setMaximumSize(new java.awt.Dimension(1424, 968));
        setMinimumSize(new java.awt.Dimension(1424, 968));
        setName("frmAddAccidente"); // NOI18N
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1400, 968));
        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Accidente"));
        panelDatos.setName(""); // NOI18N
        panelDatos.setLayout(new java.awt.CardLayout());

        jLabel4.setText("Carretera");

        jLabel5.setText("Kilómetro");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Descripción");

        txtKilometro.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtADescripcion.setColumns(20);
        txtADescripcion.setLineWrap(true);
        txtADescripcion.setRows(5);
        jScrollPane1.setViewportView(txtADescripcion);

        jLabel8.setText("Tipo accidente");

        cmbBoxTipoAccid.setMaximumRowCount(10);

        jLabel7.setText("Núm. Diligencias");

        jLabel10.setText("Libro Diligencias");

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

        txtNumDiligencias.setEditable(false);
        txtNumDiligencias.setFont(new java.awt.Font("Roboto Black", 2, 14)); // NOI18N
        txtNumDiligencias.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnSelectFecha.setText("...");
        btnSelectFecha.setToolTipText("Seleccionar fecha");
        btnSelectFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectFechaActionPerformed(evt);
            }
        });

        jLabel6.setText("Patrulla");

        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFecha.setToolTipText("Fecha de ocurrencia del accidente");
        txtFecha.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtFecha.setFocusTraversalPolicyProvider(true);
        txtFecha.setName(""); // NOI18N

        txtPatrulla.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel1.setText("Hora");

        jLabel2.setText("Fecha");

        btnAddCarretera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/conde/resources/icons/boton-agregar.png"))); // NOI18N
        btnAddCarretera.setToolTipText("\"Añadir lugar que no sea una carretera...\"");
        btnAddCarretera.setContentAreaFilled(false);
        btnAddCarretera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCarreteraActionPerformed(evt);
            }
        });

        btnAddMotivoDiligencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/conde/resources/icons/boton-agregar.png"))); // NOI18N
        btnAddMotivoDiligencias.setToolTipText("\"Añadir lugar que no sea una carretera...\"");
        btnAddMotivoDiligencias.setContentAreaFilled(false);
        btnAddMotivoDiligencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMotivoDiligenciasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6)))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPatrulla, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSelectFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtHora))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtKilometro, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                            .addComponent(cmbCarretera, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddCarretera, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(12, 12, 12)
                                .addComponent(txtNumDiligencias, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbPamplona)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbTudela))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbBoxTipoAccid, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddMotivoDiligencias, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel10Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtFecha, txtHora, txtPatrulla});

        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSelectFecha)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(cmbCarretera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtKilometro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel10)
                                    .addComponent(rbPamplona)
                                    .addComponent(rbTudela)
                                    .addComponent(txtNumDiligencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAddCarretera, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jLabel8)
                                    .addComponent(cmbBoxTipoAccid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAddMotivoDiligencias, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtPatrulla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))))))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        btnAnyadir.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        btnAnyadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/conde/resources/icons/boton-agregar.png"))); // NOI18N
        btnAnyadir.setText("Añadir");
        btnAnyadir.setEnabled(false);
        btnAnyadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnyadirActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnyadir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAnyadir, btnCancelar});

        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnAnyadir, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAnyadir, btnCancelar});

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelDatos.add(jPanel2, "card2");

        add(panelDatos);

        panelVehiculos.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos vehículos"));
        panelVehiculos.setName(""); // NOI18N
        panelVehiculos.setLayout(new java.awt.CardLayout());

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.X_AXIS));

        panelVeh1.setMaximumSize(new java.awt.Dimension(850, 290));
        panelVeh1.setMinimumSize(new java.awt.Dimension(850, 290));
        panelVeh1.setName(""); // NOI18N
        panelVeh1.setPreferredSize(new java.awt.Dimension(850, 290));
        panelVeh1.setLayout(new java.awt.CardLayout());

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Observaciones)
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane2)
                        .addGap(18, 18, 18)
                        .addComponent(cmbAddVehiculo)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
                            .addComponent(txtGestion))
                        .addGap(90, 90, 90))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Observaciones)
                    .addComponent(cmbAddVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelVeh1.add(jPanel1, "card2");

        jPanel3.add(panelVeh1);
        panelVeh1.getAccessibleContext().setAccessibleDescription("");

        panelVeh2.setMaximumSize(new java.awt.Dimension(550, 290));
        panelVeh2.setMinimumSize(new java.awt.Dimension(550, 290));
        panelVeh2.setName(""); // NOI18N
        panelVeh2.setOpaque(false);
        panelVeh2.setPreferredSize(new java.awt.Dimension(550, 290));
        panelVeh2.setLayout(new java.awt.CardLayout());

        spWin.setBorder(null);
        spWin.setAutoscrolls(true);
        spWin.setViewportView(table_Vehiculo_Form_Add);

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
        table_Vehiculo_Form_Add.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        table_Vehiculo_Form_Add.setMaximumSize(new java.awt.Dimension(2147483647, 500000));
        table_Vehiculo_Form_Add.setMinimumSize(new java.awt.Dimension(550, 0));
        table_Vehiculo_Form_Add.setName("TableVehiculos"); // NOI18N
        table_Vehiculo_Form_Add.setRowHeight(35);
        table_Vehiculo_Form_Add.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        spWin.setViewportView(table_Vehiculo_Form_Add);
        if (table_Vehiculo_Form_Add.getColumnModel().getColumnCount() > 0) {
            table_Vehiculo_Form_Add.getColumnModel().getColumn(0).setMinWidth(0);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(0).setPreferredWidth(0);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(0).setMaxWidth(0);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(1).setMinWidth(80);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(1).setPreferredWidth(80);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(1).setMaxWidth(80);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(2).setMinWidth(100);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(2).setPreferredWidth(100);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(2).setMaxWidth(100);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(5).setMinWidth(0);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(5).setPreferredWidth(0);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(5).setMaxWidth(0);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(6).setMinWidth(0);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(6).setPreferredWidth(0);
            table_Vehiculo_Form_Add.getColumnModel().getColumn(6).setMaxWidth(0);
        }

        panelVeh2.add(spWin, "card2");

        jPanel3.add(panelVeh2);

        panelVehiculos.add(jPanel3, "card2");

        add(panelVehiculos);

        panelPersonas.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos personas"));
        panelPersonas.setLayout(new java.awt.CardLayout());

        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.X_AXIS));

        panelPer1.setMaximumSize(new java.awt.Dimension(850, 400));
        panelPer1.setMinimumSize(new java.awt.Dimension(850, 400));
        panelPer1.setPreferredSize(new java.awt.Dimension(850, 400));
        panelPer1.setLayout(new java.awt.CardLayout());

        jPanel7.setMaximumSize(new java.awt.Dimension(900, 400));
        jPanel7.setMinimumSize(new java.awt.Dimension(900, 400));
        jPanel7.setPreferredSize(new java.awt.Dimension(900, 400));

        jLabel16.setText("DNI/NIE/PASS");

        jLabel17.setText("Resultado");

        jLabel18.setText("Vehículo");

        lblLugarTraslado.setText("A:");

        Observaciones1.setText("Observaciones");

        txtAObserv_Personas.setColumns(20);
        txtAObserv_Personas.setRows(5);
        jScrollPane5.setViewportView(txtAObserv_Personas);

        cmbResultadoPers.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ileso", "Herido Leve", "Herido Grave", "Fallecido" }));

        chkbTrasladado.setText("Trasladado");
        chkbTrasladado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkbTrasladadoActionPerformed(evt);
            }
        });

        btnAddPersona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/conde/resources/icons/Double Right.png"))); // NOI18N
        btnAddPersona.setOpaque(true);
        btnAddPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPersonaActionPerformed(evt);
            }
        });

        jLabel20.setText("Tipo");

        cmbTipoPersona.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoPersonaItemStateChanged(evt);
            }
        });
        cmbTipoPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoPersonaActionPerformed(evt);
            }
        });

        chkbPAlcohol.setText("Prueba de alcohol");

        chkbPAlcoholPos.setText("Positivo en alcohol");
        chkbPAlcoholPos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkbPAlcoholPosActionPerformed(evt);
            }
        });

        chkbPDrogas.setText("Prueba de drogas");
        chkbPDrogas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkbPDrogasActionPerformed(evt);
            }
        });

        chkbPDrogasPos.setText("Positivo en drogas");
        chkbPDrogasPos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkbPDrogasPosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel20)
                        .addGap(16, 16, 16)
                        .addComponent(cmbTipoPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbResultadoPers, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmbVehiculoPer, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(chkbPAlcohol)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(chkbPAlcoholPos, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chkbTrasladado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(lblLugarTraslado)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtLugarTraslado))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(chkbPDrogas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(88, 88, 88)
                                        .addComponent(chkbPDrogasPos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(Observaciones1)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnAddPersona)
                        .addGap(15, 15, 15)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel17)
                    .addComponent(cmbResultadoPers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(29, 29, 29)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbVehiculoPer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(chkbTrasladado)
                    .addComponent(lblLugarTraslado)
                    .addComponent(txtLugarTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkbPAlcohol)
                        .addComponent(chkbPAlcoholPos))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkbPDrogasPos)
                        .addComponent(chkbPDrogas)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Observaciones1)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(btnAddPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        panelPer1.add(jPanel7, "card2");

        jPanel4.add(panelPer1);

        panelPer2.setMaximumSize(new java.awt.Dimension(550, 380));
        panelPer2.setMinimumSize(new java.awt.Dimension(550, 380));
        panelPer2.setName(""); // NOI18N
        panelPer2.setPreferredSize(new java.awt.Dimension(550, 380));
        panelPer2.setLayout(new java.awt.CardLayout());

        spWinPer.setBorder(null);

        table_Persona_Form_Add.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Acciones", "Documento", "Tipo", "Vehículo", "Resultado", "Trasladado", "PAlcoh", "PAlcoh+", "PDrog", "PDrog+", "Observaciones"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_Persona_Form_Add.setRowHeight(40);
        spWinPer.setViewportView(table_Persona_Form_Add);
        if (table_Persona_Form_Add.getColumnModel().getColumnCount() > 0) {
            table_Persona_Form_Add.getColumnModel().getColumn(0).setMinWidth(0);
            table_Persona_Form_Add.getColumnModel().getColumn(0).setPreferredWidth(0);
            table_Persona_Form_Add.getColumnModel().getColumn(0).setMaxWidth(0);
            table_Persona_Form_Add.getColumnModel().getColumn(1).setMinWidth(80);
            table_Persona_Form_Add.getColumnModel().getColumn(1).setPreferredWidth(80);
            table_Persona_Form_Add.getColumnModel().getColumn(1).setMaxWidth(80);
            table_Persona_Form_Add.getColumnModel().getColumn(2).setMinWidth(100);
            table_Persona_Form_Add.getColumnModel().getColumn(2).setPreferredWidth(100);
            table_Persona_Form_Add.getColumnModel().getColumn(2).setMaxWidth(100);
            table_Persona_Form_Add.getColumnModel().getColumn(5).setMinWidth(80);
            table_Persona_Form_Add.getColumnModel().getColumn(5).setPreferredWidth(80);
            table_Persona_Form_Add.getColumnModel().getColumn(5).setMaxWidth(80);
            table_Persona_Form_Add.getColumnModel().getColumn(6).setMinWidth(0);
            table_Persona_Form_Add.getColumnModel().getColumn(6).setPreferredWidth(0);
            table_Persona_Form_Add.getColumnModel().getColumn(6).setMaxWidth(0);
            table_Persona_Form_Add.getColumnModel().getColumn(7).setMinWidth(0);
            table_Persona_Form_Add.getColumnModel().getColumn(7).setPreferredWidth(0);
            table_Persona_Form_Add.getColumnModel().getColumn(7).setMaxWidth(0);
            table_Persona_Form_Add.getColumnModel().getColumn(8).setMinWidth(0);
            table_Persona_Form_Add.getColumnModel().getColumn(8).setPreferredWidth(0);
            table_Persona_Form_Add.getColumnModel().getColumn(8).setMaxWidth(0);
            table_Persona_Form_Add.getColumnModel().getColumn(9).setMinWidth(0);
            table_Persona_Form_Add.getColumnModel().getColumn(9).setPreferredWidth(0);
            table_Persona_Form_Add.getColumnModel().getColumn(9).setMaxWidth(0);
            table_Persona_Form_Add.getColumnModel().getColumn(10).setMinWidth(0);
            table_Persona_Form_Add.getColumnModel().getColumn(10).setPreferredWidth(0);
            table_Persona_Form_Add.getColumnModel().getColumn(10).setMaxWidth(0);
            table_Persona_Form_Add.getColumnModel().getColumn(11).setMinWidth(0);
            table_Persona_Form_Add.getColumnModel().getColumn(11).setPreferredWidth(0);
            table_Persona_Form_Add.getColumnModel().getColumn(11).setMaxWidth(0);
        }

        panelPer2.add(spWinPer, "card2");

        jPanel4.add(panelPer2);

        panelPersonas.add(jPanel4, "card2");

        add(panelPersonas);
    }// </editor-fold>//GEN-END:initComponents

    private void rbTudelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTudelaActionPerformed

        setNumeroDiligencias("TUDELA");
    }//GEN-LAST:event_rbTudelaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed


    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSelectFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectFechaActionPerformed
        Fecha.showPopup();
    }//GEN-LAST:event_btnSelectFechaActionPerformed

    private void rbPamplonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPamplonaActionPerformed
        // Obtenemos el número de diligencias más alto del Equipo de Pamplona

        setNumeroDiligencias("PAMPLONA");


    }//GEN-LAST:event_rbPamplonaActionPerformed

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        setNumeroDiligencias("PAMPLONA");
    }//GEN-LAST:event_formAncestorAdded

    private void cmbAddVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAddVehiculoActionPerformed
        DefaultTableModel tblModel = (DefaultTableModel) table_Vehiculo_Form_Add.getModel();

        int num_vehiculos = tblModel.getRowCount() + 1;

        if (EditOnVehiculo && tblModel.getRowCount() > 0) {

            tblModel.removeRow(rowVehiculoEditado);
            EditOnVehiculo = false;
            num_vehiculos = (int) tblModel.getValueAt(rowVehiculoEditado, 0);
        }

        //COMPROBACIONES
        //Minimo matrícula y 6 caracteres
        if (txtMatricula.getText().isEmpty() || txtMatricula.getText().length() < 7) {
            JOptionPane.showMessageDialog(null, "Como mínimo poner una matrícula o nº serie\n y debe tener mínimo 6 caracteres");
            return;
        }

        // Comprobramos que no esté duplicada la matrícula
        if (tieneDuplicado(tblModel, 2, (Object) txtMatricula.getText().toUpperCase())) {
            JOptionPane.showMessageDialog(null, "Matrícula repetida,\n compruebe los datos");
            return;
        }

        Object[] rowData = {num_vehiculos, null, txtMatricula.getText().toUpperCase(), txtMarca.getText(), txtModelo.getText(), txtGestion.getText(), txtAObservaciones.getText()};
        tblModel.addRow(rowData);

        llenarComboMatriculaPersona();

        limpiarDatosVehiculo();

    }//GEN-LAST:event_cmbAddVehiculoActionPerformed

    private void chkbPAlcoholPosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkbPAlcoholPosActionPerformed

    }//GEN-LAST:event_chkbPAlcoholPosActionPerformed

    private void chkbPDrogasPosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkbPDrogasPosActionPerformed

    }//GEN-LAST:event_chkbPDrogasPosActionPerformed

    private void chkbPDrogasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkbPDrogasActionPerformed

    }//GEN-LAST:event_chkbPDrogasActionPerformed

    private void chkbTrasladadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkbTrasladadoActionPerformed

    }//GEN-LAST:event_chkbTrasladadoActionPerformed

    private void btnAddPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPersonaActionPerformed
        DefaultTableModel tblModel = (DefaultTableModel) table_Persona_Form_Add.getModel();

        if (EditOnPersona && tblModel.getRowCount() > 0) {

            tblModel.removeRow(rowPersonaEditada);
            EditOnPersona = false;

        }

        //Minimo matrícula y 6 caracteres
        if (txtDni.getText().isEmpty() || txtDni.getText().length() < 7) {
            JOptionPane.showMessageDialog(null, "Como mínimo poner un número de documento \n y debe tener mínimo 6 caracteres");
            return;
        }

        // Comprobramos que no esté duplicada la matrícula
        if (tieneDuplicado(tblModel, 2, (Object) txtDni.getText().toUpperCase())) {
            JOptionPane.showMessageDialog(null, "Persona repetida,\n compruebe los datos");
            return;
        }

        if (cmbVehiculoPer.getSelectedItem() == null) {
            if ((cmbTipoPersona.getSelectedItem().equals("CONDUCTOR") || cmbTipoPersona.getSelectedItem().equals("OCUPANTE"))) {
                JOptionPane.showMessageDialog(null, "Tiene que asignarle un vehículo por el tipo de persona elegido: \n Compruebe los datos");
                return;

            }
        }

        // Comprobamos que no se duplica el conductor para un mismo vehículo
        if (cmbVehiculoPer.getSelectedItem() != null) {
            if (conductorDuplicado(tblModel, (Object) cmbVehiculoPer.getSelectedItem())) {
                JOptionPane.showMessageDialog(null, "Ya existe un conductor para el vehículo: " + cmbVehiculoPer.getSelectedItem() + "\n Compruebe los datos");
                return;
            }

        }

        int num_persona = tblModel.getRowCount() + 1;

        Object[] rowData = {
            num_persona,
            null,
            txtDni.getText().toUpperCase(),
            cmbTipoPersona.getSelectedItem(),
            cmbVehiculoPer.getSelectedItem(),
            cmbResultadoPers.getSelectedItem(),
            txtLugarTraslado.getText(),
            chkbPAlcohol.isSelected(),
            chkbPAlcoholPos.isSelected(),
            chkbPDrogas.isSelected(),
            chkbPDrogasPos.isSelected(),
            txtAObserv_Personas.getText()

        };

        tblModel.addRow(rowData);

        limpiarDatosPersonas();

    }//GEN-LAST:event_btnAddPersonaActionPerformed

    private void cmbTipoPersonaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoPersonaItemStateChanged

    }//GEN-LAST:event_cmbTipoPersonaItemStateChanged

    private void cmbTipoPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoPersonaActionPerformed
        String elementoSeleccionado = (String) cmbTipoPersona.getSelectedItem();
        if (elementoSeleccionado.equals("PEATÓN")) {
            cmbVehiculoPer.setEnabled(false);
        } else {
            cmbVehiculoPer.setEnabled(true);
        }
    }//GEN-LAST:event_cmbTipoPersonaActionPerformed

    private void btnAddCarreteraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCarreteraActionPerformed

        if (FlatLaf.isLafDark()) {
            FlatDarkLaf.registerCustomDefaultsSource("com/conde/style");
            FlatDarkLaf.setup();
            btnAddCarretera.setIcon(new ImageIcon("src/com/conde/resources/icons/boton-agregar_white.png"));
        } else {
            FlatLightLaf.registerCustomDefaultsSource("com/conde/style");
            FlatLightLaf.setup();
        }

        AddCarretera modalDialog = new AddCarretera(null, true);
        modalDialog.setSize(400, 300);
        modalDialog.setLocationRelativeTo(this);
        modalDialog.setVisible(true);

        cmbCarretera.setModel(new DefaultComboBoxModel<>((String[]) modelAcc.getCarreteras().toArray(new String[0])));
    }//GEN-LAST:event_btnAddCarreteraActionPerformed

    private void btnAnyadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnyadirActionPerformed
        // Datos accidente
        Accidente newAccidente = new Accidente();

        SelectedDate d = Fecha.getSelectedDate();
        newAccidente.setFecha(d.getDay() + "/" + d.getMonth() + "/" + d.getYear());

        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");
        newAccidente.setHora(Hora.getSelectedTime().format(df));

        newAccidente.setCarretera(cmbCarretera.getSelectedItem().toString().toUpperCase());
        newAccidente.setKilometro(txtKilometro.getText());
        newAccidente.setPatrulla(txtPatrulla.getText().toUpperCase());
        newAccidente.setNum_Diligencias(Integer.parseInt(txtNumDiligencias.getText()));

        newAccidente.setTipo_Siniestro(modelAcc.getTipoAccidenteByTYPE(cmbBoxTipoAccid.getSelectedItem()));
        newAccidente.setZona_Atestados((rbPamplona.isSelected() ? "Pamplona" : "Tudela"));
        newAccidente.setDescripcion(txtADescripcion.getText());

       modelAcc.AddNuevoAccidente(newAccidente);

        //Listado vehículos
        DefaultTableModel modelVehiculos = (DefaultTableModel) table_Vehiculo_Form_Add.getModel();
        
        ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
        
        for (int row = 0; row < modelVehiculos.getRowCount(); row++) {
            Vehiculo veh = new Vehiculo();
            veh.setNum_Accidente(modelAcc.getNumAccidenteByNumDiligencias(Integer.parseInt(txtNumDiligencias.getText())));
            veh.setMatricula((String) modelVehiculos.getValueAt(row, 2));
            veh.setMarca((String) modelVehiculos.getValueAt(row, 3));
            veh.setModelo((String) modelVehiculos.getValueAt(row, 4));
            veh.setGestion((String) modelVehiculos.getValueAt(row, 5));
            veh.setObservaciones((String) modelVehiculos.getValueAt(row, 6));
            listaVehiculos.add(veh);            
        }
        
        if (!listaVehiculos.isEmpty()){
            
            modelAcc.addListadoVehiculos(listaVehiculos);
        
        }

        //Listado personas
    }//GEN-LAST:event_btnAnyadirActionPerformed

    private void btnAddMotivoDiligenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMotivoDiligenciasActionPerformed
        if (FlatLaf.isLafDark()) {
            FlatDarkLaf.registerCustomDefaultsSource("com/conde/style");
            FlatDarkLaf.setup();
            btnAddMotivoDiligencias.setIcon(new ImageIcon("src/com/conde/resources/icons/boton-agregar_white.png"));
        } else {
            FlatLightLaf.registerCustomDefaultsSource("com/conde/style");
            FlatLightLaf.setup();
        }

        AddMotivoDiligencias modalDialog = new AddMotivoDiligencias(null, true);
        modalDialog.setSize(400, 300);
        modalDialog.setLocationRelativeTo(this);
        modalDialog.setVisible(true);

        cmbBoxTipoAccid.setModel(new DefaultComboBoxModel<>((String[]) modelAcc.getTiposAccidentes().toArray(new String[0])));
    }//GEN-LAST:event_btnAddMotivoDiligenciasActionPerformed

    private void setNumeroDiligencias(String equipo) {

        SelectedDate fecha = Fecha.getSelectedDate();

        String fechaFormateadaAccess = fecha.getMonth() + "/" + fecha.getDay() + "/" + fecha.getYear();
        int Num_Diligencias = modelAcc.getNumDiligencias(equipo, fechaFormateadaAccess);
        txtNumDiligencias.setText(String.valueOf(Num_Diligencias + 1));

    }

    private void limpiarDatosVehiculo() {
        txtMatricula.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtGestion.setText("");
        txtAObservaciones.setText("");

    }

    private void limpiarDatosPersonas() {
        txtDni.setText("");
        cmbTipoPersona.setSelectedIndex(0);
        if (cmbVehiculoPer.getItemCount() > 0) {
            cmbVehiculoPer.setSelectedIndex(0);
        }
        cmbResultadoPers.setSelectedIndex(0);
        chkbTrasladado.setSelected(false);
        txtLugarTraslado.setText("");
        chkbPAlcohol.setSelected(false);
        chkbPAlcoholPos.setSelected(false);
        chkbPDrogas.setSelected(false);
        chkbPDrogasPos.setSelected(false);
        txtAObserv_Personas.setText("");

    }

    private void setDataFields() {

        Hora.set24HourView(true);
        Hora.now();
        Hora.setOrientation(SwingConstants.HORIZONTAL);
        Hora.setEditor(txtHora);

        cmbCarretera.setModel(new DefaultComboBoxModel<>((String[]) modelAcc.getCarreteras().toArray(new String[0])));

        cmbBoxTipoAccid.setModel(new DefaultComboBoxModel<>((String[]) modelAcc.getTiposAccidentes().toArray(new String[0])));

        cmbTipoPersona.setModel(new DefaultComboBoxModel<>((String[]) modelAcc.getTiposPersonas().toArray(new String[0])));

        // Listener para detectar cambios en checkbox traslado
        chkbTrasladado.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    lblLugarTraslado.setVisible(true);
                    txtLugarTraslado.setVisible(true);
                } else {
                    lblLugarTraslado.setVisible(false);
                    txtLugarTraslado.setVisible(false);
                }
            }
        });

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

    private boolean conductorDuplicado(DefaultTableModel model, Object matricula) {
        for (int fila = 0; fila < model.getRowCount(); fila++) {
            Object valorEnFila = model.getValueAt(fila, 4);

            // Si ya existe la matricula
            if (matricula.equals(valorEnFila)) {
                // Si ya tiene un conductor selecccionado como tipo persona
                if (model.getValueAt(fila, 3).equals(cmbTipoPersona.getSelectedItem())) {
                    return true;
                }
            }
        }

        // No tiene conductor duplicado.
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

    private void llenarComboMatriculaPersona() {

        cmbVehiculoPer.removeAllItems();
        cmbVehiculoPer.addItem("");
        DefaultTableModel tblModel = new DefaultTableModel();
        tblModel = (DefaultTableModel) table_Vehiculo_Form_Add.getModel();
        for (int i = 0; i < tblModel.getRowCount(); i++) {
            cmbVehiculoPer.addItem((String) tblModel.getValueAt(i, 2));
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.conde.datechooser.DateChooser Fecha;
    private raven.datetime.component.time.TimePicker Hora;
    private javax.swing.JLabel Observaciones;
    private javax.swing.JLabel Observaciones1;
    private javax.swing.ButtonGroup bntGroupLibroDiligencias;
    private javax.swing.JButton btnAddCarretera;
    private javax.swing.JButton btnAddMotivoDiligencias;
    private javax.swing.JButton btnAddPersona;
    private javax.swing.JButton btnAnyadir;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSelectFecha;
    private javax.swing.JCheckBox chkbPAlcohol;
    private javax.swing.JCheckBox chkbPAlcoholPos;
    private javax.swing.JCheckBox chkbPDrogas;
    private javax.swing.JCheckBox chkbPDrogasPos;
    private javax.swing.JCheckBox chkbTrasladado;
    private javax.swing.JButton cmbAddVehiculo;
    private javax.swing.JComboBox<String> cmbBoxTipoAccid;
    private javax.swing.JComboBox<String> cmbCarretera;
    private javax.swing.JComboBox<String> cmbResultadoPers;
    private javax.swing.JComboBox<String> cmbTipoPersona;
    private javax.swing.JComboBox<String> cmbVehiculoPer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblLugarTraslado;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelPer1;
    private javax.swing.JPanel panelPer2;
    private javax.swing.JPanel panelPersonas;
    private javax.swing.JPanel panelVeh1;
    private javax.swing.JPanel panelVeh2;
    private javax.swing.JPanel panelVehiculos;
    private javax.swing.JRadioButton rbPamplona;
    private javax.swing.JRadioButton rbTudela;
    private com.conde.swing.ScrollPaneWin11 spWin;
    private com.conde.swing.ScrollPaneWin11 spWinPer;
    private com.conde.swing.Table_Persona_Form_Add table_Persona_Form_Add;
    private com.conde.swing.Table_Vehiculo_Form_Add table_Vehiculo_Form_Add;
    private javax.swing.JTextArea txtADescripcion;
    private javax.swing.JTextArea txtAObserv_Personas;
    private javax.swing.JTextArea txtAObservaciones;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtGestion;
    private javax.swing.JFormattedTextField txtHora;
    private javax.swing.JTextField txtKilometro;
    private javax.swing.JTextField txtLugarTraslado;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtNumDiligencias;
    private javax.swing.JTextField txtPatrulla;
    // End of variables declaration//GEN-END:variables

}
