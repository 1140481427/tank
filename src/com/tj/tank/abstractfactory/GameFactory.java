package com.tj.tank.abstractfactory;

import com.tj.tank.Dir;
import com.tj.tank.Group;
import com.tj.tank.TankFrame;

public abstract class GameFactory {

    public abstract BaseTank createTank(int x, int y, Dir dir, TankFrame tf,Group group);

    public abstract BaseExplode createExplode(int x, int y, TankFrame tf);

    public abstract BaseBullet createBullet(int x, int y, Dir dir, TankFrame tf, Group group);
}
