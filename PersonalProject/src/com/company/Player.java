package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player {

    BufferedImage cursor, pointingCur, grabCur;
    UI GUI;
    boolean m1, m2;
    int cash;
    ArrayList<Integer> inventoryStack = new ArrayList<>();
    ArrayList<Resource> inventory = new ArrayList<>();

    public Player(){
        try {
            this.pointingCur = ImageIO.read(new File("Images\\Mouse\\Pointer.png"));
            this.grabCur = ImageIO.read(new File("Images\\Mouse\\Grab.png"));
        }
        catch(IOException e){
            System.out.println(e);
        }
        inventory.add(new Resource("Wood"));
        inventory.add(new Resource("Stone"));
        inventoryStack.add(10);
        inventoryStack.add(20);
        GUI = new UI();
        cash=100;
    }

    public void update(){
        GUI.update();
        if(m2){
            cursor=grabCur;
        }
        else{
            cursor=pointingCur;
        }
    }

    public void draw(Graphics pen){
        GUI.draw(pen);
        pen.drawImage(cursor,MyGame.mousex-10,MyGame.mousey,null);
    }
}
