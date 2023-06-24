package com.SkillScraper;

import java.io.IOException;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import com.SkillScraper.HostName;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Scraper {
    public void getData(String courseName) throws Exception {
//      Request for Udemy Content
        HttpRequest request1 = RequestGenerator.getRequest(HostName.UDEMY);

//      Request for youtube content
        HttpRequest request2 = RequestGenerator.getRequest(HostName.YOUTUBE);

        List<JsonNode> udemycourseList = getNodesFromRequest(request1,HostName.UDEMY);
        List<JsonNode> yt_courseList = getNodesFromRequest(request2, HostName.UDEMY);

    }

    public List<JsonNode> getNodesFromRequest(HttpRequest request, HostName hostSite) throws Exception{

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //Creating the root node from Object Mapper
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(response.body());

        String res = hostSite.toString().equalsIgnoreCase("udemy")
                        ? rootNode.get("results").toString()
                        : rootNode.get("items").toString();

        return this.parseNodesList(res);
    }

    public JsonNode getNode(String s) throws IOException {
        s = s.substring(1,s.length()-1);
        return new ObjectMapper().readTree(s);
    }

    public List<JsonNode> parseNodesList(String s) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        List<JsonNode> list =new ArrayList<>();
        int st=1;
        int openbraces = 1;

        for(int i=2;i<s.length();++i){
            if(openbraces == 0){
                JsonNode object = mapper.readTree(s.substring(st,i));
                list.add(object);
                ++i;
                st=i;
            }

            if(i<s.length() && s.charAt(i)=='{')       openbraces++;
            if(i<s.length() && s.charAt(i)=='}')       openbraces--;
        }

        return list;
    }
}
