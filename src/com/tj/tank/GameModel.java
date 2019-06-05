package com.tj.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    public GameModel(){
        int initTankCount = Integer.parseInt((String)PropertyMgr.getInstance().get("initTankCount"));
        for (int i=0; i<initTankCount; i++){
            dfTank.add(new Tank(50+i*80,110,Dir.DOWN,this,Group.BAD));
        }
    }

    Tank myTank = new Tank(200,200,Dir.DOWN,this,Group.GOOD);
    List<Bullet> bullets = new ArrayList<Bullet>();
    List<Tank> dfTank = new ArrayList<Tank>();
    List<Explode> exploads = new ArrayList<>();

    public Tank getMyTank() {
        return myTank;
    }

    public void setMyTank(Tank myTank) {
        this.myTank = myTank;
    }

    public void paint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("敌方坦克的数量:" + dfTank.size(), 10, 40);
        g.drawString("子弹的数量:" + bullets.size(), 10, 60);
        g.setColor(c);

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

    
}
