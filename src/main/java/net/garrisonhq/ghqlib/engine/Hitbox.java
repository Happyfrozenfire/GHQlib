package net.garrisonhq.ghqlib.engine;

import java.awt.geom.Rectangle2D;
import java.util.*;

public class Hitbox extends Rectangle2D.Double
{
    /**
     * The x offset relative to the parent entity.
     */
    public double xRel;
    
    /**
     * The y offset relative to the parent entity.
     */
    public double yRel;
    
    /**
     * The parent entity.
     */
    public Entity owner;
    
    /**
     * A list of properties of the hitbox. Use case examples include invincibility
     * for hurtboxes, damage for attackboxes, etc.
     */
    public HashMap<String, Object> properties;
  
    /**
     * Constructs a new hitbox.
     * 
     * @param width
     * @param height
     * @param xRel
     * @param yRel
     * @param owner 
     */
    public Hitbox(double width, double height, double xRel, double yRel, Entity owner)
    {
        this(width, height, xRel, yRel, owner, new HashMap());
    }
  
    /**
     * Constructs a new hitbox with properties.
     * 
     * @param width
     * @param height
     * @param xRel
     * @param yRel
     * @param owner
     * @param properties 
     */
    public Hitbox(double width, double height, double xRel, double yRel, Entity owner, HashMap<String, Object> properties)
    {
        super(0, 0, width, height);
        this.xRel = xRel;
        this.yRel = yRel;
        this.owner = owner;
        this.properties = properties;
        updateLoc();
    }
  
    /**
     * Updates the hitbox's location relative to its parent entity.
     */
    public void updateLoc()
    {
        x = owner.x + xRel;
        y = owner.y + yRel;
    }
  
    /**
     * Setter for {@link #xRel}.
     * 
     * @param xRel 
     */
    public void setXRel(double xRel) { this.xRel = xRel; updateLoc(); }
    
    /**
     * Setter for {@link #yRel}.
     * 
     * @param yRel 
     */
    public void setYRel(double yRel) { this.yRel = yRel; updateLoc(); }
    
    /**
     * Setter for width.
     * 
     * @param width 
     */
    public void setWidth(double width) { this.width = width; updateLoc(); }
    
    /**
     * Setter for height.
     * 
     * @param height 
     */
    public void setHeight(double height) { this.height = height; updateLoc(); }
}