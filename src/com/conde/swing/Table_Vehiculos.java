package com.conde.swing;

import com.conde.event.EventRowSelected;
import com.conde.model.StatusType;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Table_Vehiculos extends JTable {
    
    private EventRowSelected event;
    
     public void addEventRowSelected(EventRowSelected event){
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
                header.setFont(new Font("sansserif",1, 12));
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
                        com.setBackground(new Color(6, 72, 72 ));
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
                        com.setBackground(new Color(6, 72, 72 ));
                    } else {
                        com.setForeground(Color.RED);
                    }

                    setHorizontalAlignment(JLabel.CENTER);
                    setFont(new Font("sansserif", Font.BOLD, 12));
                    return com;
                    
                }

            }
        });

      
    }

    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }

}
