/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matalanscarvescollage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;

/**
 *
 * @author qwer
 */
class CollageGenerator {
    
    public static final Map<Character, Integer> charCounts = new HashMap<>();
    
    static
    {
        charCounts.put('a', 2);
        charCounts.put('b', 2);
        charCounts.put('c', 2);
        charCounts.put('d', 2);
        charCounts.put('e', 1);
        charCounts.put('f', 1);
        charCounts.put('g', 1);
        charCounts.put('h', 1);
        charCounts.put('i', 1);
        charCounts.put('j', 3);
        charCounts.put('k', 1);
        charCounts.put('l', 2);
        charCounts.put('m', 2);
        charCounts.put('n', 2);
        charCounts.put('o', 1);
        charCounts.put('p', 2);
        charCounts.put('q', 1);
        charCounts.put('r', 1);
        charCounts.put('s', 3);
        charCounts.put('t', 1);
        charCounts.put('u', 1);
        charCounts.put('v', 1);
        charCounts.put('w', 1);
        charCounts.put('x', 1);
        charCounts.put('y', 1);
        charCounts.put('z', 1);
    }
    
    public static final int borderWidth = 10;
    
    public static final void generate(int width, int height, Color bgColor, String phrase, String filename) {
        phrase = phrase.replaceAll(" ", "");
        int imgWidth = width * 200 + borderWidth * (width + 1);
        int imgHeight = height * 200 + borderWidth * (height + 1);
        
        try {
           
            BufferedImage collage = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
            
            Graphics2D canvas = collage.createGraphics();
            
            canvas.setColor(bgColor);
            canvas.fillRect(0, 0, imgWidth, imgHeight);
            
            // URL res
            
            int x = 0, y = 0, i;
            Random random = new Random();
            BufferedImage img;
            String path;
            char symbol;
            InputStream resourceStream; // URL
            
            // for (char symbol : phrase.toCharArray()) {
            for (i = 0; i < width * height; i++) {
                if (i >= phrase.length()) {
                    resourceStream = null;
                } else {
                    symbol = phrase.charAt(i);
                    path = "/matalanscarvescollage/alphabet/" + symbol + (random.nextInt(charCounts.get(symbol)) + 1) + ".png";
                    resourceStream = CollageGenerator.class.getResourceAsStream(path);
                }

                // if (imgFolder.list().length == 0) {
                if (resourceStream == null) {
                    path = "/matalanscarvescollage/alphabet/blank" + (random.nextInt(6) + 1) + ".png";
                    img = ImageIO.read(CollageGenerator.class.getResourceAsStream(path));
                } else {
                    img = ImageIO.read(resourceStream);
                }

                canvas.drawImage(img, x * 200 + (x + 1) * 10, y * 200 + (y + 1) * 10, null);

                if (x >= width - 1) {
                    x = 0;
                    y++;
                } else {
                    x++;
                }
                
            }
    
            canvas.dispose();
            
            File imageFile = new File(filename);
            ImageIO.write(collage, "png", imageFile);
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
