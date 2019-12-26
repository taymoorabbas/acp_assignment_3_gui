/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 26-Dec-19
Time: 2:56 PM
Lau ji Ghauri aya fir
*/

package util;

import java.util.prefs.Preferences;

public class Prefs {

    private static final String COUNT = "count";
    private static Preferences preferences = Preferences.userRoot().node(Preferences.class.getName());

    public static void saveCount(int count){

        preferences.putInt(COUNT, count);
    }

    public static int getCount(){

        int count = preferences.getInt(COUNT, 0);
        count++;
        saveCount(count);
        return count;
    }

}
