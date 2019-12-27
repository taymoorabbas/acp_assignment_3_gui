/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 24-Dec-19
Time: 7:11 PM
Lau ji Ghauri aya fir
*/

import view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        UIManager.put("TabbedPane.selected", Color.ORANGE);
        UIManager.put("TabbedPane.unselectedBackground", Color.GRAY);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                new MainFrame("Assignment 3");
            }
        });
    }
}
