/*
 * This class represents the control layer of the application.
 * It handles the main functionality of the CV Builder.
 */

// Import statements
package cvbuilder.control;

import cvbuilder.model.CoreModel;
import cvbuilder.model.UserModel;
import cvbuilder.utils.UserUtils;
import cvbuilder.view.UserView;
import java.awt.event.*;
import javax.swing.JFrame;

public class UserControl {

    // Member variables
    private static UserView userView;
    private static UserModel userModel;
    private static CoreModel coreModel;

    // Main method
    public static void main(String[] args) {
        // Load user and core models from CSV files
        userModel = UserUtils.parseUserModelFromCSV();
        coreModel = UserUtils.parseCoreModelFromCSV();
        
        // Initialize user interface
        initializeUserInterface();
    }

    // Method to initialize user interface
    private static void initializeUserInterface() {
        // Create a new instance of UserView
        userView = new UserView(userModel, coreModel);
        
        // Set size and position of the user interface window
        userView.setSize(1200, 800);
        userView.setLocationRelativeTo(null);

        // Make the user interface visible
        userView.setVisible(true);
        
        // Set default close operation for the window
        userView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
