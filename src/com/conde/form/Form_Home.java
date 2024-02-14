package com.conde.form;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.conde.cell.TableActionCellEditor;
import com.conde.cell.TableActionEvent;
import com.conde.component.textfieldSearch.SearchOptinEvent;
import com.conde.component.textfieldSearch.SearchOption;
import com.conde.main.Main;
import com.conde.model.JDBC.Accidentes_JDBC;
import com.conde.model.Accidente;
import com.conde.model.Persona;
import com.conde.model.Vehiculo;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.lang.Object;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

public class Form_Home extends javax.swing.JPanel {

    private ArrayList<Accidente> listAccidents = new ArrayList<>();
    private Accidentes_JDBC datos_model = new Accidentes_JDBC();
    private String cadenaBusqueda = "";
 

    public Form_Home(Main m) {

        initComponents();

        JPanel p = new JPanel();
//        p.setBackground(Color.WHITE);
        s.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        s.getViewport().setBackground(Color.WHITE);

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(JTable t, int row) {

                int respEdit = JOptionPane.showConfirmDialog(null, "¿Quiere modificar los datos del accidente?", "Atención", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if (respEdit == JOptionPane.OK_OPTION) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int indexAccident = (int) model.getValueAt(row, 0);
                    m.setForm(2, indexAccident);

                }

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
                        System.err.println("Error en el borrado del accidente en la base de datos");
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

        cargarRangoAnyosAccidentes();
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

    private void cargarRangoAnyosAccidentes() {

        // Lista para almacenar los años
        List<String> years = new ArrayList<>();

        datos_model.getRangoAnyos(years);

        // Convertir la lista de años a un array
//            String[] yearsArray = (String[]) years.toArray();
        // Crear un JComboBox y agregar los años
        cmbFiltroAnyo.setModel(new DefaultComboBoxModel<>((String[]) years.toArray(new String[0])));

        DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime hoy = LocalDateTime.now();
        String fechaHoy = fecha.format(hoy);

        cmbFiltroAnyo.setSelectedItem(fechaHoy);
        
        filtrarAccidentesAnyo();

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
        cmbFiltroAnyo = new javax.swing.JComboBox<>();
        panelExports = new javax.swing.JPanel();
        btnExportPdf = new javax.swing.JButton();
        btnExportExcel = new javax.swing.JButton();
        header = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSearchFiled = new com.conde.component.textfieldSearch.TextFieldSearchOption();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1424, 968));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1424, 968));
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

        data_Aux_Accidente.setColor1(new java.awt.Color(131, 96, 195));
        data_Aux_Accidente.setColor2(new java.awt.Color(46, 191, 145));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        panel.add(data_Aux_Accidente, gridBagConstraints);

        vehiculos.setColor1(new java.awt.Color(31, 64, 55));
        vehiculos.setColor2(new java.awt.Color(153, 242, 200));
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
        panelBorder1.setMaximumSize(new java.awt.Dimension(1417, 640));
        panelBorder1.setMinimumSize(new java.awt.Dimension(1417, 640));

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
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setShowHorizontalLines(false);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressed(evt);
            }
        });
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

        cmbFiltroAnyo.setMaximumRowCount(5);
        cmbFiltroAnyo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2024" }));
        cmbFiltroAnyo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbFiltroAnyoItemStateChanged(evt);
            }
        });
        cmbFiltroAnyo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFiltroAnyoActionPerformed(evt);
            }
        });

        panelExports.setOpaque(false);

        btnExportPdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/conde/resources/icons/pdf (1).png"))); // NOI18N
        btnExportPdf.setContentAreaFilled(false);

        btnExportExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/conde/resources/icons/excel.png"))); // NOI18N
        btnExportExcel.setContentAreaFilled(false);
        btnExportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelExportsLayout = new javax.swing.GroupLayout(panelExports);
        panelExports.setLayout(panelExportsLayout);
        panelExportsLayout.setHorizontalGroup(
            panelExportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelExportsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExportPdf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExportExcel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelExportsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnExportExcel, btnExportPdf});

        panelExportsLayout.setVerticalGroup(
            panelExportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelExportsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelExportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExportExcel)
                    .addComponent(btnExportPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        panelExportsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnExportExcel, btnExportPdf});

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1)
                        .addGap(398, 398, 398)
                        .addComponent(cmbFiltroAnyo, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelExports, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(s, javax.swing.GroupLayout.PREFERRED_SIZE, 1399, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(cmbFiltroAnyo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelExports, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(s, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        header.setOpaque(false);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/conde/resources/icons/search.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("LISTADO DE ACCIDENTES");

        txtSearchFiled.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchFiledKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 1425, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(224, 224, 224))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded


    }//GEN-LAST:event_formAncestorAdded

    private void tableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyPressed

    }//GEN-LAST:event_tableKeyPressed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained

    }//GEN-LAST:event_formFocusGained

    private void txtSearchFiledKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchFiledKeyReleased
        if (txtSearchFiled.isSelected()) {
            int option = txtSearchFiled.getSelectedIndex();
            String text = txtSearchFiled.getText().trim();
            if (option == 0) {
                //Busqueda por fecha
                cadenaBusqueda = text;

                if (cadenaBusqueda.length() == 10) {
                    if (verificarFecha(cadenaBusqueda)) {
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        Date fecha = new Date();
                        try {
                            fecha = formato.parse(cadenaBusqueda);
                            Calendar fec = Calendar.getInstance();
                            fec.setTime(fecha);
                            cargarAccidentes("where Fecha =" + "#" + (fec.get(Calendar.MONTH) + 1) + "/" + fec.get(Calendar.DAY_OF_MONTH) + "/" + fec.get(Calendar.YEAR) + "#", "");
                        } catch (ParseException ex) {
                            Logger.getLogger(Form_Home.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        JOptionPane.showMessageDialog(this, "La fecha no es correcta, formato dd/mm/yyyy");
//                        txtSearchFiled.setText("");
                        txtSearchFiled.requestFocus();
                    }

                } else if (cadenaBusqueda.length() == 0) {
                    cargarAccidentes("");
                }

            } else if (option == 1) {
                //Busqueda por equipo
                cadenaBusqueda = text;

                if (cadenaBusqueda.length() >= 3) {
                    cargarAccidentes("where Zona_Atestados like '%" + cadenaBusqueda + "%'", "");
                } else if (cadenaBusqueda.length() == 0) {
                    cargarAccidentes("");
                }

            } else if (option == 2) {
                // Busqueda por carretera
                cadenaBusqueda = text;

                if (cadenaBusqueda.length() >= 3) {
                    cargarAccidentes("where Carretera like '%" + cadenaBusqueda + "%'", "");
                } else if (cadenaBusqueda.length() == 0) {
                    cargarAccidentes("");
                }

            } else if (option == 3) {
                // Busqueda por diligencias
                cadenaBusqueda = text;
                try {

                    if (cadenaBusqueda.length() > 0) {
                        Integer numDiligencias = Integer.valueOf(cadenaBusqueda);
                        cargarAccidentes("where Num_Diligencias =" + numDiligencias ,"");
                    } else if (cadenaBusqueda.length() == 0) {
                        cargarAccidentes("");
                    }
                } catch (NumberFormatException e) {

                    JOptionPane.showMessageDialog(this, "Debe introducir un número de diligencias");

                    txtSearchFiled.requestFocus();
                }
            } else if (option == 4) {
                // Busdqueda por patrulla
                cadenaBusqueda = text;
                if (cadenaBusqueda.length() >= 3) {
                    cargarAccidentes("where Patrulla like '%" + cadenaBusqueda + "%'", "");
                } else if (cadenaBusqueda.length() == 0) {
                    cargarAccidentes("");
                }
            }
        }
    }//GEN-LAST:event_txtSearchFiledKeyReleased

    private void tableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMousePressed

    }//GEN-LAST:event_tableMousePressed

    private void cmbFiltroAnyoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFiltroAnyoActionPerformed

        filtrarAccidentesAnyo();

    }//GEN-LAST:event_cmbFiltroAnyoActionPerformed

    private void cmbFiltroAnyoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbFiltroAnyoItemStateChanged
        filtrarAccidentesAnyo();

    }//GEN-LAST:event_cmbFiltroAnyoItemStateChanged

    private void btnExportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportExcelActionPerformed
        try {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos excell", "xlsx");
            File directorio = new File(System.getProperty("user.dir"));
            JFileChooser jFileChooser = new JFileChooser(directorio);
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jFileChooser.setFileFilter(filter);
                   jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();
                if (saveFile!=null){
                    saveFile = new File(saveFile.toString()+".xlsx");
                    Workbook wb = new XSSFWorkbook();
                    Sheet sheet = wb.createSheet("Diligencias");
                    
                    Row rowCol = sheet.createRow(0);
                    
    
                    // Creamos estilo para titulos de las columnas
                    CellStyle estilo = wb.createCellStyle();
                    estilo.setAlignment(HorizontalAlignment.CENTER);
                    CellStyle estilo2 = wb.createCellStyle();
                    estilo2.setAlignment(HorizontalAlignment.CENTER);
  
                    
                    // Creamos una fuente
                        org.apache.poi.ss.usermodel.Font fuente=wb.createFont();
                        fuente.setFontName("Roboto");
                        fuente.setFontHeightInPoints((short)12);
                        fuente.setBold(true);
                        estilo.setFont(fuente);
                        
                        org.apache.poi.ss.usermodel.Font fuente2=wb.createFont();
                        fuente2.setBold(false);
                        
                        estilo2.setFont(fuente2);
                    // Ponemos nombres de columnas
                    for (int i=2; i < table.getColumnCount(); i++){
                       
                        sheet.setColumnWidth(i-2, 18*256);
                         
                        Cell cell = rowCol.createCell(i-2);
                        
                        cell.setCellStyle(estilo);
                        cell.setCellValue(table.getColumnName(i));
                    }
                    

                    // Rellenamos las filas
                   for (int r=0; r < table.getRowCount();r++){
                       Row row = sheet.createRow(r+1);
                       
                       for (int c=2; c < table.getColumnCount(); c++ ){
                               Cell cell = row.createCell(c-2);
                                if (table.getValueAt(r, c) != null){
                                    
                                    
                                    cell.setCellStyle(estilo2);
                                    cell.setCellValue(table.getValueAt(r, c).toString());
                                }

                       }
                   }
                   
                    FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                    wb.write(out);
                    wb.close();
                    out.close();
                    openFile(saveFile.toString());
                }else{
                    JOptionPane.showMessageDialog(null, "Error al generar archivo excel");
                }
            
            
        } catch (FileNotFoundException e) {
            
            System.out.println(e);
            
        } catch (HeadlessException | IOException io){
            System.out.println(io);
        }
        
    }//GEN-LAST:event_btnExportExcelActionPerformed
    public void openFile(String file){
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    private void filtrarAccidentesAnyo() {

        if (!cmbFiltroAnyo.getSelectedItem().equals("") ) {

            cargarAccidentes("WHERE Year(FECHA) =" + cmbFiltroAnyo.getSelectedItem());

        } else {

            cargarAccidentes("");
        }

    }

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

    public void cargarAccidentes(String where, Object... search) {

        listAccidents.clear();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        String SqlQuery="";
        
        if (cmbFiltroAnyo.getSelectedItem().equals("") && txtSearchFiled.getText().equals("")){
            SqlQuery = "SELECT * FROM Accidentes ORDER BY Num_Diligencias DESC";
            
        }else if (!cmbFiltroAnyo.getSelectedItem().equals("") && txtSearchFiled.getText().equals("")){
            SqlQuery = "SELECT * FROM Accidentes WHERE Year(Fecha)=" + cmbFiltroAnyo.getSelectedItem() + " ORDER BY Num_Diligencias DESC";
            
        }else if (cmbFiltroAnyo.getSelectedItem().equals("") && !txtSearchFiled.getText().equals("")){
            SqlQuery = "SELECT * FROM Accidentes " + where + " ORDER BY Num_Diligencias DESC";
            
         }else if (!cmbFiltroAnyo.getSelectedItem().equals("") && !txtSearchFiled.getText().equals("")){
            SqlQuery = "SELECT * FROM Accidentes " + where + " AND Year(Fecha)=" + cmbFiltroAnyo.getSelectedItem() + " ORDER BY Num_Diligencias DESC";
         }

        
        listAccidents = datos_model.getListAccidents(SqlQuery);

        //Lo mostramos en la tabla
        for (Accidente accidente : listAccidents) {
            table.addRow(new Object[]{accidente.getNum_Accidente(), null, accidente.getFecha(), accidente.getHora(), accidente.getCarretera(), accidente.getKilometro(), accidente.getNum_Diligencias(), accidente.getPatrulla(), accidente.getStattus()});
        }

    }

    private void cargaVehiculosAccidente(int index) {

        ArrayList<Vehiculo> vehiculosAccidente = new ArrayList<>();

        vehiculosAccidente = datos_model.getVehiculosInAccident(index);

        vehiculos.clearRows();
        vehiculos.setToolTipRows(vehiculosAccidente);
        if (vehiculosAccidente != null) {
            vehiculos.setData(vehiculosAccidente);
        }

    }

    private void cargaPersonasAccidente(int index) throws SQLException {
        ArrayList<Persona> personasAccidente = new ArrayList<>();

        personasAccidente = datos_model.getPersonasInAccidenteByIdAccident(index);

        personas.clearRows();
        personas.setToolTipRows(personasAccidente);
        if (personasAccidente != null) {
            personas.setData(personasAccidente);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportExcel;
    private javax.swing.JButton btnExportPdf;
    private javax.swing.JComboBox<String> cmbFiltroAnyo;
    private com.conde.component.Card_Accident data_Aux_Accidente;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLayeredPane panel;
    private com.conde.swing.PanelBorder panelBorder1;
    private javax.swing.JPanel panelExports;
    private com.conde.component.Card_Persona personas;
    private com.conde.swing.ScrollPaneWin11 s;
    private com.conde.swing.Table_Accidentes table;
    private com.conde.component.textfieldSearch.TextFieldSearchOption txtSearchFiled;
    private com.conde.component.Card_Vehiculos vehiculos;
    // End of variables declaration//GEN-END:variables

}
