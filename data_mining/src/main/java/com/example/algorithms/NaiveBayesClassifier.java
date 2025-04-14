package com.example.algorithms;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

public class NaiveBayesClassifier implements Algorithm {
    private NaiveBayes naiveBayes;

    @Override
    public void train(Instances data) throws Exception {
        naiveBayes = new NaiveBayes();
        naiveBayes.buildClassifier(data);
    }

    @Override
    public String getResults() {
        return naiveBayes.toString();
    }

    @Override
    public Classifier getClassifier() {
        return naiveBayes;
    }
}
