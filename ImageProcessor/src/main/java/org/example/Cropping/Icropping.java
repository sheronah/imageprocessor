package org.example.Cropping;

import java.awt.*;
import java.io.IOException;

public interface Icropping {

    /**
     * Method to crop an image.
     *
     * @param sourceImage The source BufferedImage.
     * @param cropArea The area (Rectangle) to crop from the source image.
     * @return The cropped BufferedImage.
     */
    abstract void cropImage() throws IOException;




}
