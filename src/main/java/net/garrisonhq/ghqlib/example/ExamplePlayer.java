/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.garrisonhq.ghqlib.example;

import java.awt.Color;
import java.awt.Graphics;
import net.garrisonhq.ghqlib.controls.ControlsObject;
import net.garrisonhq.ghqlib.engine.Entity;
import net.garrisonhq.ghqlib.engine.HitboxHandler;
import net.garrisonhq.ghqlib.engine.Match;

/**
 *
 * @author gusjg
 */
public class ExamplePlayer extends Entity
{
    protected ControlsObject controls;
    
    public ExamplePlayer(double x, double y, ControlsObject controls)
    {
        super(x, y);
        this.controls = controls;
        ((Match)ExampleMain.INSTANCE.handler).hitboxHandler.createHitbox(HitboxHandler.COLLISION, 20, 20, 0, 0, this);
    }
    
    @Override
    public void tick()
    {
        //vertical movement
        if(controls.isHeld(ExampleControls.UP) && !controls.isHeld(ExampleControls.DOWN))
        {
            yVel = -2;
        }
        else if(controls.isHeld(ExampleControls.DOWN) && !controls.isHeld(ExampleControls.UP))
        {
            yVel = 2;
        }
        else if(!(controls.isHeld(ExampleControls.DOWN) || controls.isHeld(ExampleControls.UP)))
        {
            yVel = 0;
        }
        
        //horizontal movement
        if(controls.isHeld(ExampleControls.LEFT) && !controls.isHeld(ExampleControls.RIGHT))
        {
            xVel = -2;
        }
        else if(controls.isHeld(ExampleControls.RIGHT) && !controls.isHeld(ExampleControls.LEFT))
        {
            xVel = 2;
        }
        else if(!(controls.isHeld(ExampleControls.RIGHT) || controls.isHeld(ExampleControls.LEFT)))
        {
            xVel = 0;
        }
        
        super.tick();
    }
    
    @Override
    public void render(Graphics g) 
    {
        g.setColor(Color.WHITE);
        g.fillRect((int)x, (int)y, 20, 20);
    }
    
}
