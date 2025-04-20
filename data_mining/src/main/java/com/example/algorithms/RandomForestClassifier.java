package com.example.algorithms;

import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.BestFirst;
import weka.attributeSelection.CfsSubsetEval;
import weka.classifiers.Classifier;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

public class RandomForestClassifier implements Algorithm {
    private RandomForest randomForest;

    @Override
    public void train(Instances data) throws Exception {
        randomForest = new RandomForest();
        Instances filteredData = applyFeatureSelection(data);
        randomForest.buildClassifier(filteredData);
    }

    @Override
    public String getResults() {
        return randomForest.toString();
    }

    @Override
    public Classifier getClassifier() {
        return randomForest;
    }

    @Override
    public Instances applyFeatureSelection(Instances data) throws Exception {
        AttributeSelection selector = new AttributeSelection();
        CfsSubsetEval evaluator = new CfsSubsetEval();
        BestFirst search = new BestFirst();

        selector.setEvaluator(evaluator);
        selector.setSearch(search);
        selector.SelectAttributes(data);
        Instances reducedData = selector.reduceDimensionality(data);
        
        return reducedData;
    }
}
