/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 24-Dec-19
Time: 7:28 PM
Lau ji Ghauri aya fir
*/

package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static Connection connection;

    public static void connect(String databasePath) {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(databasePath);
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {

        return connection;
    }

    public static void disconnect() {

        try {
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
