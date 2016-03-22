package org.itu.demandforecaster;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.ml.tuning.TrainValidationSplitModel;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.GroupedData;
import org.apache.spark.sql.SQLContext;

/**
 * Created by Group 1.
 */

public class DemandForecaster {
    public static void main(String[] args) {
        DemandModeler dm = new DemandModeler();
        SQLContext sqlContext = dm.getPp().getSqlContext();

        // For removing unnecessary info logs.
        Logger logger = Logger.getRootLogger();
        logger.setLevel(Level.OFF);

        // Evaluation
        System.out.println("evaluating linear regression");
        TrainValidationSplitModel lrModel = dm.fitModel();
        System.out.println("Generating predictions");

        DataFrame lrOut = lrModel.transform(dm.getPp().testData)
                .withColumnRenamed("label", "Label")
                .withColumnRenamed("category", "Category")
                .withColumnRenamed("Date", "Day-of-Month")
                .withColumnRenamed("dayOfWeek", "Day-of-Week")
                .withColumnRenamed("prediction", "Amount")
                .select("Label", "Day-of-Month", "Day-of-Week", "Category", "Amount");
        GroupedData groupedData = lrOut.groupBy("Day-of-Month", "Category", "Day-of-Week");
        DataFrame sum = groupedData.sum("Label", "Amount").orderBy("Day-of-Month");
        lrOut.show();
        sum.show();
    }
}
