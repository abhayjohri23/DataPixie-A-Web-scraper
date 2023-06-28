package com.SkillScraper.GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ContentPanel {
    public static JLabel centralLabel;
    public static JLabel descriptionLabel;
    public static JLabel infoLabel;
    public static JLabel thumbnail;
    public static JLabel info;

    public static void loadPanel(){
        //Setting the Central Panel
        centralLabel=new JLabel();
        centralLabel.setLayout(new BorderLayout());
        centralLabel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        centralLabel.setBackground(Color.WHITE);

        descriptionLabel=new JLabel();
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        descriptionLabel.setBorder(new EmptyBorder(0,0,0,0));
        descriptionLabel.setBackground(Color.WHITE);

        //Information Label - Thumbnail + Sub Info Label
        infoLabel=new JLabel();
        infoLabel.setLayout(new BorderLayout());
        infoLabel.setBorder(new EmptyBorder(0,0,0,0));
        infoLabel.setBackground(Color.WHITE);


        //Thumbnail - To display the thumbnail for the intro video.
        thumbnail = new JLabel();
        thumbnail.setHorizontalAlignment(SwingConstants.LEFT);
        thumbnail.setBackground(Color.WHITE);

        info = new JLabel();
        info.setHorizontalAlignment(SwingConstants.RIGHT);
        info.setBackground(Color.WHITE);

        infoLabel.add(thumbnail,BorderLayout.NORTH);
        thumbnail.setPreferredSize(new Dimension(infoLabel.getWidth(),infoLabel.getHeight()));

        infoLabel.add(info,BorderLayout.SOUTH);
        info.setPreferredSize(new Dimension(infoLabel.getWidth()*2, infoLabel.getHeight()));

        centralLabel.add(descriptionLabel,BorderLayout.NORTH);
        centralLabel.add(infoLabel, BorderLayout.CENTER);

        return ;
    }

}
