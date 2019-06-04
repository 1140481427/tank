package com.tj.tank.abstractfactory;

import com.tj.tank.*;

public class DefaultFactory extends GameFactory {

    private DefaultFactory(){}

    private static DefaultFactory instance = new DefaultFactory();

    public static DefaultFactory getInstance(){
        return instance;
    }

    @Override
    public BaseTank createTank(int x, int y, Dir dir, TankFrame tf, Group group) {
        return new Tank(x,y,dir,tf,group);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new Explode(x,y,tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        return new Bullet(x,y,dir,tf,group);
    }
}
