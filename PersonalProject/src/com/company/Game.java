package com.company;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public abstract class Game implements KeyListener, MouseListener
{
    private JFrame frame;
    private GamePanel gamePanel;
    boolean running;
    private MyGame game;

    protected void start(int width, int height)
    {
        this.game = (MyGame)this;
        running = true;
        frame = new JFrame("Strategy Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePanel = new GamePanel();
        frame.getContentPane().add(BorderLayout.CENTER, gamePanel);
        frame.setResizable(false);
        frame.setSize(width, height);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.addKeyListener(this);
        frame.addMouseListener(this);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(3,3,BufferedImage.TYPE_INT_ARGB),new Point(0,0),"blank cursor");
        frame.getContentPane().setCursor(blankCursor);
        run();
    }

    class GamePanel extends JPanel
    {
        private static final long serialVersionUID = 1L;
        public void paintComponent(Graphics g) { game.draw(g); }
    }

    private void run()
    {
        while (true)
        {
            game.update();
            try { Thread.sleep(1000/MyGame.framerate); }
            catch (Exception e) { e.printStackTrace(); }
            frame.repaint();
        }
    }
}
