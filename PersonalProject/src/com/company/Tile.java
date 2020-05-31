package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.IOException;
import java.util.ArrayList;

public class Tile {

    ArrayList<String> otherTypes = new ArrayList<>();
    ArrayList<BufferedImage> otherLeftBorder = new ArrayList<>();
    ArrayList<BufferedImage> otherTopBorder = new ArrayList<>();
    BufferedImage img;
    int offsetx, offsety;
    String type;
    Building building = null;

    public Tile(String type, int index, int offsetx, int offsety){
        if(type.equals("Water")){
            index=1;
        }
        try {
            if(type.equals("Null")){
                index=1;
            }
            this.img = ImageIO.read(new File("Images\\Tiles\\"+type+"\\"+type+index+".png"));
        }
        catch(IOException e){
            System.out.println(e);
        }
        for(String others:Board.tileTypes){
            if(!others.equals(type)){
                otherTypes.add(others);
                try {
                    otherLeftBorder.add(ImageIO.read(new File("Images\\Tiles\\Borders\\BordersHorizontal-"+others+type+".png")));
                    otherTopBorder.add(ImageIO.read(new File("Images\\Tiles\\Borders\\BordersVertical-"+others+type+".png")));
                }
                catch(IOException e){
                    System.out.println(e);
                }
            }
        }
        this.type=type;
        this.offsetx=offsetx;
        this.offsety=offsety;
    }

    public void update(int basex, int basey){
        if(building!=null) {
            building.update(basex, basey);
        }
    }

    public void draw(Graphics pen, int basex, int basey){
        pen.drawImage(img,basex+offsetx,basey+offsety,50,50 ,null);
    }

    public void borderLeft(Graphics pen, int basex, int basey, String type){
        int index = otherTypes.indexOf(type);
        if(index==-1){
            return;
        }
        pen.drawImage(otherLeftBorder.get(index),basex+offsetx-6,basey+offsety,12,50,null);
    }

    public void borderUp(Graphics pen, int basex, int basey, String type){
        int index = otherTypes.indexOf(type);
        if(index==-1){
            return;
        }
        pen.drawImage(otherTopBorder.get(index),basex+offsetx,basey+offsety-6,50,12,null);
    }

    public void drawBuilding(Graphics pen, int basex, int basey){
        building.draw(pen,basex,basey);
    }


    public void outline(Graphics pen, int basex, int basey){
        pen.drawImage(MyGame.board.tileOutline,basex+offsetx,basey+offsety,50,50,null);
    }
}
