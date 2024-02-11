package com.conde.swing;

import com.conde.model.StatusType;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;

public class TableStatus extends JLabel {

    public TableStatus() {
        setForeground(Color.white);
        setFont(new Font("roboto",1,12));
        
    }

    private StatusType type;
    private boolean selType;

    public StatusType getType() {
        return type;
    }

    public void setType(StatusType type) {
        this.type = type;
        setText(type.toString());
        repaint();
    }
    
    public void selectedTypeOn(boolean isSelected){
        this.selType = isSelected;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        if (type != null) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint g;
            if (type == StatusType.PAMPLONA ) {
                if (selType){
                         
                    g = new GradientPaint(0, 0, new Color(6, 72, 72), 0, getHeight(), new Color(6, 72, 72));
                }else {
                    g = new GradientPaint(0, 0, new Color(112,138,89 ), 0, getHeight(), new Color(120, 161, 84));
                }
                
            } else if (type == StatusType.TUDELA) {
                      if (selType){
                         
                    g = new GradientPaint(0, 0, new Color(6, 72, 72), 0, getHeight(), new Color(6, 72, 72));
                }else {
                      g = new GradientPaint(0, 0, new Color(51,128,147 ), 0, getHeight(), new Color(84,158,176));
                }
                              
            } else {
                g = new GradientPaint(0, 0, new Color(234, 216, 84 ), 0, getHeight(), new Color(231, 222, 157));
            }
            g2.setPaint(g);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 1, 1);

        }

        super.paintComponent(grphcs);
    }

}
