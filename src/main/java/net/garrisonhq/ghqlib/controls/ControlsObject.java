/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.garrisonhq.ghqlib.controls;

/**
 * This class stores three {@link java.lang.Number} instances representing the 
 * controls. They should be treated as binary arrays and operated on using
 * bitwise operators.
 * 
 * @author gusjg
 */
public abstract class ControlsObject 
{
    /**
     * Controls are added to this in realtime. Use of this variable in game 
     * logic renders said logic non-deterministic.
     */
    protected Number preControls;
    
    /**
     * Controls are added to this from {@link #preControls} at the start of each 
     * frame. Use of this variable in game keeps logic deterministic.
     */
    protected Number controls;
    
    /**
     * Controls are added to this from {@link #controls} at the start of each
     * frame, before {@link #controls} is updated.
     */
    protected Number prevControls;
    
    /**
     * Empty ControlsObject constructor.
     */
    protected ControlsObject() 
    { 
        preControls = 0b0;
        controls = 0b0;
        prevControls = 0b0;
    }
    
    /**
     * ControlsObject constructor that initializes controls and prevControls 
     * with a single set of pressed or held controls.
     * 
     * @param controls 
     * @param pressed If true, prevControls is set to 0b0. Else, it's set to 
     * controls.
     */
    protected ControlsObject(Number controls, boolean pressed)
    {
        this.controls = controls;
        this.prevControls = pressed ? 0b0 : controls;
        this.preControls = controls;
    }
    
    /**
     * Is called at the start of each frame.
     */
    public void update()
    {
        prevControls = controls;
        controls = preControls;
    }
    
    /**
     * Presses the given controls to preControls. Should be overridden with 
     * appropriate bitwise operations (ex: {@code preControls = preControls | 
     * controls;})
     * 
     * @param controls 
     */
    public abstract void press(Number controls);
    
    /**
     * Releases the given controls from preControls. Should be overridden with 
     * appropriate bitwise operations (ex: {@code preControls = preControls & 
     * ~controls;})
     * 
     * @param controls 
     */
    public abstract void release(Number controls);
    
    /**
     * Returns true if the given controls are pressed. Should be overridden with
     * appropriate bitwise operations (ex: {@code return controls == controls & 
     * getPressedControls();})
     * 
     * @param controls
     * @return true if the given controls are pressed.
     */
    public abstract boolean isPressed(Number controls);
    
    /**
     * Returns true if the given controls are held. Should be overridden with
     * appropriate bitwise operations (ex: {@code return controls == controls & 
     * getHeldControls();})
     * 
     * @param controls
     * @return 
     */
    public abstract boolean isHeld(Number controls);
    
    /**
     * Returns true if the given controls are released. Should be overridden with
     * appropriate bitwise operations (ex: {@code return controls == controls & 
     * getReleasedControls();})
     * 
     * @param controls
     * @return 
     */
    public abstract boolean isReleased(Number controls);
    
    /**
     * Returns the pressed controls. Should be overridden with appropriate
     * bitwise operations (ex: {@code return controls & ~prevControls;})
     * 
     * @return the pressed controls
     */
    public abstract Number getPressedControls();
    
    /**
     * Returns the held controls.
     * 
     * @return the held controls
     */
    public Number getHeldControls() 
    {
        return controls;
    }
    
    /**
     * Returns the released controls. Should be overridden with appropriate
     * bitwise operations (ex: {@code return ~controls & prevControls;})
     * 
     * @return the released controls
     */
    public abstract Number getReleasedControls();
    
    /**
     * Resets the controls instance.
     */
    public void reset()
    {
        preControls = 0b0;
        controls = 0b0;
        prevControls = 0b0;
    }
    
    /**
     * Presses the given controls to controls. Should be overridden with 
     * appropriate bitwise operations (ex: {@code controls = controls | 
     * controls;}). Should be used exclusively in game logic, not in receiving
     * inputs.
     * 
     * @param controls 
     */
    public abstract void forcePress(Number controls);
    
    /**
     * Releases the given controls from controls. Should be overridden with 
     * appropriate bitwise operations (ex: {@code controls = controls &
     * ~controls;}). Should be used exclusively in game logic, not in receiving
     * inputs.
     * 
     * @param controls 
     */
    public abstract void forceRelease(Number controls);
    
    /**
     * Sets controls and prevControls to their appropriate values given the 
     * pressed and held controls. Should be overridden with appropriate bitwise
     * operations.
     * 
     * @param pressedControls
     * @param heldControls 
     */
    public abstract void setControls(Number pressedControls, Number heldControls);
    
    /**
     * Returns a new 2D array of booleans. The first dimension is pressed/held
     * controls. The second dimension is the bit and should be overridden by
     * subclasses.
     * 
     * @return a new 2D array of pressed and held controls.
     */
    public boolean[][] toBoolArr()
    {
        return new boolean[2][0];
    }
    
    
    
