package org.example.Cropping;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import org.example.Inspection.imageInspection;

import javax.imageio.ImageIO;

public class Cropp implements Icropping{
    String imagePath;

    String sourcePath;

    String destinationPath;

    Rectangle dimensionsOfCroppedImage;

    String fileFormat;


    public  Cropp(String sourcePath, String destinationPath,  Rectangle dimensionsOfCroppedImage) throws IOException {
        // create an inspection object
        imageInspection imageInspector = new imageInspection(sourcePath, destinationPath, dimensionsOfCroppedImage);

        // validate if the paths are valid
        imageInspector.validateSourceAndDestinationFilePaths();

        // validate the dimensions
        imageInspector.validateCroppedImageDimensions();

        // set the file format
        this.fileFormat = imageInspector.getFileExtension(sourcePath).toUpperCase();

        // initialize the source and destination path after validating that they are files and have valid extensions
        this.sourcePath =  sourcePath;

        this.destinationPath =  destinationPath;

        this.dimensionsOfCroppedImage = dimensionsOfCroppedImage;

    }

    @Override
    public void cropImage() throws IOException {

        try {
            BufferedImage image = ImageIO.read(new File(this.sourcePath));

            // get a copy of the cropped image
            BufferedImage croppedImage = image.getSubimage(this.dimensionsOfCroppedImage.x, this.dimensionsOfCroppedImage.y, this.dimensionsOfCroppedImage.width, this.dimensionsOfCroppedImage.height);

            // save the cropped image in the destination path
            ImageIO.write(croppedImage, this.fileFormat, new File(this.destinationPath));

        } catch (Exception e){
            e.printStackTrace();

        }


    }

}
