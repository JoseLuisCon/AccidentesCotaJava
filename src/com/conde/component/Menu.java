package com.conde.component;

import com.conde.event.EventMenuSelected;
import com.conde.event.EventToogleTheme;
import com.conde.model.Model_Menu;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Menu extends javax.swing.JPanel {

    private boolean estado = true;

    private EventToogleTheme toogleTheme;

    public void addEventToogleTheme(EventToogleTheme event) {
        this.toogleTheme = event;
    }

    public void addEventMenuSelected(EventMenuSelected event) {
        listMenu1.addEventMenuSelected(event);
    }

    public Menu() {
        initComponents();
        setOpaque(false);
        listMenu1.setOpaque(false);

        init();

        lblMailTo.InitLink("By Conde", "mailto:condepa45@gmail.com");
    }

    private void init() {

        listMenu1.addItem(new Model_Menu("", "", Model_Menu.MenuType.EMPTY));
        listMenu1.addItem(new Model_Menu("1", "Listado", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("2", "Alta", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("3", "Informes", Model_Menu.MenuType.MENU));
////        listMenu1.addItem(new Model_Menu("4", "Forms Stuff", Model_Menu.MenuType.MENU));
////        listMenu1.addItem(new Model_Menu("5", "Data Table", Model_Menu.MenuType.MENU));
////        listMenu1.addItem(new Model_Menu("", "", Model_Menu.MenuType.EMPTY));
////
////        listMenu1.addItem(new Model_Menu("", "My DAta", Model_Menu.MenuType.TITLE));
////        listMenu1.addItem(new Model_Menu("", "", Model_Menu.MenuType.EMPTY));
////        listMenu1.addItem(new Model_Menu("6", "Icons", Model_Menu.MenuType.MENU));
////        listMenu1.addItem(new Model_Menu("7", "Sample Page", Model_Menu.MenuType.MENU));
////        listMenu1.addItem(new Model_Menu("8", "Extra", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("", "", Model_Menu.MenuType.EMPTY));
        listMenu1.addItem(new Model_Menu("5", "Salir", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("", "", Model_Menu.MenuType.EMPTY));

        lblLogo.setBounds(0, 0, 65, 86);

        Image imagen = new ImageIcon(this.getClass().getResource("/com/conde/resources/icons/logo_traf.png")).getImage();
        Icon icon = new ImageIcon(imagen.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_DEFAULT));
        lblLogo.setIcon(icon);

        btnToogleTheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estado = !estado; // Cambiar el estado para el próximo clic

                if (estado) {
                    Image imagen = new ImageIcon(Menu.this.getClass().getResource("/com/conde/resources/icons/luna.png")).getImage();
                    btnToogleTheme.setIcon(new ImageIcon(imagen));
                    btnToogleTheme.setToolTipText("Tema claro");
                } else {

                    Image imagen = new ImageIcon(Menu.this.getClass().getResource("/com/conde/resources/icons/el-verano.png")).getImage();
                    btnToogleTheme.setIcon(new ImageIcon(imagen));
                    btnToogleTheme.setToolTipText("Tema oscuro");
                }
                toogleTheme.toogleTheme(estado);

            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMoving = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        listMenu1 = new com.conde.swing.ListMenu<>();
        lblMailTo = new com.conde.swing.JLabelLink();
        lblLogo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnToogleTheme = new javax.swing.JToggleButton();

        panelMoving.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/conde/resources/icons/accidente_32px.png"))); // NOI18N
        jLabel1.setText("Registro de Accidentes");

        javax.swing.GroupLayout panelMovingLayout = new javax.swing.GroupLayout(panelMoving);
        panelMoving.setLayout(panelMovingLayout);
        panelMovingLayout.setHorizontalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMovingLayout.setVerticalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        lblMailTo.setForeground(new java.awt.Color(255, 255, 255));
        lblMailTo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMailTo.setText("By Conde");
        lblMailTo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblMailTo.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblMailTo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
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

        jPanel1.setOpaque(false);

        btnToogleTheme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/conde/resources/icons/luna.png"))); // NOI18N
        btnToogleTheme.setToolTipText("");
        btnToogleTheme.setBorder(null);
        btnToogleTheme.setBorderPainted(false);
        btnToogleTheme.setContentAreaFilled(false);
        btnToogleTheme.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnToogleTheme.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnToogleThemeMousePressed(evt);
            }
        });
        btnToogleTheme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToogleThemeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnToogleTheme, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnToogleTheme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMailTo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelMoving, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMoving, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(listMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
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

    private void btnToogleThemeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnToogleThemeMousePressed

    }//GEN-LAST:event_btnToogleThemeMousePressed

    private void btnToogleThemeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToogleThemeActionPerformed

//        cambiarIcono();

    }//GEN-LAST:event_btnToogleThemeActionPerformed

    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#000000"), 0, getHeight(), Color.decode("#0f9b0f"));
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnToogleTheme;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblLogo;
    private com.conde.swing.JLabelLink lblMailTo;
    private com.conde.swing.ListMenu<String> listMenu1;
    private javax.swing.JPanel panelMoving;
    // End of variables declaration//GEN-END:variables
}
