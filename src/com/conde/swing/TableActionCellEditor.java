
package com.conde.swing;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;


public class TableActionCellEditor extends DefaultCellEditor {

    public TableActionCellEditor() {
        super(new JCheckBox());
    }
    
    

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
       PanelAction action = new PanelAction();
       action.setBackground(table.getSelectionBackground());
       return action;
       
    }
    
}
