package com.conde.swing;

import com.conde.cell.PanelActionCell;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Table_Vehiculo_Form_Add extends JTable {
    


    public Table_Vehiculo_Form_Add() {
        
        getTableHeader().setReorderingAllowed(false);

        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object o, boolean isSelected, boolean hasFocus, int row, int column) {

                if (column !=1) {

                    Component com = super.getTableCellRendererComponent(table, o, isSelected, hasFocus, row, column);
                       if (isSelected) {
                        com.setBackground(new Color(148, 210, 95));
                    } else {
                        com.setBackground(table.getBackground());
                    }
                    setHorizontalAlignment(JLabel.CENTER);
                    setBorder(noFocusBorder);

                    return com;

                } else {

                    PanelActionCell action = new PanelActionCell();
                    if (isSelected) {
                        action.setBackground(new Color(148, 210, 95));
                    } else {
                        action.setBackground(table.getBackground());
                    }
                    
                    setBorder(noFocusBorder);
                    return action;

                }

            }
        });
        


    }
    
        public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }

}
