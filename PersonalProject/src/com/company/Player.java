package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {

    BufferedImage cursor, pointingCur, grabCur;
    boolean m1, m2;

    public Player(){
        try {
            this.pointingCur = ImageIO.read(new File("Images\\Mouse\\Pointer.png"));
            this.grabCur = ImageIO.read(new File("Images\\Mouse\\Grab.png"));
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    public void update(){
        if(m2){
            cursor=grabCur;
        }
        else{
            cursor=pointingCur;
        }
    }

    public void draw(Graphics pen){
        pen.drawImage(cursor,MyGame.mousex,MyGame.mousey,25,30,null);
    }
}
