package org.example.Acquisition;


/**
 * This interface defines the Image Acquisition Module of the image processing library.
 * It provides methods to read image data from various sources and perform initial format validation.
 */
public interface IimageCollecter {
    /**
     * Reads image data from a source.
     * The source could be a file, a URL, a camera, a network stream, etc.
     * The specific source is determined by the implementation of this method in a class that implements this interface.
     */
    void readImageData(String imagePath);

    /**
     * Performs initial format validation on the read image data.
     * The specific validation process is determined by the implementation of this method in a class that implements this interface.
     */
    void validateFormat();

    /**
     * Passes the image data and relevant information to the other modules.
     * The specific data passed and the modules it is passed to are determined by the implementation of this method in a class that implements this interface.
     */
    void passImageData();
}