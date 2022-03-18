package services;

import helper.Session;
import models.ProjectDetails;
import org.json.JSONArray;
import org.json.JSONObject;
import play.libs.ws.*;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CompletionStage;

import static java.util.stream.Collectors.toMap;


public class FreeLancerServices implements WSBodyReadables, WSBodyWritables {

    private WSClient wsClient;
    private Session session;

    public FreeLancerServices() {
        this.session = new Session();
    }

    public void setWsClient(WSClient wsClient)
    {
        this.wsClient=wsClient;
    }
    public WSClient getWsClient()
    {
        return this.wsClient;
    }

    String API = "https://www.freelancer.com/api/";
    static Scanner sc = new Scanner(System.in);


    public CompletionStage<WSResponse> searchResults(String phrase) {
            CompletionStage<WSResponse> wsResponseCompletionStage = null;
            WSRequest request = null;
            try {
                request = wsClient.url(API+"projects/0.1/projects/active?query=\""+ URLEncoder.encode(phrase, String.valueOf(StandardCharsets.UTF_8))+"\"&limit=250&job_details=true");

                wsResponseCompletionStage = request.stream();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return wsResponseCompletionStage;
    }


    public static Map<String, Integer> wordStatsIndevidual(String description ) {
        Map<String, Integer> counterMap = new HashMap<>();
        Map<String, Integer> sortedMap = null;

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

        return sortedMap;
    }

    public static Map<String, Integer> wordStatsGlobal(List<ProjectDetails> results) {

        Map<String, Integer> counterMap = new HashMap<>();
        Map<String, Integer> sortedMap = null;

        for (ProjectDetails project: results ) {
            Arrays.asList(
                            project.getPreviewDescription().replaceAll("\\p{Punct}", "").split(" ")
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

        return sortedMap;
    }

    public List<ProjectDetails> searchProjectsBySkill(String skillId)
    {
        List<ProjectDetails> array = new ArrayList<>();
        try {
            URL url = new URL(API + "projects/0.1/projects/active?jobs[]="+ Integer.parseInt(skillId) +"&limit=10&job_details=true");
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
                        skill.add(skillObj.get("id").toString()+"/"+ URLEncoder.encode(skillObj.get("name").toString(), String.valueOf(StandardCharsets.UTF_8)));
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

    public OptionalDouble readabilityIndex(String phrase, List<ProjectDetails> searchResults) {

        OptionalDouble searchResultUpadted = searchResults.stream().mapToDouble(project -> {
            double fkcl = 0;
            int numOfSentence = 0;
            int numOfWords = 0;
            int numOfSyllables = 0;

            String projectDescription = project.getPreviewDescription();
            numOfWords = projectDescription.trim().split("\\s+").length;
            numOfSentence = projectDescription.trim().split("([.!?:;])([\\s\\n])([A-Z]*)").length;

            numOfSyllables = Arrays.stream(projectDescription.trim().split("\\s+")).mapToInt(word -> {

                int syllables = 0;

                String vowels = "aeiouy";

                word = word.toLowerCase();
                char[] alphabets = word.toCharArray();

                if (alphabets.length <= 3)
                    return 1;

                for (char chr : alphabets) {
                    if (vowels.indexOf(chr) != -1) {
                        syllables++;
                    }
                }

                for (int i = 0; i < word.length() - 1; i++) {
                    if (vowels.indexOf(alphabets[i]) != -1 && vowels.indexOf(alphabets[i + 1]) != -1)
                        syllables--;
                }

                if (alphabets[alphabets.length - 1] == 'e') {
                    syllables--;
                }

                if ((alphabets[alphabets.length - 2] == 'e') && (alphabets[alphabets.length - 1] == 's' || alphabets[alphabets.length - 1] == 'd')) {
                    syllables--;
                }

                if ((alphabets[alphabets.length - 1] == 'e') && (alphabets[alphabets.length - 2] == 'l')) {
                    syllables++;
                }
                return syllables;

            }).sum();

            double alpha = numOfSyllables/numOfWords;
            double beta = numOfWords/numOfSentence;

            fkcl = 206.835 - (1.015*beta) - (84.6*alpha);

            project.setFleschReadabilityIndex(Math.round(fkcl));
            project.setReadability(fkcl);

            return Math.round(fkcl);

        }).average();

        return searchResultUpadted;
    }

    public OptionalDouble fleschKancidGradeLevvel(String phrase, List<ProjectDetails> searchResults) {

        OptionalDouble searchResultUpadted = searchResults.stream().mapToDouble(project -> {
            double fkgl = 0;
            int numOfSentence = 0;
            int numOfWords = 0;
            int numOfSyllables = 0;

            String projectDescription = project.getPreviewDescription();
            numOfWords = projectDescription.trim().split("\\s+").length;
            numOfSentence = projectDescription.trim().split("([.!?:;])([\\s\\n])([A-Z]*)").length;

            numOfSyllables = Arrays.stream(projectDescription.trim().split("\\s+")).mapToInt(word -> {

                int syllables = 0;

                String vowels = "aeiouy";

                word = word.toLowerCase();
                char[] alphabets = word.toCharArray();

                if (alphabets.length <= 3)
                    return 1;

                for (char chr : alphabets) {
                    if (vowels.indexOf(chr) != -1) {
                        syllables++;
                    }
                }

                for (int i = 0; i < word.length() - 1; i++) {
                    if (vowels.indexOf(alphabets[i]) != -1 && vowels.indexOf(alphabets[i + 1]) != -1)
                        syllables--;
                }

                if (alphabets[alphabets.length - 1] == 'e') {
                    syllables--;
                }

                if ((alphabets[alphabets.length - 2] == 'e') && (alphabets[alphabets.length - 1] == 's' || alphabets[alphabets.length - 1] == 'd')) {
                    syllables--;
                }

                if ((alphabets[alphabets.length - 1] == 'e') && (alphabets[alphabets.length - 2] == 'l')) {
                    syllables++;
                }
                return syllables;

            }).sum();

            double alpha = numOfSyllables/numOfWords;
            double beta = numOfWords/numOfSentence;

            fkgl = 0.39 * (beta) + 11.8 * (alpha) - 15.59;


            project.setFleschKincaidGradeLevel(Math.round(fkgl));

            return Math.round(fkgl);

        }).average();

        return searchResultUpadted;
    }
}

