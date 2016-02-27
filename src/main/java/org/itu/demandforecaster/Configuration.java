package org.itu.demandforecaster;

import org.apache.ivy.core.module.descriptor.ConfigurationGroup;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.*;

/**
 * Created by Group 1.
 */
public class Configuration {
    public static Configuration sparkConfig = null;
    protected Configuration(){ }

    public static Configuration getSparkConfig(){
        if (sparkConfig == null){
            sparkConfig = new Configuration();
        }

        return sparkConfig;
    }

    SparkConf config = new SparkConf()
            .setMaster("local[4]")
            .setAppName("DemandForecaster");

    JavaSparkContext sc = new JavaSparkContext(config);
}
