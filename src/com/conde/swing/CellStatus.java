
package com.conde.swing;

import com.conde.model.StatusType;


public class CellStatus extends javax.swing.JPanel {


    public CellStatus(StatusType type, boolean isSelected) {
        initComponents();
       status.setType(type);
       status.selectedTypeOn(isSelected);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        status = new com.conde.swing.TableStatus();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.CardLayout());

        status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        status.setText("tableStatus1");
        status.setAlignmentY(0.0F);
        status.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(status, "card2");

        getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.conde.swing.TableStatus status;
    // End of variables declaration//GEN-END:variables
}
