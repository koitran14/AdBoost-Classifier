package com.example.algorithms;

import weka.core.Instances;

public interface Algorithm {
    void apply(Instances data) throws Exception;
    String getResults();
}
