package org.pasqg.planetgenerator.renderer;

import org.pasqg.planetgenerator.defaults.Defaults;
import org.pasqg.planetgenerator.utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public enum TextureScaler {
    ;

    public static void main(String args[]) throws IOException {
        String texturePath = Defaults.class.getResource("/height_map_21K.png").getPath();
        BufferedImage image = ImageUtils.readFromFile(texturePath);
        BufferedImage scaledImage = ImageUtils.scale(image, 16384, 8192);
        String resourcesDir = texturePath.substring(0, texturePath.lastIndexOf('/'));
        ImageUtils.writeToFile(scaledImage, "./defaults/src/main/resources/height_map_16K.png", "png");
    }
}
