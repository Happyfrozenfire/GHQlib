/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.garrisonhq.ghqlib.engine.hitbox;

import net.garrisonhq.ghqlib.engine.*;

/**
 *
 * @author gusjg
 */
public class SolidCollision implements HitboxInteraction
{
    public String name = "SolidCollision";
    
    @Override
    public void activate(Hitbox solid, Hitbox collision)
    {
        double diffx = getDiffX(solid, collision);
        double diffy = getDiffY(solid, collision);
        collision.owner.collision(name, new Object[] { diffx, diffy, solid });
    }

    @Override
    public boolean condition(Hitbox solid, Hitbox collision)
    {
        return solid.intersects(collision) && solid.owner != collision.owner;
    }

    @Override
    public void postProcess() {  }
}