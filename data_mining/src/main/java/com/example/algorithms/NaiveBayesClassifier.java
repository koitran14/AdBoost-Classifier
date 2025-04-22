package com.example.algorithms;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

public class NaiveBayesClassifier implements Algorithm {
    private NaiveBayes naiveBayes;
    
    @Override
    public Instances specificPreprocess(Instances data) throws Exception {
        // Implement specific preprocessing logic if needed
        return data;
    }

    @Override
    public void train(Instances data) throws Exception {
        naiveBayes = new NaiveBayes();
        Instances filteredData = applyFeatureSelection(data);
        naiveBayes.buildClassifier(filteredData);
    }

    @Override
    public String getResults() {
        return naiveBayes.toString();
    }

    @Override
    public Classifier getClassifier() {
        return naiveBayes;
    }

    @Override
    public Instances applyFeatureSelection(Instances data) throws Exception {
        //Implement applyFeatureSelection logic if needed
        return data; 
    }
}
