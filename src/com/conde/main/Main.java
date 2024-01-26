package com.conde.main;

import com.conde.form.Form_1;
import com.conde.form.Form_2;
import com.conde.form.Form_Home;

import com.conde.model.ConexionAccess;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

public class Main extends javax.swing.JFrame {

    private Form_Home home;
    private Form_1 form1;
    private Form_2 form2;
    

    public Main() {
        
        initComponents();
        // Obtener el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calcular el ancho y alto deseado para el JFrame (por ejemplo, el 80% del tamaño de la pantalla)
//        int x=(int) (screenSize.width * 0.3) / 2;
//        int y=(int) (screenSize.height*0.3) / 2;
//        // Establecer el tamaño del JFrame
//        setBounds(x, y,  (int) (screenSize.width * 0.7),  (int) (screenSize.height * 0.7));

        
        
        
        setBackground(new Color(0, 0, 0, 0));
        menu.initMoving(Main.this);
        

        home = new Form_Home();
        form1 = new Form_1();
        form2 = new Form_2();
   

        menu.addEventMenuSelected((int index) -> {
            switch (index) {
                case 1:
                    setForm(home);
                    break;
                case 2:
                    setForm(form1);
                    break;
                case 3:
                    setForm(form2);
                    break;
                case 5:
                    ConexionAccess.desConnection();
                {
                    try {
                        Thread.sleep(600);
                        System.exit(0);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    

                default:
                    break;
            }
        });

        setForm(home);
        
        ConexionAccess.conectar();
        
    }

    private void setForm(JComponent com) {

        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header1 = new com.conde.component.Header();
        jSeparator1 = new javax.swing.JSeparator();
        panelBorder1 = new com.conde.swing.PanelBorder();
        menu = new com.conde.component.Menu();
        mainPanel = new javax.swing.JPanel();

        header1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBorder1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelBorder1.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 850));

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());
        panelBorder1.add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 1440, 850));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.conde.component.Header header1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel mainPanel;
    private com.conde.component.Menu menu;
    private com.conde.swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
