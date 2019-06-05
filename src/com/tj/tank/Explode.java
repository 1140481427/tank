package com.tj.tank;

import java.awt.*;

/**
 * 子弹
 */
public class Explode {

    private int x ,y ;
    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private boolean living = true;
    private GameModel gm = null;
    private int step = 0;

    public Explode(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;

     //  new Audio("audio/explode.wav").loop();
    }

    public void paint(Graphics g) {

        g.drawImage(ResourceMgr.explodes[step++],x,y,null);

        if(step >= ResourceMgr.explodes.length){
            gm.exploads.remove(this);
            step = 0;
        }
    }




}
