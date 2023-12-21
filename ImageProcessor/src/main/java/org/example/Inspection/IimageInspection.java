package org.example.Inspection;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface IimageInspection {
    // Method to extract image properties
    abstract  ImageProperties extractImageProperties(String sourceFilePath) throws IOException;

}
