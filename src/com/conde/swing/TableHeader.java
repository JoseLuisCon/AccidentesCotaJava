
package com.conde.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;


public class TableHeader extends JLabel {

    public TableHeader(String text) {
        super(text);
        setOpaque(true);
        setPreferredSize(new Dimension(getWidth(),25));
        setBackground(Color.white);
        setFont(new Font("sansserif",1,12));
        setForeground(new Color(102,102,102));
        setBorder(new EmptyBorder(0,0,0,0));
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.setColor(new Color(230,230,230));
        g.drawLine(0, getHeight()-1, getWidth(), getHeight()-1);
    }

    
    
    
        
}
