package test;


import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ImageTest {

    @Test
    void test(){

        try {
            BufferedImage bufferedImage = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/0.gif"));
            assertNotNull(bufferedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
