package com.tj.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class T {

    public static void main(String[] args) throws InterruptedException {
        TankFrame t = new TankFrame();

        int initTankCount = Integer.parseInt((String)PropertyMgr.getInstance().get("initTankCount"));
        for (int i=0; i<initTankCount; i++){
            t.dfTank.add(new Tank(50+i*80,110,Dir.DOWN,t,Group.BAD));
        }

        //new Thread(() -> new Audio("audio/war1.wav").loop()).start();
        while (true) {
            Thread.sleep(50);
            t.repaint();
;
        }
    }
}
