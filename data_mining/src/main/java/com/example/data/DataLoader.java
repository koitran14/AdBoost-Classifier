package com.example.data;

import java.io.File;

import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class DataLoader {
    public Instances loadDataset(String filePath) throws Exception {
        ArffLoader loader = new ArffLoader();
        loader.setFile(new File(filePath));
        Instances data = loader.getDataSet();
        data.setClassIndex(data.numAttributes() - 1); // Set class attribute if needed
        return data;
    }
}
