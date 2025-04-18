package com.example.data;

import java.io.File;
import java.io.FileNotFoundException;

import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

public class DataLoader {
    public Instances loadDataset(String filePath) throws Exception {
        ArffLoader loader = new ArffLoader();
        File datasetFile = new File(filePath);
        if (!datasetFile.exists()) {
            throw new FileNotFoundException("Dataset file not found: " + filePath);
        }
        loader.setFile(new File(filePath));
        Instances data = loader.getDataSet();
        data.setClassIndex(data.numAttributes() - 1); // Set class attribute if needed
        return data;
    }

    public Instances loadCSVDataset(String csvPath, String arffPath) throws Exception{
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File(csvPath));
        Instances data = loader.getDataSet();
        data.setClassIndex(data.numAttributes() - 1); // Set the last attribute as the class
        
        // Save to ARFF file
        ArffSaver saver = new ArffSaver();
        saver.setInstances(data);
        saver.setFile(new File(arffPath));
        saver.writeBatch();
        return data;
    }
}
