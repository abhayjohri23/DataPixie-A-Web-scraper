package com.SkillScraper.Backend.Database;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private static List<Connection> conPool;
    public static List<Connection> getConPool(){
        if(conPool.isEmpty()){
            conPool = new ArrayList<>();
        }

        return ConnectionPool.conPool;
    }
}
