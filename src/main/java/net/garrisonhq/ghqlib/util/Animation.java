/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.garrisonhq.ghqlib.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import org.stackoverflow.alexorzechowski.GifReader;
import org.stackoverflow.alexorzechowski.ImageFrame;

/**
 * O(N^2) load time with marginally higher memory usage for O(1) retrieval time.
 * Note that a fast retrieval time makes deserializing a state faster and 
 * rollback more feasible.
 * @author gusjg
 */
public class Animation 
{
    private final ImageFrame[] frameArr;
    
    public Animation(Collection<ImageFrame> frames)
    {
        ArrayList<ImageFrame> frameList = new ArrayList();
        for(ImageFrame frame : frames)
        {
            if(frame.getDelay() <= 1)
            {
                frameList.add(frame);
            }
            else
            {
                for(int i = 0; i < frame.getDelay(); i++)
                {
                    frameList.add(frame);
                }
            }
        }
        
        this.frameArr = frameList.toArray(new ImageFrame[frameList.size()]);
    }
    
    public Animation(ImageFrame[] frames)
    {
        ArrayList<ImageFrame> frameList = new ArrayList();
        for(ImageFrame frame : frames)
        {
            int delay = (int)(frame.getDelay() * 0.6);
            if(delay <= 1)
            {
                frameList.add(frame);
            }
            else
            {
                for(int i = 0; i < delay; i++)
                {
                    frameList.add(frame);
                }
            }
        }
        
        this.frameArr = frameList.toArray(new ImageFrame[frameList.size()]);
    }
    
    public ImageFrame get(int i)
    {
        return frameArr[i];
    }
    
    public int size()
    {
        return frameArr.length;
    }
    
    public static Animation fromGif(InputStream stream) throws IOException
    {
        return new Animation(GifReader.readGif(stream));
    }
}
