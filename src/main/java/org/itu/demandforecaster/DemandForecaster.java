package org.itu.demandforecaster;

import org.apache.spark.sql.DataFrame;

/**
 * Created by Group 1.
 */

public class DemandForecaster {
    public static void main(String[] args) {
        ForecasterPipeline fp = new ForecasterPipeline();
        Preprocessor pp = new Preprocessor();
        pp.loadData();
        DataFrame debug = pp.debug;
        pp.getTestData().printSchema();
        pp.getTestData().show();
    }
}
