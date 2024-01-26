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

public class Table_Accidentes extends JTable {
    
    private EventRowSelected event;
    
     public void addEventRowSelected(EventRowSelected event){
        this.event = event;
    }

    public Table_Accidentes() {

        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(30);
        
        getTableHeader().setReorderingAllowed(false);

        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSelected, boolean hasFocus, int row, int column) {

                TableHeader header = new TableHeader(o + "");
                header.setHorizontalAlignment(JLabel.CENTER);
                header.setFont(new Font("sansserif",Font.BOLD, 18));
                return header;
            }
        });

        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object o, boolean isSelected, boolean hasFocus, int row, int column) {
                if (column != 7) {
                    Component com = super.getTableCellRendererComponent(table, o, isSelected, hasFocus, row, column);
                    com.setBackground(Color.white);
                    setHorizontalAlignment(JLabel.CENTER);
                    setFont(new Font("sansserif", Font.PLAIN, 16));

                    setBorder(noFocusBorder);
                    if (isSelected) {
                        com.setForeground(new Color(240, 238, 102));
                        com.setBackground(new Color(6, 72, 72 ));
                    } else {
                        com.setForeground(new Color(53, 54, 53));
                    }

                    
                    return com;
                } else {
                    
                    StatusType type;
                    type = (StatusType) o;
                    CellStatus cell = new CellStatus(type);
             
                    return cell;
                }

            }
        });

        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                
            } 

            @Override
            public void mousePressed(MouseEvent e) {
                int filaSeleccionada = rowAtPoint(e.getPoint());
                int numAccidente = (int) getValueAt(filaSeleccionada,0);
                event.selectedRow(numAccidente);
            }
            
            
            
        });
        
        addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
              if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    // Obtener la fila seleccionada al desplazarse con las flechas arriba/abajo
                    int filaSeleccionada = getSelectedRow();
                    int numAccidente = (int) getValueAt(filaSeleccionada,0);
                    event.selectedRow(numAccidente);

                }
            }
        });
    }

    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }

}
