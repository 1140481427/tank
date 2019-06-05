package com.tj.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    public static final int GAME_WIDTH = Integer.parseInt((String)PropertyMgr.getInstance().get("gameWidth"));
    public static final int GAME_HEIGHT = Integer.parseInt((String)PropertyMgr.getInstance().get("gameHeight"));

    GameModel gm = new GameModel();



    public TankFrame(){
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);//大小固定
        setTitle("tank war");
        setVisible(true);
        addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        gm.paint(g);
    }

    class MyKeyListener extends KeyAdapter{
        boolean bL = false;
        boolean bR = false;
        boolean bD = false;
        boolean bU = false;
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    //四面设计策略
                    gm.getMyTank().fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }
        public void setMainTankDir(){
            Tank myTank = gm.getMyTank();
            if(!bL && !bU && !bR && !bD){
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);
                if(bL) myTank.dir = (Dir.LEFT);
                if(bR) myTank.dir = (Dir.RIGHT);
                if(bU) myTank.dir = (Dir.UP);
                if(bD) myTank.dir = (Dir.DOWN);
            }



        }
    }

}
