/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.garrisonhq.ghqlib.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author gusjg
 */
public class AnimationHolder extends ArrayList<Animation>
{
    public AnimationHolder()
    {
        super();
    }
    
    public AnimationHolder(int capacity)
    {
        super(capacity);
    }
    
    public AnimationHolder(Collection<Animation> animations)
    {
        super(animations);
    }
    
    public static AnimationHolder fromSpriteSheet(InputStream stream)
    {
        
        AnimationHolder holder = new AnimationHolder();
        
        return holder;
    }
}
