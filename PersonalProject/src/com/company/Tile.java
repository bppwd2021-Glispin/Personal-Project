package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tile {

    BufferedImage img;
    int offsetx, offsety;
    String type;

    public Tile(String type, int index, int offsetx, int offsety){
        try {
            this.img = ImageIO.read(new File("Images\\Tiles\\"+type+"\\"+type+index+".png"));
        }
        catch(IOException e){
            System.out.println(e);
        }
        this.offsetx=offsetx;
        this.offsety=offsety;
    }

    public void draw(Graphics pen, int basex, int basey){
        pen.drawImage(img,basex+offsetx,basey+offsety,50,50 ,null);
    }
}
