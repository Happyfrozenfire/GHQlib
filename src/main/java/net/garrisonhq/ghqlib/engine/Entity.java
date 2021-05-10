/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.garrisonhq.ghqlib.engine;

/**
 * The end programmer is advised to override this class with an abstract
 * subclass for all of their Entity instances. This is so that they can override
 * {@link #collision(java.lang.String, java.lang.Object[])}
 * 
 * @author gusjg
 */
public abstract class Entity implements GameTickable
{
    /**
     * The x position of the Entity in double precision. Floats are bad.
     */
    protected double x;
    
    /**
     * The y position of the Entity in double precision. Floats are bad.
     */
    protected double y;
    
    /**
     * The x velocity of the Entity in double precision. Note that it's velocity,
     * not speed. This means it has a direction (+/-). Speed can be derived with
     * Math.abs(xVel).
     */
    protected double xVel;
    
    /**
     * The y velocity of the Entity in double precision. Note that it's velocity,
     * not speed. This means it has a direction (+/-). Speed can be derived with
     * Math.abs(yVel).
     */
    protected double yVel;
    
    /**
     * Constructs a new Entity instance at given the location.
     * 
     * @param x
     * @param y 
     */
    protected Entity(double x, double y)
    {
        this(x, y, 0, 0);
    }
    
    /**
     * Constructs a new Entity instance at the given location with the given 
     * velocities.
     * 
     * @param x
     * @param y
     * @param xVel
     * @param yVel 
     */
    protected Entity(double x, double y, double xVel, double yVel)
    {
        this.x = x;
        this.y = y;
        this.xVel = xVel;
        this.yVel = yVel;
    }
    
    /**
     * Adjusts x/y position according to the current x/y velocities. When
     * overriding this method, super.tick() should be called last.
     */
    @Override
    public void tick()
    {
        x += xVel;
        y += yVel;
    }
    
    /**
     * Is called by an {@link net.garrisonhq.ghqlib.engine.HitboxInteraction} when 
     * interaction conditions are met. Should be overridden if a new 
     * {@link Hitbox} type is introduced.
     * 
     * @param type
     * @param args 
     */
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
    
    /**
     * Is called in the case of a Solid-Collision hitbox interaction. Pushes the 
     * entity away from the other Hitbox instance via the path of least 
     * resistance.
     * 
     * @param diffx
     * @param diffy
     * @param solid 
     */
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
