package com.tj.tank.abstractfactory;

import com.tj.tank.Dir;
import com.tj.tank.Group;
import com.tj.tank.TankFrame;

public class RectFactory extends GameFactory {

    private RectFactory(){}

    private static RectFactory instance = new RectFactory();

    public static RectFactory getInstance(){
        return instance;
    }

    @Override
    public BaseTank createTank(int x, int y, Dir dir, TankFrame tf, Group group) {
        return null;
    }

    @Override
    public RectExplode createExplode(int x, int y, TankFrame tf) {
        return new RectExplode(x,y,tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        return null;
    }
}
