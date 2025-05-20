package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.imageio.ImageIO;
import java.io.File;

public class Gui {

    public void createAndShowGUI() {
        // Initialize current resolution
        ResolutionChanger.initializeCurrentResolution();

        // Create frame
        JFrame frame = new JFrame("BE4TS Resolution Controller");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Set custom icon
        try {
            frame.setIconImage(ImageIO.read(new File("D:\\Projects\\Fullscreen\\LOGO ALB (2).png")));
        } catch (Exception e) {
            System.err.println("Error loading icon: " + e.getMessage());
        }

        // Main panel with grid layout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        frame.add(mainPanel);

        // Title
        JLabel title = new JLabel("BE4TS Resolution Controller");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(title, gbc);

        // Custom Resolution Section
        gbc.gridy++;
        JLabel customHeader = new JLabel("Custom Resolution");
        customHeader.setFont(customHeader.getFont().deriveFont(Font.BOLD));
        mainPanel.add(customHeader, gbc);

        // Custom Width (now blank by default)
        gbc.gridy++;
        gbc.gridwidth = 1;
        mainPanel.add(new JLabel("Width:"), gbc);
        gbc.gridx = 1;
        JTextField widthField = new JTextField("", 10); // Blank by default
        mainPanel.add(widthField, gbc);

        // Custom Height (now blank by default)
        gbc.gridy++;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Height:"), gbc);
        gbc.gridx = 1;
        JTextField heightField = new JTextField("", 10); // Blank by default
        mainPanel.add(heightField, gbc);

        // Custom Refresh Rate (now blank by default)
        gbc.gridy++;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Refresh Rate:"), gbc);
        gbc.gridx = 1;
        JTextField refreshField = new JTextField("", 10); // Blank by default
        mainPanel.add(refreshField, gbc);

        // Set Custom Button
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton setCustomBtn = new JButton("Set Custom Resolution");
        mainPanel.add(setCustomBtn, gbc);

        // Default Resolution Section
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        JLabel defaultHeader = new JLabel("Default Resolution");
        defaultHeader.setFont(defaultHeader.getFont().deriveFont(Font.BOLD));
        mainPanel.add(defaultHeader, gbc);

        // Default Width
        gbc.gridy++;
        mainPanel.add(new JLabel("Width:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(new JLabel(String.valueOf(ResolutionChanger.defaultWidth)), gbc);

        // Default Height
        gbc.gridy++;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Height:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(new JLabel(String.valueOf(ResolutionChanger.defaultHeight)), gbc);

        // Default Refresh Rate
        gbc.gridy++;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Refresh Rate:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(new JLabel(String.valueOf(ResolutionChanger.defaultRefreshRate)), gbc);

        // Set Default Button
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton setDefaultBtn = new JButton("Set Default Resolution");
        mainPanel.add(setDefaultBtn, gbc);

        // Button actions
        setCustomBtn.addActionListener(e -> {
            try {
                int width = Integer.parseInt(widthField.getText());
                int height = Integer.parseInt(heightField.getText());
                int refresh = Integer.parseInt(refreshField.getText());

                ResolutionChanger.changeResolution(width, height, refresh);
                // Store the values only after successful set
                ResolutionChanger.customWidth = width;
                ResolutionChanger.customHeight = height;
                ResolutionChanger.customRefresh = refresh;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numbers");
            }
        });

        setDefaultBtn.addActionListener(e -> {
            ResolutionChanger.changeResolution(
                    ResolutionChanger.defaultWidth,
                    ResolutionChanger.defaultHeight,
                    ResolutionChanger.defaultRefreshRate
            );
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}