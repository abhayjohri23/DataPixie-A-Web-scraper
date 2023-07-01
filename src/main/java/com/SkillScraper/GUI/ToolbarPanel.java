package com.SkillScraper.GUI;
import com.SkillScraper.Backend.Database.ConnectionHandler;
import com.SkillScraper.Backend.Logic.HostName;
import com.SkillScraper.Backend.Logic.Scraper;
import com.fasterxml.jackson.databind.JsonNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.Color.WHITE;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ToolbarPanel {
    public static JPanel topPanel = new JPanel();
    private static final Image searchButtonImage=Toolkit.getDefaultToolkit().getImage("E:\\icons\\search1.jpg");
    public static ResultSet resultSet;          //When window is closed, this set will become null
    public static List<String[]> entryList;
    public static CircleButton searchButton;
    public static JTextField field;
    public static int pointer = -1;
    public static void setGraphics(){
        Color newColor1=new Color(0,110,204);
        ToolbarPanel.topPanel.setBackground(newColor1.brighter());
        ToolbarPanel.topPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        ToolbarPanel.topPanel.setLayout(new FlowLayout(5,5,5));
    }

    public static void addTools(){
        ToolbarPanel.setGraphics();

        ToolbarPanel.searchButton = new CircleButton(searchButtonImage);
        ToolbarPanel.field = new JTextField();

        field.setBorder(new RoundBorder());
        field.setFont(new Font("Times New Roman",Font.BOLD,20));
        field.setHorizontalAlignment(SwingConstants.LEFT);

        int fieldWidth = 750;      //Giving the size according to frame size, as getWidth() returns zero.
        int fieldHeight = 50;

        field.setPreferredSize(new Dimension(fieldWidth,fieldHeight));

        JLabel label = new JLabel("DataPixie");
        label.setFont(new Font("Times New Roman",Font.BOLD,22));
        label.setForeground(WHITE);

        label.setBackground(new Color(0,110,204));
        ToolbarPanel.topPanel.add(label);

        ToolbarPanel.topPanel.add(field);
        ToolbarPanel.topPanel.add(searchButton);

        searchButton.setEnabled(true);
        ToolbarPanel.addListenersToButtons();
    }

    public static void addListenersToButtons(){
        ToolbarPanel.searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String query = field.getText().trim();
                if(query.length()==0){
                      ContentPanel.headerLabel.setText("No query found0");
                        return ;
                }

                Scraper scraper = new Scraper();
            try {
                Map<HostName,List<JsonNode>> contentMap = scraper.getData(query);
                ConnectionHandler.handleRequest(contentMap);
                ToolbarPanel.resultSet = ConnectionHandler.getResponse(query);
                entryList = new ArrayList<>();
                while(resultSet!=null && resultSet.next()){
                    String[] entry = new String[6];
                    entry[0] = resultSet.getString("title");
                    entry[1] = resultSet.getString("headline");
                    entry[2] = resultSet.getString("price");
                    entry[3] = resultSet.getString("url");
                    entry[4] = resultSet.getString("image_url");
                    entry[5] = resultSet.getString("source");
                    entryList.add(entry);
                }

                    pointer = -1;
                    System.out.println("Search completed!");
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
