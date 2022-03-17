package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SearchResultModel {

    private List<ProjectDetails> projectDetails;
    private double fleschReadabilityIndex;
    private double fleschKincaidGradeLevel;


    public SearchResultModel() {
        this.projectDetails = new ArrayList<>();
        this.fleschReadabilityIndex = 0.0;
        this.fleschKincaidGradeLevel = 0.0;
    }

    public SearchResultModel(List<ProjectDetails> projectDetails, double fleschReadabilityIndex, double fleschKincaidGradeLevel) {
        this.projectDetails = projectDetails;
        this.fleschReadabilityIndex = fleschReadabilityIndex;
        this.fleschKincaidGradeLevel = fleschKincaidGradeLevel;
    }


    public List<ProjectDetails> getprojectDetails() {
        return this.projectDetails;
    }

    public double getfleschReadabilityIndex() {
        return this.fleschReadabilityIndex;
    }

    public double getfleschKincaidGradeLevel() {
        return this.fleschKincaidGradeLevel;
    }

    public void setprojectDetails(List<ProjectDetails> projectDetails) {
         this.projectDetails = projectDetails;
    }

    public void setfleschReadabilityIndex(double fleschReadabilityIndex) {
         this.fleschReadabilityIndex = fleschReadabilityIndex;
    }

    public void setfleschKincaidGradeLevel(double fleschKincaidGradeLevel) {
        this.fleschKincaidGradeLevel = fleschKincaidGradeLevel;
    }

    public SearchResultModel getSearchResultModel(){
        return this;
    }
}
