package models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SearchResultModelTest {

    private List<ProjectDetails> list = new ArrayList<>();
    private double fleschReadabilityIndex = 10.0;
    private double fleschKincaidGradeLevel = 10.0;

    private SearchResultModel searchResultDefualt = new SearchResultModel();

    private SearchResultModel searchResultParams = new SearchResultModel( list, fleschReadabilityIndex, fleschKincaidGradeLevel);

    @Test
    public void getprojectDetails(){
        assertEquals(list.toString(),searchResultParams.getprojectDetails().toString());
    }

    @Test
    public void getfleschReadabilityIndex(){
        assertEquals(0, Double.compare(fleschReadabilityIndex,searchResultParams.getfleschReadabilityIndex()));
    }

    @Test
    public void getfleschKincaidGradeLevel(){
        assertEquals(0, Double.compare(fleschKincaidGradeLevel,searchResultParams.getfleschKincaidGradeLevel()));
    }

    @Test
    public void setprojectDetails(){
        searchResultParams.setprojectDetails(new ArrayList<>());
    }

    @Test
    public void setfleschReadabilityIndex(){
        searchResultParams.setfleschReadabilityIndex(fleschReadabilityIndex);
    }

    @Test
    public void setfleschKincaidGradeLevel(){
        searchResultParams.setfleschKincaidGradeLevel(fleschKincaidGradeLevel);
    }

    @Test
    public void getSearchResultModel(){
        assertEquals(searchResultDefualt.toString(), searchResultDefualt.getSearchResultModel().toString());
    }
}
