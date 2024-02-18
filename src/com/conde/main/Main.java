package com.conde.main;

import com.conde.form.FormEstadistica;
import com.conde.form.Form_Home;

import com.conde.model.ConexionAccess;

import com.conde.form.FormAddAccident;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Main extends javax.swing.JFrame {

    private Form_Home home;
   
    private FormAddAccident frmAddAcci;
    
    private FormEstadistica frmEstaditica;

    private int indexPanel = 0;

    private boolean themeLight = true;

    public void setForm(int NumForm, int accidente) {
        //Método para seleccionar paneles desde otros componentes o formularios
        if (NumForm == 1) {
            setTheme();
            home = new Form_Home(this);
            home.cargarAccidentes("");
            setForm(home);
            indexPanel = 1;
        } else if (NumForm == 2) {
            if (accidente == 0) {
                System.out.println("Accidente: " + accidente);
                setTheme();
                frmAddAcci = new FormAddAccident(this);

                setForm(frmAddAcci);
                indexPanel = 2;

            } else {
                System.out.println("Accidente: " + accidente);
                setTheme();
                frmAddAcci = new FormAddAccident(this, accidente);

                setForm(frmAddAcci);
                indexPanel = 2;

            }
        }
    }

    public Main() {

        initComponents();

        setBackground(new Color(0, 0, 0, 0));
        menu.initMoving(Main.this);

        menu.addEventToogleTheme((Boolean toogleTheme) -> {
            themeLight = toogleTheme;
            toggleTema(themeLight);

        });

        menu.addEventMenuSelected((int index) -> {
           
            switch (index) {
                case 1:
                    setTheme();
                    home = new Form_Home(this);
                    home.cargarAccidentes("");
                    setForm(home);
                    indexPanel = 1;
                    break;

                case 2:

                    setTheme();
                    frmAddAcci = new FormAddAccident(this);

                    setForm(frmAddAcci);
                    indexPanel = 2;
                    break;
                    
                case 3:

                    setTheme();
                    frmEstaditica = new FormEstadistica(this);

                    setForm(frmEstaditica);
                    indexPanel = 3;
                    break;
             
             
                case 6:
                    ConexionAccess.desConnection();
                     {
                        try {
                            Thread.sleep(300);
                            System.exit(0);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                default:
                    break;
            }
        });

        setTheme();
        home = new Form_Home(this);
        home.cargarAccidentes("");

        setForm(home);

    }

    private void setForm(JComponent com) {

        mainPanel.removeAll();
        CardLayout cl = new CardLayout();

        mainPanel.setLayout(cl);

        mainPanel.add(com);

        mainPanel.repaint();
        mainPanel.revalidate();

    }

    private void setTheme() {

        try {
            if (themeLight) {
                FlatLightLaf.registerCustomDefaultsSource("com/conde/style");
                UIManager.setLookAndFeel(new FlatLightLaf());

                mainPanel.setBackground(Color.WHITE);
            } else {

                UIManager.setLookAndFeel(new FlatDarkLaf());

                FlatDarkLaf.registerCustomDefaultsSource("com/conde/style");
                mainPanel.setBackground(Color.BLACK);
            }
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Error al cargar los temas" + e.getMessage());
        }

    }

    private void toggleTema(boolean theme) {

//        if (indexPanel ==2){
        try {
            // Obtener el Look and Feel actual
//            UIManager.LookAndFeelInfo lookAndFeelInfo = UIManager.getInstalledLookAndFeels()[0];

            // Cambiar entre el tema dark y light
            if (theme) {
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

            themeLight = theme;

        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

//        }
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
        setMinimumSize(new java.awt.Dimension(1660, 968));
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelBorder1.setOpaque(false);
        panelBorder1.setLayout(new java.awt.BorderLayout());
        panelBorder1.add(menu, java.awt.BorderLayout.WEST);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setLayout(new java.awt.CardLayout());
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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                FlatLightLaf.registerCustomDefaultsSource("com/conde/style");
                FlatLightLaf.setup();

//                new Main().setVisible(true);
                JFrame frame = new Main();

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Obtener el entorno gráfico
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                GraphicsDevice[] gd = ge.getScreenDevices();

                // Obtener la resolución de la pantalla principal
                DisplayMode displayMode = gd[0].getDisplayMode();
                int screenWidth = displayMode.getWidth();
                int screenHeight = displayMode.getHeight();

                // Establecer las proporciones deseadas
                double proporcionAncho = 0.8; // Porcentaje del ancho de la pantalla
                double proporcionAlto = 0.6;  // Porcentaje del alto de la pantalla

                // Calcular el tamaño del JFrame
                int nuevoAncho = (int) (screenWidth * proporcionAncho);
                int nuevoAlto = (int) (screenHeight * proporcionAlto);

                // Configurar el tamaño y la posición del JFrame
                frame.setSize(nuevoAncho, nuevoAlto);
                frame.setLocationRelativeTo(null); // Centrar en la pantalla

                frame.setVisible(true);

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
