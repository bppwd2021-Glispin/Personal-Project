package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player {

    UI GUI;
    boolean m1, m2;
    int cash;
    ArrayList<Integer> inventoryStack = new ArrayList<>();
    ArrayList<Resource> inventory = new ArrayList<>();

    public Player(){
        inventory.add(new Resource("Wood"));
        inventoryStack.add(55);
        GUI = new UI();
        cash=300;
    }

    public void update(){
        GUI.update();
    }

    public void draw(Graphics pen){
        GUI.draw(pen);
    }
}
