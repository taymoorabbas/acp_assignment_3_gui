/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 24-Dec-19
Time: 8:37 PM
Lau ji Ghauri aya fir
*/

package util;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class IconRenderer {

    public static ImageIcon getIcon(String path, int size){

        URL url = IconRenderer.class.getResource(path);
        if(url == null){

            System.err.println("Unable to load image from path: " + path);
        }
        else{

            ImageIcon imageIcon = new ImageIcon(url);
            Image image = imageIcon.getImage();
            Image resized = image.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);

            return new ImageIcon(resized);
        }
        return null;
    }
}
