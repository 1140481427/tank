package com.tj.tank;

import com.tj.tank.abstractfactory.BaseExplode;
import com.tj.tank.abstractfactory.DefaultFactory;
import com.tj.tank.abstractfactory.GameFactory;
import com.tj.tank.abstractfactory.RectFactory;

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


    Tank myTank = new Tank(200,200,Dir.DOWN,this,Group.GOOD);

    List<Bullet> bullets = new ArrayList<Bullet>();

    List<Tank> dfTank = new ArrayList<Tank>();
    //Bullet b = new Bullet(200,299,Dir.DOWN,this);
    public List<BaseExplode> exploads = new ArrayList<>();
   // Explode e = new Explode(100,100,this);

    GameFactory gm = RectFactory.getInstance();

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
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("敌方坦克的数量:" + dfTank.size(), 10, 40);
        g.drawString("子弹的数量:" + bullets.size(), 10, 60);
        g.setColor(c);

        //e.paint(g);
        myTank.paint(g);

        for (int i=0; i<dfTank.size(); i++){
            g.setColor(Color.blue);
            dfTank.get(i).paint(g);
            g.setColor(c);

        }

        for (int i=0; i<bullets.size(); i++){
            bullets.get(i).paint(g);
        }

        for (int i=0; i<exploads.size(); i++){
            exploads.get(i).paint(g);
        }

        for (int i=0; i<bullets.size(); i++){
            for(int j=0; j<dfTank.size(); j++)
                bullets.get(i).collideWith(dfTank.get(j));
        }
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
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }
        public void setMainTankDir(){
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
