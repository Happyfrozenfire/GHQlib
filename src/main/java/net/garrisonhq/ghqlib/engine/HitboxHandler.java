/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.garrisonhq.ghqlib.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import net.garrisonhq.ghqlib.engine.hitbox.*;

/**
 *
 * @author gusjg
 */
public class HitboxHandler implements GameTickable
{
    public static final int SOLID = 0;
    public static final int COLLISION = 1;
    public static final int HURT = 2;
    public static final int ATTACK = 3;
    
    public boolean showHitboxes = true;
    
    public ArrayList<LinkedList<Hitbox>> hitboxlist;
    
    
    
    public HitboxHandler()
    {
        hitboxlist = new ArrayList();
        hitboxlist.add(SOLID, new LinkedList());
        hitboxlist.add(COLLISION, new LinkedList());
        hitboxlist.add(HURT, new LinkedList());
        hitboxlist.add(ATTACK, new LinkedList());
    }

    @Override
    public void tick() 
    {
        for(LinkedList<Hitbox> category : hitboxlist)
        {
            for(Hitbox hitbox : category)
            {
                hitbox.updateLoc();
            }
        }
        
        checkDoInteract(SOLID, COLLISION, new SolidCollision());
        
    }

    @Override
    public void render(Graphics g) 
    {
        if(showHitboxes)
        {
            for(int i = 0; i < hitboxlist.size(); i++)
            {
                LinkedList<Hitbox> category = hitboxlist.get(i);
                switch(i)
                {
                    case SOLID:
                        g.setColor(Color.WHITE);
                        break;
                    case COLLISION:
                        g.setColor(Color.GRAY);
                        break;
                    case HURT:
                        g.setColor(Color.YELLOW);
                        break;
                    case ATTACK:
                        g.setColor(Color.RED);
                        break;
                }
                
                for(Hitbox hitbox : category)
                {
                    g.drawRect((int)hitbox.x, (int)hitbox.y, (int)hitbox.width, (int)hitbox.height);
                }
            }
        }
    }
    
    protected void checkDoInteract(int type1, int type2, HitboxInteraction interact)
    {
        LinkedList<Hitbox> group1 = hitboxlist.get(type1);
        LinkedList<Hitbox> group2 = hitboxlist.get(type2);
        for(Hitbox h1 : group1)
        {
            for(Hitbox h2 : group2)
            {
                if(h1 != null && h2 != null && interact.condition(h1, h2))
                {
                    interact.activate(h1, h2);
                }
            }
        }
        interact.postProcess();
    }
    
    public Hitbox createHitbox(int type, double width, double height, double xRel, double yRel, Entity owner)
    {
      Hitbox box = new Hitbox(width, height, xRel, yRel, owner);
      hitboxlist.get(type).add(box);
      //System.out.println(hitboxlist.get(type).size());
      return box;
    }
}
