package services;

import models.ProjectDetails;
import models.EmployerDetails;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.title;
public class FreeLancerServices {

    String API = "https://www.freelancer.com/api/";
    static Scanner sc = new Scanner(System.in);

    public List<ProjectDetails> searchResults(String phrase) {
        List<ProjectDetails> array = new ArrayList<>();
        try {
            phrase = phrase.replace(" ", "%20");
            URL url = new URL(API + "projects/0.1/projects/active?query=\"" + phrase + "\"&limit=10&job_details=true");
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

                    long ownerId = Long.parseLong(object.get("owner_id").toString());
                    long timeSubmitted = Long.parseLong(object.get("submitdate").toString());
                    String title = object.get("title").toString();
                    String type = object.get("type").toString();

                    JSONArray skills = object.getJSONArray("jobs");
                    List<List<String>> skillsList = new ArrayList<>();
                    for (int j = 0; j < skills.length(); j++) {
                        JSONObject skillObj = skills.getJSONObject(j);
                        List<String> skill = new ArrayList<>();
                        skill.add("/?jobid=" + skillObj.get("id").toString());
                        skill.add(skillObj.get("name").toString());
                        skillsList.add(skill);

                    }
                    array.add(new ProjectDetails(ownerId, skillsList, timeSubmitted, title, type));
                }
            }

        } catch (Exception e) {
        }
        return array;
    }


    public List<ProjectDetails> searchProjectsBySkill(String skillId) {
        List<ProjectDetails> array = new ArrayList<>();
        try {
            skillId = skillId.replace(" ", "%20");
            URL url = new URL(API + "projects/0.1/projects/active?query=\"" + skillId + "\"&limit=10&job_details=true");
            System.out.println(url);
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

                System.out.println("projects are- > " + projects);
                for (int i = 0; i < projects.length(); i++) {
                    JSONObject object = projects.getJSONObject(i);

                    long ownerId = Long.parseLong(object.get("owner_id").toString());
                    long timeSubmitted = Long.parseLong(object.get("submitDate").toString());
                    String title = object.get("title").toString();
                    String type = object.get("type").toString();

                    JSONArray skills = object.getJSONArray("jobs");
                    List<List<String>> skillsList = new ArrayList<>();
                    for (int j = 0; j < skills.length(); j++) {
                        JSONObject skillObj = skills.getJSONObject(j);
                        List<String> skill = new ArrayList<>();
                        skill.add("/?jobid=" + skillObj.get("id").toString());
                        skill.add(skillObj.get("name").toString());
                        skillsList.add(skill);

                    }
                    array.add(new ProjectDetails(ownerId, skillsList, timeSubmitted, title, type));
                }
            }

        } catch (Exception e) {
        }
        return array;
    }

    public List<EmployerDetails> employerResults (Long ownerID){
        List<EmployerDetails> array = new ArrayList<>();
        try {
            URL url = new URL(API + "users/0.1/users/" + ownerID );
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

                    String username = result.get("username").toString();
                    String email = result.get("email").toString();
                    String primaryLanguage = result.get("primary_language").toString();
                    String profileDescription = result.get("profile_description").toString();
                    long registrationDate = Long.parseLong(result.get("registration_date").toString());
                    Double hourlyRate= Double.valueOf(result.get("hourly_rate").toString());
                    List<ProjectDetails>employer_projects=getProjects(ownerID);
                    array.add(new EmployerDetails(username, email, primaryLanguage, profileDescription,registrationDate,hourlyRate,employer_projects));

            }


        } catch (Exception e) {
        }
        return array;
    }
                        public List<ProjectDetails> getProjects (Long ownerID){
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
                                    String title = object.get("title").toString();


                                    array2.add(new ProjectDetails(title));
                                }
                            }

                        } catch (Exception e) {
                        }
                        return array2;
                    }

                }


