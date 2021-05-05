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
public abstract class Entity 
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
    
    public void tick()
    {
        x += xVel;
        y += yVel;
    }
    
    public abstract void render(Graphics g);
}
