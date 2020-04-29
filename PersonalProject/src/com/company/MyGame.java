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
    
    public MyGame() {

    }

    public void update() {

    }

    public void draw(Graphics pen) {

    }

    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) { }

    @Override
    public void keyReleased(KeyEvent ke) { }

    @Override
    public void mouseClicked(MouseEvent ke) { }

    @Override
    public void mousePressed(MouseEvent me) { }

    @Override
    public void mouseReleased(MouseEvent me) { }

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}

    public static void main(String[] args) {
        new MyGame().start(SCREEN_WIDTH,SCREEN_HEIGHT);
    }
}