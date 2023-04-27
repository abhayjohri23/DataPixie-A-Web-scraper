package org.example;


import java.io.IOException;

public class App
{

    public static void main( String[] args ) throws Exception {
        Window frame=Window.getWindow();
        if(Window.loadImages())
            Window.setUpWindow();
        else
            System.out.println("Window couldn't be loaded. Images are not loaded!");

        Scraper.getData("python","relevance");
    }
}
