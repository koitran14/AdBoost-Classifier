package com.example.gui;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class MainFrame extends JFrame {
    private Instances data; // To store the loaded dataset

    public MainFrame() {
        setTitle("Weka Data Analyzer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Use a panel with BorderLayout
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("No data loaded", SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);

        // Add a "Load Data" button
        JButton loadButton = new JButton("Load Data");
        loadButton.addActionListener(e -> loadData(label));
        panel.add(loadButton, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    private void loadData(JLabel label) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                ArffLoader loader = new ArffLoader();
                loader.setFile(selectedFile);
                data = loader.getDataSet();
                data.setClassIndex(data.numAttributes() - 1); // Set the last attribute as the class
                label.setText("Data loaded: " + selectedFile.getName());
            } catch (Exception ex) {
                ex.printStackTrace();
                label.setText("Error loading data");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}