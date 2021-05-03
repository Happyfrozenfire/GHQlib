/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.garrisonhq.ghqlib.util;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import net.garrisonhq.ghqlib.Game;

/**
 *
 * @author gusjg
 */
public class Window extends Canvas
{
    private static final long serialVersionUID = -8394711071520018246L;


    public Window(int width, int height, String title, Game game)
    {
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        frame.toFront();
        game.startFromWindow();
    }
}