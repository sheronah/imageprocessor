package org.example.Acquisition;

import org.bytedeco.javacv.*;

/**
 * This class provides methods to detect if a camera is connected to the PC
 * and to get the index of the connected camera.
 */
public class CameraDetector implements ICameraDetector{
    /**
     * Checks if a camera is connected to the PC.
     * @return true if a camera is connected, false otherwise.
     */
    public boolean isCameraConnected() {
        int cameraIndex = 0; // start from the first camera
        FrameGrabber grabber = null;

        while (grabber == null) {
            try {
                grabber = new OpenCVFrameGrabber(cameraIndex);
                grabber.start(); // try to start the grabber
                grabber.stop();
                return true; // if successful, a camera is connected
            } catch (Exception e) {
                cameraIndex++; // if failed, try the next index
                if (cameraIndex > 10) { // limit the number of attempts
                    return false;
                }
            }
        }

        return false;
    }

    /**
     * Gets the index of the connected camera.
     * @return the index of the connected camera, or -1 if no camera is connected.
     */
    public int getCameraIndex() {
        int cameraIndex = 0; // start from the first camera
        FrameGrabber grabber = null;

        while (grabber == null) {
            try {
                grabber = new OpenCVFrameGrabber(cameraIndex);
                grabber.start(); // try to start the grabber
                grabber.stop();
                return cameraIndex; // if successful, return the camera index
            } catch (Exception e) {
                cameraIndex++; // if failed, try the next index
                if (cameraIndex > 10) { // limit the number of attempts
                    return -1;
                }
            }
        }

        return -1;
    }
}
