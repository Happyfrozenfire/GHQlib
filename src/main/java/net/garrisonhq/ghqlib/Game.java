/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.garrisonhq.ghqlib;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import net.garrisonhq.ghqlib.controls.KeyboardInput;
import net.garrisonhq.ghqlib.engine.GameTickable;
import net.garrisonhq.ghqlib.example.ExampleEntity;
import net.garrisonhq.ghqlib.engine.Match;
import net.garrisonhq.ghqlib.util.Window;

/**
 *
 * @author gusjg
 */
public abstract class Game extends Canvas implements Runnable, GameTickable
{
    /**
     * The amount of times {@link #tick()} should run per second.
     */
    public static final double FPS = 60;
    
    /**
     * The current game instance.
     */
    public static Game INSTANCE;
    
    private Thread thread;
    private boolean running = false;
    
    /**
     * The int that tells us the window width. Considering changing this to a 
     * getter.
     */
    public int WINDOW_WIDTH;
    
    /**
     * The int that tells us the window height. Considering changing this to a
     * getter.
     */
    public int WINDOW_HEIGHT;
    
    /**
     * The reference to the current handler.
     */
    public GameTickable handler;
    
    /**
     * The reference to all the current input objects.
     */
    public KeyboardInput[] inputArr;
    
    /**
     * Constructs a new Game/Window instance.
     * 
     * @param width
     * @param height
     * @param title 
     */
    protected Game(int width, int height, String title)
    {
        INSTANCE = this;
        WINDOW_WIDTH = width;
        WINDOW_HEIGHT = height;
        
        new Window(width, height, title, this);
    }
    
    @Override
    public final void run() 
    {
        long lastTime = System.nanoTime();
        double amountOfTicks = FPS;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
          long now = System.nanoTime();
          delta += (now - lastTime) / ns;
          lastTime = now;
          while(delta >= 1)
          {
            tick();
            delta--;
          }
          if(running)
          {
            fullRender();
          }
          frames++;
          if(System.currentTimeMillis() - timer > 1000)
          {
            timer += 1000;
            
            String debugStatement = "DEBUG: " + "\n";
            debugStatement += "FPS: " + frames;
            
            System.out.println(debugStatement);
            frames = 0;
          }
        }
        stop();
    }
    
    /**
     * Is called from Window. Do not call this anywhere else.
     */
    public final synchronized void startFromWindow()
    {
        this.start();
        
        running = true;
        thread = new Thread(this);
        thread.start();
        this.requestFocus();
    }
    
    /**
     * Should be overridden to create the input array, handler, etc.
     */
    public abstract void start();
    
    /**
     * Use this to end the program.
     */
    @SuppressWarnings("CallToPrintStackTrace")
    public synchronized void stop()
    {
        try
        {
            thread.join();
            running = false;
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Don't override this unless you're clever. Instead, create a new GameTickable
     * implementation and use that.
     */
    @Override
    public void tick()
    {
        //tick inputArr
        for(KeyboardInput input : inputArr)
        {
            if(input != null)
            {
                input.getControls().update();
            }
        }
        
        //tick game logic
        handler.tick();
    }
    
    /**
     * Renders the current graphics.
     */
    public final void fullRender()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
          this.createBufferStrategy(3);
          return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        this.render(g);
        
        g.dispose();
        bs.show();
    }
    
    /**
     * Don't override this unless you're clever. Instead, create a new GameTickable
     * implementation and use that.
     * 
     * @param g 
     */
    @Override
    public void render(Graphics g)
    {
        handler.render(g);
    }
}
