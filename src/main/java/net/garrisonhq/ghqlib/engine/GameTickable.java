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
    public abstract void tick();
    public abstract void render(Graphics g);
}
