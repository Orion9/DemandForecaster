package org.itu.demandforecaster;

import org.apache.spark.SparkConf;

/**
 * Created by Group 1.
 */

public class Configuration {
    public static Configuration sparkConfig = null;

    /**
     * Empty constructor for Configuration singleton.
     */
    protected Configuration(){ }

    /**
     * Returns a singleton instance that encapsulates
     * Spark settings for entire program. Instead of
     * configuring Spark for all components, only an
     * instance of this singleton will be enough.
     *
     * @return      Spark configuration
     * @see         Configuration
     */
    public static Configuration getSparkConfig(){

        if (sparkConfig == null){
            sparkConfig = new Configuration();
        }

        return sparkConfig;
    }

    SparkConf config = new SparkConf()
            .setMaster("local[4]")
            .setAppName("DemandForecaster");
}
