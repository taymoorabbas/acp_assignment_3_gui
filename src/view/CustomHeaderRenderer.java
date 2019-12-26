/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 25-Dec-19
Time: 9:58 PM
Lau ji Ghauri aya fir
*/

package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomHeaderRenderer extends DefaultTableCellRenderer {

    private static LineBorder border = new CustomLineBorder(Color.BLACK);

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                            boolean selected, boolean focus, int row, int col) {
        Component c = super.getTableCellRendererComponent(table, value, selected, focus, row, col);
        setHorizontalAlignment(JLabel.CENTER);
        setBackground(Color.ORANGE);
        setForeground(Color.BLACK);
        setBorder(border);
        setFont(new Font("SanSerif", Font.BOLD, 25));
        return c;
    }
}