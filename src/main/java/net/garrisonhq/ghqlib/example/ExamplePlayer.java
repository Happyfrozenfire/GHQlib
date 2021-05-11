/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.garrisonhq.ghqlib.example;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.net.URL;
import net.garrisonhq.ghqlib.controls.ControlsObject;
import net.garrisonhq.ghqlib.engine.Entity;
import net.garrisonhq.ghqlib.engine.HitboxHandler;
import net.garrisonhq.ghqlib.engine.Match;
import net.garrisonhq.ghqlib.util.Animation;
import org.stackoverflow.alexorzechowski.GifReader;
import org.stackoverflow.alexorzechowski.ImageFrame;

/**
 *
 * @author gusjg
 */
public class ExamplePlayer extends Entity
{
    protected ControlsObject controls;
    protected Animation frames;
    protected int frameTimer = 0;
    
    public ExamplePlayer(double x, double y, ControlsObject controls)
    {
        super(x, y);
        this.controls = controls;
        ((Match)ExampleMain.INSTANCE.handler).hitboxHandler.createHitbox(HitboxHandler.COLLISION, 20, 20, 0, 0, this);
        URL url = ExamplePlayer.class.getResource("images/peanut-butter-jelly-time.gif");
        try
        {
            frames = Animation.fromGif(url.openStream());
        }
        catch(IOException e)
        {
            System.out.println("Could not find URL: " + url);
            e.printStackTrace();
        }
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
        
        frameTimer = (frameTimer + 1) % frames.size();
        super.tick();
    }
    
    @Override
    public void render(Graphics g) 
    {
        if(frames == null)
        {
            g.setColor(Color.WHITE);
            g.fillRect((int)x, (int)y, 20, 20);
        }
        else
        {
            g.drawImage(frames.get(frameTimer).getImage(), (int)x, (int)y, null);
        }
    }
    
}
