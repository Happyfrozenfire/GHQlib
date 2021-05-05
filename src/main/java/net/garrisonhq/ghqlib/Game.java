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
import net.garrisonhq.ghqlib.debug.DebugEntity;
import net.garrisonhq.ghqlib.engine.Match;
import net.garrisonhq.ghqlib.util.Window;

/**
 *
 * @author gusjg
 */
public abstract class Game extends Canvas implements Runnable
{
    public static final double FPS = 60;
    public static Game INSTANCE;
    
    private Thread thread;
    private boolean running = false;
    public int WINDOW_WIDTH;
    public int WINDOW_HEIGHT;
    public Match match;
    
    public Game(int width, int height, String title)
    {
        INSTANCE = this;
        WINDOW_WIDTH = width;
        WINDOW_HEIGHT = height;
        
        new Window(width, height, title, this);
    }
    
    @Override
    public void run() 
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
            debugStatement += "TPS: " + frames + "\n";
            debugStatement += this.debug();
            
            System.out.println(debugStatement);
            frames = 0;
          }
        }
        stop();
    }
    
    public final synchronized void startFromWindow()
    {
        match = new Match();
        
        this.start();
        
        running = true;
        thread = new Thread(this);
        thread.start();
        this.requestFocus();
    }
    
    public abstract void start();
    
    
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
    
    
    
    public void tick()
    {
        match.tick();
    }
    
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
    
    public void render(Graphics g)
    {
        match.render(g);
    }
    
    public String debug()
    {
        return "";
    }
    
    public static void main(String[] args)
    {
        System.out.println("Hello world");
        new Game(640, 640 * 9 / 12, "New Game")
        {
            @Override
            public void start() 
            {
                match.addEntity(new DebugEntity(100, 100));
            }
        };
    }
}
