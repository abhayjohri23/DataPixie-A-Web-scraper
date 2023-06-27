package com.SkillScraper.GUI;

import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

public class RoundBorder extends AbstractBorder {
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width,int height){
        Color newColor1=new Color(0,110,204);
        g.setColor(newColor1.brighter());
        Area polyArea=new Area(new Rectangle(x,y,c.getWidth(),height));
        RoundRectangle2D.Double innerArea=new RoundRectangle2D.Double(x,y,c.getWidth(),height,height,height);
        polyArea.subtract(new Area(innerArea));

        Graphics2D g2D=(Graphics2D) g;
        g2D.fill(polyArea);

        g.setColor(Color.BLACK);
        g.drawRoundRect(x,y,c.getWidth()-1,c.getHeight(),height,height);

    }

    @Override
    public Insets getBorderInsets(Component c){
        return new Insets(10,10,10,10);
    }

}
