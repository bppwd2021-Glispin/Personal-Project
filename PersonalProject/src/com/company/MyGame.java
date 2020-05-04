package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MyGame extends Game  {

    private static final int SCREEN_WIDTH = 1920;
    private static final int SCREEN_HEIGHT = 1080;
    static int framerate = 60;

    static int mousex, mousey;
    static Font baseFont;
    static Board board;
    static Player player;

    public static int randInt(int min, int max){
        Random rand = new Random();
        return rand.nextInt(max-min)+min;
    }

    public MyGame() {
        board = new Board(50,50);
        player = new Player();
    }

    public void update() {
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        mousex = (int) b.getX();
        mousey = (int) b.getY()-20;
        board.update();
        player.update();
    }

    public void draw(Graphics pen) {
        Color trueBlack = new Color(0,0,0);
        pen.setColor(trueBlack);
        baseFont = pen.getFont();
        board.draw(pen);
        player.draw(pen);
    }

    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
        switch (ke.getKeyCode()){
            case(KeyEvent.VK_T):
                MyGame.player.GUI.tileMode=!MyGame.player.GUI.tileMode;
                break;
            case(KeyEvent.VK_I):
                MyGame.player.GUI.invOpen=!MyGame.player.GUI.invOpen;
                break;
            case(KeyEvent.VK_B):
                MyGame.player.GUI.buildMode=!MyGame.player.GUI.buildMode;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) { }

    @Override
    public void mouseClicked(MouseEvent ke) { }

    @Override
    public void mousePressed(MouseEvent me) {
        switch (me.getButton()){
            case(MouseEvent.BUTTON1):
                player.m1=true;
                break;
            case(MouseEvent.BUTTON3):
                player.m2=true;
                board.lastMousex=mousex;
                board.lastMousey=mousey;
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        switch (me.getButton()){
            case(MouseEvent.BUTTON1):
                player.m1=false;
                break;
            case(MouseEvent.BUTTON3):
                player.m2=false;
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}

    public static void main(String[] args) {
        new MyGame().start(SCREEN_WIDTH,SCREEN_HEIGHT);
    }
}