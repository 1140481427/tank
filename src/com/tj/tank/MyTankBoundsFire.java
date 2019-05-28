package com.tj.tank;

import java.util.ArrayList;
import java.util.List;

/**
 * 主站坦克策略1   四方向炮弹
 */
public class MyTankBoundsFire implements FireStrategy{

    private static final MyTankBoundsFire instance = new MyTankBoundsFire();

    private MyTankBoundsFire(){}

    public static MyTankBoundsFire getInstance(){
        return instance;
    }


    @Override
    public void fire(Tank t) {

        int bX = t.x + t.WIDTH/2 - Bullet.WIDTH/2;
        int bY = t.y + t.HEIGHT/2 - Bullet.HEIGHT/2;

        Dir[] dirs = Dir.values();

        for (int i = 0; i <dirs.length ; i++) {
            new Bullet(bX,bY,dirs[i],t.tf,t.group);
        }
    }
}
