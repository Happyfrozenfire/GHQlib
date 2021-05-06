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
public class Match implements GameTickable
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
    private LinkedList<Entity> entities = new LinkedList();
    
    public HitboxHandler hitboxHandler = new HitboxHandler();
    
    public void tick()
    {
        for(Entity entity : entities)
        {
            entity.tick();
        }
        
        hitboxHandler.tick();
    }
    
    public void render(Graphics g)
    {
        for(Entity entity : entities)
        {
            entity.render(g);
        }
        
        hitboxHandler.render(g);
    }
    
    public Entity addEntity(Entity entity)
    {
        if(entities.add(entity))
        {
            return entity;
        }
        return null;
    }
}
