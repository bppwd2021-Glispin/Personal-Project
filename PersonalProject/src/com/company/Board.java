package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {

    static String[] tileTypes = new String[]{"Grass","Stone","Sand","Water"};
    static String[] resourceTypes = new String[]{"Money","Wood","Stone","Coal"};

    ArrayList<ArrayList<Tile>> grid = new ArrayList();
    BufferedImage tileOutline;
    int width, height, basex, basey, lastMousex, lastMousey;

    public Board(int width, int height){
        try {
            this.tileOutline = ImageIO.read(new File("Images\\Tiles\\TileMode\\Outline.png"));
        }
        catch(IOException e){
            System.out.println(e);
        }
        try {
            File myObj = new File("Maps\\Map1.txt");
            Scanner myReader = new Scanner(myObj);
            int rows = 0;
            while(myReader.hasNextLine()){
                rows++;
                String[] data = myReader.nextLine().split(",");
                ArrayList<Tile> col = new ArrayList<>();
                for(int i=0;i<data.length;i++){
                    int index = MyGame.randInt(1,104)-100;
                    String tile = "Grass";
                    switch(data[i]){
                        case("G"):
                            tile="Grass";
                            break;
                        case("S"):
                            tile="Stone";
                            break;
                        case("D"):
                            tile="Sand";
                            break;
                        case("W"):
                            tile="Water";
                            break;
                    }
                    col.add(new Tile(tile,Math.max(index,1),50*(i-(width/2)),50*(rows-(height/2))));
                }
                grid.add(col);
            }
            myReader.close();
        }
        catch(FileNotFoundException e) {
            System.out.println(e);
        }
        this.basex=960;
        this.basey=540;
        this.width=width;
        this.height=height;
    }

    public void update(){
        if(MyGame.player.m2){
            basex+=MyGame.mousex-lastMousex;
            basey+=MyGame.mousey-lastMousey;
            lastMousex=MyGame.mousex;
            lastMousey=MyGame.mousey;
        }
    }

    public void draw(Graphics pen){
        int minX = 0;
        int maxX = -1;
        boolean leave = false;
        for(int i=0;i<grid.size();i++){
            for(int j=minX;j<grid.get(i).size();j++){
                if(basey+grid.get(i).get(j).offsety<-50){
                    break;
                }
                if(basex+grid.get(i).get(j).offsetx<-50){
                    minX=j+1;
                    continue;
                }
                if(basey+grid.get(i).get(j).offsety>1080){
                    leave = true;
                    break;
                }
                if(maxX==j || basex+grid.get(i).get(j).offsetx>1920){
                    maxX=j;
                    break;
                }
                grid.get(i).get(j).draw(pen,basex,basey);
                if(j-1>0 && !grid.get(i).get(j).type.equals(grid.get(i).get(j-1).type)){
                    grid.get(i).get(j).borderLeft(pen, basex, basey, grid.get(i).get(j-1).type);
                }
                if(i-1>0 && !grid.get(i).get(j).type.equals(grid.get(i-1).get(j).type)){
                    grid.get(i).get(j).borderUp(pen, basex, basey, grid.get(i-1).get(j).type);
                }
            }
            if(leave){
                break;
            }
        }
        leave = false;
        if(MyGame.player.GUI.tileMode) {
            for (int i = 0; i < grid.size(); i++) {
                for (int j = minX; j < grid.get(i).size(); j++) {
                    if (basey + grid.get(i).get(j).offsety < -50) {
                        break;
                    }
                    if (basex + grid.get(i).get(j).offsetx < -50) {
                        minX = j + 1;
                        continue;
                    }
                    if (basey + grid.get(i).get(j).offsety > 1080) {
                        leave = true;
                        break;
                    }
                    if (maxX == j || basex + grid.get(i).get(j).offsetx > 1920) {
                        maxX = j;
                        break;
                    }
                    grid.get(i).get(j).outline(pen, basex, basey);
                }
                if (leave) {
                    break;
                }
            }
        }
    }
}
