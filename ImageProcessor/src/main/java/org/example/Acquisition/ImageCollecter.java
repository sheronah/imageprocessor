package org.example.Acquisition;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;

import org.bytedeco.javacv.*;


/**
 * This class implements the Image Acquisition Module of the image processing library.
 * It provides methods to read image data from various sources and perform initial format validation.
 */
public class ImageCollecter implements IimageCollecter{

    private BufferedImage img; // Single image data
    private List<BufferedImage> images = new ArrayList<>(); // Multiple image data
    private CameraDetector cameraDetector = new CameraDetector(); // Camera detector
    private List<String> imageFormats;

    /**
     * Reads image data from a camera.
     * @param cameraIndex The index of the camera to read from.
     */
    public void readImageData(int cameraIndex) {
        try {
            // Check if a camera is connected
            if (cameraDetector.isCameraConnected()) {
                // Open a FrameGrabber to capture video from the camera
                FrameGrabber grabber = new OpenCVFrameGrabber(cameraIndex);
                grabber.start();

                // Capture a frame from the camera
                Frame frame = grabber.grab();

                // Convert the captured frame to a BufferedImage
                Java2DFrameConverter converter = new Java2DFrameConverter();
                img = converter.convert(frame);

                // Release the FrameGrabber
                grabber.stop();
            } else {
                System.out.println("No camera connected.");
            }
        } catch (FrameGrabber.Exception e) {
            System.out.println("Error: " + e);
        }
    }

    /**
     * Reads image data from a file.
     * @param imagePath The path of the image file.
     */
    @Override
    public void readImageData(String imagePath) {
        try {
            File file = new File(imagePath);
            img = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }


    /**
     * Method to get image formats from a list of images.
     * @param images A list of image files.
     */
    public void getImageFormats(List<File> images) {
        imageFormats = new ArrayList<>();
        for (File image : images) {
            try {
                String format = Files.probeContentType(image.toPath());
                imageFormats.add(format);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method to perform initial format validation.
     * Checks if the image formats are supported.
     */
    @Override
    public void validateFormat() {
        // List of supported formats
        List<String> supportedFormats = Arrays.asList("image/jpeg", "image/png", "image/bmp");

        // Check if the image formats are supported
        for (String format : imageFormats) {
            if (supportedFormats.contains(format)) {
                System.out.println("Image format " + format + " is supported.");
            } else {
                System.out.println("Image format " + format + " is not supported.");
            }
        }
    }

    @Override
    public void passImageData() {
        // Assuming OtherModule has a method that takes BufferedImage as input
        // OtherModule otherModule  = new OtherModule();
        // otherModule.processImage(img);

    }

    /**
     * Reads image data from a URL.
     * @param imageUrl The URL of the image.
     */
    public void readImageDataFromUrl(URL imageUrl) {
        try {
            img = ImageIO.read(imageUrl);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    /**
     * Reads multiple images from a list of URLs.
     * @param imageUrls The list of image URLs.
     */
    public void readImages(List<String> imageUrls) {
        for (String imageUrl : imageUrls) {
            try {
                BufferedImage img = ImageIO.read(new URL(imageUrl));
                images.add(img);
            } catch (IOException e) {
                System.out.println("Error reading image from URL " + imageUrl + ": " + e);
            }
        }
    }


}
