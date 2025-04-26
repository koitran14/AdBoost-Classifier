package com.example.evaluation;

import java.io.File;
import java.io.FileWriter;
import java.util.Random;

import javax.swing.JFrame;

import com.example.algorithms.Algorithm;
import com.example.utils.Helpers;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.ThresholdCurve;
import weka.core.Instances;
import weka.core.Utils;
import weka.gui.visualize.PlotData2D;
import weka.gui.visualize.ThresholdVisualizePanel;

public class ModelEvaluator {
    
    /**
        * Evaluates the given model on the provided dataset, writes a report to disk,
        * and visualizes the ROC curve (for a binary classifier).
        * 
        * @param algorithm An instance of the Algorithm that wraps a trained classifier.
        * @param data The test Instances to evaluate the classifier on.
        * @param reportPath The file path where to save the evaluation report.
        * @throws Exception If an error occurs during evaluation or file IO.
    */

    public void evaluateModel(Algorithm algorithm, Instances data, String reportName) throws Exception {
        long startTime = System.nanoTime();
        algorithm.train(data);
        Classifier trainedModel = algorithm.getClassifier();
        Evaluation eval = new Evaluation(data);        
        // Perform cross-validation
        eval.crossValidateModel(trainedModel, data, 10, new Random(1));
        long endTime = System.nanoTime();
        double runtime = (endTime - startTime) / 1_000_000_000.0; 

        System.out.println("Accuracy: " + eval.pctCorrect() + "%");

        Helpers helpers = new Helpers();
        String outputPath = helpers.getOutputPath();

        String absFileName = new File(outputPath, reportName).getPath();
        
        try (FileWriter writer = new FileWriter(absFileName, true)) {
            writer.write("Model: " + algorithm.getClass().getSimpleName() + "\n");
            writer.write("Accuracy: " + eval.pctCorrect() + "%\n");
            writer.write("Precision: " + eval.weightedPrecision() + "\n");
            writer.write("Recall: " + eval.weightedRecall() + "\n");
            writer.write("F1-Score: " + eval.weightedFMeasure() + "\n");
            writer.write("Runtime: " + runtime + " seconds\n");
            writer.write("Confusion Matrix:\n" + eval.toMatrixString() + "\n\n");
        }

        // --- Visualization: Generate ROC Curve for binary classification ---
        // Note: This assumes the classifier produces probability estimates.

        ThresholdCurve tc = new ThresholdCurve();
        int classIndex = 0;
        Instances rocCurveData = tc.getCurve(eval.predictions(), classIndex);
        PlotData2D plotData = new PlotData2D(rocCurveData);
        plotData.setPlotName("ROC Curve");
        plotData.addInstanceNumberAttribute();

        ThresholdVisualizePanel tvp = new ThresholdVisualizePanel();
        double auc = ThresholdCurve.getROCArea(rocCurveData);
        tvp.setROCString("(Area under ROC = " + Utils.doubleToString(auc, 4) + ")");
        tvp.setName("ROC Curve");
        tvp.addPlot(plotData);
        
        JFrame frame = new JFrame("Weka ROC Curve: " + algorithm.getClass().getSimpleName());
        frame.setSize(600, 400);
        frame.getContentPane().add(tvp);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
