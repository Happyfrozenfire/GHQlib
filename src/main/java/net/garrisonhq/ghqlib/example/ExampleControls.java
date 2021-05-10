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
    
    @Override
    public boolean[][] toBoolArr()
    {
        boolean[][] arr = new boolean[2][4];
        byte pressed = getPressedControls().byteValue();
        byte held = getHeldControls().byteValue();
        for(int i = 0; i < 4; i++)
        {
            arr[0][i] = (0b1 << i) == (pressed & (0b1 << i));
            arr[1][i] = (0b1 << i) == (held & (0b1 << i));
        }

        return arr;
    }
}
