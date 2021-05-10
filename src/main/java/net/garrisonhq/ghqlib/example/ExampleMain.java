/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.garrisonhq.ghqlib.example;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import net.garrisonhq.ghqlib.Game;
import net.garrisonhq.ghqlib.controls.KeyboardInput;
import net.garrisonhq.ghqlib.engine.Match;

/**
 *
 * @author gusjg
 */
public class ExampleMain extends Game
{
    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    
    public ExampleMain()
    {
        super(640, 640 * 9 / 12, "New Game");
    }
    
    @Override
    public void start() 
    {
        inputArr = new KeyboardInput[1];
        inputArr[0] = new KeyboardInput(new ExampleControls());
        this.addKeyListener(inputArr[0]);
        inputArr[0].getInputMap().put(KeyEvent.VK_UP, ExampleControls.UP);
        inputArr[0].getInputMap().put(KeyEvent.VK_DOWN, ExampleControls.DOWN);
        inputArr[0].getInputMap().put(KeyEvent.VK_LEFT, ExampleControls.LEFT);
        inputArr[0].getInputMap().put(KeyEvent.VK_RIGHT, ExampleControls.RIGHT);
        Match match = getMatch();
        match.addEntity(new ExampleEntity(100, 100));
        match.addEntity(new ExampleEntity(new Rectangle2D.Double(0, 0, 40, 40), 1, 2, Color.GREEN));
        match.addEntity(new ExampleEntity(new Rectangle2D.Double(0, 400, 1000, 1000), 0, 0, Color.MAGENTA));
        match.addEntity(new ExamplePlayer(300, 300, inputArr[0].getControls()));
    }
    
    public static void main(String[] args)
    {
        System.out.println("Hello world");
        INSTANCE = new ExampleMain();
    }
    
    public Match getMatch()
    {
        return (Match)handler;
    }
}
