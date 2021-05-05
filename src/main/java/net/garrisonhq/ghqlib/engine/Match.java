/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.garrisonhq.ghqlib.engine;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author gusjg
 */
public class Match 
{
    /**
     * The list of entities that gets ticked every frame.
     * Operations I need to do on entities:
     * 
     *             ArrayList | LinkedList 
     * Add(e)      | O(N)    | O(1)    | name: add
     * Delete(e)   | O(N)    | O(N)    | name: remove
     * Add(e[])    | O(N)    | O(N(e)) | name: addAll
     * Delete(e[]) | O(N)    | O(N)    | name: removeAll
     */
    private LinkedList<Entity> entities;
    
    public void tick()
    {
        
    }
    
    public void render(Graphics g)
    {
        
    }
}
