package com.SkillScraper.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;

/*Referring https://happycoding.io for tutorial to extend jButton for circular button*/
/*https://happycoding.io/tutorials/java/swing/circle-button with slight modification according to use*/
public class CircleButton extends JButton{
    private boolean mousePressed=false;
    private boolean mouseOver=false;
    private final int dia=50;                 //rep 50 pixels dimensioned button
    private Image image;


    public CircleButton(Image img) {
        this.image=img;
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);

        MouseAdapter mouseListener=new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                if(contains(e.getX(),e.getY()))
                    mousePressed=true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e){
                mousePressed=false;
                mouseOver=false;
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e){
                mouseOver=Point2D.distance(getWidth()/2,getHeight()/2,e.getX(),e.getY())<getDiameter()/2;
                repaint();
            }
        };
    }

    @Override
    public boolean contains(int x,int y){
        int diameter=this.getDiameter();
        int radius=diameter/2;
        return Point2D.distance(x,y,getWidth()/2,getHeight()/2)<radius;
    }

    public int getDiameter(){
        return dia;
    }

    @Override
    public Dimension getPreferredSize(){                    //Giving a constant size (50 pixels) to the button.
        return new Dimension(dia,dia);
    }

    @Override
    public void paintComponent(Graphics g){

        int radius=this.getDiameter()/2;
        int x=getWidth()/2-radius;
        int y=getHeight()/2-radius;

        g.setClip(new Ellipse2D.Double(x,y,dia,dia));           //Clipping the image with a shape. Ellipse2d.class is a class out of java.awt.geom;
        g.drawImage(image,x,y,dia,dia,null);           //We cover the clip of the image around the given ellipse area and draw the image
        //To set the image to the button

        g.setColor(new Color(0,102,204));


    }
}
