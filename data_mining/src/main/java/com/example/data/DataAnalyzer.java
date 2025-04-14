package com.example.data;

import weka.core.Instances;

public class DataAnalyzer {
     public void analyzeData(Instances data) {
        System.out.println("Number of instances: " + data.numInstances());
        System.out.println("Number of attributes: " + data.numAttributes());
        System.out.println("Class attribute: " + data.classAttribute().name());    
}
}