    /**
     * An 8-bit representation of ControlsObject. Can be used for NES-style
     * controls.
     */
    public static class Byte extends ControlsObject
    {
        public Byte()
        {
            super();
        }
        
        public Byte(byte controls, boolean pressed)
        {
            super(controls, pressed);
        }
        
        public Byte(byte pressedControls, byte heldControls)
        {
            this();
            setControls(pressedControls, heldControls);
        }
        
        @Override
        public void press(Number controls) 
        {
            preControls = preControls.byteValue() | controls.byteValue();
        }

        @Override
        public void release(Number controls) 
        {
            preControls = preControls.byteValue() & ~controls.byteValue();
        }

        @Override
        public boolean isPressed(Number controls) 
        {
            return controls.byteValue() == (controls.byteValue() & getPressedControls().byteValue());
        }

        @Override
        public boolean isHeld(Number controls) 
        {
            return controls.byteValue() == (controls.byteValue() & getHeldControls().byteValue());
        }

        @Override
        public boolean isReleased(Number controls) 
        {
            return controls.byteValue() == (controls.byteValue() & getReleasedControls().byteValue());
        }

        @Override
        public Number getPressedControls() 
        {
            return controls.byteValue() & ~prevControls.byteValue();
        }
        
        @Override
        public Number getHeldControls()
        {
            return controls.byteValue();
        }

        @Override
        public Number getReleasedControls() 
        {
            return prevControls.byteValue() & ~controls.byteValue();
        }

        @Override
        public void forcePress(Number controls) 
        {
            this.controls = this.controls.byteValue() | controls.byteValue();
            preControls = this.controls;
        }

        @Override
        public void forceRelease(Number controls) 
        {
            this.controls = this.controls.byteValue() & ~controls.byteValue();
            preControls = this.controls;
        }

        @Override
        public void setControls(Number pressedControls, Number heldControls) 
        {
            controls = heldControls.byteValue() | pressedControls.byteValue();
            prevControls = controls.byteValue() & ~pressedControls.byteValue();
            preControls = controls;
        }
        
        @Override
        public boolean[][] toBoolArr()
        {
            boolean[][] arr = new boolean[2][8];
            byte pressed = getPressedControls().byteValue();
            byte held = getHeldControls().byteValue();
            for(int i = 0; i < 8; i++)
            {
                arr[0][i] = (0b1 << i) == (pressed & (0b1 << i));
                arr[1][i] = (0b1 << i) == (held & (0b1 << i));
            }
            
            return arr;
        }
    }
    
    /**
     * A 16-bit representation of ControlsObject.
     */
    public static class Short extends ControlsObject
    {
        public Short()
        {
            super();
        }
        
        public Short(short controls, boolean pressed)
        {
            super(controls, pressed);
        }
        
        public Short(short pressedControls, short heldControls)
        {
            this();
            setControls(pressedControls, heldControls);
        }
        
        @Override
        public void press(Number controls) 
        {
            preControls = preControls.shortValue() | controls.shortValue();
        }

        @Override
        public void release(Number controls) 
        {
            preControls = preControls.shortValue() & ~controls.shortValue();
        }

        @Override
        public boolean isPressed(Number controls) 
        {
            return controls.shortValue() == (controls.shortValue() & getPressedControls().shortValue());
        }

        @Override
        public boolean isHeld(Number controls) 
        {
            return controls.shortValue() == (controls.shortValue() & getHeldControls().shortValue());
        }

        @Override
        public boolean isReleased(Number controls) 
        {
            return controls.shortValue() == (controls.shortValue() & getReleasedControls().shortValue());
        }

        @Override
        public Number getPressedControls() 
        {
            return controls.shortValue() & ~prevControls.shortValue();
        }
        
        @Override
        public Number getHeldControls()
        {
            return controls.shortValue();
        }

        @Override
        public Number getReleasedControls() 
        {
            return prevControls.shortValue() & ~controls.shortValue();
        }

        @Override
        public void forcePress(Number controls) 
        {
            this.controls = this.controls.shortValue() | controls.shortValue();
            preControls = this.controls;
        }

        @Override
        public void forceRelease(Number controls) 
        {
            this.controls = this.controls.shortValue() & ~controls.shortValue();
            preControls = this.controls;
        }

        @Override
        public void setControls(Number pressedControls, Number heldControls) 
        {
            controls = heldControls.shortValue() | pressedControls.shortValue();
            prevControls = controls.shortValue() & ~pressedControls.shortValue();
            preControls = controls;
        }
        
        @Override
        public boolean[][] toBoolArr()
        {
            boolean[][] arr = new boolean[2][16];
            short pressed = getPressedControls().shortValue();
            short held = getHeldControls().shortValue();
            for(int i = 0; i < 16; i++)
            {
                arr[0][i] = (0b1 << i) == (pressed & (0b1 << i));
                arr[1][i] = (0b1 << i) == (held & (0b1 << i));
            }
            
            return arr;
        }
    }
    
