package services;

import models.ProjectDetails;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.*;

public class FreeLancerServices {

    String API = "https://www.freelancer.com/api/";
    static Scanner sc = new Scanner(System.in);


    public HashMap<String, List<ProjectDetails>> searchResults(String phrase, HashMap<String, List<ProjectDetails>> searchResults)
    {
        List<ProjectDetails> array = new ArrayList<>();
        try {
            URL url = new URL(API + "projects/0.1/projects/active?query=\""+ phrase +"\"&limit=10&job_details=true");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if(conn.getResponseCode() == 200) {
                Scanner scan = new Scanner(url.openStream());
                String temp="";
                while(scan.hasNext()) {
                    temp = temp + scan.nextLine();
                }
                JSONObject json = new JSONObject(temp);
                JSONObject result = json.getJSONObject("result");
                JSONArray projects = (JSONArray) result.getJSONArray("projects");

                for (int i = 0; i < projects.length() ; i++){
                    JSONObject object = projects.getJSONObject(i);

                    long ownerId =  Long.parseLong(object.get("owner_id").toString());
                    long timeSubmitted = Long.parseLong(object.get("submitdate").toString());
                    String title = object.get("title").toString() ;
                    String type = object.get("type").toString();
                    String projectDescription = object.get("preview_description").toString();

                    JSONArray skills = object.getJSONArray("jobs");
                    List <String> skillsList = new ArrayList<>();
                    for( int j=0; j<skills.length(); j++){
                        JSONObject skillObj = skills.getJSONObject(j);
                        skillsList.add(skillObj.get("name").toString());
                    }
                    array.add(new ProjectDetails(ownerId, skillsList, timeSubmitted, title, type, projectDescription));
                }
            }

            searchResults.put(phrase , array);
        } catch (Exception e) {
        }
        readabilityIndex(phrase, searchResults);
        return searchResults;
    }

    public double readabilityIndex(String phrase, HashMap<String, List<ProjectDetails>> searchResults)
    {


        Double searchResultUpadted = searchResults.get(phrase).stream().mapToDouble(project -> {
            double fkcl = 0;
            double fkgl = 0;
            int numOfSentence = 5;
            int numOfWords = 0;
            int numOfSyllables = 0;
            String educationalLevel = "";


            numOfWords = project.getProjectDescription().trim().split("\\s+").length;
            numOfSentence = project.getProjectDescription().trim().split("([.!?])([\\s\\n])([A-Z]*)").length;
//            numOfSyllables = project.getProjectDescription().trim()

            fkcl = (206.835 - 84.6 *((numOfSyllables/numOfWords)) - (1.015 *(numOfWords/numOfSentence)))/10;
            fkgl = (0.39 *((numOfSyllables/numOfWords)) + (11.8 *(numOfWords/numOfSentence))  -15.59);

            project.setDescriptionReadability(fkcl);

            if(fkcl > 100){
                educationalLevel = "Early" ;
            }else if(fkcl > 91 && fkcl <= 100){
                educationalLevel = "5th grade" ;
            }else if(fkcl > 81 && fkcl <= 91){
                educationalLevel = "6th grade" ;
            }else if(fkcl > 71 && fkcl <= 81){
                educationalLevel = "7th grade" ;
            }else if(fkcl > 66 && fkcl <= 71){
                educationalLevel = "8th grade" ;
            }else if(fkcl > 61 && fkcl <= 66){
                educationalLevel = "9th grade" ;
            }else if(fkcl > 51 && fkcl <= 61){
                educationalLevel = "High School" ;
            }else if(fkcl > 31 && fkcl <= 51){
                educationalLevel = "Some College" ;
            } else if(fkcl > 0 && fkcl <= 31){
                educationalLevel = "College Graduate" ;
            }else if(fkcl <= 0 ){
                educationalLevel = "Law School Graduate" ;
            }

            project.setEducationalLevel(educationalLevel);

            return fkcl;
        }).average().getAsDouble();

        return searchResultUpadted;
    }
}

