/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.garrisonhq.ghqlib.example;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import net.garrisonhq.ghqlib.Game;
import net.garrisonhq.ghqlib.engine.Entity;
import net.garrisonhq.ghqlib.engine.HitboxHandler;
import net.garrisonhq.ghqlib.engine.Match;

/**
 *
 * @author gusjg
 */
public class ExampleEntity extends Entity
{
    private final double WIDTH;
    private final double HEIGHT;
    private final Color color;
    
    public ExampleEntity(double x, double y)
    {
        this(new Rectangle2D.Double(x, y, 30, 20), 0, 0, Color.WHITE);
    }
    
    public ExampleEntity(Rectangle2D.Double rect, double xVel, double yVel, Color color)
    {
        super(rect.x, rect.y, xVel, yVel);
        WIDTH = rect.width;
        HEIGHT = rect.height;
        this.color = color;
        
        Match match = (Match)Game.INSTANCE.handler;
        match.hitboxHandler.createHitbox(HitboxHandler.SOLID, WIDTH, HEIGHT, 0, 0, this);
        match.hitboxHandler.createHitbox(HitboxHandler.COLLISION, WIDTH, HEIGHT, 0, 0, this);
    }

    @Override
    public void render(Graphics g) 
    {
        g.setColor(color);
        g.fillRect((int)x, (int)y, (int)WIDTH, (int)HEIGHT);
    }
    
}
