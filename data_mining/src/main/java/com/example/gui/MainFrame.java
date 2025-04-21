package com.example.gui;

import javax.swing.JFrame;

import com.example.controllers.MiningController;

public class MainFrame extends JFrame {    
    public static void main(String[] args) throws Exception {
        MiningController controller = new MiningController();
        controller.runPipeline(
            "D:\\Project\\Data-Mining-Proj\\data_mining\\src\\main\\resources\\data.arff",
            "D:\\Project\\Data-Mining-Proj\\data_mining\\src\\main\\resources\\cleaned_dataset.arff",
            "D:\\Project\\Data-Mining-Proj\\data_mining\\src\\main\\resources\\evaluation_report.txt"
        );
    }
}