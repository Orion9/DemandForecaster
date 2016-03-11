package org.itu.demandforecaster;

import org.apache.spark.ml.tuning.TrainValidationSplitModel;
import org.apache.spark.sql.DataFrame;

/**
 * Created by Group 1.
 */

public class DemandForecaster {
    public static void main(String[] args) {
        DemandModeler dm = new DemandModeler();

        System.out.println("evaluating linear regression");
        TrainValidationSplitModel lrModel = dm.fitModel();
        System.out.println("Generating predictions");

        DataFrame lrOut = lrModel.transform(dm.getPp().testData)
                .withColumnRenamed("prediction","Amount")
                .withColumnRenamed("dayOfWeek","dayOfWeek")
                .withColumnRenamed("weekOfYear", "weekOfYear")
                .withColumnRenamed("category", "category")
                .withColumnRenamed("subcategory", "subcategory")
                .select("weekOfYear", "dayOfWeek", "category", "subcategory", "Amount");
        lrOut.show();
    }
}
