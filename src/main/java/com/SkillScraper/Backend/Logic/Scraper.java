package com.SkillScraper.Backend.Logic;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Scraper {
    public Map<HostName,List<JsonNode>> getData(String courseName) throws Exception {
        HttpRequest request1 = RequestGenerator.getRequest(courseName,HostName.UDEMY);
        HttpRequest request2 = RequestGenerator.getRequest(courseName,HostName.YOUTUBE);

//        System.out.println("Request created!");

        List<JsonNode> udemycourseList = getNodesFromRequest(request1,HostName.UDEMY);
        List<JsonNode> yt_courseList = getNodesFromRequest(request2, HostName.YOUTUBE);

//        System.out.println("Nodes received");
        Map<HostName,List<JsonNode>> finalMap = new HashMap<>();
        finalMap.put(HostName.UDEMY,udemycourseList);
        finalMap.put(HostName.YOUTUBE,yt_courseList);

        return finalMap;
    }

    public List<JsonNode> getNodesFromRequest(HttpRequest request, HostName hostSite) throws Exception {

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //Creating the root JSON node from Object Mapper
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(response.body());

        String res;
        if(hostSite == HostName.UDEMY)
            res = rootNode.get("results").toString();
        else
            res = rootNode.get("contents").toString();

        System.out.println(res);

        return new ObjectMapper().readValue(res, new TypeReference<List<JsonNode>>() {
        });
    }
}
