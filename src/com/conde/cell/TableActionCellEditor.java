package com.conde.cell;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableActionCellEditor extends DefaultCellEditor {

    private TableActionEvent event;

    public TableActionCellEditor(TableActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }
    
    

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        PanelActionCell action = new PanelActionCell();
      
        action.initEvent(event, table, row);
        
        action.setBackground(new Color(6, 72, 72));
       
        return action;

    }

}
