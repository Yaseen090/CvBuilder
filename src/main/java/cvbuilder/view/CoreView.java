/*
 * This class represents the view layer for displaying and interacting with core model data.
 * It includes methods for creating and managing panels to display personal statements and skills.
 */
package cvbuilder.view;

import cvbuilder.model.CoreModel;
import static cvbuilder.view.UserView.customizedCV;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CoreView {

    // Member variables
    private static JPanel personalStatementPanel, skillsPanel;
    private static JPanel mainContentPanel;
    public static int iHeight = 50;
    public static JLabel psLabel, skillsLabel;
    public static JButton prevButton, nextButton;
    public static Color borderColor;
    public static java.util.List<JRadioButton> radiobuttonslist = new ArrayList<>();
    public static ButtonGroup btnGroup = new ButtonGroup();

    // Method to create a panel for displaying core model data
    public static JPanel createPanel(String title, CoreModel coreModel, JButton previousButton, JButton nextButton) {
        // Initialize member variables
        prevButton = previousButton;
        nextButton = nextButton;
        JPanel combinedPanel = new JPanel();
        combinedPanel.setLayout(new BoxLayout(combinedPanel, BoxLayout.Y_AXIS));

        borderColor = Color.decode("#C7DCF1");
        Border labelBorder = BorderFactory.createMatteBorder(1, 1, 0, 1, borderColor);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        // Create labels
        psLabel = new JLabel("Personal Statement");
        skillsLabel = new JLabel("Skills");

        // Apply custom border to labels
        psLabel.setBorder(labelBorder);
        skillsLabel.setBorder(labelBorder);
        psLabel.setOpaque(true);
        skillsLabel.setOpaque(true);
        psLabel.setBackground(borderColor);

        // Add labels to the top panel
        topPanel.add(psLabel);
        topPanel.add(skillsLabel);

        // Add the topPanel to the combinedPanel
        combinedPanel.add(topPanel);

        // Create main content panel
        mainContentPanel = new JPanel(new BorderLayout());
        combinedPanel.add(mainContentPanel);

        // Create panels for personal statements and skills
        personalStatementPanel = createIndividualPanel("Personal Statements", coreModel.getPersonalStatementList(), coreModel);
        skillsPanel = createIndividualPanel("Skills", coreModel.getSkillsList(), coreModel);

        // Show personal statement panel by default
        showPanel(personalStatementPanel);

        // Add mouse listeners to labels to switch between panels
        psLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showPanel(personalStatementPanel);
                iHeight = 200;
                psLabel.setBackground(borderColor);
                skillsLabel.setBackground(null);
            }
        });
        skillsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                iHeight = 400;
                showPanel(skillsPanel);
                skillsLabel.setBackground(borderColor);
                psLabel.setBackground(null);
            }
        });

        return combinedPanel;
    }

    // Method to create an individual panel for personal statements or skills
    public static JPanel createIndividualPanel(String title, java.util.List<String> list, CoreModel coreModel) {
        iHeight = 50;
        JPanel panel = new JPanel(new FlowLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(5, 5, 5, 5, Color.decode("#C7DCF1")),
                BorderFactory.createTitledBorder(title)));

        // Create include panel for personal statements
        JPanel includePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        includePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(true);
        JLabel includeLabel = new JLabel("Include");
        includePanel.add(checkBox);
        includePanel.add(includeLabel);

        if (title.equals("Personal Statements")) {
            panel.add(includePanel);
        }

        // Create titled borders with buttons for each item
        for (int i = 0; i < list.size(); i++) {
            createTitledBorderWithButtons(title + " " + (i + 1), panel, title, list.get(i), coreModel);
        }

        // Create border panel for adding new items
        JPanel borderPanel = new JPanel(new FlowLayout());
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Add new " + title);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        borderPanel.setBorder(titledBorder);
        borderPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea area = new JTextArea();
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setRows(5);

        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setPreferredSize(new Dimension(200, 100));

        JTextField addField = new JTextField();
        addField.setPreferredSize(new Dimension(200, (int) addField.getPreferredSize().getHeight()));

        JButton addButton = new JButton("Add " + title);
        addButton.setPreferredSize(new Dimension(addButton.getPreferredSize().width, addButton.getPreferredSize().height));

        // Add action listener to add button
        addButton.addActionListener(e -> {
            String areaText = area.getText().toString();
            String fieldText = addField.getText().toString();
            if (title.equals("Personal Statements")) {
                addMethod(panel, areaText, coreModel, coreModel.getPersonalStatementList(), "Personal Statements");
            }
            if (title.equals("Skills")) {
                addMethod(panel, fieldText, coreModel, coreModel.getSkillsList(), "Skills");
            }
        });

        if (title.equals("Personal Statements")) {
            borderPanel.add(scrollPane);
        } else if (title.equals("Skills")) {
            borderPanel.add(addField);
        }

        borderPanel.add(addButton);

        panel.add(borderPanel);

        return panel;
    }

    // Method to create titled borders with buttons
    public static void createTitledBorderWithButtons(String title, JPanel panel, String panelTitle, String listItem, CoreModel coreModel) {
        JPanel borderPanel = new JPanel(new FlowLayout());
        TitledBorder titledBorder = BorderFactory.createTitledBorder(title);
        titledBorder.setTitleJustification(TitledBorder.LEFT);
        borderPanel.setBorder(titledBorder);

        JRadioButton radioButton = new JRadioButton();
        btnGroup.add(radioButton);

        JLabel label = new JLabel(listItem);
        JTextArea area = new JTextArea(listItem);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setRows(5);

        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setPreferredSize(new Dimension(200, 100));

        JPanel radioLabelPanel = new JPanel();
        radioLabelPanel.add(radioButton);
        StringBuilder newBuild = new StringBuilder();
        if (panelTitle.equals("Personal Statements")) {
            radioLabelPanel.add(scrollPane);
            newBuild.setLength(0);
            newBuild.append(area.getText().toString());
        } else if (panelTitle.equals("Skills")) {
            radioLabelPanel.add(label);
            newBuild.setLength(0);
            newBuild.append(label.getText().toString());
        }

        // Add action listener to radio button
        radioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appendToCV(newBuild.toString(), panelTitle);
            }
        });

        borderPanel.add(radioLabelPanel, BorderLayout.CENTER);

        // Create Edit and Delete buttons
        JButton editButton = new JButton("Edit");
        editButton.setPreferredSize(new Dimension(80, editButton.getPreferredSize().height));
        JButton deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(80, deleteButton.getPreferredSize().height));

        // Add action listeners to Edit and Delete buttons
        editButton.addActionListener(e -> {
            if (panelTitle.equals("Personal Statements")) {
                String edited = editMethod(panel, listItem, coreModel, coreModel.getPersonalStatementList(), "Personal Statements");
            }
            if (panelTitle.equals("Skills")) {
                String edited = editMethod(panel, listItem, coreModel, coreModel.getSkillsList(), "Skills");
            }
        });
        deleteButton.addActionListener(e -> {
            if (panelTitle.equals("Personal Statements")) {
                deletMethod(panel, listItem, coreModel, coreModel.getPersonalStatementList(), "Personal Statements");
            }
            if (panelTitle.equals("Skills")) {
                deletMethod(panel, listItem, coreModel, coreModel.getSkillsList(), "Skills");
            }
        });

        // Add Edit and Delete buttons to border panel
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 5, 0));
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);
        borderPanel.add(buttonsPanel, BorderLayout.EAST);

        panel.add(borderPanel);
    }

    // Method to show a specific panel in the main content area
    public static void showPanel(JPanel panel) {
        mainContentPanel.removeAll();
        mainContentPanel.add(panel, BorderLayout.CENTER);
        mainContentPanel.revalidate();
        mainContentPanel.repaint();
    }

    // Method to edit an item in the list
    public static String editMethod(Component parentComponent, String title, CoreModel coreModel, java.util.List<String> listToUpdate, String listToUpdateString) {
        final JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(parentComponent), title, true);
        JPanel panel = new JPanel(new BorderLayout());
        JTextField textField = new JTextField(title);
        JButton button = new JButton("OK");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String editedText = textField.getText();
                int selectedIndex = listToUpdate.indexOf(title);
                if (selectedIndex != -1) {
                    listToUpdate.set(selectedIndex, editedText);
                    if (listToUpdateString.equals("Personal Statements")) {
                        coreModel.setPersonalStatementList(listToUpdate);
                        personalStatementPanel = createIndividualPanel("Personal Statements", coreModel.getPersonalStatementList(), coreModel);
                        showPanel(personalStatementPanel);
                    }
                    if (listToUpdateString.equals("Skills")) {
                        coreModel.setSkillsList(listToUpdate);
                        skillsPanel = createIndividualPanel("Skills", coreModel.getSkillsList(), coreModel);
                        showPanel(skillsPanel);
                    }
                }
                dialog.dispose();
            }
        });

        panel.add(textField, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(parentComponent);
        dialog.setVisible(true);

        return textField.getText();
    }

    // Method to delete an item from the list
    public static void deletMethod(Component parentComponent, String title, CoreModel coreModel, java.util.List<String> listToUpdate, String listToUpdateString) {
        int selectedIndex = listToUpdate.indexOf(title);
        if (selectedIndex != -1) {
            listToUpdate.remove(selectedIndex);
            if (listToUpdateString.equals("Personal Statements")) {
                coreModel.setPersonalStatementList(listToUpdate);
                personalStatementPanel = createIndividualPanel("Personal Statements", coreModel.getPersonalStatementList(), coreModel);
                showPanel(personalStatementPanel);
            }
            if (listToUpdateString.equals("Skills")) {
                coreModel.setSkillsList(listToUpdate);
                skillsPanel = createIndividualPanel("Skills", coreModel.getSkillsList(), coreModel);
                showPanel(skillsPanel);
            }
        }
    }

    // Method to add an item to the list
    public static void addMethod(Component parentComponent, String title, CoreModel coreModel, java.util.List<String> listToUpdate, String listToUpdateString) {
        listToUpdate.add(title);
        if (listToUpdateString.equals("Personal Statements")) {
            coreModel.setPersonalStatementList(listToUpdate);
            personalStatementPanel = createIndividualPanel("Personal Statements", coreModel.getPersonalStatementList(), coreModel);
            showPanel(personalStatementPanel);
        }
        if (listToUpdateString.equals("Skills")) {
            coreModel.setSkillsList(listToUpdate);
            skillsPanel = createIndividualPanel("Skills", coreModel.getSkillsList(), coreModel);
            showPanel(skillsPanel);
        }
    }

    // Method to append text to the customized CV
    public static void appendToCV(String newLine, String title) {
        String currentText = customizedCV.getText();
        StringBuilder cvContent = new StringBuilder(currentText.length() + newLine.length() + title.length() + 5);

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
