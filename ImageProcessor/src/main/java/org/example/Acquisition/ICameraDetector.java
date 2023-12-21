package org.example.Acquisition;


/**
 * This interface defines the methods for a Camera Detector.
 * It provides methods to detect if a camera is connected to the PC and to get the index of the connected camera.
 */
public interface ICameraDetector {
    /**
     * Checks if a camera is connected to the PC.
     * @return true if a camera is connected, false otherwise.
     */
    boolean isCameraConnected();

    /**
     * Gets the index of the connected camera.
     * @return the index of the connected camera, or -1 if no camera is connected.
     */
    int getCameraIndex();
}