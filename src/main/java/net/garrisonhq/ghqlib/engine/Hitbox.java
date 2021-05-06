package net.garrisonhq.ghqlib.engine;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.*;

public class Hitbox extends Rectangle2D.Double
{
    public double xRel;
    public double yRel;
    public Entity owner;
    public HashMap<String, Object> properties;
  
  
    public Hitbox(double width, double height, double xRel, double yRel, Entity owner)
    {
        this(width, height, xRel, yRel, owner, null);
    }
  
    public Hitbox(double width, double height, double xRel, double yRel, Entity owner, HashMap<String, Object> properties)
    {
        super(0, 0, width, height);
        this.xRel = xRel;
        this.yRel = yRel;
        this.owner = owner;
        this.properties = properties;
        updateLoc();
    }
  
  
    public void updateLoc()
    {
        x = owner.x + xRel;
        y = owner.y + yRel;
    }
  
    public void setXRel(double xRel) { this.xRel = xRel; updateLoc(); }
    public void setYRel(double yRel) { this.yRel = yRel; updateLoc(); }
    public void setWidth(double width) { this.width = width; updateLoc(); }
    public void setHeight(double height) { this.height = height; updateLoc(); }
}