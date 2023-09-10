package com.ddquin.tetrisdd.util;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Util {

    public static InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = Util.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }


    public static Font getArcadeFont(int fontSize) {
        InputStream is = getFileFromResourceAsStream("fonts/arcade.ttf");
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont((float) fontSize);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
