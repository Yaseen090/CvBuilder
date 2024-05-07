package cvbuilder.model;

import java.util.ArrayList;
import java.util.List;

// Class representing the core model of the application
public class CoreModel {

    // Member variables
    private List<String> personalStatementList = new ArrayList<>();
    private List<String> skillsList = new ArrayList<>();

    // Constructor
    public CoreModel(List<String> personalStatementList, List<String> skillsList) {
        // Initialize personal statement and skills lists
        this.personalStatementList = personalStatementList;
        this.skillsList = skillsList;
    }

    // Getter for personal statement list
    public List<String> getPersonalStatementList() {
        return personalStatementList;
    }

    // Setter for personal statement list
    public void setPersonalStatementList(List<String> personalStatementList) {
        this.personalStatementList = personalStatementList;
    }

    // Getter for skills list
    public List<String> getSkillsList() {
        return skillsList;
    }

    // Setter for skills list
    public void setSkillsList(List<String> skillsList) {
        this.skillsList = skillsList;
    }
}
