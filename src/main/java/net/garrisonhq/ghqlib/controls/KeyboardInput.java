/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.garrisonhq.ghqlib.controls;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 *
 * @author gusjg
 */
public class KeyboardInput extends KeyAdapter
{
    /**
     * The ControlsObject instance.
     */
    protected ControlsObject controls;
    
    /**
     * The map of VKs corresponding to control inputs.
     */
    protected HashMap<Integer, Number> inputMap;
    
    public KeyboardInput(ControlsObject controls)
    {
        this.controls = controls;
        inputMap = new HashMap();
    }
    
    @Override
    public void keyPressed(KeyEvent e)
    {
        if(inputMap.containsKey(e.getKeyCode()))
        {
            controls.press(inputMap.get(e.getKeyCode()));
        }
     }
  
    @Override
    public void keyReleased(KeyEvent e)
    {
        if(inputMap.containsKey(e.getKeyCode()))
        {
            controls.release(inputMap.get(e.getKeyCode()));
        }
    }
    
    /**
     * @return {@link #inputMap}
     */
    public HashMap<Integer, Number> getInputMap()
    {
        return inputMap;
    }
    
    /**
     * @return {@link #controls}
     */
    public ControlsObject getControls()
    {
        return controls;
    }
}
