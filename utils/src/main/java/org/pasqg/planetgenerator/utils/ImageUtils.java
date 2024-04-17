package org.pasqg.planetgenerator.utils;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtils {

    public static BufferedImage readFromFile(String aPath) throws IOException {
        return readFromFile(new File(aPath));
    }

    public static BufferedImage readFromFile(File aFile) throws IOException {
        return ImageIO.read(aFile);
    }

    public static void writeToFile(BufferedImage aImage, String aPath, String aFormat) throws IOException {
        ImageIO.write(aImage, aFormat, new File(aPath));
    }

    public static BufferedImage scale(BufferedImage aOriginal, int aResultX, int aResultY) {
        int type = aOriginal.getType() == BufferedImage.TYPE_CUSTOM
                ? BufferedImage.TYPE_3BYTE_BGR
                : aOriginal.getType();
        BufferedImage scaledImage = new BufferedImage(aResultX, aResultY, type);
        Graphics2D graphics2D2 = scaledImage.createGraphics();
        graphics2D2.drawImage(aOriginal, 0, 0, aResultX, aResultY, null);
        graphics2D2.dispose();

        return scaledImage;
    }
}
