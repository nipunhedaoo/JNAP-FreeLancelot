package services;

import models.ProjectDetails;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.toMap;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class FreeLancerServices {

    String API = "https://www.freelancer.com/api/";
    static Scanner sc = new Scanner(System.in);
    static LinkedHashMap<String, Map<String, Integer>> wordStats = new LinkedHashMap<>();

    public List<ProjectDetails> searchResults(String phrase)
    {
        List<ProjectDetails> array = new ArrayList<>();
        List<String> descriptionArray = new ArrayList<>();
        try {
            URL url = new URL(API + "projects/0.1/projects/active?query=\""+ URLEncoder.encode(phrase, String.valueOf(StandardCharsets.UTF_8)) +"\"&limit=10&job_details=true");
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

                    long projectID =  Long.parseLong(object.get("id").toString());
                    long ownerId =  Long.parseLong(object.get("owner_id").toString());
                    long timeSubmitted = Long.parseLong(object.get("submitdate").toString());
                    String title = object.get("title").toString() ;
                    String type = object.get("type").toString();
                    String projectDescription = object.get("preview_description").toString();
                    String preview_description = object.get("preview_description").toString();
                    descriptionArray.add(preview_description);

                    Map<String, Integer> wordStats = wordStatsIndevidual(object.get("preview_description").toString());

                    JSONArray skills = object.getJSONArray("jobs");
                    List <List<String>> skillsList = new ArrayList<>();
                    for( int j=0; j<skills.length(); j++){
                        JSONObject skillObj = skills.getJSONObject(j);
                        List<String> skill=new ArrayList<>();
                        skill.add(skillObj.get("id").toString()+"/"+ URLEncoder.encode(skillObj.get("name").toString(), StandardCharsets.UTF_8));
                        skill.add(skillObj.get("name").toString());
                        skillsList.add(skill);

                    }
                    array.add(new ProjectDetails(projectID, ownerId, skillsList, timeSubmitted, title, type, projectDescription, descriptionReadability,
                    educationalLevel,
                    preview_description, wordStats, preview_description));
                }
            }
        } catch (Exception e) {
        }
        return array;
    }

    public Map<String, Integer> wordStatsIndevidual(String description ) {
        Map<String, Integer> counterMap = new HashMap<>();
        Map<String, Integer> sortedMap = null;
        try {
            Arrays.asList(
                        description.replaceAll("\\p{Punct}", "").split(" ")
                    )
                    .stream()
                    .forEach(word -> {
                        if (counterMap.get(word) == null)
                            counterMap.put(word, 1);
                        else
                            counterMap.put(word, counterMap.get(word) + 1);
                    });

            sortedMap = counterMap
                    .entrySet()
                    .stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));


        } catch (Exception e) {
            e.printStackTrace();
        }
        readabilityIndex(phrase, searchResults);
        return searchResults;
    }

    public static char lastChar (String aWord)
    {
        char aChar = aWord.charAt(aWord.length() - 2);
        System.out.print (aChar);
        return aChar;
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

            String projectDescription = project.getProjectDescription();
            numOfWords = projectDescription.trim().split("\\s+").length;
            numOfSentence = projectDescription.trim().split("([.!?:;])([\\s\\n])([A-Z]*)").length;
            numOfSyllables = Arrays.stream(projectDescription.trim().split("\\s+")).mapToInt(word -> {

                int syllables = 0;

                ArrayList<Integer> vowels = new ArrayList<>(Arrays. asList(97,101,105,111,117, 65, 69,73, 79, 85));

                if(word.length() <= 3){
                    syllables = 1;
                }else {
                    char[] alphabets = word.toCharArray();

                    char syllableList [] = {'a', 'e', 'i', 'o', 'u', 'y'};

                    // Syllables
                    for (int k = 0; k < syllableList.length; k++)
                    {
                        for (int i = 0; i < projectDescription.length(); i++)
                        {
                            if (projectDescription.charAt(i) == syllableList[k]) {
                                syllables = syllables + 1;
                            }
                            if (lastChar(projectDescription) == 'E' || lastChar(projectDescription) == 'e') {
                                syllables = syllables - 1;
                            } else {
                                break;
                            }
                        }
                    }


                }

               return syllables;
            }).sum();

            fkcl = (206.835 - 84.6 *((numOfSyllables/numOfWords)) - (1.015 *(numOfWords/numOfSentence)));
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

        return sortedMap;
    }

    public Map<String, Integer> wordStatsGlobal(String phrase ) {

        List<String> descriptionArray = new ArrayList<>();

        Map<String, Integer> counterMap = new HashMap<>();
        Map<String, Integer> sortedMap = null;

        try {
            URL url = new URL(API + "projects/0.1/projects/active?query=\""+ phrase +"\"&limit=250&job_details=true");
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
                    String preview_description = object.get("preview_description").toString();
                    descriptionArray.add(preview_description);
                }

                for (String description: descriptionArray ) {
                    Arrays.asList(
                                    description.replaceAll("\\p{Punct}", "").split(" ")
                            )
                            .stream()
                            .forEach(word -> {
                                if(!word.equals("") && !word.equals(" ")) {
                                    if (counterMap.get(word) == null)
                                        counterMap.put(word, 1);
                                    else
                                        counterMap.put(word, counterMap.get(word) + 1);
                                }
                            });
                }

                sortedMap = counterMap
                        .entrySet()
                        .stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                        .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sortedMap;
    }

    public List<ProjectDetails> searchProjectsBySkill(String skillId)
    {
        List<ProjectDetails> array = new ArrayList<>();
        try {
            URL url = new URL(API + "projects/0.1/projects/active?jobs[]="+ Integer.parseInt(skillId) +"&limit=10&job_details=true");
           System.out.println(url);
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
                System.out.println("projects are- > "+ projects);
                for (int i = 0; i < projects.length() ; i++){
                    JSONObject object = projects.getJSONObject(i);
                    long projectID =  Long.parseLong(object.get("id").toString());
                    long ownerId =  Long.parseLong(object.get("owner_id").toString());
                    long timeSubmitted = Long.parseLong(object.get("submitdate").toString());
                    String title = object.get("title").toString() ;
                    String type = object.get("type").toString();
                    String preview_description = object.get("preview_description").toString();

                    JSONArray skills = object.getJSONArray("jobs");
                    List <List<String>> skillsList = new ArrayList<>();
                    for( int j=0; j<skills.length(); j++){
                        JSONObject skillObj = skills.getJSONObject(j);
                        List<String> skill=new ArrayList<>();
                        skill.add(skillObj.get("id").toString()+"/"+ URLEncoder.encode(skillObj.get("name").toString(), StandardCharsets.UTF_8));
                        skill.add(skillObj.get("name").toString());
                        skillsList.add(skill);

                    }
                    array.add(new ProjectDetails(projectID, ownerId, skillsList, timeSubmitted, title, type, null, preview_description));
                }
            }

        } catch (Exception e) {
        }
        return array;
    }
}

