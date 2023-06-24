package com.SkillScraper;

import com.fasterxml.jackson.databind.JsonNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.SkillScraper.ResponseContent.*;

public class ToolbarPanel {
    public static JPanel topPanel = new JPanel();

    private static Image searchButtonImage;
    private static Image nextButtonImage;
    private static Image backButtonImage;

    static{
        try{
            searchButtonImage=Toolkit.getDefaultToolkit().getImage("E:\\icons\\search1.jpg");
            nextButtonImage=Toolkit.getDefaultToolkit().getImage("E:\\icons\\nextButton.png");
            backButtonImage= Toolkit.getDefaultToolkit().getImage("E:\\icons\\backButton.png");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static CircleButton searchButton;
    public static CircleButton nextButton;
    public static CircleButton backButton;
    public static JTextField field;
    public static void setGraphics(){
        Color newColor1=new Color(0,110,204);
        ToolbarPanel.topPanel.setBackground(newColor1.brighter());
        ToolbarPanel.topPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        ToolbarPanel.topPanel.setLayout(new FlowLayout(5,5,5));
    }

    public static void addTools(){
//        ToolbarPanel.loadImages();
        ToolbarPanel.setGraphics();

        ToolbarPanel.searchButton = new CircleButton(searchButtonImage);
        ToolbarPanel.nextButton = new CircleButton(nextButtonImage);
        ToolbarPanel.backButton = new CircleButton(backButtonImage);
        ToolbarPanel.field = new JTextField();

        field.setBorder(new RoundBorder());
        field.setFont(new Font("Times New Roman",Font.BOLD,20));
        field.setHorizontalAlignment(SwingConstants.LEFT);

        int fieldWidth = 800-searchButton.getDiameter();      //Giving the size according to frame size, as getWidth() returns zero.
        int fieldHeight = nextButton.getDiameter();

        field.setPreferredSize(new Dimension(fieldWidth,fieldHeight));

        ToolbarPanel.topPanel.add(backButton);
        ToolbarPanel.topPanel.add(nextButton);
        ToolbarPanel.topPanel.add(field);
        ToolbarPanel.topPanel.add(searchButton);

        searchButton.setEnabled(true);
        nextButton.setEnabled(true);
        backButton.setEnabled(true);
    }

    public static void addListenersToButtons(){
        ToolbarPanel.searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String query = field.getText().trim();
                if(query.length()==0){
                    ContentPanel.descriptionLabel.setText("No query found0");
                    return ;
                }

                Scraper scraper = new Scraper();
                try {
                    ResponseContent.contentMap.putAll(scraper.getData(query));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        ToolbarPanel.nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(ResponseContent.contentMap.isEmpty())
                    ContentPanel.descriptionLabel.setText("No query found2");
                else{
                    JsonNode nextNode;
                    if(prev == 1){
                        nextNode = ResponseContent.contentMap.get(HostName.YOUTUBE).get(cnt2);
                        prev=2;
                        ++cnt2;
                    }
                    else{
                        nextNode = ResponseContent.contentMap.get(HostName.UDEMY).get(cnt1);
                        prev=1;
                        ++cnt1;
                    }

                    ContentPanel.centralLabel.setText(nextNode.get("id").toString());
                }
            }
        });

        ToolbarPanel.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(ResponseContent.contentMap.isEmpty())
                    ContentPanel.descriptionLabel.setText("No query found1");
                else{
                    JsonNode nextNode;
                    if(prev == 1){
                        nextNode = ResponseContent.contentMap.get(HostName.UDEMY).get(cnt1);
                        prev=2;
                        cnt2--;

                        if(cnt2<0)
                            cnt2=0;
                    }
                    else{
                        nextNode = ResponseContent.contentMap.get(HostName.YOUTUBE).get(cnt1);
                        prev=1;
                        --cnt1;

                        if(cnt1<0)
                            cnt1=0;
                    }

                    ContentPanel.centralLabel.setText(nextNode.get("id").toString());
                }
            }
        });
    }
}
