package com.tj.tank.abstractfactory;

import com.tj.tank.ResourceMgr;
import com.tj.tank.TankFrame;

import java.awt.*;

/**
 * 子弹
 */
public class RectExplode extends BaseExplode {

    private int x ,y ;
    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private boolean living = true;
    private TankFrame tf = null;
    private int step = 0;

    public RectExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

     //  new Audio("audio/explode.wav").loop();
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.red);

        g.drawRect(x,y,10*step,10*step);
        step++;
        //g.drawImage(ResourceMgr.explodes[step++],x,y,null);

        if(step >= 15){
            tf.exploads.remove(this);
            step = 0;
        }
        g.setColor(c);
    }




}
