package com.conde.model;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class RutaBBDD extends JFileChooser {
    
  
    private FileNameExtensionFilter filter;
    private String ruta;
    
    
    public RutaBBDD(String r){
       this.filter =new FileNameExtensionFilter("Reg-Accidentes-Sector-Navarra", "mdb");
       this.ruta=r;
       
    }

    public RutaBBDD(FileNameExtensionFilter filter, String ruta) {
        this.filter = filter;
        this.ruta = ruta;
    }
    
    

    public String getRuta() {
        
        this.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        this.setFileFilter(this.filter);
        File directorio =new File(this.ruta);
        setCurrentDirectory(directorio);
        
        int opcion=this.showOpenDialog(this);
        if (opcion==JFileChooser.APPROVE_OPTION){
            
            return this.getSelectedFile().toString();
        }else{
            return "";
        }
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    
    
}
