package org.itu.demandforecaster;

import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.evaluation.RegressionEvaluator;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.regression.LinearRegression;
import org.apache.spark.ml.tuning.ParamGridBuilder;
import org.apache.spark.ml.tuning.TrainValidationSplit;

/**
 * Created by Group 1.
 */
public class ForecasterPipeline {
    /**
     * Attributes
     */

    protected TrainValidationSplit tvs;

    /**
     * Pipeline class constructor for forecaster. Basically, when creating a new
     * pipeline object it generates necessary parameters and creates
     * new pipeline for data processing.
     *
     */
    public ForecasterPipeline(){
        Preprocessor pp = new Preprocessor();
        LinearRegression linearRegression = new LinearRegression();

        // Parameters for linear regression model.
        ParamMap[] paramGrid = new ParamGridBuilder()
                .addGrid(linearRegression.regParam(), new double[]{0.1, 0.01})
                .addGrid(linearRegression.fitIntercept())
                .addGrid(linearRegression.elasticNetParam(), new double[]{0.0, 0.25, 0.5, 0.75, 1.0})
                .build();

        // Pipeline stages determined by columns that have been processed.
        PipelineStage[] pipelineStages = {pp.categoryIndexer, pp.dayOfWeekEncoder,
                pp.vectorAssembler, linearRegression};
        Pipeline pipeline = new Pipeline()
                .setStages(pipelineStages);

        // Splits training data for validation BUT not for evaluation. Evaluation needs additional data.
        tvs = new TrainValidationSplit()
                .setEstimator(pipeline)
                .setEvaluator(new RegressionEvaluator())
                .setEstimatorParamMaps(paramGrid)
                .setTrainRatio(0.75);
    }
}
