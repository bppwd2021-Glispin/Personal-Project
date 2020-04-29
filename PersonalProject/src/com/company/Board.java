package com.company;

import java.awt.*;
import java.util.ArrayList;

public class Board {

    ArrayList<ArrayList<Tile>> grid = new ArrayList();
    int width, height, basex, basey, lastMousex, lastMousey;

    public Board(int width, int height){
        this.basex=960;
        this.basey=540;
        this.width=width;
        this.height=height;
        for(int i=0;i<width;i++){
            ArrayList<Tile> col = new ArrayList<>();
            for(int j=0;j<height;j++){
                int index = MyGame.randInt(1,14)-10;
                col.add(new Tile("Grass", Math.max(index,1),50*(i-(width/2)),50*(j-(height/2))));
            }
            grid.add(col);
        }
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
        for(ArrayList<Tile> col:grid){
            for(Tile t:col){
                t.draw(pen,basex,basey);
            }
        }
    }

}
