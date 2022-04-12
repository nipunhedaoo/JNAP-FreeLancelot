package services;

import actors.WordStatsIndividualActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import akka.stream.Materializer;
import akka.util.Timeout;
import helper.Session;
import models.EmployerDetails;
import models.ProjectDetails;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import play.libs.ws.WSBodyReadables;
import play.libs.ws.WSBodyWritables;
import play.libs.ws.WSClient;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeoutException;

import static java.util.stream.Collectors.toMap;

/**
 * The FreeLancerServices class is used for fetching the project details
 *   @author Nipun Hedaoo
 *   @author Alankrit Gupta
 *   @author Jasleen Kaur
 *   @author Pragya Tomar
 */
public class FreeLancerServices implements WSBodyReadables, WSBodyWritables {

    final ActorRef wordstatsIndividualActor;

    private final ActorSystem actorSystem;
    private final Materializer materializer;

    private static WSClient wsClient;
    private Session session;

    public FreeLancerServices(ActorSystem actorSystem, Materializer materializer) {
        this.session = new Session();
        this.actorSystem = actorSystem;
        this.materializer = materializer;
      wordstatsIndividualActor = actorSystem.actorOf(WordStatsIndividualActor.getProps());
    }

    public void setWsClient(WSClient wsClient) {
        this.wsClient = wsClient;
    }

    public WSClient getWsClient() {
        return this.wsClient;
    }

    static String API = "https://www.freelancer.com/api/";
    static Scanner sc = new Scanner(System.in);

    /**
     * <p>With this function API call is made for main page search results</p>
     *
     * @param phrase It represents the search phrase
     * @return It returns the API response for active projects
     * @author Nipun Hedaoo
     * @author Alankrit Gupta
     */

    public static Object searchResults(String phrase) throws  JSONException, IOException {
        JSONObject json=null;
        try {
            String temp="";

            URL url = new URL(API + "projects/0.1/projects/active?query=\"" + URLEncoder.encode(phrase, String.valueOf(StandardCharsets.UTF_8)) + "\"&limit=250&job_details=true");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == 200) {
                Scanner scan = new Scanner(url.openStream());
                while (scan.hasNext()) {
                    temp = temp + scan.nextLine();
                }
            }
                 json = new JSONObject(temp);
        } catch (UnsupportedEncodingException e) {

            System.out.println("Error is " + e);
            e.printStackTrace();
        }

