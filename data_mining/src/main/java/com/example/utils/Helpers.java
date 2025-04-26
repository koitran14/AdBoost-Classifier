package com.example.utils;

import java.io.File;

import weka.core.Instances;
import weka.core.converters.CSVSaver;

public class Helpers {
    public void exportToCSV(Instances instances, String fileName) throws Exception {
        try {
            String outputPath = getOutputPath();
            String absFileName = new File(outputPath, fileName).getPath();
            CSVSaver saver = new CSVSaver();
            saver.setInstances(instances);
            saver.setFile(new File(absFileName));
            saver.writeBatch();
        } catch (Exception e) {
            System.err.println("Error saving CSV file '" + fileName + "': " + e.getMessage());
            throw e; // Re-throw to allow caller to handle if needed
        }
    }

    public String getOutputPath() throws Exception {
        File outputDir = new File("output");
        if (!outputDir.exists()) {
            if (!outputDir.mkdirs()) {
                throw new Exception("Failed to create output directory: " + outputDir.getAbsolutePath());
            }
        }
        return outputDir.getAbsolutePath();
    }
}
