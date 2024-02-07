
package com.conde.cell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;


public class PanelActionCell extends javax.swing.JPanel {

    public PanelActionCell() {
        initComponents();

   }
    
   public void initEvent(TableActionEvent event, JTable table, int row){
       
        cmdEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               event.onEdit(table,row);
               
        }
    });
        cmdDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               event.onDelete(table,row);
        }
    });
 }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        cmdEdit = new com.conde.cell.ActionButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 50));
        cmdDelete = new com.conde.cell.ActionButton();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));

        setLayout(new java.awt.CardLayout());

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.X_AXIS));
        jPanel1.add(filler1);

        cmdEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/conde/cell/editar_16px.png"))); // NOI18N
        jPanel1.add(cmdEdit);
        jPanel1.add(filler2);

        cmdDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/conde/cell/borrar_16px .png"))); // NOI18N
        jPanel1.add(cmdDelete);
        jPanel1.add(filler3);

        add(jPanel1, "card2");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.conde.cell.ActionButton cmdDelete;
    private com.conde.cell.ActionButton cmdEdit;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
