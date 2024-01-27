package com.conde.swing;

import com.conde.event.EventRowSelected;
import com.conde.model.Model_Accident;
import com.conde.model.Persona;
import com.conde.model.StatusType;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Table_Personas extends JTable {

    private ArrayList<Persona> listaPersonas = new ArrayList<>();

    public void setListaPersonas(ArrayList<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    private EventRowSelected event;

    public Table_Personas() {

        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));

        getTableHeader().setReorderingAllowed(false);

        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSelected, boolean hasFocus, int row, int column) {

                TableHeader header = new TableHeader(o + "");
                header.setHorizontalAlignment(JLabel.CENTER);
                header.setFont(new Font("sansserif", 1, 12));
                return header;
            }
        });

        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object o, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = super.getTableCellRendererComponent(table, o, isSelected, hasFocus, row, column);

                if (column != 2) {
                    com.setBackground(Color.white);
                    setBorder(noFocusBorder);
                    if (isSelected) {
                        com.setForeground(new Color(240, 238, 102));
                        com.setBackground(new Color(6, 72, 72));
                    } else {
                        com.setForeground(new Color(53, 54, 53));
                    }

                    setHorizontalAlignment(JLabel.CENTER);
                    setFont(new Font("sansserif", Font.PLAIN, 12));
                    return com;

                } else {
                    com.setBackground(Color.white);

                    setBorder(noFocusBorder);
                    if (isSelected) {
                        com.setForeground(new Color(240, 238, 102));
                        com.setBackground(new Color(6, 72, 72));
                    } else {
                        com.setForeground(Color.RED);
                    }

                    setHorizontalAlignment(JLabel.CENTER);
                    setFont(new Font("sansserif", Font.BOLD, 12));
                    return com;

                }

            }
        });

//        addMouseListener(new MouseAdapter(){
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int filaSeleccionada = rowAtPoint(e.getPoint());
//                int numVehiculo = (int) getValueAt(filaSeleccionada,0);
//                event.selectedRow(numVehiculo);
//            } 
//        });
//        addKeyListener(new KeyListener(){
//            @Override
//            public void keyTyped(KeyEvent e) {}
//
//            @Override
//            public void keyPressed(KeyEvent e) {}
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//              if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
//                    // Obtener la fila seleccionada al desplazarse con las flechas arriba/abajo
//                    int filaSeleccionada = getSelectedRow();
//                    int numVehiculo = (int) getValueAt(filaSeleccionada,0);
//                    event.selectedRow(numVehiculo);
//
//                }
//            }
//        });
        // Agregar un MouseMotionListener a la tabla para manejar eventos de movimiento del ratón
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {

                // Obtener la fila y la columna en la que se encuentra el ratón
                int fila = rowAtPoint(e.getPoint());
                int columna = columnAtPoint(e.getPoint());

                // Verificar si la fila y la columna son válidas
                if (fila >= 0 && columna >= 0) {
                    // Obtener el valor de la celda
                    Object num_Persona = getValueAt(fila, 0);
                    Persona per = buscaNumAccidente((int) num_Persona);
                    String toolTip = "";

                    toolTip = per.getDocumento() + "; ";

                    if (per.getTrasladado()) {
                        toolTip += "Trasladado a: " + per.getLugar_traslado() + "; ";
                    }
                    if (per.getPrueba_alcoholemia()) {
                        if (per.getAlcoholemia_positiva()) {
                            toolTip += "Prueba de alcohol: positiva; ";
                        } else {
                            toolTip += "Prueba de alcohol: negativa; ";
                        }

                    } else {
                        toolTip += "Prueba de alcohol no realizada; ";
                    }

                    if (per.getPrueba_drogas()) {
                        if (per.getDrogas_positiva()) {
                            toolTip += "Prueba de drogas: positiva; ";
                        } else {
                            toolTip += "Prueba de drogas: negativa; ";
                        }

                    } else {
                        toolTip += "Prueba de drogas no realizada; ";
                    }

                    if (per.getObservaciones() != null) {
                        toolTip += per.getObservaciones() + "\n";
                    }

                    setToolTipText(toolTip);

                } else {
                    // Si el ratón no está sobre una celda, eliminar el tooltip
                    setToolTipText(null);
                }

            }

            private Persona buscaNumAccidente(int Num_Persona) {

                for (Persona persona : listaPersonas) {
                    if (persona.getId_Persona() == Num_Persona) {
                        return persona;

                    }
                }
                return null;
            }
        });

    }

    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }

}
