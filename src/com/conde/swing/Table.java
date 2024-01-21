package com.conde.swing;

import com.conde.model.StatusType;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Table extends JTable {

    public Table() {

        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(30);
        
        getTableHeader().setReorderingAllowed(false);
        

        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSelected, boolean hasFocus, int row, int column) {

                TableHeader header = new TableHeader(o + "");
                header.setHorizontalAlignment(JLabel.CENTER);
                header.setFont(new Font("sansserif",1, 16));
                return header;
            }
        });

        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object o, boolean isSelected, boolean hasFocus, int row, int column) {
                if (column != 7) {
                    Component com = super.getTableCellRendererComponent(table, o, isSelected, hasFocus, row, column);
                    com.setBackground(Color.white);

                    setBorder(noFocusBorder);
                    if (isSelected) {
                        com.setForeground(new Color(240, 238, 102));
                        com.setBackground(new Color(234, 151, 127));
                    } else {
                        com.setForeground(new Color(39, 55, 70));
                    }

                    setHorizontalAlignment(JLabel.CENTER);
                    setFont(new Font("sansserif", Font.PLAIN, 14));
                    return com;
                } else {

                    StatusType type;
                    type = (StatusType) o;
                    CellStatus cell = new CellStatus(type);
                    return cell;
                }

            }
        });
    }

    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }

}
