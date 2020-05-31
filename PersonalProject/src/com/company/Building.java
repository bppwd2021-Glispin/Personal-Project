package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;

public class Building {

    String type, name;
    BufferedImage img;
    int offsetx, offsety;

    public Building(BufferedImage img, String type, String name, int offsetx, int offsety){
        this.img=img;
        this.type=type;
        this.name=name;
        this.offsetx=offsetx;
        this.offsety=offsety;
    }

    public void update(int basex, int basey){
        if(MyGame.mousex>basex+offsetx && MyGame.mousex<basex+offsetx+50 && MyGame.mousey>basey+offsety && MyGame.mousey<basey+offsety+50 && MyGame.player.m1){
            MyGame.player.GUI.highlightBuilding=this;
            MyGame.player.m1=false;
        }
    }

    public void draw(Graphics pen, int basex, int basey){
        pen.drawImage(img,basex+offsetx,basey+offsety,50,50,null);
    }

}