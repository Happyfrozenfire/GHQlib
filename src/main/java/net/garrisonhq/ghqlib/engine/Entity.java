/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.garrisonhq.ghqlib.engine;

import java.awt.Graphics;

/**
 *
 * @author gusjg
 */
public abstract class Entity implements GameTickable
{
    protected double x;
    protected double y;
    protected double xVel;
    protected double yVel;
    
    protected Entity(double x, double y)
    {
        this.x = x;
        this.y = y;
        this.xVel = 0;
        this.yVel = 0;
    }
    
    protected Entity(double x, double y, double xVel, double yVel)
    {
        this.x = x;
        this.y = y;
        this.xVel = xVel;
        this.yVel = yVel;
    }
    
    @Override
    public void tick()
    {
        x += xVel;
        y += yVel;
    }
    
    public void collision(String type, Object[] args)
    {
        switch(type)
        {
            case "SolidCollision":
                double diffx = (double)args[0];
                double diffy = (double)args[1];
                Hitbox solid = (Hitbox)args[2];
                solidCollision(diffx, diffy, solid);
                break;
        }
    }
    
    protected void solidCollision(double diffx, double diffy, Hitbox solid)
    {
        if(Math.abs(diffx) > Math.abs(diffy))
        {
            y += diffy;
            yVel = 0;
        }
        else
        {
            x += diffx;
            xVel = 0;
        }
        System.out.println("h");
    }
}
