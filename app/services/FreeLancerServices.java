package services;

import helper.Session;
import models.EmployerDetails;
import models.ProjectDetails;
import org.json.JSONArray;
import org.json.JSONException;
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


/**
 * The FreeLancerServices class is used for fetching the project details
 */
public class FreeLancerServices implements WSBodyReadables, WSBodyWritables {

    private WSClient wsClient;
    private Session session;

    public FreeLancerServices() {
        this.session = new Session();
    }

    public void setWsClient(WSClient wsClient) {
        this.wsClient = wsClient;
    }

    public WSClient getWsClient() {
        return this.wsClient;
    }

    String API = "https://www.freelancer.com/api/";
    static Scanner sc = new Scanner(System.in);


    public CompletionStage<WSResponse> searchResults(String phrase) {
        CompletionStage<WSResponse> wsResponseCompletionStage = null;
        WSRequest request = null;
        try {
            request = wsClient.url(API + "projects/0.1/projects/active?query=\"" + URLEncoder.encode(phrase, String.valueOf(StandardCharsets.UTF_8)) + "\"&limit=250&job_details=true");

            wsResponseCompletionStage = request.stream();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return wsResponseCompletionStage;
    }

    public static Map<String, Integer> wordStatsIndevidual(String description) {
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

        for (ProjectDetails project : results) {
            Arrays.asList(
                            project.getPreviewDescription().replaceAll("\\p{Punct}", "").split(" ")
                    )
                    .stream()
                    .forEach(word -> {
                        if (!word.equals("") && !word.equals(" ")) {
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

    /**
     * <p>With this function API call is made to fetch the projects associated with skill</p>
     * @param skillId It represents the skillId associated with the skill
     * @return It returns the API response for active jobs
     * @author Jasleen Kaur
     */
    public CompletionStage<WSResponse> searchSkillResults(String skillId) {
        CompletionStage<WSResponse> wsResponseCompletionStage = null;
        WSRequest request = null;
        request = wsClient.url(API + "projects/0.1/projects/active?jobs[]=" + Integer.parseInt(skillId) + "&limit=10&job_details=true");
        wsResponseCompletionStage = request.stream();
        return wsResponseCompletionStage;
    }

    /**
     * <p>With this function all the lastest projects associated with skill can be fetched.</p>
     * @param res It represents API response from function searchSkillResults()
     * @return It returns list of maximum 10 projects associated with the skill.
     * @author Jasleen Kaur
     */
    public List<ProjectDetails> searchProjectsBySkill(WSResponse res) throws JSONException {
        List<ProjectDetails> array = new ArrayList<>();
        try {
                System.out.println("Response + " + res);
               JSONObject json = new JSONObject(res.getBody());

            return searchSkillProjectsJson(json);
            }
        catch (Exception e) {
        }
  return array;
    }


    public List<ProjectDetails> searchSkillProjectsJson(JSONObject json) throws JSONException {
        List<ProjectDetails> array = new ArrayList<>();
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
                try {
                    skill.add(skillObj.get("id").toString()+"/"+ URLEncoder.encode(skillObj.get("name").toString(), String.valueOf(StandardCharsets.UTF_8)));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                skill.add(skillObj.get("name").toString());
                skillsList.add(skill);

            }
            array.add(new ProjectDetails(projectID, ownerId, skillsList, timeSubmitted, title, type, null, preview_description, 0.0, 0.0, "Early" ));
        }
        return array;
    }


    public List<ProjectDetails> searchModelByKeyWord(WSResponse res) throws JSONException {
        List<ProjectDetails> array =new ArrayList<>();
        try {
            JSONObject json = new JSONObject(res.getBody());
            array= searchModelByKeywordJson(json);
        }
        catch (Exception e) {
        }
  return array;
    }



    public List<ProjectDetails>  searchModelByKeywordJson(JSONObject json) throws JSONException {
        List<ProjectDetails> array = new ArrayList<>();
        List<String> descriptionArray = new ArrayList<>();
        JSONObject result = json.getJSONObject("result");
        JSONArray projects = (JSONArray) result.getJSONArray("projects");

        for (int i = 0; i < projects.length(); i++) {
            JSONObject object = projects.getJSONObject(i);

            long projectID = Long.parseLong(object.get("id").toString());
            long ownerId = Long.parseLong(object.get("owner_id").toString());
            long timeSubmitted = Long.parseLong(object.get("submitdate").toString());
            String title = object.get("title").toString();
            String type = object.get("type").toString();
            String preview_description = object.get("preview_description").toString();
            descriptionArray.add(preview_description);

            Map<String, Integer> wordStats = wordStatsIndevidual(object.get("preview_description").toString());


            JSONArray skills = object.getJSONArray("jobs");
            List<List<String>> skillsList = new ArrayList<>();
            for (int j = 0; j < skills.length(); j++) {
                JSONObject skillObj = skills.getJSONObject(j);
                List<String> skill = new ArrayList<>();
                try {
                    skill.add(skillObj.get("id").toString() + "/" + URLEncoder.encode(skillObj.get("name").toString(), String.valueOf(StandardCharsets.UTF_8)));
                } catch (UnsupportedEncodingException e) {

                }
                skill.add(skillObj.get("name").toString());
                skillsList.add(skill);

            }
            array.add(new ProjectDetails(projectID, ownerId, skillsList, timeSubmitted, title, type, wordStats, preview_description,0.0, 0.0, "Early"));
        }

        return array;
    }
    public List<EmployerDetails> employerResults(String ownerID) {
        List<EmployerDetails> array = new ArrayList<>();
        try {
            URL url = new URL(API + "users/0.1/users/" + ownerID);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == 200) {
                Scanner scan = new Scanner(url.openStream());
                String temp = "";
                while (scan.hasNext()) {
                    temp = temp + scan.nextLine();
                }
                JSONObject json = new JSONObject(temp);
                JSONObject result = json.getJSONObject("result");
                JSONObject location = result.getJSONObject("location");
                JSONObject country = location.getJSONObject("country");
                JSONObject status = result.getJSONObject("status");
                JSONObject currency = result.getJSONObject("primary_currency");

                String id = result.get("id").toString();
                String username = result.get("username").toString();
                String registrationDate = result.get("registration_date").toString();
                String limitedAccount = result.get("limited_account").toString();
                String displayName = result.get("display_name").toString();
                String countryName = country.get("name").toString();
                String role = result.get("role").toString();
                String chosenRole = result.get("chosen_role").toString();
                String emailVerified = status.get("email_verified").toString();
                String primaryCurrency = currency.get("name").toString();
                List<ProjectDetails> employer_projects = getProjects(ownerID);


                array.add(new EmployerDetails(id, username, registrationDate, limitedAccount, displayName, countryName, role, chosenRole, emailVerified, primaryCurrency, employer_projects));

            }


        } catch (Exception e) {
        }
        return array;
    }

    public List<ProjectDetails> getProjects(String ownerID) {
        List<ProjectDetails> array2 = new ArrayList<>();
        try {
            URL url = new URL(API + "projects/0.1/projects/?owners[]=" + ownerID + "&limit=10&job_details=true");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == 200) {
                Scanner scan = new Scanner(url.openStream());
                String temp = "";
                while (scan.hasNext()) {
                    temp = temp + scan.nextLine();
                }
                JSONObject json = new JSONObject(temp);
                JSONObject result = json.getJSONObject("result");
                JSONArray projects = (JSONArray) result.getJSONArray("projects");

                for (int i = 0; i < projects.length(); i++) {
                    JSONObject object = projects.getJSONObject(i);

                    long projectID = Long.parseLong(object.get("id").toString());
                    long ownerId = Long.parseLong(object.get("owner_id").toString());
                    long timeSubmitted = Long.parseLong(object.get("submitdate").toString());
                    String title = object.get("title").toString();
                    String type = object.get("type").toString();
                    String preview_description = object.get("preview_description").toString();


                    JSONArray skills = object.getJSONArray("jobs");
                    List<List<String>> skillsList = new ArrayList<>();
                    for (int j = 0; j < skills.length(); j++) {
                        JSONObject skillObj = skills.getJSONObject(j);
                        List<String> skill = new ArrayList<>();
                        skill.add(skillObj.get("id").toString() + "/" + URLEncoder.encode(skillObj.get("name").toString(), String.valueOf(StandardCharsets.UTF_8)));
                        skill.add(skillObj.get("name").toString());
                        skillsList.add(skill);

                    }
                    array2.add(new ProjectDetails(projectID, ownerId, skillsList, timeSubmitted, title, type, null, preview_description, 0.0, 0.0, "Early"));
                }
            }

        } catch (Exception e) {
        }
        return array2;
    }

    /**
     * <p>This function calculates the Flesch Readability Index</p>
     *
     * @param searchResults It represents the list of projects associated with the search keyword
     * @return It updates the Flesch Readability Index for each project and returns average Flesch Readability Index of project description's.
     * @author Nipun Hedaoo
     */
    public OptionalDouble readabilityIndex(List<ProjectDetails> searchResults) {

        OptionalDouble searchResultUpadted = searchResults.stream().mapToDouble(project -> {
            double fkcl = 0;
            int numOfSentence = 0;
            int numOfWords = 0;
            int numOfSyllables = 0;

            String projectDescription = project.getPreviewDescription();
            numOfWords = getNumOfWords(projectDescription);
            numOfSentence = getNumOfSentences(projectDescription);

            numOfSyllables = getNnumOfSyllables(projectDescription);

            fkcl = calculateFRI(numOfSentence, numOfWords, numOfSyllables);

            project.setFleschReadabilityIndex(Math.round(fkcl));
            project.setReadability(Math.round(fkcl));

            return Math.round(fkcl);

        }).average();

        return searchResultUpadted;
    }

    /**
     * <p>This function calculates the Flesch–Kincaid grade level  </p>
     *
     * @param searchResults It represents the list of projects associated with the search keyword
     * @return It updates the Flesch–Kincaid grade level   for each project and returns average Flesch–Kincaid grade level of project description's.
     * @author Nipun Hedaoo
     */
    public OptionalDouble fleschKancidGradeLevvel(List<ProjectDetails> searchResults) {
        OptionalDouble searchResultUpadted = searchResults.stream().mapToDouble(project -> {
            double fkgl = 0;
            int numOfSentence = 0;
            int numOfWords = 0;
            int numOfSyllables = 0;

            String projectDescription = project.getPreviewDescription();
            numOfWords = getNumOfWords(projectDescription);
            numOfSentence = getNumOfSentences(projectDescription);
            numOfSyllables = getNnumOfSyllables((projectDescription));

            fkgl = calculateFKGL(numOfSentence, numOfWords, numOfSyllables);

            project.setFleschKincaidGradeLevel(Math.round(fkgl));

            return Math.round(fkgl);

        }).average();

        return searchResultUpadted;
    }

    public double calculateFRI(int numOfSentence, int numOfWords, int numOfSyllables) {
        double fkcl = 0.0;

        fkcl = 206.835 - 1.015 * ( numOfWords / numOfSentence) - 84.6 * ( numOfSyllables / numOfWords );
        return fkcl;
    }

    public double calculateFKGL(int numOfSentence, int numOfWords, int numOfSyllables) {
        double fkgl = 0.0;
        fkgl = (0.39 * ( numOfWords / numOfSentence )) + 11.8 * ( numOfSyllables / numOfWords ) - 15.59;
        return fkgl;
    }

    public int getNumOfWords(String projectDescription) {
        int word = 0;
        word = projectDescription.trim().split("\\s+").length;
        return word;
    }

    public int getNumOfSentences(String projectDescription) {
        return projectDescription.trim().split("([.!?:;])([\\s\\n])([A-Z]*)").length;
    }

    public int getNnumOfSyllables(String projectDescription) {
        int numOfSyllables = 0;

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

            if ((alphabets[alphabets.length - 2] == 'l') && (alphabets[alphabets.length - 1] == 'e')) {
                syllables++;
            }
            return syllables;
        }).sum();

        return numOfSyllables;
    }

}