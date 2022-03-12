package services;

import models.ProjectDetails;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import static java.util.stream.Collectors.toMap;

public class FreeLancerServices {

    String API = "https://www.freelancer.com/api/";
    static Scanner sc = new Scanner(System.in);

    public List<ProjectDetails> searchResults(String phrase)
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

                    long projectID =  Long.parseLong(object.get("id").toString());
                    long ownerId =  Long.parseLong(object.get("owner_id").toString());
                    long timeSubmitted = Long.parseLong(object.get("submitdate").toString());
                    String title = object.get("title").toString() ;
                    String type = object.get("type").toString();
                    String preview_description = object.get("preview_description").toString();

                    Map<String, Integer> wordStats = wordStatsIndevidual(object.get("preview_description").toString());

                    JSONArray skills = object.getJSONArray("jobs");
                    List <String> skillsList = new ArrayList<>();
                    for( int j=0; j<skills.length(); j++){
                        JSONObject skillObj = skills.getJSONObject(j);
                        skillsList.add(skillObj.get("name").toString());
                    }
                    array.add(new ProjectDetails(projectID, ownerId, skillsList, timeSubmitted, title, type, wordStats, preview_description));
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

        return sortedMap;
    }

}

