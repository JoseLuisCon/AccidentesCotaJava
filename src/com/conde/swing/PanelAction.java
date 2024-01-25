
package com.conde.swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;


public class PanelAction extends javax.swing.JPanel {

  
    public PanelAction() {
        initComponents();
        
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_Show = new com.conde.swing.ActionButton();

        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setPreferredSize(new java.awt.Dimension(37, 37));
        setLayout(new java.awt.CardLayout());
        add(btn_Show, "card2");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.conde.swing.ActionButton btn_Show;
    // End of variables declaration//GEN-END:variables

    public void init() {
//        setSize(32, 32);
        ImageIcon image = new ImageIcon("src/com/conde/resources/icons/Info.png");
        Icon icon = new ImageIcon(image.getImage().getScaledInstance( 25 , 25, Image.SCALE_DEFAULT));
        btn_Show.setIcon(icon);
        
        setBackground(Color.WHITE);
    }
}
