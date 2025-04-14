package com.example.gui;

import javax.swing.JFrame;

import com.example.controllers.MiningController;

public class MainFrame extends JFrame {    
    public static void main(String[] args) throws Exception {
        MiningController controller = new MiningController();
        controller.runPipeline(
            "resources/raw_dataset.csv",
            "resources/cleaned_dataset.arff",
            "resources/evaluation_report.txt"
        );
    }
}