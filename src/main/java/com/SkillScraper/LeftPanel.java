package com.SkillScraper;

import javax.swing.*;
import java.awt.*;

public class LeftPanel {
    public static JPanel leftPanel;
    public static JRadioButton sortOption1;
    public static JRadioButton sortOption2;
    public static JRadioButton sortOption3;
    public static JButton applyButton;

    private static final Color blueColor=new Color(0,155,255);
    public static void loadLeftPanel(){
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(15,1,1,2));
        leftPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        leftPanel.setBackground(blueColor);

        sortOption1=new JRadioButton("Sort By: Price High to Low");
        sortOption1.setFont(new Font("Times New Roman",Font.BOLD,16));
        sortOption1.setBackground(blueColor);

        sortOption2=new JRadioButton("Sort By: Price Low to High");
        sortOption2.setBackground(blueColor);
        sortOption2.setFont(new Font("Times New Roman",Font.BOLD,16));

        sortOption3=new JRadioButton("Sort By: Popularity");
        sortOption3.setBackground(blueColor);
        sortOption3.setFont(new Font("Times New Roman",Font.BOLD,16));

        applyButton=new JButton("Apply");
        applyButton.setFont(new Font("Times New Roman",Font.BOLD,16));
        applyButton.setPreferredSize(new Dimension(15,15));

        ButtonGroup bgrp=new ButtonGroup();
        bgrp.add(sortOption1);
        bgrp.add(sortOption2);
        bgrp.add(sortOption3);

        leftPanel.add(sortOption1);
        leftPanel.add(sortOption2);
        leftPanel.add(sortOption3);
        leftPanel.add(applyButton);

        return ;
    }
}
