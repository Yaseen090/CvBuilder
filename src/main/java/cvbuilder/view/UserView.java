/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cvbuilder.view;

import cvbuilder.model.CoreModel;
import cvbuilder.model.UserModel;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class UserView extends JFrame {

    private JPanel namePanel, titlePanel, emailPanel;
    private JPanel mainContentPanel;
    int iheight = 50;
    JButton prevButton, nextButton;
    JPanel btnSectionPanel;

    JPanel bigPanel;
    public static int i = 0;
    private JLabel nameLabel, titleLabel, emailLabel;
    Color borderColor;
    public UserModel localUserModel;
    public CoreModel localCoreModel;
    public static ButtonGroup btnGroup = new ButtonGroup();
    public static JTextArea customizedCV;
    public static StringBuilder cvString = new StringBuilder();

    public UserView(UserModel userModel, CoreModel coreModel) {
        localUserModel = userModel;
        localCoreModel = coreModel;
        setTitle("CV Builder");
        JPanel combinedPanel = new JPanel();
        combinedPanel.setLayout(new BoxLayout(combinedPanel, BoxLayout.Y_AXIS));
        borderColor = Color.decode("#C7DCF1");
        Border labelBorder = BorderFactory.createMatteBorder(1, 1, 0, 1, borderColor); // Top, Right, Bottom, Left

        // Create a panel for holding "Name: User" and "Core" labels
        JPanel userCorePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        JLabel userLabel = new JLabel("User");
        JLabel coreLabel = new JLabel("Core");
        userLabel.setBorder(labelBorder);
        coreLabel.setBorder(labelBorder);

        userLabel.setOpaque(true);
        coreLabel.setOpaque(true);
        userLabel.setBackground(borderColor);

        userCorePanel.add(userLabel);
        userCorePanel.add(coreLabel);

        bigPanel = new JPanel(new FlowLayout());
        bigPanel.setLayout(new BoxLayout(bigPanel, BoxLayout.Y_AXIS));
        bigPanel.setBounds(0, 0, 700, (int) bigPanel.getPreferredSize().getHeight());
        // Add the userCorePanel to the combinedPanel
        // Create a panel for holding labels

        // Create a JPanel to hold the label and text area
        JPanel textareaPanel = new JPanel(new BorderLayout());
        textareaPanel.setOpaque(true);
        textareaPanel.setBackground(Color.WHITE);
        // Create a label
        JLabel textareaLabel = new JLabel("Customized CV");
        textareaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Font labelFont = textareaLabel.getFont();
        textareaLabel.setFont(new Font(labelFont.getName(), Font.BOLD, 40));
        textareaPanel.add(textareaLabel, BorderLayout.NORTH);

        customizedCV = new JTextArea();
        customizedCV.setPreferredSize(new Dimension(700, 700));
        customizedCV.setLineWrap(true);
        customizedCV.setWrapStyleWord(true);
        customizedCV.setBorder(null);
        textareaPanel.add(new JScrollPane(customizedCV), BorderLayout.CENTER);

        getContentPane().add(textareaPanel, BorderLayout.EAST);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0)); // Horizontal alignment with no gaps

        // Create labels
        nameLabel = new JLabel("User Name");
        titleLabel = new JLabel("User Title");
        emailLabel = new JLabel("User Email");

        // Apply custom border with color #C7DCF1 to labels
        nameLabel.setBorder(labelBorder);
        titleLabel.setBorder(labelBorder);
        emailLabel.setBorder(labelBorder);
        nameLabel.setOpaque(true);
        titleLabel.setOpaque(true);
        emailLabel.setOpaque(true);
        nameLabel.setBackground(borderColor);

        // Add labels to the top panel
        topPanel.add(nameLabel);
        topPanel.add(titleLabel);
        topPanel.add(emailLabel);

        // Add the topPanel to the combinedPanel
        combinedPanel.add(topPanel);
        // Add the combinedPanel to the JFrame's content pane

        // Create main content panel to hold the main content area
        mainContentPanel = new JPanel(new BorderLayout());
        combinedPanel.add(mainContentPanel);

        // Create panels for Name, Title, and Email
        namePanel = createPanel("Name", localUserModel.getNamesList());
        titlePanel = createPanel("Title", localUserModel.getTitleList());
        emailPanel = createPanel("Email", localUserModel.getEmailList());

        bigPanel.add(userCorePanel, BorderLayout.NORTH);
        bigPanel.add(combinedPanel, BorderLayout.WEST);

        btnSectionPanel = new JPanel(new FlowLayout());
        btnSectionPanel.setLayout(new BoxLayout(btnSectionPanel, BoxLayout.X_AXIS));
        prevButton = new JButton("Previous Section");
        nextButton = new JButton("Next Section");
        btnSectionPanel.add(prevButton);
        btnSectionPanel.add(nextButton);

        bigPanel.add(btnSectionPanel, BorderLayout.SOUTH);
        // Show Name panel by default
        getContentPane().add(bigPanel, BorderLayout.WEST);

        showPanel(namePanel, "name");
        setSize(600, iheight);

        nextButton.addActionListener(e -> {
            iheight = 50;
            iheight = iheight + (coreModel.getPersonalStatementList().size() * 250);
            coreLabel.setBackground(borderColor);
            userLabel.setBackground(null);
            bigPanel.removeAll(); // Remove all components from the main content panel
            bigPanel.add(userCorePanel, BorderLayout.NORTH);
            bigPanel.add(CoreView.createPanel("Core", coreModel, prevButton, nextButton), BorderLayout.WEST); // Add the specified panel
            bigPanel.add(btnSectionPanel, BorderLayout.SOUTH);

            bigPanel.revalidate(); // Revalidate the main content panel
            bigPanel.repaint();

            getContentPane().add(bigPanel);

        });
        prevButton.addActionListener(g -> {
            iheight = 50;
            iheight = iheight + (userModel.getNamesList().size() * 120);
            bigPanel.removeAll(); // Remove all components from the main content panel
            bigPanel.add(userCorePanel, BorderLayout.NORTH);
            bigPanel.add(combinedPanel, BorderLayout.WEST);
            bigPanel.add(btnSectionPanel, BorderLayout.SOUTH);
            bigPanel.revalidate(); // Revalidate the main content panel
            bigPanel.repaint();
//                setSize(getWidth(), iheight);

            userLabel.setBackground(borderColor);
            coreLabel.setBackground(null);
            getContentPane().add(bigPanel);
        });

        userLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                iheight = 50;
                iheight = iheight + (userModel.getNamesList().size() * 120);
                bigPanel.removeAll(); // Remove all components from the main content panel
                bigPanel.add(userCorePanel, BorderLayout.NORTH);
                bigPanel.add(combinedPanel, BorderLayout.WEST);
                bigPanel.add(btnSectionPanel, BorderLayout.SOUTH);
                bigPanel.revalidate(); // Revalidate the main content panel
                bigPanel.repaint();
