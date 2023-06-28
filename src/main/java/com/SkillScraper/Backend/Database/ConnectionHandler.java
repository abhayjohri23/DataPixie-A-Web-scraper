package com.SkillScraper.Backend.Database;
import com.SkillScraper.Backend.Logic.HostName;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Map;

public final class ConnectionHandler {
    public static void handleRequest(Map<HostName, List<JsonNode>> contentMap){
        List<JsonNode> udemyList = contentMap.get(HostName.UDEMY);
        List<JsonNode> ytList = contentMap.get(HostName.YOUTUBE);

        System.out.println("UDEMY size: "+ udemyList.size());
        System.out.println("YT size: "+ ytList.size());

        udemyList.forEach((node)->{
            try {
                boolean flag = addFields(HostName.UDEMY,node);
                if(!flag){
                    System.out.println("Udemy video not inserted!");
                    return ;
                }

            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });

        ytList.forEach((node)->{
            try{
                boolean flag = addFields(HostName.YOUTUBE,node);
                if(!flag){
                    System.out.println("Youtube video not inserted!");
                    return ;
                }

            }catch(SQLException | IOException e){
                e.printStackTrace();
            }
        });

        System.out.println("Tuples added successfully");
    }

    public static boolean addFields(HostName source, JsonNode node) throws SQLException, IOException {
        Connection con = ConnectionProvider.getConnection();
        try{
            String checkQuery = "USE SKILLSCRAPER_DB";
            con.createStatement().execute(checkQuery);
        }catch(Exception e){
            boolean flag = ConnectionHandler.DatabaseSetUp(con);
            if(!flag){
                System.out.println("DATABASE IS NOT CREATED!");
                return false;
            }
        }

        String courseQuery = "INSERT INTO COURSES (COURSE_ID, TITLE, HEADLINE, PRICE, URL, IMAGE_URL, INSTRUCTOR_NAME, SOURCE)"
                +" VALUES (?,?,?,?,?,?,?,'"+source.toString()+"')";

        PreparedStatement courseStmt = con.prepareStatement(courseQuery);
        boolean areParamSet = ConnectionHandler.setParams(courseStmt,node,source);

        if(!areParamSet)
            return false;

        try{
            courseStmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean DatabaseSetUp(Connection con){
        try{
            System.out.println("DATABASE IS NOT SETUP");
            String dbCreateQuery = "CREATE DATABASE SKILLSCRAPER_DB";
            con.createStatement().execute(dbCreateQuery);

            String dbUseQuery = "USE SKILLSCRAPER_DB";
            con.createStatement().execute(dbUseQuery);

            //Creating the table
            System.out.println("Creating table");
            con.createStatement().execute("CREATE TABLE COURSES (COURSE_ID VARCHAR(20) PRIMARY KEY, TITLE VARCHAR(500), HEADLINE VARCHAR(1000), PRICE VARCHAR(10), URL VARCHAR(800), IMAGE_URL VARCHAR(800), INSTRUCTOR_NAME VARCHAR(100), SOURCE VARCHAR(20))");
            System.out.println("Course table created!");
        }catch(SQLException e){
            return false;
        }

        return true;
    }

    public static boolean setParams(PreparedStatement query,JsonNode node,HostName source) throws IOException{
        try {
        if(source == HostName.UDEMY){
                query.setString(1,node.get("id").toString());
                query.setString(2,node.get("title").toString().toLowerCase());
                query.setString(3,node.get("headline").toString());
                query.setString(4,node.get("price").toString());
                query.setString(5,node.get("url").toString());
                query.setString(6,node.get("image_480x270").toString());

                String contString = node.get("visible_instructors").toString();
                JsonNode containerNode = new ObjectMapper().readTree(contString.substring(1,contString.length()-1));
                query.setString(7,containerNode.get("display_name").toString());
            }
            else{
                JsonNode idNode = node.get("video");
                if(idNode == null)
                    return false;
                query.setString(1,node.get("video").get("videoId").toString());
                query.setString(2,node.get("video").get("title").toString().toLowerCase());
                query.setString(3,node.get("video").get("descriptionSnippet").toString());
                query.setString(4,"Free");
                List<JsonNode> temp = new ObjectMapper().readValue(node.get("video").get("thumbnails").toString(),new TypeReference<List<JsonNode>>(){});
                if(temp == null)    return false;
                query.setString(5,temp.get(0).get("url").toString());

                temp = new ObjectMapper().readValue(node.get("video").get("movingThumbnails").toString(),new TypeReference<List<JsonNode>>(){});
                if(temp == null)    return false;
                query.setString(6,temp.get(0).get("url").toString());

                query.setString(7,node.get("video").get("author").get("title").toString());
            }
        } catch (Exception e) {
            System.out.println("Parameter setting issue. Response doesn't follow the format");
            return false;
        }

        return true;
    }

    public static ResultSet getResponse(String courseName) throws SQLException {
        Connection con = ConnectionProvider.getConnection();
        try{
            String checkQuery = "USE SKILLSCRAPER_DB";
            con.createStatement().execute(checkQuery);
        }catch(Exception e){
            boolean flag = ConnectionHandler.DatabaseSetUp(con);
            if(!flag){
                System.out.println("There is some serious issue.");
                System.out.println("Aborting!");
                return null;
            }
        }
        String query = "SELECT * FROM COURSES WHERE TITLE LIKE \"%"+courseName.toLowerCase()+"%\"";
        Statement stmt = con.createStatement();

        return stmt.executeQuery(query);
    }
}
