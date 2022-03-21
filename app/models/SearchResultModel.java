package models;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the model class for displaying search results on the Home page
 * @author Nipun Hedaoo
 * @author Alankrit Gupta
 * @author Pragya Tomar
 * @author Jasleen Kaur
 *
 */
public class SearchResultModel {

    private List<ProjectDetails> projectDetails;
    private double fleschReadabilityIndex;
    private double fleschKincaidGradeLevel;




    public SearchResultModel() {
        this.projectDetails = new ArrayList<>();
        this.fleschReadabilityIndex = 0.0;
        this.fleschKincaidGradeLevel = 0.0;
    }

    /**
     * Parametrized Constructor with projectDetails, fleschReadabilityIndex and fleschKincaidGradeLevel
     * @param projectDetails List of project details
     * @param fleschReadabilityIndex Flesch Readability Index
     * @param fleschKincaidGradeLevel Flesch–Kincaid grade level
     */
    public SearchResultModel(List<ProjectDetails> projectDetails, double fleschReadabilityIndex, double fleschKincaidGradeLevel) {
        this.projectDetails = projectDetails;
        this.fleschReadabilityIndex = fleschReadabilityIndex;
        this.fleschKincaidGradeLevel = fleschKincaidGradeLevel;
    }


    /**
     * This method returns the list of projects.
     * @return list Returns the list of projects.
     */
    public List<ProjectDetails> getprojectDetails() {
        return this.projectDetails;
    }

    /**
     * This method returns the flesch Readability Index.
     * @return double Returns the value of flesch Readability Index.
     */
    public double getfleschReadabilityIndex() {
        return this.fleschReadabilityIndex;
    }

    /**
     * This method returns the flesch Kincaid Grade Level.
     * @return double Returns the value of flesch Kincaid Grade Level.
     */
    public double getfleschKincaidGradeLevel() {
        return this.fleschKincaidGradeLevel;
    }

    /**
     * This method is used to set the project Details
     * @param projectDetails
     */
    public void setprojectDetails(List<ProjectDetails> projectDetails) {
         this.projectDetails = projectDetails;
    }

    /**
     * This method is used to set the Flesch Readability Index
     * @param fleschReadabilityIndex
     */
    public void setfleschReadabilityIndex(double fleschReadabilityIndex) {
         this.fleschReadabilityIndex = fleschReadabilityIndex;
    }

    /**
     * This method is used to set the Flesch Kincaid Grade Level
     * @param fleschKincaidGradeLevel
     */
    public void setfleschKincaidGradeLevel(double fleschKincaidGradeLevel) {
        this.fleschKincaidGradeLevel = fleschKincaidGradeLevel;
    }

    /**
     *
     * @return
     */
    public SearchResultModel getSearchResultModel(){
        return this;
    }
}
