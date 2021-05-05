/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.garrisonhq.ghqlib.debug;

import java.awt.Color;
import java.awt.Graphics;
import net.garrisonhq.ghqlib.engine.Entity;

/**
 *
 * @author gusjg
 */
public class DebugEntity extends Entity
{
    private static final double WIDTH = 30;
    private static final double HEIGHT = 20;
    
    public DebugEntity(double x, double y)
    {
        super(x, y, 1, 2);
    }

    @Override
    public void render(Graphics g) 
    {
        int x = (int)(this.x - WIDTH / 2);
        int y = (int)(this.y - HEIGHT / 2);
        g.setColor(Color.GREEN);
        g.fillRect(x, y, (int)WIDTH, (int)HEIGHT);
    }
    
}
