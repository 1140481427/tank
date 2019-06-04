package com.tj.tank;

import com.tj.tank.abstractfactory.BaseExplode;

import java.awt.*;

/**
 * 子弹
 */
public class Explode extends BaseExplode {

    private int x ,y ;
    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private boolean living = true;
    private TankFrame tf = null;
    private int step = 0;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

     //  new Audio("audio/explode.wav").loop();
    }

    @Override
    public void paint(Graphics g) {

        g.drawImage(ResourceMgr.explodes[step++],x,y,null);

        if(step >= ResourceMgr.explodes.length){
            tf.exploads.remove(this);
            step = 0;
        }
    }




}
