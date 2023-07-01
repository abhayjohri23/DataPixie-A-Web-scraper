package com.SkillScraper.Backend.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionProvider {
    private static Connection con;
    public static Connection getConnection() throws SQLException {
        if(con == null || con.isClosed()){
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","LOYDX0@123");
        }
        return ConnectionProvider.con;
    }
}
