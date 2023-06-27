package com.SkillScraper.Backend.Database;
import com.SkillScraper.Backend.Logic.HostName;

import java.sql.*;

public class ConnectionHandler {
    public boolean addFields(HostName source) throws SQLException{
        Connection con = DriverManager.getConnection("","root","");
    }
}
