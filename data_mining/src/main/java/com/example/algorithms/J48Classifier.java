package com.example.algorithms;

import weka.classifiers.trees.J48;
import weka.core.Instances;

public class J48Classifier implements Algorithm {
    private J48 tree;

    @Override
    public void train (Instances data) throws Exception {
        tree = new J48(); 
        tree.buildClassifier(data); 
    }
    
    @Override
    public String getResults() {
        return tree.toString();
    }

    @Override
    public J48 getClassifier() {
        return tree;
    }
}
