package com.example.algorithms;

import weka.attributeSelection.BestFirst;
import weka.attributeSelection.CfsSubsetEval;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;

public class J48Classifier implements Algorithm {
    private J48 tree;

    @Override
    public void train (Instances data) throws Exception {
        tree = new J48(); 
        Instances filteredData = applyFeatureSelection(data);
        tree.buildClassifier(filteredData); 
    }
    
    @Override
    public String getResults() {
        return tree.toString();
    }

    @Override
    public J48 getClassifier() {
        return tree;
    }

    @Override
    public Instances applyFeatureSelection(Instances data) throws Exception {
        AttributeSelection filter = new AttributeSelection();
        CfsSubsetEval eval = new CfsSubsetEval();
        BestFirst search = new BestFirst();
        filter.setEvaluator(eval);
        filter.setSearch(search);
        filter.setInputFormat(data);
        
        return Filter.useFilter(data, filter);
    }
}