//                setSize(getWidth(), iheight);

                userLabel.setBackground(borderColor);
                coreLabel.setBackground(null);
                getContentPane().add(bigPanel);

            }
        });

        coreLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                iheight = 50;
                iheight = iheight + (coreModel.getPersonalStatementList().size() * 250);
                coreLabel.setBackground(borderColor);
                userLabel.setBackground(null);
                bigPanel.removeAll(); // Remove all components from the main content panel
                bigPanel.add(userCorePanel, BorderLayout.NORTH);
                bigPanel.add(CoreView.createPanel("Core", coreModel, prevButton, nextButton), BorderLayout.WEST); // Add the specified panel
                bigPanel.add(btnSectionPanel, BorderLayout.SOUTH);

                bigPanel.revalidate(); // Revalidate the main content panel
                bigPanel.repaint();

                getContentPane().add(bigPanel);

            }
        });

        // Add MouseListeners to labels to show respective panels
        nameLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showPanel(namePanel, "name");
                nameLabel.setBackground(borderColor);
                titleLabel.setBackground(null); // Reset background color
                emailLabel.setBackground(null);

            }
        });

        titleLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showPanel(titlePanel, "title");
                titleLabel.setBackground(borderColor);
                nameLabel.setBackground(null); // Reset background color
                emailLabel.setBackground(null);

            }
        });

        emailLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showPanel(emailPanel, "email");
                emailLabel.setBackground(borderColor);
                titleLabel.setBackground(null); // Reset background color
                nameLabel.setBackground(null);

            }
        });
    }

    private JPanel createPanel(String title, java.util.List<String> list) {
        System.out.println(title + " list size: " + list.size());
        JPanel panel = new JPanel(new FlowLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(5, 5, 5, 5, Color.decode("#C7DCF1")), // Border with margin
                BorderFactory.createTitledBorder(title))); // Titled border

        JPanel includePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        includePanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to the left
        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(true);

        JLabel includeLabel = new JLabel("Include");
        includePanel.add(checkBox);
        includePanel.add(includeLabel);

        if (title.equals("Title")) {
            panel.add(includePanel);
        }

        for (int i = 0; i < list.size(); i++) {
            iheight += 50;
            createTitledBorderWithButtons(title + " " + (i + 1), panel, list.get(i), title, btnGroup);

        }

        JPanel borderPanel = new JPanel(new FlowLayout());
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Add new " + title);
        titledBorder.setTitleJustification(TitledBorder.CENTER); // Align the title to the left
        borderPanel.setBorder(titledBorder);
        borderPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField addField = new JTextField();
        addField.setPreferredSize(new Dimension(200, (int) addField.getPreferredSize().getHeight()));
        JButton addButton = new JButton("Add " + title);
        addButton.setPreferredSize(new Dimension(addButton.getPreferredSize().width + 20, addButton.getPreferredSize().height)); // Set preferred width
        StringBuilder listitem = new StringBuilder();
        listitem.setLength(0);
        listitem.append(addField.getText());

        addButton.addActionListener(e -> {
            if (title.equals("Name")) {
                addMethod(panel, addField.getText().toString(), localUserModel, localUserModel.getNamesList(), "Name");
            }
            if (title.equals("Title")) {
                addMethod(panel, addField.getText().toString(), localUserModel, localUserModel.getTitleList(), "Title");
            }
            if (title.equals("Email")) {
                addMethod(panel, addField.getText().toString(), localUserModel, localUserModel.getEmailList(), "Email");
            }

        });
        borderPanel.add(addField);
        borderPanel.add(addButton);
        panel.add(borderPanel);
        // Add content to the panel
        return panel;
    }

    private void createTitledBorderWithButtons(String title, JPanel panel, String listItem, String originalTitle, ButtonGroup btnGroup) {
        JPanel borderPanel = new JPanel(new FlowLayout());
        TitledBorder titledBorder = BorderFactory.createTitledBorder(title);
        titledBorder.setTitleJustification(TitledBorder.LEFT); // Align the title to the left
        borderPanel.setBorder(titledBorder); // Titled border

        // Create radio button and label
        JRadioButton radioButton = new JRadioButton();

        JLabel label = new JLabel(listItem);
        btnGroup.add(radioButton);
        // Add radio button and label to the border panel
        JPanel radioLabelPanel = new JPanel();
        radioLabelPanel.add(radioButton);
        radioLabelPanel.add(label);
        radioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appendToCV(label.getText(), originalTitle);
            }
        });
        borderPanel.add(radioLabelPanel, BorderLayout.CENTER);

        // Create Edit and Delete buttons with preferred size
        JButton editButton = new JButton("Edit");
        editButton.setPreferredSize(new Dimension(80, editButton.getPreferredSize().height)); // Set preferred width
        JButton deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(80, deleteButton.getPreferredSize().height)); // Set preferred width
        editButton.addActionListener(e -> {
            if (originalTitle.equals("Name")) {
                String edited = editMethod(panel, listItem, localUserModel, localUserModel.getNamesList(), "Name");
            }
            if (originalTitle.equals("Title")) {
                String edited = editMethod(panel, listItem, localUserModel, localUserModel.getTitleList(), "Title");
            }
            if (originalTitle.equals("Email")) {
                String edited = editMethod(panel, listItem, localUserModel, localUserModel.getEmailList(), "Email");
            }

        });
        deleteButton.addActionListener(e -> {
            if (originalTitle.equals("Name")) {
                deleteMethod(panel, listItem, localUserModel, localUserModel.getNamesList(), "Name");
            }
            if (originalTitle.equals("Title")) {
                deleteMethod(panel, listItem, localUserModel, localUserModel.getTitleList(), "Title");
            }
            if (originalTitle.equals("Email")) {
                deleteMethod(panel, listItem, localUserModel, localUserModel.getEmailList(), "Email");
            }

        });
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 5, 0)); // 1 row, 2 columns, horizontal gap of 5, no vertical gap
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);
        borderPanel.add(buttonsPanel, BorderLayout.EAST);

        // Add the border panel to the titled borders panel
        panel.add(borderPanel);
    }

    // Method to show a specific panel in the main content area
    private void showPanel(JPanel panel, String title) {

        mainContentPanel.removeAll(); // Remove all components from the main content panel
        mainContentPanel.add(panel, BorderLayout.CENTER); // Add the specified panel
        mainContentPanel.revalidate(); // Revalidate the main content panel
        mainContentPanel.repaint(); // Repaint the main content panel

    }

    public String editMethod(Component parentComponent, String title, UserModel userModel, java.util.List<String> listToUpdate, String listToUpdateString) {
        final JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(parentComponent), title, true);
        JPanel panel = new JPanel(new BorderLayout());
        JTextField textField = new JTextField(title);
        JButton button = new JButton("OK");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String editedText = textField.getText(); // Get the edited text from the text field
                int selectedIndex = listToUpdate.indexOf(title); // Get the index of the item being edited
                if (selectedIndex != -1) { // Check if the item exists in the list
                    listToUpdate.set(selectedIndex, editedText); // Update the item in the list
                    if (listToUpdateString.equals("Name")) {
                        userModel.setNamesList(listToUpdate);
                        namePanel = createPanel("Name", localUserModel.getNamesList());
                        showPanel(namePanel, "name");
                    }
                    if (listToUpdateString.equals("Title")) {
                        userModel.setTitleList(listToUpdate);
                        titlePanel = createPanel("Title", localUserModel.getTitleList());
                        showPanel(titlePanel, "title");
                    }
                    if (listToUpdateString.equals("Email")) {
                        userModel.setEmailList(listToUpdate);
                        emailPanel = createPanel("Email", localUserModel.getEmailList());
                        showPanel(emailPanel, "email");
                    }
                }
                dialog.dispose(); // Close the dialog when the button is clicked
            }
        });

        panel.add(textField, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(parentComponent);
        dialog.setVisible(true); // Show the dialog and wait for user input

        return textField.getText(); // Return the text entered in the text field
    }

    public void deleteMethod(Component parentComponent, String title, UserModel userModel, java.util.List<String> listToUpdate, String listToUpdateString) {
        // Get the edited text from the text field
        int selectedIndex = listToUpdate.indexOf(title); // Get the index of the item being edited
        if (selectedIndex != -1) { // Check if the item exists in the list
            listToUpdate.remove(selectedIndex); // Update the item in the list
            if (listToUpdateString.equals("Name")) {
                userModel.setNamesList(listToUpdate);
                namePanel = createPanel("Name", localUserModel.getNamesList());
                showPanel(namePanel, "name");
            }
            if (listToUpdateString.equals("Title")) {
                userModel.setTitleList(listToUpdate);
                titlePanel = createPanel("Title", localUserModel.getTitleList());
                showPanel(titlePanel, "title");
            }
            if (listToUpdateString.equals("Email")) {
                userModel.setEmailList(listToUpdate);
                emailPanel = createPanel("Email", localUserModel.getEmailList());
                showPanel(emailPanel, "email");
            }
        }
    }

    public void addMethod(Component parentComponent, String title, UserModel userModel, java.util.List<String> listToUpdate, String listToUpdateString) {

        // Check if the item exists in the list
        listToUpdate.add(title); // Update the item in the list
        if (listToUpdateString.equals("Name")) {
            userModel.setNamesList(listToUpdate);
            namePanel = createPanel("Name", localUserModel.getNamesList());
            showPanel(namePanel, "name");
        }
        if (listToUpdateString.equals("Title")) {
            userModel.setTitleList(listToUpdate);
            titlePanel = createPanel("Title", localUserModel.getTitleList());
            showPanel(titlePanel, "title");
        }
        if (listToUpdateString.equals("Email")) {
            userModel.setEmailList(listToUpdate);
            emailPanel = createPanel("Email", localUserModel.getEmailList());
            showPanel(emailPanel, "email");
        }

        // Return the text entered in the text field
    }

    public static void appendToCV(String newLine, String title) {
        String currentText = customizedCV.getText();
        StringBuilder cvContent = new StringBuilder(currentText.length() + newLine.length() + title.length() + 5); // 5 for ": " and "\n"

        if (currentText.isEmpty()) {
            cvContent.append(title).append(" : ").append(newLine).append("\n");
        } else {
            String[] parts = currentText.split("\n");

            for (String part : parts) {
                if (part.startsWith(title)) {
                    cvContent.append(title).append(" : ").append(newLine).append("\n");
                } else {
                    cvContent.append(part).append("\n");
                }
            }

            if (!parts[parts.length - 1].startsWith(title)) {
                cvContent.append(title).append(" : ").append(newLine).append("\n");
            }
        }

        customizedCV.setText(cvContent.toString());
    }

}
