package com.conde.component;

import com.conde.event.EventMenuSelected;
import com.conde.model.Model_Menu;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu extends javax.swing.JPanel {

    public void addEventMenuSelected(EventMenuSelected event) {
        listMenu1.addEventMenuSelected(event);
    }

    public Menu() {
        initComponents();
        setOpaque(false);
        listMenu1.setOpaque(false);

        init();
        pintarImagen(lblLogo, "src/com/conde/resources/icons/logTraf2.png");
        
        lblMailTo.InitLink("By Conde", "mailto:condepa45@gmail.com");
    }

    private void init() {

        listMenu1.addItem(new Model_Menu("", "", Model_Menu.MenuType.EMPTY));
        listMenu1.addItem(new Model_Menu("1", "Listado", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("2", "Alta", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("3", "Informes", Model_Menu.MenuType.MENU));
//        listMenu1.addItem(new Model_Menu("4", "Forms Stuff", Model_Menu.MenuType.MENU));
//        listMenu1.addItem(new Model_Menu("5", "Data Table", Model_Menu.MenuType.MENU));
//        listMenu1.addItem(new Model_Menu("", "", Model_Menu.MenuType.EMPTY));
//
//        listMenu1.addItem(new Model_Menu("", "My DAta", Model_Menu.MenuType.TITLE));
//        listMenu1.addItem(new Model_Menu("", "", Model_Menu.MenuType.EMPTY));
//        listMenu1.addItem(new Model_Menu("6", "Icons", Model_Menu.MenuType.MENU));
//        listMenu1.addItem(new Model_Menu("7", "Sample Page", Model_Menu.MenuType.MENU));
//        listMenu1.addItem(new Model_Menu("8", "Extra", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("", "", Model_Menu.MenuType.EMPTY));
        listMenu1.addItem(new Model_Menu("5", "Salir", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("", "", Model_Menu.MenuType.EMPTY));

    }

    private void pintarImagen(JLabel lbl, String ruta) {

        imagen = new ImageIcon(ruta);

        lbl.setBounds(0,0,65, 86);
        icon = new ImageIcon(imagen.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT));
        lbl.setIcon(icon);
        repaint();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMoving = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        listMenu1 = new com.conde.swing.ListMenu<>();
        lblMailTo = new com.conde.swing.JLabelLink();
        lblLogo = new javax.swing.JLabel();

        panelMoving.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/conde/resources/icons/accidente_32px.png"))); // NOI18N
        jLabel1.setText("Registro de Accidentes");

        javax.swing.GroupLayout panelMovingLayout = new javax.swing.GroupLayout(panelMoving);
        panelMoving.setLayout(panelMovingLayout);
        panelMovingLayout.setHorizontalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMovingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
        );
        panelMovingLayout.setVerticalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovingLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblMailTo.setForeground(new java.awt.Color(255, 255, 255));
        lblMailTo.setText("By Conde");
        lblMailTo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblMailToMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblMailToMouseExited(evt);
            }
        });

        lblLogo.setAlignmentY(0.0F);
        lblLogo.setAutoscrolls(true);
        lblLogo.setIconTextGap(0);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMoving, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(listMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMailTo, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMoving, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMailTo, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblMailToMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMailToMouseEntered
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblMailToMouseEntered

    private void lblMailToMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMailToMouseExited
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_lblMailToMouseExited

    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#11998e"), 0, getHeight(), Color.decode("#38ef7d"));
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(getWidth() - 20, 0, getWidth(), getHeight());

        super.paintChildren(grphcs);
    }

    private int x;
    private int y;

    public void initMoving(JFrame fram) {
        panelMoving.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }
        });
        panelMoving.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                fram.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
            }
        });
    }

    private ImageIcon imagen;
    private Icon icon;


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblLogo;
    private com.conde.swing.JLabelLink lblMailTo;
    private com.conde.swing.ListMenu<String> listMenu1;
    private javax.swing.JPanel panelMoving;
    // End of variables declaration//GEN-END:variables
}