    /**
     * A 32-bit representation of ControlsObject.
     */
    public static class Integer extends ControlsObject
    {
        public Integer()
        {
            super();
        }
        
        public Integer(int controls, boolean pressed)
        {
            super(controls, pressed);
        }
        
        public Integer(int pressedControls, int heldControls)
        {
            this();
            setControls(pressedControls, heldControls);
        }
        
        @Override
        public void press(Number controls) 
        {
            preControls = preControls.intValue() | controls.intValue();
        }

        @Override
        public void release(Number controls) 
        {
            preControls = preControls.intValue() & ~controls.intValue();
        }

        @Override
        public boolean isPressed(Number controls) 
        {
            return controls.intValue() == (controls.intValue() & getPressedControls().intValue());
        }

        @Override
        public boolean isHeld(Number controls) 
        {
            return controls.intValue() == (controls.intValue() & getHeldControls().intValue());
        }

        @Override
        public boolean isReleased(Number controls) 
        {
            return controls.intValue() == (controls.intValue() & getReleasedControls().intValue());
        }

        @Override
        public Number getPressedControls() 
        {
            return controls.intValue() & ~prevControls.intValue();
        }
        
        @Override
        public Number getHeldControls()
        {
            return controls.intValue();
        }

        @Override
        public Number getReleasedControls() 
        {
            return prevControls.intValue() & ~controls.intValue();
        }

        @Override
        public void forcePress(Number controls) 
        {
            this.controls = this.controls.intValue() | controls.intValue();
            preControls = this.controls;
        }

        @Override
        public void forceRelease(Number controls) 
        {
            this.controls = this.controls.intValue() & ~controls.intValue();
            preControls = this.controls;
        }

        @Override
        public void setControls(Number pressedControls, Number heldControls) 
        {
            controls = heldControls.intValue() | pressedControls.intValue();
            prevControls = controls.intValue() & ~pressedControls.intValue();
            preControls = controls;
        }
        
        @Override
        public boolean[][] toBoolArr()
        {
            boolean[][] arr = new boolean[2][32];
            int pressed = getPressedControls().intValue();
            int held = getHeldControls().intValue();
            for(int i = 0; i < 32; i++)
            {
                arr[0][i] = (0b1 << i) == (pressed & (0b1 << i));
                arr[1][i] = (0b1 << i) == (held & (0b1 << i));
            }
            
            return arr;
        }
    }
    
    /**
     * A 64-bit representation of ControlsObject.
     */
    public static class Long extends ControlsObject
    {
        public Long()
        {
            super();
        }
        
        public Long(long controls, boolean pressed)
        {
            super(controls, pressed);
        }
        
        public Long(long pressedControls, long heldControls)
        {
            this();
            setControls(pressedControls, heldControls);
        }
        
        @Override
        public void press(Number controls) 
        {
            preControls = preControls.longValue() | controls.longValue();
        }

        @Override
        public void release(Number controls) 
        {
            preControls = preControls.longValue() & ~controls.longValue();
        }

        @Override
        public boolean isPressed(Number controls) 
        {
            return controls.longValue() == (controls.longValue() & getPressedControls().longValue());
        }

        @Override
        public boolean isHeld(Number controls) 
        {
            return controls.longValue() == (controls.longValue() & getHeldControls().longValue());
        }

        @Override
        public boolean isReleased(Number controls) 
        {
            return controls.longValue() == (controls.longValue() & getReleasedControls().longValue());
        }

        @Override
        public Number getPressedControls() 
        {
            return controls.longValue() & ~prevControls.longValue();
        }
        
        @Override
        public Number getHeldControls()
        {
            return controls.longValue();
        }

        @Override
        public Number getReleasedControls() 
        {
            return prevControls.longValue() & ~controls.longValue();
        }

        @Override
        public void forcePress(Number controls) 
        {
            this.controls = this.controls.longValue() | controls.longValue();
            preControls = this.controls;
        }

        @Override
        public void forceRelease(Number controls) 
        {
            this.controls = this.controls.longValue() & ~controls.longValue();
            preControls = this.controls;
        }

        @Override
        public void setControls(Number pressedControls, Number heldControls) 
        {
            controls = heldControls.longValue() | pressedControls.longValue();
            prevControls = controls.longValue() & ~pressedControls.longValue();
            preControls = controls;
        }
        
        @Override
        public boolean[][] toBoolArr()
        {
            boolean[][] arr = new boolean[2][64];
            long pressed = getPressedControls().longValue();
            long held = getHeldControls().longValue();
            for(int i = 0; i < 64; i++)
            {
                arr[0][i] = (0b1 << i) == (pressed & (0b1 << i));
                arr[1][i] = (0b1 << i) == (held & (0b1 << i));
            }
            
            return arr;
        }
    }
}
