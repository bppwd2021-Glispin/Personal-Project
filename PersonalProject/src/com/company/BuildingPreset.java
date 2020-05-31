package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BuildingPreset {

    String type, name, UIBackground;
    BufferedImage img;
    String[] buildOn;
    String[][] cost;

    public BuildingPreset(String type){
        try {
            File myObj = new File("Images\\Buildings\\" + type + ".txt");
            Scanner myReader = new Scanner(myObj);
            name = myReader.nextLine();
            buildOn = myReader.nextLine().split(",");
            String[] tempCost = myReader.nextLine().split(",");
            cost = new String[tempCost.length][2];
            for(int i=0;i<tempCost.length;i++){
                cost[i]=tempCost[i].split("-");
            }
            UIBackground = myReader.nextLine();
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

    public Building build(int offsetx, int offsety){
        return new Building(img,type,name,offsetx,offsety);
    }

}
