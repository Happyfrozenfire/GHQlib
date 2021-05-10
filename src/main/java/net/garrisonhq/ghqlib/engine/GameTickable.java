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
public interface GameTickable 
{
    /**
     * Runs each frame to handle game logic.
     */
    public abstract void tick();
    
    /**
     * Runs each frame to handle rendering. May be skipped. Game logic should
     * never be put here.
     * @param g The {@link java.awt.Graphics} instance to draw on.
     */
    public abstract void render(Graphics g);
}
