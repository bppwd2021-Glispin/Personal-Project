package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Resource {

    String type;
    BufferedImage img;

    public Resource(String type){
        try {
            this.img = ImageIO.read(new File("Images\\Resources\\"+type+".png"));
        }
        catch(IOException e){
            System.out.println(e);
        }
        this.type=type;
    }

}
