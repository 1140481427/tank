package com.tj.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class T {

    public static void main(String[] args) throws InterruptedException {
        TankFrame t = new TankFrame();



        //new Thread(() -> new Audio("audio/war1.wav").loop()).start();
        while (true) {
            Thread.sleep(50);
            t.repaint();
;
        }
    }
}
