package com.tj.tank;

import java.awt.*;

/**
 * 子弹
 */
public class Bullet {

    private int x = 200;
    private int y = 200;
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    private static final int SPEED = Integer.parseInt((String)PropertyMgr.getInstance().get("bulletSpeed"));
    private Dir dir = null;
    private boolean isLive = true;
    private Group group = Group.BAD;
    private GameModel gm = null;
    Rectangle ret = new Rectangle();
    public Group getGroup() {
        return group;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Bullet(int x, int y, Dir dir, GameModel gm, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gm = gm;
        this.group = group;

        ret.x = x;
        ret.y = y;
        ret.width = WIDTH;
        ret.height = HEIGHT;
        gm.bullets.add(this);
    }

    public void paint(Graphics g) {


        if(!isLive){
            gm.bullets.remove(this);
        } else {
            switch(dir) {
                case LEFT:
                    g.drawImage(ResourceMgr.bulletL, x, y, null);
                    break;
                case UP:
                    g.drawImage(ResourceMgr.bulletU, x, y, null);
                    break;
                case RIGHT:
                    g.drawImage(ResourceMgr.bulletR, x, y, null);
                    break;
                case DOWN:
                    g.drawImage(ResourceMgr.bulletD, x, y, null);
                    break;
            }
            move();
        }
    }

    public void move(){
        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        ret.x = x;
        ret.y = y;
        
        if( x < 0 || x > TankFrame.GAME_WIDTH || y < 0 || y > TankFrame.GAME_HEIGHT) isLive = false;
    }

    public void collideWith(Tank tank) {

        if (tank.group == this.group) return;

        /*Rectangle rec = new Rectangle(x,y,WIDTH,HEIGHT);*/
        /*Rectangle rec2 = new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);*/

        if (ret.intersects(tank.ret)) {
            tank.die();
            this.die();
        }

    }

    private void die() {
        this.isLive = false;
    }
}
