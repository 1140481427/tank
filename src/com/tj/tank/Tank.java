package com.tj.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x , y;
    private static final int SPEED = Integer.parseInt((String)PropertyMgr.getInstance().get("bulletSpeed"));
    private Dir dir = Dir.DOWN;
    private boolean moving = true;
    private TankFrame tf = null;

    Rectangle ret = new Rectangle();
    public static int WIDTH = ResourceMgr.goodTankD.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankD.getHeight();

    public boolean isLiving = true;
    public Random random = new Random();
    private Group group = Group.BAD;
    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    public Tank(int x, int y, Dir dir, TankFrame tf,Group group){
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        ret.x = x;
        ret.y = y;
        ret.width = WIDTH;
        ret.height = HEIGHT;
    }
    public void paint(Graphics g) {

        if(!this.isLiving) {
            tf.dfTank.remove(this);
        } else {
                switch(dir) {
                    case LEFT:
                        g.drawImage(group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                        break;
                    case UP:
                        g.drawImage(group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                        break;
                    case RIGHT:
                        g.drawImage(group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                        break;
                    case DOWN:
                        g.drawImage(group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                        break;
                }

            }



            move();
        }



    public void move(){

        //到达边界停止
        /*if(x<0 || x>800 || y<0 || y> 600){
            return;
        }*/

        if(moving){
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
        }



        if(random.nextInt(10) >8 && this.group == Group.BAD) this.fire();
        if(this.group == Group.BAD && random.nextInt(10) > 8)
            randomDir();

        boundsCheck();

        ret.x = x;
        ret.y = y;
    }

    private void boundsCheck() {
        if(x<0) x=0;
        else if(y<30) y=30;
        else if(x>TankFrame.GAME_WIDTH-WIDTH) x=TankFrame.GAME_WIDTH-WIDTH;
        else if(y>TankFrame.GAME_HEIGHT-HEIGHT) y=TankFrame.GAME_HEIGHT-HEIGHT;

    }

    private void randomDir(){

        this.dir = Dir.values()[random.nextInt(4)];
    }

    /**
     * 坦克开火方法
     */
    public void fire() {
        int bX = this.x + WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + HEIGHT/2 - Bullet.HEIGHT/2;
        tf.bullets.add(new Bullet(bX,bY,dir,this.tf,this.group));
    }

    public void die() {
        int eX = this.x + WIDTH/2 - Explode.WIDTH/2;
        int eY = this.y + HEIGHT/2 - Explode.HEIGHT/2;
        tf.exploads.add(new Explode(eX,eY,tf));
        this.isLiving = false;
    }
}
