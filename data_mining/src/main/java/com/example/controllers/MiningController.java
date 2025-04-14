package com.example.controllers;

import com.example.algorithms.J48Classifier;
import com.example.algorithms.NaiveBayesClassifier;
import com.example.data.DataAnalyzer;
import com.example.data.DataCleaner;
import com.example.data.DataLoader;
import com.example.evaluation.ModelEvaluator;

import weka.core.Instances;

public class MiningController {
    private final DataCleaner cleaner = new DataCleaner();
    private final DataAnalyzer analyzer = new DataAnalyzer();
    private final DataLoader loader = new DataLoader();
    private final ModelEvaluator evaluator = new ModelEvaluator();

    public void runPipeline(String rawPath, String arffPath, String reportPath) throws Exception {
        // Step 1: Preprocessing
        Instances data = loader.loadCSVDataset(rawPath, arffPath);
        data = cleaner.cleanData(data);
        analyzer.analyzeData(data);

        // Step 2: J48 Classifier
        J48Classifier j48 = new J48Classifier();
        evaluator.evaluateModel(j48, data, reportPath);

        // Step 3: Naive Bayes Classifier
        NaiveBayesClassifier nb = new NaiveBayesClassifier();
        evaluator.evaluateModel(nb, data, reportPath);
    }
}