package cvbuilder.utils;

import cvbuilder.model.CoreModel;
import cvbuilder.model.UserModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Utility class for parsing CSV files into UserModel and CoreModel objects
public class UserUtils {

    // Path to the CSV file
    private static final String CSV_FILE_PATH = "C:\\Users\\yasee\\OneDrive\\Documents\\NetBeansProjects\\cvbuilder\\src\\main\\java\\cvbuilder\\utils\\cv_repo_1.csv";

    // Method to parse UserModel from CSV
    public static UserModel parseUserModelFromCSV() {
        // Lists to store names, titles, and emails
        List<String> namesList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        List<String> emailList = new ArrayList<>();
        String line = "";
        try {
            // Read the CSV file line by line
            BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH));
            while ((line = reader.readLine()) != null) {
                // Split the line into parts using comma as delimiter
                String[] parts = line.split(",");
                // Check if the line corresponds to user data
                if (parts[0].equals("User")) {
                    // Check if the part corresponds to name data
                    if (parts[1].equals("Name")) {
                        // Add names to the names list
                        for (int i = 2; i < parts.length; i++) {
                            namesList.add(parts[i]);
                        }
                    }
                    // Check if the part corresponds to title data
                    if (parts[1].equals("Title")) {
                        // Add titles to the title list
                        for (int i = 2; i < parts.length; i++) {
                            titleList.add(parts[i]);
                        }
                    }
                    // Check if the part corresponds to email data
                    if (parts[1].equals("Email")) {
                        // Add emails to the email list
                        for (int i = 2; i < parts.length; i++) {
                            emailList.add(parts[i]);
                        }
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return a new instance of UserModel with the parsed data
        return new UserModel(namesList, titleList, emailList);
    }

    // Method to parse CoreModel from CSV
    public static CoreModel parseCoreModelFromCSV() {
        // Lists to store personal statements and skills
        List<String> personalStatementList = new ArrayList<>();
        List<String> skillsList = new ArrayList<>();
        String line = "";
        try {
            // Read the CSV file line by line
            BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH));
            while ((line = reader.readLine()) != null) {
                // Split the line into parts using comma as delimiter
                String[] parts = line.split(",");
                // Check if the line corresponds to core competencies data
                if (parts[0].equals("Core Competencies")) {
                    // Check if the part corresponds to skills data
                    if (parts[1].equals("Skills")) {
                        // Parse and add skills to the skills list
                        for (int k = 2; k < parts.length; k++) {
                            String[] innerParts = parts[k].split(",");
                            for (int i = 0; i < innerParts.length; i++) {
                                String[] individualSkills = innerParts[i].split("////");
                                StringBuilder stringbuild = new StringBuilder();
                                for (String individualSkill : individualSkills) {
                                    stringbuild.append(individualSkill + ",");
                                }
                                skillsList.add(stringbuild.toString());
                            }
                        }
                    }
                    // Check if the part corresponds to profile statement data
                    if (parts[1].equals("Profile Statement")) {
                        // Parse and add personal statements to the personal statement list
                        for (int k = 2; k < parts.length; k++) {
                            String[] innerParts = parts[k].split(",");
                            for (int i = 0; i < innerParts.length; i++) {
                                StringBuilder stringBuilder = new StringBuilder(innerParts[i]);
                                String substringToRemove = "////";
                                int index;
                                while ((index = stringBuilder.indexOf(substringToRemove)) != -1) {
                                    stringBuilder.replace(index, index + substringToRemove.length(), " ");
                                }
                                personalStatementList.add(stringBuilder.toString());
                            }
                        }
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return a new instance of CoreModel with the parsed data
        return new CoreModel(personalStatementList, skillsList);
    }
}
