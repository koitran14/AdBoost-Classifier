package com.example.algorithms;

import weka.classifiers.Classifier;
import weka.core.Instances;

public interface Algorithm {
    
    void train(Instances data) throws Exception;

    String getResults();

    Classifier getClassifier();
}
