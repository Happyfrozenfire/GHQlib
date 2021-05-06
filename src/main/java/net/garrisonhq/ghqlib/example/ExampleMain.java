/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.garrisonhq.ghqlib.example;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import net.garrisonhq.ghqlib.Game;
import net.garrisonhq.ghqlib.engine.Match;

/**
 *
 * @author gusjg
 */
public class ExampleMain extends Game
{
     
    public ExampleMain()
    {
        super(640, 640 * 9 / 12, "New Game");
    }
    
    @Override
    public void start() 
    {
        Match match = (Match)handler;
        match.addEntity(new ExampleEntity(100, 100));
        match.addEntity(new ExampleEntity(new Rectangle2D.Double(0, 0, 40, 40), 1, 2, Color.GREEN));
        match.addEntity(new ExampleEntity(new Rectangle2D.Double(0, 400, 1000, 1000), 0, 0, Color.MAGENTA));
    }
    
    public static void main(String[] args)
    {
        System.out.println("Hello world");
        new ExampleMain();
    }
}
