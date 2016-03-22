package org.itu.demandforecaster;

import org.apache.spark.ml.tuning.TrainValidationSplit;
import org.apache.spark.ml.tuning.TrainValidationSplitModel;
import org.apache.spark.mllib.evaluation.RegressionMetrics;
import org.apache.spark.sql.DataFrame;

/**
 * Created by Group 1.
 */

public class DemandModeler {

    protected TrainValidationSplit tvs;
    protected DataFrame data;
    protected Preprocessor pp;
    protected ForecasterPipeline fp;

    public DemandModeler(){
        fp = new ForecasterPipeline();
        pp = new Preprocessor();
        pp.loadData();
        data = pp.getTrainingData();
        tvs = fp.getTvs();
    }

    public TrainValidationSplitModel fitModel(){

        System.out.println("Fitting data");
        TrainValidationSplitModel model = tvs.fit(pp.getTrainingData());
        System.out.println("Now performing test on hold out set");

        DataFrame holdout = model.transform(pp.getTestData()).select("prediction","label");

        RegressionMetrics rm = new RegressionMetrics(holdout);
        System.out.println("Test Metrics");
        System.out.println("Test Explained Variance:");
        System.out.println(rm.explainedVariance());
        System.out.println("Test R^2 Coef:");
        System.out.println(rm.r2());
        System.out.println("Test MSE");
        System.out.println(rm.meanSquaredError());
        System.out.println("Test RMSE");
        System.out.println(rm.rootMeanSquaredError());

        return model;
    }

    public TrainValidationSplit getTvs() { return tvs; }

    public void setTvs(TrainValidationSplit tvs) { this.tvs = tvs; }

    public DataFrame getData() { return data; }

    public void setData(DataFrame data) { this.data = data; }

    public Preprocessor getPp() { return pp; }
}
