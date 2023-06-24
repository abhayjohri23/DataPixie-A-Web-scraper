package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Scraper {
    public static enum site{
        UDEMY , YOUTUBE;
    }
    public void getData(String courseName) throws Exception {
//      Request for Udemy Content
        HttpRequest request1 = HttpRequest.newBuilder()
                .header("Accept","application/json, text/plain, */*")
                .header("Authorization","Basic em5DYXByMDJYb3U2aHJZVHdIaEs3V09jVkV0aEZGem9VTkswNFY4Wjo3aEtnbmFmSHI2bndETDRGRkp0aFM4NEhSQ1RiNGgyNmVEWDVzMUZFc2Fab0FGNUI5Wld2VmRaaHJlbVBaeGV0Nkl6TGZRdEgyeWdMQXh3dk9MbmZ2T0hwazRhbVZKbmwwWGNicDdKYjh4Sm40elE0Ymk4V0RVdnozMFRjdElZOQ==")
                .header("Content-Type","application/json")
                .uri(URI.create("https://www.udemy.com/api-2.0/courses/?page=1&page_size=5"))
                .build();

        List<JsonNode> udemycourseList = getNodesFromRequest(request1,Scraper.site.UDEMY);

//      Request for youtube content
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(URI.create("https://youtube-search-results.p.rapidapi.com/youtube-search/?q=justin%2Bbieber"))
                .header("X-RapidAPI-Key", "2e0b7c2f57mshbaf13e7cc92be15p16369bjsnc34a01c4c15a")
                .header("X-RapidAPI-Host", "youtube-search-results.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        List<JsonNode> yt_courseList = getNodesFromRequest(request2, site.YOUTUBE);

    }

    public List<JsonNode> getNodesFromRequest(HttpRequest request,Scraper.site source) throws Exception{

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(response.body());

        JsonNode result_node = null;
        if(source.toString().equalsIgnoreCase("udemy"))             result_node = rootNode.get("results");
        else                                                                    result_node = rootNode.get("items");

        String result = result_node.toString();

        List<JsonNode> myList =new ArrayList<>();
        int st=1;
        int openbraces = 1;

        for(int i=2;i<result.length();++i){
            if(openbraces == 0){
                JsonNode object = mapper.readTree(result.substring(st,i));
                myList.add(object);
                ++i;
                st=i;
            }

            if(result.charAt(i)=='{')       openbraces++;
            if(result.charAt(i)=='}')       openbraces--;

        }

        return myList;
    }
}
