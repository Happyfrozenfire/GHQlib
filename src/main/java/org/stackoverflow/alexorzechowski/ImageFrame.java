package org.stackoverflow.alexorzechowski;
//https://stackoverflow.com/a/17269591

import java.awt.image.BufferedImage;

/**
 * Represents an immutable frame of animation.
 * @author Alex Orzechowski
 */
public class ImageFrame 
{
    private final int delay;
    private final BufferedImage image;
    private final String disposal;
    private final int width, height;

    /**
     * Constructs a new ImageFrame instance from the given image, delay, disposal, width, and height.
     * 
     * @param image
     * @param delay
     * @param disposal
     * @param width
     * @param height 
     */
    public ImageFrame (BufferedImage image, int delay, String disposal, int width, int height){
        this.image = image;
        this.delay = delay;
        this.disposal = disposal;
        this.width = width;
        this.height = height;
        System.out.println("Delay: " + delay);
    }

    /**
     * Constructs a new ImageFrame instance from the given image. 
     * @param image 
     */
    public ImageFrame (BufferedImage image){
        this.image = image;
        this.delay = -1;
        this.disposal = null;
        this.width = -1;
        this.height = -1;
    }

    /**
     * @return the ImageFrame's image.
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * @return the delay from this frame to the next in hundredths of a second.
     */
    public int getDelay() {
        return delay;
    }

    /**
     * @return The disposal method of this instance.
     */
    public String getDisposal() {
        return disposal;
    }

    /**
     * @return The width of the image.
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return The height of the image.
     */
    public int getHeight() {
            return height;
    }
    
    public ImageFrame changeDelay(int delay)
    {
        return new ImageFrame(image, delay, disposal, width, height);
    }
}