package co.com.bancolombia.smokeTest.learning_day_ia.utils;

public class ConfigurationUtils {
    // AWS Configuration, write any aws configuration here
    final public static String lambdaName = "learning_day_ia";


    // Local Configuration, write any local configuration here
    final public static String eventFilePath = "src/test/resources/files/events";

    // Write any logic for configurations, for example getting local files and mapping to s3 paths, or to dynamodb local files.
    
    public static String getEventFilePath(String sceneryType){
        return eventFilePath + "/" + sceneryType + "_event.json";
    }
    
}