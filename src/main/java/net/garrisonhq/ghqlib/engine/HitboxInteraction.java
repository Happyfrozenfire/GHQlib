package net.garrisonhq.ghqlib.engine;

public interface HitboxInteraction
{
    public abstract void activate(Hitbox box1, Hitbox box2);
    public abstract boolean condition(Hitbox box1, Hitbox box2);
    public abstract void postProcess();
    
    default double getDiffX(Hitbox box1, Hitbox box2)
    {
        double l1 = box1.x;
        double m1 = box1.x + box1.width / 2;
        double r1 = box1.x + box1.width;
        
        double l2 = box2.x;
        double m2 = box2.x + box2.width / 2;
        double r2 = box2.x + box2.width;
        
        double diffx = (m2 < m1) ? -(r2 - l1) : (r1 - l2);
        return diffx;
    }
    
    default double getDiffY(Hitbox box1, Hitbox box2)
    {
        double l1 = box1.y;
        double m1 = box1.y + box1.height / 2;
        double r1 = box1.y + box1.height;
        
        double l2 = box2.y;
        double m2 = box2.y + box2.height / 2;
        double r2 = box2.y + box2.height;
        
        double diffy = (m2 < m1) ? -(r2 - l1) : (r1 - l2);
        return diffy;
    }
}