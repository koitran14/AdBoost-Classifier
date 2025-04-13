package com.example.algorithms;

import weka.classifiers.trees.J48;
import weka.core.Instances;

public class J48Algorithm {
    private J48 tree;

    public void apply (Instances data) throws Exception {
        tree = new J48(); 
        tree.buildClassifier(data); 
    }
    
    public String getResults() {
        return tree.toString();
    }
}
