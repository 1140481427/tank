package com.tj.tank;

/**
 * 主站坦克策略1   四方向炮弹
 */
public class DefaultFireStrategy implements FireStrategy{

    private static final DefaultFireStrategy instance = new DefaultFireStrategy();

    private DefaultFireStrategy(){}

    public static DefaultFireStrategy getInstance(){
        return instance;
    }


    @Override
    public void fire(Tank t) {

        int bX = t.x + t.WIDTH/2 - Bullet.WIDTH/2;
        int bY = t.y + t.HEIGHT/2 - Bullet.HEIGHT/2;


        new Bullet(bX,bY,t.dir,t.gm,t.group);
    }
}
