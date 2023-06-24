package org.example;

import java.util.Scanner;

public class App
{

    public static void main( String[] args ) throws Exception {
//        Window myWindow = Window.getWindow();
//         if(Window.loadImages())
//             Window.setUpWindow();
//         else
//             System.out.println("Window couldn't be loaded. Images are not loaded!");


        Scraper scraper = new Scraper();
        scraper.getData("python");
    }
}
