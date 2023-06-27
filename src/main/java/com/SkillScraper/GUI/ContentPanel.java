package com.SkillScraper.GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ContentPanel {
    public static JLabel centralLabel;
    public static JLabel descriptionLabel;
    public static JLabel infoLabel;
    public static JLabel thumbnail;

    public static void loadPanel(){
        //Setting the Central Panel
        centralLabel=new JLabel();
        centralLabel.setLayout(new GridLayout(3,1));
        centralLabel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        centralLabel.setBackground(Color.WHITE);

        descriptionLabel=new JLabel("Description of the Course",SwingConstants.CENTER);
        descriptionLabel.setBorder(new EmptyBorder(0,0,0,0));
        descriptionLabel.setBackground(Color.WHITE);

        //Information Label - Thumbnail + Sub Info Label
        infoLabel=new JLabel();
        infoLabel.setLayout(new GridLayout(1,3));
        infoLabel.setBorder(new EmptyBorder(0,0,0,0));
        infoLabel.setBackground(Color.WHITE);


        //Thumbnail - To display the thumbnail for the intro video.
        thumbnail=new JLabel("Thumbnail",SwingConstants.CENTER);
        JLabel info=new JLabel("info: Price, Rating and link to purchase",SwingConstants.LEFT);
        thumbnail.setBackground(Color.WHITE);
        info.setBackground(Color.WHITE);

        infoLabel.add(thumbnail,Component.RIGHT_ALIGNMENT);
        thumbnail.setPreferredSize(new Dimension(infoLabel.getWidth(),infoLabel.getHeight()));

        infoLabel.add(info,Component.LEFT_ALIGNMENT);
        info.setPreferredSize(new Dimension(infoLabel.getWidth()*2, infoLabel.getHeight()));

        centralLabel.add(descriptionLabel);
        centralLabel.add(infoLabel);

        return ;
    }

}
