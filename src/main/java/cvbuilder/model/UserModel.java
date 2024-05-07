package cvbuilder.model;

import java.util.ArrayList;
import java.util.List;

// Class representing the user model of the application
public class UserModel {

    // Member variables
    private List<String> namesList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private List<String> emailList = new ArrayList<>();

    // Constructor
    public UserModel(List<String> namesList, List<String> titleList, List<String> emailList) {
        // Initialize names, title, and email lists
        this.namesList = namesList;
        this.titleList = titleList;
        this.emailList = emailList;
    }

    // Getter for names list
    public List<String> getNamesList() {
        return namesList;
    }

    // Setter for names list
    public void setNamesList(List<String> namesList) {
        this.namesList = namesList;
    }

    // Getter for title list
    public List<String> getTitleList() {
        return titleList;
    }

    // Setter for title list
    public void setTitleList(List<String> titleList) {
        this.titleList = titleList;
    }

    // Getter for email list
    public List<String> getEmailList() {
        return emailList;
    }

    // Setter for email list
    public void setEmailList(List<String> emailList) {
        this.emailList = emailList;
    }
}
