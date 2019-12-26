/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 25-Dec-19
Time: 6:41 PM
Lau ji Ghauri aya fir
*/

package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomColumnRenderer extends DefaultTableCellRenderer {

    private Color backgroundColor, foregroundColor;

    public CustomColumnRenderer(Color bkgnd, Color foregnd) {
        super();
        backgroundColor = bkgnd;
        foregroundColor = foregnd;
    }

    public Component getTableCellRendererComponent
            (JTable table, Object value, boolean isSelected,
             boolean hasFocus, int row, int column)
    {
        Component cell = super.getTableCellRendererComponent
                (table, value, isSelected, hasFocus, row, column);

        cell.setBackground(backgroundColor);
        cell.setForeground(foregroundColor);

        return cell;
    }
}
