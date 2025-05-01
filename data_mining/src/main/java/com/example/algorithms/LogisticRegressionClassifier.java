package com.example.algorithms;

import java.util.Random;

import com.example.utils.Helpers;

import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.GreedyStepwise;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.Logistic;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.supervised.instance.SMOTE;
import weka.filters.unsupervised.attribute.Standardize;
import weka.filters.unsupervised.attribute.StringToWordVector;


public class LogisticRegressionClassifier implements Algorithm {
    private Logistic logistic;
    private double bestF1Score = 0.0;
    private String[] bestOptions;

    @Override
    public Instances specificPreprocess(Instances data) throws Exception {
        Instances processedData = applyTextProcessing(data);

        Instances balancedData = applySMOTE(processedData);

        Standardize standardize = new Standardize();
        standardize.setInputFormat(balancedData);
        Instances standardizedData = Filter.useFilter(balancedData, standardize);
        return standardizedData;
    }

    @Override
    public void train(Instances data) throws Exception {
        System.out.println("Starting preprocessing...");
        Instances preprocessedData = specificPreprocess(data);
        System.out.println("Preprocessing done. Instances: " + preprocessedData.numInstances() + ", Attributes: " + preprocessedData.numAttributes());

        Instances filteredFinalData = applyFeatureSelection(preprocessedData);

        logistic = new Logistic();
        logistic.setOptions(bestOptions != null ? bestOptions : new String[]{"-R", "1.0"});
        System.out.println("Training final model...");
        logistic.buildClassifier(filteredFinalData);
        System.out.println("Training complete.");

        Helpers helper = new Helpers();
        helper.exportToCSV(filteredFinalData, "logistic_filtered_dataset.csv");
    }

    private void optimizeLogistic(Instances data) throws Exception {
        String[][] paramGrid = {
            {"-R", "0.001"}, {"-R", "0.1"}, {"-R", "1.0"} // Reduced grid
        };
        bestF1Score = 0.0;
        bestOptions = null;

        for (String[] options : paramGrid) {
            try {
                Logistic tempLogistic = new Logistic();
                tempLogistic.setOptions(options);
                tempLogistic.buildClassifier(data);

                Evaluation eval = new Evaluation(data);
                eval.crossValidateModel(tempLogistic, data, 5, new Random(1)); // 5 folds
                double f1Score = eval.weightedFMeasure(); // Use F1-score
                System.out.println("Options: " + String.join(" ", options) + " | F1-Score: " + f1Score);

                if (f1Score > bestF1Score) {
                    bestF1Score = f1Score;
                    bestOptions = options.clone();
                }
            } catch (Exception e) {
                System.err.println("Error evaluating options " + String.join(" ", options) + ": " + e.getMessage());
                e.printStackTrace();
                continue;
            }
        }
        if (bestOptions == null) {
            bestOptions = new String[]{"-R", "1.0"}; // Fallback
        }
        System.out.println("Best Options: " + String.join(" ", bestOptions) + " | Best F1-Score: " + bestF1Score);
    }

    @Override
    public String getResults() {
        return logistic.toString();
    }

    @Override
    public Logistic getClassifier() {
        return logistic;
    }

    @Override
    public Instances applyFeatureSelection(Instances data) throws Exception {
        weka.attributeSelection.AttributeSelection selector = new weka.attributeSelection.AttributeSelection();
        CfsSubsetEval evaluator = new CfsSubsetEval();
        GreedyStepwise search = new GreedyStepwise();
        search.setSearchBackwards(true);
        selector.setEvaluator(evaluator);
        selector.setSearch(search);
        selector.SelectAttributes(data);
        return selector.reduceDimensionality(data);
    }

    private Instances applyTextProcessing(Instances data) throws Exception {
        StringToWordVector filter = new StringToWordVector();
        filter.setInputFormat(data);
        filter.setIDFTransform(true);
        filter.setOutputWordCounts(true);
        filter.setWordsToKeep(1000);
        filter.setMinTermFreq(5);
        // String attributes: title(0), currency_buyer(3), tags(17), product_color(18),
        // product_variation_size_id(19), shipping_option_name(21), urgency_text(27),
        // origin_country(28), merchant_title(29), merchant_name(30),
        // merchant_info_subtitle(31), merchant_id(34), product_id(36), theme(37)
        filter.setAttributeIndices("1,4,18,19,20,22,28,29,30,31,32,35,37,38");

        filter.setTokenizer(new weka.core.tokenizers.NGramTokenizer());
        weka.core.tokenizers.NGramTokenizer tokenizer = (weka.core.tokenizers.NGramTokenizer) filter.getTokenizer();
        tokenizer.setNGramMinSize(1);
        tokenizer.setNGramMaxSize(2);

        return Filter.useFilter(data, filter);
    }

    private Instances applySMOTE(Instances data) throws Exception {
        if (data.classIndex() < 0) {
            int classIndex = data.attribute("uses_ad_boosts") != null ? data.attribute("uses_ad_boosts").index() : 5;
            data.setClassIndex(classIndex);
        }

        SMOTE smote = new SMOTE();
        smote.setInputFormat(data);
        smote.setPercentage(200);
        return Filter.useFilter(data, smote);
    }
}