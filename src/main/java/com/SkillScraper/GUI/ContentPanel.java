package com.SkillScraper.GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static java.awt.BorderLayout.LINE_END;
import static java.awt.BorderLayout.LINE_START;
import static java.awt.Color.WHITE;

import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import java.net.URL;

import static com.SkillScraper.GUI.ToolbarPanel.entryList;
import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.NORTH;
import static javax.swing.SwingConstants.SOUTH;

public class ContentPanel {
    public static JPanel centralPanel = new JPanel(new BorderLayout());
    public static JLabel headerLabel = new JLabel();
    public static JLabel picLabel = new JLabel();
    public static JLabel descLabel = new JLabel();
    public static JPanel footerPanel = new JPanel(new BorderLayout());
    public static JButton next = new JButton("Next");;
    public static JButton previous = new JButton("Previous");
    public static void loadPanel(){
        //Setting the Central Panel

        centralPanel.setBackground(WHITE);
        centralPanel.setBorder(BorderFactory.createEtchedBorder());

        previous.setBackground(WHITE);
        previous.setFont(new Font("Times New Roman",Font.BOLD,22));
        previous.setBorder(new EmptyBorder(0,0,0,0));

        next.setBackground(WHITE);
        next.setFont(new Font("Times New Roman",Font.BOLD,22));
        next.setBorder(new EmptyBorder(0,0,0,0));

        footerPanel.add(previous,LINE_START);
        footerPanel.add(next,LINE_END);
        footerPanel.setPreferredSize(new Dimension(650,35));
        footerPanel.setBackground(WHITE);

        JPanel middlePane = new JPanel(new BorderLayout());

        picLabel.setHorizontalAlignment(CENTER);
        descLabel.setHorizontalAlignment(CENTER);
        middlePane.setBackground(WHITE);

        picLabel.setPreferredSize(new Dimension(160,150));
        descLabel.setPreferredSize(new Dimension(150,150));


        middlePane.setPreferredSize(new Dimension(400,300));
        middlePane.add(picLabel,NORTH);
        middlePane.add(descLabel,SOUTH);

        headerLabel.setHorizontalAlignment(CENTER);
        headerLabel.setPreferredSize(new Dimension(650,65));

        next.setEnabled(true);
        previous.setEnabled(true);

        centralPanel.add(headerLabel,NORTH);
        centralPanel.add(footerPanel,SOUTH);
        centralPanel.add(middlePane,CENTER);

        ContentPanel.addListeners();
        return ;
    }

    public static void addListeners(){
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(ToolbarPanel.pointer==entryList.size()-1){
                    return ;
                }
                ++ToolbarPanel.pointer;
                String[] data = entryList.get(ToolbarPanel.pointer);

                headerLabel.setText(data[0].substring(1,data[0].length()-1));
                headerLabel.setFont(new Font("Times New Roman",Font.BOLD,22));

                descLabel.setText("<html>"+data[1].substring(1,data[1].length()-1)+"<br>"+"Price: "+data[2]+"<br>"+"LINK: <a href="+data[3]+">"+data[5]+"</a><html>");
                descLabel.setFont(new Font("Times New Roman",Font.ITALIC,16));

                String url = data[4].replaceAll("\"","");
                try {
                    Icon pic = new ImageIcon(ImageIO.read(new URL(url)));
                    picLabel.setIcon(pic);
                } catch (IOException e) {
                    picLabel.setText("Image Not found");
                }
            }
        });

        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(ToolbarPanel.pointer==0){
                    return ;
                }
                --ToolbarPanel.pointer;
                String[] data = entryList.get(ToolbarPanel.pointer);
                headerLabel.setText(data[0].substring(1,data[0].length()-1));
                headerLabel.setFont(new Font("Times New Roman",Font.BOLD,22));

                descLabel.setText("<html>"+data[1].substring(1,data[1].length()-1)+"<br>"+"Price: "+data[2]+"<br>"+"LINK: <a href="+data[3]+">"+data[5]+"</a><html>");
                descLabel.setFont(new Font("Times New Roman",Font.ITALIC,16));

                String url = data[4].replaceAll("\"","");
                try {
                    Icon pic = new ImageIcon(ImageIO.read(new URL(url)));
                    picLabel.setIcon(pic);
                } catch (IOException e) {
                    picLabel.setText("Image Not found");
                }
            }
        });
    }
}
