package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public final class Window {
    private static Window appWindow;
    private static JFrame frame;
    private static Image searchImage;
    private static Image nextButtonImage;
    private static Image backButtonImage;
    private static final Color blueColor=new Color(0,155,255);

    private Window()
    {
        frame=new JFrame("upScale-Me");
        frame.setPreferredSize(new Dimension(1000,500));
        frame.setLocation(100,100);
        frame.setLayout(new BorderLayout());
    }

    public static boolean loadImages(){
        try{
            searchImage=Toolkit.getDefaultToolkit().getImage("E:\\icons\\search1.jpg");
            nextButtonImage=Toolkit.getDefaultToolkit().getImage("E:\\icons\\nextButton.png");
            backButtonImage= Toolkit.getDefaultToolkit().getImage("E:\\icons\\backButton.png");
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public static Window getWindow()
    {
        if(appWindow==null)
            appWindow=new Window();
        return appWindow;
    }


    public static void setUpWindow()
    {
        // Font textFont=new Font("Times New Roman",Font.BOLD|Font.ITALIC,30);
        //Setting up the containers

        Color backColor=new Color(255,255,255);
        Container mainContainer=frame.getContentPane();
        mainContainer.setBackground(backColor);
        mainContainer.setLayout(new BorderLayout(8,6));

        JPanel topPanel=getTopPanel();
        JLabel centralLabel=getCentralLabel();
        JPanel leftPanel=getLeftPane();

        mainContainer.add(topPanel,BorderLayout.NORTH);
        mainContainer.add(leftPanel,BorderLayout.WEST);
        mainContainer.add(centralLabel,BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static JPanel getTopPanel(){
        Font textFont=new Font("Times New Roman",Font.BOLD,20);
        Color newColor1=new Color(0,110,204);
        //Top Panel
        JPanel topPanel=new JPanel();
        topPanel.setBackground(newColor1.brighter());
        topPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        topPanel.setLayout(new FlowLayout(5,5,5));

        //Top Panel Components
        CircleButton searchButton=new CircleButton(searchImage);
        searchButton.setEnabled(true);
        CircleButton nextButton=new CircleButton(nextButtonImage);
        nextButton.setEnabled(true);
        CircleButton backButton=new CircleButton(backButtonImage);
        backButton.setEnabled(true);


        //Text Field
        JTextField field=new JTextField();
        field.setBorder(new RoundBorder());
        field.setHorizontalAlignment(SwingConstants.LEFT);
        field.setFont(textFont);

        topPanel.add(backButton);
        topPanel.add(nextButton);
        topPanel.add(field);
        topPanel.add(searchButton);

        int fieldWidth=800-searchButton.getDiameter();      //Giving the size according to frame size, as getWidth() returns zero.
        int fieldHeight=nextButton.getDiameter();

        field.setPreferredSize(new Dimension(fieldWidth,fieldHeight));

        return topPanel;
    }
    public static JLabel getCentralLabel(){
        //Central Label creation
        JLabel centralLabel=new JLabel();
        centralLabel.setLayout(new GridLayout(3,1));
        centralLabel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        centralLabel.setBackground(Color.WHITE);

        //Top Description Label - to display the description of the course displayed <h1>
        JLabel descriptionLabel=new JLabel("Description of the Course",SwingConstants.CENTER);
        descriptionLabel.setBorder(new EmptyBorder(0,0,0,0));
        descriptionLabel.setBackground(Color.WHITE);

        //Information Label - Thumbnail + Sub Info Label
        JLabel infoLabel=new JLabel();
        infoLabel.setLayout(new GridLayout(1,3));
        infoLabel.setBorder(new EmptyBorder(0,0,0,0));
        infoLabel.setBackground(Color.WHITE);


        //Thumbnail - To display the thumbnail for the intro video.
        JLabel thumbnail=new JLabel("Thumbnail",SwingConstants.CENTER);
        JLabel info=new JLabel("info: Price, Rating and link to purchase",SwingConstants.LEFT);
        thumbnail.setBackground(Color.WHITE);
        info.setBackground(Color.WHITE);

        //Sub Info Label - to display the label with information on price, rating and link to purchase (Purchase button).
        infoLabel.add(thumbnail,Component.RIGHT_ALIGNMENT);
        thumbnail.setPreferredSize(new Dimension(infoLabel.getWidth(),infoLabel.getHeight()));

        infoLabel.add(info,Component.LEFT_ALIGNMENT);
        info.setPreferredSize(new Dimension(infoLabel.getWidth()*2, infoLabel.getHeight()));

        centralLabel.add(descriptionLabel);
        centralLabel.add(infoLabel);

        return centralLabel;
    }
    public static JPanel getLeftPane(){
        //Left Panel Creation - Layout Manager, Border and Background Selection
        JPanel leftPanel=new JPanel();
        leftPanel.setLayout(new GridLayout(15,1,1,2));
        leftPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        leftPanel.setBackground(blueColor);

        //LeftPanel Components - Definition
        Font textFont=new Font("Book Antiqua",Font.PLAIN,16);
        JRadioButton sortOption1=new JRadioButton("Sort By: Price High to Low");
        sortOption1.setFont(textFont);

        sortOption1.setBackground(blueColor);

        JRadioButton sortOption2=new JRadioButton("Sort By: Price Low to High");
        sortOption2.setBackground(blueColor);
        sortOption2.setFont(textFont);

        JRadioButton sortOption3=new JRadioButton("Sort By: Popularity");
        sortOption3.setBackground(blueColor);
        sortOption3.setFont(textFont);

        JButton applyButton=new JButton("Apply");
        applyButton.setFont(textFont);
        applyButton.setPreferredSize(new Dimension(15,15));

        ButtonGroup bgrp=new ButtonGroup();
        bgrp.add(sortOption1);
        bgrp.add(sortOption2);
        bgrp.add(sortOption3);

        leftPanel.add(sortOption1);
        leftPanel.add(sortOption2);
        leftPanel.add(sortOption3);
        leftPanel.add(applyButton);

        return leftPanel;
    }
}
