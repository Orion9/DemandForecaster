package org.itu.demandforecaster;

import org.apache.spark.ml.tuning.TrainValidationSplitModel;
import org.apache.spark.ml.tuning.TrainValidationSplit;
import org.apache.spark.sql.DataFrame;

/**
 * Created by Group 1.
 */

public class DemandForecaster {
    public static void main(String[] args) {
        ForecasterPipeline fp = new ForecasterPipeline();
        Preprocessor pp = new Preprocessor();
        pp.loadData();
        System.out.println("tita2");
        DataFrame debug = pp.debug;
        pp.getTestData().printSchema();
        System.out.println("tita3");
        pp.getTestData().show();
        System.out.println("tita4");


        System.out.println("tita5");
        TrainValidationSplit linearTvs = fp.tvs;
        System.out.println("evaluating linear regression");
        TrainValidationSplitModel lrModel = pp.fitModel(linearTvs,pp.trainingData );
        System.out.println("Generating predictions");

        DataFrame lrOut = lrModel.transform(pp.testData)
                .withColumnRenamed("prediction","Amount")
                .withColumnRenamed("dayOfWeek","dayOfWeek")
                .withColumnRenamed("weekOfYear", "weekOfYear")
                .withColumnRenamed("category", "category")
                .select("weekOfYear", "dayOfWeek", "category","Amount");
        lrOut.show();
    }
}