        return json;
    }

    /**
     * <p>With this function word stats for individual projects are calculated</p>
     *
     * @param description It represents escription of each project
     * @return It returns the hashmap of wordstats
     * @author Alankrit Gupta
     */
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

    /**
     * <p>With this function global word stats for projects are calculated</p>
     *
     * @param results It represents list of projects
     * @return It returns the hashmap of wordstats
     * @author Alankrit Gupta
     */
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
     *
     * @param skillId It represents the skillId associated with the skill
     * @return It returns the API response for active jobs
     * @author Jasleen Kaur
     */
    public static Object searchSkillResults(String skillId) {
    JSONObject json=null;

        try {
            String temp="";
            URL url = new URL(API + "projects/0.1/projects/active?jobs[]=" + Integer.parseInt(skillId) + "&limit=10&job_details=true");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == 200) {
                Scanner scan = new Scanner(url.openStream());
                while (scan.hasNext()) {
                    temp = temp + scan.nextLine();
                }
            }
            json = new JSONObject(temp);
        } catch (Exception e) {

            System.out.println("Error is " + e);
            e.printStackTrace();
        }

        return json;

    }

    /**
     * <p>With this function all the lastest projects associated with skill can be fetched.</p>
     *
     * @param res It represents API response from function searchSkillResults()
     * @return It returns list of maximum 10 projects associated with the skill.
     * @author Jasleen Kaur
     */
    public List<ProjectDetails> searchProjectsBySkill(JSONObject res) throws JSONException {
        List<ProjectDetails> array = new ArrayList<>();
        try {
                System.out.println("Response + " + res);

            return searchSkillProjectsJson(res);
            }
        catch (Exception e) {
        }
  return array;
    }

    /**
     * <p>With this function all the lastest projects associated with skills are parsed.</p>
     *
     * @param json It represents API response in JSON format for computation
     * @return It returns list of maximum 10 projects associated with the skill.
     * @author Jasleen Kaur
     */

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

    /**
     * <p>With this function all the lastest projects associated can be fetched.</p>
     *
     * @param res It represents API response for search keywords
     * @return It returns list of maximum 10 projects associated with the skill.
     * @author Nipun Hedaoo
     */
    public List<ProjectDetails> searchModelByKeyWord(JSONObject res) throws JSONException {
        System.out.println("Inside search model1");
        List<ProjectDetails> array =new ArrayList<>();
        try {
//            JSONObject json = new JSONObject(res.getBody());
//            System.out.println("Inside search model"+ json.toString());
            array= searchModelByKeywordJson(res);
        }
        catch (Exception e) {
        }
  return array;
    }

    /**
     * <p>With this function all the lastest projects are parsed</p>
     *
     * @param json It represents API response in JSON format for computation
     * @return It returns list of maximum 10 projects associated with the skill.
     * @author Alankrit Gupta
     */

    public List<ProjectDetails>  searchModelByKeywordJson(JSONObject json) throws JSONException, InterruptedException, TimeoutException {
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

            Timeout timeout =  new Timeout(Duration.create(5, "seconds"));
            Future<Object> future = Patterns.ask(wordstatsIndividualActor, object.get("preview_description").toString(), timeout);
            Map<String, Integer> wordStats = (Map<String, Integer>) Await.result(future, timeout.duration());

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

    /**
     * <p>With this function all the lastest employer results are returned</p>
     *
     * @param ownerID It represents the owner id of the employer
     * @return It returns employer details associated with the ownerId.
     * @author Pragya Tomar
     */
    public static Object employerDetails(String ownerID) {
        JSONObject jsonObject = null;
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
                jsonObject = new JSONObject(temp);
            }
        } catch (Exception e) {

        }
        return jsonObject;
    }


    public static List<EmployerDetails> employerResults(String ownerID, JSONObject json1) throws JSONException {
        List<EmployerDetails> array = new ArrayList<>();
        JSONObject result = json1.getJSONObject("result");
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


        return array;
    }
    /**
     * <p>With this function API call for fetching employer projects is made</p>
     *
     * @param ownerID It represents the owner id of the employer
     * @return It returns list of latest 10 projects associated with the ownerId.
     * @author Pragya Tomar
     */
    public static List<ProjectDetails> getProjects(String ownerID) {
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
    public static Double readabilityIndex(List<ProjectDetails> searchResults) {
            double searchResultUpadted = 0.0;
            try {
                 searchResultUpadted = searchResults.stream().mapToDouble(project -> {
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

                }).average().getAsDouble();


            }catch(Exception e){

            }

            return searchResultUpadted;
    }


    /**
     * <p>This function calculates the Flesch–Kincaid grade level  </p>
     *
     * @param searchResults It represents the list of projects associated with the search keyword
     * @return It updates the Flesch–Kincaid grade level   for each project and returns average Flesch–Kincaid grade level of project description's.
     * @author Nipun Hedaoo
     */
    public static Double fleschKancidGradeLevvel(List<ProjectDetails> searchResults) {
        Double searchResultUpadted = searchResults.stream().mapToDouble(project -> {
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

        }).average().getAsDouble();

        return searchResultUpadted;
    }

    public static double calculateFRI(int numOfSentence, int numOfWords, int numOfSyllables) {
        double fkcl = 0.0;

        fkcl = 206.835 - 1.015 * ( numOfWords / numOfSentence) - 84.6 * ( numOfSyllables / numOfWords );
        return fkcl;
    }

    public static double calculateFKGL(int numOfSentence, int numOfWords, int numOfSyllables) {
        double fkgl = 0.0;
        fkgl = (0.39 * ( numOfWords / numOfSentence )) + 11.8 * ( numOfSyllables / numOfWords ) - 15.59;
        return fkgl;
    }

    public static int getNumOfWords(String projectDescription) {
        int word = 0;
        word = projectDescription.trim().split("\\s+").length;
        return word;
    }

    public static int getNumOfSentences(String projectDescription) {
        return projectDescription.trim().split("([.!?:;])([\\s\\n])([A-Z]*)").length;
    }

    public static int getNnumOfSyllables(String projectDescription) {
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