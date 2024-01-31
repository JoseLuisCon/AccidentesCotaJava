package com.conde.swing;

import com.conde.event.EventRowSelected;
import com.conde.model.Vehiculo;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Table_Vehiculos extends JTable {

    private EventRowSelected event;
    
    private ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
    
      public void setListaVehículos(ArrayList<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }
    
    public void addEventRowSelected(EventRowSelected event) {
        this.event = event;
    }

    public Table_Vehiculos() {

        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(20);

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
                if (column != 1) {
                    Component com = super.getTableCellRendererComponent(table, o, isSelected, hasFocus, row, column);
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
                    Component com = super.getTableCellRendererComponent(table, o, isSelected, hasFocus, row, column);
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
                    Object num_Vehiculo = getValueAt(fila, 0);
                    Vehiculo vehiculo = buscaNumVehiculo((int) num_Vehiculo);
                    String toolTip = "";
                    if (!vehiculo.getObservaciones().isEmpty()){
                        toolTip = vehiculo.getObservaciones();
                    }
                    
                    setToolTipText(toolTip);

                } else {
                    // Si el ratón no está sobre una celda, eliminar el tooltip
                    setToolTipText(null);
                }

            }

            private Vehiculo buscaNumVehiculo(int Num_Vehiculo) {

                for (Vehiculo vehiculo : listaVehiculos) {
                    if (vehiculo.getId_Vehiculo() == Num_Vehiculo) {
                        return vehiculo;
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
