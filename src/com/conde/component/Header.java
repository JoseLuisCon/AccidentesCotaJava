
package com.conde.component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import textfield.SearchOptinEvent;
import textfield.SearchOption;


public class Header extends javax.swing.JPanel {
    
    private String textSearch="";


    public Header() {
        initComponents();
        setOpaque(false);
        
        txtSearchFiled.addEventOptionSelected(new SearchOptinEvent() {
            @Override
            public void optionSelected(SearchOption option, int index) {
                txtSearchFiled.setHint("Búsqueda por "+ option.getName() + "...");
            }
        });
        
        
        txtSearchFiled.addOption(new SearchOption("Fecha",new ImageIcon(getClass().getResource("/com/conde/resources/icons/fecha.png"))));
        txtSearchFiled.addOption(new SearchOption("Equipo",new ImageIcon(getClass().getResource("/com/conde/resources/icons/horizonte.png"))));
        txtSearchFiled.addOption(new SearchOption("Carretera",new ImageIcon(getClass().getResource("/com/conde/resources/icons/carretera.png"))));
        txtSearchFiled.addOption(new SearchOption("Diligencias",new ImageIcon(getClass().getResource("/com/conde/resources/icons/diligencias.png"))));
        txtSearchFiled.addOption(new SearchOption("Patrulla",new ImageIcon(getClass().getResource("/com/conde/resources/icons/patrulla.png"))));
        txtSearchFiled.setSelectionEnd(0);
        
        
        
        txtSearchFiled.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
               actualizarPropiedad();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarPropiedad();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
               actualizarPropiedad();
            }
            private void actualizarPropiedad() {
                // Obtener el texto del JTextField y establecerlo como la propiedad
                setTextSearch(textSearch);
                
            }
            
            
        });
        
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSearchFiled = new textfield.TextFieldSearchOption();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/conde/resources/icons/search.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("LISTADO DE ACCIDENTES");

        txtSearchFiled.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchFiledKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearchFiled, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtSearchFiled, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchFiledKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchFiledKeyReleased
        if (txtSearchFiled.isSelected()){
            int option = txtSearchFiled.getSelectedIndex();
            String text = txtSearchFiled.getText().trim();
            if (option == 0){
                //Busqueda por fecha
               
                
                textSearch="#"+text+"#";
                
            }else if(option == 1) {
                //Busqueda por equipo
            
            }else if (option == 2){
                // Busqueda por carretera
            
            }else if (option == 3){
                // Busqueda por diligencias
                
            }else if (option ==4){
                // Busdqueda por patrulla
            
            }
            
        }
    }//GEN-LAST:event_txtSearchFiledKeyReleased

    public String getTextSearch() {
        return textSearch;
    }
    
    public void setTextSearch(String t){
        this.textSearch=t;
  }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(0, 0, 25, getHeight());
        g2.fillRect(getWidth()-25,getHeight()-25, getWidth(), getHeight());
        super.paintComponent(grphcs); 
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private textfield.TextFieldSearchOption txtSearchFiled;
    // End of variables declaration//GEN-END:variables
}
