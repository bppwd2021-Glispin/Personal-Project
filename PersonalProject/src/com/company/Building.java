package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Building {

    String type;
    BufferedImage img;
    String[] buildOn;
    String[][] cost;

    public Building(String type){
        try {
            File myObj = new File("Images\\Buildings\\" + type + ".txt");
            Scanner myReader = new Scanner(myObj);
            buildOn = myReader.nextLine().split(",");
            String[] tempCost = myReader.nextLine().split(",");
            cost = new String[tempCost.length][2];
            for(int i=0;i<tempCost.length;i++){
                cost[i]=tempCost[i].split("-");
            }
        }
        catch(FileNotFoundException e) {
            System.out.println(e);
        }
        try {
            this.img = ImageIO.read(new File("Images\\Buildings\\"+type+".png"));
        }
        catch(IOException e){
            System.out.println(e);
        }
        this.type=type;
    }

}
