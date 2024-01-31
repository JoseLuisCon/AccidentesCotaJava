package com.conde.main;

import com.conde.event.EventToogleTheme;
import com.conde.form.Form_2;
import com.conde.form.Form_Home;

import com.conde.model.ConexionAccess;

import com.conde.form.FormAddAccident;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main extends javax.swing.JFrame {

    private Form_Home home;
    private FormAddAccident frmAddAcc;
    private Form_2 form2;
    
    private int indexPanel=0;
    
    
   
    

    public Main() {

        initComponents();

        setBackground(new Color(0, 0, 0, 0));
        menu.initMoving(Main.this);

        home = new Form_Home();

        form2 = new Form_2();
        
        menu.addEventToogleTheme((Boolean toogleTheme)->{
            
            
            toggleTema();
        
        });
        

        menu.addEventMenuSelected((int index) -> {
            switch (index) {
                case 1:
                    setForm(home);
                    indexPanel=1;
                    break;
                case 2:

                    FlatLightLaf.registerCustomDefaultsSource("com/conde/style");
                    FlatLightLaf.setup();

                    FormAddAccident p = new FormAddAccident();
                    
                    setForm(p);
                    indexPanel=2;
                    break;
                case 3:
                    setForm(form2);
                    indexPanel=3;
                    break;
                case 5:
                    ConexionAccess.desConnection();
                     {
                        try {
                            Thread.sleep(400);
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
        CardLayout cl = new CardLayout();
        cl.setVgap(5);
        mainPanel.setLayout(cl);
        if (com.getName() != null && com.getName().equals("frmAddAccidente")) {
            
            com.setSize(getWidth(), getHeight());
        }

        mainPanel.add(com);

        mainPanel.repaint();
        mainPanel.revalidate();
    }
    
    private void toggleTema() {
        
        if (indexPanel ==2){
        
          try {
            // Obtener el Look and Feel actual
            UIManager.LookAndFeelInfo lookAndFeelInfo = UIManager.getInstalledLookAndFeels()[0];

            // Cambiar entre el tema dark y light
            if (FlatDarkLaf.class.getName().equals(UIManager.getLookAndFeel().getClass().getName())) {
               FlatLightLaf.registerCustomDefaultsSource("com/conde/style");        
                UIManager.setLookAndFeel(new FlatLightLaf());

                mainPanel.setBackground(Color.WHITE);
            } else {
           
                UIManager.setLookAndFeel(new FlatDarkLaf());
               FlatDarkLaf.registerCustomDefaultsSource("com/conde/style");
                mainPanel.setBackground(Color.BLACK);
            }

            // Actualizar el UI de la aplicación
            SwingUtilities.updateComponentTreeUI(mainPanel);

            // Repintar y actualizar los componentes
            repaint();
            revalidate();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        
        
        }
        
      
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header1 = new com.conde.component.Header();
        jSeparator1 = new javax.swing.JSeparator();
        buttonGroup1 = new javax.swing.ButtonGroup();
        panelBorder1 = new com.conde.swing.PanelBorder();
        menu = new com.conde.component.Menu();
        mainPanel = new javax.swing.JPanel();

        header1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1660, 968));
        setUndecorated(true);

        panelBorder1.setOpaque(false);
        panelBorder1.setLayout(new java.awt.BorderLayout());
        panelBorder1.add(menu, java.awt.BorderLayout.WEST);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setLayout(new java.awt.BorderLayout());
        panelBorder1.add(mainPanel, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 1660, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 968, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
     
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new Main().setVisible(true);
            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.conde.component.Header header1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel mainPanel;
    private com.conde.component.Menu menu;
    private com.conde.swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables


}
