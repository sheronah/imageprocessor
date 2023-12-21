import org.example.Cropping.Cropp;

import java.awt.Rectangle;
import java.io.IOException;

public class Tester {
    public static void main(String[] args) throws IOException {
        String sourcePath = "src/sample.jpg";

        String destinationPath = "src/copped.jpg";

        // cropping dimensions
        Rectangle croppingDimensions = new Rectangle(0, 0, 200, 200);

        // use the library
        Cropp cropper = new Cropp(sourcePath, destinationPath, croppingDimensions);

        // crop the image
        cropper.cropImage();

        System.out.println("Finished");


    }
}
