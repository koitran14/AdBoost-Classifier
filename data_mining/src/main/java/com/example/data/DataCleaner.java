package com.example.data;

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;

public class DataCleaner {
    public Instances cleanData(Instances rawData) throws Exception {
        ReplaceMissingValues filter = new ReplaceMissingValues();
        filter.setInputFormat(rawData);
        Instances cleanedData = Filter.useFilter(rawData, filter);
        return cleanedData;
    }
}
