
package com.conde.cell;

import javax.swing.JTable;


public interface TableActionEvent {
    public void onEdit(JTable table,int row);
    public void onDelete(JTable table, int row);
}
