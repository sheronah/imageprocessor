package org.example.Inspection;

public class ImageProperties {
    // define the properties of the image
    int height;

    int width;

    String colorModel;



    public ImageProperties(){

    }


    public void setColorModel(String colorModel) {
        this.colorModel = colorModel;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
