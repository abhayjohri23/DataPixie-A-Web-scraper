package com.SkillScraper.GUI;

import com.SkillScraper.Backend.Database.ConnectionHandler;
import com.SkillScraper.Backend.Logic.HostName;
import com.SkillScraper.Backend.Logic.ResponseContent;
import com.SkillScraper.Backend.Logic.Scraper;
import com.fasterxml.jackson.databind.JsonNode;

import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.SkillScraper.Backend.Logic.HostName.UDEMY;
import static com.SkillScraper.Backend.Logic.HostName.YOUTUBE;
import static com.SkillScraper.Backend.Logic.ResponseContent.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ToolbarPanel {
    public static JPanel topPanel = new JPanel();

    private static Image searchButtonImage;
    private static Image nextButtonImage;
    private static Image backButtonImage;
    public static ResultSet resultSet;          //When window is closed, this set will become null

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

    public static int pointer = 0;
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

        ToolbarPanel.addListenersToButtons();
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
                    Map<HostName,List<JsonNode>> contentMap = scraper.getData(query);
                    ConnectionHandler.handleRequest(contentMap);
                    ToolbarPanel.resultSet = ConnectionHandler.getResponse(query);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        ToolbarPanel.nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(resultSet == null)
                    return ;
                try{
                    resultSet.next();
                }catch(Exception e){
                    System.out.println("We found the end!");
                    return ;
                }

                try {
                    ContentPanel.descriptionLabel.setText(resultSet.getString("TITLE"));
                    ContentPanel.centralLabel.setText(resultSet.getString("HEADLINE") +
                            "\nBy: "+resultSet.getString("INSTRUCTOR_NAME")+" ("+resultSet.getString("SOURCE")+")\n"
                    +"PRICE: "+resultSet.getString("PRICE"));

                    String url = resultSet.getString("IMAGE_URL");
//                    HttpRequest image_request = HttpRequest.newBuilder().uri(URI.create(url)).build();
//
//                    HttpResponse image = HttpClient.newHttpClient().send(image_request, HttpResponse.BodySubscribers.);
//                    ContentPanel.thumbnail
//                            .setIcon(new Image(Toolkit.getDefaultToolkit().getImage()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        ToolbarPanel.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(resultSet == null)
                    return ;
                try{
                    resultSet.previous();
                }catch(Exception e){
                    e.printStackTrace();
                    System.out.println("We found the end!");
                    return ;
                }

                try {
                    ContentPanel.descriptionLabel.setText(resultSet.getString("TITLE"));
                    ContentPanel.info.setText(resultSet.getString("HEADLINE") +
                            "\n By:"+resultSet.getString("INSTRUCTOR_NAME ("+resultSet.getString("SOURCE")+")\n"
                            +"PRICE: "+resultSet.getString("PRICE")));

                    ContentPanel.thumbnail
                            .setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(resultSet.getString("IMAGE_URL"))));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
