package com.SkillScraper.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;

import static com.SkillScraper.GUI.ContentPanel.headerLabel;
import static com.SkillScraper.GUI.ToolbarPanel.entryList;
import static com.SkillScraper.GUI.ToolbarPanel.pointer;

public class LeftPanel {
    public static JPanel leftPanel;
    public static JRadioButton sortOption1;
    public static JRadioButton sortOption2;
    public static JButton applyButton;
    public static ButtonGroup bgrp=new ButtonGroup();
    private static final Color blueColor=new Color(0,155,255);
    public static void loadLeftPanel(){
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(15,1,1,2));
        leftPanel.setBorder(BorderFactory.createEtchedBorder());
        leftPanel.setBackground(blueColor);

        sortOption1=new JRadioButton("Sort By: Price High to Low");
        sortOption1.setFont(new Font("Times New Roman",Font.BOLD,16));
        sortOption1.setBackground(blueColor);

        sortOption2=new JRadioButton("Sort By: Price Low to High");
        sortOption2.setBackground(blueColor);
        sortOption2.setFont(new Font("Times New Roman",Font.BOLD,16));

        applyButton=new JButton("Apply");
        applyButton.setFont(new Font("Times New Roman",Font.BOLD,16));
        applyButton.setPreferredSize(new Dimension(15,15));

        bgrp.add(sortOption1);
        bgrp.add(sortOption2);


        leftPanel.add(sortOption1);
        leftPanel.add(sortOption2);
        leftPanel.add(applyButton);

        applyButton.setEnabled(true);
        LeftPanel.addListeners();

        return ;
    }

    public static void addListeners(){
        class comp1 implements Comparator<String[]> {
            @Override
            public int compare(String[] a,String[] b){
                return a[3].compareTo(b[3]);
            }
        }
        class comp2 implements Comparator<String[]> {
            @Override
            public int compare(String[] a,String[] b){
                return a[3].compareTo(b[3])*-1;
            }
        }
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(headerLabel.getText().toString().length()>0)  return ;
                if(sortOption1.isSelected()) {
                    Collections.sort(entryList,new comp1());
                } else{
                    Collections.sort(entryList, new comp2());
                }

                System.out.println("Sorted!");
            }
        });
    }
}
