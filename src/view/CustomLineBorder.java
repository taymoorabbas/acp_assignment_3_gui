/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 25-Dec-19
Time: 9:41 PM
Lau ji Ghauri aya fir
*/

package view;

import javax.swing.border.LineBorder;
import java.awt.*;

public class CustomLineBorder extends LineBorder {

    public CustomLineBorder(Color color) {
        super(color, 1, false);
    }

    public CustomLineBorder(Color color, int thickness) {
        super(color, thickness, false);
    }

    public CustomLineBorder(Color color, int thickness, boolean roundedCorners) {
        super(color, thickness, roundedCorners);
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        super.paintBorder(c, g, x, y, width, height);
        g.setColor(Color.black);
        g.fillRect(x, y, x+1, y+height);
        g.fillRect(x+width-1, y, x+width, y+height);
    }
}