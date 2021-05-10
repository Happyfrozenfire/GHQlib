/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.garrisonhq.ghqlib.example;

import net.garrisonhq.ghqlib.controls.ControlsObject;

/**
 *
 * @author gusjg
 */
public class ExampleControls extends ControlsObject.Byte
{
    /**
     * Up.
     */
    public static final byte UP = 0b00000001;
    
    /**
     * Down.
     */
    public static final byte DOWN = 0b00000010;
    
    /**
     * Left.
     */
    public static final byte LEFT = 0b00000100;
    
    /**
     * Right.
     */
    public static final byte RIGHT = 0b00001000;
    
    public ExampleControls()
    {
        super();
    }

    public ExampleControls(byte controls, boolean pressed)
    {
        super(controls, pressed);
    }

    public ExampleControls(byte pressedControls, byte heldControls)
    {
        super(pressedControls, heldControls);
    }
}
