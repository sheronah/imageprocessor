package org.example.Inspection;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;


import java.io.File;
import java.io.IOException;

public class imageInspection implements IimageInspection {
    String sourceFileLocation;

    String destinationFileLocation;

    Rectangle dimensionsForCropping;

    String [] acceptedFileExtensions = {"jpg", "png", "jpeg", "bmp"};


    public imageInspection(String sourceFileLocation, String destinationFileLocation, Rectangle dimensionsForCropping){
        this.sourceFileLocation = sourceFileLocation;

        this.destinationFileLocation = destinationFileLocation;

        this.dimensionsForCropping = dimensionsForCropping;

    }

    public void validateSourceAndDestinationFilePaths(){
        // check if the source path is a file
        boolean sourceIsFile = this.validateImageFilePath(this.sourceFileLocation);

        // validate if it is truly a file
        assert sourceIsFile : "The source file path has to be for fil";

        boolean destinationIsFile = this.validateImageFilePath(this.destinationFileLocation);

        assert destinationIsFile : "The destination file path has to be for file";

        // validate that they have valid file extensions
        boolean sourceExtensionIsValid = this.hasValidExtension(this.sourceFileLocation);

        assert  sourceExtensionIsValid : "The provided source file location is not for an image";

        boolean destinationExtensionIsValid = this.hasValidExtension(this.destinationFileLocation);

        assert  destinationExtensionIsValid : "The provided destination file location is not for an image";

        return;
    }


    public boolean validateImageFilePath(String imageFilePath){
        File fileInstance = new File(imageFilePath);

        // check if it is a folder or file

        return fileInstance.isFile();
    }

    public String getFileExtension(String filePath){
        int dotIndex = filePath.lastIndexOf(".");

        return filePath.substring(dotIndex + 1);
    }

    /**
     *
     * @param filePath path to a file
     * @return if the file has a valid file extension
     */
    public boolean hasValidExtension(String filePath){
        //   /drive/image/file.jpg

        boolean hasValidFileExtension = false;

        String fileExtension = this.getFileExtension(filePath);

        for (String acceptedExtension : this.acceptedFileExtensions) {
            if (fileExtension.equals(acceptedExtension)) {
                hasValidFileExtension = true;
                break;
            }
        }

        return hasValidFileExtension;
    }


    public void validateCroppedImageDimensions() throws IOException {
        // get image properties
        ImageProperties originalProperties = this.extractImageProperties(this.sourceFileLocation);

        // validate the start positions
        assert  dimensionsForCropping.x >= 0 : "Only positive points are accepted";

        assert  dimensionsForCropping.y >= 0 : "Only positive points are accepted";

        // validate the height and width
        assert  dimensionsForCropping.width > 0: "Width should be a non negative value and greater than zero";

        assert  dimensionsForCropping.height > 0: "Height should be a non negative value and greater than zero";

        // validate if the width and height are within range
        assert dimensionsForCropping.width <= originalProperties.width : "Cropping width dimensions are out of range";

        assert dimensionsForCropping.height <= originalProperties.height : "Cropping width dimensions are out of range";

    }




    /**
     * Overloaded method to extract image properties from an image file.
     *
     * @param filePath The path to the image file.
     * @return The ImageProperties object containing the properties of the image.
     * @throws IOException If an error occurs while reading the file.
     */
    public ImageProperties extractImageProperties(String filePath) throws IOException {
        File file = new File(filePath);

        BufferedImage img = ImageIO.read(file);

        ImageProperties properties = new ImageProperties();

        properties.setWidth(img.getWidth());

        properties.setHeight(img.getHeight());


        return properties;
    }

}





