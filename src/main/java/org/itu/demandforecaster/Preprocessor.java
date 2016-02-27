package org.itu.demandforecaster;

/**
 * Created by Group 1.
 */

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

public class Preprocessor {
    public static void main(String[] args) {
        Configuration sparkConfig = Configuration.getSparkConfig();
        JavaSparkContext sc = new JavaSparkContext(sparkConfig.config);
        SQLContext sqlContext = new SQLContext(sc);

        DataFrame df = sqlContext.read()
                .format("com.databricks.spark.csv")
                .option("header", "true")
                .load("transactions.csv");

        df.groupBy("date").count().show();
        df.printSchema();
    }
}
