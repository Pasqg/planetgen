package org.pasqg.planetgenerator.renderer;

import org.pasqg.planetgenerator.defaults.Defaults;
import org.pasqg.planetgenerator.utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public enum TextureScaler {
    ;

    public static void main(String[] args) throws IOException {
        String texturePath = Defaults.class.getResource("/worldmap.jpg").getPath();
        BufferedImage image = ImageUtils.readFromFile(texturePath);
        BufferedImage scaledImage = ImageUtils.scale(image, 16384, 8192);
        ImageUtils.writeToFile(scaledImage, "./defaults/src/main/resources/worldmap_16K.png", "png");
    }
}
